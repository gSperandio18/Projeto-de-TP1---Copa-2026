package controller.administracao;

import controller.exceptions.Copa2026Exceptions;
import domain.classes.administracao.SessaoUsuario;
import domain.classes.administracao.Usuario;

import java.util.ArrayList;

/*
 * Para a tela de login, que autentica o usuário
 * Coloca o usuário encontrado como o usuário da sessão
 */

public class LoginController extends UsuarioController {
    public void fazerLogin(String email, String senha) throws Copa2026Exceptions {
        // TODO: colocar a parte da DAO aqui
        ArrayList<Usuario> usuarios = new ArrayList<>();

        for (Usuario u : usuarios) {
            if (u.getEmail().equals(email)) {
                if (u.getSenha().equals(senha)) {
                    SessaoUsuario.getInstancia().setUsuarioAtual(u);
                } else {
                    throw new Copa2026Exceptions("Senha incorreta.");
                }
            }
        }
        throw new Copa2026Exceptions("Usuário não encontrado.");
    }
}
