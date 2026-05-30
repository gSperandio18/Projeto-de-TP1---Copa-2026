package classes.estadios;

import classes.partidas.Partida;
import classes.administracao.Usuario;

import java.util.List;

public class Arbitro extends Usuario {
    private String pais;
    private int experiencia;
    private List<Partida> partidasApitadas;

    public Arbitro(String nomeCompleto, String email, String senha) {
        super(nomeCompleto, email, senha);
    }

    public String getPais() {
        return pais;
    }

    public void setPais(String pais) {
        this.pais = pais;
    }

    public int getExperiencia() {
        return experiencia;
    }

    public void setExperiencia(int experiencia) {
        this.experiencia = experiencia;
    }

    public List<Partida> getPartidasApitadas() {
        return partidasApitadas;
    }

    public void setPartidasApitadas(List<Partida> partidasApitadas) {
        this.partidasApitadas = partidasApitadas;
    }
}