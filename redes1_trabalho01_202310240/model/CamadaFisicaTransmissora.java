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
    int fluxoBrutoDeBits[] = {};
    return fluxoBrutoDeBits;
  }

  /**************************************************************** <p>
  * Metodo: camadaFisicaTransmissoraCodificacaoManchester <p>
  * Funcao: codificacao do quadro com protocolo manchester <p>
  @param quadro quadro a ser codificado
  @return <code>int[]</code> fluxo de bits codificados
  ****************************************************************/
  private int[] camadaFisicaTransmissoraCodificacaoManchester(int[] quadro) {
    int fluxoBrutoDeBits[] = {};
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
}
