package domain.classes.administracao;

import domain.classes.partidas.Partida;
import domain.classes.selecoes.Jogador;
import domain.classes.selecoes.Selecao;

import java.util.ArrayList;
import java.util.List;

public class Organizador extends Usuario {
    public Organizador(String nomeCompleto, String senha, String email,Tipo personagem) {
        super(nomeCompleto, email, senha, personagem);
    }
}
