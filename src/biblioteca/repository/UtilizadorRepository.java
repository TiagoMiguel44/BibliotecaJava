package biblioteca.repository;

import biblioteca.model.Utilizador;

import java.util.ArrayList;
import java.util.List;

public class UtilizadorRepository {

    private List<Utilizador> utilizadores = new ArrayList<>();



    public void adicionarUtilizador(Utilizador utilizador) {
        this.utilizadores.add(utilizador);
    }

    public void removerUtilizador(Utilizador utilizador) {
        this.utilizadores.remove(utilizador);
    }

    public List<Utilizador> getUtilizadores() {
        return utilizadores;
    }


}
