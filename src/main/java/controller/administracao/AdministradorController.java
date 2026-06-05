package controller.administracao;

import controller.exceptions.Copa2026Exceptions;
import domain.classes.administracao.*;
import domain.classes.administracao.Usuario.Tipo;
import domain.classes.estadios.Arbitro;

import java.util.*;

public class AdministradorController extends UsuarioController{
    private List<Arbitro> listaArbitro = new ArrayList<>();
    private List<Organizador> listaOrganizador = new ArrayList<>();

    //Dao dao;

    Administrador cadastrarAdministrador(String nomeCompleto, String email, String senha, Tipo personagem)throws Copa2026Exceptions {
        validarNome(nomeCompleto);
        validarSenha(senha);
        if(email == null) throw new Copa2026Exceptions("Email não pode ser vazio");


        return new Administrador(nomeCompleto, email, senha, personagem);
    }

    public void criaUsuario(String nomeCompleto, String email, String senha, Tipo personagem )throws Copa2026Exceptions{
        validarNome(nomeCompleto);
        validarSenha(senha);
        if(email == null) return;

        if(personagem == Tipo.ARBITRO){
            Arbitro arbitro = new Arbitro(nomeCompleto,senha,email,personagem);
            listaArbitro.add(arbitro);
        }else if(personagem == Tipo.ORGANIZADOR){
            Organizador organizador = new Organizador(nomeCompleto, senha, email,personagem);
            listaOrganizador.add(organizador);
        }
    }

    public void excluiUsuario(String nomeCompleto, String senha, Tipo personagem)throws Copa2026Exceptions{
        validarNome(nomeCompleto);
        validarSenha(senha);

        if(personagem == Tipo.ORGANIZADOR){
            for(Organizador org: listaOrganizador) {
                if (org.getNomeCompleto().equals(nomeCompleto)) {
                    listaOrganizador.remove(org);
                    System.out.println("Organizador excluído com sucesso");
                    return;
                }
            }
        }else if(personagem == Tipo.ARBITRO){
            for(Arbitro arb: listaArbitro){
                if(arb.getNomeCompleto().equals(nomeCompleto)){
                    listaArbitro.remove(arb);
                    System.out.println("Árbitro excluído com sucesso");
                    return;
                }
            }
        }
    }

    public void getRelatorio(){

    }
}
