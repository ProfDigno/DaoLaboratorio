/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package FORMULARIO.VISTA;

import BASEDATO.EvenConexion;
import BASEDATO.LOCAL.ConnPostgres;
import Evento.Color.cla_color_pelete;
import Evento.Combobox.EvenCombobox;
import Evento.JTextField.EvenJTextField;
import Evento.Jframe.EvenJFRAME;
import Evento.Jtable.EvenJtable;
import Evento.Mensaje.EvenMensajeJoptionpane;
import FORMULARIO.BO.BO_item_orden_lab;
import FORMULARIO.BO.BO_orden_lab;
import FORMULARIO.BO.BO_persona;
import FORMULARIO.DAO.DAO_item_orden_lab;
import FORMULARIO.DAO.DAO_orden_lab;
import FORMULARIO.DAO.DAO_persona;
import FORMULARIO.DAO.DAO_plan_seguro;
import FORMULARIO.DAO.DAO_tipo_seguro;
import FORMULARIO.ENTIDAD.item_orden_lab;
import FORMULARIO.ENTIDAD.orden_lab;
import FORMULARIO.ENTIDAD.persona;
import FORMULARIO.ENTIDAD.plan_seguro;
import FORMULARIO.ENTIDAD.tabla_orden_lab;
import FORMULARIO.ENTIDAD.tipo_seguro;
import java.awt.Color;
import java.awt.event.KeyEvent;
import java.sql.Connection;

/**
 *
 * @author Digno
 */
public class FrmOrden_lab_cargaDato extends javax.swing.JInternalFrame {

    EvenConexion eveconn = new EvenConexion();
    EvenJFRAME evetbl = new EvenJFRAME();
    EvenJtable eveJtab = new EvenJtable();
    EvenJTextField evejtf = new EvenJTextField();
    EvenCombobox comb = new EvenCombobox();
    Connection conn = ConnPostgres.getConnPosgres();
    cla_color_pelete clacolor = new cla_color_pelete();
    EvenMensajeJoptionpane evemen = new EvenMensajeJoptionpane();

    private persona pers = new persona();
    private BO_persona persBO = new BO_persona();
    private DAO_persona persdao = new DAO_persona();
    private tipo_seguro tseg = new tipo_seguro();
    private DAO_tipo_seguro tsegdao = new DAO_tipo_seguro();
    private plan_seguro pseg = new plan_seguro();
    private DAO_plan_seguro psegdao = new DAO_plan_seguro();
    private tabla_orden_lab tbl_or = new tabla_orden_lab();
    private orden_lab ordl = new orden_lab();
    private DAO_orden_lab ordDao = new DAO_orden_lab();
    private BO_orden_lab ordBo = new BO_orden_lab();
    private item_orden_lab iord = new item_orden_lab();
    private DAO_item_orden_lab iolDao = new DAO_item_orden_lab();
    private BO_item_orden_lab iolBo=new BO_item_orden_lab();
    private int idorden_lab;
    private int fila_select_item_orden_lab;
    private boolean buscar_tabla_predefinido;
    private int idlab_estudio_predefinido;
    private int fk_idlab_estudio;
    private String filtro_orden_lab="";

    private void abrir_formulario() {
        this.setTitle("CARGAR ORDEN");
        evetbl.centrar_formulario_internalframa(this);
        ordDao.actualizar_tabla_orden_lab(conn, tblbuscar_orden,filtro_orden_lab);
        txtnota.setEnabled(false);
    }
    private String getStringFiltrofec_orden_lab(){
        String fecha="";
        /**
         * + "where  date_part('year',fecha_emision)=" + ano
                + " and date_part('week',fecha_emision)=" + semana
         */
        if(jRfecha_hoy.isSelected()){
            fecha=" and date(ol.fecha_inicio)=date(current_date) ";
//            fecha=" and date_part('year',ol.fecha_inicio)=date_part('year',current_date) ";
        }
        if(jRfecha_ayer.isSelected()){
            fecha=" and date(ol.fecha_inicio)=date(current_date - 1) ";
        }
        if(jRfecha_semana.isSelected()){
            fecha=" and date_part('year',ol.fecha_inicio)=date_part('year',current_date) "
                    + "and date_part('week',ol.fecha_inicio)=date_part('week',current_date) ";
        }
        if(jRfecha_mes.isSelected()){
            fecha=" and date_part('year',ol.fecha_inicio)=date_part('year',current_date) "
                    + "and date_part('month',ol.fecha_inicio)=date_part('month',current_date) ";
        }
        return fecha;
    }
    void radio_filtro_fecha(){
        ordDao.actualizar_tabla_orden_lab(conn, tblbuscar_orden,getStringFiltrofec_orden_lab());
    }
    private void seleccionar_orden() {
        if (tblbuscar_orden.getSelectedRow() >= 0) {
            idorden_lab = eveJtab.getInt_select_id(tblbuscar_orden);
            ordDao.cargar_orden_lab(conn, ordl, idorden_lab);
            txtorden.setText(String.valueOf(ordl.getC1idorden_lab()));
            txtfec_inicio.setText(ordl.getC2fecha_inicio());
            persdao.cargar_persona(conn, pers, ordl.getC7fk_idpersona1());
            txtpaciente_nombre.setText(pers.getC2nombre() + " " + pers.getC3apellido());
            txtpaciente_cedula.setText(pers.getC4cedula());
            txtpaciente_direccion.setText(pers.getC6direccion());
            txtpaciente_telefono.setText(pers.getC5telefono());
            txtpaciente_genero.setText(pers.getC8genero());
            txtpaciente_edad.setText(pers.getC16edad());
            tsegdao.cargar_tipo_seguro(conn, tseg, pers.getC12fk_idtipo_seguro());
            txtpaciente_tipo_seguro.setText(tseg.getC2nombre());
            psegdao.cargar_plan_seguro(conn, pseg, pers.getC13fk_idplan_seguro());
            txtpaciente_plan_seguro.setText(pseg.getC2nombre());
            if (pers.getC8genero().equals("M")) {
                panel_paciente.setBackground(new Color(204, 204, 255));
            }
            if (pers.getC8genero().equals("F")) {
                panel_paciente.setBackground(new Color(255, 204, 204));
            }
            iolDao.actualizar_tabla_item_orden_lab_pedido(conn, tblitem_orden_lab, idorden_lab);
            tblitem_orden_lab.requestFocus();
            tblitem_orden_lab.changeSelection(0, 0, false, false);
            cargar_item_orden_lab();

        }
    }

