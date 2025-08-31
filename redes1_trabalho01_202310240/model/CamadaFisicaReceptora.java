/*************************************************************** <p>
* Autor            : Artur Rodrigues Moura Rocha <p>
* Matricula        : 202310240 <p>
* Inicio           : 21/08/2025 <p>
* Ultima alteracao : 21/08/2025 <p>
* Nome             : CamadaFisicaReceptora.java <p>
* Funcao           : Simular a camada fisica de rede para o 
dispositivo receptor <p>
*************************************************************** */

package model;

import controller.Controlador;

/**************************************************************** <p>
* Classe: CamadaFisicaReceptora <p>
* Funcao: Simular a camada fisica de rede para o 
dispositivo receptor <p>
****************************************************************/
public class CamadaFisicaReceptora {

  /** camada adjacente no modelo */
  CamadaAplicacaoReceptora camadaAplicacaoReceptora = new CamadaAplicacaoReceptora();

  /**************************************************************** <p>
  * Metodo: camadaFisicaReceptora <p>
  * Funcao: decodificar o fluxo bruto de bits oriundos do meio de 
  comunicacao <p>
  @param fluxoBrutoDeBits bits recebidos do meio de comunicacao
  @return <code>void</code> n/a
  ****************************************************************/
  void camadaFisicaReceptora(int fluxoBrutoDeBits[]) {
    int tipoDeDecodificacao;

    try {
      tipoDeDecodificacao = Controlador.getInstance().getProtocoloCamadaFisica();
      int quadro[] = {};

      switch (tipoDeDecodificacao) {
        case 0: //codificao binaria
          quadro = camadaFisicaReceptoraDecodificacaoBinaria(fluxoBrutoDeBits);
          break;
        case 1: //codificacao manchester
          quadro = camadaFisicaReceptoraDecodificacaoManchester(fluxoBrutoDeBits);
          break;
        case 2: //codificacao manchester diferencial
          quadro = camadaFisicaReceptoraDecodificacaoManchesterDiferencial(fluxoBrutoDeBits);
          break;
      }//fim do switch/case

      camadaAplicacaoReceptora.camadaDeAplicacaoReceptora(quadro);
    } catch (Exception e) {
      e.printStackTrace();
    }
  }

  /**************************************************************** <p>
  * Metodo: camadaFisicaReceptoraDecodificacaoBinaria <p>
  * Funcao: decodificacao do fluxo Bruto de bits com protocolo binario <p>
  @param fluxoBrutoDeBits bits a serem decodificados
  @return <code>int[]</code> quadro decodificado
  ****************************************************************/
  private int[] camadaFisicaReceptoraDecodificacaoBinaria(int[] fluxoBrutoDeBits) {
    return fluxoBrutoDeBits;
  }

  /**************************************************************** <p>
  * Metodo: camadaFisicaReceptoraDecodificacaoManchester <p>
  * Funcao: decodificacao do fluxo Bruto de bits com protocolo manchester <p>
  @param fluxoBrutoDeBits bits a serem decodificados
  @return <code>int[]</code> quadro decodificado
  ****************************************************************/
  private int[] camadaFisicaReceptoraDecodificacaoManchester(int[] fluxoBrutoDeBits) {
    //a quantidade de sinais uteis recebida no meio de comunicacao eh sempre o drobro
    //da quantidade de bits da mensagem
    int qtdBitsUteis = ContarBits.quantidadeDeBitsUteis(fluxoBrutoDeBits.clone()) / 2;

    //calcula quantos inteiros de 32 bits sao necessarios para transmitir a mensagem
    //como eh necessario arredondar para cima, faz qtdBitsUteis + 31
    int quadro[] = new int[(qtdBitsUteis + 31) / 32];

    int indiceQuadro = 0;
    int bitsInseridos = 0;

    for (int i = (fluxoBrutoDeBits.length - 1); i >= 0; i--) { //para todos os inteiros recebidos...

      //como na codificacao manchester, SEMPRE ha 2 sinais distintos por bit, eh possivel
      //identificar quando os sinais acabaram em um inteiro (quando seu valor chegar em 0)
      while (fluxoBrutoDeBits[i] != 0) { //enquanto houver bits a serem lidos nesse inteiro...

        //abro espaco no quadro para 1 novo bit
        quadro[indiceQuadro] <<= 1;

        int mascara = 3; //a analise eh feita de 2 em 2 sinais (3 = 11 em binario, por isso consegue ler 2 sinais)
        int sinais = 0 | (mascara & fluxoBrutoDeBits[i]); //armazena os 2 primeiros sinais
        fluxoBrutoDeBits[i] >>>= 2; //avanca para os proximos 2 sinais

        int altoBaixo = (1 << 1);
        // int baixoAlto = 1;

        int bitAInserir = 0;

        if (sinais == altoBaixo) {
          bitAInserir = 1;
        } else {
          bitAInserir = 0;
        }

        //insere o bit no quadro
        quadro[indiceQuadro] |= bitAInserir;
        bitsInseridos++;

        //verifica se o inteiro encheu
        if (bitsInseridos % 32 == 0) {
          indiceQuadro++;
          bitsInseridos = 0;
        }
      }
    }
    return quadro;
  }

