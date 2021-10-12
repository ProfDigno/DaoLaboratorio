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
import FORMULARIO.ENTIDAD.orden_lab;
import FORMULARIO.ENTIDAD.persona;
import FORMULARIO.ENTIDAD.plan_seguro;
import FORMULARIO.ENTIDAD.tabla_orden_lab;
import FORMULARIO.ENTIDAD.tipo_seguro;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.sql.Connection;
import java.sql.ResultSet;
import java.util.ArrayList;
import javax.swing.JButton;
import javax.swing.JOptionPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author Digno
 */
public class FrmOrden_lab_pedido extends javax.swing.JInternalFrame {

    EvenConexion eveconn = new EvenConexion();
    EvenJFRAME evetbl = new EvenJFRAME();
    EvenJtable eveJtab = new EvenJtable();
    EvenJTextField evejtf = new EvenJTextField();
    EvenCombobox comb = new EvenCombobox();
    Connection conn = ConnPostgres.getConnPosgres();
    Connection connSer = ConnPostgresServer.getConnPosgresServer();
    cla_color_pelete clacolor = new cla_color_pelete();
    EvenMensajeJoptionpane evemen = new EvenMensajeJoptionpane();
    DefaultTableModel model_per = new DefaultTableModel();
    DefaultTableModel model_item_orden_lab = new DefaultTableModel();
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
    private BO_item_orden_lab iolBo = new BO_item_orden_lab();
    private DAO_item_orden_lab iolDao = new DAO_item_orden_lab();
    private java.util.List<JButton> botones_tabla3;
    private java.util.List<JButton> botones_tabla6;
    private java.util.List<JButton> botones_marca;
    private int cant_boton_tabla3;
    private int cant_boton_unid;
    private boolean hab_orden_lugar = false;
    private int fk_idorden_lugar;
    private int fk_idpersona1;
    private int fk_idpersona2;
    private int fk_idpersona3;
    private int fk_idusuario = 1;
//    private String estado_orden = "PEDIDO";
    private int cant_estu_local;
    private int cant_estu_ser;
    private String suma_estudio_ser;

    private void abrir_formulario() {
        this.setTitle("ORDEN PEDIDO");
        evetbl.centrar_formulario_internalframa(this);
        botones_tabla3 = new ArrayList<>();
        botones_tabla6 = new ArrayList<>();
        botones_marca = new ArrayList<>();
        ordDao.actualizar_tabla_orden_lab(conn, tblorden_lab,"");
        cargar_boton_tabla3();
        cargar_orden_lugar();
        reestableser_orden();
    }

    private void reestableser_orden() {
        limpiar_paciente_uno();
        txtmedico_idpersona.setText(null);
        txtbuscar_medico.setText(null);
        txtbuscar_registro_medico.setText(null);
        txtvisacion.setText(null);
        txtobservacion.setText("Ninguna");
        jCorden_lugar.setSelectedIndex(0);
        eveJtab.limpiar_tabla_datos(model_item_orden_lab);
        fk_idpersona1 = 0;
        fk_idpersona2 = 0;
        fk_idpersona3 = 52066;
        fk_idorden_lugar = 0;
        txtbuscar_nombre_estudio.requestFocus();
    }

    private void cargar_orden_lugar() {
        comb.cargarCombobox(conn, jCorden_lugar, "idorden_lugar", "nombre", "public.orden_lugar", "");
        hab_orden_lugar = true;
    }

    private void buscar_persona(int tipo) {
        String campo = "";
        String buscar = "";
        if (tipo == 1) {
            txtpaciente_cedula.setText(null);
            if (txtpaciente_nombre.getText().trim().length() >= 3) {
                buscar = txtpaciente_nombre.getText();
                campo = "concat(p.nombre,' ',p.apellido)";
                pers.setSta_tipo_persona(pers.getSta_tipo_pers_paciente());
                int idpersona = persdao.getInt_buscar_idpersona(conn, campo, buscar, pers.getSta_tipo_persona());
                if (idpersona > 0) {
                    cargar_paciente_uno(idpersona);
                } else {
                    JDBuscar_persona frm = new JDBuscar_persona(null, true);
                    frm.setVisible(true);
                }
            }
        }
        if (tipo == 2) {
            txtpaciente_nombre.setText(null);
            if (txtpaciente_cedula.getText().trim().length() >= 3) {
                buscar = txtpaciente_cedula.getText();
                campo = "p.cedula";
                pers.setSta_tipo_persona(pers.getSta_tipo_pers_paciente());
                int idpersona = persdao.getInt_buscar_idpersona(conn, campo, buscar, pers.getSta_tipo_persona());
                System.out.println("IDPERSONA:" + idpersona);
                if (idpersona > 0) {
                    cargar_paciente_uno(idpersona);
                } else {
                    JDBuscar_persona frm = new JDBuscar_persona(null, true);
                    frm.setVisible(true);
                }
            }
        }
        if (tipo == 3) {
            if (txtbuscar_medico.getText().trim().length() >= 3) {
                buscar = txtbuscar_medico.getText();
                campo = "concat(p.nombre,' ',p.apellido)";
                pers.setSta_tipo_persona(pers.getSta_tipo_pers_medico());
                int idpersona = persdao.getInt_buscar_idpersona(conn, campo, buscar, pers.getSta_tipo_persona());
                if (idpersona > 0) {
                    cargar_medico_uno(idpersona);
                } else {
                    JDBuscar_persona frm = new JDBuscar_persona(null, true);
                    frm.setVisible(true);
                }
            }
        }
    }

