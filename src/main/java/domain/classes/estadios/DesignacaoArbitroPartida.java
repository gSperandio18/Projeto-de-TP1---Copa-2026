package domain.classes.estadios;

import domain.classes.partidas.Partida;

public class DesignacaoArbitroPartida {

    private Arbitro arbitro;

    private Partida partida;

    private boolean principal;

    public DesignacaoArbitroPartida(
            Arbitro arbitro,
            Partida partida,
            boolean principal) {

        this.arbitro = arbitro;
        this.partida = partida;
        this.principal = principal;
    }

    public Arbitro getArbitro() {
        return arbitro;
    }

    public Partida getPartida() {
        return partida;
    }

    public boolean isPrincipal() {
        return principal;
    }

    @Override
    public String toString() {

        return arbitro.getNomeCompleto()
                + " - "
                + partida.toString();
    }
}