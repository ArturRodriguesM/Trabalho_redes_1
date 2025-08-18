
/* ***************************************************************
* Autor............:  Artur Rodrigues Moura Rocha
* Matricula........:  202310240
* Inicio...........:  25/08/2024
* Ultima alteracao.:  08/09/2024
* Nome.............:  Principal
* Funcao...........:  Iniciar a aplicacao do Javafx
*************************************************************** */

import controller.*;
import javafx.application.Application;
import javafx.scene.image.Image;
import javafx.scene.text.Font;
import javafx.stage.Stage;

/**************************************************************** <p>
* Classe: Principal <p>
* Funcao: Inicias a aplicacado do javafx <p>
****************************************************************/
public class Principal extends Application {
  public static void main(String args[]) throws Exception {
    launch(args);
    System.exit(0);
  }

  /****************************************************************
  * Metodo: start (metodo herdado de Application)
  * Funcao: Iniciar a aplicacao JavaFX
  * Parametros: palco
  * Retorno: VOID
  *************************************************************** */
  @Override
  public void start(Stage palco) throws Exception {
    // Font.loadFont(Principal.class.getResourceAsStream("view/ARIAL.ttf"), 20);
    Controlador controladorInterface = new Controlador("Interface.fxml", "../index.css");
    palco = Declarador.getG_Palco();
    palco.setTitle("MSN (Marlos Sem Net)");
    palco.getIcons().add(new Image("./img/icone_msn_w2.png"));
    palco.setResizable(false);
    palco.sizeToScene();
    controladorInterface.abrir();
  }
}
