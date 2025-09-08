package biblioteca.service;

import biblioteca.model.Livro;
import biblioteca.model.Utilizador;

import java.util.ArrayList;
import java.util.List;

public class BibliotecaService {

private List<Livro> livros; // lista principal dos livros da biblioteca
    private List<Utilizador> utilizadores;
    private static BibliotecaService instancia; // instancia singleton da classe

public BibliotecaService() {
    this.livros = new ArrayList<>(); // inicializa a lista de livros
    this.utilizadores = new ArrayList<>(); // inicializa a lista de utilizadores
}


public List<Livro> getLivrosporTitulo(String titulo) { // metodo que retorna uma lista de livros com base no título procurado
    List<Livro> resultados = new ArrayList<>(); // lista que vai conter os livros encontrados
    for (Livro livro : this.livros) { // percorre a lista principal de livros
        if (livro.getTitulo().toLowerCase().contains(titulo.toLowerCase())) { // Para cada livro da lista principal, pega no título desse livro e verifica se contém o texto que o utilizador escreveu.
            resultados.add(livro); // adiciona o livro encontrado na lista de resultados
        }
    }
    return resultados; // retorna a lista de livros encontrados
}

public List<Livro> getLivrosporAutor(String autor) {
    List<Livro> livros = new ArrayList<>();
    for (Livro livro : this.livros) {
        if (livro.getAutor().toLowerCase().contains(autor.toLowerCase())) {
            livros.add(livro);
        }
    }
    return livros;
}

public List<Livro> getLivros() { // metodo que retorna a lista principal de livros
    return this.livros;
}

public void adicionarUtilizador(Utilizador utilizador) { // metodo que adiciona um utilizador à lista de utilizadores
    this.utilizadores.add(utilizador);
}

public List<Utilizador> getUtilizadores() { // metodo que retorna a lista de utilizadores
    return this.utilizadores;
}

    public static BibliotecaService getInstancia() {
        if (instancia == null) { // verifica se a instância já foi criada
            instancia = new BibliotecaService(); // se não foi criada, cria uma nova instância
        }
        return instancia;
    }
}


