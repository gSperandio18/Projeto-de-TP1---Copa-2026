package controller.estadios;

import domain.classes.estadios.Estadio;
import domain.classes.partidas.Partida;
import domain.classes.administracao.SessaoUsuario;

import domain.dao.EstadioDAO;
import domain.dao.EstadioJsonDAO;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class EstadioController {
    private final EstadioDAO dao;
    private final List<Estadio> estadios;

    public EstadioController() {
        dao = new EstadioJsonDAO();
        estadios = dao.carregar();
    }

    private void validarPermissao() {

        if (!SessaoUsuario.getInstancia().getUsuarioAtual().podeGerenciarCompeticao()) {

            throw new SecurityException(
                    "Usuário sem permissão."
            );
        }
    }

    public void cadastrar(Estadio estadio) {

        validarPermissao();

        for(Estadio e : estadios){

            if ("Todos".equals(estadio.getPais())) {
                throw new IllegalArgumentException(
                        "Selecione um país válido."
                );
            }

            if(e.getCodigo().equals(
                    estadio.getCodigo())){

                throw new IllegalArgumentException(
                        "Código já cadastrado.");
            }
        }

        if(estadio == null){
            throw new IllegalArgumentException(
                    "Estádio inválido."
            );
        }

        estadios.add(estadio);

        dao.salvar(estadios);
    }

    public void editar(
            String codigo,
            String nome,
            int capacidade,
            String pais,
            String estado,
            String cidade,
            int ano) {

        validarPermissao();

        for (Estadio estadio : estadios) {

            if ("Todos".equals(estadio.getPais())) {
                throw new IllegalArgumentException(
                        "Selecione um país válido."
                );
            }

            if (estadio.getCodigo().equals(codigo)) {

                estadio.setNome(nome);
                estadio.setCapacidade(capacidade);
                estadio.setPais(pais);
                estadio.setEstado(estado);
                estadio.setCidade(cidade);
                estadio.setAnoInauguracao(ano);

                dao.salvar(estadios);

                return;
            }
        }

        throw new IllegalArgumentException(
                "Estádio não encontrado."
        );
    }

    public void excluir(String codigo) {

        validarPermissao();

        boolean removido = estadios.removeIf(
                e -> e.getCodigo().equals(codigo)
        );

        if(!removido){
            throw new IllegalArgumentException(
                    "Estádio não encontrado."
            );
        }

        dao.salvar(estadios);
    }

    public void associarPartida(
            String codigoEstadio,
            Partida partida) {

        validarPermissao();

        for (Estadio estadio : estadios) {

            if (estadio.getCodigo()
                    .equals(codigoEstadio)) {

                estadio.associarPartida(partida);

                dao.salvar(estadios);

                return;
            }
        }

        throw new IllegalArgumentException(
                "Estádio não encontrado."
        );
    }

    public List<Estadio> listar() {
        return new ArrayList<>(estadios);
    }

    public List<Estadio> pesquisar(
            String nome,
            String pais,
            String estado,
            String cidade,
            Integer capacidadeMinima) {

        return estadios.stream()
                .filter(e -> nome == null ||
                        nome.isBlank() ||
                        e.getNome().toLowerCase()
                                .contains(nome.toLowerCase()))

                .filter(e -> pais == null ||
                        pais.isBlank() ||
                        e.getPais().equalsIgnoreCase(pais))

                .filter(e -> estado == null ||
                        estado.isBlank() ||
                        e.getEstado().equalsIgnoreCase(estado))

                .filter(e -> cidade == null ||
                        cidade.isBlank() ||
                        e.getCidade().equalsIgnoreCase(cidade))

                .filter(e -> capacidadeMinima == null ||
                        e.getCapacidade() >= capacidadeMinima)

                .toList();
    }
}