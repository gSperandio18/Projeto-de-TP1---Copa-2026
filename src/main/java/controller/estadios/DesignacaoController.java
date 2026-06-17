package controller.estadios;

import domain.classes.administracao.SessaoUsuario;
import domain.classes.estadios.Arbitro;
import domain.classes.estadios.ConflitoPaisException;
import domain.classes.estadios.DesignacaoArbitroPartida;
import domain.classes.partidas.Partida;

import domain.dao.DesignacaoDAO;
import domain.dao.DesignacaoJsonDAO;

import java.util.ArrayList;
import java.util.List;

public class DesignacaoController {
    private final DesignacaoDAO dao;

    private final List<DesignacaoArbitroPartida>
            designacoes;

    public DesignacaoController() {

        dao = new DesignacaoJsonDAO();
        designacoes = dao.carregar();
    }

    private void validarPermissao() {

        if (!SessaoUsuario.getInstancia().getUsuarioAtual()
                .podeGerenciarCompeticao()) {

            throw new SecurityException(
                    "Usuário sem permissão.");
        }
    }

    public void designar(
            Arbitro arbitro,
            Partida partida,
            boolean principal) {

        validarPermissao();

        if(arbitro == null){
            throw new IllegalArgumentException(
                    "Árbitro inválido."
            );
        }

        if(partida == null){
            throw new IllegalArgumentException(
                    "Partida inválida."
            );
        }


        String pais1 =
                partida
                        .getSelecao1()
                        .getPaisSelecao();

        String pais2 =
                partida
                        .getSelecao2()
                        .getPaisSelecao();

        if(arbitro.getPais() == null ||
                arbitro.getPais().isBlank()) {

            throw new IllegalArgumentException(
                    "O árbitro precisa possuir um país cadastrado."
            );
        }

        if (arbitro.getPais()
                .equalsIgnoreCase(pais1)
                ||
                arbitro.getPais()
                        .equalsIgnoreCase(pais2)) {

            throw new ConflitoPaisException(
                    "Árbitro não pode apitar seleção de seu país.");
        }

        // Verifica se o árbitro já foi designado para esta partida
        for (DesignacaoArbitroPartida d : designacoes) {

            if (d.getPartida().equals(partida)
                    &&
                    d.getArbitro().equals(arbitro)) {

                throw new IllegalArgumentException(
                        "Árbitro já designado para esta partida."
                );
            }
        }

        // Impede mais de um árbitro principal
        if (principal &&
                possuiArbitroPrincipal(partida)) {

            throw new IllegalArgumentException(
                    "A partida já possui árbitro principal."
            );
        }

        DesignacaoArbitroPartida
                designacao =
                new DesignacaoArbitroPartida(
                        arbitro,
                        partida,
                        principal
                );

        designacoes.add(
                designacao);

        dao.salvar(
                designacoes);
    }

    public boolean possuiArbitroPrincipal(
            Partida partida) {

        return designacoes.stream()
                .anyMatch(d ->
                        d != null &&
                        d.getPartida() != null &&
                        d.getPartida()
                                .equals(partida)
                                &&
                                d.isPrincipal());
    }

    public List<DesignacaoArbitroPartida>
    listar() {

        return new ArrayList<>(designacoes);
    }

    public void validarPartida(Partida partida){

        boolean possuiPrincipal =
                designacoes.stream()
                        .anyMatch(d ->
                                d != null &&
                                d.getPartida() != null &&
                                d.getPartida().equals(partida)
                                        &&
                                        d.isPrincipal());

        if(!possuiPrincipal){

            throw new IllegalStateException(
                    "A partida deve possuir um árbitro principal."
            );
        }
    }
}