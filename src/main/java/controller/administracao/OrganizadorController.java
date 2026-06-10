package controller.administracao;

import controller.exceptions.Copa2026Exceptions;
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
    //Note que o Organizador tem acesso a todas as seleções e joagores
    //Ele que administra isso, então ele tem acesso a todos

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

    //metodo auxiliar para verificar a validade da selecao
    private boolean jogadorEstaEmOutraSelecao(Jogador jogador){
        //Percorre todas as selecoes buscando jogador em mais de uma ao mesmo tempo
        for(Selecao selecao: Organizador.getSelecoes()){
            for(Jogador j: selecao.getJogadores()){
                if(j.getIdJogador().equals(jogador.getIdJogador())){
                    return true; //achou o jogador
                }
            }
        }
        return false; //não achou jogador
    }

    Organizador cadastraOrganizador(String nomeCompleto, String senha, String email, Tipo personagem) throws Copa2026Exceptions {
        validarNome(nomeCompleto);
        validarSenha(senha);
        if(email == null) throw new Copa2026Exceptions("Email não pode ser vazio");

        return new Organizador(nomeCompleto, email, senha, personagem);
    }

    Selecao criaSelecao(String idSelecao, String paisSelecao, String tecnico, char grupo, List<Jogador> equipe) throws Copa2026Exceptions{
        //esse cara tem que passar uma lista, não?
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
        for(Selecao s: Organizador.getSelecoes()){
            if(s.getIdSelecao().equals(idSelecao)){
                throw new Copa2026Exceptions("Já existe seleção com o id: "+idSelecao);
            }
            if(s.getPaisSelecao().equalsIgnoreCase(paisSelecao)){
                throw new Copa2026Exceptions("Já existe seleção do país: "+paisSelecao);
            }
        }

        validarJogadores(equipe);

        Selecao selecao = new Selecao(idSelecao, paisSelecao, tecnico, grupo);
        for(Jogador jogador: equipe){
            selecao.getJogadores().add(jogador);
        }

        return selecao;
    }

    public void excluiSelecao(String idSelecao) throws Copa2026Exceptions{
        if(idSelecao == null){
            throw new Copa2026Exceptions("Id da seleção não pode ser vazio");
        }

        //busca a selecao
        Selecao selecaoRemover = null;
        for(Selecao s: Organizador.getSelecoes()){
            if(s.getIdSelecao().equals(idSelecao)){
                selecaoRemover = s;
                break;
            }
        }

        if(selecaoRemover == null){
            throw new Copa2026Exceptions("Seleção não encontrada");
        }
        Organizador.getSelecoes().remove(selecaoRemover);
        System.out.println("Seleção removida com sucesso!");
    }

    public void excluiPartida(Selecao selecao1, Selecao selecao2 , LocalDateTime dataEHora)throws Copa2026Exceptions{
        if(selecao1 == null || selecao2 == null){
            throw new Copa2026Exceptions("Seleções não podem estar vazias");
        }
        if(dataEHora == null){
            throw new Copa2026Exceptions("Data e hora não podem ser nulas");
        }

        Partida partidaRemover = null;
        for(Partida p: Organizador.getPartidas()){
            if(p.getSelecao1().getIdSelecao().equals(selecao1.getIdSelecao()) && p.getSelecao2().getIdSelecao().equals(selecao2.getIdSelecao()) && p.getDataEHora().equals(dataEHora)){
                partidaRemover = p;
                break;
            }
        }

        if(partidaRemover == null){
            throw new Copa2026Exceptions("Partida não encontrada");
        }
        if(partidaRemover.getStatus() == Partida.StatusPartida.FINALIZADA){
            throw new Copa2026Exceptions("Não é possível excluir uma partida finalizada");
        }else if(partidaRemover.getStatus() == Partida.StatusPartida.ANDAMENTO){
            throw new Copa2026Exceptions("Não é possível excluir uma partida em andamento");
        }
        Organizador.getPartidas().remove(partidaRemover);
        System.out.println("Partida excluida com sucesso");
    }

    public List<Selecao> ListarSelecoes(){
        return new ArrayList<>(Organizador.getSelecoes());
    }

    public Selecao buscarSelecaoPorId(String idSelecao){
        for(Selecao s: Organizador.getSelecoes()){
            if(s.getIdSelecao().equals(idSelecao)){
                return s;
            }
        }
        return null;
    }

    public Partida buscarPartida(Selecao selecao1, Selecao selecao2 , LocalDateTime dataEHora){
        for(Partida p: Organizador.getPartidas()){
            if(p.getSelecao1().getIdSelecao().equals(selecao1.getIdSelecao()) && p.getSelecao2().getIdSelecao().equals(selecao2.getIdSelecao()) && p.getDataEHora().equals(dataEHora)){
                return p;
            }
        }
        return null;
    }

    public List<Partida> buscarPartidasPorSelecao(Selecao selecao){
        List<Partida> partidasEcontradas = new ArrayList<>();

        for(Partida p: Organizador.getPartidas()){
            if(p.getSelecao1().getIdSelecao().equals(selecao.getIdSelecao()) || p.getSelecao2().getIdSelecao().equals(selecao.getIdSelecao())){
                partidasEcontradas.add(p);
            }
        }
        return partidasEcontradas;
    }
}
