package domain.dao;

import domain.classes.administracao.Usuario;
import java.util.List;

public interface UsuarioDAO {
    void salvar(List<Usuario> usuarios);
    List<Usuario> carregar();
}