    private void cargar_paciente_uno(int idpersona) {
        persdao.cargar_persona(conn, pers, idpersona);
        FrmOrden_lab_pedido.txtpersona_id.setText(String.valueOf(pers.getC1idpersona()));
        FrmOrden_lab_pedido.txtpaciente_nombre.setText(pers.getC2nombre() + " " + pers.getC3apellido());
        FrmOrden_lab_pedido.txtpaciente_cedula.setText(pers.getC4cedula());
        tsegdao.cargar_tipo_seguro(conn, tseg, pers.getC12fk_idtipo_seguro());
        FrmOrden_lab_pedido.txtpaciente_tipo_seguro.setText(tseg.getC2nombre());
        psegdao.cargar_plan_seguro(conn, pseg, pers.getC13fk_idplan_seguro());
        FrmOrden_lab_pedido.txtpaciente_plan_seguro.setText(pseg.getC2nombre());
        FrmOrden_lab_pedido.txtbuscar_medico.requestFocus();
    }

    private void cargar_medico_uno(int idpersona) {
        persdao.cargar_persona(conn, pers, idpersona);
        FrmOrden_lab_pedido.txtmedico_idpersona.setText(String.valueOf(pers.getC1idpersona()));
        FrmOrden_lab_pedido.txtbuscar_medico.setText(pers.getC2nombre() + " " + pers.getC3apellido());
        FrmOrden_lab_pedido.txtbuscar_registro_medico.setText(String.valueOf(pers.getC10registro()));
        txtvisacion.requestFocus();
    }

    private void limpiar_paciente_uno() {
        FrmOrden_lab_pedido.txtpersona_id.setText(null);
        FrmOrden_lab_pedido.txtpaciente_nombre.setText(null);
        FrmOrden_lab_pedido.txtpaciente_cedula.setText(null);
        FrmOrden_lab_pedido.txtpaciente_tipo_seguro.setText(null);
        FrmOrden_lab_pedido.txtpaciente_plan_seguro.setText(null);
    }

    private void cargar_boton_tabla3() {//area
        String titulo = "cargar_boton_tabla3";
        String sql = "select idlab_grupo_estudio,nombre,orden \n"
                + "from public.lab_grupo_estudio\n"
                + "where fk_idlab_grupo=" + tbl_or.getIdtabla_area()
                + " order by orden asc;";
        try {
            ResultSet rs = eveconn.getResulsetSQL(conn, sql, titulo);
            panel_referencia_tabla3.removeAll();
            botones_tabla3.clear();
            int cant = 0;
            while (rs.next()) {
                cant++;
                String textboton = rs.getString("nombre");
                String nameboton = rs.getString("idlab_grupo_estudio");
                crear_boton_tabla3(textboton, nameboton);

            }
        } catch (Exception e) {
            evemen.mensaje_error(e, sql, titulo);
        }
    }

    private void crear_boton_tabla3(String textboton, String nameboton) {
        JButton boton = new JButton(textboton);
        boton.setFont(new Font("Arial", Font.BOLD, 12));
        boton.setName(nameboton);
        panel_referencia_tabla3.add(boton);
        botones_tabla3.add(boton);
        cant_boton_tabla3++;
        boton.addActionListener(new ActionListener() {
            private String idlab_grupo_estudio;

            @Override
            public void actionPerformed(ActionEvent e) {
                for (int p = 0; p < cant_boton_tabla3; p++) {
                    botones_tabla3.get(p).setBackground(new java.awt.Color(255, 255, 255));
                }
                ((JButton) e.getSource()).setBackground(new java.awt.Color(153, 153, 255));
                idlab_grupo_estudio = ((JButton) e.getSource()).getName();
                cargar_boton_tabla6(idlab_grupo_estudio);
                carga_tabla_lab_estudio(tbllab_estudio, "lge.idlab_grupo_estudio", idlab_grupo_estudio);
            }
        });
        panel_referencia_tabla3.updateUI();
    }

    public void ancho_tabla_lab_estudio(JTable tblbuscar) {
        int Ancho[] = {10, 5, 60, 15, 10};
        eveJtab.setAnchoColumnaJtable(tblbuscar, Ancho);
    }

    private void cargar_boton_tabla6(String idlab_grupo_estudio) {
        String titulo = "cargar_boton_tabla6";
        panel_referencia_tabla6.removeAll();
        botones_tabla6.clear();
        panel_referencia_tabla6.updateUI();
        cant_boton_unid = 0;
        String sql = "select lge2.idlab_grupo_estudio,lge2.nombre,lge2.orden \n"
                + "from public.lab_grupo_estudio lge,public.item_lab_estudio ile,public.lab_estudio le,\n"
                + "public.lab_grupo_estudio lge2,public.item_lab_estudio ile2\n"
                + "where lge.idlab_grupo_estudio=ile.fk_idlab_grupo_estudio\n"
                + "and ile.fk_idlab_estudio=le.idlab_estudio\n"
                + "and lge.idlab_grupo_estudio=" + idlab_grupo_estudio + "\n"
                + " and lge.fk_idlab_grupo=" + tbl_or.getIdtabla_area() + "\n"
                + " and le.idlab_estudio=ile2.fk_idlab_estudio\n"
                + "and ile2.fk_idlab_grupo_estudio=lge2.idlab_grupo_estudio\n"
                + "and lge2.fk_idlab_grupo=" + tbl_or.getIdtabla_panel() + "\n"
                + " group by 1,2,3 order by 3 desc ";
        try {
            ResultSet rs = eveconn.getResulsetSQL(conn, sql, titulo);
            while (rs.next()) {
                String textboton = rs.getString("nombre");
                String nameboton = rs.getString("idlab_grupo_estudio");
                boton_agregar_tabla6(textboton, nameboton);
            }
        } catch (Exception e) {
            evemen.mensaje_error(e, sql, titulo);
        }
    }

