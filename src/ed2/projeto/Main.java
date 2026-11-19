package ed2.projeto;

import ed2.projeto.algoritmosordenacao.BubbleSort;
import ed2.projeto.algoritmosordenacao.IAlgoritmoOrdenacao;
import ed2.projeto.algoritmosordenacao.InsertionSort;
import ed2.projeto.algoritmosordenacao.MergeSort;
import ed2.projeto.algoritmosordenacao.QuickSort;
import ed2.projeto.algoritmosordenacao.SelectionSort;
import ed2.projeto.algoritmosordenacao.Utils;
import ed2.projeto.tabelahash.Aluno;
import ed2.projeto.tabelahash.LeitorAlunos;
import ed2.projeto.tabelahash.TabelaHash;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.List;
import java.util.Scanner;

public class Main {

    public static void main(String[] args) {
        Scanner teclado = new Scanner(System.in);
        int[] numeros = new int[0];
        String arquivo = "dados/numeros.txt";
        TabelaHash tabelaHash = new TabelaHash();

        if (args.length > 0) {
            arquivo = args[0];
        }

        System.out.println("Estrutura de Dados II - 2ª Unidade");

        try {
            numeros = carregarArquivo(arquivo, numeros, tabelaHash);
        } catch (IOException | IllegalArgumentException erro) {
            System.out.println("Erro ao carregar os dados. " + erro.getMessage());
        }

        while (true) {
            System.out.println("\nMENU PRINCIPAL");
            System.out.println("1 - Algoritmos de ordenação");
            System.out.println("2 - Simulação de tempos");
            System.out.println("3 - Carregar arquivo (números ou alunos)");
            System.out.println("4 - Tabela Hash (Alunos)");
            System.out.println("0 - Sair");

            int opcao = lerOpcao(teclado);

            switch (opcao) {
                case 1:
                    menuOrdenacao(teclado, numeros);
                    break;
                case 2:
                    Simulacao.exibirTabela();
                    break;
                case 3:
                    System.out.print("Caminho do arquivo TXT: ");
                    if (!teclado.hasNextLine()) {
                        teclado.close();
                        return;
                    }
                    arquivo = teclado.nextLine().trim();
                    try {
                        numeros = carregarArquivo(arquivo, numeros, tabelaHash);
                    } catch (IOException | IllegalArgumentException erro) {
                        System.out.println("Erro ao carregar os dados. " + erro.getMessage());
                    }
                    break;
                case 4:
                    if (menuTabelaHash(teclado, tabelaHash)) {
                        System.out.println("Programa encerrado.");
                        teclado.close();
                        return;
                    }
                    break;
                case 0:
                    System.out.println("Programa encerrado.");
                    teclado.close();
                    return;
                default:
                    System.out.println("Opção inválida. Tente novamente.");
            }
        }
    }

    private static int[] carregarArquivo(String caminho, int[] numerosAtuais, TabelaHash tabelaHash)
            throws IOException {
        caminho = caminho.trim();
        if (caminho.length() >= 2 && ((caminho.startsWith("\"") && caminho.endsWith("\""))
                || (caminho.startsWith("'") && caminho.endsWith("'")))) {
            caminho = caminho.substring(1, caminho.length() - 1);
        }
        if (caminho.isBlank()) {
            throw new IllegalArgumentException("Informe o caminho do arquivo TXT.");
        }

        Path arquivo = Path.of(caminho).toAbsolutePath().normalize();
        String conteudo = Files.readString(arquivo, StandardCharsets.UTF_8);
        int[] numerosCarregados;

        try {
            // Números também podem ser separados por ponto e vírgula.
            numerosCarregados = Utils.lerConteudo(conteudo);
        } catch (IllegalArgumentException erroNumeros) {
            if (!conteudo.contains(";")) {
                throw erroNumeros;
            }

            // Valida todas as linhas antes de modificar a tabela existente.
            List<Aluno> alunos = LeitorAlunos.lerConteudo(conteudo);
            int inseridos = 0;
            int duplicados = 0;
            for (Aluno aluno : alunos) {
                if (tabelaHash.inserir(aluno)) {
                    inseridos++;
                } else {
                    duplicados++;
                }
            }

            System.out.println(inseridos + " alunos carregados na tabela hash de " + arquivo);
            if (duplicados > 0) {
                System.out.println(duplicados + " matrículas duplicadas ignoradas.");
            }
            return numerosAtuais;
        }

        System.out.println(numerosCarregados.length + " números carregados de " + arquivo);
        return numerosCarregados;
    }

