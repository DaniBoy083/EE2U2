package ed2.projeto.tabelahash;

public class NoAluno {
    public Aluno aluno;
    public NoAluno proximo;

    public NoAluno(Aluno aluno) {
        this.aluno = aluno;
        this.proximo = null;
    }
}