package controller.selecoes;

import domain.classes.selecoes.Jogador;
import domain.classes.selecoes.Selecao;
import domain.dao.JogadorDAO;
import domain.dao.JogadorJsonDAO;

import java.util.ArrayList;
import java.util.List;

public class JogadorController {
    private final JogadorDAO dao;
    private final List<Jogador> jogadores;

    public JogadorController() {
        dao = new JogadorJsonDAO();
        jogadores = dao.carregar();
    }


    public List<Jogador> listar() {
        return new ArrayList<>(jogadores);
    }

    public void cadastrar(Jogador jogador) {

        for(Jogador a : jogadores){
            if(a.getIdJogador().equalsIgnoreCase(jogador.getIdJogador())){
                throw new IllegalArgumentException(
                        "Jogador já cadastrado."
                );
            }
        }

        jogadores.add(jogador);
        dao.salvar(jogadores);
    }

    public List<Jogador> pesquisar(
            String id,
            String nome,
            String posicao,
            String status,
            Integer numero) {

        return jogadores.stream()

                .filter(a ->
                        id == null ||
                                id.isBlank() ||
                                a.getIdJogador()
                                        .equals(id))
                .filter(a ->
                        nome == null ||
                                nome.isBlank() ||
                                a.getNomeJogador()
                                        .toLowerCase()
                                        .contains(nome.toLowerCase()))

                .filter(a ->
                        posicao == null ||
                                posicao.isBlank() ||
                                (a.getPosicaoJogador() != null &&
                                        a.getPosicaoJogador().equalsIgnoreCase(posicao)))
                .filter(a ->
                        status == null ||
                                status.isBlank() ||
                                a.getStatus()
                                        .equalsIgnoreCase(status))

                .filter(a ->
                        numero == null ||
                                a.getNumeroJogador() == numero)
                .toList();
    }



    public void editar(
            String id,
            String nome,
            String posicao,
            int numero,
            int idade,
            Selecao selecao,
            String status) {

        for (Jogador jogador : jogadores) {

            if (jogador.getIdJogador().equals(id)) {

                jogador.setNomeJogador(nome);
                jogador.setPosicaoJogador(posicao);
                jogador.setNumeroJogador(numero);
                jogador.setIdadeJogador(idade);
                jogador.setSelecaoJogador(selecao);
                jogador.setStatus(status);
                if (status.equalsIgnoreCase("ativo")){
                    jogador.setPodeParticipar(true);
                }
                if (status.equalsIgnoreCase("suspenso") || status.equalsIgnoreCase("lesionado")){
                    jogador.setPodeParticipar(false);
                }

                dao.salvar(jogadores);

                return;
            }
        }

        throw new IllegalArgumentException(
                "Jogador não encontrado."
        );
    }

    public void excluir(String id) {

        boolean removido = jogadores.removeIf(
                e -> e.getIdJogador().equals(id)
        );

        if(!removido){
            throw new IllegalArgumentException(
                    "Jogador não encontrado."
            );
        }

        dao.salvar(jogadores);
    }
}

