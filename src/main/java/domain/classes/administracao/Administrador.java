package domain.classes.administracao;

import domain.classes.estadios.Arbitro;

import java.util.ArrayList;
import java.util.List;

public class Administrador extends Usuario {
    public Administrador(String nomeCompleto,String email, String senha,Tipo personagem){
        super(nomeCompleto, email, senha,personagem);
    }
}