package controller.partidas;

import controller.exceptions.Copa2026Exceptions;
import domain.classes.estadios.Estadio;
import domain.classes.partidas.Fase;
import domain.classes.partidas.Partida;
import domain.classes.partidas.Resultado;
import domain.classes.selecoes.Selecao;
import domain.dao.partidas.PartidaDAO;
import domain.dao.partidas.PartidaJsonDAO;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

public class PartidaController {
    private final PartidaDAO dao;

    PartidaController() {
        this.dao = new PartidaJsonDAO();
    }

    public void cadastrarPartida(Estadio estadio, Selecao selecao1, Selecao selecao2, String data,
                                 String horario, Fase fase, Partida.StatusPartida status) throws Copa2026Exceptions {

        if (selecao1.equals(selecao2)) {
            throw new Copa2026Exceptions("As seleções não podem ser iguais.");
        }

        /* Construir data a partir das strings */
        DateTimeFormatter formato = DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm");
        LocalDateTime dataPartida = LocalDateTime.parse(data + ' ' + horario, formato);


        /* Pegar todas as partidas e verificar se alguma delas ocorre no mesmo horário e tem alguma das seleções dessa partida */
        List<Partida> partidas = dao.carregar();
        for (Partida p : partidas) {

            // Considerando que um jogo tem aproximadamente 90 minutos, não pode haver outros jogos com a mesma seleção
            // nem 90min antes do início desse jogo, nem menos de 90min depois do começo do jogo
            LocalDateTime finalPartidaExistente = p.getDataEHora().plusMinutes(90);
            LocalDateTime finalPartidaNova = dataPartida.plusMinutes(90);

            // Houve colisão de horários com a partida "p"
            if (dataPartida.isBefore(finalPartidaExistente) && p.getDataEHora().isBefore(finalPartidaNova)) {
                if (selecao1.equals(p.getSelecao1()) || selecao1.equals(p.getSelecao2())) {
                    throw new Copa2026Exceptions("A seleção do(a) " + selecao1.getPaisSelecao() + " já vai jogar um jogo no mesmo horário.");
                } else if (selecao2.equals(p.getSelecao1()) || selecao2.equals(p.getSelecao2())) {
                    throw new Copa2026Exceptions("A seleção do(a) " + selecao2.getPaisSelecao() + " já vai jogar um jogo no mesmo horário.");
                }
            }
        }

        Partida novaPartida = new Partida(estadio, selecao1, selecao2, dataPartida, fase, status);
        partidas.add(novaPartida);
        dao.salvar(partidas);
    }

    public List<Partida> filtrarPartidas(Selecao filtroSelecao, Fase filtroFase, String dataFiltro) {
        List<Partida> partidas = dao.carregar();
        ArrayList<Partida> partidasEncontradas = new ArrayList<>();

        for (Partida p : partidas) {
            // Verifica se o filtro está vazio ou se encaixa nessa partida
            boolean selecaoDesejada = (filtroSelecao == null) || p.getSelecao1().equals(filtroSelecao) || p.getSelecao2().equals(filtroSelecao);
            boolean faseDesejada = (filtroFase == null) || p.getFase() == filtroFase;
            boolean dataDesejada = (dataFiltro == null) || dataFiltro.trim().isEmpty() ||
                                    (p.getDataEHora().toLocalDate().toString().contains(dataFiltro));

            if (selecaoDesejada && faseDesejada && dataDesejada) {
                partidasEncontradas.add(p);
            }
        }

        return partidasEncontradas;
    }

    public void registrarResultados(Partida partida, int placarSelecao1, int placarSelecao2, String descricao) throws Copa2026Exceptions {
        if (placarSelecao1 < 0 || placarSelecao2 < 0) {
            throw new Copa2026Exceptions("O placar de uma seleção não pode ser negativo.");
        } else if (partida.getStatus() == (Partida.StatusPartida.FINALIZADA)) {
            throw new Copa2026Exceptions("A partida selecionada já foi finalizada anteriormente.");
        }

        /* passar mesmo ID gerado para a partida */
        partida.setResultado(new Resultado(partida.getId(), partida, placarSelecao1, placarSelecao2, descricao));
        partida.setStatus(Partida.StatusPartida.FINALIZADA);

        /* atualizar no JSON */
        List<Partida> partidas = dao.carregar();

        for (int i = 0; i < partidas.size(); i++) {
            Partida p = partidas.get(i);

            if (p.getId().equals(partida.getId())) {
                partidas.set(i, partida);
                break;
            }
        }

        dao.salvar(partidas);
    }
}