/*************************************************************** <p>
* Autor            : Artur Rodrigues Moura Rocha <p>
* Matricula        : 202310240 <p>
* Inicio           : 21/08/2025 <p>
* Ultima alteracao : 21/08/2025 <p>
* Nome             : CamadaFisicaReceptora.java <p>
* Funcao           : Simular a camada física de rede para o 
dispositivo receptor <p>
*************************************************************** */

package model;

import controller.Controlador;

/**************************************************************** <p>
* Classe: CamadaFisicaReceptora <p>
* Funcao: Simular a camada física de rede para o 
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
    // TODO Auto-generated method stub
    throw new UnsupportedOperationException("Unimplemented method 'camadaFisicaReceptoraDecodificacaoBinaria'");
  }

  /**************************************************************** <p>
  * Metodo: camadaFisicaReceptoraDecodificacaoManchester <p>
  * Funcao: decodificacao do fluxo Bruto de bits com protocolo manchester <p>
  @param fluxoBrutoDeBits bits a serem decodificados
  @return <code>int[]</code> quadro decodificado
  ****************************************************************/
  private int[] camadaFisicaReceptoraDecodificacaoManchester(int[] fluxoBrutoDeBits) {
    // TODO Auto-generated method stub
    throw new UnsupportedOperationException("Unimplemented method 'camadaFisicaReceptoraDecodificacaoManchester'");
  }

  /**************************************************************** <p>
  * Metodo: camadaFisicaReceptoraDecodificacaoManchesterDiferencial <p>
  * Funcao: decodificacao do fluxo Bruto de bits com protocolo manchester diferencial <p>
  @param fluxoBrutoDeBits bits a serem decodificados
  @return <code>int[]</code> quadro decodificado
  ****************************************************************/
  private int[] camadaFisicaReceptoraDecodificacaoManchesterDiferencial(int[] fluxoBrutoDeBits) {
    // TODO Auto-generated method stub
    throw new UnsupportedOperationException(
        "Unimplemented method 'camadaFisicaReceptoraDecodificacaoManchesterDiferencial'");
  }
}