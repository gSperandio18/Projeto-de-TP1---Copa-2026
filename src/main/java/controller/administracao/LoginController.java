package controller.administracao;

import controller.exceptions.Copa2026Exceptions;
import domain.classes.administracao.SessaoUsuario;
import domain.classes.administracao.Usuario;
import domain.dao.UsuarioJsonDAO;
import domain.dao.UsuarioDAO;

import java.util.ArrayList;

/*
 * Para a tela de login, que autentica o usuário
 * Coloca o usuário encontrado como o usuário da sessão
 */

public class LoginController extends UsuarioController {
    private UsuarioJsonDAO usuarioDAO;

    public LoginController(){
        this.usuarioDAO = new UsuarioJsonDAO();
    }

    public void fazerLogin(String email, String senha) throws Copa2026Exceptions {
        validarSenha(senha);
        if(email == null){ throw new Copa2026Exceptions("Email não pode ser vazio");}

        java.util.List<Usuario> usuarios = usuarioDAO.carregar();

        for (Usuario u : usuarios) {
            if (u.getEmail().equals(email)) {
                if (u.getSenha().equals(senha)) {
                    //Achou o personagem
                    SessaoUsuario.getInstancia().setUsuarioAtual(u);
                    return;
                } else {
                    System.out.println(senha);
                    System.out.println(u.getSenha());
                    throw new Copa2026Exceptions("Senha incorreta.");
                }
            }
        }
        throw new Copa2026Exceptions("Usuário não encontrado.");
    }
}
