package domain.classes.administracao;

import domain.classes.estadios.Arbitro;
import java.util.List;

public class Administrador extends Usuario {
    private int codigoPartida;
    private List<Arbitro> arbitros;
    private List<Organizador> organizadores;

    public Administrador(String nomeCompleto,String email, String senha,Tipo personagem){
        super(nomeCompleto, email, senha,personagem);

    }

    public int getCodigoPartida() {
        return codigoPartida;
    }

    public void setCodigoPartida(int codigoPartida) {
        this.codigoPartida = codigoPartida;
    }

    public List<Arbitro> getArbitros() {
        return arbitros;
    }

    public void setArbitros(List<Arbitro> arbitros) {
        this.arbitros = arbitros;
    }

    public List<Organizador> getOrganizadores() {
        return organizadores;
    }

    public void setOrganizadores(List<Organizador> organizadores) {
        this.organizadores = organizadores;
    }
}