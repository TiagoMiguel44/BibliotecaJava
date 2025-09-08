package biblioteca.main;

import biblioteca.model.Livro;
import biblioteca.model.Utilizador;
import biblioteca.service.BibliotecaService;
import biblioteca.service.UtilizadorFactory;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.io.FileWriter;
import java.io.IOException;
import java.io.BufferedReader;
import java.io.FileReader;

public class Main {

    public static void guardarLivros(ArrayList<Livro> livros) {
        try (FileWriter writer = new FileWriter("livros.txt")) {
            for (Livro livro : livros) {
                writer.write(livro.getTitulo() + ";" + livro.getAutor() + ";" +
                        livro.getAnoPublicacao() + ";" + livro.getIsbn() + "\n");
            }
        } catch (IOException e) {
            System.out.println("Erro ao guardar livros: " + e.getMessage());
        }
    }

    public static void guardarUtilizadores(ArrayList<Utilizador> utilizadores) {
        try (FileWriter writer = new FileWriter("utilizadores.txt")) {
            for (Utilizador u : utilizadores) {
                writer.write(u.getNome() + ";" + u.getEmail() + ";" + u.getTipo() + "\n");
            }
        } catch (IOException e) {
            System.out.println("Erro ao guardar utilizadores: " + e.getMessage());
        }
    }

    public static ArrayList<Utilizador> lerUtilizadores() {
        ArrayList<Utilizador> utilizadores = new ArrayList<>();
        try (BufferedReader reader = new BufferedReader(new FileReader("utilizadores.txt"))) {
            String linha;
            while ((linha = reader.readLine()) != null) {
                String[] partes = linha.split(";");
                if (partes.length == 3) {
                    utilizadores.add(new Utilizador(partes[0], partes[1], partes[2]));
                }
            }
        } catch (IOException e) {
            System.out.println("Nenhum ficheiro encontrado, a começar vazio.");
        }
        return utilizadores;
    }

