package domain.classes.selecoes;

import java.util.ArrayList;
import java.util.List;

public class Selecao {
    private String idSelecao, paisSelecao, tecnico;
    private char grupo;

    private List<Jogador> jogadores;

    public Selecao (String idSelecao, String paisSelecao, String tecnico, char grupo) {
        this.idSelecao = idSelecao;
        this.paisSelecao = paisSelecao;
        this.tecnico = tecnico;
        this.grupo = grupo;
        this.jogadores = new ArrayList<Jogador>();
    }

    public String getIdSelecao() {
        return idSelecao;
    }

    public void setIdSelecao(String idSelecao) {
        this.idSelecao = idSelecao;
    }

    public String getPaisSelecao() {
        return paisSelecao;
    }

    public void setPaisSelecao(String pais) {
        this.paisSelecao = paisSelecao;
    }

    public String getTecnico() {
        return tecnico;
    }

    public void setTecnico(String tecnico) {
        this.tecnico = tecnico;
    }

    public char getGrupo() {
        return grupo;
    }

    public void setGrupo(char grupo) {
        this.grupo = grupo;
    }

    public List<Jogador> getJogadores() {
        return jogadores;
    }

    public void setJogadores(List<Jogador> jogadores) {
        this.jogadores = jogadores;
    }
}