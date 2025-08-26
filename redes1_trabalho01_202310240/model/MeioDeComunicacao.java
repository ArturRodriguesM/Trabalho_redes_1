/*************************************************************** <p>
* Autor            : Artur Rodrigues Moura Rocha <p>
* Matricula        : 202310240 <p>
* Inicio           : 21/08/2025 <p>
* Ultima alteracao : 21/08/2025 <p>
* Nome             : MeioDeComunicacao.java <p>
* Funcao           : Simular a transmissoa de dados pelo meio de 
comunicacao <p>
*************************************************************** */

package model;

/**************************************************************** <p>
* Classe: MeioDeComunicacao <p>
* Funcao: Simular a transmissoa de dados pelo meio de 
comunicacao <p>
****************************************************************/
public class MeioDeComunicacao {

  /** camada adjacente no modelo */
  CamadaFisicaReceptora camadaFisicaReceptora = new CamadaFisicaReceptora();

  /**************************************************************** <p>
  * Metodo: meioDeComunicacao <p>
  * Funcao: transmite o fluxo bruto de bits gerado pela camada fisica
  para o receptor <p>
  @param fluxoBrutoDeBits fluxo de bits gerado pela camada fisica 
  transmissora
  @return <code>void</code> n/a
  ****************************************************************/
  void meioDeComunicacao(int fluxoBrutoDeBits[]) {
    int fluxoBrutoDeBitsPontoA[], fluxoBrutoDeBitsPontoB[] = new int[fluxoBrutoDeBits.length];
    fluxoBrutoDeBitsPontoA = fluxoBrutoDeBits;
    //TODO: fazer passagem de bit a bit
    int qtdBitsSignificativos = fluxoBrutoDeBits.length;

    for (int i = 0; i < qtdBitsSignificativos; i++) {
      //escovacao de bits
      fluxoBrutoDeBitsPontoB[i] = fluxoBrutoDeBitsPontoA[i];
    }

    camadaFisicaReceptora.camadaFisicaReceptora(fluxoBrutoDeBitsPontoB);
  }
}