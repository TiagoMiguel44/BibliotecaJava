package biblioteca.model;

import biblioteca.service.Observer;

public class Utilizador implements Observer {

    private String nome;
    private String email;
    private String tipo; // "Aluno" ou "Professor" ou "Administrador"


    public Utilizador(String nome, String email, String tipo) {
        this.nome = nome;
        this.email = email;
        this.tipo = tipo;
    }

    public String getNome() {
        return nome;
    }

    public String getEmail() {
        return email;
    }
    public String getTipo() {
        return tipo;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }
    public void setEmail(String email) {
        this.email = email;
    }
    public void setTipo(String tipo) {
        this.tipo = tipo;
    }

    @Override
    public void atualizar(String mensagem) {
        System.out.println("Notificação para " + nome + " (" + tipo + "): " + mensagem);
    }
}
