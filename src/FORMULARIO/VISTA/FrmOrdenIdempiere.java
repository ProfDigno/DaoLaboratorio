/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package FORMULARIO.VISTA;

import BASEDATO.EvenConexion;
import BASEDATO.LOCAL.ConnPostgres;
import BASEDATO.SERVER.ConnPostgresServer;
import Evento.Color.cla_color_pelete;
import Evento.JTextField.EvenJTextField;
import Evento.Jframe.EvenJFRAME;
import Evento.Jtable.EvenJtable;
import Evento.Mensaje.EvenMensajeJoptionpane;
import FORMULARIO.BO.BO_persona;
import FORMULARIO.DAO.DAO_persona;
import FORMULARIO.ENTIDAD.persona;
import java.awt.Color;
import java.sql.Connection;
import java.sql.ResultSet;
import javax.swing.JOptionPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author Digno
 */
public class FrmOrdenIdempiere extends javax.swing.JInternalFrame {

    EvenJFRAME evetbl = new EvenJFRAME();
    EvenJtable eveJtab = new EvenJtable();

    EvenJTextField evejtf = new EvenJTextField();
    Connection connSer = ConnPostgresServer.getConnPosgresServer();
    Connection conn = ConnPostgres.getConnPosgres();
    cla_color_pelete clacolor = new cla_color_pelete();
    EvenConexion eveconn = new EvenConexion();
    EvenJtable evejt = new EvenJtable();
    EvenMensajeJoptionpane evemen = new EvenMensajeJoptionpane();
    DefaultTableModel model_item_orden_lab = new DefaultTableModel();
    private persona pers = new persona();
    private BO_persona persBO = new BO_persona();
    private DAO_persona persdao = new DAO_persona();

    private void abrir_formulario() {
        this.setTitle("ORDEN IDEMPIERE");
        evetbl.centrar_formulario_internalframa(this);
        actualizar_tabla_orden_idempiere(connSer, tblorden);
        crear_orden_line_idempiere();
    }

    private void actualizar_tabla_orden_idempiere(Connection conn, JTable tbltabla) {
        String sql = "select co.c_order_id as idord,co.documentno as nro_docu,to_char(co.created,'yyyy-MM-dd HH24:MI') as fec_creado,\n"
                + "case when co.docstatus='CO' then 'Completado'\n"
                + "when co.docstatus ='VO' then 'Anulado'\n"
                + "when co.docstatus ='DR' then 'Borrador'\n"
                + "when co.docstatus ='IN' then 'En_Proceso'\n"
                + "else ('otro: '||co.docstatus) end as estado,\n"
                + "cdt.\"name\" as tipo_doc,pri.\"name\" as tipo_seguro,\n"
                + "cb.value as cod_paciente,cb.\"name\" as paciente, cb.taxid as ruc_ci\n"
                + "from c_order co,c_doctype cdt,m_pricelist pri,\n"
                + "c_bpartner cb\n"
                + "where  co.c_doctype_id=cdt.c_doctype_id \n"
                + "and co.m_pricelist_id=pri.m_pricelist_id \n"
                + "and co.c_bpartner_id=cb.c_bpartner_id \n"
                + "and (co.c_doctype_id=1000181 "
                + "or co.c_doctype_id=1000180 "
                + "or co.c_doctype_id=1000189 "
                + "or co.c_doctype_id=1058628  )\n"
                + "order by co.created desc";
        eveconn.Select_cargar_jtable(conn, sql, tbltabla);
        ancho_tabla_orden_idempiere(tbltabla);
    }

    private void ancho_tabla_orden_idempiere(JTable tbltabla) {
        int Ancho[] = {5, 8, 12, 8, 12, 8, 7, 30, 10};
        evejt.setAnchoColumnaJtable(tbltabla, Ancho);
    }

