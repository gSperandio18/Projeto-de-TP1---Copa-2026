package domain.classes.selecoes;

public class Jogador {


    private String idJogador, nomeJogador, posicaoJogador;
    private int numeroJogador, idadeJogador;
    private Selecao selecaoJogador;
    private String status;
    private boolean podeParticipar; //podeParticipar eh um booleano que indica se o jogador pode participar de acordo com o status do jogador

    public Jogador (String idJogador, String nomeJogador, String posicaoJogador, int numeroJogador,
                    int idadeJogador, Selecao selecaoJogador, String status) {
        this.idJogador = idJogador;
        this.nomeJogador = nomeJogador;
        this.posicaoJogador = posicaoJogador;
        this.numeroJogador = numeroJogador;
        this.idadeJogador = idadeJogador;
        this.selecaoJogador = selecaoJogador;
        this.status = status;
        if (status.equalsIgnoreCase("lesionado") || status.equalsIgnoreCase("suspenso")) {
            this.podeParticipar = false;
        }
        else {
            this.podeParticipar = true;
        }
    }

    public String getIdJogador() {
        return idJogador;
    }

    public void setIdJogador(String idJogador) {
        this.idJogador = idJogador;
    }

    public String getNomeJogador() {
        return nomeJogador;
    }

    public void setNomeJogador(String nomeJogador) {
        this.nomeJogador = nomeJogador;
    }

    public String getPosicaoJogador() {
        return posicaoJogador;
    }

    public void setPosicaoJogador(String posicaoJogador) {
        this.posicaoJogador = posicaoJogador;
    }

    public int getNumeroJogador() {
        return numeroJogador;
    }

    public void setNumeroJogador(int numeroJogador) {
        this.numeroJogador = numeroJogador;
    }

    public int getIdadeJogador() {
        return idadeJogador;
    }

    public void setIdadeJogador(int idadeJogador) {
        this.idadeJogador = idadeJogador;
    }

    public Selecao getSelecaoJogador() {
        return selecaoJogador;
    }

    public void setSelecaoJogador(Selecao selecaoJogador) {
        this.selecaoJogador = selecaoJogador;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public boolean getPodeParticipar() { return podeParticipar; }

    public void setPodeParticipar(boolean podeParticipar) {
        this.podeParticipar = podeParticipar;
    }
}