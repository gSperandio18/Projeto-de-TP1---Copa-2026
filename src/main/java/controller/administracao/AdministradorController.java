package controller.administracao;

import controller.exceptions.Copa2026Exceptions;
import controller.partidas.PartidaController;
import domain.classes.administracao.*;
import domain.classes.administracao.Usuario.Tipo;
import domain.classes.estadios.Arbitro;
import domain.classes.partidas.Partida;
import domain.classes.partidas.Resultado;
import domain.classes.selecoes.Selecao;
import domain.dao.UsuarioDAO;
import domain.dao.UsuarioJsonDAO;
import domain.dao.partidas.PartidaDAO;
import domain.dao.partidas.PartidaJsonDAO;

import java.time.LocalDateTime;
import java.util.*;

public class AdministradorController extends UsuarioController{
    private UsuarioDAO usuarioDAO;
    private List<Usuario> usuarios;

    /*CONSTRUTOR*/
    public AdministradorController() {
        this.usuarioDAO = new UsuarioJsonDAO();
        this.usuarios = usuarioDAO.carregar();
    }

    /*CRIA USUÁRIO PELO ADMIN*/
    public void criaUsuario(String nomeCompleto, String email, String senha/*,String dataNascimento*/, Tipo personagem)throws Copa2026Exceptions{
        String dataNascimento = "VALIDO";
        verificarPermissaoAdmin();
        validarNome(nomeCompleto);
        validarSenha(senha);
        if(email == null) return;

        if(buscarUsuarioPorNome(nomeCompleto) != null){
            throw new Copa2026Exceptions("Nome já cadastrado");
        }

        if(personagem == Tipo.ARBITRO){
            Arbitro arbitro = new Arbitro(nomeCompleto,email,senha,dataNascimento, personagem);
            usuarios.add(arbitro);
            usuarioDAO.salvar(usuarios);
        }else if(personagem == Tipo.ORGANIZADOR){
            Organizador organizador = new Organizador(nomeCompleto, senha, email,personagem);
            usuarios.add(organizador);
            usuarioDAO.salvar(usuarios);
        }else if(personagem == Tipo.ADMINISTRADOR){
            Administrador administrador = new Administrador(nomeCompleto, email, senha, personagem);
            usuarios.add(administrador);
            usuarioDAO.salvar(usuarios);
        }
    }

    /*CRIA USUÁRIO NA TELA SINGUP*/
    public void cadastroPublico(String nomeCompleto, String email, String senha, Tipo personagem) throws Copa2026Exceptions{
        String dataNascimento = "VALIDO";
        validarNome(nomeCompleto);
        validarSenha(senha);

        if (personagem == Tipo.ARBITRO) {
            Arbitro arbitro = new Arbitro(nomeCompleto, email, senha, dataNascimento, personagem);
            usuarios.add(arbitro);
            usuarioDAO.salvar(usuarios);
        } else if (personagem == Tipo.ORGANIZADOR) {
            Organizador organizador = new Organizador(nomeCompleto, email, senha, personagem);
            usuarios.add(organizador);
            usuarioDAO.salvar(usuarios);
        }
    }

    /*EXCLUI USUÁRIO PELO ADMIN*/
    public void excluiUsuario(String nomeCompleto, String email)throws Copa2026Exceptions{
        Usuario usuario = buscarUsuarioPorNome(nomeCompleto);

        if(usuario == null){
            throw new Copa2026Exceptions("Usuário não encontrado!");
        }

        Usuario logado = getUsuarioLogado();
        if(logado.getEmail().equals(email)){
            throw new Copa2026Exceptions("Você não pode excluir a si mesmo");
        }

        usuarios.remove(usuario);
        usuarioDAO.salvar(usuarios);
    }

    /*EDITA USUÁRIO PELO ADMIN*/
    public void editarUsuario(String nomeCompleto, String novoEmail, String novaSenha, Tipo novoPersonagem) throws Copa2026Exceptions{
        verificarPermissaoAdmin();

        Usuario usuario = buscarUsuarioPorNome(nomeCompleto);
        if(usuario == null){
            throw new Copa2026Exceptions("Usuário não encontrado: "+nomeCompleto);
        }
        if(novoEmail != null){
            usuario.setEmail(novoEmail);
        }else if(novaSenha != null){
            usuario.setSenha(novaSenha);
        }else if(usuario.getPersonagem() != null){
            usuario.setPersonagem(novoPersonagem);
        }

        usuarioDAO.salvar(usuarios);
    }

    /*RETORNA UM USUÁRIO POR MEIO DO SEU NOME*/
    public Usuario buscarUsuarioPorNome(String nomeCompleto) throws Copa2026Exceptions{
        verificarPermissaoAdmin();
        for(Usuario u : usuarios){
            if(u.getNomeCompleto().equalsIgnoreCase(nomeCompleto)){
                return u;
            }
        }

        return null;
    }

