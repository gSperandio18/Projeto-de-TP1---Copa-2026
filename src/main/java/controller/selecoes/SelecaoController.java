package controller.selecoes;

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

    public void cadastrar(Selecao selecao) {

        for(Selecao a : selecoes){
            if(a.getIdSelecao().equalsIgnoreCase(selecao.getIdSelecao())){
                throw new IllegalArgumentException(
                        "Seleção já cadastrada."
                );
            }
        }

        selecoes.add(selecao);
        dao.salvar(selecoes);
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

    public void editar(
            String id,
            String pais,
            String tecnico,
            Character grupo) {

        for (Selecao selecao : selecoes) {

            if (selecao.getIdSelecao().equals(id)) {

                selecao.setPaisSelecao(pais);
                selecao.setTecnico(tecnico);
                selecao.setGrupo(grupo);

                dao.salvar(selecoes);

                return;
            }
        }

        throw new IllegalArgumentException(
                "Seleção não encontrada."
        );
    }

    public void excluir(String id) {

        boolean removido = selecoes.removeIf(
                e -> e.getIdSelecao().equals(id)
        );

        if(!removido){
            throw new IllegalArgumentException(
                    "Seleção não encontrada."
            );
        }

        dao.salvar(selecoes);
    }


}