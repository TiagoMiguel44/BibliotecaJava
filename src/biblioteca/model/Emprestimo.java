package biblioteca.model;

import java.util.ArrayList;
import java.util.List;
import java.time.LocalDate;

public class Emprestimo {

    private Utilizador utilizador;
    private Livro livro;
    private LocalDate dataEmprestimo;
    private LocalDate dataDevolucao;

    public Emprestimo(Utilizador utilizador, Livro livro, LocalDate dataEmprestimo, LocalDate dataDevolucao) {
        this.utilizador = utilizador;
        this.livro = livro;
        this.dataEmprestimo = dataEmprestimo;
        this.dataDevolucao = dataDevolucao;
    }

    public Emprestimo(Utilizador utilizador, Livro livro) {
        this.utilizador = utilizador;
        this.livro = livro;
        this.dataEmprestimo = LocalDate.now();
        this.dataDevolucao = null; // Inicialmente, o livro não foi devolvido
    }

    public Utilizador getUtilizador() {
        return utilizador;
    }

    public Livro getLivro() {
        return livro;
    }

    public LocalDate getDataEmprestimo() {
        return dataEmprestimo;
    }

    public LocalDate getDataDevolucao() {
        return dataDevolucao;
    }

    public void devolverLivro() {
        this.dataDevolucao = LocalDate.now();
    }

    public boolean isdevolvido() { // Verifica se o livro foi devolvido
        return this.dataDevolucao != null; // Se a data de devolução for diferente de null, o livro foi devolvido
    }

    public String toString() {
        return "Emprestimo{" +
                "utilizador=" + utilizador +
                ", livro=" + livro +
                ", dataEmprestimo=" + dataEmprestimo +
                ", dataDevolucao=" + dataDevolucao +
                '}';
    }


}
