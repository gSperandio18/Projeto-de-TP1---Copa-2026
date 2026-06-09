package domain.classes.administracao;

import domain.classes.estadios.Arbitro;

import java.util.ArrayList;
import java.util.List;

public class Administrador extends Usuario {
    private static List<Arbitro> arbitros =  new ArrayList<>();
    private static List<Organizador> organizadores = new ArrayList<>();
    private static List<Administrador> administradores = new ArrayList<>();

    public Administrador(String nomeCompleto,String email, String senha,Tipo personagem){
        super(nomeCompleto, email, senha,personagem);

    }

    public static List<Arbitro> getArbitros() {
        return arbitros;
    }

    public void setArbitros(List<Arbitro> arbitros) {
        this.arbitros = arbitros;
    }

    public static List<Organizador> getOrganizadores() {
        return organizadores;
    }

    public void setOrganizadores(List<Organizador> organizadores) {
        this.organizadores = organizadores;
    }

    public static List<Administrador> getAdministradores() {return administradores;}

    public void setAdministradores(List<Administrador> adm) {this.administradores = administradores;}
}