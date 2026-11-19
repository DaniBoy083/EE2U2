# Estrutura de Dados II — 2ª Unidade

Projeto da disciplina de Estrutura de Dados II do curso de Ciência da Computação do Centro Universitário de João Pessoa — UNIPÊ.

**Prazo de entrega em 18/11/2026, às 23h59.** A atividade deve ser realizada em equipe de 5 a 7 alunos e vale até 2,5 pontos da nota do 2º estágio.

[Repositório do projeto](https://github.com/DaniBoy083/EE2U2)[cite: 3]

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

Implementação própria (sem bibliotecas prontas de hashing) de uma **Tabela Hash Encadeada** de tamanho `M = 11` para cadastro de alunos (`matrícula`, `nome` e `idade`), utilizando a função de transformação `h(chave) = chave mod 11`.

- **Tratamento de colisões:** encadeamento separado com inserção do novo aluno no **final** da lista encadeada.
- **Integridade:** bloqueio de cadastro de matrículas duplicadas e cálculo explícito do endereço hash antes de inserir, buscar ou remover.
- **Operações disponíveis:** Inserir novo Aluno, Buscar Aluno, Remover Aluno, Exibir Tabela Hash, Voltar ao menu principal e Encerrar Sistema.

## Status do projeto

- [x] Definir quais integrantes ficarão responsáveis pela tabela hash.
- [x] Obter e ler o arquivo **Projeto - Tabela Hash 5a feira.pptx** para identificar os requisitos dessa etapa.
- [x] Implementar a tabela hash conforme as orientações do material.
- [x] Integrar as opções da tabela hash ao menu principal do projeto.
- [x] Validar as operações da tabela hash e a navegação entre as duas partes do projeto após a integração.
- [x] Atualizar este README com os requisitos e as instruções de uso da tabela hash implementada.
- [x] Revisar o projeto completo e realizar a entrega até **18/11/2026, às 23h59**[cite: 3].

## Como executar

O projeto usa **JDK 17 ou superior** e a biblioteca padrão do Java. O ambiente local foi validado com **OpenJDK Temurin 21.0.10**[cite: 3].

### Pelo terminal do Windows

Abra o PowerShell na pasta do projeto e execute o comando abaixo. A opção `-ExecutionPolicy Bypass` vale para esse processo do PowerShell[cite: 3].

```powershell
powershell -NoProfile -ExecutionPolicy Bypass -File .\executar.ps1
```

### Carregar dados

No menu principal, escolha **3 — Carregar arquivo (números ou alunos)** e informe o caminho, por exemplo `dados/alunos.txt`. O caminho pode ser colado com ou sem aspas; caminhos relativos partem da pasta do projeto ao executar pelo script.

O programa reconhece automaticamente o formato:

- **Números para ordenação:** inteiros separados por espaços, quebras de linha, vírgulas ou ponto e vírgula, como em `dados/numeros.txt`.
- **Alunos para a tabela hash:** arquivo UTF-8, sem cabeçalho, com um aluno por linha no formato `matricula;nome;idade`, como em `dados/alunos.txt`:

```text
101;Ana Silva;20
112;Bruno Santos;22
123;Carla Oliveira;19
```

Para visualizar os alunos importados, escolha **4 — Tabela Hash (Alunos)** no menu principal e depois **4 — Exibir Tabela Hash**.

Também é possível carregar o arquivo de alunos ao iniciar:

```powershell
powershell -NoProfile -ExecutionPolicy Bypass -File .\executar.ps1 -Arquivo dados/alunos.txt
```

Carregar alunos preserva os números já carregados, e carregar números preserva os alunos. Matrículas duplicadas são ignoradas, sem sobrescrever o cadastro existente. Antes de inserir, o programa valida todo o arquivo de alunos: matrícula inteira não negativa, nome não vazio e idade inteira maior que zero. Se houver uma linha inválida, informa o número da linha e não insere alunos daquele arquivo.

Para apresentar o encadeamento com `dados/alunos.txt`, exiba a tabela e observe `101 → 112 → 123` no índice **2**. Use **2 — Buscar Aluno** para buscar a matrícula `123`; depois **3 — Remover Aluno** para remover `112`. Ao exibir novamente, a cadeia será `101 → 123`.
