package domain.classes.partidas;

public class Resultado {
    private String idResultado;
    private Partida partida;
    private int placarSelecao1;
    private int placarSelecao2;
    private String eventos;

    public Resultado(String idResultado, Partida partida, int placarSelecao1, int placarSelecao2, String eventos) {
        this.idResultado = idResultado;
        this.partida = partida;
        this.placarSelecao1 = placarSelecao1;
        this.placarSelecao2 = placarSelecao2;
        this.eventos = eventos;
    }

    public String getIdResultado() {
        return idResultado;
    }

    public void setIdResultado(String idResultado) {
        this.idResultado = idResultado;
    }

    public Partida getPartida() {
        return partida;
    }

    public void setPartida(Partida partida) {
        this.partida = partida;
    }

    public int getPlacarSelecao1() {
        return placarSelecao1;
    }

    public void setPlacarSelecao1(int placarSelecao1) {
        this.placarSelecao1 = placarSelecao1;
    }

    public int getPlacarSelecao2() {
        return placarSelecao2;
    }

    public void setPlacarSelecao2(int placarSelecao2) {
        this.placarSelecao2 = placarSelecao2;
    }

    public String getEventos() {
        return eventos;
    }

    public void setEventos(String eventos) {
        this.eventos = eventos;
    }
}