    private void boton_agregar_tabla6(String textboton, String nameboton) {
        JButton boton = new JButton(textboton);
        boton.setFont(new Font("Arial", Font.BOLD, 10));
        boton.setName(nameboton);
        panel_referencia_tabla6.add(boton);
        botones_tabla6.add(boton);
        cant_boton_unid++;
        boton.addActionListener(new ActionListener() {
            private String idlab_grupo_estudio;

            @Override
            public void actionPerformed(ActionEvent e) {
                for (int p = 0; p < cant_boton_unid; p++) {
                    botones_tabla6.get(p).setBackground(new java.awt.Color(255, 255, 255));
                }
                ((JButton) e.getSource()).setBackground(new java.awt.Color(153, 153, 255));
                idlab_grupo_estudio = ((JButton) e.getSource()).getName();
                System.out.println("idlab_grupo_estudio: " + idlab_grupo_estudio);
                carga_tabla_lab_estudio(tbllab_estudio, "le.fk_idlab_grupo_estudio", idlab_grupo_estudio);
                tbllab_estudio.requestFocus();
                tbllab_estudio.changeSelection(0, 0, false, false);
                if (tbllab_estudio.getRowCount() == 1) {
                    cargar_item_orden_lab();

                    txtbuscar_nombre_estudio.requestFocus();
                }
            }
        });
        panel_referencia_tabla6.updateUI();
    }

    private void cargar_item_orden_lab() {
        String idlab_estudio = eveJtab.getString_select(tbllab_estudio, 0);
        String idg = eveJtab.getString_select(tbllab_estudio, 1);
        String nombre_completo = eveJtab.getString_select(tbllab_estudio, 2);
        String unidad = eveJtab.getString_select(tbllab_estudio, 3);
        Object dato[] = {idlab_estudio, idg, nombre_completo, unidad, false};
        model_item_orden_lab = (DefaultTableModel) tblitem_orden_lab.getModel();
        model_item_orden_lab.addRow(dato);
        txtbuscar_nombre_estudio.setText(null);
    }

    private void carga_tabla_lab_estudio(JTable tblbuscar, String campo, String idlab_grupo_estudio) {
        String sql = "select le.idlab_estudio as idle,le.fk_idlab_grupo_estudio as idg,"
                + "le.nombre_completo as ncompleto,lun.nombre as unid,\n"
                + "case \n"
                + "when le.es_numerico=true then 'nro'\n"
                + "when le.es_testo=true then 'tes'\n"
                + "when le.es_predefinido then 'pre'\n"
                + "else 'otro' end tipo "
                + "from public.lab_grupo_estudio lge,public.item_lab_estudio ile,public.lab_estudio le,public.lab_unidad lun\n"
                + "where lge.idlab_grupo_estudio=ile.fk_idlab_grupo_estudio\n"
                + "and ile.fk_idlab_estudio=le.idlab_estudio\n"
                + "and le.fk_idlab_unidad=lun.idlab_unidad\n"
                + "and " + campo + "=" + idlab_grupo_estudio + "\n"
                + " and lge.fk_idlab_grupo=" + tbl_or.getIdtabla_area() + "\n"
                + "order by ile.orden desc ";
        eveconn.Select_cargar_jtable(conn, sql, tblbuscar);
        ancho_tabla_lab_estudio(tblbuscar);
    }

    private void buscar_tabla_lab_estudio(JTable tblbuscar) {
        if (txtbuscar_nombre_estudio.getText().trim().length() > 0) {
            String buscar = txtbuscar_nombre_estudio.getText();
            String sql = "select le.idlab_estudio as idle,le.fk_idlab_grupo_estudio as idg,"
                    + "le.nombre_completo as ncompleto,lun.nombre as unid,\n"
                    + "case \n"
                    + "when le.es_numerico=true then 'nro'\n"
                    + "when le.es_testo=true then 'tes'\n"
                    + "when le.es_predefinido then 'pre'\n"
                    + "else 'otro' end tipo "
                    + "from public.lab_grupo_estudio lge,public.item_lab_estudio ile,public.lab_estudio le,public.lab_unidad lun\n"
                    + "where lge.idlab_grupo_estudio=ile.fk_idlab_grupo_estudio\n"
                    + "and ile.fk_idlab_estudio=le.idlab_estudio\n"
                    + "and le.fk_idlab_unidad=lun.idlab_unidad\n"
                    + "and le.nombre_completo ilike'%" + buscar + "%' \n"
                    + " and lge.fk_idlab_grupo=" + tbl_or.getIdtabla_area() + "\n"
                    + "order by ile.orden desc ";
            eveconn.Select_cargar_jtable(conn, sql, tblbuscar);
            ancho_tabla_lab_estudio(tblbuscar);
        }
    }

    private void eliminar_item_orden_lab() {
        eveJtab.getBoolean_Eliminar_Fila(tblitem_orden_lab, model_item_orden_lab);
    }