    private void seleccionar_order() {
        int c_order_id = eveJtab.getInt_select_id(tblorden);
        eveJtab.limpiar_tabla_datos(model_item_orden_lab);
        String cedula = eveJtab.getString_select(tblorden, 8);
        String nro_documento = eveJtab.getString_select(tblorden, 1);
        buscar_paciente(cedula);
        verificar_orden_existente(nro_documento);
        actualizar_tabla_orden_line_idempiere(connSer, tblitem_estudios, c_order_id);
    }

    private void seleccionar_item_orden() {
        String nombre = eveJtab.getString_select(tblitem_estudios, 1);
        String sku = eveJtab.getString_select(tblitem_estudios, 2);
        txtsku.setText(sku);
        buscar_estudio_por_sku(sku);
    }

    private void boton_cargar() {
        try {
            FrmLab_estudio.txtsku.setText(txtsku.getText());
            FrmLab_estudio.txtnombre_completo.setText(txtestudio_local.getText());
            FrmLab_estudio.txtnombre_corto.setText(txtestudio_local.getText());
        } catch (Exception e) {
            JOptionPane.showMessageDialog(this, "EL FORMULAIO DE ESTUDIO DEBE ABRIRCE \n" + e);
        }

    }
    private void boton_pasar_nro_orden(){
        try {
            String nro_documento = eveJtab.getString_select(tblorden, 1);
            FrmOrden_lab_pedido.txtnro_documento.setText(nro_documento);
        } catch (Exception e) {
            JOptionPane.showMessageDialog(this, "EL FORMULAIO DE PEDIDO DEBE ABRIRCE \n" + e);
        }
    }
    private void crear_orden_line_idempiere() {
        String dato[] = {"m_product_id", "servicio", "sku", "encontrado"};
        evejt.crear_tabla_datos(tblitem_estudios, model_item_orden_lab, dato);
        eveJtab.limpiar_tabla_datos(model_item_orden_lab);
    }

    private void actualizar_tabla_orden_line_idempiere(Connection conn, JTable tbltabla, int c_order_id) {
        String titulo = "actualizar_tabla_orden_line_idempiere";
        String sql = "select col.m_product_id,mp.name as servicio,mp.sku as sku\n"
                + "from c_orderline col,m_product mp \n"
                + "where col.m_product_id=mp.m_product_id \n"
                + "and col.c_order_id=" + c_order_id
                + " order by col.c_orderline_id asc;";
//        eveconn.Select_cargar_jtable(conn, sql, tbltabla);
        try {
            ResultSet rs = eveconn.getResulsetSQL(conn, sql, titulo);
            while (rs.next()) {
                String m_product_id = rs.getString("m_product_id");
                String servicio = rs.getString("servicio");
                String sku = rs.getString("sku");
                Object dato[] = {m_product_id, servicio, sku, getString_comparar_estudio_por_sku(sku)};
                model_item_orden_lab = (DefaultTableModel) tbltabla.getModel();
                model_item_orden_lab.addRow(dato);
            }
        } catch (Exception e) {
            evemen.mensaje_error(e, sql, titulo);
        }
        ancho_tabla_orden_line_idempiere(tbltabla);
    }

    private void ancho_tabla_orden_line_idempiere(JTable tbltabla) {
        int Ancho[] = {15, 55, 15, 15};
        evejt.setAnchoColumnaJtable(tbltabla, Ancho);
    }

    private void buscar_estudio_por_sku(String sku) {
        String titulo = "buscar_estudio_por_sku";
        String sql = "select le.idlab_estudio as idle,le.fk_idlab_grupo_estudio as idg,\n"
                + "le.nombre_completo as ncompleto,lun.nombre as unid\n"
                + "from public.lab_estudio le,public.lab_unidad lun\n"
                + "where le.fk_idlab_unidad=lun.idlab_unidad\n"
                + "and le.sku=" + sku;
        try {
            ResultSet rs = eveconn.getResulsetSQL(conn, sql, titulo);
            if (rs.next()) {
                String idlab_estudio = rs.getString("idle");
                String idg = rs.getString("idg");
                String nombre_completo = rs.getString("ncompleto");
                String unidad = rs.getString("unid");
                txtestudio_local.setText(nombre_completo);
                txtestudio_local.setBackground(Color.white);
                txtunidad_local.setText(unidad);
            } else {
                txtestudio_local.setText("NO ENCONTRADO");
                txtestudio_local.setBackground(Color.yellow);
                txtunidad_local.setText(null);
            }
        } catch (Exception e) {
            evemen.mensaje_error(e, sql, titulo);
        }
    }

