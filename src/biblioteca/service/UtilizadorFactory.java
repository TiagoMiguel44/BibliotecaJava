package biblioteca.service;

import biblioteca.model.Utilizador;

public class UtilizadorFactory { // Factory para criar utilizadores com base no tipo
    public static Utilizador criarUtilizador(String nome, String email, String tipo) { // metodo que cria um utilizador com base no tipo
        switch (tipo) { // verifica o tipo de utilizador
            case "Aluno":
                return new Utilizador(nome, email, "Aluno"); // cria um utilizador do tipo Aluno
            case "Professor":
                return new Utilizador(nome, email, "Professor"); // cria um utilizador do tipo Professor
            case "Administrador":
                return new Utilizador(nome, email, "Administrador"); // cria um utilizador do tipo Administrador
            default:
                throw new IllegalArgumentException("Tipo de utilizador inválido: " + tipo); // lança uma exceção se o tipo for inválido
        }
    }
}