    private boolean validar_guardar_orden_lab() {
        if (evejtf.getBoo_JTextField_vacio(txtnro_documento, "NO SE CARGO NINGUNA NUMERO DE DOCUMENTO")) {
            return false;
        }
        if (evejtf.getBoo_JTextField_vacio(txtpaciente_nombre, "NO SE CARGO NINGUNA NOMBRE DE PACIENTE")) {
            return false;
        }
        if (evejtf.getBoo_JTextField_vacio(txtbuscar_medico, "NO SE CARGO NINGUNA NOMBRE DE MEDICO")) {
            return false;
        }
        if (txtpersona_id.getText().trim().length() > 0) {
            fk_idpersona1 = Integer.parseInt(txtpersona_id.getText());
        }
        if (txtmedico_idpersona.getText().trim().length() > 0) {
            fk_idpersona2 = Integer.parseInt(txtmedico_idpersona.getText());
        }
        fk_idpersona3 = 52066;
        if (fk_idpersona1 == 0) {
            JOptionPane.showMessageDialog(null, "NO SE CARGO NINGUN PACIENTE");
            return false;
        }
        if (fk_idpersona2 == 0) {
            JOptionPane.showMessageDialog(null, "NO SE CARGO NINGUN MEDICO");
            return false;
        }
        if (evejtf.getBoo_JTextField_vacio(txtvisacion, "NO SE CARGO NINGUNA VISACION")) {
            return false;
        }
        if (evejtf.getBoo_JTextField_vacio(txtobservacion, "NO SE CARGO NINGUNA OBSERVACION")) {
            return false;
        }
        if (eveJtab.getBoolean_validar_cant_cargado(tblitem_orden_lab)) {
            return false;
        }
        if (jCorden_lugar.getSelectedIndex() == 0) {
            JOptionPane.showMessageDialog(jCorden_lugar, "NO SE CARGO NINGUN LUGAR");
            return false;
        }
        return true;
    }

    private void boton_guardar_orden() {
        if (validar_guardar_orden_lab()) {
            ordl.setC4estado(tbl_or.getEstado_pedido());
            ordl.setC5observacion(txtobservacion.getText());
            ordl.setC6visacion(Integer.parseInt(txtvisacion.getText()));
            ordl.setC7fk_idpersona1(fk_idpersona1);
            ordl.setC8fk_idpersona2(fk_idpersona2);
            ordl.setC9fk_idpersona3(fk_idpersona3);
            ordl.setC10fk_idorden_lugar(fk_idorden_lugar);
            ordl.setC11fk_idusuario(fk_idusuario);
            ordl.setC12nro_documento(Integer.parseInt(txtnro_documento.getText()));
            ordBo.insertar_orden_lab(ordl, tblitem_orden_lab);
            ordDao.actualizar_tabla_orden_lab(conn, tblorden_lab,"");
            reestableser_orden();
        }
    }

    private void seleccionar_orden() {
        if (tblorden_lab.getSelectedRow() >= 0) {
            //String idorden_lab = eveJtab.getString_select(tblorden_lab, 0);
            int idorden_lab = eveJtab.getInt_select_id(tblorden_lab);
            iolDao.actualizar_tabla_item_orden_lab_pedido(conn, tblitem_orden_lab_pedido, idorden_lab);
        }
    }

    private void buscar_orden(KeyEvent evt) {
        if (evt.getKeyCode() == KeyEvent.VK_ENTER) {
            if (txtnro_documento.getText().trim().length() > 0) {
                String documentno = txtnro_documento.getText();
                buscar_orden_idempiere(documentno);
                buscar_paciente_idempiere(documentno);
            }
        }
    }

    private void buscar_orden_idempiere(String documentno) {
        String titulo = "buscar_orden_idempiere";
        String sql = "select col.m_product_id,mp.name as servicio,mp.sku as sku\n"
                + "from c_orderline col,m_product mp,c_order co \n"
                + "where co.c_order_id=col.c_order_id \n"
                + "and col.m_product_id=mp.m_product_id \n"
                + "and co.documentno='" + documentno + "' "
                + "order by col.c_orderline_id asc;";
        try {
            ResultSet rs = eveconn.getResulsetSQL(connSer, sql, titulo);
            cant_estu_ser = 0;
            cant_estu_local = 0;
            suma_estudio_ser = "";
            eveJtab.limpiar_tabla_datos(model_item_orden_lab);
            while (rs.next()) {
                cant_estu_ser++;
                String servicio = rs.getString("servicio");
                String sku = rs.getString("sku");
                System.out.println("servicio: " + servicio + " sku: " + sku);
                suma_estudio_ser = suma_estudio_ser + "(" + sku + "):" + servicio;
                buscar_estudio_por_sku(sku);
            }
            if (cant_estu_ser == 0) {
                JOptionPane.showMessageDialog(null, "NO SE ENCONTRO NINGUN REGISTRO");
            } else if(cant_estu_ser == cant_estu_local){
                JOptionPane.showMessageDialog(null, "CANTIDAD DE SERVICIO ENCONTRADO:"
                        + "\nCant. Ser:" + cant_estu_ser
                        + "\nCant. Local:" + cant_estu_local
                        + "\n" + suma_estudio_ser);
            }else if(cant_estu_ser > cant_estu_local){
                JOptionPane.showMessageDialog(null, "NO SE ENCONTRO TODOS LOS ESTUDIOS:"+(cant_estu_ser-cant_estu_local)
                        + "\nCant. Ser:" + cant_estu_ser
                        + "\nCant. Local:" + cant_estu_local
                        + "\n" + suma_estudio_ser,"ERROR",JOptionPane.ERROR_MESSAGE);
            }else {
                JOptionPane.showMessageDialog(null, "NINGUN PROCESO ENCONTRADO");
            }
        } catch (Exception e) {
            evemen.mensaje_error(e, sql, titulo);
        }
    }