    private String getString_comparar_estudio_por_sku(String sku) {
        String encontrado = "NO";
        String titulo = "getString_comparar_estudio_por_sku";
        String sql = "select le.idlab_estudio as idle\n"
                + "from public.lab_estudio le\n"
                + "where le.sku=" + sku;
        try {
            ResultSet rs = eveconn.getResulsetSQL(conn, sql, titulo);
            if (rs.next()) {
                encontrado = "SI";
            } else {
                encontrado = "NO";
            }
        } catch (Exception e) {
            evemen.mensaje_error(e, sql, titulo);
        }
        return encontrado;
    }
    private void verificar_orden_existente(String nro_documento) {
        String encontrado = "NO";
        String titulo = "getString_comparar_estudio_por_sku";
        String sql = "select * from public.orden_lab where nro_documento=" + nro_documento;
        try {
            ResultSet rs = eveconn.getResulsetSQL(conn, sql, titulo);
            if (rs.next()) {
                encontrado = " (YA FUE CREADO)";
                txtmensaje_orden.setBackground(Color.orange);
            } else {
                encontrado = " (NUEVO)";
                txtmensaje_orden.setBackground(Color.green);
            }
            txtmensaje_orden.setText("ORDEN:"+nro_documento+encontrado);
        } catch (Exception e) {
            evemen.mensaje_error(e, sql, titulo);
        }
    }
    private void cargar_paciente_uno(int idpersona) {
        persdao.cargar_persona(conn, pers, idpersona);
        txtcliente_local_nom.setText(pers.getC2nombre() + " " + pers.getC3apellido());
        txtcliente_local_ci.setText(pers.getC4cedula());
    }

    private void buscar_paciente(String cedula) {
        String campo = "";
        String buscar = "";
        buscar = cedula;
        campo = "p.cedula";
        pers.setSta_tipo_persona(pers.getSta_tipo_pers_paciente());
        int idpersona = persdao.getInt_buscar_idpersona(conn, campo, buscar, pers.getSta_tipo_persona());
        System.out.println("IDPERSONA:" + idpersona);
        if (idpersona > 0) {
            cargar_paciente_uno(idpersona);
            txtcliente_local_nom.setBackground(Color.white);
        } else {
            txtcliente_local_nom.setText("NO ENCONTRADO");
            txtcliente_local_nom.setBackground(Color.yellow);
            txtcliente_local_ci.setText(null);
        }
    }

