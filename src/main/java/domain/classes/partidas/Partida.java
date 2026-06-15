package domain.classes.partidas;

import domain.classes.estadios.Estadio;
import domain.classes.selecoes.Selecao;

import java.time.LocalDateTime;
import java.util.UUID;

public class Partida {

    public enum StatusPartida {
        AGENDADA("Agendada"),
        FINALIZADA("Finalizada"),
        ANDAMENTO("Em andamento");

        final private String descricao;

        StatusPartida(String descricao) {
            this.descricao = descricao;
        }

        public String getDescricao() {
            return descricao;
        }
    }

    private final String id;
    private Estadio estadio;
    private Selecao selecao1;
    private Selecao selecao2;
    private LocalDateTime dataEHora;
    private Fase fase;
    private StatusPartida status;
    private Resultado resultado;

    public Partida(Estadio estadio, Selecao selecao1, Selecao selecao2,
                   LocalDateTime dataEHora, Fase fase, StatusPartida status)
    {
        this.estadio = estadio;
        this.selecao1 = selecao1;
        this.selecao2 = selecao2;
        this.dataEHora = dataEHora;
        this.fase = fase;
        this.status = status;

        this.id = UUID.randomUUID().toString();
    }

    public String getId() {
        return id;
    }

    public Estadio getEstadio() {
        return estadio;
    }

    public void setEstadio(Estadio estadio) {
        this.estadio = estadio;
    }

    public Selecao getSelecao1() {
        return selecao1;
    }

    public void setSelecao1(Selecao selecao1) {
        this.selecao1 = selecao1;
    }

    public Selecao getSelecao2() {
        return selecao2;
    }

    public void setSelecao2(Selecao selecao2) {
        this.selecao2 = selecao2;
    }

    public LocalDateTime getDataEHora() {
        return dataEHora;
    }

    public void setDataEHora(LocalDateTime dataEHora) {
        this.dataEHora = dataEHora;
    }

    public Fase getFase() {
        return fase;
    }

    public void setFase(Fase fase) {
        this.fase = fase;
    }

    public StatusPartida getStatus() {
        return status;
    }

    public void setStatus(StatusPartida status) {
        this.status = status;
    }

    public Resultado getResultado() {
        return resultado;
    }

    public void setResultado(Resultado resultado) {
        this.resultado = resultado;
    }

    @Override
    public String toString() {

        return selecao1.getPaisSelecao()
                + " x "
                + selecao2.getPaisSelecao();
    }

    @Override
    public boolean equals(Object o) {

        if(this == o)
            return true;

        if(!(o instanceof Partida))
            return false;

        Partida partida = (Partida) o;

        return id.equals(partida.id);
    }

    @Override
    public int hashCode() {
        return id.hashCode();
    }
}