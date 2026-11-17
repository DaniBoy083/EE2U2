package ed2.projeto.tabelahash;

public class TabelaHash {
    public static final int TAMANHO_M = 11;
    private final NoAluno[] tabela;

    public TabelaHash() {
        this.tabela = new NoAluno[TAMANHO_M];
    }

    // Função de transformação exigida: h(chave) = chave mod M (M = 11)
    public int funcaoHash(int matricula) {
        return Math.floorMod(matricula, TAMANHO_M);
    }

    public boolean inserir(Aluno novoAluno) {
        int endereco = funcaoHash(novoAluno.getMatricula());
        System.out.println("Endereço calculado pela Função Hash h(" + novoAluno.getMatricula() + ") = " + endereco);

        NoAluno novoNo = new NoAluno(novoAluno);

        // Posição vazia: insere diretamente
        if (tabela[endereco] == null) {
            tabela[endereco] = novoNo;
            return true;
        }

        // Posição ocupada: percorre até o final verificando se a matrícula já existe
        NoAluno atual = tabela[endereco];
        while (true) {
            if (atual.aluno.getMatricula() == novoAluno.getMatricula()) {
                return false; // Matrícula duplicada não permitida
            }
            if (atual.proximo == null) {
                break;
            }
            atual = atual.proximo;
        }

        // Colisão: insere obrigatoriamente no FINAL da lista encadeada
        atual.proximo = novoNo;
        return true;
    }

    public Aluno buscar(int matricula) {
        int endereco = funcaoHash(matricula);
        System.out.println("Endereço calculado pela Função Hash h(" + matricula + ") = " + endereco);

        NoAluno atual = tabela[endereco];
        while (atual != null) {
            if (atual.aluno.getMatricula() == matricula) {
                return atual.aluno;
            }
            atual = atual.proximo;
        }
        return null;
    }

    public boolean remover(int matricula) {
        int endereco = funcaoHash(matricula);
        System.out.println("Endereço calculado pela Função Hash h(" + matricula + ") = " + endereco);

        NoAluno atual = tabela[endereco];
        NoAluno anterior = null;

        while (atual != null) {
            if (atual.aluno.getMatricula() == matricula) {
                if (anterior == null) {
                    tabela[endereco] = atual.proximo;
                } else {
                    anterior.proximo = atual.proximo;
                }
                return true;
            }
            anterior = atual;
            atual = atual.proximo;
        }
        return false;
    }

    public void exibirTabela() {
        System.out.println("\n=== TABELA HASH ENCADEADA (M = " + TAMANHO_M + ") ===");
        for (int i = 0; i < TAMANHO_M; i++) {
            System.out.print("Índice [" + i + "]: ");
            NoAluno atual = tabela[i];
            if (atual == null) {
                System.out.println("null");
            } else {
                while (atual != null) {
                    System.out.print(atual.aluno);
                    if (atual.proximo != null) {
                        System.out.print(" -> ");
                    }
                    atual = atual.proximo;
                }
                System.out.println(" -> null");
            }
        }
    }
}   