
/*************************************************************** <p>
* Autor            : Artur Rodrigues Moura Rocha <p>
* Matricula        : 202310240 <p>
* Inicio           : 21/08/2025 <p>
* Ultima alteracao : 21/08/2025 <p>
* Nome             : AplicacaoReceptora.java <p>
* Funcao           : Simula a aplicacao de interacao do receptor
com o usuario  <p>
*************************************************************** */

package model;

import controller.Controlador;
import javafx.application.Platform;

/**************************************************************** <p>
* Classe: AplicacaoReceptora <p>
* Funcao: Simula a aplicacao de interacao do receptor
com o usuario <p>
****************************************************************/
public class AplicacaoReceptora {

  /**************************************************************** <p>
  * Metodo: aplicacaoReceptora <p>
  * Funcao: imprime a mensagem do parametro na interface do receptor <p>
  @param mensagem mensagem a ser mostrada na interface do receptor
  @return <code>void</code> n/a
  ****************************************************************/
  void aplicacaoReceptora(String mensagem) {

    //eh necessario adicionar a mensagem na interface, e, para acessar a thread
    //da interface do javafx, utiliza-se o Platform.runLater();
    Platform.runLater(() -> {
      try {
        Controlador.getInstance().adicionarNoChat(mensagem);
      } catch (Exception e) {
        e.printStackTrace();
      }
    });
  }
}