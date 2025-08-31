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

import controller.Controlador;

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
    //declaracao do fluxo bruto de bits do transmissor
    int fluxoBrutoDeBitsPontoA[];
    fluxoBrutoDeBitsPontoA = fluxoBrutoDeBits;

    //declaracao do fluxo bruto de bits do receptor
    int fluxoBrutoDeBitsPontoB[] = new int[fluxoBrutoDeBits.length];
    for (int i = 0; i < fluxoBrutoDeBits.length; i++) {
      fluxoBrutoDeBitsPontoB[i] = 0;
    }

    // System.out.println("fluxo de bits do ponto A: ");
    // for (int valor : fluxoBrutoDeBitsPontoA) {
    //   System.out.println(Integer.toBinaryString(valor));
    // }

    int qtdBitsSignificativos = ContarBits.quantidadeDeBitsUteis(fluxoBrutoDeBitsPontoA.clone());

    //itera pelos inteiros que armazenam sinais
    int indiceFluxoDeBits = 0;

    int sinal = 0; //armazena sinal por sinal para fazer a transferencia e animar
    int mascara = 1; //seleciona sinal por sinal

    //para todos os bits significativos dos inteiros...
    for (int sinaisInseridos = 1; sinaisInseridos <= qtdBitsSignificativos; sinaisInseridos++) {
      //ESCOVACAO DE BITS

      //primeiro - seleciona o primeiro sinal do fluxo de bits do transmissor
      sinal |= (mascara & fluxoBrutoDeBitsPontoA[indiceFluxoDeBits]);

      //segundo - animacao do sinal 
      try {
        int sinalAnimado = sinal == 0 ? 0 : 1; //indica se o sinal eh alto ou baixo
        Controlador.getInstance().animarSinal(sinalAnimado); //envia o sinal para a interface
        Controlador.sincronizacaoRedeAnimacao.acquire(); //espera a interface terminar a animacao 
      } catch (Exception e) {
        e.printStackTrace();
      }

      //terceiro - adiciona o sinal e faz a mascara avançar para o proximo sinal
      fluxoBrutoDeBitsPontoB[indiceFluxoDeBits] |= sinal;
      mascara <<= 1;
      sinal = 0;

      //quarto - verifica se o inteiro encheu ou nao
      if (sinaisInseridos % 32 == 0) {
        indiceFluxoDeBits++;
        mascara = 1;
      }

    }

    // System.out.println("fluxo de bits do ponto B: ");
    // for (int valor : fluxoBrutoDeBitsPontoB) {
    //   System.out.println(Integer.toBinaryString(valor));
    // }

    camadaFisicaReceptora.camadaFisicaReceptora(fluxoBrutoDeBitsPontoB);
  }
}