    private void cargar_item_orden_lab() {
        if (tblitem_orden_lab.getSelectedRow() >= 0) {
            txtnota.setEnabled(false);
            fila_select_item_orden_lab = tblitem_orden_lab.getSelectedRow();
            int iditem_orden_lab = eveJtab.getInt_select_id(tblitem_orden_lab);
            iolDao.cargar_item_orden_lab(conn, iord, iditem_orden_lab);
            lblvalor_numerico.setEnabled(iord.getC9es_numerico());
            txtvalor_numerico.setEnabled(iord.getC9es_numerico());
            panel_valor_texto.setEnabled(iord.getC10es_testo());
            txtAvalor_testo.setEnabled(iord.getC10es_testo());
            btnguardar_testo.setEnabled(iord.getC10es_testo());
            txtAvalor_de_referencia.setText(iord.getC7valor_de_referencia());
            txtnombre_predefinido.setEnabled(iord.getC11es_predefinido());
            jLista_predefinido.setVisible(false);
            txtnota.setText(iord.getC13nota());
            txtUnidad.setText(iord.getC8unidad());
            if (iord.getC12estado_estudio().equals(tbl_or.getEstado_pedido())) {
                txtvalor_numerico.setText(null);
                txtAvalor_testo.setText(null);
            }
            if (iord.getC12estado_estudio().equals(tbl_or.getEstado_cargado())) {
                txtvalor_numerico.setText(String.valueOf(iord.getC4valor_numerico()));
                txtAvalor_testo.setText(iord.getC5valor_testo());
                fk_idlab_estudio = iord.getC6valor_predefinido();
            }
            if (iord.getC9es_numerico()) {
                txtvalor_numerico.requestFocus();
            }
            if (iord.getC10es_testo()) {
                eveJtab.mostrar_JTabbedPane(jTab_testo_predefinido, 0);
                txtAvalor_testo.requestFocus();
            }
            if (iord.getC11es_predefinido()) {
                eveJtab.mostrar_JTabbedPane(jTab_testo_predefinido, 1);
                fk_idlab_estudio = eveJtab.getInt_select(tblitem_orden_lab, 6);
                txtnombre_predefinido.requestFocus();
            }
        }
    }

    private void update_item_orden_lab_numerico() {
        if (tblitem_orden_lab.getSelectedRow() >= 0) {
            if (txtvalor_numerico.getText().trim().length() > 0) {
                int iditem_orden_lab = eveJtab.getInt_select_id(tblitem_orden_lab);
                Double valor_numerico = Double.parseDouble(txtvalor_numerico.getText());
                String nota = txtnota.getText();
                iord.setC4valor_numerico(valor_numerico);
                iord.setC13nota(nota);
                iord.setC12estado_estudio(tbl_or.getEstado_cargado());
                iord.setC1iditem_orden_lab(iditem_orden_lab);
//                iolDao.update_item_orden_lab_cargado_numerico(conn, iord);
                iolBo.update_item_orden_lab(iord, 1);
                iolDao.actualizar_tabla_item_orden_lab_pedido(conn, tblitem_orden_lab, idorden_lab);
                txtvalor_numerico.setText(null);
                fila_select_item_orden_lab++;
                tblitem_orden_lab.requestFocus();
                System.out.println("fila_select_item_orden_lab:" + fila_select_item_orden_lab);
                if (fila_select_item_orden_lab >= tblitem_orden_lab.getRowCount()) {
                    fila_select_item_orden_lab = 0;
                }
                tblitem_orden_lab.changeSelection(fila_select_item_orden_lab, 0, false, false);
                cargar_item_orden_lab();
            }
        }
    }

    private void update_item_orden_lab_testo() {
        if (tblitem_orden_lab.getSelectedRow() >= 0) {
            int iditem_orden_lab = eveJtab.getInt_select_id(tblitem_orden_lab);
            String valor_testo = txtAvalor_testo.getText();
            String nota = txtnota.getText();
            iord.setC5valor_testo(valor_testo);
            iord.setC13nota(nota);
            iord.setC12estado_estudio(tbl_or.getEstado_cargado());
            iord.setC1iditem_orden_lab(iditem_orden_lab);
//            iolDao.update_item_orden_lab_cargado_testo(conn, iord);
            iolBo.update_item_orden_lab(iord, 2);
            iolDao.actualizar_tabla_item_orden_lab_pedido(conn, tblitem_orden_lab, idorden_lab);
            txtAvalor_testo.setText(null);
            fila_select_item_orden_lab++;
            tblitem_orden_lab.requestFocus();
            System.out.println("fila_select_item_orden_lab:" + fila_select_item_orden_lab);
            if (fila_select_item_orden_lab >= tblitem_orden_lab.getRowCount()) {
                fila_select_item_orden_lab = 0;
            }
            tblitem_orden_lab.changeSelection(fila_select_item_orden_lab, 0, false, false);
            cargar_item_orden_lab();
        }
    }

