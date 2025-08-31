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
import java.util.concurrent.Semaphore;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextArea;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;
import model.*;

/**************************************************************** <p>
 * Classe: Controlador <p>
 * Funcao: Controle a animacao da interface <p>
 ****************************************************************/
public class Controlador extends Declarador implements Initializable {

  /** sincroniza a transmissao de sinais com a animacao da onda na interface*/
  public static Semaphore sincronizacaoRedeAnimacao = new Semaphore(0);

  /** objeto responsavel pela animacao da onda na interface */
  AnimacaoOnda animacaoOnda;

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

  /** camada de aplicacao do transmissor */
  AplicacaoTransmissora aplicacaoTransmissora;

  /**************************************************************** <p>
  * Metodo: Controlador <p>
  * Funcao: construtor da classe controlador <p>
  @param arquivo arquivo de fxml da interface
  ****************************************************************/
  private Controlador(String arquivo) throws Exception {
    super(arquivo);
  }

  /**************************************************************** <p>
  * Metodo: Controlador <p>
  * Funcao: construtor da classe controlador com css<p>
  @param arquivo arquivo de fxml da interface
  @param css arquivo de estilizacao css
  ****************************************************************/
  private Controlador(String arquivo, String css) throws Exception {
    super(arquivo, css);

    animacaoOnda = new AnimacaoOnda();
    //Declaracao das imagens do fio transmissor de dados    
    for (int i = 0; i < 12; i++) { //adiciona-se 12 paineis de visualizacao da onda na interface
      ImageView temporario = new ImageView(Sinais.LOW.getImagem()); //inicia-se todos com low
      animacaoOnda.add(temporario);
      telaDispositivos.getChildren().add(temporario); //adiciona-se na interface

      //posicionamento do elemento na interface
      temporario.toFront(); //traz pra frente na interface
      AnchorPane.setLeftAnchor(temporario, 140 + i * 50.0);
      AnchorPane.setBottomAnchor(temporario, 121.0);
    }

  }

  /**************************************************************** <p>
  * Metodo: getInstance <p>
  * Funcao: retorna a instancia do singleton do controlador da interface <p>
  @return <code>Declarador</code> retorna o builder do controller
  ****************************************************************/
  public static Controlador getInstance() throws Exception {
    if (Declarador.instancia == null) {
      if (Declarador.getCss().isEmpty()) {
        Declarador.instancia = new Controlador(Declarador.getArquivo());
      } else {
        Declarador.instancia = new Controlador(Declarador.getArquivo(), Declarador.getCss());
      }
    }
    return (Controlador) Declarador.instancia;
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

    //declaracao da aplicacao transmissora
    aplicacaoTransmissora = new AplicacaoTransmissora(caixaDeTextoEntrada);

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
  * Metodo: getProtocoloCamadaFisica <p>
  * Funcao: retorna qual eh o protocolo selecionado pelo usuario para
  a camada fisica <p>
  @return <code>int</code> indice do protocolo gerado
  ****************************************************************/
  public int getProtocoloCamadaFisica() {
    String protocolo = menuProtocolosCamadaFisica.getValue();
    switch (protocolo) {
      case "Binaria":
        return 0;
      case "Manchester":
        return 1;
      case "Manchester Diferencial":
        return 2;
      default:
        return 0;
    }
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

    //como a thread que chama esse metodo eh a thread do Javafx, 
    //eh necessario criar outra responsavel pelo processamento dos protocolos, 
    //pois assim separa-se a thread que realiza a animacao da thread que 
    //aplica os algoritmos de rede. Importante para sincronizar os sinais
    //que sao enviados para a comunicacao
    Thread comunicacao = new Thread() {
      @Override
      public void run() {
        //a aplicacao transmissora lerá a caixa de texto quando o botao for pressionado
        aplicacaoTransmissora.aplicacaoTransmissora();
      }
    };
    comunicacao.start();
  }

  /**************************************************************** <p>
  * Metodo: animarSinal <p>
  * Funcao: envia o sinal do parametro para a animar na interface <p>
  @param sinal sinal a ser animado
  @return <code>void</code> n/a
  ****************************************************************/

  public void animarSinal(int sinal) {
    animacaoOnda.recebeSinal(sinal);
  }

  /**************************************************************** <p>
  * Metodo: adicionarNoChat <p>
  * Funcao: adiciona alguma mensagem no chat da interface do msn <p>
  @param mensagem mensagem a ser adicionada
  @return <code>void</code> n/a
  ****************************************************************/
  public void adicionarNoChat(String mensagem) {
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
  private Text criarMensagem(String mensagem) {
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
