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
        if (SessaoUsuario.getInstancia()
                .getUsuarioAtual() == null) {

            throw new SecurityException(
                    "Nenhum usuário logado."
            );
        }

        if (!SessaoUsuario.getInstancia().getUsuarioAtual().podeGerenciarCompeticao()) {
            throw new SecurityException("Usuário sem permissão.");
        }
    }

    /*
    public void cadastrar(Arbitro arbitro) {
        validarPermissao();

        for(Arbitro a : arbitros){
            if(a.getEmail().equalsIgnoreCase(arbitro.getEmail())){
                throw new IllegalArgumentException(
                        "E-mail já cadastrado."
                );
            }
        }

        arbitros.add(arbitro);
        dao.salvar(arbitros);
    }

    Essa funcionalidade já é implementada pelo AdministradorController

     */

    public void editar(String codigo,String pais,int experiencia) {
        validarPermissao();

        //implementando restrições

        if (pais == null || pais.trim().isBlank() || "Selecione um País".equals(pais)) {
            throw new IllegalArgumentException(
                    "Selecione um país válido."
            );
        }

        if (experiencia < 0){
            throw new IllegalArgumentException(
                    "Insira uma experiência não-negativa válida."
            );
        }

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

    /*
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
    Essa funcionalidade já é implementada pelo AdministradorController
     */

    public List<Arbitro> listar() {
        return new ArrayList<>(arbitros);
    }


    public List<Arbitro> pesquisar(
            String nome,
            String pais,
            Integer experienciaMinima) {

        return arbitros.stream()

                .filter(a ->
                        nome == null ||
                                nome.isBlank() ||
                                a.getNomeCompleto()
                                        .toLowerCase()
                                        .contains(nome.toLowerCase()))

                .filter(a ->
                        pais == null ||
                                pais.isBlank() ||
                                pais.equals("Selecione um País") ||
                                (a.getPais() != null &&
                                        a.getPais().equalsIgnoreCase(pais)))

                .filter(a ->
                        experienciaMinima == null ||
                                a.getExperiencia() >= experienciaMinima)

                .toList();
    }

    public Arbitro buscarPorCodigo(String codigo) {

        for (Arbitro a : arbitros) {
            if (a.getCodigo().equals(codigo)) {
                return a;
            }
        }

        return null;
    }

}