    private void buscar_paciente_idempiere(String documentno) {
        String titulo = "buscar_paciente_idempiere";
        String sql = "select co.documentno,cb.\"name\" as paciente, cb.taxid as cedula \n"
                + "from c_order co,c_bpartner cb \n"
                + "where co.c_bpartner_id=cb.c_bpartner_id \n"
                + "and co.documentno='" + documentno + "' ";
        try {
            ResultSet rs = eveconn.getResulsetSQL(connSer, sql, titulo);
            int cant = 0;
            if (rs.next()) {
                cant++;
                String paciente = rs.getString("paciente");
                String cedula = rs.getString("cedula");
                txtpaciente_cedula.setText(cedula);
                buscar_persona(2);
            }
            if (cant == 0) {
                JOptionPane.showMessageDialog(null, "NO SE ENCONTRO NINGUN PACIENTE");
            }
        } catch (Exception e) {
            evemen.mensaje_error(e, sql, titulo);
        }
    }

    private void buscar_estudio_por_sku(String sku) {
        String titulo = "buscar_estudio_por_sku";
        String sql = "select le.idlab_estudio as idle,le.fk_idlab_grupo_estudio as idg,le.es_estudio,\n"
                + "le.nombre_completo as ncompleto,lun.nombre as unid\n"
                + "from public.lab_estudio le,public.lab_unidad lun\n"
                + "where le.fk_idlab_unidad=lun.idlab_unidad\n"
                + "and le.sku=" + sku;
        try {
            ResultSet rs = eveconn.getResulsetSQL(conn, sql, titulo);
            if (rs.next()) {
                cant_estu_local++;
                String idlab_estudio = rs.getString("idle");
                String idg = rs.getString("idg");
                String nombre_completo = rs.getString("ncompleto");
                String unidad = rs.getString("unid");
                boolean es_estudio = rs.getBoolean("es_estudio");
                if (es_estudio) {
                    Object dato[] = {idlab_estudio, idg, nombre_completo, unidad, false};
                    model_item_orden_lab = (DefaultTableModel) tblitem_orden_lab.getModel();
                    model_item_orden_lab.addRow(dato);
                }
                suma_estudio_ser = suma_estudio_ser + " ==>>OK\n";
            }else{
                suma_estudio_ser = suma_estudio_ser + " ==>>NO\n";
            }
        } catch (Exception e) {
            evemen.mensaje_error(e, sql, titulo);
        }
    }

