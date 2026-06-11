package dao;

import classes.estadios.DesignacaoArbitroPartida;
import java.util.List;

public interface DesignacaoDAO {

    void salvar(List<DesignacaoArbitroPartida> designacoes);

    List<DesignacaoArbitroPartida> carregar();
}