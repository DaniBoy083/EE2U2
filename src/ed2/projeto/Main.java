package ed2.projeto;

import ed2.projeto.algoritmosordenacao.BubbleSort;
import ed2.projeto.algoritmosordenacao.IAlgoritmoOrdenacao;
import ed2.projeto.algoritmosordenacao.InsertionSort;
import ed2.projeto.algoritmosordenacao.MergeSort;
import ed2.projeto.algoritmosordenacao.QuickSort;
import ed2.projeto.algoritmosordenacao.SelectionSort;
import ed2.projeto.algoritmosordenacao.Utils;
import java.io.IOException;
import java.util.Scanner;

public class Main {

    public static void main(String[] args) {
        Scanner teclado = new Scanner(System.in);
        int[] numeros = new int[0];
        String arquivo = "dados/numeros.txt";

        if (args.length > 0) {
            arquivo = args[0];
        }

        System.out.println("Estrutura de Dados II - 2ª Unidade");

        try {
            numeros = Utils.lerArquivo(arquivo);
            System.out.println(numeros.length + " números carregados de " + arquivo);
        } catch (IOException | IllegalArgumentException erro) {
            System.out.println("Erro ao carregar os dados. " + erro.getMessage());
        }

        while (true) {
            System.out.println("\nMENU PRINCIPAL");
            System.out.println("1 - Algoritmos de ordenação");
            System.out.println("2 - Simulação de tempos");
            System.out.println("3 - Carregar outro arquivo");
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
                        numeros = Utils.lerArquivo(arquivo);
                        System.out.println(numeros.length + " números carregados.");
                    } catch (IOException | IllegalArgumentException erro) {
                        System.out.println("Erro ao carregar os dados. " + erro.getMessage());
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

    private static int lerOpcao(Scanner teclado) {
        System.out.print("Escolha uma opção: ");
        if (!teclado.hasNextLine()) {
            return 0;
        }

        try {
            return Integer.parseInt(teclado.nextLine().trim());
        } catch (NumberFormatException erro) {
            return -1;
        }
    }
}
