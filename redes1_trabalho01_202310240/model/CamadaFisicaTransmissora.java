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

    int quantidadeBitsUteis = quantidadeDeBitsUteis(quadro.clone()) * 2;

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
    int fluxoBrutoDeBits[] = {};
    return fluxoBrutoDeBits;
  }

  int quantidadeDeBitsUteis(int vetor[]) {
    int quantidadeBitsUteis = 0;
    int tamanhoVetor = vetor.length;

    //os n-1 inteiros sempre estao cheio de bits, apenas o ultimo que se deve realizar 
    //analise de quantos bits tem
    quantidadeBitsUteis += (tamanhoVetor - 1) * 32;

    //conta quantos bits uteis tem ate o ultimo inteiro zerar
    while (vetor[tamanhoVetor - 1] != 0) {
      quantidadeBitsUteis += 8;
      vetor[tamanhoVetor - 1] >>>= 8;
    }

    return quantidadeBitsUteis;
  }
}