    /*RETORNA UMA LISTA DE USUÁRIOS COM NOME EM COMUM*/
    public List<Usuario> buscarUsuariosPorNome(String nome) throws Copa2026Exceptions{
        verificarPermissaoAdmin();

        List<Usuario> resultado = new ArrayList<>();
        if(nome == null || nome.trim().isEmpty()){
            return new ArrayList<>(usuarios);
        }

        for (Usuario u : usuarios) {
            if (u.getNomeCompleto().toLowerCase().contains(nome.toLowerCase())) {
                resultado.add(u);
            }
        }
        return resultado;
    }

    /*RETORNAR TODOS OS USUÁRIOS*/
    public List<Usuario> listarTodosUsuarios() throws Copa2026Exceptions {
        verificarPermissaoAdmin();
        return new ArrayList<>(usuarios);
    }

    /*GERA O RELATÓRIO NECESSÁRIO DO ADMIN*/
    public String geraRelatorio() throws Copa2026Exceptions{
        verificarPermissaoAdmin();

        //verificar se há partida para gerar relatório
        StringBuilder relatorio = new StringBuilder();

        PartidaDAO partidaDAO = new PartidaJsonDAO();
        List<Partida> todasPartidas = partidaDAO.carregar();

        if(todasPartidas.isEmpty()){
            throw new Copa2026Exceptions("Não há partidas registradas para gerar relatório");
        }

        //vamos avaliar as finalizadas
        List<Partida> partidasFinalizadas = new ArrayList<>();
        for(Partida p: todasPartidas){
            if(p.getStatus() == Partida.StatusPartida.FINALIZADA && p.getResultado() != null){
                partidasFinalizadas.add(p);
            }
        }

        if(partidasFinalizadas.isEmpty()){
            relatorio.append("Nenhuma partida finalizada no momento!");
            relatorio.append("Total de partidas cadastradas: ").append(todasPartidas.size());
            return relatorio.toString();
        }

        relatorio.append("\n============================================================\n");
        relatorio.append("        RELATÓRIO DE PARTIDAS - COPA 2026\n");
        relatorio.append("============================================================\n");
        relatorio.append("Total de partidas realizadas: ").append(partidasFinalizadas.size()).append("\n");
        relatorio.append("------------------------------------------------------------\n");

        int totalGols = 0;
        for(Partida partida: partidasFinalizadas){
            Resultado resultado = partida.getResultado();

            relatorio.append("\n📅 ").append(formatarData(partida.getDataEHora())).append("\n");
            relatorio.append("🏟️  Estádio: ").append(partida.getEstadio().getNome()).append("\n");
            relatorio.append("🏆 Fase: ").append(partida.getFase().getDescricao()).append("\n");

            relatorio.append("\n⚽ PLACAR FINAL:\n");
            relatorio.append(String.format("   🟡 %-20s %d  X  %d %-20s 🟢\n",
                    partida.getSelecao1().getPaisSelecao(),
                    resultado.getPlacarSelecao1(),
                    resultado.getPlacarSelecao2(),
                    partida.getSelecao2().getPaisSelecao()));

            relatorio.append("\n📊 DESEMPENHO:\n");
            relatorio.append(analisarDesempenhoString(partida.getSelecao1(), resultado.getPlacarSelecao1(), resultado.getPlacarSelecao2()));
            relatorio.append(analisarDesempenhoString(partida.getSelecao2(), resultado.getPlacarSelecao2(), resultado.getPlacarSelecao1()));


            if(resultado.getEventos() != null && !resultado.getEventos().isEmpty()) {
                relatorio.append("\n📝 EVENTOS IMPORTANTES:\n");
                relatorio.append("   ").append(resultado.getEventos()).append("\n");
            }

            relatorio.append("\n----------------------------------------\n");
        }
        return relatorio.toString();
    }

    /*METODO AUXILIAR DO GERA RELATORIO*/
    private String analisarDesempenhoString(Selecao selecao, int golsFeitos, int golsSofridos){
        StringBuilder sb = new StringBuilder();
        sb.append(String.format("   🇧🇷 %s:\n", selecao.getPaisSelecao()));
        sb.append(String.format("      ✅ Gols marcados: %d\n", golsFeitos));
        sb.append(String.format("      ❌ Gols sofridos: %d\n", golsSofridos));

        if(golsFeitos > golsSofridos){
            sb.append("      🏆 Resultado: VITÓRIA\n");
        }else if(golsFeitos < golsSofridos){
            sb.append("      📉 Resultado: DERROTA\n");
        }else{
            sb.append("      🤝 Resultado: EMPATE\n");
        }

        return sb.toString();
    }

    /*METODO AUXILIAR DO GERA RELATORIO*/
    private String formatarData(LocalDateTime data){
        return data.format(java.time.format.DateTimeFormatter.ofPattern("dd/MM/yyyy - HH:mm"));
    }
}
