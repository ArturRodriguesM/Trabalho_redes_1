# Camada Física

Simulação dos protocolos de codificação presentes na camada física do modelo OSI, contextualizadas em uma simulação de comunicação de 2 dispositivos. 

## Sobre o Projeto


Este projeto simula o funcionamento dos protocolos presentes na camada física do modelo OSI de comunicação de redes, observáveis no padrão Ethernet. São eles a codificação binária, manchester e manchester diferendcial. Eles estão contextualizados dentro de uma  simulação de comunicação entre 2 dispositivos (um transmissor e outro receptor), cuja transmissão de sinais é representada na interface. 
Esse projeto foi desenvolvido como parte da disciplina de Redes I do curso de Ciência da Computação.

## Demonstração

[demo.webm](https://github.com/user-attachments/assets/c9a09eac-9d26-4fa4-83e7-62db3ca28a35)

## Arquitetura

O projeto segue o padrão **MVC (Model-View-Controller)** centralizado:

- **Model**: responsável pela lógica dos algoritimos implementados e pelas estruturas de dados da simulação.
- **View**: telas e componentes JavaFX (FXML) responsáveis pela apresentação visual.
- **Controller**: intermediário entre Model e View, tratando eventos da interface e orquestrando as atualizações da simulação.

## Tecnologia Utilizada

- **Java** (versão 1.8.0_482)

## Pré-requisitos

- JDK 1.8.0_482 ou superior instalado

## Como Executar

1. Clone o repositório:
   ```bash
   git clone https://github.com/ArturRodriguesM/camada_fisica.git
   cd Problema-dos-Trens
   ```

2. Compile o projeto:
   ```bash
   javac Principal.java
   ```

3. Execute a aplicação:
   ```bash
   java Principal
   ```

## Como Usar

1. Indique o protocolo a ser utilizado para a transmissão
2. Clique em "Iniciar" para começar a simulação
3. Insira uma mensagem na caixa de texto do dispositivo transmissor e aperte em "Enviar"
4. Utilize o controle disponível para alterar a velocidade da transmissão
5. Visualize a mensagem recebida
