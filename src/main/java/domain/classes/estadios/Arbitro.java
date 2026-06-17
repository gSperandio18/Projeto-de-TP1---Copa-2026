package domain.classes.estadios;

import domain.classes.administracao.Usuario;
import domain.classes.partidas.Partida;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

public class Arbitro extends Usuario {

    private String pais;
    private int experiencia;
    private String codigo;
    //private Tipo personagem;

    private transient List<Partida> partidasApitadas;

    public Arbitro(
            String nomeCompleto,
            String email,
            String senha,
            Tipo personagem) {

        super(nomeCompleto, email, senha, personagem);

        this.pais = null;
        this.experiencia = 0;
        this.partidasApitadas = new ArrayList<>();
        this.codigo =
                "ARB-" +
                        UUID.randomUUID()
                                .toString()
                                .substring(0,8);

    }

    public void adicionarPartida(Partida partida){
        getPartidasApitadas().add(partida);
    }

    public void setPais(String pais) {
        this.pais = pais;
    }

    public void setExperiencia(int experiencia) {
        this.experiencia = experiencia;
    }

    public void setPartidasApitadas(List<Partida> partidasApitadas) {
        this.partidasApitadas = partidasApitadas;
    }

    public String getPais() {
        return pais;
    }

    public int getExperiencia() {
        return experiencia;
    }


    public List<Partida> getPartidasApitadas() {

        if(partidasApitadas == null) {
            partidasApitadas = new ArrayList<>();
        }

        return partidasApitadas;
    }

    public String getCodigo() {
        return codigo;
    } //TA AQUI

    @Override
    public String toString() {
        return getNomeCompleto();
    }

    @Override
    public boolean equals(Object o) {

        if(this == o)
            return true;

        if(!(o instanceof Arbitro))
            return false;

        Arbitro arbitro = (Arbitro) o;

        return codigo.equals(arbitro.codigo);
    }

    @Override
    public int hashCode() {
        return codigo.hashCode();
    }
}