  /**************************************************************** <p>
  * Metodo: camadaFisicaReceptoraDecodificacaoManchesterDiferencial <p>
  * Funcao: decodificacao do fluxo Bruto de bits com protocolo manchester diferencial <p>
  @param fluxoBrutoDeBits bits a serem decodificados
  @return <code>int[]</code> quadro decodificado
  ****************************************************************/
  private int[] camadaFisicaReceptoraDecodificacaoManchesterDiferencial(int[] fluxoBrutoDeBits) {

    //a quantidade de sinais uteis recebida no meio de comunicacao eh sempre o drobro
    //da quantidade de bits da mensagem
    int qtdBitsUteis = ContarBits.quantidadeDeBitsUteis(fluxoBrutoDeBits.clone()) / 2;

    //calcula quantos inteiros de 32 bits sao necessarios para transmitir a mensagem
    //como eh necessario arredondar para cima, faz qtdBitsUteis + 31
    int quadro[] = new int[(qtdBitsUteis + 31) / 32];

    //le-se os 2 primeiros sinais e armazena como o anteriores
    int mascara = 3;
    int sinaisAnteriores = (mascara & (fluxoBrutoDeBits[fluxoBrutoDeBits.length - 1]));
    fluxoBrutoDeBits[fluxoBrutoDeBits.length - 1] >>>= 2; //avanca para leitura dos proximos 2 sinais

    //estruturas de controle
    int bitsInseridos = 0; //contador de bits inseridos
    int indiceQuadro = 0;
    int bit;

    for (int i = (fluxoBrutoDeBits.length - 1); i >= 0; i--) { //para todos os inteiros do fluxo bruto de bits...

      while (fluxoBrutoDeBits[i] != 0) { // enquanto houver bits significativos no inteiro ...

        quadro[indiceQuadro] <<= 1; //abre espaco para novo bit

        //le os 2 sinais para analise
        int sinais = mascara & fluxoBrutoDeBits[i];
        fluxoBrutoDeBits[i] >>>= 2;

        if (sinais == sinaisAnteriores) {
          //se a dupla de sinais forem iguais, ha 2 possibilidades:
          //    10 10    ou     01 01
          //em ambos os casos, HA TRANSICAO, e, portanto, o bit eh o 0
          bit = 0;
        } else { // se nao, nao ha transicao, e, portanto, o bit eh o 1
          bit = 1;
        }

        sinaisAnteriores = sinais;

        quadro[indiceQuadro] |= bit;
        bitsInseridos++;

        if (bitsInseridos % 32 == 0) {
          indiceQuadro++;
        }
      }
    }

    int baixoAlto = 1; //sinal 01
    //O ULTIMO PAR DE SINAIS LIDOS ESTA NO PADRAO MANCHESTER, pois foi o primeiro bit a ser codificado no dispositivo transmissor
    if (sinaisAnteriores == baixoAlto)
      bit = 0;
    else
      bit = 1;
    //abre espaco e adiciona o bit no quadro
    quadro[indiceQuadro] <<= 1;
    quadro[indiceQuadro] |= bit;

    return quadro;
  }

}