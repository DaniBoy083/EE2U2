# Estrutura de Dados II — 2ª Unidade

Projeto da disciplina de Estrutura de Dados II do curso de Ciência da Computação do Centro Universitário de João Pessoa — UNIPÊ.

**Prazo de entrega em 18/11/2026, às 23h59.** A atividade deve ser realizada em equipe de 5 a 7 alunos e vale até 2,5 pontos da nota do 2º estágio.

[Repositório do projeto](https://github.com/DaniBoy083/EE2U2)

## Integrantes

| Nome | RGM |
| --- | --- |
| Ian Rodrigues Simões Silva | 42942471 |
| Jhonas de Holanda Rodrigues | 43964206 |
| Miguel Luis da Silva Santos | 44476281 |
| Marcos Vinicius Silva de Souza | 43735282 |
| Sidney André de Araújo Barbosa | 43949851 |
| Igor Marinho dos Santos Silva | 44100035 |
| Daniel Costa Carvalho Martins | 37196201 |

## Requisitos da atividade

### 1. Algoritmos de ordenação — até 1,0 ponto

- Baixar o arquivo ZIP fornecido e importar as classes de ordenação na IDE Java.
- Completar o código das classes fornecidas no material da atividade.
- Implementar a classe com `main()` e um menu para escolher o algoritmo de ordenação.
- É permitido fornecer um conjunto grande de números em um arquivo TXT, lido e carregado para a memória no início do sistema.
- Mostrar os dados de entrada e de saída. Em conjuntos grandes, mostrar apenas os 100 primeiros valores.
- Simular a execução para entradas de `n`, `n²`, `n³` e `n¹⁰`, registrando os tempos estimados em uma tabela comparativa.
- Usar a IA escolhida pela equipe para as simulações, fornecendo os algoritmos, e informar qual IA foi utilizada.

### 2. Tabela hash — até 1,5 ponto

Fazer download do arquivo **Projeto - Tabela Hash 5a feira.pptx** e seguir as orientações do material.

Esta parte aguarda a definição dos responsáveis pela equipe.

## O que falta fazer

A parte de ordenação já conta com os cinco algoritmos integrados, menu interativo, leitura de TXT, exibição limitada aos 100 primeiros valores e simulação de tempos com a IA identificada. A configuração de execução e a tabela comparativa também estão disponíveis.

- [ ] Definir quais integrantes ficarão responsáveis pela tabela hash.
- [ ] Obter e ler o arquivo **Projeto - Tabela Hash 5a feira.pptx** para identificar os requisitos dessa etapa.
- [ ] Implementar a tabela hash conforme as orientações do material.
- [ ] Integrar as opções da tabela hash ao menu principal do projeto.
- [ ] Validar as operações da tabela hash e a navegação entre as duas partes do projeto após a integração.
- [ ] Atualizar este README com os requisitos e as instruções de uso da tabela hash implementada.
- [ ] Revisar o projeto completo e realizar a entrega até **18/11/2026, às 23h59**.

## Como executar

O projeto usa **JDK 17 ou superior** e a biblioteca padrão do Java. O ambiente local foi validado com **OpenJDK Temurin 21.0.10**.

### Pelo terminal do Windows

Abra o PowerShell na pasta do projeto e execute o comando abaixo. A opção `-ExecutionPolicy Bypass` vale para esse processo do PowerShell.

```powershell
powershell -NoProfile -ExecutionPolicy Bypass -File .\executar.ps1
```

O script compila os arquivos Java em `bin/` e abre o menu. Para iniciar com outro arquivo de entrada, informe o caminho pelo parâmetro `-Arquivo`.

```powershell
powershell -NoProfile -ExecutionPolicy Bypass -File .\executar.ps1 -Arquivo "C:\dados\entrada.txt"
```

### Pelo VS Code

Abra a pasta do projeto, utilize o **Extension Pack for Java** indicado nas extensões recomendadas e pressione **F5** com a configuração **Executar projeto ED2**. O menu aparece no terminal integrado.

As configurações em `.vscode/` definem `src/` como pasta dos fontes, `bin/` como saída e UTF-8 como codificação dos arquivos.

### Compilação manual no PowerShell

```powershell
New-Item -ItemType Directory -Path bin -Force | Out-Null
$fontes = Get-ChildItem -Path src -Recurse -Filter *.java | Select-Object -ExpandProperty FullName
javac -encoding UTF-8 --release 17 -d bin $fontes
java -cp bin ed2.projeto.Main
```

## Menu e dados de entrada

```text
MENU PRINCIPAL
1 - Algoritmos de ordenação
2 - Simulação de tempos
3 - Carregar outro arquivo
0 - Sair
```

Na opção de ordenação, escolha **BubbleSort**, **SelectionSort**, **InsertionSort**, **MergeSort** ou **QuickSort**. Use `0` para voltar ao menu principal.

O programa carrega `dados/numeros.txt` ao iniciar. O arquivo de exemplo contém 120 inteiros, incluindo negativos e repetidos. Outros arquivos devem usar UTF-8 e conter números inteiros separados por espaços, quebras de linha, tabulações, vírgulas ou ponto e vírgula.

```text
42 7 -3 18 0
9 42 1 25 -10
```

Cada ordenação recebe uma cópia dos dados carregados. A tela mostra os primeiros 100 números da entrada e da saída, a quantidade total quando houver truncamento e o tempo gasto na ordenação em milissegundos.

Para trocar os dados durante a execução, use a opção **3** do menu principal. Se o carregamento falhar, o programa informa o erro e mantém os últimos dados carregados com sucesso.

## Simulação e tabela comparativa

A IA utilizada foi **ChatGPT (OpenAI Codex)**, com as implementações Java deste repositório como referência. Foi adotado **n = 10** para definir os quatro tamanhos solicitados no enunciado.

A opção **2** do menu refaz a medição de referência e apresenta as estimativas seguindo este procedimento.

1. Gerar 1.000 inteiros aleatórios entre 0 e 9.999 com `Random(42)`.
2. Fazer três execuções de aquecimento para cada algoritmo.
3. Medir cinco execuções com cópias da mesma entrada e usar a mediana como tempo de referência `T(1000)`. A cópia e a impressão ficam fora da medição.
4. Calcular os tempos dos quatro tamanhos a partir dos modelos de crescimento abaixo, usando `N` como quantidade de elementos.

| Algoritmos | Modelo para entradas aleatórias | Fórmula da estimativa |
| --- | --- | --- |
| BubbleSort, SelectionSort e InsertionSort | O(N²) | `T(N) = T(1000) × (N / 1000)²` |
| MergeSort e caso médio do QuickSort | O(N log N) | `T(N) = T(1000) × (N log N) / (1000 log 1000)` |

### Tempos estimados em milissegundos

Valores registrados em uma execução local com Java 21.0.10. A coluna de 1.000 elementos corresponde à mediana medida e serve de referência para as demais colunas. A notação `e` representa potência de dez.

| Algoritmo | n = 10 | n² = 100 | n³ = 1.000 | n¹⁰ = 10.000.000.000 |
| --- | --- | --- | --- | --- |
| BubbleSort | 2,654800e-04 | 2,654800e-02 | 2,654800e+00 | 2,654800e+14 |
| SelectionSort | 5,268000e-05 | 5,268000e-03 | 5,268000e-01 | 5,268000e+13 |
| InsertionSort | 1,216300e-04 | 1,216300e-02 | 1,216300e+00 | 1,216300e+14 |
| MergeSort | 4,560000e-04 | 9,120000e-03 | 1,368000e-01 | 4,560000e+06 |
| QuickSort | 4,426667e-04 | 8,853333e-03 | 1,328000e-01 | 4,426667e+06 |

As projeções consideram o crescimento assintótico, desconsiderando os custos fixos da JVM e os limites de memória. Os resultados variam com a máquina e com a distribuição dos dados. O tamanho `n¹⁰` é usado no cálculo matemático da estimativa.

## Organização dos arquivos

```text
EE2U2/
├── .vscode/                     Configuração de execução no VS Code
├── dados/
│   └── numeros.txt              Entrada de exemplo com 120 números
├── src/ed2/projeto/
│   ├── Main.java                Menu principal e escolha da ordenação
│   ├── Simulacao.java           Medição de referência e estimativas
│   └── algoritmosordenacao/
│       ├── BubbleSort.java
│       ├── IAlgoritmoOrdenacao.java
│       ├── InsertionSort.java
│       ├── MergeSort.java
│       ├── QuickSort.java
│       ├── SelectionSort.java
│       └── Utils.java           Leitura, impressão e geração de vetores
├── executar.ps1                 Compilação e execução pelo PowerShell
└── readme.md
```

As classes fornecidas no material mantêm o crédito original a **Hilario Tomaz Alves de Oliveira**. O MergeSort reutiliza um único vetor auxiliar durante cada ordenação.

## Composição da nota da 2ª Unidade

| Atividade | Pontuação |
| --- | --- |
| Kahoot, por participação | 0,5 ponto |
| Lista de exercícios | 1,0 ponto |
| Projeto | 2,5 pontos |
| Avaliação, prova online | 1,0 ponto |
