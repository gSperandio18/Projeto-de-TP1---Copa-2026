package domain.classes.estadios;

import domain.classes.partidas.Partida;

import java.util.ArrayList;
import java.util.List;

public class Estadio {

    private String codigo;
    private String nome;
    private int anoInauguracao;
    private int capacidade;
    private String pais;
    private String estado;
    private String cidade;

    private transient List<Partida> partidas;

    public Estadio(
            String codigo,
            String nome,
            int anoInauguracao,
            int capacidade,
            String pais, String estado, String cidade) {

        this.codigo = codigo;
        this.nome = nome;
        this.anoInauguracao = anoInauguracao;
        this.capacidade = capacidade;

        this.pais = pais;
        this.estado = estado;
        this.cidade = cidade;

        this.partidas = new ArrayList<>();
    }


    public void associarPartida(Partida novaPartida) {

        for (Partida partidaExistente : getPartidas()) {

            if (partidaExistente.getDataEHora()
                    .equals(novaPartida.getDataEHora())) {

                throw new ConflitoHorarioException(
                        "O estádio "
                                + nome
                                + " já possui uma partida neste horário."
                );
            }
        }

        getPartidas().add(novaPartida);
    }

    public void removerPartida(Partida partida) {
        getPartidas().remove(partida);
    }

    public int quantidadePartidas() {
        return getPartidas().size();
    }

    public String getCodigo() {
        return codigo;
    }

    public void setCodigo(String codigo) {
        this.codigo = codigo;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public int getAnoInauguracao() {
        return anoInauguracao;
    }

    public void setAnoInauguracao(int anoInauguracao) {
        this.anoInauguracao = anoInauguracao;
    }

    public int getCapacidade() {
        return capacidade;
    }

    public void setCapacidade(int capacidade) {
        this.capacidade = capacidade;
    }

    public List<Partida> getPartidas() {

        if(partidas == null) {
            partidas = new ArrayList<>();
        }

        return partidas;
    }

    public void setPartidas(List<Partida> partidas) {
        this.partidas = partidas;
    }

    public String getPais() {
        return pais;
    }

    public String getEstado() {
        return estado;
    }

    public String getCidade() {
        return cidade;
    }

    public void setPais(String pais) {
        this.pais = pais;
    }

    public void setEstado(String estado) {
        this.estado = estado;
    }

    public void setCidade(String cidade) {
        this.cidade = cidade;
    }

    @Override
    public String toString() {

        return nome +
                " - " +
                cidade +
                "/" +
                estado;
    }

    @Override
    public boolean equals(Object o) {

        if(this == o)
            return true;

        if(!(o instanceof Estadio))
            return false;

        Estadio estadio = (Estadio) o;

        return codigo.equals(estadio.codigo);
    }

    @Override
    public int hashCode() {
        return codigo.hashCode();
    }
}