package domain.dao;

import domain.classes.estadios.Arbitro;

import java.util.List;

public interface ArbitroDAO {
    void salvar(List<Arbitro> arbitros);
    List<Arbitro> carregar();
}
