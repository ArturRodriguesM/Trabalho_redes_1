
/*************************************************************** <p>
* Autor            : Artur Rodrigues Moura Rocha <p>
* Matricula        : 202310240 <p>
* Inicio           : 31/08/2025 <p>
* Ultima alteracao : 31/08/2025 <p>
* Nome             : Sinais.java <p>
* Funcao           : tipo enum que retorna as imagens utilizadas
para a animacao dos sinais na interface <p>
*************************************************************** */

package model;

import javafx.scene.image.Image;

/**************************************************************** <p>
* Classe: Sinais <p>
* Funcao: tipo enum que retorna as imagens utilizadas
para a animacao dos sinais na interface <p>
****************************************************************/
public enum Sinais {
  /** sinal baixo isolado */
  LOW(new Image("img/low.png")),
  /** sinal baixo com transicao */
  LOWTRANSICAO(new Image("img/lowTransicao.png")),
  /** sinal alto isolado */
  HIGH(new Image("img/high.png")),
  /** sinal alto com transicao */
  HIGHTRANSICAO(new Image("img/highTransicao.png"));

  public Image imagem;

  Sinais(Image imagem) {
    this.imagem = imagem;
  }

  public Image getImagem() {
    return imagem;
  }

}