    public static ArrayList<Livro> lerLivros() {
        ArrayList<Livro> livros = new ArrayList<>();
        try (BufferedReader reader = new BufferedReader(new FileReader("livros.txt"))) {
            String linha;
            while ((linha = reader.readLine()) != null) {
                String[] partes = linha.split(";");
                if (partes.length == 4) {
                    livros.add(new Livro(partes[0], partes[1], Integer.parseInt(partes[2]), partes[3]));
                }
            }
        } catch (IOException e) {
            System.out.println("Nenhum ficheiro encontrado, a começar vazio.");
        }
        return livros;
    }

    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);
        BibliotecaService bibliotecaService = BibliotecaService.getInstancia();


        // Carregar livros do ficheiro
        ArrayList<Livro> livrosDoFicheiro = lerLivros();
        bibliotecaService.getLivros().addAll(livrosDoFicheiro);
        // Carregar utilizadores do ficheiro
        ArrayList<Utilizador> utilizadoresDoFicheiro = lerUtilizadores();
        bibliotecaService.getUtilizadores().addAll(utilizadoresDoFicheiro);


        int opcao;

        do {
            System.out.println("\n===== Menu =====");
            System.out.println("1. Adicionar Livro");
            System.out.println("2. Listar Livros");
            System.out.println("3. Adicionar Utilizador");
            System.out.println("4. Listar Utilizadores");
            System.out.println("5. Listar Livros por Titulo");
            System.out.println("6. Listar Livros por Autor");
            System.out.println("7. Sair");
            System.out.print("Escolha uma opcao: ");
            opcao = input.nextInt();
            input.nextLine(); // Consumir a nova linha

            switch (opcao) {
                case 1:
                    System.out.print("Titulo: ");
                    String titulo = input.nextLine();
                    System.out.print("Autor: ");
                    String autor = input.nextLine();
                    System.out.print("Ano de Publicacao: ");
                    int ano = input.nextInt();
                    input.nextLine();
                    System.out.print("ISBN: ");
                    String isbn = input.nextLine();

                    Livro novoLivro = new Livro(titulo, autor, ano, isbn);
                    bibliotecaService.getLivros().add(novoLivro);
                    System.out.println("Livro adicionado com sucesso!");
                    break;

                case 2:
                    if (bibliotecaService.getLivros().isEmpty()) {
                        System.out.println("Nenhum livro registado.");
                    } else {
                        for (Livro livro : bibliotecaService.getLivros()) {
                            System.out.println("Titulo: " + livro.getTitulo() +
                                    ", Autor: " + livro.getAutor() +
                                    ", Ano: " + livro.getAnoPublicacao() +
                                    ", ISBN: " + livro.getIsbn());
                        }
                    }
                    break;

                case 3:
                    System.out.print("Nome: ");
                    String nome = input.nextLine();
                    System.out.print("Email: ");
                    String email = input.nextLine();
                    System.out.print("Tipo (Aluno/Professor/Administrador): ");
                    String tipo = input.nextLine();

                    try {
                        Utilizador utilizador = UtilizadorFactory.criarUtilizador(nome, email, tipo); // Usar a factory para criar o utilizador
                        bibliotecaService.adicionarUtilizador(utilizador);
                        System.out.println("Utilizador adicionado com sucesso!");
                    } catch (IllegalArgumentException e) {
                        System.out.println("Erro: " + e.getMessage());
                    }

                    /* bibliotecaService.adicionarUtilizador(new Utilizador(nome, email, tipo));

                    */
                    System.out.println("Utilizador adicionado com sucesso!");
                    break;

                case 4:
                    if (bibliotecaService.getUtilizadores().isEmpty()) {
                        System.out.println("Nenhum utilizador registado.");
                    } else {
                        for (Utilizador u : bibliotecaService.getUtilizadores()) {
                            System.out.println("Nome: " + u.getNome() +
                                    ", Email: " + u.getEmail() +
                                    ", Tipo: " + u.getTipo());
                        }
                    }
                    break;
                case 5:
                    System.out.print("Digite o titulo para pesquisa: ");
                    String tituloPesquisa = input.nextLine();
                    List<Livro> resultadosTitulo = new ArrayList<>(bibliotecaService.getLivrosporTitulo(tituloPesquisa));
                    if (resultadosTitulo.isEmpty()) {
                        System.out.println("Nenhum livro encontrado com esse titulo.");
                    } else {
                        for (Livro livro : resultadosTitulo) {
                            System.out.println("Titulo: " + livro.getTitulo() +
                                    ", Autor: " + livro.getAutor() +
                                    ", Ano: " + livro.getAnoPublicacao() +
                                    ", ISBN: " + livro.getIsbn());
                        }
                    }
                    break;
                case 6:
                    System.out.print("Digite o autor para pesquisa: ");
                    String autorPesquisa = input.nextLine();
                    List<Livro> resultadosAutor = new ArrayList<>(bibliotecaService.getLivrosporAutor(autorPesquisa));
                    if (resultadosAutor.isEmpty()) {
                        System.out.println("Nenhum livro encontrado com esse autor.");
                    } else {
                        for (Livro livro : resultadosAutor) {
                            System.out.println("Titulo: " + livro.getTitulo() +
                                    ", Autor: " + livro.getAutor() +
                                    ", Ano: " + livro.getAnoPublicacao() +
                                    ", ISBN: " + livro.getIsbn());
                        }
                    }
                    break;

                case 7:
                    System.out.println("A guardar livros e a sair...");
                    guardarLivros(new ArrayList<>(bibliotecaService.getLivros()));
                    guardarUtilizadores(new ArrayList<>(bibliotecaService.getUtilizadores()));
                    break;

                default:
                    System.out.println("Opcao invalida.");
            }
        } while (opcao != 7);

        input.close();
    }
}
