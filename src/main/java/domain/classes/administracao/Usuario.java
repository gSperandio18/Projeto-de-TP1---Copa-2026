package domain.classes.administracao;

public abstract class Usuario {
    private String nomeCompleto;
    private String email;
    private String senha;
    private Tipo personagem;

    public enum Tipo{
        ADMINISTRADOR,
        ARBITRO,
        ORGANIZADOR;

        public boolean podeGerenciarCompeticao(){
            return this == ADMINISTRADOR || this == ORGANIZADOR;
        }
        public boolean podeAdministrarSistema(){
            return this == ADMINISTRADOR;
        }
    }

    public Usuario(String nomeCompleto, String email, String senha,Tipo personagem){
        this.nomeCompleto = nomeCompleto;
        this.email = email;
        this.senha = senha;
        this.personagem = personagem;
    }

    public String getNomeCompleto() {
        return nomeCompleto;
    }

    public void setNomeCompleto(String nomeCompleto) {
        this.nomeCompleto = nomeCompleto;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getSenha() {
        return senha;
    }

    public void setSenha(String senha) {
        this.senha = senha;
    }

    public Tipo getPersonagem(){ return personagem; }

    public boolean podeGerenciarCompeticao(){
        return personagem.podeGerenciarCompeticao();
    }

    public boolean podeAdministrarSistema(){
        return personagem.podeAdministrarSistema();
    }
}