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
import javafx.scene.Group;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextArea;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
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

  /** tela que possui o transmissor, receptor e o fio de comunicacao */
  @FXML
  private AnchorPane telaDispositivos;

  /** tela que possui o botao de iniciar */
  @FXML
  private AnchorPane telaInicio;

  /** choice box para escolher os protocolos da camada fisica */
  @FXML
  private ChoiceBox<String> menuProtocolosCamadaFisica;

  /**************************************************************** <p>
  * Metodo: Controlador <p>
  * Funcao: construtor da classe controlador <p>
  @param arquivo arquivo de fxml da interface
  ****************************************************************/
  public Controlador(String arquivo) throws Exception {
    super(arquivo);
  }

  /**************************************************************** <p>
  * Metodo: Controlador <p>
  * Funcao: construtor da classe controlador com css<p>
  @param arquivo arquivo de fxml da interface
  @param css arquivo de estilizacao css
  ****************************************************************/
  public Controlador(String arquivo, String css) throws Exception {
    super(arquivo, css);
  }

  /**************************************************************** <p>
   * Metodo: initialize <p>
   * Funcao: inicializa a aplicacao e a interface <p>
   @return <code>void</code> n/a
   ****************************************************************/
  @Override
  public void initialize(URL location, ResourceBundle resources) {
    System.out.println("Controlador declarado");

    caixaDeTextoSaida.heightProperty().addListener((obs, oldVal, newVal) -> {
      scrollPane.setVvalue(1.0); //sempre que for adicionada uma mensagem, faz com que o sroll fique em baixo
    });

    //configurando protocolos da caixa de selecao
    menuProtocolosCamadaFisica.getItems().addAll("Binaria", "Manchester", "Manchester Diferencial");
    menuProtocolosCamadaFisica.setValue("Binaria");

    //abre a tela de escolha dos protocolos e fecha a tela dos dispositivos
    telaDispositivos.setVisible(false);
    telaInicio.setVisible(true);
  }

  /**************************************************************** <p>
  * Metodo: escolherProtocolo <p>
  * Funcao: muda a tela para a de escolha do protocolo <p>
  @param event evento que acionou o metodo
  @return <code>void</code> n/a
  ****************************************************************/
  @FXML
  void escolherProtocolo(MouseEvent event) {
    telaDispositivos.setVisible(false);
    telaInicio.setVisible(true);
  }

  /**************************************************************** <p>
  * Metodo: iniciar <p>
  * Funcao: permite o usuario abrir os dispositivos de comunicacao <p>
  @param event evento da ativacao
  @return <code>void</code> n/a
  ****************************************************************/
  @FXML
  void iniciar(MouseEvent event) {
    telaDispositivos.setVisible(true);
    telaInicio.setVisible(false);
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
    if (!mensagem.isEmpty()) {
      System.out.println(mensagem);
      adicionarNoChat(mensagem);
    }
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
    texto.setWrappingWidth(scrollPane.getWidth() - 20);

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
