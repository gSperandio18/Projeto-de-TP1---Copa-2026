package domain.classes.administracao;

/*
 * Classe para guardar qual o usuário logado na sessão atual
 * Na tela de login, a classe é inicializada com o getInstancia e o usuário é colocado aqui dentro
 * Como é estático, essa instância é a única durante o programa (o que faz só ter um usuário logado por vez)
 *
 * Em qualquer classe, pode-se importar a SessaoUsuario e obter o usuário
 * logado com SessaoUsuario.getInstancia().getUsuarioAtual()
 */

public class SessaoUsuario {
    private static SessaoUsuario instancia;
    private Usuario usuarioAtual;

    private SessaoUsuario() {}

    public static SessaoUsuario getInstancia() {
        if (instancia == null) {
            instancia = new SessaoUsuario();
        }
        return instancia;
    }

    public Usuario getUsuarioAtual() {
        return usuarioAtual;
    }

    public void setUsuarioAtual(Usuario usuarioAtual) {
        this.usuarioAtual = usuarioAtual;
    }
}
