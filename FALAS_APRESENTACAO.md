# Roteiro de falas — Estrutura de Dados II

Arquivo de apoio local para a apresentação. Está no `.gitignore` e não integra o commit.

## Divisão e tempo de apresentação

O README lista sete integrantes. Este roteiro considera os sete nomes, mantém Daniel no menu e na integração e Jhonas na tabela hash, conforme a divisão confirmada. Para os outros cinco integrantes, a responsabilidade de apresentação fica distribuída em um algoritmo de ordenação por pessoa. Essa distribuição não atribui autoria individual ao código.

**Tempo sugerido: 14 minutos, com 2 minutos por integrante.** Cada fala tem 205 palavras, contadas por separação em espaços. Reservar aproximadamente 1 minuto e 40 segundos para a fala e 20 segundos para mostrar o código ou a execução. No ensaio, cada pessoa deve respeitar o mesmo limite de tempo. As instruções de demonstração não precisam ser lidas.

| Ordem | Integrante | Responsabilidade na apresentação | Intervalo |
| --- | --- | --- | --- |
| 1 | Daniel Costa Carvalho Martins | Abertura, menu, leitura de arquivo, integração e medição | 00:00–02:00 |
| 2 | Ian Rodrigues Simões Silva | BubbleSort | 02:00–04:00 |
| 3 | Miguel Luis da Silva Santos | SelectionSort | 04:00–06:00 |
| 4 | Marcos Vinicius Silva de Souza | InsertionSort | 06:00–08:00 |
| 5 | Sidney André de Araújo Barbosa | MergeSort | 08:00–10:00 |
| 6 | Igor Marinho dos Santos Silva | QuickSort e leitura da tabela comparativa | 10:00–12:00 |
| 7 | Jhonas de Holanda Rodrigues | Tabela hash, operações e encerramento | 12:00–14:00 |

## 1. Daniel Costa Carvalho Martins — menu e integração

**Mostrar:** `Main.java`, `Utils.lerArquivo()` e a opção 2 do menu principal, implementada em `Simulacao.java`.

### Fala

> Bom dia. Nosso projeto de Estrutura de Dados II reúne cinco algoritmos de ordenação e uma tabela hash para cadastro de alunos. Vou apresentar a organização do programa e como essas partes foram integradas.
>
> A execução começa na classe Main, que tenta carregar os números de um arquivo de texto. O arquivo padrão é dados/numeros.txt, mas podemos informar outro caminho. A leitura aceita espaços, vírgulas e ponto e vírgula como separadores e apresenta mensagens quando encontra um arquivo vazio ou valores inválidos.
>
> O menu permite ordenar os dados, consultar a simulação de tempos, carregar outro arquivo e acessar o cadastro de alunos. Na ordenação, todos os algoritmos seguem a mesma interface. Cada execução recebe uma cópia independente do vetor original, permitindo comparar os métodos com exatamente os mesmos dados.
>
> A entrada e a saída são exibidas, limitadas aos primeiros cem valores. O tempo mostrado nesse menu mede apenas a chamada de ordenação, usando System.nanoTime.
>
> Já a simulação utiliza mil números, três aquecimentos e a mediana de cinco medições. A partir dessa base, projeta tempos para tamanhos maiores. Portanto, medição e estimativa têm significados diferentes. Agora o Ian vai explicar o primeiro algoritmo, mostrando como ele reorganiza os valores e em quais situações seu custo aumenta.

**Demonstração de até 20 segundos:** mostrar o menu e selecionar `2` para exibir a tabela comparativa. Deixar a explicação detalhada dos modelos para as próximas falas.

## 2. Ian Rodrigues Simões Silva — BubbleSort

**Mostrar:** `BubbleSort.java`, especialmente a comparação entre vizinhos, a troca e a variável `isTroca`.

### Fala

