package dao;

import classes.estadios.Estadio;
import java.util.List;

public interface EstadioDAO {

    void salvar(List<Estadio> estadios);

    List<Estadio> carregar();
}