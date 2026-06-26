package controller.partidas;

import controller.exceptions.Copa2026Exceptions;
import domain.classes.estadios.ConflitoHorarioException;
import domain.classes.estadios.ConflitoPaisException;
import domain.classes.estadios.Arbitro;
import domain.classes.estadios.Estadio;
import domain.classes.partidas.Fase;
import domain.classes.partidas.Partida;
import domain.classes.partidas.Resultado;
import domain.classes.selecoes.Jogador;
import domain.classes.selecoes.Selecao;
import domain.dao.partidas.PartidaDAO;
import domain.dao.partidas.PartidaJsonDAO;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.ArrayList;
import java.util.List;

public class PartidaController {
    private final PartidaDAO dao;
    private final List<Partida> partidas;

    public PartidaController() {
        this.dao = new PartidaJsonDAO();
        partidas = dao.carregar();
    }

    /* Verifica se todos os jogadores da seleção podem jogar */
    public boolean verificarSelecoes(Selecao selecao) {
        for (Jogador j : selecao.getJogadores()) {
            if (!j.getPodeParticipar()) {
                return false;
            }
        }
        return true;
    }

    public LocalDateTime validarDados(Estadio estadio, Selecao selecao1, Selecao selecao2, String data,
                                      String horario, Fase fase, Partida.StatusPartida status, String idPartidaAIgnorar)
            throws Copa2026Exceptions {

        /* Primeira verificação para ver se nada veio vazio */
        if (estadio == null) { throw new Copa2026Exceptions("É necessário escolher um estádio."); }
        else if (selecao1 == null) { throw new Copa2026Exceptions("O campo da seleção 1 não pode ficar vazio."); }
        else if (selecao2 == null) { throw new Copa2026Exceptions("O campo da seleção 2 não pode ficar vazio."); }
        else if (data == null || data.isBlank()) { throw new Copa2026Exceptions("É necessário digitar a data do jogo."); }
        else if (horario == null || horario.isBlank()) { throw new Copa2026Exceptions("É necessário digitar o horário do jogo."); }
        else if (fase == null) { throw new Copa2026Exceptions("É necessário preencher a fase do jogo."); }
        else if (status == null) { throw new Copa2026Exceptions("É necessário preencher o status do jogo."); }

        if (selecao1.equals(selecao2)) {
            throw new Copa2026Exceptions("As seleções não podem ser iguais.");
        }

        /* Construir data a partir das strings */
        LocalDateTime dataPartida;
        DateTimeFormatter formato = DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm");
        try {
            dataPartida = LocalDateTime.parse(data + ' ' + horario, formato);
        } catch (DateTimeParseException e) {
            throw new Copa2026Exceptions("Data ou horário inserido é inválido. Eles devem estar no formato" +
                    " dd/MM/yyyy e HH:mm.");
        }

        /* Verificar se as seleções têm algum jogador que não pode jogar */
        if (!verificarSelecoes(selecao1)) {
            throw new Copa2026Exceptions("A seleção 1 tem um jogador não apto a jogar.");
        } else if (!verificarSelecoes(selecao2)) {
            throw new Copa2026Exceptions("A seleção 2 tem um jogador não apto a jogar.");
        }

        /* Pegar todas as partidas e verificar se alguma delas ocorre no mesmo horário e tem alguma das seleções dessa partida
         * ou tem o mesmo estádio */
        for (Partida p : partidas) {

            /* Para usar na edição; se estivermos editando uma partida, não queremos que ela cheque a disponibilidade
              * de horário com ela mesma, então passamos o código da partida para que ela seja ignorada */
            if (p.getId().equals(idPartidaAIgnorar) && idPartidaAIgnorar != null) {
                continue;
            }

            /* Checar se o dia bate primeiro */
            if (!dataPartida.toLocalDate().equals(p.getDataEHora().toLocalDate())) {
                continue;
            }

            /* Considerando que um jogo tem aproximadamente 90 minutos, não pode haver outros jogos com a mesma seleção
             * nem 90min antes do início desse jogo, nem menos de 90min depois do começo do jogo */
            LocalDateTime finalPartidaExistente = p.getDataEHora().plusMinutes(90);
            LocalDateTime finalPartidaNova = dataPartida.plusMinutes(90);

            /* Houve colisão de horários com a partida "p" */
            if (dataPartida.isBefore(finalPartidaExistente) && p.getDataEHora().isBefore(finalPartidaNova)) {
                if (selecao1.equals(p.getSelecao1()) || selecao1.equals(p.getSelecao2())) {
                    throw new ConflitoHorarioException("A seleção do(a) " + selecao1.getPaisSelecao() + " já vai " +
                            "jogar um jogo no mesmo horário.");
                } else if (selecao2.equals(p.getSelecao1()) || selecao2.equals(p.getSelecao2())) {
                    throw new ConflitoHorarioException("A seleção do(a) " + selecao2.getPaisSelecao() + " já vai " +
                            "jogar um jogo no mesmo horário.");
                } else if (estadio.equals(p.getEstadio())) {
                    throw new ConflitoHorarioException("O estádio " + estadio.getNome() + " já está reservado nesse " +
                            "horário, para a partida: " + p.toString() + '.');
                }
            }
        }

        return dataPartida;
    }

