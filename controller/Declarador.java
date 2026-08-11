
/*************************************************************** <p>
* Autor            : Artur Rodrigues Moura Rocha <p>
* Matricula        : 202310240 <p>
* Inicio           : 04/08/2025 <p>
* Ultima alteracao : 04/08/2025 <p>
* Nome             : Declarador.java <p>
* Funcao           : Constroi um controller para a interface 
javafx com base em um arquivo FXML <p>
*************************************************************** */

package controller;

import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

/**************************************************************** <p>
* Classe: Declarador <p>
* Funcao: Constroi um controller para a interface 
javafx com base em um arquivo FXML <p>
****************************************************************/
public class Declarador {
  private static String arquivo; //nome do arquivo da classe
  private static String css; //nome do arquivo da classe
  private FXMLLoader carregador; //objeto do arquivo fxml
  private AnchorPane elementos; //objeto dos elementos do arquivo fxml
  private Scene cena; //Cena em que o controlador e responsavel
  //O codigo possui um unico objeto do tipo STAGE, no qual eh global para todo o projeto.
  public static Stage g_Palco = new Stage();
  public static Declarador instancia;

  /****************************************************************
  * Metodo: Declarador
  * Funcao: Construtor da classe Declarador. Torna o qualquer objeto 
            declarado como Controlador do arquivo fxml de entrada
  @param arquivo arquivo FXML da interface
  @return <code>N/A</code> novo declarador
  *************************************************************** */
  protected Declarador(String arquivo) throws Exception {
    this(arquivo, "");
  }

  /****************************************************************
  * Metodo: Declarador
  * Funcao: Construtor da classe Declarador. Torna o qualquer objeto 
            declarado como Controlador do arquivo fxml de entrada
  @param arquivo arquivo FXML da interface
  @param css arquivo de estilizacao
  @return <code>N/A</code> novo declarador
  *************************************************************** */
  protected Declarador(String arquivo, String css) throws Exception {
    Declarador.arquivo = arquivo;
    Declarador.css = css;
    carregador = new FXMLLoader(getClass().getResource("../view/" + Declarador.arquivo));
    this.carregador.setController(this);
    elementos = this.carregador.load();
    cena = new Scene(elementos);
    if (!Declarador.css.isEmpty()) {
      cena.getStylesheets().add(getClass().getResource(css).toExternalForm());
    }
  }

  /**************************************************************** <p>
  * Metodo: getInstance <p>
  * Funcao: retorna a instancia do singleton do controlador da interface <p>
  @return <code>Declarador</code> retorna o builder do controller
  ****************************************************************/
  public static Declarador getInstance() throws Exception {
    if (instancia == null) {
      if (css.isEmpty()) {
        instancia = new Declarador(arquivo);
      } else {
        instancia = new Declarador(arquivo, css);
      }
    }
    return instancia;
  }

  /****************************************************************
  * Metodo: Abrir
  * Funcao: Coloca a "cena"(scene) de um certo controlador no "palco" 
            (stage)
  * Parametros: N/A
  * Retorno: void
  *************************************************************** */
  public void abrir() {
    g_Palco.setScene(this.getCena());
    g_Palco.show();
  }

  /****************************************************************
  * Metodos: Getters
  * Funcao: Getters dos atributos da classe
  * Parametros: N/A
  * Retorno: Respectivos atributos
  *************************************************************** */
  public static String getArquivo() {
    return arquivo;
  }

  public FXMLLoader getCarregador() {
    return carregador;
  }

  public AnchorPane getElementos() {
    return elementos;
  }

  public Scene getCena() {
    return cena;
  }

  public static Stage getG_Palco() {
    return g_Palco;
  }

  public static String getCss() {
    return css;
  }

  /****************************************************************
  * Metodos: Setters
  * Funcao: Setters dos atributos da classe
  * Parametros: Respectivos atributos
  * Retorno: VOID
  *************************************************************** */
  public static void setArquivo(String newArquivo) {
    arquivo = newArquivo;
  }

  public void setCarregador(FXMLLoader carregador) {
    this.carregador = carregador;
  }

  public void setElementos(AnchorPane elementos) {
    this.elementos = elementos;
  }

  public void setCena(Scene cena) {
    this.cena = cena;
  }

  public static void setG_Palco(Stage g_Palco) {
    Declarador.g_Palco = g_Palco;
  }

  public static void setCss(String newCss) {
    css = newCss;
  }
}
