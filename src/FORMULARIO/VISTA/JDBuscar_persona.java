/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package FORMULARIO.VISTA;

import BASEDATO.EvenConexion;
import BASEDATO.LOCAL.ConnPostgres;
import Evento.JTextField.EvenJTextField;
import Evento.Jframe.EvenJFRAME;
import Evento.Jtable.EvenJtable;
import FORMULARIO.BO.BO_persona;
import FORMULARIO.DAO.DAO_persona;
import FORMULARIO.DAO.DAO_plan_seguro;
import FORMULARIO.DAO.DAO_tipo_seguro;
import FORMULARIO.ENTIDAD.persona;
import FORMULARIO.ENTIDAD.plan_seguro;
import FORMULARIO.ENTIDAD.tipo_seguro;
import java.awt.event.KeyEvent;
import java.sql.Connection;

/**
 *
 * @author Digno
 */
public class JDBuscar_persona extends javax.swing.JDialog {

    EvenConexion eveconn = new EvenConexion();
    EvenJFRAME evetbl = new EvenJFRAME();
    EvenJtable eveJtab = new EvenJtable();
    EvenJTextField evejtf = new EvenJTextField();
    Connection conn = ConnPostgres.getConnPosgres();
    private persona pers = new persona();
    private BO_persona persBO = new BO_persona();
    private DAO_persona persdao = new DAO_persona();
    private tipo_seguro tseg = new tipo_seguro();
    private DAO_tipo_seguro tsegdao = new DAO_tipo_seguro();
    private plan_seguro pseg = new plan_seguro();
    private DAO_plan_seguro psegdao = new DAO_plan_seguro();

    private void abrir_formulario() {
        this.setTitle("BUSCAR PERSONA");
        buscar_previa();
    }

    private void buscar_previa() {
        if (pers.getSta_tipo_persona().equals(pers.getSta_tipo_pers_paciente())) {
            if (FrmOrden_lab_pedido.txtpaciente_cedula.getText().trim().length() > 0) {
                txtbuscar_cedula.setText(FrmOrden_lab_pedido.txtpaciente_cedula.getText());
                txtbuscar_cedula.requestFocus();
            }
            if (FrmOrden_lab_pedido.txtpaciente_nombre.getText().trim().length() > 0) {
                txtbuscar_nombre.setText(FrmOrden_lab_pedido.txtpaciente_nombre.getText());
                txtbuscar_nombre.requestFocus();
            }
        }
        if (pers.getSta_tipo_persona().equals(pers.getSta_tipo_pers_medico())) {
            if (FrmOrden_lab_pedido.txtbuscar_medico.getText().trim().length() > 0) {
                txtbuscar_nombre.setText(FrmOrden_lab_pedido.txtbuscar_medico.getText());
                txtbuscar_nombre.requestFocus();
            }
        }
    }

    private void buscar_persona(int tipo) {
        String campo = "";
        String buscar = "";
        if (tipo == 1) {
            if (txtbuscar_nombre.getText().trim().length() >= 3) {
                buscar = txtbuscar_nombre.getText();
                campo = "concat(p.nombre,' ',p.apellido)";
                persdao.actualizar_tabla_buscar_persona(conn, tblbuscar_persona, campo, buscar, pers.getSta_tipo_persona());
            }
        }
        if (tipo == 2) {
            if (txtbuscar_cedula.getText().trim().length() >= 3) {
                buscar = txtbuscar_cedula.getText();
                campo = "p.cedula";
                persdao.actualizar_tabla_buscar_persona(conn, tblbuscar_persona, campo, buscar, pers.getSta_tipo_persona());
            }
        }
    }

    private void seleccionar_tabla_persona() {
        int idpersona = eveJtab.getInt_select_id(tblbuscar_persona);
        persdao.cargar_persona(conn, pers, idpersona);
        if (pers.getSta_tipo_persona().equals(pers.getSta_tipo_pers_paciente())) {
            FrmOrden_lab_pedido.txtpersona_id.setText(String.valueOf(pers.getC1idpersona()));
            FrmOrden_lab_pedido.txtpaciente_nombre.setText(pers.getC2nombre() + " " + pers.getC3apellido());
            FrmOrden_lab_pedido.txtpaciente_cedula.setText(pers.getC4cedula());
            tsegdao.cargar_tipo_seguro(conn, tseg, pers.getC12fk_idtipo_seguro());
            FrmOrden_lab_pedido.txtpaciente_tipo_seguro.setText(tseg.getC2nombre());
            psegdao.cargar_plan_seguro(conn, pseg, pers.getC13fk_idplan_seguro());
            FrmOrden_lab_pedido.txtpaciente_plan_seguro.setText(pseg.getC2nombre());
            FrmOrden_lab_pedido.txtbuscar_medico.requestFocus();
        }
        if (pers.getSta_tipo_persona().equals(pers.getSta_tipo_pers_medico())) {
            FrmOrden_lab_pedido.txtmedico_idpersona.setText(String.valueOf(pers.getC1idpersona()));
            FrmOrden_lab_pedido.txtbuscar_medico.setText(pers.getC2nombre() + " " + pers.getC3apellido());
            FrmOrden_lab_pedido.txtbuscar_registro_medico.setText(String.valueOf(pers.getC10registro()));
            FrmOrden_lab_pedido.txtvisacion.requestFocus();
        }
    }

