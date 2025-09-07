import java.util.ArrayList;
import java.util.Scanner;
import java.io.FileWriter;
import java.io.IOException;
import java.io.BufferedReader;
import java.io.FileReader;

public class Main {

    public static void guardarLivros(ArrayList<Livro> livros) { // guarda a lista de livros num ficheiro de texto
        try (FileWriter writer = new FileWriter("livros.txt")) { //
            for (Livro livro : livros) { // percorre a lista de livros e guarda cada livro numa linha do ficheiro
                writer.write(livro.getTitulo() + ";" + livro.getAutor() + ";" + livro.getAnoPublicacao() + ";" + livro.getIsbn() + "\n");
            }
        } catch (IOException e) {
            System.out.println("Erro ao guardar livros: " + e.getMessage());
        }
    }

    public static ArrayList<Livro> lerLivros() { // lê a lista de livros de um ficheiro de texto
        ArrayList<Livro> livros = new ArrayList<>(); // cria uma lista vazia de livros
        try (BufferedReader reader = new BufferedReader(new FileReader("livros.txt"))) { // abre o ficheiro para leitura
            String linha;
            while ((linha = reader.readLine()) != null) { // lê cada linha do ficheiro
                String[] partes = linha.split(";");
                if (partes.length == 4) { // evitar erro se o ficheiro estiver mal formatado
                    livros.add(new Livro(partes[0], partes[1], Integer.parseInt(partes[2]), partes[3])); // divide a linha em partes, cria um objeto Livro e adiciona à lista
                }
            }
        } catch (IOException e) {
            System.out.println("Nenhum ficheiro encontrado, a começar vazio.");
        }
        return livros; // retorna a lista de livros lida do ficheiro
    }

    public static void main(String[] args) {
        // Carregar livros do ficheiro
        ArrayList<Livro> livros = lerLivros();
        ArrayList<Utilizador> utilizadores = new ArrayList<>();
        Scanner input = new Scanner(System.in);
        int opcao;

        do {
            System.out.println("\n===== Menu =====");
            System.out.println("1. Adicionar Livro");
            System.out.println("2. Listar Livros");
            System.out.println("3. Adicionar Utilizador");
            System.out.println("4. Listar Utilizadores");
            System.out.println("5. Sair");
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
                    livros.add(new Livro(titulo, autor, ano, isbn));
                    System.out.println("Livro adicionado com sucesso!");
                    break;

                case 2:
                    if (livros.isEmpty()) {
                        System.out.println("Nenhum livro registado.");
                    } else {
                        for (Livro livro : livros) {
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
                    utilizadores.add(new Utilizador(nome, email, tipo));
                    System.out.println("Utilizador adicionado com sucesso!");
                    break;

                case 4:
                    if (utilizadores.isEmpty()) {
                        System.out.println("Nenhum utilizador registado.");
                    } else {
                        for (Utilizador utilizador : utilizadores) {
                            System.out.println("Nome: " + utilizador.getNome() +
                                    ", Email: " + utilizador.getEmail() +
                                    ", Tipo: " + utilizador.getTipo());
                        }
                    }
                    break;

                case 5:
                    System.out.println("A guardar livros e a sair...");
                    guardarLivros(livros);
                    break;

                default:
                    System.out.println("Opcao invalida.");
            }
        } while (opcao != 5);

        input.close();
    }
}
