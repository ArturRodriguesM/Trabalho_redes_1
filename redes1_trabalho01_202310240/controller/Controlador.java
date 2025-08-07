/* ***************************************************************
* Autor............:  Artur Rodrigues Moura Rocha
* Matricula........:  202310240
* Inicio...........:  04/08/2025 <p>
* Ultima alteracao.:  04/08/2025 <p>
* Nome.............:  Controlador
* Funcao...........:  Controla a animacao da interface
*************************************************************** */

package controller;

import java.net.URL;
import java.util.ResourceBundle;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextArea;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;

/**************************************************************** <p>
 * Classe: Controlador <p>
 * Funcao: Controle a animacao da interface <p>
 ****************************************************************/
public class Controlador extends Declarador implements Initializable {

  /** caixa de texto responsavel pela entrada de mensagens na aplicacao */
  @FXML
  private TextArea caixaDeTextoEntrada;

  /** caixa de texto responsavel pela saida de mensagens na aplciacao  */
  @FXML
  private VBox caixaDeTextoSaida;

  /** barra de rolamento do conteudo da saida de mensagens */
  @FXML
  private ScrollPane scrollPane;

  public Controlador(String arquivo) throws Exception {
    super(arquivo);
  }

  @Override
  public void initialize(URL location, ResourceBundle resources) {
    System.out.println("Controlador declarado");

    caixaDeTextoSaida.heightProperty().addListener((obs, oldVal, newVal) -> {
      scrollPane.setVvalue(1.0); //sempre que for adicionada uma mensagem, faz com que o sroll fique em baixo
    });

  }

  /**************************************************************** <p>
  * Metodo: enviarMensagem <p>
  * Funcao: envia a mensagem da interface para transmissao <p>
  @param event evento de click no botao
  @return <code>void</code> n/a
  *****************************************************************/
  @FXML
  public void enviarMensagem(MouseEvent event) {
    String mensagem = caixaDeTextoEntrada.getText();
    System.out.println(mensagem);
    adicionarNoChat(mensagem);
  }

  /**************************************************************** <p>
  * Metodo: adicionarNoChat <p>
  * Funcao: adiciona alguma mensagem no chat da interface do msn <p>
  @param mensagem mensagem a ser adicionada
  @return <code>void</code> n/a
  ****************************************************************/
  private void adicionarNoChat(String mensagem) {
    Text texto = criarMensagem(mensagem);
    caixaDeTextoSaida.getChildren().add(texto);
    caixaDeTextoEntrada.setText("");
  }

  /**************************************************************** <p>
  * Metodo: criarMensagem <p>
  * Funcao: cria uma mensagem para colocar no chat, especificando 
  detalhes de seu envio <p>
  @param mensagem conteudo da mensagem
  @return <code>Text</code> texto a ser adicionado na interface
  ****************************************************************/

  public Text criarMensagem(String mensagem) {
    Text texto = new Text(mensagem);
    texto.setWrappingWidth(caixaDeTextoSaida.getWidth());

    return texto;
  }

  /**************************************************************** <p>
  * Metodo: limparChat <p>
  * Funcao: limpa o chat do receptor <p>
  @param event evento de ativacao
  @return <code>void</code> n/a
  ****************************************************************/
  @FXML
  void limparChat(MouseEvent event) {
    caixaDeTextoSaida.getChildren().clear();
  }
}
