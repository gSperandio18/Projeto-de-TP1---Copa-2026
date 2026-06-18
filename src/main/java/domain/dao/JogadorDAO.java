package domain.dao;

import domain.classes.selecoes.Jogador;

import java.util.List;

public interface JogadorDAO {
    void salvar(List<Jogador> jogadores);
    List<Jogador> carregar();
}
