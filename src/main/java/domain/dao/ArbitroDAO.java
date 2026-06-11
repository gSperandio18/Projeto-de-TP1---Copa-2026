package dao;

import classes.estadios.Arbitro;
import java.util.List;

public interface ArbitroDAO {

    void salvar(List<Arbitro> arbitros);

    List<Arbitro> carregar();
}