package controller.administracao;

import controller.exceptions.Copa2026Exceptions;
import controller.partidas.PartidaController;
import domain.classes.administracao.Usuario.Tipo;
import domain.classes.administracao.*;
import domain.classes.estadios.Estadio;
import domain.classes.partidas.Fase;
import domain.classes.partidas.Partida;
import domain.classes.selecoes.Jogador;
import domain.classes.selecoes.Selecao;

import java.time.LocalDateTime;
import java.util.*;

public class OrganizadorController extends UsuarioController {
    private PartidaController partidaController;
    private SelecaoDAO selecaoDAO;
    private PartidaDAO partidaDAO;
    private List<Selecao> selecoes;
    private List<Partida> partidas;

    public OrganizadorController(){
        this.selecaoDAO = new SelecaoJsonDAO();
        this.partidaDAO = new PartidaJsonDAO();
        this.selecoes = selecaoDAO.carregar();
        this.partidas = partidaDAO.carregar();
    }

    /*VALIDAÇÃO DE DADOS*/
    public boolean validarJogadores(List<Jogador> equipe) throws Copa2026Exceptions{
        //cada jogador só pode participar de 1 selecao;
        if(equipe == null){
            throw new Copa2026Exceptions("Equipe não pode estar vazia");
        }
        if(equipe.size() < 18){
            throw new Copa2026Exceptions("Equipe não pode possuir menos que 18 jogadores. Atual: "+equipe.size());
        }else if(equipe.size() > 26){
            throw new Copa2026Exceptions("Equipe não pode possuir mais que 26 jogadores. Atual: "+equipe.size());
        }

        //verifica se há jogadores duplicados na mesma selecao
        Set<String> idsJogadoresEquipe = new HashSet<>();
        for(Jogador jogador: equipe){
            if(idsJogadoresEquipe.contains(jogador.getIdJogador())){
                throw new Copa2026Exceptions("Jogador '"+jogador.getNomeJogador()+"' duplicado na mesma seleção");
            }
            idsJogadoresEquipe.add(jogador.getIdJogador());
        }

        //verifica se cada jogador não está em outra seleção
        for(Jogador jogador: equipe){
            if(jogadorEstaEmOutraSelecao(jogador)){
                throw new Copa2026Exceptions("jogador '"+jogador.getNomeJogador()+"' já está em outra seleção");
            }
        }
        return true;
    }

    private boolean jogadorEstaEmOutraSelecao(Jogador jogador){
        //Percorre todas as selecoes buscando jogador em mais de uma ao mesmo tempo
        for(Selecao selecao: selecoes){
            for(Jogador j: selecao.getJogadores()){
                if(j.getIdJogador().equals(jogador.getIdJogador())){
                    return true; //achou o jogador
                }
            }
        }
        return false;
    }

    /*CRIA ORGANIZADOR*/
    Organizador cadastraOrganizador(String nomeCompleto, String senha, String email, Tipo personagem) throws Copa2026Exceptions {
        validarNome(nomeCompleto);
        validarSenha(senha);
        if(email == null) throw new Copa2026Exceptions("Email não pode ser vazio");

        return new Organizador(nomeCompleto, email, senha, personagem);
    }

    /*GERENCIA SELEÇÕES*/
    Selecao criaSelecao(String idSelecao, String paisSelecao, String tecnico, char grupo, List<Jogador> equipe) throws Copa2026Exceptions{
        verificarPermissaoCompeticao();

        if(idSelecao == null){
            throw new Copa2026Exceptions("Id da seleção não pode ser vazio");
        }
        if(paisSelecao == null){
            throw new Copa2026Exceptions("País da seleção não pode ser vazio");
        }
        if(tecnico == null){
            throw new Copa2026Exceptions("Nome do técnico não pode ser vazio");
        }

        //verifica se existe id repetido
        if(buscarSelecaoPorId(idSelecao) != null){
            throw new Copa2026Exceptions("Seleção já existe com ID: "+idSelecao);
        }

        validarJogadores(equipe);

        Selecao selecao = new Selecao(idSelecao, paisSelecao, tecnico, grupo);
        for(Jogador jogador: equipe){
            selecao.getJogadores().add(jogador);
        }
        selecoes.add(selecao);
        selecaoDAO.salvar(selecoes);

        return selecao;
    }

    public void excluiSelecao(String idSelecao) throws Copa2026Exceptions{
        verificarPermissaoCompeticao();

        Selecao selecao = buscarSelecaoPorId(idSelecao);
        if(selecao == null){
            throw new Copa2026Exceptions("Seleção não encontrada");
        }
        selecoes.remove(selecao);
        selecaoDAO.salvar(selecoes);
    }

    /*GERENCIA PARTIDAS*/
    public void criaPartida(Estadio estadio, Selecao selecao1, Selecao selecao2, String data, String horario, Fase fase, Partida.StatusPartida status) throws Copa2026Exceptions{
        verificarPermissaoCompeticao();

        if(!selecoes.contains(selecao1) || !selecoes.contains(selecao2)){
            throw new Copa2026Exceptions("Seleção não cadastrada no sistema!");
        }

        partidaController.cadastrarPartida(estadio, selecao1, selecao2, data, horario, fase, status);
    }

    public void excluiPartida(Selecao selecao1, Selecao selecao2 , LocalDateTime dataEHora)throws Copa2026Exceptions{
        verificarPermissaoCompeticao();

        if(selecao1 == null || selecao2 == null){
            throw new Copa2026Exceptions("Seleções não podem estar vazias");
        }
        if(dataEHora == null){
            throw new Copa2026Exceptions("Data e hora não podem ser nulas");
        }

        Partida partida = buscarPartida(selecao1, selecao2, dataEHora);

        if(partida == null){throw new Copa2026Exceptions("Partida não encontrada!");}
        if(partida.getStatus() == Partida.StatusPartida.FINALIZADA){throw new Copa2026Exceptions("Não é possível excluir uma partida finalizada");}
        if(partida.getStatus() == Partida.StatusPartida.ANDAMENTO){throw new Copa2026Exceptions("Não é possível excluir uma partida em andamento");}

        partidas.remove(partida);
        partidaDAO.salvar(partidas);
    }

    /*MÉTODOS AUXILIARES*/
    public List<Selecao> ListarSelecoes(){
        return new ArrayList<>(selecoes);
    }

    public Selecao buscarSelecaoPorId(String idSelecao){
        for(Selecao s: selecoes){
            if(s.getIdSelecao().equals(idSelecao)){
                return s;
            }
        }
        return null;
    }

    public Partida buscarPartida(Selecao selecao1, Selecao selecao2 , LocalDateTime dataEHora){
        for(Partida p: partidas){
            if(p.getSelecao1().getIdSelecao().equals(selecao1.getIdSelecao())
                    && p.getSelecao2().getIdSelecao().equals(selecao2.getIdSelecao())
                    && p.getDataEHora().equals(dataEHora)){
                return p;
            }
        }
        return null;
    }
}
