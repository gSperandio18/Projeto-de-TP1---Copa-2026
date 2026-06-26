/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */
package guiEtelas.partidasFolder;

import controller.estadios.ArbitroController;
import controller.estadios.DesignacaoController;
import controller.exceptions.Copa2026Exceptions;
import controller.partidas.PartidaController;
import controller.estadios.EstadioController;
import controller.selecoes.SelecaoController;
import domain.classes.estadios.ConflitoHorarioException;
import domain.classes.estadios.ConflitoPaisException;
import domain.classes.estadios.Estadio;
import domain.classes.partidas.Partida;
import domain.classes.partidas.Resultado;
import domain.classes.selecoes.Selecao;
import domain.classes.partidas.Partida.StatusPartida;
import domain.classes.partidas.Fase;
import domain.classes.estadios.Arbitro;

import javax.swing.*;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author giova
 */
public class cadastrarNovaPartida extends javax.swing.JFrame {
    
    private static final java.util.logging.Logger logger = java.util.logging.Logger.getLogger(cadastrarNovaPartida.class.getName());
    private final PartidaController partidaController = new PartidaController();
    private final DesignacaoController designacaoController = new DesignacaoController();
    private Partida partidaEditada = null; // Pro botão de salvar saber se foi uma edição ou cadastro

    /* Construtor padrão para cadastrar partidas novas */
    public cadastrarNovaPartida() {
        initComponents();
        carregarMenuEstadios();
        carregarFasesEStatus();
        carregarArbitros();
        carregarMenuSelecoes();

        /* Esconder a parte dos resultados a princípio, e só mostrar se o status selecionado for de "finalizado" */
        verParteEscondida(false);
    }

    /* Construtor para editar uma partida existente */
    public cadastrarNovaPartida(Partida partida) {
        initComponents();
        carregarMenuEstadios();
        carregarFasesEStatus();
        carregarArbitros();
        carregarMenuSelecoes();

        /*
          * Todos os campos são definidos com os atributos da partida
          * A preencherCampos() já lida com a parte dos campos de resultado escondidos
        */
        preencherCampos(partida);
        partidaEditada = partida;
    }

    private void verParteEscondida(boolean ver) {
//        botaoSalvar.getParent().setLayout(null);

        painelEscondido.setVisible(ver);
//        int posX = botaoSalvar.getX();

//        if (ver) {
//            this.setSize(600, 600);
//            botaoSalvar.setLocation(posX, 200);
//        } else {
//            this.setSize(600, 400);
//            botaoSalvar.setLocation(posX, 200);
//        }

        this.repaint();
    }

    private void preencherCampos(Partida partida) {
        menuEstadios.setSelectedItem(partida.getEstadio());
        menuSelecao1.setSelectedItem(partida.getSelecao1());
        menuSelecao2.setSelectedItem(partida.getSelecao2());
        dataPartida.setText(partida.getDataEHora().format(DateTimeFormatter.ofPattern("dd/MM/yyyy")));
        horarioPartida.setText(partida.getDataEHora().format(DateTimeFormatter.ofPattern("HH:mm")));
        fasePartida.setSelectedItem(partida.getFase());
        statusPartida.setSelectedItem(partida.getStatus());

        if (partida.getResultado() != null) {
            verParteEscondida(true);
            placarSelecao1.setText(String.valueOf(partida.getResultado().getPlacarSelecao1()));
            placarSelecao2.setText(String.valueOf(partida.getResultado().getPlacarSelecao2()));
            acontecimentos.setText(partida.getResultado().getEventos());
        } else {
            verParteEscondida(false);
        }

        /* Para selecionar os árbitros na JList, precisamos dos índices dos árbitros da partida */
        List<Arbitro> arbitros = designacaoController.listarArbitros(partida);
        int[] indices = new int[arbitros.size()];
        int atual = 0;
        ListModel<Arbitro> modelo = listaArbitros.getModel();

        for (int i = 0; i < modelo.getSize(); i++) {
            Arbitro a = modelo.getElementAt(i);

            if (arbitros.contains(a)) {
                indices[atual] = i;
                atual++;
            }
        }

        /* Com os índices, preencher o campo dos árbitros */
        listaArbitros.setSelectedIndices(indices);
        menuArbitroPrincipal.setSelectedItem(designacaoController.buscarArbitroPrincipal(partida));
    }
    
