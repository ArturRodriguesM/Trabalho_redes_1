/*************************************************************** <p>
* Autor            : Artur Rodrigues Moura Rocha <p>
* Matricula        : 202310240 <p>
* Inicio           : 18/08/2025 <p>
* Ultima alteracao : 18/08/2025 <p>
* Nome             : CamadaAplicacaoTransmissora.java <p>
* Funcao           : Simular a camada de aplicacao de rede para o
dispositivo transmissor <p>
*************************************************************** */
package model;

/**************************************************************** <p>
* Classe: CamadaAplicacaoTransmissora <p>
* Funcao: Simular a camada de aplicacao de rede para o
dispositivo transmissor <p>
****************************************************************/
public class CamadaAplicacaoTransmissora {

  /** camada adjacente no modelo */
  CamadaFisicaTransmissora camadaFisicaTransmissora = new CamadaFisicaTransmissora();

  /**************************************************************** <p>
  * Metodo: camadaDeAplicacaoTransmissora <p>
  * Funcao: transforma a cadeia de caracteres em um quadro de bits <p>
  @param mensagem mensagem de entrada
  @return <code>void</code> n/a
  ****************************************************************/
  public void camadaDeAplicacaoTransmissora(String mensagem) {
    int quadro[] = {};

    camadaFisicaTransmissora.camadaFisicaTransmissora(quadro);
  }

}
