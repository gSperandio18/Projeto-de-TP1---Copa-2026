package controller.estadios;

import domain.classes.estadios.Arbitro;
import domain.classes.administracao.Usuario;

import domain.dao.ArbitroDAO;
import domain.dao.ArbitroJsonDAO;

import java.util.List;
import java.util.stream.Collectors;

public class ArbitroController {

    private final Usuario usuarioLogado;

    private final ArbitroDAO dao;

    private final List<Arbitro> arbitros;

    public ArbitroController(
            Usuario usuarioLogado) {

        this.usuarioLogado = usuarioLogado;

        dao = new ArbitroJsonDAO();

        arbitros = dao.carregar();
    }

    private void validarPermissao() {

        if (!usuarioLogado.podeGerenciarCompeticao()) {

            throw new SecurityException(
                    "Usuário sem permissão."
            );
        }
    }

    public void cadastrar(
            Arbitro arbitro) {

        for(Arbitro a : arbitros){

            if(a.getCodigo().equals(arbitro.getCodigo())){

                throw new IllegalArgumentException(
                        "Código já cadastrado."
                );
            }
        }

        validarPermissao();

        arbitros.add(arbitro);

        dao.salvar(arbitros);
    }

    public void editar(
            String email,
            String pais,
            int experiencia) {

        validarPermissao();

        for (Arbitro arbitro : arbitros) {

            if (arbitro.getEmail()
                    .equalsIgnoreCase(email)) {

                arbitro.setPais(pais);
                arbitro.setExperiencia(experiencia);

                dao.salvar(arbitros);

                return;
            }
        }
    }

    public void excluir(
            String email) {

        validarPermissao();

        arbitros.removeIf(
                a -> a.getEmail()
                        .equalsIgnoreCase(email)
        );

        dao.salvar(arbitros);
    }

    public List<Arbitro> listar() {
        return arbitros;
    }

    public List<Arbitro> buscarPorPais(
            String pais) {

        return arbitros.stream()
                .filter(a ->
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