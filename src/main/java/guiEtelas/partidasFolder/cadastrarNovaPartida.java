/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */
package guiEtelas.partidasFolder;

import controller.estadios.ArbitroController;
import controller.exceptions.Copa2026Exceptions;
import controller.partidas.PartidaController;
import controller.estadios.EstadioController;
import domain.classes.estadios.ConflitoHorarioException;
import domain.classes.estadios.ConflitoPaisException;
import domain.classes.estadios.Estadio;
import domain.classes.partidas.Partida;
import domain.classes.selecoes.Selecao;
import domain.classes.partidas.Partida.StatusPartida;
import domain.classes.partidas.Fase;
import domain.classes.estadios.Arbitro;

import javax.swing.*;
import java.time.format.DateTimeFormatter;
import java.util.List;

/**
 *
 * @author giova
 */
public class cadastrarNovaPartida extends javax.swing.JFrame {
    
    private static final java.util.logging.Logger logger = java.util.logging.Logger.getLogger(cadastrarNovaPartida.class.getName());
    private final PartidaController partidaController = new PartidaController();
    private Partida partidaEditada = null; // Pro botão de salvar saber se foi uma edição ou cadastro

    /* Construtor padrão para cadastrar partidas novas */
    public cadastrarNovaPartida() {
        initComponents();
        carregarMenuEstadios();
        carregarFasesEStatus();
        carregarArbitros();
//        carregarMenuSelecoes();

        /* Esconder a parte dos resultados a princípio, e só mostrar se o status selecionado for de "finalizado" */
        verParteEscondida(false);
    }

    /* Construtor para editar uma partida existente */
    public cadastrarNovaPartida(Partida partida) {
        initComponents();
        carregarMenuEstadios();
        carregarFasesEStatus();
        carregarArbitros();
//        carregarMenuSelecoes();

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
        int[] indices = new int[partida.getArbitros().size()];
        int atual = 0;
        ListModel<Arbitro> modelo = listaArbitros.getModel();

        for (int i = 0; i < modelo.getSize(); i++) {
            Arbitro a = modelo.getElementAt(i);

            if (partida.getArbitros().contains(a)) {
                indices[atual] = i;
                atual++;
            }
        }

        /* Com os índices, preencher o campo dos árbitros */
        listaArbitros.setSelectedIndices(indices);
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
/*
    TODO: colocar as seleções nos menus
    private void carregarMenuSelecoes() {

    }
*/

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel2 = new javax.swing.JPanel();
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
        setMaximumSize(new java.awt.Dimension(400, 300));
        setMinimumSize(new java.awt.Dimension(400, 300));
        setResizable(false);
        getContentPane().setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jPanel2.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabel2.setText("Estádios: ");
        jPanel2.add(jLabel2, new org.netbeans.lib.awtextra.AbsoluteConstraints(6, 9, -1, -1));

        jLabel3.setText("Seleção 1: ");
        jPanel2.add(jLabel3, new org.netbeans.lib.awtextra.AbsoluteConstraints(6, 52, -1, -1));

        jLabel4.setText("Seleção 2: ");
        jPanel2.add(jLabel4, new org.netbeans.lib.awtextra.AbsoluteConstraints(317, 52, -1, -1));

        jLabel1.setText("Data:");
        jPanel2.add(jLabel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(15, 95, -1, -1));

        dataPartida.setText("dd/MM/yyyy");
        jPanel2.add(dataPartida, new org.netbeans.lib.awtextra.AbsoluteConstraints(95, 92, 158, -1));

        horarioPartida.setText("hh:mm");
        jPanel2.add(horarioPartida, new org.netbeans.lib.awtextra.AbsoluteConstraints(400, 92, 180, -1));

        jLabel5.setText("Horário: ");
        jPanel2.add(jLabel5, new org.netbeans.lib.awtextra.AbsoluteConstraints(317, 95, -1, -1));

        jLabel6.setText("Fase: ");
        jPanel2.add(jLabel6, new org.netbeans.lib.awtextra.AbsoluteConstraints(15, 138, -1, -1));

        jPanel2.add(fasePartida, new org.netbeans.lib.awtextra.AbsoluteConstraints(95, 135, 158, -1));

        jLabel7.setText("Status: ");
        jPanel2.add(jLabel7, new org.netbeans.lib.awtextra.AbsoluteConstraints(317, 138, -1, -1));

        statusPartida.addActionListener(this::statusPartidaActionPerformed);
        jPanel2.add(statusPartida, new org.netbeans.lib.awtextra.AbsoluteConstraints(400, 135, 180, -1));

        jPanel2.add(menuEstadios, new org.netbeans.lib.awtextra.AbsoluteConstraints(95, 6, 485, -1));

        jPanel2.add(menuSelecao1, new org.netbeans.lib.awtextra.AbsoluteConstraints(95, 49, 158, -1));

        jPanel2.add(menuSelecao2, new org.netbeans.lib.awtextra.AbsoluteConstraints(400, 49, 180, -1));

        jLabel8.setText("Árbitros:");
        jPanel2.add(jLabel8, new org.netbeans.lib.awtextra.AbsoluteConstraints(6, 219, -1, -1));

        jScrollPane1.setViewportView(listaArbitros);

        jPanel2.add(jScrollPane1, new org.netbeans.lib.awtextra.AbsoluteConstraints(95, 187, 485, 90));

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

        jPanel2.add(painelEscondido, new org.netbeans.lib.awtextra.AbsoluteConstraints(6, 295, 574, -1));

        botaoSalvar.setText("Salvar Partida");
        botaoSalvar.addActionListener(this::botaoSalvarActionPerformed);
        jPanel2.add(botaoSalvar, new org.netbeans.lib.awtextra.AbsoluteConstraints(140, 540, 303, -1));

        getContentPane().add(jPanel2, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 10, 600, 590));

