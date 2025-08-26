/*************************************************************** <p>
* Autor            : Artur Rodrigues Moura Rocha <p>
* Matricula        : 202310240 <p>
* Inicio           : 26/08/2025 <p>
* Ultima alteracao : 26/08/2025 <p>
* Nome             : ContarBits.java <p>
* Funcao           : Metodo para contar bits de um array de inteiros <p>
*************************************************************** */

package model;

/**************************************************************** <p>
* Classe: ContarBits <p>
* Funcao: Metodo para contar bits de um array de inteiros <p>
****************************************************************/
public class ContarBits {

  /**************************************************************** <p>
  * Metodo: quantidadeDeBitsUteis <p>
  * Funcao: conta quantos bits uteis ha em um vetor de inteiros <p>
  @param vetor vetor de inteiros a ser contado
  @return <code>int</code> quantidade de bits uteis
  ****************************************************************/
  public static int quantidadeDeBitsUteis(int vetor[]) {
    int quantidadeBitsUteis = 0;
    int tamanhoVetor = vetor.length;

    //os n-1 inteiros sempre estao cheio de bits, apenas o ultimo que se deve realizar 
    //analise de quantos bits tem
    quantidadeBitsUteis += (tamanhoVetor - 1) * 32;

    //conta quantos bits uteis tem ate o ultimo inteiro zerar
    while (vetor[tamanhoVetor - 1] != 0) {
      quantidadeBitsUteis += 8;
      vetor[tamanhoVetor - 1] >>>= 8;
    }

    return quantidadeBitsUteis;
  }
}