    public JDBuscar_persona(java.awt.Frame parent, boolean modal) {
        super(parent, modal);
        initComponents();
        abrir_formulario();
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        txtbuscar_nombre = new javax.swing.JTextField();
        txtbuscar_cedula = new javax.swing.JTextField();
        jLabel2 = new javax.swing.JLabel();
        jPanel2 = new javax.swing.JPanel();
        jRpaciente = new javax.swing.JRadioButton();
        jRmedico = new javax.swing.JRadioButton();
        jPanel3 = new javax.swing.JPanel();
        jScrollPane1 = new javax.swing.JScrollPane();
        tblbuscar_persona = new javax.swing.JTable();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);

        jPanel1.setBorder(javax.swing.BorderFactory.createTitledBorder("BUSCAR PERSONA"));

        jLabel1.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        jLabel1.setText("NOMBRE:");

        txtbuscar_nombre.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        txtbuscar_nombre.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                txtbuscar_nombreKeyPressed(evt);
            }
            public void keyReleased(java.awt.event.KeyEvent evt) {
                txtbuscar_nombreKeyReleased(evt);
            }
        });

        txtbuscar_cedula.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        txtbuscar_cedula.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                txtbuscar_cedulaKeyPressed(evt);
            }
            public void keyReleased(java.awt.event.KeyEvent evt) {
                txtbuscar_cedulaKeyReleased(evt);
            }
        });

        jLabel2.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        jLabel2.setText("CEDULA:");

        jPanel2.setBorder(javax.swing.BorderFactory.createTitledBorder("TIPO PERSONA"));

        jRpaciente.setSelected(true);
        jRpaciente.setText("PACIENTE");

        jRmedico.setText("MEDICO");

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jRpaciente)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jRmedico))
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                .addComponent(jRpaciente)
                .addComponent(jRmedico, javax.swing.GroupLayout.PREFERRED_SIZE, 23, javax.swing.GroupLayout.PREFERRED_SIZE))
        );

        jPanel3.setBackground(new java.awt.Color(153, 204, 255));
        jPanel3.setBorder(javax.swing.BorderFactory.createTitledBorder("TABLA PERSONA"));

        tblbuscar_persona.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null}
            },
            new String [] {
                "Title 1", "Title 2", "Title 3", "Title 4"
            }
        ));
        tblbuscar_persona.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseReleased(java.awt.event.MouseEvent evt) {
                tblbuscar_personaMouseReleased(evt);
            }
        });
        tblbuscar_persona.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                tblbuscar_personaKeyPressed(evt);
            }
        });
        jScrollPane1.setViewportView(tblbuscar_persona);

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jScrollPane1)
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 468, Short.MAX_VALUE)
        );

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel1)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(txtbuscar_nombre, javax.swing.GroupLayout.PREFERRED_SIZE, 430, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel2)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(txtbuscar_cedula, javax.swing.GroupLayout.PREFERRED_SIZE, 186, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(122, Short.MAX_VALUE))
            .addComponent(jPanel3, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.CENTER)
                    .addComponent(jLabel1)
                    .addComponent(txtbuscar_nombre, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel2)
                    .addComponent(txtbuscar_cedula, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanel3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        pack();
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents

    private void txtbuscar_nombreKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtbuscar_nombreKeyReleased
        // TODO add your handling code here:
        buscar_persona(1);
    }//GEN-LAST:event_txtbuscar_nombreKeyReleased

    private void txtbuscar_cedulaKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtbuscar_cedulaKeyReleased
        // TODO add your handling code here:
        buscar_persona(2);
    }//GEN-LAST:event_txtbuscar_cedulaKeyReleased

    private void txtbuscar_nombreKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtbuscar_nombreKeyPressed
        // TODO add your handling code here:
        eveJtab.seleccionar_tabla_flecha_abajo(evt, tblbuscar_persona);
    }//GEN-LAST:event_txtbuscar_nombreKeyPressed

    private void txtbuscar_cedulaKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtbuscar_cedulaKeyPressed
        // TODO add your handling code here:
        eveJtab.seleccionar_tabla_flecha_abajo(evt, tblbuscar_persona);
    }//GEN-LAST:event_txtbuscar_cedulaKeyPressed

    private void tblbuscar_personaKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_tblbuscar_personaKeyPressed
        // TODO add your handling code here:
        seleccionar_tabla_persona();
        if (evt.getKeyCode() == KeyEvent.VK_ENTER) {
            this.dispose();
        }
    }//GEN-LAST:event_tblbuscar_personaKeyPressed

    private void tblbuscar_personaMouseReleased(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tblbuscar_personaMouseReleased
        // TODO add your handling code here:
        seleccionar_tabla_persona();
    }//GEN-LAST:event_tblbuscar_personaMouseReleased

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
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(JDBuscar_persona.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(JDBuscar_persona.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(JDBuscar_persona.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(JDBuscar_persona.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the dialog */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                JDBuscar_persona dialog = new JDBuscar_persona(new javax.swing.JFrame(), true);
                dialog.addWindowListener(new java.awt.event.WindowAdapter() {
                    @Override
                    public void windowClosing(java.awt.event.WindowEvent e) {
                        System.exit(0);
                    }
                });
                dialog.setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JRadioButton jRmedico;
    private javax.swing.JRadioButton jRpaciente;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTable tblbuscar_persona;
    public static javax.swing.JTextField txtbuscar_cedula;
    public static javax.swing.JTextField txtbuscar_nombre;
    // End of variables declaration//GEN-END:variables
}
