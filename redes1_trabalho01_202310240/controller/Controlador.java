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

import javafx.fxml.Initializable;

/**************************************************************** <p>
 * Classe: Controlador <p>
 * Funcao: Controle a animacao da interface <p>
 ****************************************************************/
public class Controlador extends Declarador implements Initializable {

  public Controlador(String arquivo) throws Exception {
    super(arquivo);
  }

  @Override
  public void initialize(URL location, ResourceBundle resources) {
    System.out.println("Controlador declarado");
  }

}
