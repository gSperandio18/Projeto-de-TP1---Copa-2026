package domain.classes.administracao;

import domain.classes.partidas.Partida;
import domain.classes.selecoes.Jogador;
import domain.classes.selecoes.Selecao;

import java.util.ArrayList;
import java.util.List;

public class Organizador extends Usuario {
    static private List<Partida> partidas = new ArrayList<>();
    static private List<Selecao> selecoes = new ArrayList<>();

    public Organizador(String nomeCompleto, String senha, String email,Tipo personagem) {
        super(nomeCompleto, email, senha, personagem);
    }

    public static List<Partida> getPartidas() {
        return partidas;
    }

    public void setPartidas(List<Partida> partidas) {
        this.partidas = partidas;
    }

    public static List<Selecao> getSelecoes() {
        return selecoes;
    }

    public void setSelecoes(List<Selecao> selecao){
        this.selecoes = selecao;
    }
}
