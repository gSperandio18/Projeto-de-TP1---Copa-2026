package controller.selecoes;

import domain.classes.administracao.SessaoUsuario;
import domain.classes.selecoes.Selecao;
import domain.dao.SelecaoDAO;
import domain.dao.SelecaoJsonDAO;

import java.util.ArrayList;
import java.util.List;

public class SelecaoController {
    private final SelecaoDAO dao;
    private final List<Selecao> selecoes;

    public SelecaoController() {
        dao = new SelecaoJsonDAO();
        selecoes = dao.carregar();
    }

    public List<Selecao> listar() {
        return new ArrayList<>(selecoes);
    }

    public List<Selecao> pesquisar(
            String id,
            String pais,
            String tecnico,
            Character grupo) {

        return selecoes.stream()

                .filter(a ->
                        id == null ||
                                id.isBlank() ||
                                a.getIdSelecao()
                                        .equals(id))

                .filter(a ->
                        pais == null ||
                                pais.isBlank() ||
                                (a.getPaisSelecao() != null &&
                                        a.getPaisSelecao().equalsIgnoreCase(pais)))

                .filter(a ->
                        tecnico == null ||
                                tecnico.isBlank() ||
                                a.getTecnico()
                                        .toLowerCase()
                                        .contains(tecnico.toLowerCase()))
                .filter(a ->
                        grupo == null ||
                                a.getGrupo().equals(grupo))

                .toList();
    }

}