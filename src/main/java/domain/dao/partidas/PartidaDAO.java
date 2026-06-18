package domain.dao.partidas;

import domain.classes.partidas.Partida;
import java.util.List;

public interface PartidaDAO {
    void salvar(List<Partida> partidas);

    List<Partida> carregar();
}
