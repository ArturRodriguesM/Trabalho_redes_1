/*************************************************************** <p>
* Autor            : Artur Rodrigues Moura Rocha <p>
* Matricula        : 202310240 <p>
* Inicio           : 18/08/2025 <p>
* Ultima alteracao : 18/08/2025 <p>
* Nome             : CamadaFisicaTransmissora.java <p>
* Funcao           : Simular a camada física de rede para o 
dispositivo transmissor <p>
*************************************************************** */
package model;

import controller.Controlador;

/**************************************************************** <p>
* Classe: CamadaFisicaTransmissora <p>
* Funcao: Simular a camada física de rede para o 
dispositivo transmissor <p>
****************************************************************/
public class CamadaFisicaTransmissora {

  /** camada adjacente no modelo */
  private MeioDeComunicacao meioDeComunicacao = new MeioDeComunicacao();

  /**************************************************************** <p>
  * Metodo: camadaFisicaTransmissora <p>
  * Funcao: recebe um quadro de bits e aplcica a codificacao escolhida
  pelo usuario da rede para enviar ao transmissor <p>
  @param quadro quadro de bits que serao transmitidos
  @return <code>void</code> n/a
  ****************************************************************/

  public void camadaFisicaTransmissora(int[] quadro) {
    int tipoDeCodificacao;

    try {
      tipoDeCodificacao = Controlador.getInstance().getProtocoloCamadaFisica();
      int fluxoBrutoDeBits[] = {};

      switch (tipoDeCodificacao) {
        case 0: //codificao binaria
          fluxoBrutoDeBits = camadaFisicaTransmissoraCodificacaoBinaria(quadro);
          break;
        case 1: //codificacao manchester
          fluxoBrutoDeBits = camadaFisicaTransmissoraCodificacaoManchester(quadro);
          break;
        case 2: //codificacao manchester diferencial
          fluxoBrutoDeBits = camadaFisicaTransmissoraCodificacaoManchesterDiferencial(quadro);
          break;
      }//fim do switch/case

      meioDeComunicacao.meioDeComunicacao(fluxoBrutoDeBits);
    } catch (Exception e) {
      e.printStackTrace();
    }
  }

  /**************************************************************** <p>
  * Metodo: camadaFisicaTransmissoraCodificacaoBinaria <p>
  * Funcao: codificacao do quadro com protocolo binario <p>
  @param quadro quadro a ser codificado
  @return <code>int[]</code> fluxo de bits codificados
  ****************************************************************/
  private int[] camadaFisicaTransmissoraCodificacaoBinaria(int[] quadro) {
    return quadro;
  }

  /**************************************************************** <p>
   * Metodo: camadaFisicaTransmissoraCodificacaoManchester <p>
   * Funcao: codificacao do quadro com protocolo manchester <p>
   @param quadro quadro a ser codificado
   @return <code>int[]</code> fluxo de bits codificados
   ****************************************************************/
  private int[] camadaFisicaTransmissoraCodificacaoManchester(int[] quadro) {
    //a codificacao manchester dobra a quantidade de bits uteis transmitidos
    int quantidadeBitsUteis = ContarBits.quantidadeDeBitsUteis(quadro.clone()) * 2;

    //calcula quantos inteiros de 32 bits sao necessarios para transmitir a mensagem
    //como eh necessario arredondar para cima, faz quantidadeBitsUteis + 31
    int[] fluxoBrutoDeBits = new int[(quantidadeBitsUteis + 31) / 32];

    System.out.println("tamanho fluxo bruto de bits " + fluxoBrutoDeBits.length);

    int indiceFluxoBrutoDeBits = 0; //indice para acessar o fluxo bruto de bits
    int qtdBitsInseridos = 0; //conta quantos bits foram inseridos no fluxo bruto de bits

    for (int indiceQuadro = (quadro.length - 1); indiceQuadro >= 0; indiceQuadro--) { //para cada inteiro pertencente ao quadro
      System.out.println(
          "indice quadro: " + indiceQuadro);

      while (quadro[indiceQuadro] != 0) { // enquanto houver bits uteis no inteiro...
        System.out.println(
            " valor quadro: " + Integer.toBinaryString(quadro[indiceQuadro]));
        for (int numBitsLidos = 0; numBitsLidos < 8; numBitsLidos++) { // para cada 8 bits presente no quadro...

          //abre espaco para os 2 novos bits a serem transmitidos
          fluxoBrutoDeBits[indiceFluxoBrutoDeBits] <<= 2;

          //le o primeiro bit do quadro
          int mascara = 1;
          int bit = 0 | (mascara & quadro[indiceQuadro]);

          quadro[indiceQuadro] >>>= 1; //passa para o proximo bit

          int inserir; //armazenara os bits que serao inseridos a depender do bit lido
          if (bit == 0) { //se for 0, entao eh BAIXO-ALTO (01 == 1)
            inserir = 1;
          } else { //se for 1, entao eh ALTO-BAIXO (10 == 3)
            inserir = (1 << 1);
          }

          //depois de decidir o valor, insere ele no fluxo bruto de bits
          fluxoBrutoDeBits[indiceFluxoBrutoDeBits] |= inserir;
          qtdBitsInseridos += 2; // conta +2 bits inseridos

          //verifica se o inteiro encheu
          if (qtdBitsInseridos % 32 == 0) {
            indiceFluxoBrutoDeBits++;
            qtdBitsInseridos = 0;
          }

        }
      }
    }

    for (int valor : fluxoBrutoDeBits) {
      System.out.println(Integer.toBinaryString(valor));
    }

    return fluxoBrutoDeBits;
  }

