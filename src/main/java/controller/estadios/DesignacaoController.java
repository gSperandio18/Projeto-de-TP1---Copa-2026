package controller.estadios;

import domain.classes.administracao.SessaoUsuario;
import domain.classes.estadios.Arbitro;
import domain.classes.estadios.ConflitoPaisException;
import domain.classes.estadios.DesignacaoArbitroPartida;
import domain.classes.partidas.Partida;

import domain.dao.DesignacaoDAO;
import domain.dao.DesignacaoJsonDAO;

import java.util.List;

public class DesignacaoController {
    private final DesignacaoDAO dao;
    private final List<DesignacaoArbitroPartida> designacoes;

    public DesignacaoController() {
        dao = new DesignacaoJsonDAO();
        designacoes = dao.carregar();
    }

    private void validarPermissao() {

        if (!SessaoUsuario.getInstancia().getUsuarioAtual().podeGerenciarCompeticao()) {
            throw new SecurityException("Usuário sem permissão.");
        }
    }

    public void designar(Arbitro arbitro, Partida partida, boolean principal) {
        validarPermissao();
        String pais1 = partida.getSelecao1().getPaisSelecao();
        String pais2 = partida.getSelecao2().getPaisSelecao();

        if (arbitro.getPais().equalsIgnoreCase(pais1) || arbitro.getPais().equalsIgnoreCase(pais2)) {
            throw new ConflitoPaisException("Árbitro não pode apitar seleção de seu país.");
        }

        // Verifica se o árbitro já foi designado para esta partida
        for (DesignacaoArbitroPartida d : designacoes) {
            if (d.getPartida().equals(partida) && d.getArbitro().equals(arbitro)) {
                throw new IllegalArgumentException("Árbitro já designado para esta partida.");
            }
        }

        // Impede mais de um árbitro principal
        if (principal && possuiArbitroPrincipal(partida)) {
            throw new IllegalArgumentException(
                    "A partida já possui árbitro principal.");
        }

        DesignacaoArbitroPartida designacao = new DesignacaoArbitroPartida(arbitro, partida, principal);
        designacoes.add(designacao);
        dao.salvar(designacoes);
    }

    public void designarLista(List<Arbitro> arbitros, Partida partida, Arbitro principal) {
        for (Arbitro a : arbitros) { designar(a, partida, a.equals(principal)); }
    }

    public boolean possuiArbitroPrincipal(Partida partida) {
        return designacoes.stream()
                .anyMatch(d -> d.getPartida().equals(partida) && d.isPrincipal());
    }

    public List<DesignacaoArbitroPartida> listar() {
        return designacoes;
    }

    public void validarPartida(Partida partida) throws IllegalStateException {
        boolean possuiPrincipal = possuiArbitroPrincipal(partida);
        if(!possuiPrincipal){
            throw new IllegalStateException("A partida deve possuir um árbitro principal.");
        }
    }

    public List<Arbitro> listarArbitros(Partida partida) {
        return designacoes.stream()
                .filter(p -> p.getPartida().equals(partida)) // As Designações com a partida
                .map(DesignacaoArbitroPartida::getArbitro) // Da Designação, pegar o árbitro
                .toList();
    }

    public List<Partida> listarPartidas(Arbitro arbitro) {
        return designacoes.stream()
                .filter(p -> p.getArbitro().equals(arbitro)) // As Designações com o árbitro
                .map(DesignacaoArbitroPartida::getPartida) // Da Designação, pegar a partida
                .toList();
    }
}