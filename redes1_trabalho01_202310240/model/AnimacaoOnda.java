/*************************************************************** <p>
* Autor            : Artur Rodrigues Moura Rocha <p>
* Matricula        : 202310240 <p>
* Inicio           : 31/08/2025 <p>
* Ultima alteracao : 31/08/2025 <p>
* Nome             : AnimacaoOnda.java <p>
* Funcao           : anima a onda na interface <p>
*************************************************************** */

package model;

import java.util.LinkedList;
import javafx.scene.image.ImageView;
import javafx.scene.control.Slider;
import javafx.scene.image.Image;
import java.util.ListIterator;

import controller.Controlador;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.event.ActionEvent;
import javafx.util.Duration;

/**************************************************************** <p>
* Classe: AnimacaoOnda <p>
* Funcao: anima a onda na interface <p>
****************************************************************/
public class AnimacaoOnda extends LinkedList<ImageView> {
  /** itera pelos sinais do fio */
  ListIterator<ImageView> sinaisDoFio;
  /** objeto responsavel pela animacao da onda */
  Timeline timeline = new Timeline();
  /** slider de velocidade da onda */
  Slider controleVelocidade;

  /**************************************************************** <p>
  * Metodo: AnimacaoOnda <p>
  * Funcao: construtor da classe AnimacaoOnda <p>
  ****************************************************************/
  public AnimacaoOnda() {
    this(null);
  }

  /**************************************************************** <p>
   * Metodo: AnimacaoOnda <p>
   * Funcao: construtor da classe animacaoOnda <p>
   @param controleVelocidade controla a velocidade da onda
   ****************************************************************/
  public AnimacaoOnda(Slider controleVelocidade) {
    //indica que sempre que a animacao acabar, ele chamara o metodo "enviarFimDaAnimacaoSinal"
    timeline.setOnFinished((ActionEvent e) -> enviarFimDaAnimacaoSinal());
    this.controleVelocidade = controleVelocidade;
  }

  /**************************************************************** <p>
  * Metodo: recebeSinal <p>
  * Funcao: recebe um sinal do meio de comunicacao e insere a imagem
  desse sinal na interface <p>
  @param sinal valor do sinal a ser inserido
  @return <code>void</code> n/a
  ****************************************************************/
  public void recebeSinal(int sinal) {
    Image imagemNova = selecionarImagem(sinal);

    //uso do keyFrame para animar o sinal na interface
    KeyFrame keyFrame = new KeyFrame(getTempo(), (ActionEvent e) -> {
      passaSinalProximo(imagemNova);
    });
    //start da animacao
    timeline.getKeyFrames().add(keyFrame);
    timeline.play();
  }

  /**************************************************************** <p>
  * Metodo: selecionarImagem <p>
  * Funcao: indica qual eh a imagem que deve ser animada para colocar
  na animacao <p>
  @param sinal sinal para ser animado
  @return <code>Image</code> imagem a ser inserida no meio de comunicacao
  ****************************************************************/
  private Image selecionarImagem(int sinal) {
    //seleciona o sinal que esta no meio de comunicacao atual
    ImageView primeiroDoFio = this.getFirst();
    Image retorno = null;

    if (primeiroDoFio.getImage() == Sinais.LOW.getImagem()
        || primeiroDoFio.getImage() == Sinais.LOWTRANSICAO.getImagem()) {
      //se o sinal atual do fio for BAIXO

      if (sinal == 0) { //insere sinal baixo sem transicao
        retorno = Sinais.LOW.getImagem();
      } else { //insere sinal alto com transicao
        retorno = Sinais.HIGHTRANSICAO.getImagem();
      }

    } else {
      //se o sinal atual do fio for ALTO

      if (sinal == 1) { //insere sinal baixo sem transicao
        retorno = Sinais.HIGH.getImagem();
      } else { //insere sinal baixo com transicao
        retorno = Sinais.LOWTRANSICAO.getImagem();
      }

    }

    return retorno;
  }