  /**************************************************************** <p>
   * Metodo: camadaFisicaTransmissoraCodificacaoManchesterDiferencial <p>
   * Funcao: codificacao do quadro com protocolo manchester diferencial <p>
   @param quadro quadro a ser codificado
  @return <code>int[]</code> fluxo de bits codificados
  ****************************************************************/
  private int[] camadaFisicaTransmissoraCodificacaoManchesterDiferencial(int[] quadro) {
    System.out.println("camada fisica transmissora: ");

    //a codificacao manchester dobra a quantidade de bits uteis transmitidos
    int quantidadeBitsUteis = ContarBits.quantidadeDeBitsUteis(quadro.clone()) * 2;

    //calcula quantos inteiros de 32 bits sao necessarios para transmitir a mensagem
    //como eh necessario arredondar para cima, faz quantidadeBitsUteis + 31
    int[] fluxoBrutoDeBits = new int[(quantidadeBitsUteis + 31) / 32];

    //PRIMEIRO - ANALISE DO PRIMEIRO BIT COM BASE NA MANCHESTER
    int mascara = 1;
    int bit = 0 | (mascara & quadro[quadro.length - 1]);
    quadro[quadro.length - 1] >>>= 1;

    int altoBaixo = (1 << 1);
    int baixoAlto = 1;
    int inserir; //sinal a inserir no fluxo bruto de bits

    if (bit == 0) {
      inserir = baixoAlto;
    } else {
      inserir = altoBaixo;
    }

    //insere os sinais no fluxo bruto de bits
    int indiceFluxo = 0;
    fluxoBrutoDeBits[indiceFluxo] |= inserir;
    int sinaisInseridos = 2;

    //para analise da alternancia dos sinais, deve-se analisar o sinal anterior
    int sinalAnterior = inserir;

    int j = 1; //iterador dos bits do quadro (como o primeiro ja foi lido, inicia-se com 1)
    for (int i = (quadro.length - 1); i >= 0; i--) { //para cada inteiro do quadro...
      while (quadro[i] != 0) { //enquanto o inteiro possuir bits significativos...
        for (; j < 8; j++) { //para cada 8 bits...

          //abre espaco para mais 2 sinais no fluxo Bruto de bits
          fluxoBrutoDeBits[indiceFluxo] <<= 2;

          //selecionar bit
          bit = 0 | (mascara & quadro[i]);
          quadro[i] >>>= 1;

          //verificar qual dupla de sinais inserir no fluxo de bits
          if (bit != 0) {
            //se for 1, manter sinal
            //portanto,
            //se os sinais anteriores forem 10, insere-se 01
            //anteriores forem 01, insere-se 10
            if (sinalAnterior == altoBaixo) { //se for 10 
              inserir = baixoAlto;
            } else {
              inserir = altoBaixo;
            }
          }
          //se for 0, fazer inversao de sinal
          //portanto,
          //se os sinais anteriores forem 10, insere-se 10
          //se os sinais anteriores forem 01, insere-se 01 
          //portanto, a dupla de sinais mantem-se a mesma

          fluxoBrutoDeBits[indiceFluxo] |= inserir;
          sinalAnterior = inserir;
          sinaisInseridos += 2;

          if (sinaisInseridos % 32 == 0) { //se o inteiro encheu...
            indiceFluxo++; //passa para o proximo inteiro
          }

        }
        j = 0;
      }
    }

    System.out.println("fluxo bruto de bits: ");
    for (int valor : fluxoBrutoDeBits) {
      System.out.println(Integer.toBinaryString(valor));
    }

    return fluxoBrutoDeBits;
  }

}