        pack();
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents

    private void botaoSalvarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_botaoSalvarActionPerformed
        // TODO: Tratamento de exceções só pra edição
        try {
            if (partidaEditada == null) { /* Foi feito um cadastro de partida nova */
                partidaController.cadastrarPartida(
                        (Estadio) menuEstadios.getSelectedItem(), (Selecao) menuSelecao1.getSelectedItem(),
                        (Selecao) menuSelecao2.getSelectedItem(), dataPartida.getText(), horarioPartida.getText(),
                        (Fase) fasePartida.getSelectedItem(), (StatusPartida) statusPartida.getSelectedItem(),
                        listaArbitros.getSelectedValuesList());
            } else { /* Foi feita uma edição */
                partidaController.salvarPartidaEditada(partidaEditada);
            }
        } catch (Copa2026Exceptions | ConflitoHorarioException | ConflitoPaisException e) {
            JOptionPane.showMessageDialog(this,
                    e.getMessage(),
                    "Erro no cadastro de partida",
                    JOptionPane.ERROR_MESSAGE
            );
        }
    }//GEN-LAST:event_botaoSalvarActionPerformed

    private void statusPartidaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_statusPartidaActionPerformed
        StatusPartida status = (StatusPartida) statusPartida.getSelectedItem();
        verParteEscondida(status == StatusPartida.FINALIZADA); // true se o selecionado é FINALIZADA, false senão
    }//GEN-LAST:event_statusPartidaActionPerformed

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
    private javax.swing.JPanel jPanel2;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPaneAcontecimentos;
    private javax.swing.JLabel labelPlacar1;
    private javax.swing.JLabel labelPlacar2;
    private javax.swing.JList<Arbitro> listaArbitros;
    private javax.swing.JComboBox<Estadio> menuEstadios;
    private javax.swing.JComboBox<Selecao> menuSelecao1;
    private javax.swing.JComboBox<Selecao> menuSelecao2;
    private javax.swing.JPanel painelEscondido;
    private javax.swing.JTextField placarSelecao1;
    private javax.swing.JTextField placarSelecao2;
    private javax.swing.JComboBox<StatusPartida> statusPartida;
    // End of variables declaration//GEN-END:variables
}