    private void carregarMenuEstadios() {
        EstadioController estadioController = new EstadioController();
        List<Estadio> estadios = estadioController.listar();

        for (Estadio e : estadios) {
            menuEstadios.addItem(e); 
        }

        menuEstadios.setSelectedIndex(-1);
    }
    
    private void carregarFasesEStatus() {
        for (Fase f : Fase.values()) {
            fasePartida.addItem(f);
        }

        for (StatusPartida s : StatusPartida.values()) {
            statusPartida.addItem(s);
        }

        fasePartida.setSelectedIndex(-1);
        statusPartida.setSelectedIndex(-1);
    }

    private void carregarArbitros() {
        ArbitroController controller = new ArbitroController();
        DefaultListModel<Arbitro> modelo = new DefaultListModel<>();

        List<Arbitro> arbitros = controller.listar();
        for (Arbitro a : arbitros) { modelo.addElement(a); }

        listaArbitros.setModel(modelo);
    }
    private void carregarMenuSelecoes() {
        SelecaoController controller = new SelecaoController();
        List<Selecao> selecoes = controller.listar();

        for (Selecao s : selecoes) {
            menuSelecao1.addItem(s);
            menuSelecao2.addItem(s);
        }

        menuSelecao1.setSelectedIndex(-1);
        menuSelecao2.setSelectedIndex(-1);
    }