> Minha parte é o BubbleSort, um algoritmo que organiza o vetor comparando elementos vizinhos. Quando o valor da esquerda é maior que o da direita, os dois trocam de posição. Esse processo se repete até que uma passagem inteira termine sem nenhuma troca.
>
> No código, a variável isTroca registra se houve alguma alteração durante a passagem. Ela começa como falsa e muda para verdadeira quando acontece uma troca. Assim, o algoritmo consegue reconhecer que o vetor já está ordenado e encerrar o processamento.
>
> Por exemplo, no vetor cinco, dois e quatro, a primeira comparação troca cinco com dois. Depois, cinco troca com quatro. O vetor fica dois, quatro e cinco, e a passagem seguinte confirma que não há mais trocas necessárias.
>
> No melhor caso, quando os valores já estão ordenados, uma passagem basta, com custo linear. Em entradas aleatórias ou no pior caso, o custo é quadrático. Por isso, a simulação do projeto usa o modelo quadrático para esse algoritmo.
>
> Ele utiliza memória auxiliar constante e mantém a ordem relativa de valores iguais, porque só troca quando o primeiro é maior. Sua vantagem didática é permitir acompanhar cada comparação. O Miguel vai apresentar outra estratégia: procurar diretamente o menor elemento antes de realizar a troca.

**Demonstração de até 20 segundos:** no menu principal, selecionar `1` e depois `1`. Apontar a entrada, a saída ordenada e o tempo. Permanecer no submenu de ordenação.

## 3. Miguel Luis da Silva Santos — SelectionSort

**Mostrar:** `SelectionSort.java`, destacando `indiceMenorElemento`, os dois laços e a troca final de cada etapa.

### Fala

> Vou explicar o SelectionSort. A ideia desse algoritmo é selecionar o menor valor da parte ainda desordenada e colocá-lo na posição correta. Depois, ele repete o processo para a próxima posição, até completar o vetor.
>
> Na implementação, o laço externo escolhe a posição que será preenchida. A variável indiceMenorElemento começa apontando para essa posição. O laço interno percorre os elementos seguintes e atualiza esse índice sempre que encontra um valor menor. Ao terminar a busca, o algoritmo troca os elementos quando isso é necessário.
>
> Usando cinco, dois e quatro como exemplo, a primeira busca encontra dois e o coloca no início. Na etapa seguinte, quatro ocupa a segunda posição. Assim, uma parte ordenada cresce da esquerda para a direita.
>
> Mesmo quando o vetor já está ordenado, essa implementação continua procurando o menor elemento no restante dos dados. Por isso, seu custo de comparações é quadrático nos casos melhor, médio e pior.
>
> Em compensação, cada etapa realiza no máximo uma troca, e a memória auxiliar é constante. Na comparação dos resultados, devemos observar também as características da entrada, porque tempo depende de mais fatores que a quantidade de trocas. O Marcos vai mostrar uma abordagem diferente, que insere cada valor dentro de uma parte já ordenada.

**Demonstração de até 20 segundos:** selecionar `2` no submenu de ordenação. Mostrar que o método recebe a mesma entrada original, mesmo depois da execução do BubbleSort.

## 4. Marcos Vinicius Silva de Souza — InsertionSort

**Mostrar:** `InsertionSort.java`, com atenção ao valor salvo em `aux`, ao deslocamento no `while` e à inserção final.

### Fala

> Minha responsabilidade é apresentar o InsertionSort. Podemos imaginar seu funcionamento como organizar cartas na mão: pegamos um novo valor e o colocamos na posição adequada entre os valores que já estão organizados.
>
> O algoritmo começa no segundo elemento. Esse valor é guardado temporariamente na variável aux, enquanto os elementos anteriores que são maiores são deslocados uma posição para a direita. Quando encontra o lugar correto, o valor guardado é inserido no espaço disponível.
>
> No exemplo cinco, dois e quatro, primeiro guardamos dois e deslocamos cinco. O início fica dois e cinco. Depois, guardamos quatro, deslocamos cinco novamente e inserimos quatro entre dois e cinco. O resultado final é crescente.
>
> Quando os dados já estão ordenados, praticamente não há deslocamentos e o custo é linear. Quando estão em ordem inversa, o trabalho cresce de forma quadrática. Para entradas aleatórias, a simulação também utiliza um modelo quadrático.
>
> A implementação usa apenas memória auxiliar constante e preserva a ordem dos valores iguais, porque desloca apenas os elementos estritamente maiores. Isso ajuda a explicar seu comportamento em conjuntos pequenos ou quase ordenados. Ao comparar tempos, precisamos considerar esse perfil da entrada. O Sidney vai apresentar o MergeSort, que utiliza divisão e combinação para trabalhar com as partes do vetor.