    private void update_item_orden_lab_predefinido() {
        if (tblitem_orden_lab.getSelectedRow() >= 0) {
            int iditem_orden_lab = eveJtab.getInt_select_id(tblitem_orden_lab);
            String nota = txtnota.getText();
            iord.setC6valor_predefinido(idlab_estudio_predefinido);
            iord.setC13nota(nota);
            iord.setC12estado_estudio(tbl_or.getEstado_cargado());
            iord.setC1iditem_orden_lab(iditem_orden_lab);
//            iolDao.update_item_orden_lab_cargado_predefino(conn, iord);
            iolBo.update_item_orden_lab(iord, 3);
            iolDao.actualizar_tabla_item_orden_lab_pedido(conn, tblitem_orden_lab, idorden_lab);
            txtnombre_predefinido.setText(null);
            fila_select_item_orden_lab++;
            tblitem_orden_lab.requestFocus();
            System.out.println("fila_select_item_orden_lab:" + fila_select_item_orden_lab);
            if (fila_select_item_orden_lab >= tblitem_orden_lab.getRowCount()) {
                fila_select_item_orden_lab = 0;
                update_orden_cargado();
            }
            tblitem_orden_lab.changeSelection(fila_select_item_orden_lab, 0, false, false);
            cargar_item_orden_lab();
        }
    }

    private void update_orden_cargado() {
        if (tblbuscar_orden.getSelectedRow() >= 0) {
            ordl.setC1idorden_lab(idorden_lab);
            ordl.setC4estado(tbl_or.getEstado_cargado());
            ordBo.update_orden_lab_estado(ordl,tbl_or.getEstado_cargado());
            ordDao.actualizar_tabla_orden_lab(conn, tblbuscar_orden,filtro_orden_lab);
        }
    }
     private void update_orden_cancelado() {
        if (tblbuscar_orden.getSelectedRow() >= 0) {
            ordl.setC1idorden_lab(idorden_lab);
            ordl.setC4estado(tbl_or.getEstado_cancelado());
            ordBo.update_orden_lab_estado(ordl,tbl_or.getEstado_cancelado());
            ordDao.actualizar_tabla_orden_lab(conn, tblbuscar_orden,filtro_orden_lab);
        }
    }

    private void buscar_cargar_Jlista_predefinido(int fk_idlab_estudio) {
        eveconn.buscar_cargar_Jlista(conn, txtnombre_predefinido, jLista_predefinido, "public.item_lab_estudio_predefinido ilep,public.lab_estudio_predefinido lep",
                "ilep.fk_idlab_estudio_predefinido=lep.idlab_estudio_predefinido and ilep.fk_idlab_estudio=" + fk_idlab_estudio + " and lep.nombre",
                "(lep.idlab_estudio_predefinido||'-('||lep.nombre||')') as nombre", 5);
    }

    private int cargar_idtabla_predefinido() {
        int idlab_estudio_predefinido = eveconn.getInt_seleccionar_JLista(conn, txtnombre_predefinido, jLista_predefinido, false,
                "public.item_lab_estudio_predefinido ilep,public.lab_estudio_predefinido lep",
                "concat(lep.idlab_estudio_predefinido,'-(',lep.nombre,')')",
                "(lep.idlab_estudio_predefinido||'-('||lep.nombre||')') as nombre", "nombre", "idlab_estudio_predefinido");
        return idlab_estudio_predefinido;
    }