    private void carregarMenuArbitroPrincipal(List<Arbitro> arbitros) {
        for (Arbitro a : arbitros) {
            menuArbitroPrincipal.addItem(a);
        }

        menuArbitroPrincipal.setSelectedIndex(-1);
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        painelFixo = new javax.swing.JPanel();
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        jLabel1 = new javax.swing.JLabel();
        dataPartida = new javax.swing.JTextField();
        horarioPartida = new javax.swing.JTextField();
        jLabel5 = new javax.swing.JLabel();
        jLabel6 = new javax.swing.JLabel();
        fasePartida = new javax.swing.JComboBox<>();
        jLabel7 = new javax.swing.JLabel();
        statusPartida = new javax.swing.JComboBox<>();
        menuEstadios = new javax.swing.JComboBox<>();
        menuSelecao1 = new javax.swing.JComboBox<>();
        menuSelecao2 = new javax.swing.JComboBox<>();
        jLabel8 = new javax.swing.JLabel();
        jScrollPane1 = new javax.swing.JScrollPane();
        listaArbitros = new javax.swing.JList<>();
        jLabel9 = new javax.swing.JLabel();
        menuArbitroPrincipal = new javax.swing.JComboBox<>();
        painelEscondido = new javax.swing.JPanel();
        labelPlacar1 = new javax.swing.JLabel();
        placarSelecao2 = new javax.swing.JTextField();
        jScrollPaneAcontecimentos = new javax.swing.JScrollPane();
        acontecimentos = new javax.swing.JTextArea();
        labelPlacar2 = new javax.swing.JLabel();
        placarSelecao1 = new javax.swing.JTextField();
        botaoSalvar = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setTitle("Cadastrar nova partida - Copa 2026");
        setMinimumSize(new java.awt.Dimension(400, 300));
        setPreferredSize(new java.awt.Dimension(614, 684));
        getContentPane().setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabel2.setText("Estádios: ");

        jLabel3.setText("Seleção 1: ");

        jLabel4.setText("Seleção 2: ");

        jLabel1.setText("Data:");

        dataPartida.setText("dd/MM/yyyy");

        horarioPartida.setText("hh:mm");

        jLabel5.setText("Horário: ");

        jLabel6.setText("Fase: ");

        jLabel7.setText("Status: ");

        statusPartida.addActionListener(this::statusPartidaActionPerformed);

        jLabel8.setText("Árbitros:");

        listaArbitros.addListSelectionListener(this::listaArbitrosValueChanged);
        jScrollPane1.setViewportView(listaArbitros);

        jLabel9.setText("Árbitro Principal:");

        javax.swing.GroupLayout painelFixoLayout = new javax.swing.GroupLayout(painelFixo);
        painelFixo.setLayout(painelFixoLayout);
        painelFixoLayout.setHorizontalGroup(
            painelFixoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(painelFixoLayout.createSequentialGroup()
                .addGroup(painelFixoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(painelFixoLayout.createSequentialGroup()
                        .addGap(6, 6, 6)
                        .addComponent(jLabel2)
                        .addGap(20, 20, 20)
                        .addComponent(menuEstadios, javax.swing.GroupLayout.PREFERRED_SIZE, 485, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(painelFixoLayout.createSequentialGroup()
                        .addGap(6, 6, 6)
                        .addComponent(jLabel3)
                        .addGap(12, 12, 12)
                        .addComponent(menuSelecao1, javax.swing.GroupLayout.PREFERRED_SIZE, 158, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(64, 64, 64)
                        .addComponent(jLabel4)
                        .addGap(6, 6, 6)
                        .addComponent(menuSelecao2, javax.swing.GroupLayout.PREFERRED_SIZE, 180, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(painelFixoLayout.createSequentialGroup()
                        .addGap(15, 15, 15)
                        .addComponent(jLabel1)
                        .addGap(40, 40, 40)
                        .addComponent(dataPartida, javax.swing.GroupLayout.PREFERRED_SIZE, 158, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(64, 64, 64)
                        .addComponent(jLabel5)
                        .addGap(21, 21, 21)
                        .addComponent(horarioPartida, javax.swing.GroupLayout.PREFERRED_SIZE, 180, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(painelFixoLayout.createSequentialGroup()
                        .addGap(15, 15, 15)
                        .addComponent(jLabel6)
                        .addGap(38, 38, 38)
                        .addComponent(fasePartida, javax.swing.GroupLayout.PREFERRED_SIZE, 158, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(64, 64, 64)
                        .addComponent(jLabel7)
                        .addGap(29, 29, 29)
                        .addComponent(statusPartida, javax.swing.GroupLayout.PREFERRED_SIZE, 180, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(painelFixoLayout.createSequentialGroup()
                        .addGap(6, 6, 6)
                        .addComponent(jLabel8)
                        .addGap(28, 28, 28)
                        .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 485, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(painelFixoLayout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(jLabel9)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(menuArbitroPrincipal, javax.swing.GroupLayout.PREFERRED_SIZE, 120, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGap(10, 10, 10))
        );
        painelFixoLayout.setVerticalGroup(
            painelFixoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(painelFixoLayout.createSequentialGroup()
                .addGap(6, 6, 6)
                .addGroup(painelFixoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(painelFixoLayout.createSequentialGroup()
                        .addGap(3, 3, 3)
                        .addComponent(jLabel2))
                    .addComponent(menuEstadios, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(painelFixoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(menuSelecao1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(menuSelecao2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(painelFixoLayout.createSequentialGroup()
                        .addGap(3, 3, 3)
                        .addGroup(painelFixoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel3)
                            .addComponent(jLabel4))))
                .addGap(18, 18, 18)
                .addGroup(painelFixoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(dataPartida, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(horarioPartida, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(painelFixoLayout.createSequentialGroup()
                        .addGap(3, 3, 3)
                        .addGroup(painelFixoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel1)
                            .addComponent(jLabel5))))
                .addGap(18, 18, 18)
                .addGroup(painelFixoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(fasePartida, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(statusPartida, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(painelFixoLayout.createSequentialGroup()
                        .addGap(3, 3, 3)
                        .addGroup(painelFixoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel6)
                            .addComponent(jLabel7))))
                .addGap(27, 27, 27)
                .addGroup(painelFixoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(painelFixoLayout.createSequentialGroup()
                        .addGap(32, 32, 32)
                        .addComponent(jLabel8))
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 90, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 29, Short.MAX_VALUE)
                .addGroup(painelFixoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel9)
                    .addComponent(menuArbitroPrincipal, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(39, 39, 39))
        );

        getContentPane().add(painelFixo, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 10, 590, 370));

        labelPlacar1.setText("<html>Placar da<br>Seleção 1:");

        acontecimentos.setColumns(20);
        acontecimentos.setRows(5);
        acontecimentos.setBorder(javax.swing.BorderFactory.createTitledBorder("Acontecimentos da partida"));
        jScrollPaneAcontecimentos.setViewportView(acontecimentos);
        acontecimentos.getAccessibleContext().setAccessibleName("Acontecimentos");

        labelPlacar2.setText("<html>Placar da<br>Seleção 2:");

        javax.swing.GroupLayout painelEscondidoLayout = new javax.swing.GroupLayout(painelEscondido);
        painelEscondido.setLayout(painelEscondidoLayout);
        painelEscondidoLayout.setHorizontalGroup(
            painelEscondidoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(painelEscondidoLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(painelEscondidoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(painelEscondidoLayout.createSequentialGroup()
                        .addComponent(labelPlacar1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(placarSelecao2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(painelEscondidoLayout.createSequentialGroup()
                        .addComponent(labelPlacar2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(placarSelecao1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGap(12, 12, 12)
                .addComponent(jScrollPaneAcontecimentos, javax.swing.GroupLayout.DEFAULT_SIZE, 401, Short.MAX_VALUE)
                .addContainerGap())
        );
        painelEscondidoLayout.setVerticalGroup(
            painelEscondidoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(painelEscondidoLayout.createSequentialGroup()
                .addGroup(painelEscondidoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(painelEscondidoLayout.createSequentialGroup()
                        .addGap(31, 31, 31)
                        .addGroup(painelEscondidoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(placarSelecao2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(labelPlacar1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(57, 57, 57)
                        .addGroup(painelEscondidoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(labelPlacar2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(placarSelecao1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addGroup(painelEscondidoLayout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(jScrollPaneAcontecimentos, javax.swing.GroupLayout.PREFERRED_SIZE, 197, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        getContentPane().add(painelEscondido, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 380, -1, -1));

        botaoSalvar.setText("Salvar Partida");
        botaoSalvar.addActionListener(this::botaoSalvarActionPerformed);
        getContentPane().add(botaoSalvar, new org.netbeans.lib.awtextra.AbsoluteConstraints(150, 610, 303, -1));

        pack();
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents

    private void botaoSalvarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_botaoSalvarActionPerformed
        try {
            Partida partidaAtual;
            if (partidaEditada == null) { /* Foi feito um cadastro de partida nova */
                partidaAtual = partidaController.cadastrarPartida(
                        (Estadio) menuEstadios.getSelectedItem(), (Selecao) menuSelecao1.getSelectedItem(),
                        (Selecao) menuSelecao2.getSelectedItem(), dataPartida.getText(), horarioPartida.getText(),
                        (Fase) fasePartida.getSelectedItem(), (StatusPartida) statusPartida.getSelectedItem()
                );

                designacaoController.designarLista(
                        listaArbitros.getSelectedValuesList(),
                        partidaAtual,
                        (Arbitro) menuArbitroPrincipal.getSelectedItem()
                );
            } else { /* Foi feita uma edição */
                partidaController.editarPartida(
                        partidaEditada,
                        (Estadio) menuEstadios.getSelectedItem(), (Selecao) menuSelecao1.getSelectedItem(),
                        (Selecao) menuSelecao2.getSelectedItem(), dataPartida.getText(), horarioPartida.getText(),
                        (Fase) fasePartida.getSelectedItem(), (StatusPartida) statusPartida.getSelectedItem()
                );

                /* Tirar todos os árbitros e recolocar os selecionados */
                designacaoController.limparArbitros(partidaEditada);

                designacaoController.designarLista(
                        listaArbitros.getSelectedValuesList(),
                        partidaEditada,
                        (Arbitro) menuArbitroPrincipal.getSelectedItem()
                );
                partidaAtual = partidaEditada;
            }

            /* Incluir os placares */
            if (statusPartida.getSelectedItem() == StatusPartida.FINALIZADA) {
                int p1, p2;
                try {
                    p1 = Integer.parseInt(placarSelecao1.getText());
                    p2 = Integer.parseInt(placarSelecao2.getText());
                } catch (NumberFormatException ex) {
                    JOptionPane.showMessageDialog(this, "O placar deve conter apenas números válidos!", "Erro no placar", JOptionPane.ERROR_MESSAGE);
                    return;
                }
                partidaAtual.setResultado(new Resultado(partidaAtual.getId(), p1, p2, acontecimentos.getText()));
            } else {
                partidaAtual.setResultado(null); // Apagar o que tiver lá
            }

            partidaController.salvarPartidaEditada(partidaAtual);
            JOptionPane.showMessageDialog(this, "Partida salva com sucesso!");
            this.dispose();

        } catch (Copa2026Exceptions | ConflitoHorarioException | ConflitoPaisException e) {
            String titulo = (partidaEditada == null) ? "Erro no cadastro de partida" : "Erro na edição de partida";
            JOptionPane.showMessageDialog(this,
                    e.getMessage(),
                    titulo,
                    JOptionPane.ERROR_MESSAGE
            );
        }
    }//GEN-LAST:event_botaoSalvarActionPerformed

    private void statusPartidaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_statusPartidaActionPerformed
        StatusPartida status = (StatusPartida) statusPartida.getSelectedItem();
        verParteEscondida(status == StatusPartida.FINALIZADA); // true se o selecionado é FINALIZADA, false senão
    }//GEN-LAST:event_statusPartidaActionPerformed

    private void listaArbitrosValueChanged(javax.swing.event.ListSelectionEvent evt) {//GEN-FIRST:event_listaArbitrosValueChanged
        if (!evt.getValueIsAdjusting()) {
            Arbitro principalAtual = (Arbitro) menuArbitroPrincipal.getSelectedItem();

            List<Arbitro> selecionados = listaArbitros.getSelectedValuesList();
            menuArbitroPrincipal.removeAllItems();
            carregarMenuArbitroPrincipal(selecionados);

            /* Se o que tava selecionado antes ainda tiver, ele continua; senão, nada fica marcado */
            if (principalAtual != null && selecionados.contains(principalAtual)) {
                menuArbitroPrincipal.setSelectedItem(principalAtual);
            } else {
                menuArbitroPrincipal.setSelectedIndex(-1);
            }
        }
    }//GEN-LAST:event_listaArbitrosValueChanged

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ReflectiveOperationException | javax.swing.UnsupportedLookAndFeelException ex) {
            logger.log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(() -> new cadastrarNovaPartida().setVisible(true));
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JTextArea acontecimentos;
    private javax.swing.JButton botaoSalvar;
    private javax.swing.JTextField dataPartida;
    private javax.swing.JComboBox<Fase> fasePartida;
    private javax.swing.JTextField horarioPartida;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPaneAcontecimentos;
    private javax.swing.JLabel labelPlacar1;
    private javax.swing.JLabel labelPlacar2;
    private javax.swing.JList<Arbitro> listaArbitros;
    private javax.swing.JComboBox<Arbitro> menuArbitroPrincipal;
    private javax.swing.JComboBox<Estadio> menuEstadios;
    private javax.swing.JComboBox<Selecao> menuSelecao1;
    private javax.swing.JComboBox<Selecao> menuSelecao2;
    private javax.swing.JPanel painelEscondido;
    private javax.swing.JPanel painelFixo;
    private javax.swing.JTextField placarSelecao1;
    private javax.swing.JTextField placarSelecao2;
    private javax.swing.JComboBox<StatusPartida> statusPartida;
    // End of variables declaration//GEN-END:variables
}
