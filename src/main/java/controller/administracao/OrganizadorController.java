package controller.administracao;

import controller.exceptions.Copa2026Exceptions;
import domain.classes.administracao.Usuario.Tipo;
import domain.classes.administracao.*;
import domain.classes.estadios.Estadio;
import domain.classes.partidas.Fase;
import domain.classes.partidas.Partida;
import domain.classes.selecoes.Jogador;
import domain.classes.selecoes.Selecao;

import java.time.LocalDateTime;
import java.util.*;

public class OrganizadorController extends UsuarioController {
    //Note que o Organizador tem acesso a todas as seleções e joagores
    //Ele que administra isso, então ele tem acesso a todos
    private List<Selecao> listaSelecoes = new ArrayList<>();
    private List<Jogador> listaJogadores = new ArrayList<>();

    public boolean validarSelecao(){
        //min 18 jogadores e max 26;
        return true;
    }

    public boolean validarJogador(){
        //cada jogador só pode participar de 1 selecao;
        return true;
    }

    Organizador cadastraOrganizador(String nomeCompleto, String senha, String email, Tipo personagem) throws Copa2026Exceptions {
        validarNome(nomeCompleto);
        validarSenha(senha);
        if(email == null) throw new Copa2026Exceptions("Email não pode ser vazio");

        return new Organizador(nomeCompleto, email, senha, personagem);
    }

    Selecao criaSelecao(String idSelecao, String paisSelecao, String tecnico, char grupo){
        return null;
    }

    Partida criaPartida(Estadio estadio, Selecao selecao1, Selecao selecao2,
                            LocalDateTime dataEHora, Fase fase, Partida.StatusPartida status)
    {

        return new Partida(estadio, selecao1, selecao2, dataEHora, fase, status);
    }

    public void excluiPartida(){}

    public void excluiSelecao(){}
}
