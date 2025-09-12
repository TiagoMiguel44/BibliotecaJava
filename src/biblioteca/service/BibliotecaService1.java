package biblioteca.service;

import biblioteca.model.Livro;
import biblioteca.model.Utilizador;
import biblioteca.model.Emprestimo;
import biblioteca.repository.LivroRepository;
import biblioteca.repository.UtilizadorRepository;
import biblioteca.repository.EmprestimoRepository;

import java.util.ArrayList;
import java.util.List;

public class BibliotecaService1 {

    private static BibliotecaService1 instancia;

    private LivroRepository livroRepo;
    private UtilizadorRepository utilizadorRepo;
    private EmprestimoRepository emprestimoRepo;

    private List<Observer> observers;

    private BibliotecaService1() {
        this.livroRepo = new LivroRepository();
        this.utilizadorRepo = new UtilizadorRepository();
        this.emprestimoRepo = new EmprestimoRepository(new ArrayList<>());
        this.observers = new ArrayList<>();
    }

    public static BibliotecaService1 getInstancia() {
        if (instancia == null) {
            instancia = new BibliotecaService1();
        }
        return instancia;
    }

    // -------------------- Observer --------------------
    public void registarObservador(Observer observer) {
        observers.add(observer);
    }

    public void notificarObservadores(String mensagem) {
        for (Observer observer : observers) {
            observer.atualizar(mensagem);
        }
    }

    // -------------------- Livros --------------------
    public void adicionarLivro(Livro livro) {
        livroRepo.adicionarLivro(livro);
        notificarObservadores("Novo livro adicionado: " + livro.getTitulo());
    }

    public List<Livro> getLivrosDisponiveis() {
        List<Livro> disponiveis = new ArrayList<>();
        for (Livro l : livroRepo.getLivros()) {
            if (emprestimoRepo.getEmprestimosPorLivro(l).isEmpty()) {
                disponiveis.add(l);
            }
        }
        return disponiveis;
    }

    public List<Livro> getLivrosporTitulo(String titulo) {
        return livroRepo.getLivrosporTitulo(titulo);
    }

    public List<Livro> getLivrosporAutor(String autor) {
        return livroRepo.getLivrosporAutor(autor);
    }

    // -------------------- Utilizadores --------------------
    public void adicionarUtilizador(Utilizador utilizador) {
        utilizadorRepo.adicionarUtilizador(utilizador);
        registarObservador(utilizador);
    }

    public List<Utilizador> getUtilizadores() {
        return utilizadorRepo.getUtilizadores();
    }

    // -------------------- Empréstimos --------------------
    public void emprestarLivro(Livro livro, Utilizador utilizador) {
        if (!livroRepo.getLivros().contains(livro)) { // Verificar se o livro existe na lista de livros
            System.out.println("O livro '" + livro.getTitulo() + "' não está disponível.");
            return;
        }

        // Verificar se já está emprestado
        List<Emprestimo> jaEmprestado = emprestimoRepo.getEmprestimosPorLivro(livro);
        if (!jaEmprestado.isEmpty()) {
            System.out.println("O livro '" + livro.getTitulo() + "' já está emprestado!");
            return;
        }

        // Remover da lista de disponíveis e criar empréstimo
        livroRepo.removerLivro(livro);
        Emprestimo e = new Emprestimo(utilizador, livro);
        emprestimoRepo.adicionarEmprestimo(e);
        notificarObservadores("O livro '" + livro.getTitulo() + "' foi emprestado.");
    }

    public void devolverLivro(Livro livro) {
        List<Emprestimo> emprestimos = emprestimoRepo.getEmprestimosPorLivro(livro); // Pega na lista de empréstimos com base no livro

        if (emprestimos.isEmpty()) { // Se a lista estiver vazia, significa que o livro não está emprestado
            System.out.println("O livro '" + livro.getTitulo() + "' não está emprestado.");
            return;
        }

        // Pega no primeiro empréstimo e remove
        Emprestimo e = emprestimos.get(0); // Pega no primeiro empréstimo da lista
        emprestimoRepo.removerEmprestimo(e);

        // Devolve o livro para os disponíveis
        livroRepo.adicionarLivro(livro);

        notificarObservadores("O livro '" + livro.getTitulo() + "' foi devolvido e está disponível.");
    }
}