    private static void menuOrdenacao(Scanner teclado, int[] numeros) {
        if (numeros.length == 0) {
            System.out.println("Carregue um arquivo com números antes de ordenar.");
            return;
        }

        while (true) {
            System.out.println("\nALGORITMOS DE ORDENAÇÃO");
            System.out.println("1 - BubbleSort");
            System.out.println("2 - SelectionSort");
            System.out.println("3 - InsertionSort");
            System.out.println("4 - MergeSort");
            System.out.println("5 - QuickSort");
            System.out.println("0 - Voltar");

            IAlgoritmoOrdenacao algoritmo;
            int opcao = lerOpcao(teclado);

            switch (opcao) {
                case 1:
                    algoritmo = new BubbleSort();
                    break;
                case 2:
                    algoritmo = new SelectionSort();
                    break;
                case 3:
                    algoritmo = new InsertionSort();
                    break;
                case 4:
                    algoritmo = new MergeSort();
                    break;
                case 5:
                    algoritmo = new QuickSort();
                    break;
                case 0:
                    return;
                default:
                    System.out.println("Opção inválida. Tente novamente.");
                    continue;
            }

            // Cada algoritmo recebe uma cópia dos mesmos dados de entrada.
            int[] copia = numeros.clone();
            System.out.println("\nAlgoritmo escolhido: " + algoritmo);
            System.out.println("Entrada:");
            Utils.imprimirVetor(copia);

            long inicio = System.nanoTime();
            algoritmo.ordenarVetor(copia);
            double tempo = (System.nanoTime() - inicio) / 1_000_000.0;

            System.out.println("\nSaída:");
            Utils.imprimirVetor(copia);
            System.out.printf("%nTempo de ordenação: %.6f ms%n", tempo);
        }
    }

    private static boolean menuTabelaHash(Scanner teclado, TabelaHash tabelaHash) {
        while (true) {
            System.out.println("\nTABELA HASH - CADASTRO DE ALUNOS");
            System.out.println("1 - Inserir novo Aluno");
            System.out.println("2 - Buscar Aluno");
            System.out.println("3 - Remover Aluno");
            System.out.println("4 - Exibir Tabela Hash");
            System.out.println("5 - Voltar ao menu principal");
            System.out.println("0 - Encerrar Sistema");

            int opcao = lerOpcao(teclado);

            switch (opcao) {
                case 1:
                    System.out.print("Matrícula (inteiro): ");
                    Integer matricula = lerInteiro(teclado);
                    if (matricula == null || matricula < 0) {
                        System.out.println("Matrícula inválida. Informe um número inteiro não negativo.");
                        break;
                    }

                    System.out.print("Nome do aluno: ");
                    String nome = teclado.hasNextLine() ? teclado.nextLine().trim() : "";
                    if (nome.isEmpty()) {
                        System.out.println("O nome do aluno não pode ficar vazio.");
                        break;
                    }

                    System.out.print("Idade do aluno: ");
                    Integer idade = lerInteiro(teclado);
                    if (idade == null || idade <= 0) {
                        System.out.println("Idade inválida.");
                        break;
                    }

                    Aluno novoAluno = new Aluno(matricula, nome, idade);
                    if (tabelaHash.inserir(novoAluno)) {
                        System.out.println("Aluno cadastrado com sucesso!");
                    } else {
                        System.out.println("Erro: já existe um aluno cadastrado com a matrícula " + matricula + ".");
                    }
                    break;

                case 2:
                    System.out.print("Matrícula do aluno para busca: ");
                    Integer matBusca = lerInteiro(teclado);
                    if (matBusca == null || matBusca < 0) {
                        System.out.println("Matrícula inválida.");
                        break;
                    }

                    Aluno encontrado = tabelaHash.buscar(matBusca);
                    if (encontrado != null) {
                        System.out.println("Aluno encontrado: " + encontrado);
                    } else {
                        System.out.println("Aluno com matrícula " + matBusca + " não encontrado.");
                    }
                    break;

                case 3:
                    System.out.print("Matrícula do aluno para remoção: ");
                    Integer matRemocao = lerInteiro(teclado);
                    if (matRemocao == null || matRemocao < 0) {
                        System.out.println("Matrícula inválida.");
                        break;
                    }

                    if (tabelaHash.remover(matRemocao)) {
                        System.out.println("Aluno removido com sucesso!");
                    } else {
                        System.out.println("Aluno com matrícula " + matRemocao + " não encontrado.");
                    }
                    break;

                case 4:
                    tabelaHash.exibirTabela();
                    break;

                case 5:
                    return false;

                case 0:
                    return true;

                default:
                    System.out.println("Opção inválida. Tente novamente.");
            }
        }
    }

    private static int lerOpcao(Scanner teclado) {
        System.out.print("Escolha uma opção: ");
        Integer valor = lerInteiro(teclado);
        return valor != null ? valor : -1;
    }

    private static Integer lerInteiro(Scanner teclado) {
        if (!teclado.hasNextLine()) {
            return null;
        }
        try {
            return Integer.parseInt(teclado.nextLine().trim());
        } catch (NumberFormatException erro) {
            return null;
        }
    }
}