    public FrmOrden_lab_cargaDato() {
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

        gru_fec = new javax.swing.ButtonGroup();
        jTabbedPane1 = new javax.swing.JTabbedPane();
        jPanel1 = new javax.swing.JPanel();
        jPanel3 = new javax.swing.JPanel();
        jScrollPane1 = new javax.swing.JScrollPane();
        tblbuscar_orden = new javax.swing.JTable();
        jPanel5 = new javax.swing.JPanel();
        jRfecha_hoy = new javax.swing.JRadioButton();
        jRfecha_ayer = new javax.swing.JRadioButton();
        jRfecha_semana = new javax.swing.JRadioButton();
        jLabel13 = new javax.swing.JLabel();
        txtfecha_desde = new javax.swing.JTextField();
        jLabel14 = new javax.swing.JLabel();
        jLabel15 = new javax.swing.JLabel();
        txtfecha_hasta = new javax.swing.JTextField();
        jLabel16 = new javax.swing.JLabel();
        jRfecha_mes = new javax.swing.JRadioButton();
        jPanel2 = new javax.swing.JPanel();
        jPanel4 = new javax.swing.JPanel();
        jScrollPane2 = new javax.swing.JScrollPane();
        tblitem_orden_lab = new javax.swing.JTable();
        panel_paciente = new javax.swing.JPanel();
        jLabel4 = new javax.swing.JLabel();
        txtpaciente_nombre = new javax.swing.JTextField();
        jLabel5 = new javax.swing.JLabel();
        txtpaciente_cedula = new javax.swing.JTextField();
        jLabel6 = new javax.swing.JLabel();
        txtpaciente_direccion = new javax.swing.JTextField();
        jLabel7 = new javax.swing.JLabel();
        txtpaciente_telefono = new javax.swing.JTextField();
        jLabel8 = new javax.swing.JLabel();
        jLabel9 = new javax.swing.JLabel();
        txtpaciente_genero = new javax.swing.JTextField();
        txtpaciente_edad = new javax.swing.JTextField();
        jLabel10 = new javax.swing.JLabel();
        txtpaciente_tipo_seguro = new javax.swing.JTextField();
        jLabel11 = new javax.swing.JLabel();
        txtpaciente_plan_seguro = new javax.swing.JTextField();
        jPanel6 = new javax.swing.JPanel();
        lblvalor_numerico = new javax.swing.JLabel();
        txtvalor_numerico = new javax.swing.JTextField();
        jLabel1 = new javax.swing.JLabel();
        btnagregar_nota = new javax.swing.JButton();
        txtnota = new javax.swing.JTextField();
        txtUnidad = new javax.swing.JTextField();
        jPanel8 = new javax.swing.JPanel();
        jScrollPane4 = new javax.swing.JScrollPane();
        txtAvalor_de_referencia = new javax.swing.JTextArea();
        jLabel2 = new javax.swing.JLabel();
        txtorden = new javax.swing.JTextField();
        jTab_testo_predefinido = new javax.swing.JTabbedPane();
        panel_valor_texto = new javax.swing.JPanel();
        jScrollPane3 = new javax.swing.JScrollPane();
        txtAvalor_testo = new javax.swing.JTextArea();
        btnguardar_testo = new javax.swing.JButton();
        jPanel7 = new javax.swing.JPanel();
        jLabel3 = new javax.swing.JLabel();
        txtnombre_predefinido = new javax.swing.JTextField();
        jLista_predefinido = new javax.swing.JList<>();
        txtfec_inicio = new javax.swing.JTextField();
        jLabel12 = new javax.swing.JLabel();
        btnpasar_cargados = new javax.swing.JButton();
        btncancelar_orden = new javax.swing.JButton();
        btnimprimir_orden = new javax.swing.JButton();

        setClosable(true);
        setIconifiable(true);
        setMaximizable(true);
        setResizable(true);
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

        jPanel3.setBackground(new java.awt.Color(204, 204, 255));
        jPanel3.setBorder(javax.swing.BorderFactory.createTitledBorder("ORDEN PEDIDO"));

        tblbuscar_orden.setModel(new javax.swing.table.DefaultTableModel(
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
        tblbuscar_orden.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseReleased(java.awt.event.MouseEvent evt) {
                tblbuscar_ordenMouseReleased(evt);
            }
        });
        jScrollPane1.setViewportView(tblbuscar_orden);

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 1313, Short.MAX_VALUE)
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 360, javax.swing.GroupLayout.PREFERRED_SIZE)
        );

        jPanel5.setBorder(javax.swing.BorderFactory.createTitledBorder("FILTRO FECHA"));

        gru_fec.add(jRfecha_hoy);
        jRfecha_hoy.setSelected(true);
        jRfecha_hoy.setText("HOY");
        jRfecha_hoy.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                jRfecha_hoyItemStateChanged(evt);
            }
        });

        gru_fec.add(jRfecha_ayer);
        jRfecha_ayer.setText("AYER");
        jRfecha_ayer.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                jRfecha_ayerItemStateChanged(evt);
            }
        });

        gru_fec.add(jRfecha_semana);
        jRfecha_semana.setText("SEMANA");
        jRfecha_semana.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                jRfecha_semanaItemStateChanged(evt);
            }
        });

        jLabel13.setText("DESDE:");

        jLabel14.setText("aaa-mm-dd");

        jLabel15.setText("HASTA:");

        jLabel16.setText("aaa-mm-dd");

        gru_fec.add(jRfecha_mes);
        jRfecha_mes.setText("MES");
        jRfecha_mes.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                jRfecha_mesItemStateChanged(evt);
            }
        });

        javax.swing.GroupLayout jPanel5Layout = new javax.swing.GroupLayout(jPanel5);
        jPanel5.setLayout(jPanel5Layout);
        jPanel5Layout.setHorizontalGroup(
            jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel5Layout.createSequentialGroup()
                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel15, javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jLabel13, javax.swing.GroupLayout.Alignment.TRAILING))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(txtfecha_hasta, javax.swing.GroupLayout.PREFERRED_SIZE, 94, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txtfecha_desde, javax.swing.GroupLayout.PREFERRED_SIZE, 94, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel16)
                    .addComponent(jLabel14))
                .addGap(0, 0, Short.MAX_VALUE))
            .addGroup(jPanel5Layout.createSequentialGroup()
                .addComponent(jRfecha_hoy)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jRfecha_ayer)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jRfecha_semana)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jRfecha_mes, javax.swing.GroupLayout.DEFAULT_SIZE, 57, Short.MAX_VALUE)
                .addContainerGap())
        );
        jPanel5Layout.setVerticalGroup(
            jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel5Layout.createSequentialGroup()
                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jRfecha_hoy)
                    .addComponent(jRfecha_ayer)
                    .addComponent(jRfecha_semana)
                    .addComponent(jRfecha_mes))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel13)
                    .addComponent(txtfecha_desde, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel14))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel15)
                    .addComponent(txtfecha_hasta, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel16)))
        );

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addComponent(jPanel5, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, Short.MAX_VALUE))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addComponent(jPanel3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanel5, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 76, Short.MAX_VALUE))
        );

        jTabbedPane1.addTab("BUSCAR ORDEN DE LAB", jPanel1);

        jPanel4.setBackground(new java.awt.Color(204, 204, 255));
        jPanel4.setBorder(javax.swing.BorderFactory.createTitledBorder("CARGAR ORDEN ESTUDIO"));

        tblitem_orden_lab.setModel(new javax.swing.table.DefaultTableModel(
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
        tblitem_orden_lab.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseReleased(java.awt.event.MouseEvent evt) {
                tblitem_orden_labMouseReleased(evt);
            }
        });
        jScrollPane2.setViewportView(tblitem_orden_lab);

        javax.swing.GroupLayout jPanel4Layout = new javax.swing.GroupLayout(jPanel4);
        jPanel4.setLayout(jPanel4Layout);
        jPanel4Layout.setHorizontalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jScrollPane2, javax.swing.GroupLayout.DEFAULT_SIZE, 884, Short.MAX_VALUE)
        );
        jPanel4Layout.setVerticalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jScrollPane2, javax.swing.GroupLayout.DEFAULT_SIZE, 387, Short.MAX_VALUE)
        );

        panel_paciente.setBackground(new java.awt.Color(204, 204, 255));
        panel_paciente.setBorder(javax.swing.BorderFactory.createTitledBorder("DATOS DE PACIENTE"));

        jLabel4.setText("NOMBRE:");

        txtpaciente_nombre.setEditable(false);
        txtpaciente_nombre.setBackground(new java.awt.Color(255, 255, 204));

        jLabel5.setText("CEDULA:");

        txtpaciente_cedula.setEditable(false);
        txtpaciente_cedula.setBackground(new java.awt.Color(255, 255, 204));

        jLabel6.setText("DIRECCION:");

        txtpaciente_direccion.setEditable(false);
        txtpaciente_direccion.setBackground(new java.awt.Color(255, 255, 204));

        jLabel7.setText("TEL.:");

        txtpaciente_telefono.setEditable(false);
        txtpaciente_telefono.setBackground(new java.awt.Color(255, 255, 204));

        jLabel8.setText("GENERO:");

        jLabel9.setText("EDAD:");

        txtpaciente_genero.setEditable(false);
        txtpaciente_genero.setBackground(new java.awt.Color(255, 255, 204));

        txtpaciente_edad.setEditable(false);
        txtpaciente_edad.setBackground(new java.awt.Color(255, 255, 204));

        jLabel10.setText("SEGURO:");

        txtpaciente_tipo_seguro.setEditable(false);
        txtpaciente_tipo_seguro.setBackground(new java.awt.Color(255, 255, 204));

        jLabel11.setText("PLAN MEDICO:");

        txtpaciente_plan_seguro.setEditable(false);
        txtpaciente_plan_seguro.setBackground(new java.awt.Color(255, 255, 204));

        javax.swing.GroupLayout panel_pacienteLayout = new javax.swing.GroupLayout(panel_paciente);
        panel_paciente.setLayout(panel_pacienteLayout);
        panel_pacienteLayout.setHorizontalGroup(
            panel_pacienteLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panel_pacienteLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(panel_pacienteLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel4)
                    .addComponent(jLabel6)
                    .addComponent(jLabel10))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(panel_pacienteLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addGroup(panel_pacienteLayout.createSequentialGroup()
                        .addGroup(panel_pacienteLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(txtpaciente_direccion, javax.swing.GroupLayout.PREFERRED_SIZE, 345, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(txtpaciente_nombre, javax.swing.GroupLayout.PREFERRED_SIZE, 346, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(panel_pacienteLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel5)
                            .addComponent(jLabel7))
                        .addGap(11, 11, 11)
                        .addGroup(panel_pacienteLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(txtpaciente_cedula)
                            .addComponent(txtpaciente_telefono, javax.swing.GroupLayout.DEFAULT_SIZE, 173, Short.MAX_VALUE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(panel_pacienteLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel8)
                            .addComponent(jLabel9))
                        .addGap(10, 10, 10)
                        .addGroup(panel_pacienteLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(txtpaciente_edad)
                            .addGroup(panel_pacienteLayout.createSequentialGroup()
                                .addComponent(txtpaciente_genero, javax.swing.GroupLayout.PREFERRED_SIZE, 134, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(0, 0, Short.MAX_VALUE))))
                    .addGroup(panel_pacienteLayout.createSequentialGroup()
                        .addComponent(txtpaciente_tipo_seguro, javax.swing.GroupLayout.PREFERRED_SIZE, 186, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(jLabel11)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(txtpaciente_plan_seguro)))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        panel_pacienteLayout.setVerticalGroup(
            panel_pacienteLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panel_pacienteLayout.createSequentialGroup()
                .addGroup(panel_pacienteLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel4)
                    .addComponent(txtpaciente_nombre, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel5)
                    .addComponent(txtpaciente_cedula, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel8)
                    .addComponent(txtpaciente_genero, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(panel_pacienteLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel6)
                    .addComponent(txtpaciente_direccion, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel7)
                    .addComponent(txtpaciente_telefono, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel9)
                    .addComponent(txtpaciente_edad, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(panel_pacienteLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel10)
                    .addComponent(txtpaciente_tipo_seguro, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel11)
                    .addComponent(txtpaciente_plan_seguro, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(0, 8, Short.MAX_VALUE))
        );

        jPanel6.setBackground(new java.awt.Color(153, 204, 255));
        jPanel6.setBorder(javax.swing.BorderFactory.createTitledBorder("DATOS"));

        lblvalor_numerico.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        lblvalor_numerico.setText("NUMERICO:");

        txtvalor_numerico.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        txtvalor_numerico.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                txtvalor_numericoKeyPressed(evt);
            }
            public void keyTyped(java.awt.event.KeyEvent evt) {
                txtvalor_numericoKeyTyped(evt);
            }
        });

        jLabel1.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jLabel1.setText("UNID:");

        btnagregar_nota.setText("Nota");
        btnagregar_nota.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnagregar_notaActionPerformed(evt);
            }
        });

        txtUnidad.setEditable(false);
        txtUnidad.setBackground(new java.awt.Color(204, 204, 255));
        txtUnidad.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N

        javax.swing.GroupLayout jPanel6Layout = new javax.swing.GroupLayout(jPanel6);
        jPanel6.setLayout(jPanel6Layout);
        jPanel6Layout.setHorizontalGroup(
            jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel6Layout.createSequentialGroup()
                .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                    .addComponent(btnagregar_nota, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(javax.swing.GroupLayout.Alignment.LEADING, jPanel6Layout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(lblvalor_numerico)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel6Layout.createSequentialGroup()
                        .addComponent(txtvalor_numerico, javax.swing.GroupLayout.PREFERRED_SIZE, 153, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jLabel1)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(txtUnidad)
                        .addContainerGap())
                    .addComponent(txtnota)))
        );
        jPanel6Layout.setVerticalGroup(
            jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel6Layout.createSequentialGroup()
                .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.CENTER)
                    .addComponent(lblvalor_numerico)
                    .addComponent(txtvalor_numerico, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel1)
                    .addComponent(txtUnidad, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(3, 3, 3)
                .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.CENTER)
                    .addComponent(btnagregar_nota, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(txtnota)))
        );

        jPanel8.setBorder(javax.swing.BorderFactory.createTitledBorder("VALOR DE REFERENCIA"));

        txtAvalor_de_referencia.setEditable(false);
        txtAvalor_de_referencia.setBackground(new java.awt.Color(204, 204, 255));
        txtAvalor_de_referencia.setColumns(20);
        txtAvalor_de_referencia.setFont(new java.awt.Font("Arial", 0, 12)); // NOI18N
        txtAvalor_de_referencia.setRows(5);
        jScrollPane4.setViewportView(txtAvalor_de_referencia);

        javax.swing.GroupLayout jPanel8Layout = new javax.swing.GroupLayout(jPanel8);
        jPanel8.setLayout(jPanel8Layout);
        jPanel8Layout.setHorizontalGroup(
            jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jScrollPane4)
        );
        jPanel8Layout.setVerticalGroup(
            jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jScrollPane4, javax.swing.GroupLayout.DEFAULT_SIZE, 168, Short.MAX_VALUE)
        );

        jLabel2.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        jLabel2.setText("ORDEN:");

        txtorden.setEditable(false);
        txtorden.setBackground(new java.awt.Color(255, 255, 204));
        txtorden.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N

        panel_valor_texto.setBackground(new java.awt.Color(153, 204, 255));
        panel_valor_texto.setBorder(javax.swing.BorderFactory.createTitledBorder("TESTO"));

        txtAvalor_testo.setColumns(20);
        txtAvalor_testo.setRows(5);
        txtAvalor_testo.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                txtAvalor_testoKeyPressed(evt);
            }
        });
        jScrollPane3.setViewportView(txtAvalor_testo);

        btnguardar_testo.setText("Guardar Texto - F5");
        btnguardar_testo.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnguardar_testoActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout panel_valor_textoLayout = new javax.swing.GroupLayout(panel_valor_texto);
        panel_valor_texto.setLayout(panel_valor_textoLayout);
        panel_valor_textoLayout.setHorizontalGroup(
            panel_valor_textoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jScrollPane3, javax.swing.GroupLayout.DEFAULT_SIZE, 402, Short.MAX_VALUE)
            .addGroup(panel_valor_textoLayout.createSequentialGroup()
                .addComponent(btnguardar_testo)
                .addGap(0, 0, Short.MAX_VALUE))
        );
        panel_valor_textoLayout.setVerticalGroup(
            panel_valor_textoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panel_valor_textoLayout.createSequentialGroup()
                .addComponent(jScrollPane3, javax.swing.GroupLayout.DEFAULT_SIZE, 127, Short.MAX_VALUE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(btnguardar_testo))
        );

        jTab_testo_predefinido.addTab("DATO TESTO", panel_valor_texto);

        jPanel7.setBorder(javax.swing.BorderFactory.createTitledBorder("PREDEFINIDO"));

        jLabel3.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jLabel3.setText("DATO:");

        txtnombre_predefinido.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        txtnombre_predefinido.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                txtnombre_predefinidoKeyPressed(evt);
            }
            public void keyReleased(java.awt.event.KeyEvent evt) {
                txtnombre_predefinidoKeyReleased(evt);
            }
        });

        jLista_predefinido.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jLista_predefinido.setSelectionBackground(new java.awt.Color(255, 0, 0));
        jLista_predefinido.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                jLista_predefinidoKeyPressed(evt);
            }
        });

        javax.swing.GroupLayout jPanel7Layout = new javax.swing.GroupLayout(jPanel7);
        jPanel7.setLayout(jPanel7Layout);
        jPanel7Layout.setHorizontalGroup(
            jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel7Layout.createSequentialGroup()
                .addGap(2, 2, 2)
                .addGroup(jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLista_predefinido, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(jPanel7Layout.createSequentialGroup()
                        .addComponent(jLabel3, javax.swing.GroupLayout.PREFERRED_SIZE, 46, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(txtnombre_predefinido, javax.swing.GroupLayout.DEFAULT_SIZE, 340, Short.MAX_VALUE)))
                .addContainerGap())
        );
        jPanel7Layout.setVerticalGroup(
            jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel7Layout.createSequentialGroup()
                .addGroup(jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.CENTER)
                    .addComponent(jLabel3)
                    .addComponent(txtnombre_predefinido, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLista_predefinido, javax.swing.GroupLayout.DEFAULT_SIZE, 127, Short.MAX_VALUE))
        );

        jTab_testo_predefinido.addTab("DATO PREDEFINIDO", jPanel7);

        txtfec_inicio.setEditable(false);
        txtfec_inicio.setBackground(new java.awt.Color(255, 255, 204));
        txtfec_inicio.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N

        jLabel12.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        jLabel12.setText("Fec. INICIO:");

        btnpasar_cargados.setBackground(new java.awt.Color(204, 255, 204));
        btnpasar_cargados.setText("PASAR A CARGADOS");
        btnpasar_cargados.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnpasar_cargadosActionPerformed(evt);
            }
        });

        btncancelar_orden.setBackground(new java.awt.Color(255, 153, 0));
        btncancelar_orden.setText("CANCELAR ORDEN");
        btncancelar_orden.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btncancelar_ordenActionPerformed(evt);
            }
        });

        btnimprimir_orden.setText("IMPRIMIR ORDEN");
        btnimprimir_orden.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnimprimir_ordenActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                        .addComponent(jPanel4, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(panel_paciente, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addGap(8, 8, 8)
                        .addComponent(jLabel2)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(txtorden, javax.swing.GroupLayout.PREFERRED_SIZE, 121, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jLabel12)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(txtfec_inicio, javax.swing.GroupLayout.PREFERRED_SIZE, 194, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(btnpasar_cargados)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(btncancelar_orden)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(btnimprimir_orden)))
                .addGap(10, 10, 10)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jPanel6, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jTab_testo_predefinido)
                    .addComponent(jPanel8, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addComponent(jPanel6, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jTab_testo_predefinido, javax.swing.GroupLayout.PREFERRED_SIZE, 207, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jPanel8, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(0, 0, Short.MAX_VALUE))
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addComponent(panel_paciente, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.CENTER)
                            .addComponent(jLabel2)
                            .addComponent(txtorden, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel12)
                            .addComponent(txtfec_inicio, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(btnpasar_cargados)
                            .addComponent(btncancelar_orden)
                            .addComponent(btnimprimir_orden))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jPanel4, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                .addContainerGap())
        );

        jTabbedPane1.addTab("CARGAR ORDEN DE LAB", jPanel2);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jTabbedPane1)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jTabbedPane1)
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void formInternalFrameOpened(javax.swing.event.InternalFrameEvent evt) {//GEN-FIRST:event_formInternalFrameOpened
        // TODO add your handling code here:
        ordDao.ancho_tabla_orden_lab(tblbuscar_orden);
    }//GEN-LAST:event_formInternalFrameOpened

    private void tblbuscar_ordenMouseReleased(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tblbuscar_ordenMouseReleased
        // TODO add your handling code here:
        seleccionar_orden();
    }//GEN-LAST:event_tblbuscar_ordenMouseReleased

    private void tblitem_orden_labMouseReleased(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tblitem_orden_labMouseReleased
        // TODO add your handling code here:
        cargar_item_orden_lab();
    }//GEN-LAST:event_tblitem_orden_labMouseReleased

    private void btnagregar_notaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnagregar_notaActionPerformed
        // TODO add your handling code here:
        txtnota.setEnabled(true);
    }//GEN-LAST:event_btnagregar_notaActionPerformed

    private void txtvalor_numericoKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtvalor_numericoKeyPressed
        // TODO add your handling code here:
        if (evt.getKeyCode() == KeyEvent.VK_ENTER) {
            update_item_orden_lab_numerico();
        }
    }//GEN-LAST:event_txtvalor_numericoKeyPressed

    private void btnguardar_testoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnguardar_testoActionPerformed
        // TODO add your handling code here:
        update_item_orden_lab_testo();
    }//GEN-LAST:event_btnguardar_testoActionPerformed

    private void txtAvalor_testoKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtAvalor_testoKeyPressed
        // TODO add your handling code here:
        if (evt.getKeyCode() == KeyEvent.VK_F5) {
            update_item_orden_lab_testo();
        }
    }//GEN-LAST:event_txtAvalor_testoKeyPressed

    private void txtnombre_predefinidoKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtnombre_predefinidoKeyPressed
        // TODO add your handling code here:
        if (evejtf.seleccionar_lista(evt, jLista_predefinido)) {
            buscar_tabla_predefinido = true;
        }
        if (evt.getKeyCode() == KeyEvent.VK_ENTER) {
            update_item_orden_lab_predefinido();
        }
    }//GEN-LAST:event_txtnombre_predefinidoKeyPressed

    private void txtnombre_predefinidoKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtnombre_predefinidoKeyReleased
        // TODO add your handling code here:
        buscar_cargar_Jlista_predefinido(fk_idlab_estudio);
    }//GEN-LAST:event_txtnombre_predefinidoKeyReleased

    private void jLista_predefinidoKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jLista_predefinidoKeyPressed
        // TODO add your handling code here:
        if (evt.getKeyCode() == KeyEvent.VK_ENTER) {
            if (buscar_tabla_predefinido) {
                idlab_estudio_predefinido = cargar_idtabla_predefinido();
                buscar_tabla_predefinido = false;
                txtnombre_predefinido.requestFocus();
//                evejtf.saltar_campo_directo(txtnombre_predefinido, txtorden_predefinido);
            }
        }
    }//GEN-LAST:event_jLista_predefinidoKeyPressed

    private void btnpasar_cargadosActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnpasar_cargadosActionPerformed
        // TODO add your handling code here:
        update_orden_cargado();
    }//GEN-LAST:event_btnpasar_cargadosActionPerformed

    private void btncancelar_ordenActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btncancelar_ordenActionPerformed
        // TODO add your handling code here:
        update_orden_cancelado();
    }//GEN-LAST:event_btncancelar_ordenActionPerformed

    private void btnimprimir_ordenActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnimprimir_ordenActionPerformed
        // TODO add your handling code here:
        if (tblbuscar_orden.getSelectedRow() >= 0) {
            ordDao.imprimir_orden(conn, idorden_lab);
        }
    }//GEN-LAST:event_btnimprimir_ordenActionPerformed

    private void txtvalor_numericoKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtvalor_numericoKeyTyped
        // TODO add your handling code here:
        evejtf.soloNumero(evt);
    }//GEN-LAST:event_txtvalor_numericoKeyTyped

    private void jRfecha_hoyItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_jRfecha_hoyItemStateChanged
        // TODO add your handling code here:
        radio_filtro_fecha();
    }//GEN-LAST:event_jRfecha_hoyItemStateChanged

    private void jRfecha_ayerItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_jRfecha_ayerItemStateChanged
        // TODO add your handling code here:
        radio_filtro_fecha();
    }//GEN-LAST:event_jRfecha_ayerItemStateChanged

    private void jRfecha_semanaItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_jRfecha_semanaItemStateChanged
        // TODO add your handling code here:
        radio_filtro_fecha();
    }//GEN-LAST:event_jRfecha_semanaItemStateChanged

    private void jRfecha_mesItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_jRfecha_mesItemStateChanged
        // TODO add your handling code here:
        radio_filtro_fecha();
    }//GEN-LAST:event_jRfecha_mesItemStateChanged


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnagregar_nota;
    private javax.swing.JButton btncancelar_orden;
    private javax.swing.JButton btnguardar_testo;
    private javax.swing.JButton btnimprimir_orden;
    private javax.swing.JButton btnpasar_cargados;
    private javax.swing.ButtonGroup gru_fec;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel12;
    private javax.swing.JLabel jLabel13;
    private javax.swing.JLabel jLabel14;
    private javax.swing.JLabel jLabel15;
    private javax.swing.JLabel jLabel16;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JList<String> jLista_predefinido;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JPanel jPanel4;
    private javax.swing.JPanel jPanel5;
    private javax.swing.JPanel jPanel6;
    private javax.swing.JPanel jPanel7;
    private javax.swing.JPanel jPanel8;
    private javax.swing.JRadioButton jRfecha_ayer;
    private javax.swing.JRadioButton jRfecha_hoy;
    private javax.swing.JRadioButton jRfecha_mes;
    private javax.swing.JRadioButton jRfecha_semana;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JScrollPane jScrollPane3;
    private javax.swing.JScrollPane jScrollPane4;
    private javax.swing.JTabbedPane jTab_testo_predefinido;
    private javax.swing.JTabbedPane jTabbedPane1;
    private javax.swing.JLabel lblvalor_numerico;
    private javax.swing.JPanel panel_paciente;
    private javax.swing.JPanel panel_valor_texto;
    private javax.swing.JTable tblbuscar_orden;
    private javax.swing.JTable tblitem_orden_lab;
    private javax.swing.JTextArea txtAvalor_de_referencia;
    private javax.swing.JTextArea txtAvalor_testo;
    private javax.swing.JTextField txtUnidad;
    private javax.swing.JTextField txtfec_inicio;
    private javax.swing.JTextField txtfecha_desde;
    private javax.swing.JTextField txtfecha_hasta;
    private javax.swing.JTextField txtnombre_predefinido;
    private javax.swing.JTextField txtnota;
    private javax.swing.JTextField txtorden;
    private javax.swing.JTextField txtpaciente_cedula;
    private javax.swing.JTextField txtpaciente_direccion;
    private javax.swing.JTextField txtpaciente_edad;
    private javax.swing.JTextField txtpaciente_genero;
    private javax.swing.JTextField txtpaciente_nombre;
    private javax.swing.JTextField txtpaciente_plan_seguro;
    private javax.swing.JTextField txtpaciente_telefono;
    private javax.swing.JTextField txtpaciente_tipo_seguro;
    private javax.swing.JTextField txtvalor_numerico;
    // End of variables declaration//GEN-END:variables
}
