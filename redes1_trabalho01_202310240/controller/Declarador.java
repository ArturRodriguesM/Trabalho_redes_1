
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
  private String arquivo; //nome do arquivo da classe
  private FXMLLoader carregador; //objeto do arquivo fxml
  private AnchorPane elementos; //objeto dos elementos do arquivo fxml
  private Scene cena; //Cena em que o controlador e responsavel
  //O codigo possui um unico objeto do tipo STAGE, no qual eh global para todo o projeto.
  public static Stage g_Palco = new Stage();

  /****************************************************************
  * Metodo: Declarador
  * Funcao: Construtor da classe Declarador. Torna o qualquer objeto 
            declarado como Controlador do arquivo fxml de entrada
  * Parametros: Nome do arquivo fxml a ser controlado; arquivo FXML
  * Retorno: Metodo construtor nao possui retorno
  *************************************************************** */
  public Declarador(String arquivo) throws Exception {
    carregador = new FXMLLoader(getClass().getResource("../view/" + arquivo));
    this.arquivo = arquivo;
    this.carregador.setController(this);
    elementos = this.carregador.load();
    cena = new Scene(elementos);
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
  public String getArquivo() {
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

  /****************************************************************
  * Metodos: Setters
  * Funcao: Setters dos atributos da classe
  * Parametros: Respectivos atributos
  * Retorno: VOID
  *************************************************************** */
  public void setArquivo(String arquivo) {
    this.arquivo = arquivo;
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
}