    public FrmOrdenIdempiere() {
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
        jPanel2 = new javax.swing.JPanel();
        jScrollPane1 = new javax.swing.JScrollPane();
        tblorden = new javax.swing.JTable();
        jPanel3 = new javax.swing.JPanel();
        jScrollPane2 = new javax.swing.JScrollPane();
        tblitem_estudios = new javax.swing.JTable();
        jPanel4 = new javax.swing.JPanel();
        jLabel4 = new javax.swing.JLabel();
        txtunidad_local = new javax.swing.JTextField();
        jLabel3 = new javax.swing.JLabel();
        txtestudio_local = new javax.swing.JTextField();
        txtsku = new javax.swing.JTextField();
        jLabel2 = new javax.swing.JLabel();
        btnpasar = new javax.swing.JButton();
        jPanel5 = new javax.swing.JPanel();
        jLabel5 = new javax.swing.JLabel();
        txtcliente_local_ci = new javax.swing.JTextField();
        jLabel6 = new javax.swing.JLabel();
        txtcliente_local_nom = new javax.swing.JTextField();
        txtmensaje_orden = new javax.swing.JTextField();
        btnpasar_nro_orden = new javax.swing.JButton();

        setBackground(new java.awt.Color(153, 204, 255));
        setClosable(true);
        setIconifiable(true);
        setMaximizable(true);
        addInternalFrameListener(new javax.swing.event.InternalFrameListener() {
            public void internalFrameActivated(javax.swing.event.InternalFrameEvent evt) {
            }
            public void internalFrameClosed(javax.swing.event.InternalFrameEvent evt) {
            }
            public void internalFrameClosing(javax.swing.event.InternalFrameEvent evt) {
            }
            public void internalFrameDeactivated(javax.swing.event.InternalFrameEvent evt) {
            }
            public void internalFrameDeiconified(javax.swing.event.InternalFrameEvent evt) {
            }
            public void internalFrameIconified(javax.swing.event.InternalFrameEvent evt) {
            }
            public void internalFrameOpened(javax.swing.event.InternalFrameEvent evt) {
                formInternalFrameOpened(evt);
            }
        });

        jPanel2.setBackground(new java.awt.Color(102, 153, 255));
        jPanel2.setBorder(javax.swing.BorderFactory.createTitledBorder("ORDEN IDEMPIERE"));

        tblorden.setModel(new javax.swing.table.DefaultTableModel(
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
        tblorden.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseReleased(java.awt.event.MouseEvent evt) {
                tblordenMouseReleased(evt);
            }
        });
        tblorden.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                tblordenKeyReleased(evt);
            }
        });
        jScrollPane1.setViewportView(tblorden);

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jScrollPane1)
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 221, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, Short.MAX_VALUE))
        );

        jPanel3.setBackground(new java.awt.Color(153, 204, 255));
        jPanel3.setBorder(javax.swing.BorderFactory.createTitledBorder("ITEM ESTUDIOS"));

        tblitem_estudios.setModel(new javax.swing.table.DefaultTableModel(
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
        tblitem_estudios.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseReleased(java.awt.event.MouseEvent evt) {
                tblitem_estudiosMouseReleased(evt);
            }
        });
        tblitem_estudios.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                tblitem_estudiosKeyReleased(evt);
            }
        });
        jScrollPane2.setViewportView(tblitem_estudios);

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jScrollPane2, javax.swing.GroupLayout.DEFAULT_SIZE, 652, Short.MAX_VALUE)
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 286, javax.swing.GroupLayout.PREFERRED_SIZE)
        );

        jPanel4.setBorder(javax.swing.BorderFactory.createTitledBorder("LOCAL POR SKU"));

        jLabel4.setText("UNIDAD LOCAL:");

        jLabel3.setText("ESTUDIO LOCAL:");

        jLabel2.setText("SKU:");

        btnpasar.setText("PASAR");
        btnpasar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnpasarActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel4Layout = new javax.swing.GroupLayout(jPanel4);
        jPanel4.setLayout(jPanel4Layout);
        jPanel4Layout.setHorizontalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel3, javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jLabel4, javax.swing.GroupLayout.Alignment.TRAILING))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(txtestudio_local, javax.swing.GroupLayout.PREFERRED_SIZE, 365, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(jPanel4Layout.createSequentialGroup()
                        .addComponent(txtunidad_local, javax.swing.GroupLayout.PREFERRED_SIZE, 98, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jLabel2)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(txtsku, javax.swing.GroupLayout.PREFERRED_SIZE, 103, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(btnpasar)))
                .addContainerGap(21, Short.MAX_VALUE))
        );
        jPanel4Layout.setVerticalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(txtestudio_local, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel3))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel4)
                    .addComponent(txtunidad_local, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel2)
                    .addComponent(txtsku, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnpasar))
                .addGap(0, 10, Short.MAX_VALUE))
        );

        jPanel5.setBorder(javax.swing.BorderFactory.createTitledBorder("CLIENTE LOCAL"));

        jLabel5.setText("CEDULA:");

        jLabel6.setText("NOMBRE:");

        javax.swing.GroupLayout jPanel5Layout = new javax.swing.GroupLayout(jPanel5);
        jPanel5.setLayout(jPanel5Layout);
        jPanel5Layout.setHorizontalGroup(
            jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel5Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel5, javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jLabel6, javax.swing.GroupLayout.Alignment.TRAILING))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(txtcliente_local_ci, javax.swing.GroupLayout.PREFERRED_SIZE, 117, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txtcliente_local_nom, javax.swing.GroupLayout.PREFERRED_SIZE, 365, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel5Layout.setVerticalGroup(
            jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel5Layout.createSequentialGroup()
                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(txtcliente_local_nom, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel6))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel5)
                    .addComponent(txtcliente_local_ci, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(0, 9, Short.MAX_VALUE))
        );

        txtmensaje_orden.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        txtmensaje_orden.setText("jTextField1");

        btnpasar_nro_orden.setText("PASAR NRO A ORDEN");
        btnpasar_nro_orden.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnpasar_nro_ordenActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addComponent(jPanel3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jPanel4, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jPanel5, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(txtmensaje_orden)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(btnpasar_nro_orden, javax.swing.GroupLayout.PREFERRED_SIZE, 199, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(0, 0, Short.MAX_VALUE)))
                .addContainerGap())
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(txtmensaje_orden, javax.swing.GroupLayout.PREFERRED_SIZE, 29, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(btnpasar_nro_orden)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jPanel5, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jPanel4, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(jPanel3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(15, Short.MAX_VALUE))
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
    }// </editor-fold>//GEN-END:initComponents

    private void formInternalFrameOpened(javax.swing.event.InternalFrameEvent evt) {//GEN-FIRST:event_formInternalFrameOpened
        // TODO add your handling code here:
        ancho_tabla_orden_idempiere(tblorden);
    }//GEN-LAST:event_formInternalFrameOpened

    private void tblordenMouseReleased(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tblordenMouseReleased
        // TODO add your handling code here:
        seleccionar_order();
    }//GEN-LAST:event_tblordenMouseReleased

    private void tblitem_estudiosMouseReleased(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tblitem_estudiosMouseReleased
        // TODO add your handling code here:
        seleccionar_item_orden();
    }//GEN-LAST:event_tblitem_estudiosMouseReleased

    private void btnpasarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnpasarActionPerformed
        // TODO add your handling code here:
        boton_cargar();
    }//GEN-LAST:event_btnpasarActionPerformed

    private void tblitem_estudiosKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_tblitem_estudiosKeyReleased
        // TODO add your handling code here:
        seleccionar_item_orden();
    }//GEN-LAST:event_tblitem_estudiosKeyReleased

    private void tblordenKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_tblordenKeyReleased
        // TODO add your handling code here:
        seleccionar_order();
    }//GEN-LAST:event_tblordenKeyReleased

    private void btnpasar_nro_ordenActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnpasar_nro_ordenActionPerformed
        // TODO add your handling code here:
        boton_pasar_nro_orden();
    }//GEN-LAST:event_btnpasar_nro_ordenActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnpasar;
    private javax.swing.JButton btnpasar_nro_orden;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JPanel jPanel4;
    private javax.swing.JPanel jPanel5;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JTable tblitem_estudios;
    private javax.swing.JTable tblorden;
    private javax.swing.JTextField txtcliente_local_ci;
    private javax.swing.JTextField txtcliente_local_nom;
    private javax.swing.JTextField txtestudio_local;
    private javax.swing.JTextField txtmensaje_orden;
    private javax.swing.JTextField txtsku;
    private javax.swing.JTextField txtunidad_local;
    // End of variables declaration//GEN-END:variables
}
