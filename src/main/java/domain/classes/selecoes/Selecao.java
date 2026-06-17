package domain.classes.selecoes;

import java.util.ArrayList;
import java.util.List;

public class Selecao {

    private String idSelecao;
    private String paisSelecao;
    private String tecnico;
    private Character grupo;

    private List<Jogador> jogadores;

    public Selecao(
            String idSelecao,
            String paisSelecao,
            String tecnico,
            char grupo) {

        if (idSelecao == null || idSelecao.isBlank()) {
            throw new IllegalArgumentException(
                    "ID da seleção não pode ser vazio."
            );
        }

        if (paisSelecao == null || paisSelecao.isBlank()) {
            throw new IllegalArgumentException(
                    "País da seleção não pode ser vazio."
            );
        }

        this.idSelecao = idSelecao;
        this.paisSelecao = paisSelecao;
        this.tecnico = tecnico;
        this.grupo = grupo;
        this.jogadores = new ArrayList<>();
    }

    // ==========================
    // Regras de negócio
    // ==========================

    public void adicionarJogador(Jogador jogador) {

        if (jogador == null) {
            throw new IllegalArgumentException(
                    "Jogador inválido."
            );
        }

        jogadores.add(jogador);
    }

    public void removerJogador(Jogador jogador) {
        jogadores.remove(jogador);
    }

    public int quantidadeJogadores() {
        return jogadores.size();
    }

    // ==========================
    // Getters e Setters
    // ==========================

    public String getIdSelecao() {
        return idSelecao;
    }

    public void setIdSelecao(String idSelecao) {
        this.idSelecao = idSelecao;
    }

    public String getPaisSelecao() {
        return paisSelecao;
    }

    public void setPaisSelecao(String paisSelecao) {
        this.paisSelecao = paisSelecao;
    }

    public String getTecnico() {
        return tecnico;
    }

    public void setTecnico(String tecnico) {
        this.tecnico = tecnico;
    }

    public Character getGrupo() {
        return grupo;
    }

    public void setGrupo(char grupo) {
        this.grupo = grupo;
    }

    public List<Jogador> getJogadores() {
        return jogadores;
    }

    public void setJogadores(List<Jogador> jogadores) {

        if (jogadores == null) {
            this.jogadores = new ArrayList<>();
        } else {
            this.jogadores = jogadores;
        }
    }

    // ==========================
    // Utilitários
    // ==========================

    @Override
    public String toString() {

        return paisSelecao +
                " (Grupo " +
                grupo +
                ")";
    }

    @Override
    public boolean equals(Object o) {

        if(this == o)
            return true;

        if(!(o instanceof Selecao))
            return false;

        Selecao selecao = (Selecao) o;

        return idSelecao.equals(selecao.idSelecao);
    }

    @Override
    public int hashCode() {
        return idSelecao.hashCode();
    }
}