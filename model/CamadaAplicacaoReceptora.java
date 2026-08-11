/*************************************************************** <p>
* Autor            : Artur Rodrigues Moura Rocha <p>
* Matricula        : 202310240 <p>
* Inicio           : 21/08/2025 <p>
* Ultima alteracao : 21/08/2025 <p>
* Nome             : CamadaAplicacaoReceptora.java <p>
* Funcao           : Simular a camada de aplicacao de rede para o
dispositivo receptor <p>
*************************************************************** */

package model;

/**************************************************************** <p>
* Classe: CamadaAplicacaoReceptora <p>
* Funcao: Simular a camada de aplicacao de rede para o
dispositivo receptor <p>
****************************************************************/
public class CamadaAplicacaoReceptora {

  /** camada adjacente no modelo */
  AplicacaoReceptora aplicacaoReceptora = new AplicacaoReceptora();

  /**************************************************************** <p>
  * Metodo: camadaDeAplicacaoReceptora <p>
  * Funcao: transforma o quadro de bits em uma cadeia de caracteres <p>
  @param quadro quadro de entrada
  @return <code>void</code> n/a
  ****************************************************************/
  void camadaDeAplicacaoReceptora(int quadro[]) {
    String mensagem = "";
    // int quantidadeBitsUteis = quantidadeDeBitsUteis(quadro);

    for (int i = (quadro.length - 1); i >= 0; i--) { //repete para cada inteiro do quadro

      while (quadro[i] != 0) { //enquanto o inteiro sendo lido tiver bits significativos...

        int ascii = 0;
        for (int j = 0; j < 8; j++) { //percorre a cada 8 bits e armazena em um inteiro ascii
          int mascara = 1;

          //abre espaco para mais bits pro ascii
          ascii <<= 1;

          // le o primeiro bit do quadro
          ascii |= (mascara & quadro[i]);

          //passa para o proximo bit
          quadro[i] >>>= 1;
        }

        //adiciona o caractere na mensagem
        mensagem = ((char) ascii) + mensagem;
      }
    }

    aplicacaoReceptora.aplicacaoReceptora(mensagem);
  }

}