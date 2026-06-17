package controller.estadios;

import domain.classes.estadios.Arbitro;
import domain.classes.administracao.SessaoUsuario;
import domain.dao.ArbitroDAO;
import domain.dao.ArbitroJsonDAO;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class ArbitroController {
    private final ArbitroDAO dao;
    private final List<Arbitro> arbitros;

    public ArbitroController() {
        dao = new ArbitroJsonDAO();
        arbitros = dao.carregar();
    }

    private void validarPermissao() {
        if (!SessaoUsuario.getInstancia().getUsuarioAtual().podeGerenciarCompeticao()) {
            throw new SecurityException("Usuário sem permissão.");
        }
    }

    public void cadastrar(Arbitro arbitro) {
        validarPermissao();

        for(Arbitro a : arbitros){
            if(a.getCodigo().equals(arbitro.getCodigo())){
                throw new IllegalArgumentException(
                        "Código de árbitro já cadastrado."
                );
            }
        }

        arbitros.add(arbitro);
        dao.salvar(arbitros);
    }

    public void editar(String codigo,String pais,int experiencia) {
        validarPermissao();

        for (Arbitro arbitro : arbitros) {
            if (arbitro.getCodigo().equals(codigo)) {
                arbitro.setPais(pais);
                arbitro.setExperiencia(experiencia);
                dao.salvar(arbitros);
                return;
            }
        }

        throw new IllegalArgumentException(
                "Árbitro não encontrado."
        );
    }

    public void excluir(String email) {
        validarPermissao();

        boolean removido = arbitros.removeIf(a -> a.getEmail().equalsIgnoreCase(email));

        if(!removido){
            throw new IllegalArgumentException(
                    "Árbitro não encontrado."
            );
        }

        dao.salvar(arbitros);
    }

    public List<Arbitro> listar() {
        return new ArrayList<>(arbitros);
    }

    public List<Arbitro> buscarPorPais(
            String pais) {

        return arbitros.stream()
                .filter(a ->
                        a.getPais() != null &&
                        a.getPais()
                                .equalsIgnoreCase(pais))
                .collect(Collectors.toList());
    }

    public List<Arbitro> buscarPorExperiencia(
            int experienciaMinima) {

        return arbitros.stream()
                .filter(a ->
                        a.getExperiencia()
                                >= experienciaMinima)
                .collect(Collectors.toList());
    }

}
