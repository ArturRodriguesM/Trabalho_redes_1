/*************************************************************** <p>
* Autor            : Artur Rodrigues Moura Rocha <p>
* Matricula        : 202310240 <p>
* Inicio           : 18/08/2025 <p>
* Ultima alteracao : 18/08/2025 <p>
* Nome             : CamadaAplicacaoTransmissora.java <p>
* Funcao           : Simular a camada de aplicacao de rede para o
dispositivo transmissor <p>
*************************************************************** */
package model;

/**************************************************************** <p>
* Classe: CamadaAplicacaoTransmissora <p>
* Funcao: Simular a camada de aplicacao de rede para o
dispositivo transmissor <p>
****************************************************************/
public class CamadaAplicacaoTransmissora {

  /** camada adjacente no modelo */
  CamadaFisicaTransmissora camadaFisicaTransmissora = new CamadaFisicaTransmissora();

  CamadaAplicacaoReceptora camadaAplicacaoReceptora = new CamadaAplicacaoReceptora();

  /**************************************************************** <p>
  * Metodo: camadaDeAplicacaoTransmissora <p>
  * Funcao: transforma a cadeia de caracteres em um quadro de bits <p>
  @param mensagem mensagem de entrada
  @return <code>void</code> n/a
  ****************************************************************/
  public void camadaDeAplicacaoTransmissora(String mensagem) {
    //como cada inteiro consegue carregar 4 letras, entao o 
    //numero de inteiros sera a quantidade de letras dividido
    //por 4, porem arredondado para cima (por isso soma-se 3
    //na quantidade de letras)
    int quadro[] = new int[(mensagem.length() + 3) / 4];

    int vetorAscii[] = new int[mensagem.length()];
    for (int i = 0; i < mensagem.length(); i++) {
      vetorAscii[i] = (int) (mensagem.charAt(i));
    }

    int indiceInteiro = 0;
    for (int i = 1; i <= vetorAscii.length * 8; i++) { //deve-se percorrer todos os BITS das letras
      int mascara = 1;

      // como cada inteiro do vetor de ascii possui 8 bits, entao o indice deve ser o valor de i / 8
      // ex.: se i esta no setimo bit, entao ele esta armazenado no inteiro 0 (7/8 = 0)
      int indiceVetorAscii = (i - 1) / 8;

      //abre espaco para o proximo bit entrar no quadro
      quadro[indiceInteiro] <<= 1;

      //le o primeiro bit com a mascara e armazena no quadro
      quadro[indiceInteiro] |= (mascara & vetorAscii[indiceVetorAscii]);

      //busca o proximo bit para ser lido
      vetorAscii[indiceVetorAscii] >>= 1;

      //se foram lidos 32 bits, entao deve-se encher o proximo inteiro
      if (i % 32 == 0) {
        indiceInteiro++;
      }
    }

    // camadaAplicacaoReceptora.camadaDeAplicacaoReceptora(quadro);
    camadaFisicaTransmissora.camadaFisicaTransmissora(quadro);
  }

}
