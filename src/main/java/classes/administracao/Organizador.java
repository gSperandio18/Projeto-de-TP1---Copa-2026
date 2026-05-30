package classes.administracao;

import classes.partidas.Partida;
import classes.selecoes.Selecao;

import java.util.List;

public class Organizador extends Usuario {
    private String selecao;
    private String partida;
    private List<Partida> partidas;
    private int codigoPartida;

    public Organizador(String nomeCompleto, String senha, String email) {
        super(nomeCompleto, email, senha);
    }

    public Partida criaPartida() {
        return null;
    }

    public void excluiPartida() {
    }

    public Selecao criaSelecao() {
        return null;
    }

    public void excluiSelecao() {
    }

    public String getSelecao() {
        return selecao;
    }

    public void setSelecao(String selecao) {
        this.selecao = selecao;
    }

    public String getPartida() {
        return partida;
    }

    public void setPartida(String partida) {
        this.partida = partida;
    }    public List<Partida> getPartidas() {
        return partidas;
    }

    public void setPartidas(List<Partida> partidas) {
        this.partidas = partidas;
    }

    public int getCodigoPartida() {
        return codigoPartida;
    }

    public void setCodigoPartida(int codigoPartida) {
        this.codigoPartida = codigoPartida;
    }
}
