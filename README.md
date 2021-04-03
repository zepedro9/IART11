# FEUP-IART 20/21 AQUARIUM
## INSTRUÇÕES DE EXECUÇÃO DO PROGRAMA

Para compilar o programa basta executar o seguinte comando no terminal dentro da pasta do projeto:
~~~ 
javaw -jar Aquarium.jar
~~~

## INSTRUÇÕES DE UTILIZAÇÃO DO PROGRAMA

Após a compilação do projeto, abrirá uma janela em que aparece 3 caixas para selecionar:
* **Tipo de Jogador** ("Choose type of player") - Opções: Human ou AI~~~~
* **Tamanho do Puzzle** ("Choose puzzle size") - Opções: 6x6 ou 10x10
* **Algoritmo de Pesquisa** ("(AI only) Choose search algorithm")  caso se tenha selecionado o tipo de jogador "AI" - Opções: Depth-first search, Breadth-first search, Iterative Deepening, Greedy search e A* algorithm.

[<center><img src="https://i.imgur.com/eLs8VTw.png" width="400"></img></center>](https://i.imgur.com/eLs8VTw.png)

<center><em>Figura 1: Menu inicial</em></center>
<br>

Para continuar seleciona-se o **"Start game!"** e abrirá uma janela com o Puzzle Aquarium por preencher.

### Human
Para que as peças do puzzle sejam preenchidas, desloca-se a célula vermelha com as **SETAS** do teclado até a posição desejada e carrega-se no **ENTER**. Caso se cumpra com as restrições ou alguma restrição seja quebrada, o puzzle avisa o jogador com a mudança de cor.
[<center><img src="https://i.imgur.com/5uc0hF8.png" width="400"></img></center>](https://i.imgur.com/eLs8VTw.png)

<center><em>Figura 2: Jogador Humano - Puzzle Incompleto</em></center>
<br>



[<center><img src="https://i.imgur.com/KPrS8zl.png" width="400"></img></center>](https://i.imgur.com/eLs8VTw.png)

<center><em>Figura 3: Jogador Humano - Puzzle Completo</em></center>
<br>

### AI
Para que o puzzle se preencha com o algoritmo selecionado, carrega-se no **ENTER** e começa a resolver. 

[<center><img src="https://i.imgur.com/hSbPqGZ.png" width="400"></img></center>](https://i.imgur.com/eLs8VTw.png)

<center><em>Figura 4: Jogador AI - Puzzle Vazio</em></center>
<br>

Quando estiver concluido é mostrado o número de passos, o tempo em segundos que demorou a resolver o puzzle e o puzzle preenchido.

[<center><img src="https://i.imgur.com/iVIlpi3.png" width="400"></img></center>](https://i.imgur.com/eLs8VTw.png)

<center><em>Figura 5: Jogador AI - Puzzle Completo</em></center>
<br>
 
## ELEMENTOS DO GRUPO 11
* José Rodrigues - up201708806
* Marina Dias - up201806787
* Pedro Vale - up201806083