    public FrmOrden_lab_pedido() {
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

        jTabbedPane1 = new javax.swing.JTabbedPane();
        jPanel1 = new javax.swing.JPanel();
        jScrollPane3 = new javax.swing.JScrollPane();
        panel_referencia_tabla3 = new javax.swing.JPanel();
        panel_referencia_tabla6 = new javax.swing.JPanel();
        jPanel2 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        txtbuscar_nombre_estudio = new javax.swing.JTextField();
        jScrollPane1 = new javax.swing.JScrollPane();
        tbllab_estudio = new javax.swing.JTable();
        jPanel3 = new javax.swing.JPanel();
        jLabel2 = new javax.swing.JLabel();
        txtpaciente_nombre = new javax.swing.JTextField();
        jLabel3 = new javax.swing.JLabel();
        txtpaciente_cedula = new javax.swing.JTextField();
        jLabel4 = new javax.swing.JLabel();
        txtpaciente_tipo_seguro = new javax.swing.JTextField();
        jLabel5 = new javax.swing.JLabel();
        txtpaciente_plan_seguro = new javax.swing.JTextField();
        btnnuevo_persona = new javax.swing.JButton();
        btnbuscar_persona = new javax.swing.JButton();
        txtpersona_id = new javax.swing.JTextField();
        btnlimpiar_persona = new javax.swing.JButton();
        jLabel6 = new javax.swing.JLabel();
        txtbuscar_medico = new javax.swing.JTextField();
        jLabel7 = new javax.swing.JLabel();
        txtbuscar_registro_medico = new javax.swing.JTextField();
        jLabel8 = new javax.swing.JLabel();
        jCorden_lugar = new javax.swing.JComboBox<>();
        jPanel4 = new javax.swing.JPanel();
        jScrollPane2 = new javax.swing.JScrollPane();
        tblitem_orden_lab = new javax.swing.JTable();
        btneliminar_item = new javax.swing.JButton();
        btnguardar_orden = new javax.swing.JButton();
        btncancelar_orden = new javax.swing.JButton();
        jLabel9 = new javax.swing.JLabel();
        txtobservacion = new javax.swing.JTextField();
        jLabel10 = new javax.swing.JLabel();
        txtvisacion = new javax.swing.JTextField();
        txtmedico_idpersona = new javax.swing.JTextField();
        jLabel11 = new javax.swing.JLabel();
        txtnro_documento = new javax.swing.JTextField();
        jPanel5 = new javax.swing.JPanel();
        jScrollPane4 = new javax.swing.JScrollPane();
        tblorden_lab = new javax.swing.JTable();
        jScrollPane5 = new javax.swing.JScrollPane();
        tblitem_orden_lab_pedido = new javax.swing.JTable();

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

        panel_referencia_tabla3.setBackground(new java.awt.Color(102, 153, 255));
        panel_referencia_tabla3.setBorder(javax.swing.BorderFactory.createEtchedBorder());
        panel_referencia_tabla3.setLayout(new java.awt.GridLayout(0, 1));
        jScrollPane3.setViewportView(panel_referencia_tabla3);

        panel_referencia_tabla6.setBackground(new java.awt.Color(102, 153, 255));
        panel_referencia_tabla6.setBorder(javax.swing.BorderFactory.createEtchedBorder());
        panel_referencia_tabla6.setLayout(new java.awt.GridLayout(0, 1));

        jPanel2.setBorder(javax.swing.BorderFactory.createTitledBorder("ESTUDIOS"));

        jLabel1.setText("NOMBRE:");

        txtbuscar_nombre_estudio.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                txtbuscar_nombre_estudioKeyPressed(evt);
            }
            public void keyReleased(java.awt.event.KeyEvent evt) {
                txtbuscar_nombre_estudioKeyReleased(evt);
            }
        });

        tbllab_estudio.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "idest", "nombre_c", "unid", "select"
            }
        ) {
            Class[] types = new Class [] {
                java.lang.Object.class, java.lang.Object.class, java.lang.Object.class, java.lang.Boolean.class
            };

            public Class getColumnClass(int columnIndex) {
                return types [columnIndex];
            }
        });
        tbllab_estudio.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                tbllab_estudioKeyPressed(evt);
            }
        });
        jScrollPane1.setViewportView(tbllab_estudio);

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel1)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(txtbuscar_nombre_estudio, javax.swing.GroupLayout.PREFERRED_SIZE, 350, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
            .addComponent(jScrollPane1, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 539, javax.swing.GroupLayout.PREFERRED_SIZE)
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel1)
                    .addComponent(txtbuscar_nombre_estudio, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 252, Short.MAX_VALUE))
        );

        jPanel3.setBorder(javax.swing.BorderFactory.createTitledBorder("PACIENTE"));

        jLabel2.setText("NOMBRE:");

        txtpaciente_nombre.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                txtpaciente_nombreKeyPressed(evt);
            }
        });

        jLabel3.setText("CEDULA:");

        txtpaciente_cedula.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                txtpaciente_cedulaKeyPressed(evt);
            }
        });

        jLabel4.setText("SEGURO:");

        txtpaciente_tipo_seguro.setEditable(false);
        txtpaciente_tipo_seguro.setBackground(new java.awt.Color(204, 204, 204));

        jLabel5.setText("PLAN:");

        txtpaciente_plan_seguro.setEditable(false);
        txtpaciente_plan_seguro.setBackground(new java.awt.Color(204, 204, 204));

        btnnuevo_persona.setText("NUEVO");

        btnbuscar_persona.setText("BUSCAR");
        btnbuscar_persona.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnbuscar_personaActionPerformed(evt);
            }
        });

        txtpersona_id.setEditable(false);
        txtpersona_id.setBackground(new java.awt.Color(204, 204, 204));

        btnlimpiar_persona.setText("LIMPIAR");
        btnlimpiar_persona.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnlimpiar_personaActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel4)
                    .addComponent(jLabel3)
                    .addComponent(jLabel2)
                    .addComponent(jLabel5))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(txtpaciente_plan_seguro)
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addComponent(txtpersona_id, javax.swing.GroupLayout.PREFERRED_SIZE, 46, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(txtpaciente_nombre))
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(txtpaciente_cedula, javax.swing.GroupLayout.PREFERRED_SIZE, 89, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGroup(jPanel3Layout.createSequentialGroup()
                                .addComponent(txtpaciente_tipo_seguro, javax.swing.GroupLayout.PREFERRED_SIZE, 144, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(btnnuevo_persona)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(btnbuscar_persona)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(btnlimpiar_persona)))
                        .addGap(0, 93, Short.MAX_VALUE)))
                .addContainerGap())
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel2)
                    .addComponent(txtpaciente_nombre, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txtpersona_id, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel3)
                    .addComponent(txtpaciente_cedula, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel4)
                    .addComponent(txtpaciente_tipo_seguro, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnnuevo_persona)
                    .addComponent(btnbuscar_persona)
                    .addComponent(btnlimpiar_persona))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(txtpaciente_plan_seguro, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel5))
                .addGap(0, 2, Short.MAX_VALUE))
        );

        jLabel6.setText("Medico:");

        txtbuscar_medico.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtbuscar_medicoActionPerformed(evt);
            }
        });
        txtbuscar_medico.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                txtbuscar_medicoKeyPressed(evt);
            }
        });

        jLabel7.setText("Registro:");

        jLabel8.setText("Lugar Pedico:");

        jCorden_lugar.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));
        jCorden_lugar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jCorden_lugarActionPerformed(evt);
            }
        });

        jPanel4.setBorder(javax.swing.BorderFactory.createTitledBorder("ESTUDIOS SELECCIONADOS"));

        tblitem_orden_lab.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "idest", "idg", "nombre_c", "unid", "cbt"
            }
        ) {
            Class[] types = new Class [] {
                java.lang.Object.class, java.lang.Object.class, java.lang.Object.class, java.lang.Object.class, java.lang.Boolean.class
            };

            public Class getColumnClass(int columnIndex) {
                return types [columnIndex];
            }
        });
        jScrollPane2.setViewportView(tblitem_orden_lab);

        btneliminar_item.setText("ELIMINAR ITEM");
        btneliminar_item.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btneliminar_itemActionPerformed(evt);
            }
        });

        btnguardar_orden.setText("GUARDAR ORDEN");
        btnguardar_orden.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnguardar_ordenActionPerformed(evt);
            }
        });

        btncancelar_orden.setText("CANCELAR ORDEN");
        btncancelar_orden.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btncancelar_ordenActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel4Layout = new javax.swing.GroupLayout(jPanel4);
        jPanel4.setLayout(jPanel4Layout);
        jPanel4Layout.setHorizontalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addComponent(btneliminar_item)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(btncancelar_orden)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(btnguardar_orden, javax.swing.GroupLayout.PREFERRED_SIZE, 167, javax.swing.GroupLayout.PREFERRED_SIZE))
            .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addComponent(jScrollPane2, javax.swing.GroupLayout.DEFAULT_SIZE, 559, Short.MAX_VALUE))
        );
        jPanel4Layout.setVerticalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel4Layout.createSequentialGroup()
                .addGap(0, 267, Short.MAX_VALUE)
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btneliminar_item)
                    .addComponent(btnguardar_orden)
                    .addComponent(btncancelar_orden)))
            .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(jPanel4Layout.createSequentialGroup()
                    .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 262, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGap(0, 28, Short.MAX_VALUE)))
        );

        jLabel9.setText("Observacion:");

        jLabel10.setText("Visacion:");

        txtmedico_idpersona.setEditable(false);
        txtmedico_idpersona.setBackground(new java.awt.Color(204, 204, 204));

        jLabel11.setText("Nro DOCUMENTO IDEMPIERE:");

        txtnro_documento.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                txtnro_documentoKeyPressed(evt);
            }
        });

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addComponent(jScrollPane3, javax.swing.GroupLayout.PREFERRED_SIZE, 176, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                        .addGroup(jPanel1Layout.createSequentialGroup()
                            .addComponent(jLabel9)
                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                            .addComponent(txtobservacion))
                        .addComponent(panel_referencia_tabla6, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(jPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(jLabel11)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(txtnro_documento, javax.swing.GroupLayout.PREFERRED_SIZE, 148, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jPanel3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addComponent(jLabel8)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jCorden_lugar, javax.swing.GroupLayout.PREFERRED_SIZE, 183, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jLabel10)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(txtvisacion))
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addComponent(jLabel6)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(txtmedico_idpersona, javax.swing.GroupLayout.PREFERRED_SIZE, 51, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(txtbuscar_medico)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jLabel7)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(txtbuscar_registro_medico, javax.swing.GroupLayout.PREFERRED_SIZE, 75, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addComponent(jPanel4, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                        .addContainerGap())))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                        .addGroup(javax.swing.GroupLayout.Alignment.LEADING, jPanel1Layout.createSequentialGroup()
                            .addGap(7, 7, 7)
                            .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                .addComponent(jLabel11)
                                .addComponent(txtnro_documento, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                            .addComponent(panel_referencia_tabla6, javax.swing.GroupLayout.PREFERRED_SIZE, 112, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                            .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                            .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                .addComponent(jLabel9)
                                .addComponent(txtobservacion, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addGroup(jPanel1Layout.createSequentialGroup()
                            .addComponent(jPanel3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                            .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.CENTER)
                                .addComponent(jLabel6)
                                .addComponent(txtbuscar_medico, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addComponent(jLabel7)
                                .addComponent(txtbuscar_registro_medico, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addComponent(txtmedico_idpersona, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                            .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                .addComponent(jLabel8)
                                .addComponent(jCorden_lugar, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addComponent(jLabel10)
                                .addComponent(txtvisacion, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                            .addComponent(jPanel4, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addComponent(jScrollPane3, javax.swing.GroupLayout.PREFERRED_SIZE, 558, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(31, Short.MAX_VALUE))
        );

        jTabbedPane1.addTab("ORDEN PEDIDO", jPanel1);

        tblorden_lab.setModel(new javax.swing.table.DefaultTableModel(
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
        tblorden_lab.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseReleased(java.awt.event.MouseEvent evt) {
                tblorden_labMouseReleased(evt);
            }
        });
        jScrollPane4.setViewportView(tblorden_lab);

        tblitem_orden_lab_pedido.setModel(new javax.swing.table.DefaultTableModel(
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
        jScrollPane5.setViewportView(tblitem_orden_lab_pedido);

        javax.swing.GroupLayout jPanel5Layout = new javax.swing.GroupLayout(jPanel5);
        jPanel5.setLayout(jPanel5Layout);
        jPanel5Layout.setHorizontalGroup(
            jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jScrollPane4, javax.swing.GroupLayout.DEFAULT_SIZE, 1289, Short.MAX_VALUE)
            .addGroup(jPanel5Layout.createSequentialGroup()
                .addComponent(jScrollPane5, javax.swing.GroupLayout.PREFERRED_SIZE, 1000, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, Short.MAX_VALUE))
        );
        jPanel5Layout.setVerticalGroup(
            jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel5Layout.createSequentialGroup()
                .addComponent(jScrollPane4, javax.swing.GroupLayout.PREFERRED_SIZE, 196, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane5, javax.swing.GroupLayout.PREFERRED_SIZE, 355, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 32, Short.MAX_VALUE))
        );

        jTabbedPane1.addTab("FILTRO ORDEN PEDIDO", jPanel5);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jTabbedPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 1294, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(jTabbedPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 617, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void formInternalFrameOpened(javax.swing.event.InternalFrameEvent evt) {//GEN-FIRST:event_formInternalFrameOpened
        // TODO add your handling code here:
        ancho_tabla_lab_estudio(tbllab_estudio);
        ancho_tabla_lab_estudio(tblitem_orden_lab);
        ordDao.ancho_tabla_orden_lab(tblorden_lab);
    }//GEN-LAST:event_formInternalFrameOpened

    private void jCorden_lugarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jCorden_lugarActionPerformed
        // TODO add your handling code here:
        if (hab_orden_lugar) {
            fk_idorden_lugar = comb.getInt_seleccionar_COMBOBOX(conn, jCorden_lugar, "idorden_lugar", "nombre", "public.orden_lugar");
        }
    }//GEN-LAST:event_jCorden_lugarActionPerformed

    private void btnbuscar_personaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnbuscar_personaActionPerformed
        // TODO add your handling code here:
        pers.setSta_tipo_persona(pers.getSta_tipo_pers_paciente());
        JDBuscar_persona frm = new JDBuscar_persona(null, true);
        frm.setVisible(true);
    }//GEN-LAST:event_btnbuscar_personaActionPerformed

    private void txtpaciente_cedulaKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtpaciente_cedulaKeyPressed
        // TODO add your handling code here:
        if (evt.getKeyCode() == KeyEvent.VK_ENTER) {
            buscar_persona(2);
        }
    }//GEN-LAST:event_txtpaciente_cedulaKeyPressed

    private void txtpaciente_nombreKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtpaciente_nombreKeyPressed
        // TODO add your handling code here:
        if (evt.getKeyCode() == KeyEvent.VK_ENTER) {
            buscar_persona(1);
        }
    }//GEN-LAST:event_txtpaciente_nombreKeyPressed

    private void txtbuscar_medicoKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtbuscar_medicoKeyPressed
        // TODO add your handling code here:
        if (evt.getKeyCode() == KeyEvent.VK_ENTER) {
            buscar_persona(3);
        }
    }//GEN-LAST:event_txtbuscar_medicoKeyPressed

    private void txtbuscar_nombre_estudioKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtbuscar_nombre_estudioKeyReleased
        // TODO add your handling code here:
        buscar_tabla_lab_estudio(tbllab_estudio);
    }//GEN-LAST:event_txtbuscar_nombre_estudioKeyReleased

    private void txtbuscar_nombre_estudioKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtbuscar_nombre_estudioKeyPressed
        // TODO add your handling code here:
        eveJtab.seleccionar_tabla_flecha_abajo(evt, tbllab_estudio);

    }//GEN-LAST:event_txtbuscar_nombre_estudioKeyPressed

    private void tbllab_estudioKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_tbllab_estudioKeyPressed
        // TODO add your handling code here:
        if (evt.getKeyCode() == KeyEvent.VK_ENTER) {
            cargar_item_orden_lab();
        }
        eveJtab.saltar_a_campo_enter(evt, tbllab_estudio, txtbuscar_nombre_estudio);
    }//GEN-LAST:event_tbllab_estudioKeyPressed

    private void btneliminar_itemActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btneliminar_itemActionPerformed
        // TODO add your handling code here:
        eliminar_item_orden_lab();
    }//GEN-LAST:event_btneliminar_itemActionPerformed

    private void txtbuscar_medicoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtbuscar_medicoActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtbuscar_medicoActionPerformed

    private void btnlimpiar_personaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnlimpiar_personaActionPerformed
        // TODO add your handling code here:
        limpiar_paciente_uno();
    }//GEN-LAST:event_btnlimpiar_personaActionPerformed

    private void btncancelar_ordenActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btncancelar_ordenActionPerformed
        // TODO add your handling code here:
        if (evemen.MensajeGeneral_warning("ESTAS SEGURO DE CANCELAR LA ORDEN PEDIDO", "CANCELAR PEDIDO", "ACEPTAR", "NO CANCELAR")) {
            reestableser_orden();
        }
    }//GEN-LAST:event_btncancelar_ordenActionPerformed

    private void btnguardar_ordenActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnguardar_ordenActionPerformed
        // TODO add your handling code here:
        boton_guardar_orden();
    }//GEN-LAST:event_btnguardar_ordenActionPerformed

    private void tblorden_labMouseReleased(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tblorden_labMouseReleased
        // TODO add your handling code here:
        seleccionar_orden();
    }//GEN-LAST:event_tblorden_labMouseReleased

    private void txtnro_documentoKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtnro_documentoKeyPressed
        // TODO add your handling code here:
        buscar_orden(evt);
    }//GEN-LAST:event_txtnro_documentoKeyPressed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnbuscar_persona;
    private javax.swing.JButton btncancelar_orden;
    private javax.swing.JButton btneliminar_item;
    private javax.swing.JButton btnguardar_orden;
    private javax.swing.JButton btnlimpiar_persona;
    private javax.swing.JButton btnnuevo_persona;
    private javax.swing.JComboBox<String> jCorden_lugar;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JPanel jPanel4;
    private javax.swing.JPanel jPanel5;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JScrollPane jScrollPane3;
    private javax.swing.JScrollPane jScrollPane4;
    private javax.swing.JScrollPane jScrollPane5;
    private javax.swing.JTabbedPane jTabbedPane1;
    private javax.swing.JPanel panel_referencia_tabla3;
    private javax.swing.JPanel panel_referencia_tabla6;
    private javax.swing.JTable tblitem_orden_lab;
    private javax.swing.JTable tblitem_orden_lab_pedido;
    private javax.swing.JTable tbllab_estudio;
    private javax.swing.JTable tblorden_lab;
    public static javax.swing.JTextField txtbuscar_medico;
    private javax.swing.JTextField txtbuscar_nombre_estudio;
    public static javax.swing.JTextField txtbuscar_registro_medico;
    public static javax.swing.JTextField txtmedico_idpersona;
    public static javax.swing.JTextField txtnro_documento;
    private javax.swing.JTextField txtobservacion;
    public static javax.swing.JTextField txtpaciente_cedula;
    public static javax.swing.JTextField txtpaciente_nombre;
    public static javax.swing.JTextField txtpaciente_plan_seguro;
    public static javax.swing.JTextField txtpaciente_tipo_seguro;
    public static javax.swing.JTextField txtpersona_id;
    public static javax.swing.JTextField txtvisacion;
    // End of variables declaration//GEN-END:variables
}
