package domain.classes.estadios;

import domain.classes.partidas.Partida;

public class DesignacaoArbitroPartida {
    private Arbitro arbitro;
    private Partida partida;

    private final String paisSelecao1 = partida.getSelecao1().getPaisSelecao();
    private final String paisSelecao2 = partida.getSelecao2().getPaisSelecao();

    public void apitaPartida(Partida novaPartida) throws ConflitoPaisException {
        if (arbitro.getPais().equals(paisSelecao1) || arbitro.getPais().equals(paisSelecao2)) {
            throw new ConflitoPaisException("Erro: a partida possui árbitro de mesmo país que uma das seleções");
        }
    }
}