/*************************************************************** <p>
* Autor            : Artur Rodrigues Moura Rocha <p>
* Matricula        : 202310240 <p>
* Inicio           : 21/08/2025 <p>
* Ultima alteracao : 21/08/2025 <p>
* Nome             : CamadaAplicacaoReceptora.java <p>
* Funcao           : Simular a camada de aplicacao de rede para o
dispositivo receptor <p>
*************************************************************** */

package model;

/**************************************************************** <p>
* Classe: CamadaAplicacaoReceptora <p>
* Funcao: Simular a camada de aplicacao de rede para o
dispositivo receptor <p>
****************************************************************/
public class CamadaAplicacaoReceptora {

  /** camada adjacente no modelo */
  AplicacaoReceptora aplicacaoReceptora = new AplicacaoReceptora();

  /**************************************************************** <p>
  * Metodo: camadaDeAplicacaoReceptora <p>
  * Funcao: transforma o quadro de bits em uma cadeia de caracteres <p>
  @param quadro quadro de entrada
  @return <code>void</code> n/a
  ****************************************************************/
  void camadaDeAplicacaoReceptora(int quadro[]) {
    String mensagem = "";

    aplicacaoReceptora.aplicacaoReceptora(mensagem);
  }
}