**Demonstração de até 20 segundos:** selecionar `3` no submenu de ordenação e apontar a saída crescente. Mostrar no código a condição que impede o deslocamento de elementos iguais.

## 5. Sidney André de Araújo Barbosa — MergeSort

**Mostrar:** `MergeSort.java`, destacando as chamadas recursivas, o vetor `aux` e o método `intercala()`.

### Fala

> Vou apresentar o MergeSort, que utiliza a estratégia de dividir para conquistar. Ele separa o problema em partes menores, ordena essas partes e depois combina os resultados até formar novamente o vetor completo.
>
> Na implementação, calculamos o meio do intervalo e fazemos chamadas recursivas para as duas metades. Quando cada intervalo tem apenas um elemento, ele já está ordenado. A etapa de intercalação compara os menores elementos disponíveis em cada metade e os copia para um vetor auxiliar.
>
> Por exemplo, cinco, dois, quatro e um são divididos em duas partes. Depois da ordenação dessas partes, temos dois e cinco de um lado, um e quatro do outro. A intercalação produz um, dois, quatro e cinco.
>
> Seu custo de tempo é da ordem de N log N, inclusive no pior caso. Nesta implementação, um vetor auxiliar do tamanho da entrada é criado e compartilhado pelas chamadas recursivas. Isso exige memória adicional linear.
>
> Na simulação, o tempo medido com mil números é escalado pela relação entre N log N do tamanho desejado e o da base. Isso é uma projeção, sujeita às condições da máquina e aos limites de memória. O Igor vai mostrar o QuickSort e explicar por que seu desempenho médio também aparece nesse grupo.

**Demonstração de até 20 segundos:** selecionar `4` no submenu de ordenação. Mostrar no código que o vetor auxiliar é passado às chamadas, em vez de ser criado novamente em cada chamada interna.

## 6. Igor Marinho dos Santos Silva — QuickSort e comparação

**Mostrar:** `QuickSort.java` e `Simulacao.java`. Na partição, destacar o pivô central, os índices `i` e `j` e os intervalos recursivos.

### Fala

> Apresentarei o QuickSort. Ele escolhe um pivô e reorganiza os elementos em duas regiões. Depois, aplica o mesmo processo recursivamente em cada região, até que os intervalos não precisem mais ser divididos.
>
> Neste código, o pivô é o valor da posição central do intervalo. Dois índices percorrem o vetor em sentidos opostos, procurando valores que devem mudar de lado. Quando necessário, esses valores são trocados. A partição retorna o limite utilizado pelas próximas chamadas recursivas.
>
> O desempenho médio é da ordem de N log N, mas o pior caso é quadrático quando as divisões ficam muito desequilibradas. Escolher o elemento central não elimina essa possibilidade. O algoritmo também usa a pilha das chamadas recursivas, mesmo sem criar um vetor auxiliar como o MergeSort.
>
> Na tabela comparativa, adotamos o caso médio do QuickSort para entradas aleatórias. Com n igual a dez, as colunas representam dez, cem, mil e dez bilhões de elementos. A última coluna é uma estimativa matemática, não uma execução com esse volume.
>
> A tela identifica ChatGPT, da OpenAI, como a IA utilizada. As projeções partem das medições locais e dos modelos de complexidade, sem garantir tempos exatos. O Jhonas vai concluir apresentando a tabela hash, responsável pelo cadastro e pela consulta dos alunos.

**Demonstração de até 20 segundos:** selecionar `5` para executar QuickSort, depois `0` para voltar ao menu principal e `2` para rever a tabela de tempos.

## 7. Jhonas de Holanda Rodrigues — tabela hash e encerramento

**Mostrar:** `Aluno.java`, `NoAluno.java` e `TabelaHash.java`, principalmente `funcaoHash()`, `inserir()`, `buscar()` e `remover()`.

### Fala