    public Partida cadastrarPartida(Estadio estadio, Selecao selecao1, Selecao selecao2, String data,
                                 String horario, Fase fase, Partida.StatusPartida status)
            throws Copa2026Exceptions {

        LocalDateTime dataPartida = validarDados(estadio, selecao1, selecao2, data, horario, fase, status, null);
        Partida novaPartida = new Partida(estadio, selecao1, selecao2, dataPartida, fase, status);
        partidas.add(novaPartida);
        dao.salvar(partidas);

        return novaPartida;
    }

    public void editarPartida(Partida partida, Estadio estadio, Selecao selecao1, Selecao selecao2, String data,
                                    String horario, Fase fase, Partida.StatusPartida status)
            throws Copa2026Exceptions {

        LocalDateTime dataPartida = validarDados(estadio, selecao1, selecao2, data, horario, fase, status, partida.getId());
        partida.setEstadio(estadio);
        partida.setSelecao1(selecao1);
        partida.setSelecao2(selecao2);
        partida.setDataEHora(dataPartida);
        partida.setFase(fase);
        partida.setStatus(status);

        /* Atualizar o JSON */
        dao.salvar(partidas);
    }

    public List<Partida> listar() { return partidas; }

    /*
        * Essa função recebe quatro parâmetros como filtros; ela retorna uma lista de partidas que têm os atributos
        * desejados. Caso não seja necessário usar todos os filtros, passe null para os filtros que não serão usados.
     */
    public List<Partida> filtrarPartidas(Partida.StatusPartida filtroStatus, Selecao filtroSelecao,
                                         Fase filtroFase, String dataFiltro) {

        ArrayList<Partida> partidasEncontradas = new ArrayList<>();
        DateTimeFormatter formatoBusca = DateTimeFormatter.ofPattern("dd/MM/yyyy");

        for (Partida p : partidas) {
            // Verifica se o filtro está vazio ou se encaixa nessa partida
            boolean statusDesejado = (filtroStatus == null) || (filtroStatus.equals(p.getStatus()));
            boolean selecaoDesejada = (filtroSelecao == null) || (p.getSelecao1().equals(filtroSelecao)) || (p.getSelecao2().equals(filtroSelecao));
            boolean faseDesejada = (filtroFase == null) || (p.getFase() == filtroFase);
            boolean dataDesejada = (dataFiltro == null) || (dataFiltro.trim().isEmpty()) ||
                                    (p.getDataEHora().format(formatoBusca).contains(dataFiltro));

            if (statusDesejado && selecaoDesejada && faseDesejada && dataDesejada) {
                partidasEncontradas.add(p);
            }
        }

        return partidasEncontradas;
    }

    public void registrarResultados(Partida partida, String placarSelecao1, String placarSelecao2, String descricao) throws Copa2026Exceptions {
        if (partida == null) { throw new Copa2026Exceptions("É necessário escolher uma partida."); }
        else if (placarSelecao1.isBlank()) { throw new Copa2026Exceptions("É necessário digitar o placar da seleção 1."); }
        else if (placarSelecao2.isBlank()) { throw new Copa2026Exceptions("É necessário digitar o placar da seleção 2."); }

        if (partida.getStatus() == (Partida.StatusPartida.FINALIZADA)) {
            throw new Copa2026Exceptions("A partida selecionada já foi finalizada anteriormente.");
        }

        int sel1, sel2;
        try {
            sel1 = Integer.parseInt(placarSelecao1);
            sel2 = Integer.parseInt(placarSelecao2);
        } catch (NumberFormatException e) {
            throw new Copa2026Exceptions("Digite um número válido para os placares.");
        }

        if (sel1 < 0 || sel2 < 0) {
            throw new Copa2026Exceptions("O placar de uma seleção não pode ser negativo.");
        }

        /* passar mesmo ID gerado para a partida */
        partida.setResultado(new Resultado(partida.getId(), sel1, sel2, descricao));
        partida.setStatus(Partida.StatusPartida.FINALIZADA);

        salvarPartidaEditada(partida);
    }

    public void excluirPartida(Partida partida) {
        partidas.remove(partida); // ele compara os objetos pelo ID, pelo equals() do Partida
        dao.salvar(partidas);
    }

    public void salvarPartidaEditada(Partida partida) {
        for (int i = 0; i < partidas.size(); i++) {
            Partida p = partidas.get(i);

            if (p.equals(partida)) {
                partidas.set(i, partida);
                break;
            }
        }
        dao.salvar(partidas);
    }
}