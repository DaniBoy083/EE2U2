package ed2.projeto.tabelahash;

import java.util.ArrayList;
import java.util.List;

public final class LeitorAlunos {

    private LeitorAlunos() {
    }

    public static List<Aluno> lerConteudo(String conteudo) {
        if (conteudo.startsWith("\uFEFF")) {
            conteudo = conteudo.substring(1);
        }

        List<Aluno> alunos = new ArrayList<>();
        String[] linhas = conteudo.split("\\R", -1);

        for (int i = 0; i < linhas.length; i++) {
            String linha = linhas[i].trim();
            if (linha.isEmpty()) {
                continue;
            }

            int numeroLinha = i + 1;
            String[] campos = linha.split(";", -1);
            if (campos.length != 3) {
                throw erroLinha(numeroLinha, "use o formato matrícula;nome;idade.");
            }

            int matricula;
            int idade;
            try {
                matricula = Integer.parseInt(campos[0].trim());
                idade = Integer.parseInt(campos[2].trim());
            } catch (NumberFormatException erro) {
                throw erroLinha(numeroLinha, "matrícula e idade devem ser números inteiros.");
            }

            if (matricula < 0) {
                throw erroLinha(numeroLinha, "a matrícula deve ser um inteiro não negativo.");
            }

            String nome = campos[1].trim();
            if (nome.isEmpty()) {
                throw erroLinha(numeroLinha, "o nome do aluno não pode ficar vazio.");
            }

            if (idade <= 0) {
                throw erroLinha(numeroLinha, "a idade deve ser um inteiro positivo.");
            }

            alunos.add(new Aluno(matricula, nome, idade));
        }

        if (alunos.isEmpty()) {
            throw new IllegalArgumentException("O arquivo está vazio.");
        }

        return alunos;
    }

    private static IllegalArgumentException erroLinha(int numeroLinha, String motivo) {
        return new IllegalArgumentException("Linha " + numeroLinha + ": " + motivo);
    }
}
