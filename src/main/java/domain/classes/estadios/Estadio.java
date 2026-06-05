package domain.classes.estadios;

import domain.classes.partidas.Partida;

import java.util.List;
import java.util.ArrayList;

public class Estadio {
    private String codigo;
    private String nome;
    private int anoInauguracao;
    private int capacidade;
    private String cep;

    final private List<Partida> partidas; //para fins de pesquisa

    public void assinalaPartida(Partida novaPartida) throws ConflitoHorarioException{
        for(Partida partidaExistente : partidas){
            if(partidaExistente.getDataEHora().equals(novaPartida.getDataEHora())){
                throw new ConflitoHorarioException("Erro: o estadio" + this.nome + "já possui a partida " + partidaExistente.getId() + " neste horário");
            }
        }

        partidas.add(novaPartida);
    }

    public Estadio(String codigo, String nome, int anoInauguracao, int capacidade, String cep) {
        this.codigo = codigo;
        this.nome = nome;
        this.anoInauguracao = anoInauguracao;
        this.capacidade = capacidade;
        this.cep = cep;
        this.partidas = new ArrayList<>();
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

    public String getCep() {
        return cep;
    }

    public void setCep(String cep) {
        this.cep = cep;
    }

    public List<Partida> getPartidas(){
        return partidas;
    }
}