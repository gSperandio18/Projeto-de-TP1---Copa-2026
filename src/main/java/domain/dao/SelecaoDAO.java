package domain.dao;

import domain.classes.selecoes.Selecao;

import java.util.List;

public interface SelecaoDAO {
    void salvar(List<Selecao> selecoes);
    List<Selecao> carregar();
}