  /**************************************************************** <p>
  * Metodo: passaSinalProximo <p>
  * Funcao: recebe um sinal e insere no meio de comunicacao <p>
  @param imagemNova sinal n-1 (a ser inserido)
  @return <code>void</code> n/a
  ****************************************************************/
  public void passaSinalProximo(Image imagemNova) {
    ImageView sinalAtual = sinaisDoFio.next(); //guarda o sinal da posicao n do meio de comunicacao
    Image imagemVelha = sinalAtual.getImage();

    sinalAtual.setImage(imagemNova); //passa o sinal que estava na posicao n-1 para a n
    if (sinaisDoFio.hasNext()) { //se ainda ha sinais para atualizar...
      passaSinalProximo(imagemVelha); //passa o sinal n para o n+1
    } else {
      sinaisDoFio = this.listIterator(0);
    }
  }

  /**************************************************************** <p>
  * Metodo: enviarFimDaAnimacaoSinal <p>
  * Funcao: indica quando o sinal foi completamente animado <p>
  @return <code>void</code> n/a
  ****************************************************************/
  public void enviarFimDaAnimacaoSinal() {
    //limpa o objeto de animacao para animar-se novamente
    timeline.getKeyFrames().clear();
    //libera o meio de comunicacao para enviar outro sinal
    Controlador.sincronizacaoRedeAnimacao.release();
  }

  /**************************************************************** <p>
  * Metodo: zerarOnda <p>
  * Funcao: zera a onda quando todos os bits para serem transmitidos
  forem enviados <p>
  @return <code>void</code> n/a
  ****************************************************************/
  public void zerarOnda() {
    KeyFrame keyFrame;
    ImageView primeiroDoFio = this.getFirst();
    Image primeiroSinal;

    //primeiro - analise do sinal que ha na onda no momento
    if (primeiroDoFio.getImage() == Sinais.LOW.getImagem()
        || primeiroDoFio.getImage() == Sinais.LOWTRANSICAO.getImagem()) {
      //se ja tiver um sinal BAIXO na onda, entao deve-se inserir um sinal baixo se transicao
      primeiroSinal = Sinais.LOW.getImagem();
    } else {
      //se tiver um sinal ALTO, entao deve-se inserir um sinal baixo com transicao
      primeiroSinal = Sinais.LOWTRANSICAO.getImagem();
    }

    //adiciona o sinal na fila pra animar
    keyFrame = new KeyFrame(getTempo(), (ActionEvent e) -> {
      passaSinalProximo(primeiroSinal);
    });
    timeline.getKeyFrames().add(keyFrame);

    //para todos os paineis existentes na lista de sinais....
    for (int i = 2; i <= 12; i++) {
      //preenche-se todos com low
      keyFrame = new KeyFrame(getTempo().multiply(i), (ActionEvent e) -> {
        passaSinalProximo(Sinais.LOW.getImagem());
      });
      timeline.getKeyFrames().add(keyFrame);
    }

    //inicia-se a animacao
    timeline.play();

  }

  /**************************************************************** <p>
  * Metodo: add <p>
  * Funcao: adiciona os objetos do tipo ImageView que servirao como
  painel para a exibicao dos sinais na interface <p>
  @param e elemento para se adicionar na lista encadeada
  @return <code>boolean</code> retorna se foi possivel inserir o 
  elemento ou nao
  ****************************************************************/
  @Override
  public boolean add(ImageView e) {
    boolean retorno = super.add(e);
    sinaisDoFio = this.listIterator();
    return retorno;
  }

  /**************************************************************** <p>
  * Metodo: getTempo <p>
  * Funcao: retorna o tempo necessario para animar 1 sinal da onda <p>
  @return <code>Duration</code> tempo em milissegundos
  ****************************************************************/
  public Duration getTempo() {
    return new Duration(1000 / controleVelocidade.getValue());
  }

}