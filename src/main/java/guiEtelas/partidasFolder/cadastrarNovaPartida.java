/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */
package guiEtelas.partidasFolder;

import controller.estadios.ArbitroController;
import controller.exceptions.Copa2026Exceptions;
import controller.partidas.PartidaController;
import controller.estadios.EstadioController;
import domain.classes.administracao.SessaoUsuario;
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
        labelPlacar1.setVisible(ver);
        labelPlacar2.setVisible(ver);
        placarSelecao1.setVisible(ver);
        placarSelecao2.setVisible(ver);
        acontecimentos.setVisible(ver);
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
        EstadioController estadioController = new EstadioController(SessaoUsuario.getInstancia().getUsuarioAtual());
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
        ArbitroController controller = new ArbitroController(SessaoUsuario.getInstancia().getUsuarioAtual());
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
        jScrollPane2 = new javax.swing.JScrollPane();
        acontecimentos = new javax.swing.JTextArea();
        labelPlacar1 = new javax.swing.JLabel();
        placarSelecao1 = new javax.swing.JTextField();
        labelPlacar2 = new javax.swing.JLabel();
        placarSelecao2 = new javax.swing.JTextField();
        botaoSalvar = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setTitle("Cadastrar nova partida - Copa 2026");
        setMaximumSize(new java.awt.Dimension(400, 300));
        setMinimumSize(new java.awt.Dimension(400, 300));
        setResizable(false);
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

        jScrollPane1.setViewportView(listaArbitros);

        acontecimentos.setColumns(20);
        acontecimentos.setRows(5);
        acontecimentos.setBorder(javax.swing.BorderFactory.createTitledBorder("Acontecimentos da partida"));
        jScrollPane2.setViewportView(acontecimentos);
        acontecimentos.getAccessibleContext().setAccessibleName("Acontecimentos");

        labelPlacar1.setText("<html>Placar da<br>Seleção 1:");

        labelPlacar2.setText("<html>Placar da<br>Seleção 2:");

        botaoSalvar.setText("Salvar Partida");
        botaoSalvar.addActionListener(this::botaoSalvarActionPerformed);

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addComponent(jLabel3)
                        .addGroup(jPanel2Layout.createSequentialGroup()
                            .addGap(9, 9, 9)
                            .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                .addComponent(jLabel6)
                                .addComponent(jLabel1)))
                        .addComponent(jLabel8)
                        .addComponent(labelPlacar2, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(labelPlacar1, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(jLabel2))
                .addGap(12, 12, 12)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.LEADING, jPanel2Layout.createSequentialGroup()
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                            .addGroup(javax.swing.GroupLayout.Alignment.LEADING, jPanel2Layout.createSequentialGroup()
                                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                                    .addComponent(dataPartida, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, 158, Short.MAX_VALUE)
                                    .addComponent(menuSelecao1, javax.swing.GroupLayout.Alignment.LEADING, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                    .addComponent(fasePartida, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                                .addGap(64, 64, 64)
                                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jLabel4, javax.swing.GroupLayout.PREFERRED_SIZE, 77, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(jLabel5)
                                    .addComponent(jLabel7))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                    .addComponent(menuSelecao2, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                    .addComponent(horarioPartida)
                                    .addComponent(statusPartida, javax.swing.GroupLayout.PREFERRED_SIZE, 180, javax.swing.GroupLayout.PREFERRED_SIZE)))
                            .addGroup(jPanel2Layout.createSequentialGroup()
                                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(placarSelecao1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(placarSelecao2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addGap(18, 18, 18)
                                .addComponent(jScrollPane2))
                            .addComponent(menuEstadios, javax.swing.GroupLayout.Alignment.LEADING, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                        .addGap(0, 0, Short.MAX_VALUE)))
                .addContainerGap(10, Short.MAX_VALUE))
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addGap(137, 137, 137)
                .addComponent(botaoSalvar, javax.swing.GroupLayout.PREFERRED_SIZE, 303, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel2)
                    .addComponent(menuEstadios, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel3)
                    .addComponent(jLabel4)
                    .addComponent(menuSelecao1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(menuSelecao2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel1)
                    .addComponent(dataPartida, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel5)
                    .addComponent(horarioPartida, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(jLabel6)
                        .addComponent(fasePartida, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(statusPartida, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(jLabel7)))
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addGap(27, 27, 27)
                        .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 90, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addGap(59, 59, 59)
                        .addComponent(jLabel8)))
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addGap(18, 18, 18)
                        .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 197, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addGap(58, 58, 58)
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(labelPlacar1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(placarSelecao1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(51, 51, 51)
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(labelPlacar2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(placarSelecao2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 37, Short.MAX_VALUE)
                .addComponent(botaoSalvar)
                .addContainerGap())
        );

        getContentPane().add(jPanel2, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 10, 590, 560));

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
        this.revalidate();
        this.repaint();
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
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JLabel labelPlacar1;
    private javax.swing.JLabel labelPlacar2;
    private javax.swing.JList<Arbitro> listaArbitros;
    private javax.swing.JComboBox<Estadio> menuEstadios;
    private javax.swing.JComboBox<Selecao> menuSelecao1;
    private javax.swing.JComboBox<Selecao> menuSelecao2;
    private javax.swing.JTextField placarSelecao1;
    private javax.swing.JTextField placarSelecao2;
    private javax.swing.JComboBox<StatusPartida> statusPartida;
    // End of variables declaration//GEN-END:variables
}