> Vou finalizar com a tabela hash de alunos. Ela possui onze posições e armazena matrícula, nome e idade. A matrícula é a chave, e o endereço corresponde ao resto da divisão dessa chave por onze.
>
> Quando matrículas diferentes produzem o mesmo endereço, acontece uma colisão. O projeto resolve isso com encadeamento separado: cada posição aponta para uma lista de nós. Cada nó guarda um aluno e a referência para o próximo.
>
> As matrículas cento e um, cento e doze e cento e vinte e três, por exemplo, chegam ao índice dois. Os alunos são inseridos no final dessa lista. Antes de inserir, o código percorre a lista para impedir matrículas duplicadas.
>
> A busca calcula o endereço e percorre somente a lista correspondente. A remoção também calcula esse endereço e ajusta as referências, inclusive quando o aluno está no primeiro nó. As três operações exibem o endereço calculado, permitindo acompanhar o funcionamento.
>
> O menu valida matrícula não negativa, nome preenchido e idade positiva. Também permite mostrar a tabela, voltar ao menu principal ou encerrar. Com boa distribuição e poucas colisões, o acesso tende a ser rápido; no pior caso, a lista exige percurso linear. Assim, concluímos a apresentação das ordenações e do cadastro integrado de alunos.

**Demonstração de até 20 segundos:** selecionar `4` no menu principal e `4` no submenu da tabela hash para mostrar a colisão preparada no índice `2`. Apontar os nós na ordem de inserção. A sequência completa abaixo serve para preparação e ensaio.

## Preparação e conferência antes da apresentação

1. Executar `powershell -NoProfile -ExecutionPolicy Bypass -File .\executar.ps1` na raiz do projeto. É necessário JDK 17 ou superior.
2. Confirmar que `dados/numeros.txt` foi carregado. Todos os algoritmos receberão cópias desses mesmos números.
3. Preparar a tabela hash antes do início das falas: no menu principal, selecionar `4` e cadastrar os três alunos abaixo pela opção `1`. Depois, selecionar `5` para voltar ao menu principal. Os alunos continuam em memória enquanto essa execução estiver aberta.

| Ordem de inserção | Matrícula | Nome de exemplo | Idade | Índice esperado |
| --- | --- | --- | --- | --- |
| 1 | 101 | Ana | 20 | 2 |
| 2 | 112 | Bruno | 21 | 2 |
| 3 | 123 | Carla | 22 | 2 |

4. Na fala de Jhonas, exibir a tabela e mostrar a cadeia `101 -> 112 -> 123` no índice `2`. A tela inclui nome e idade de cada aluno.
5. No ensaio, conferir também: tentar inserir `112` novamente e observar a rejeição; buscar `112`; remover `112`; exibir a cadeia `101 -> 123`; buscar `112` novamente e observar que não foi encontrado. Essa conferência completa não faz parte dos 20 segundos de demonstração da fala.
6. Ensaiar com cronômetro: cada integrante dispõe dos mesmos dois minutos, incluindo a transição para o próximo. Não interromper o programa entre as falas, pois os cadastros são mantidos apenas em memória.

## Apoio para perguntas

- **Comparação justa:** o menu clona a entrada original antes de cada ordenação. A cópia e a impressão ficam fora do intervalo cronometrado.
- **Base da simulação:** mil inteiros pseudoaleatórios, gerados com semente `42`; três execuções de aquecimento e mediana de cinco medições para cada algoritmo. A semente reproduz os dados, mas não garante tempos idênticos.
- **Projeção quadrática:** tempo da base multiplicado por `(N / 1000)²`, para BubbleSort, SelectionSort e InsertionSort em entradas aleatórias.
- **Projeção N log N:** tempo da base multiplicado por `(N × log(N)) / (1000 × log(1000))`, para MergeSort e o caso médio de QuickSort.
- **Limite da estimativa:** não há alocação nem ordenação real de dez bilhões de elementos. A projeção desconsidera limites de memória e não deve ser tratada como tempo garantido.
- **Hash implementada:** vetor de onze referências e listas próprias de `NoAluno`; não usa `HashMap`. A função utiliza `Math.floorMod(matricula, 11)`, equivalente ao resto para as matrículas não negativas aceitas pelo menu.
- **Custo da hash:** depende do tamanho das listas. Inserir percorre a lista para verificar duplicidade e chegar ao final; buscar e remover também podem percorrê-la. No pior caso, o custo é linear na quantidade de alunos.
- **Persistência:** os alunos não são gravados em arquivo e desaparecem quando o programa é encerrado.
