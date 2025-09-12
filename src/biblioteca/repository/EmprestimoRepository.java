package biblioteca.repository;

import biblioteca.model.Emprestimo;
import biblioteca.model.Livro;
import biblioteca.model.Utilizador;

import java.util.ArrayList;
import java.util.List;

public class EmprestimoRepository {

    private List<Emprestimo> emprestimos;

    public EmprestimoRepository(List<Emprestimo> emprestimos) {
        this.emprestimos = emprestimos;
    }

    public void adicionarEmprestimo(Emprestimo emprestimo) {
        this.emprestimos.add(emprestimo);
    }

    public void removerEmprestimo(Emprestimo emprestimo) {
        this.emprestimos.remove(emprestimo);
    }

    public List<Emprestimo> getEmprestimos() {
        return emprestimos;
    }

    public List<Emprestimo> getEmprestimosPorUtilizador(String nomeUtilizador) {
        List<Emprestimo> resultados = new ArrayList<>();
        for (Emprestimo e : emprestimos) {
            if (e.getUtilizador().getNome().equalsIgnoreCase(nomeUtilizador)) {
                resultados.add(e);
            }
        }
        return resultados;
    }

    public List<Emprestimo> getEmprestimosPorLivro(Livro livro) { // metodo que retorna uma lista de emprestimos com base no livro procurado
        List<Emprestimo> resultados = new ArrayList<>();
        for (Emprestimo e : emprestimos) {
            if (e.getLivro().equals(livro)) {
                resultados.add(e);
            }
        }
        return resultados;
    }


}
