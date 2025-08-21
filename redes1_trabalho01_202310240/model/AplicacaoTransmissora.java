/*************************************************************** <p>
* Autor            : Artur Rodrigues Moura Rocha <p>
* Matricula        : 202310240 <p>
* Inicio           : 21/08/2025 <p>
* Ultima alteracao : 21/08/2025 <p>
* Nome             : AplicacaoTransmissora.java <p>
* Funcao           : Simula a aplicacao de interacao do transmissor
com o usuario <p>
*************************************************************** */

package model;

import javafx.scene.control.TextArea;

/**************************************************************** <p>
* Classe: AplicacaoTransmissora <p>
* Funcao: Simula a aplicacao de interacao com o usuario  <p>
****************************************************************/
public class AplicacaoTransmissora {
  /** caixa de texto responsavel pela entrada de mensagens na aplicacao */
  private TextArea caixaDeTextoEntrada;

  /** camada adjacente no modelo */
  private CamadaAplicacaoTransmissora camadaAplicacaoTransmissora;

  /**************************************************************** <p>
  * Metodo: AplicacaoTransmissora <p>
  * Funcao: construtor da aplicacao do transmissor <p>
  @param caixaDeTextoEntrada caixa de texto utilizado para entrada de
  texto
  ****************************************************************/
  public AplicacaoTransmissora(TextArea caixaDeTextoEntrada) {
    this.caixaDeTextoEntrada = caixaDeTextoEntrada;

    camadaAplicacaoTransmissora = new CamadaAplicacaoTransmissora();
  }

  /**************************************************************** <p>
  * Metodo: aplicacaoTransmissora <p>
  * Funcao: responsavel por receber o texto da caixa de entrada <p>
  @return <code>void</code> n/a
  ****************************************************************/
  public void aplicacaoTransmissora() {
    String mensagem = caixaDeTextoEntrada.getText();
    if (!mensagem.isEmpty()) {
      camadaAplicacaoTransmissora.camadaDeAplicacaoTransmissora(mensagem);
    }
  }
}