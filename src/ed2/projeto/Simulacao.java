package ed2.projeto;

import ed2.projeto.algoritmosordenacao.BubbleSort;
import ed2.projeto.algoritmosordenacao.IAlgoritmoOrdenacao;
import ed2.projeto.algoritmosordenacao.InsertionSort;
import ed2.projeto.algoritmosordenacao.MergeSort;
import ed2.projeto.algoritmosordenacao.QuickSort;
import ed2.projeto.algoritmosordenacao.SelectionSort;
import java.util.Arrays;
import java.util.Random;

public class Simulacao {

    public static void exibirTabela() {
        IAlgoritmoOrdenacao[] algoritmos = {
            new BubbleSort(), new SelectionSort(), new InsertionSort(),
            new MergeSort(), new QuickSort()
        };
        long[] tamanhos = {10, 100, 1000, 10_000_000_000L};
        int[] entrada = new int[1000];
        Random random = new Random(42);

        for (int i = 0; i < entrada.length; i++) {
            entrada[i] = random.nextInt(10000);
        }

        System.out.println("\nSIMULAÇÃO DE TEMPOS - n = 10");
        System.out.println("IA utilizada: ChatGPT (OpenAI Codex).");
        System.out.println("Base medida: mediana de 5 execuções com 1.000 números aleatórios.");
        System.out.println("São feitas 3 execuções de aquecimento por algoritmo.");
        System.out.println("Estimativas em milissegundos para entradas aleatórias.");
        System.out.println("Modelo O(N²) para Bubble, Selection e Insertion.");
        System.out.println("Modelo O(N log N) para Merge e para o caso médio do Quick.");
        System.out.println("As estimativas dependem da máquina e desconsideram limites de memória.");
        System.out.printf("%-15s %15s %15s %15s %15s %15s%n",
                "Algoritmo", "Base (ms)", "n = 10", "n² = 100", "n³ = 1.000", "n^10 = 10^10");

        for (int i = 0; i < algoritmos.length; i++) {
            IAlgoritmoOrdenacao algoritmo = algoritmos[i];

            for (int repeticao = 0; repeticao < 3; repeticao++) {
                algoritmo.ordenarVetor(entrada.clone());
            }

            long[] tempos = new long[5];
            for (int repeticao = 0; repeticao < tempos.length; repeticao++) {
                int[] copia = entrada.clone();
                long inicio = System.nanoTime();
                algoritmo.ordenarVetor(copia);
                tempos[repeticao] = System.nanoTime() - inicio;
            }

            Arrays.sort(tempos);
            double tempoBase = tempos[2] / 1_000_000.0;
            System.out.printf("%-15s %15.6e", algoritmo, tempoBase);

            for (long tamanho : tamanhos) {
                double fator = Math.pow(tamanho / (double) entrada.length, 2);
                if (i >= 3) {
                    fator = (tamanho * Math.log(tamanho))
                            / (entrada.length * Math.log(entrada.length));
                }
                System.out.printf(" %15.6e", tempoBase * fator);
            }
            System.out.println();
        }
    }
}
