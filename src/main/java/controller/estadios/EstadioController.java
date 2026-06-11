package controller.estadios;

import domain.classes.estadios.Estadio;
import domain.classes.partidas.Partida;
import domain.classes.administracao.Usuario;

import dao.EstadioDAO;
import dao.EstadioJsonDAO;

import java.util.List;
import java.util.stream.Collectors;

public class EstadioController {

    private final Usuario usuarioLogado;

    private final EstadioDAO dao;

    private final List<Estadio> estadios;

    public EstadioController(Usuario usuarioLogado) {

        this.usuarioLogado = usuarioLogado;

        dao = new EstadioJsonDAO();

        estadios = dao.carregar();
    }

    private void validarPermissao() {

        if (!usuarioLogado.podeGerenciarCompeticao()) {

            throw new SecurityException(
                    "Usuário sem permissão."
            );
        }
    }

    public void cadastrar(Estadio estadio) {

        validarPermissao();

        for(Estadio e : estadios){

            if(e.getCodigo().equals(
                    estadio.getCodigo())){

                throw new IllegalArgumentException(
                        "Código já cadastrado.");
            }
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
    }

    public List<Estadio> listar() {
        return estadios;
    }

    public List<Estadio> buscarPorNome(
            String nome) {

        return estadios.stream()
                .filter(e ->
                        e.getNome()
                                .toLowerCase()
                                .contains(nome.toLowerCase()))
                .collect(Collectors.toList());
    }

    public List<Estadio> buscarPorCapacidade(
            int capacidadeMinima) {

        return estadios.stream()
                .filter(e ->
                        e.getCapacidade()
                                >= capacidadeMinima)
                .collect(Collectors.toList());
    }

    public List<Estadio> buscarPorCidade(
            String cidade) {

        return estadios.stream()
                .filter(e ->
                        e.getCidade()
                                .equalsIgnoreCase(cidade))
                .toList();
    }

    public List<Estadio> buscarPorPais(
            String pais) {

        return estadios.stream()
                .filter(e ->
                        e.getPais()
                                .equalsIgnoreCase(pais))
                .toList();
    }

    public List<Estadio> buscarPorEstado(
            String estado) {

        return estadios.stream()
                .filter(e ->
                        e.getEstado()
                                .equalsIgnoreCase(estado))
                .toList();
    }
}