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
import FORMULARIO.BO.*;
import FORMULARIO.DAO.*;
import FORMULARIO.ENTIDAD.*;
import java.awt.event.KeyEvent;
import java.sql.Connection;
import javax.swing.JList;
import javax.swing.JOptionPane;
import javax.swing.JTextField;

/**
 *
 * @author Digno
 */
public class FrmLab_estudio extends javax.swing.JInternalFrame {

    EvenConexion eveconn = new EvenConexion();
    EvenJFRAME evetbl = new EvenJFRAME();
    EvenJtable eveJtab = new EvenJtable();
    EvenJTextField evejtf = new EvenJTextField();
    EvenCombobox comb = new EvenCombobox();
    Connection conn = ConnPostgres.getConnPosgres();
    cla_color_pelete clacolor = new cla_color_pelete();
    private lab_estudio estu = new lab_estudio();
    private BO_lab_estudio esBO = new BO_lab_estudio();
    private DAO_lab_estudio esdao = new DAO_lab_estudio();
    private lab_unidad unid = new lab_unidad();
    private BO_lab_unidad uBO = new BO_lab_unidad();
    private DAO_lab_unidad udao = new DAO_lab_unidad();
    private lab_grupo lgru = new lab_grupo();
    private BO_lab_grupo lgruBO = new BO_lab_grupo();
    private DAO_lab_grupo lgrudao = new DAO_lab_grupo();
    private lab_grupo_estudio lgrue = new lab_grupo_estudio();
    private BO_lab_grupo_estudio lgrueBO = new BO_lab_grupo_estudio();
    private DAO_lab_grupo_estudio lgruedao = new DAO_lab_grupo_estudio();
    private item_lab_estudio iles = new item_lab_estudio();
    private BO_item_lab_estudio ilesBO = new BO_item_lab_estudio();
    private DAO_item_lab_estudio ilesdao = new DAO_item_lab_estudio();
    private item_lab_estudio_predefinido ilesp = new item_lab_estudio_predefinido();
    private BO_item_lab_estudio_predefinido ilespBO = new BO_item_lab_estudio_predefinido();
    private DAO_item_lab_estudio_predefinido ilespDAO = new DAO_item_lab_estudio_predefinido();
    private tabla_orden_lab tbl_or = new tabla_orden_lab();
    private int fk_idlab_unidad;
    boolean hab_unidad = false;
    private boolean buscar_tabla_1 = false;
    private boolean buscar_tabla_2 = false;
    private boolean buscar_tabla_3 = false;
    private boolean buscar_tabla_4 = false;
    private boolean buscar_tabla_5 = false;
    private boolean buscar_tabla_6 = false;
    private boolean buscar_tabla_7 = false;

    private int idlab_grupo_estudio_1 = 0;
    private int idlab_grupo_estudio_2 = 0;
    private int idlab_grupo_estudio_3 = 0;
    private int idlab_grupo_estudio_4 = 0;
    private int idlab_grupo_estudio_5 = 0;
    private int idlab_grupo_estudio_6 = 0;
    private int idlab_grupo_estudio_7 = 0;
    private int idlab_estudio;
//    private int idlab_unidad;
    private boolean buscar_tabla_predefinido;
    private int idlab_estudio_predefinido;
    private boolean buscar_tabla_unidad = false;
    private boolean buscar_tabla_panel;
    private int idlab_grupo_estudio_panel;

    private void abrir_formulario() {
        this.setTitle("LAB ESTUDIO");
        evetbl.centrar_formulario_internalframa(this);
        reestableser();
        esdao.actualizar_tabla_lab_estudio(conn, tbllab, "");
        color_formulario();
        cargar_lab_grupo();
    }

    private void buscar_estudio(JTextField txtbuscar, int fk_idlab_grupo) {
        String buscar = txtbuscar.getText();
        String filtro = " and (select lge.nombre as gru_valor from item_lab_estudio ile1,lab_grupo_estudio lge\n"
                + "where ile1.fk_idlab_grupo_estudio=lge.idlab_grupo_estudio\n"
                + "and lge.fk_idlab_grupo="+fk_idlab_grupo
                + " and ile1.fk_idlab_estudio=les.idlab_estudio) ilike'%" + buscar + "%' \n";
        esdao.actualizar_tabla_lab_estudio(conn, tbllab, filtro);
    }

    private void boton_guardar_item_lab_estudio() {
        String suma_grupo_guardado = "";
        if (idlab_grupo_estudio_1 > 0) {
            iles.setC2orden(0);
            iles.setC3fk_idlab_estudio(idlab_estudio);
            iles.setC4fk_idlab_grupo_estudio(idlab_grupo_estudio_1);
            ilesBO.insertar_item_lab_estudio(iles);
            idlab_grupo_estudio_1 = 0;
            suma_grupo_guardado = suma_grupo_guardado + txttabla_departamento.getText() + "\n";
        }
        if (idlab_grupo_estudio_2 > 0) {
            iles.setC2orden(0);
            iles.setC3fk_idlab_estudio(idlab_estudio);
            iles.setC4fk_idlab_grupo_estudio(idlab_grupo_estudio_2);
            ilesBO.insertar_item_lab_estudio(iles);
            idlab_grupo_estudio_2 = 0;
            suma_grupo_guardado = suma_grupo_guardado + txttabla_area.getText() + "\n";
        }
        if (idlab_grupo_estudio_3 > 0) {
            iles.setC2orden(0);
            iles.setC3fk_idlab_estudio(idlab_estudio);
            iles.setC4fk_idlab_grupo_estudio(idlab_grupo_estudio_3);
            ilesBO.insertar_item_lab_estudio(iles);
            idlab_grupo_estudio_3 = 0;
            suma_grupo_guardado = suma_grupo_guardado + txttabla_seccion.getText() + "\n";
        }
        if (idlab_grupo_estudio_4 > 0) {
            iles.setC2orden(0);
            iles.setC3fk_idlab_estudio(idlab_estudio);
            iles.setC4fk_idlab_grupo_estudio(idlab_grupo_estudio_4);
            ilesBO.insertar_item_lab_estudio(iles);
            idlab_grupo_estudio_4 = 0;
            suma_grupo_guardado = suma_grupo_guardado + txttabla_grupovalor.getText() + "\n";
        }
        if (idlab_grupo_estudio_5 > 0) {
            iles.setC2orden(0);
            iles.setC3fk_idlab_estudio(idlab_estudio);
            iles.setC4fk_idlab_grupo_estudio(idlab_grupo_estudio_5);
            ilesBO.insertar_item_lab_estudio(iles);
            idlab_grupo_estudio_5 = 0;
            suma_grupo_guardado = suma_grupo_guardado + txttabla_panel.getText() + "\n";
        }
        if (idlab_grupo_estudio_6 > 0) {
            iles.setC2orden(0);
            iles.setC3fk_idlab_estudio(idlab_estudio);
            iles.setC4fk_idlab_grupo_estudio(idlab_grupo_estudio_6);
            ilesBO.insertar_item_lab_estudio(iles);
            idlab_grupo_estudio_6 = 0;
            suma_grupo_guardado = suma_grupo_guardado + txttabla_muestra.getText() + "\n";
        }
        if (idlab_grupo_estudio_7 > 0) {
            iles.setC2orden(0);
            iles.setC3fk_idlab_estudio(idlab_estudio);
            iles.setC4fk_idlab_grupo_estudio(idlab_grupo_estudio_7);
            ilesBO.insertar_item_lab_estudio(iles);
            idlab_grupo_estudio_7 = 0;
            suma_grupo_guardado = suma_grupo_guardado + txttabla_metodo.getText() + "\n";
        }
        JOptionPane.showMessageDialog(null, "GRUPOS DE ESTUDIOS GRADADOS\n" + suma_grupo_guardado);
        esdao.actualizar_tabla_lab_estudio(conn, tbllab, "");
    }

    private void cargar_lab_grupo() {
        lgrudao.cargar_lab_grupo(conn, lgru, tbl_or.getIdtabla_departamento());
        lbltabla_1.setText(lgru.getC2nombre() + ":");
        lgrudao.cargar_lab_grupo(conn, lgru, tbl_or.getIdtabla_area());
        lbltabla_2.setText(lgru.getC2nombre() + ":");
        lgrudao.cargar_lab_grupo(conn, lgru, tbl_or.getIdtabla_seccion());
        lbltabla_3.setText(lgru.getC2nombre() + ":");
        lgrudao.cargar_lab_grupo(conn, lgru, tbl_or.getIdtabla_grupo_valor());
        lbltabla_4.setText(lgru.getC2nombre() + ":");
        lgrudao.cargar_lab_grupo(conn, lgru, tbl_or.getIdtabla_panel());
        lbltabla_5.setText(lgru.getC2nombre() + ":");
        lgrudao.cargar_lab_grupo(conn, lgru, tbl_or.getIdtabla_muestra());
        lbltabla_6.setText(lgru.getC2nombre() + ":");
        lgrudao.cargar_lab_grupo(conn, lgru, tbl_or.getIdtabla_metodo());
        lbltabla_7.setText(lgru.getC2nombre() + ":");
    }

//    private void cargar_unidad() {
//        comb.cargarCombobox(conn, jCUnidad, "idlab_unidad", "nombre", "lab_unidad", "");
//        hab_unidad = true;
//    }
    private void color_formulario() {
        panel_tabla.setBackground(clacolor.getColor_tabla());
        panel_insertar.setBackground(clacolor.getColor_insertar_primario());
    }

    private boolean validar_guardar() {
        if (evejtf.getBoo_JTextField_vacio(txtnombre_completo, "DEBE CARGAR UN NOMBRE LARGO")) {
            return false;
        }
        if (evejtf.getBoo_JTextField_vacio(txtnombre_corto, "DEBE CARGAR UN NOMBRE CORTO")) {
            return false;
        }
        if (evejtf.getBoo_JTextarea_vacio(txtAvalor_de_referencia, "DEBE CARGAR UN VALOR DE REFERENCIA")) {
            return false;
        }
        if (fk_idlab_unidad == 0) {
            JOptionPane.showMessageDialog(null, "SELECCIONE UNA UNIDAD");
            return false;
        }
        return true;
    }

    private int getDecimal() {
        int deci = 0;
        if (jRdeci_0.isSelected()) {
            deci = 0;
        }
        if (jRdeci_1.isSelected()) {
            deci = 1;
        }
        if (jRdeci_2.isSelected()) {
            deci = 2;
        }
        return deci;
    }

    private void cargar_decimal(int deci) {
        if (deci == 0) {
            jRdeci_0.setSelected(true);
            jRdeci_1.setSelected(false);
            jRdeci_2.setSelected(false);
        }
        if (deci == 1) {
            jRdeci_0.setSelected(false);
            jRdeci_1.setSelected(true);
            jRdeci_2.setSelected(false);
        }
        if (deci == 2) {
            jRdeci_0.setSelected(false);
            jRdeci_1.setSelected(false);
            jRdeci_2.setSelected(true);
        }
    }

    private void boton_guardar() {
        if (validar_guardar()) {
            estu.setC2nombre_completo(txtnombre_completo.getText());
            estu.setC3nombre_corto(txtnombre_corto.getText());
            estu.setC4numerico_decimal(getDecimal());
            estu.setC5es_numerico(jRes_numerico.isSelected());
            estu.setC6es_testo(jRes_testo.isSelected());
            estu.setC7es_predefinido(jRes_predefinido.isSelected());
            estu.setC8es_validado(jCes_validado.isSelected());
            estu.setC9valor_de_referencia(txtAvalor_de_referencia.getText());
            estu.setC10fk_idlab_unidad(fk_idlab_unidad);
            estu.setC11fk_idlab_grupo_estudio(idlab_grupo_estudio_panel);
            esBO.insertar_lab_estudio(estu, tbllab);
            reestableser();
            esdao.actualizar_tabla_lab_estudio(conn, tbllab, "");
        }
    }

    private void boton_editar() {
        if (validar_guardar()) {
            estu.setC1idlab_estudio(Integer.parseInt(txtid.getText()));
            estu.setC2nombre_completo(txtnombre_completo.getText());
            estu.setC3nombre_corto(txtnombre_corto.getText());
            estu.setC4numerico_decimal(getDecimal());
            estu.setC5es_numerico(jRes_numerico.isSelected());
            estu.setC6es_testo(jRes_testo.isSelected());
            estu.setC7es_predefinido(jRes_predefinido.isSelected());
            estu.setC8es_validado(jCes_validado.isSelected());
            estu.setC9valor_de_referencia(txtAvalor_de_referencia.getText());
            estu.setC10fk_idlab_unidad(fk_idlab_unidad);
            estu.setC11fk_idlab_grupo_estudio(idlab_grupo_estudio_panel);
            esBO.update_lab_estudio(estu, tbllab);
            reestableser();
            esdao.actualizar_tabla_lab_estudio(conn, tbllab, "");
        }
    }

    private void seleccionar_tabla_lab_estudio() {
        idlab_estudio = eveJtab.getInt_select_id(tbllab);
        esdao.cargar_lab_estudio(conn, estu, idlab_estudio);
        txtid.setText(String.valueOf(estu.getC1idlab_estudio()));
        txtnombre_completo.setText(estu.getC2nombre_completo());
        txtnombre_corto.setText(estu.getC3nombre_corto());
        int deci = estu.getC4numerico_decimal();
        cargar_decimal(deci);
        jRes_numerico.setSelected(estu.getC5es_numerico());
        jRes_testo.setSelected(estu.getC6es_testo());
        jRes_predefinido.setSelected(estu.getC7es_predefinido());
        jCes_validado.setSelected(estu.getC8es_validado());
        bloquear_predefinido(estu.getC7es_predefinido());
        ilespDAO.actualizar_tabla_item_lab_estudio_predefinido(conn, tbllab_item_estudio_predefinido, idlab_estudio);
        udao.cargar_lab_unidad(conn, unid, estu.getC10fk_idlab_unidad());
        fk_idlab_unidad = unid.getC1idlab_unidad();
        txtbuscar_unidad.setText("(" + unid.getC1idlab_unidad() + ")-" + unid.getC2nombre());
        txtAvalor_de_referencia.setText(estu.getC9valor_de_referencia());
        if (estu.getC11fk_idlab_grupo_estudio() > 0) {
            lgruedao.cargar_lab_grupo_estudio(conn, lgrue, estu.getC11fk_idlab_grupo_estudio());
            txtbuscar_panel.setText("(" + lgrue.getC1idlab_grupo_estudio() + ")-" + lgrue.getC2nombre());
        }
        cargar_grupo_seleccion(txttabla_departamento, idlab_estudio, tbl_or.getIdtabla_departamento());
        cargar_grupo_seleccion(txttabla_area, idlab_estudio, tbl_or.getIdtabla_area());
        cargar_grupo_seleccion(txttabla_seccion, idlab_estudio, tbl_or.getIdtabla_seccion());
        cargar_grupo_seleccion(txttabla_grupovalor, idlab_estudio, tbl_or.getIdtabla_grupo_valor());
        cargar_grupo_seleccion(txttabla_panel, idlab_estudio, tbl_or.getIdtabla_panel());
        idlab_grupo_estudio_panel = getIntcargar_grupo_seleccion(idlab_estudio, tbl_or.getIdtabla_panel());
        cargar_grupo_seleccion(txttabla_muestra, idlab_estudio, tbl_or.getIdtabla_muestra());
        cargar_grupo_seleccion(txttabla_metodo, idlab_estudio, tbl_or.getIdtabla_metodo());

        btnguardar.setEnabled(false);
        btneditar.setEnabled(true);
    }

    private void bloquear_predefinido(boolean blo) {
        txtnombre_predefinido.setEnabled(blo);
        txtorden_predefinido.setEnabled(blo);
        tbllab_item_estudio_predefinido.setEnabled(blo);
        btneliminar_predefinido.setEnabled(blo);
    }

    void cargar_grupo_seleccion(JTextField txttabla, int idlab_estudio, int idtabla_1) {
        String sql = "select (lges.nombre||'-('||lg.nombre||')') as grupo \n"
                + "from item_lab_estudio iles,lab_grupo_estudio lges,lab_grupo lg\n"
                + "where iles.fk_idlab_grupo_estudio=lges.idlab_grupo_estudio\n"
                + "and lges.fk_idlab_grupo=lg.idlab_grupo\n"
                + "and lg.idlab_grupo=" + idtabla_1
                + " and iles.fk_idlab_estudio=" + idlab_estudio;
        txttabla.setText(eveconn.getString_filtro_sql(conn, sql));

    }

    private int getIntcargar_grupo_seleccion(int idlab_estudio, int idtabla_1) {
        int id = 0;
        String sql2 = "select iles.fk_idlab_grupo_estudio as grupo \n"
                + "from item_lab_estudio iles,lab_grupo_estudio lges,lab_grupo lg\n"
                + "where iles.fk_idlab_grupo_estudio=lges.idlab_grupo_estudio\n"
                + "and lges.fk_idlab_grupo=lg.idlab_grupo\n"
                + "and lg.idlab_grupo=" + idtabla_1
                + " and iles.fk_idlab_estudio=" + idlab_estudio;
        id = (eveconn.getInt_filtro_sql(conn, sql2));
        return id;
    }

    private void reestableser() {
        txtid.setText(null);
        txtnombre_completo.setText(null);
        txtnombre_corto.setText(null);
        txtAvalor_de_referencia.setText(null);
        txtorden_predefinido.setText("1");
        jRdeci_0.setSelected(true);
        jRes_numerico.setSelected(true);
        fk_idlab_unidad = 0;
        idlab_grupo_estudio_panel = 0;
        txtbuscar_unidad.setText(null);
        jLista_aux.setVisible(false);
        jLista_predefinido.setVisible(false);
        jLista_unidad.setVisible(false);
        jLista_panel.setVisible(false);
        bloquear_predefinido(false);
        btnguardar.setEnabled(true);
        btneditar.setEnabled(false);
        btndeletar.setEnabled(false);
        bloquear_predefinido(false);
        ilespDAO.actualizar_tabla_item_lab_estudio_predefinido(conn, tbllab_item_estudio_predefinido, 0);
        txtnombre_completo.grabFocus();
    }

    private int cargar_idtabla(JTextField txttabla, JList jLista) {
        int idlab_grupo_estudio = eveconn.getInt_seleccionar_JLista(conn, txttabla, jLista, false,
                "lab_grupo_estudio ges,lab_grupo gru",
                "ges.fk_idlab_grupo=gru.idlab_grupo and concat(ges.nombre,'-(',gru.nombre,')')",
                "(ges.nombre||'-('||gru.nombre||')') as grupo_estudio", "grupo_estudio", "idlab_grupo_estudio");
        return idlab_grupo_estudio;
    }

    private void buscar_cargar_Jlista(JTextField txttabla, JList jLista, int idtabla) {
        eveconn.buscar_cargar_Jlista(conn, txttabla, jLista, "lab_grupo_estudio ges,lab_grupo gru",
                "ges.fk_idlab_grupo=gru.idlab_grupo and ges.fk_idlab_grupo=" + idtabla + " and ges.nombre",
                "(ges.nombre||'-('||gru.nombre||')') as nombre", 5);
    }

    private void buscar_cargar_Jlista_predefinido() {
        eveconn.buscar_cargar_Jlista(conn, txtnombre_predefinido, jLista_predefinido, "lab_estudio_predefinido",
                "nombre",
                "(idlab_estudio_predefinido||'-('||nombre||')') as nombre", 5);
    }

    private void buscar_cargar_Jlista_unidad() {
        eveconn.buscar_cargar_Jlista(conn, txtbuscar_unidad, jLista_unidad, "lab_unidad",
                "nombre",
                "(idlab_unidad||'-('||nombre||')') as nombre", 5);
    }

    private int cargar_idtabla_predefinido() {
        int idlab_estudio_predefinido = eveconn.getInt_seleccionar_JLista(conn, txtnombre_predefinido, jLista_predefinido, false,
                "lab_estudio_predefinido",
                "concat(idlab_estudio_predefinido,'-(',nombre,')')",
                "(idlab_estudio_predefinido||'-('||nombre||')') as nombre", "nombre", "idlab_estudio_predefinido");
        return idlab_estudio_predefinido;
    }

    private int cargar_idtabla_unidad() {
        int idlab_unidad = eveconn.getInt_seleccionar_JLista(conn, txtbuscar_unidad, jLista_unidad, false,
                "lab_unidad",
                "concat(idlab_unidad,'-(',nombre,')')",
                "(idlab_unidad||'-('||nombre||')') as nombre", "nombre", "idlab_unidad");
        return idlab_unidad;
    }

    private void cargar_idtabla_panel() {
        idlab_grupo_estudio_panel = cargar_idtabla(txtbuscar_panel, jLista_panel);
        buscar_tabla_panel = false;
//        evejtf.saltar_campo_directo(txttabla_departamento, txttabla_area);
    }

    private void cargar_idtabla_1() {
        idlab_grupo_estudio_1 = cargar_idtabla(txttabla_departamento, jLista_aux);
        buscar_tabla_1 = false;
        evejtf.saltar_campo_directo(txttabla_departamento, txttabla_area);
    }

    private void cargar_idtabla_2() {
        idlab_grupo_estudio_2 = cargar_idtabla(txttabla_area, jLista_aux);
        buscar_tabla_2 = false;
        evejtf.saltar_campo_directo(txttabla_area, txttabla_seccion);
    }

    private void cargar_idtabla_3() {
        idlab_grupo_estudio_3 = cargar_idtabla(txttabla_seccion, jLista_aux);
        buscar_tabla_3 = false;
        evejtf.saltar_campo_directo(txttabla_seccion, txttabla_grupovalor);
    }

    private void cargar_idtabla_4() {
        idlab_grupo_estudio_4 = cargar_idtabla(txttabla_grupovalor, jLista_aux);
        buscar_tabla_4 = false;
        evejtf.saltar_campo_directo(txttabla_grupovalor, txttabla_panel);
    }

    private void cargar_idtabla_5() {
        idlab_grupo_estudio_5 = cargar_idtabla(txttabla_panel, jLista_aux);
        buscar_tabla_5 = false;
        evejtf.saltar_campo_directo(txttabla_panel, txttabla_muestra);
    }

    private void cargar_idtabla_6() {
        idlab_grupo_estudio_6 = cargar_idtabla(txttabla_muestra, jLista_aux);
        buscar_tabla_6 = false;
        evejtf.saltar_campo_directo(txttabla_muestra, txttabla_metodo);
    }

    private void cargar_idtabla_7() {
        idlab_grupo_estudio_7 = cargar_idtabla(txttabla_metodo, jLista_aux);
        buscar_tabla_7 = false;
        evejtf.saltar_campo_directo(txttabla_metodo, txttabla_departamento);
    }

    private void guardar_item_lab_estudio_predefinido() {
        if (txtorden_predefinido.getText().trim().length() > 0) {

            int orden = Integer.parseInt(txtorden_predefinido.getText());
            ilesp.setC2orden(orden);
            ilesp.setC3fk_idlab_estudio_predefinido(idlab_estudio_predefinido);
            ilesp.setC4fk_idlab_estudio(idlab_estudio);
            ilespBO.insertar_item_lab_estudio_predefinido(ilesp);
            ilespDAO.actualizar_tabla_item_lab_estudio_predefinido(conn, tbllab_item_estudio_predefinido, idlab_estudio);
            txtnombre_predefinido.setText(null);
            String Sorden = String.valueOf(eveconn.getInt_SumarOrden_mas_uno(conn, "item_lab_estudio_predefinido", "orden", "fk_idlab_estudio", idlab_estudio));
            txtorden_predefinido.setText(Sorden);
//            txtorden_predefinido.setText("0");
            txtnombre_predefinido.requestFocus();
        }
    }

    private void boton_nuevo() {
        reestableser();
    }

    public FrmLab_estudio() {
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

        gru_tDato = new javax.swing.ButtonGroup();
        gru_deci = new javax.swing.ButtonGroup();
        jTabbedPane1 = new javax.swing.JTabbedPane();
        panel_insertar = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        txtid = new javax.swing.JTextField();
        jLabel2 = new javax.swing.JLabel();
        txtnombre_completo = new javax.swing.JTextField();
        btnnuevo = new javax.swing.JButton();
        btnguardar = new javax.swing.JButton();
        btneditar = new javax.swing.JButton();
        btndeletar = new javax.swing.JButton();
        jLabel3 = new javax.swing.JLabel();
        txtnombre_corto = new javax.swing.JTextField();
        jPanel1 = new javax.swing.JPanel();
        jRes_numerico = new javax.swing.JRadioButton();
        jRes_testo = new javax.swing.JRadioButton();
        jRes_predefinido = new javax.swing.JRadioButton();
        jPanel2 = new javax.swing.JPanel();
        jScrollPane2 = new javax.swing.JScrollPane();
        txtAvalor_de_referencia = new javax.swing.JTextArea();
        jPanel3 = new javax.swing.JPanel();
        jScrollPane3 = new javax.swing.JScrollPane();
        tbllab_item_estudio_predefinido = new javax.swing.JTable();
        txtnombre_predefinido = new javax.swing.JTextField();
        jLista_predefinido = new javax.swing.JList<>();
        jLabel4 = new javax.swing.JLabel();
        jLabel6 = new javax.swing.JLabel();
        txtorden_predefinido = new javax.swing.JTextField();
        btneliminar_predefinido = new javax.swing.JButton();
        jPanel4 = new javax.swing.JPanel();
        jRdeci_0 = new javax.swing.JRadioButton();
        jRdeci_1 = new javax.swing.JRadioButton();
        jRdeci_2 = new javax.swing.JRadioButton();
        jPanel5 = new javax.swing.JPanel();
        jScrollPane4 = new javax.swing.JScrollPane();
        tbllab2 = new javax.swing.JTable();
        txtnombre5 = new javax.swing.JTextField();
        jCes_validado = new javax.swing.JCheckBox();
        jPanel7 = new javax.swing.JPanel();
        jLista_unidad = new javax.swing.JList<>();
        txtbuscar_unidad = new javax.swing.JTextField();
        jPanel8 = new javax.swing.JPanel();
        jLista_panel = new javax.swing.JList<>();
        txtbuscar_panel = new javax.swing.JTextField();
        panel_tabla = new javax.swing.JPanel();
        jScrollPane1 = new javax.swing.JScrollPane();
        tbllab = new javax.swing.JTable();
        jPanel6 = new javax.swing.JPanel();
        lbltabla_1 = new javax.swing.JLabel();
        txttabla_departamento = new javax.swing.JTextField();
        lbltabla_2 = new javax.swing.JLabel();
        txttabla_area = new javax.swing.JTextField();
        lbltabla_3 = new javax.swing.JLabel();
        txttabla_seccion = new javax.swing.JTextField();
        lbltabla_4 = new javax.swing.JLabel();
        txttabla_grupovalor = new javax.swing.JTextField();
        lbltabla_5 = new javax.swing.JLabel();
        txttabla_panel = new javax.swing.JTextField();
        lbltabla_6 = new javax.swing.JLabel();
        txttabla_muestra = new javax.swing.JTextField();
        lbltabla_7 = new javax.swing.JLabel();
        txttabla_metodo = new javax.swing.JTextField();
        jLista_aux = new javax.swing.JList<>();
        btnguardar_relaciones = new javax.swing.JButton();
        jPanel9 = new javax.swing.JPanel();
        jLabel5 = new javax.swing.JLabel();
        txtbuscar_area = new javax.swing.JTextField();
        jLabel7 = new javax.swing.JLabel();
        txtbuscar_seccion = new javax.swing.JTextField();
        jLabel8 = new javax.swing.JLabel();
        txtbuscar_grupovalor = new javax.swing.JTextField();
        jLabel9 = new javax.swing.JLabel();
        txtbuscar_estudio = new javax.swing.JTextField();

        setClosable(true);
        setIconifiable(true);
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

        panel_insertar.setBackground(new java.awt.Color(153, 204, 255));
        panel_insertar.setBorder(javax.swing.BorderFactory.createTitledBorder("CREAR DATO"));

        jLabel1.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jLabel1.setText("ID:");

        txtid.setEditable(false);
        txtid.setBackground(new java.awt.Color(204, 204, 204));
        txtid.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N

        jLabel2.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jLabel2.setText("NOM. COMPLETO:");

        txtnombre_completo.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        txtnombre_completo.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                txtnombre_completoKeyPressed(evt);
            }
        });

        btnnuevo.setIcon(new javax.swing.ImageIcon(getClass().getResource("/iconos/ABM/nuevo.png"))); // NOI18N
        btnnuevo.setText("NUEVO");
        btnnuevo.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        btnnuevo.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        btnnuevo.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnnuevoActionPerformed(evt);
            }
        });

        btnguardar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/iconos/ABM/guardar.png"))); // NOI18N
        btnguardar.setText("GUARDAR");
        btnguardar.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        btnguardar.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        btnguardar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnguardarActionPerformed(evt);
            }
        });

        btneditar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/iconos/ABM/modificar.png"))); // NOI18N
        btneditar.setText("EDITAR");
        btneditar.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        btneditar.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        btneditar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btneditarActionPerformed(evt);
            }
        });

        btndeletar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/iconos/ABM/eliminar.png"))); // NOI18N
        btndeletar.setText("DELETAR");
        btndeletar.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        btndeletar.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        btndeletar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btndeletarActionPerformed(evt);
            }
        });

        jLabel3.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jLabel3.setText("NOM. CORTO:");

        txtnombre_corto.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        txtnombre_corto.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                txtnombre_cortoKeyPressed(evt);
            }
        });

        jPanel1.setBorder(javax.swing.BorderFactory.createTitledBorder("Tipo de Dato"));

        gru_tDato.add(jRes_numerico);
        jRes_numerico.setText("NUMERICO");

        gru_tDato.add(jRes_testo);
        jRes_testo.setSelected(true);
        jRes_testo.setText("TESTO");

        gru_tDato.add(jRes_predefinido);
        jRes_predefinido.setText("PREDEFINIDO");

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jRes_numerico)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jRes_testo)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jRes_predefinido))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                .addComponent(jRes_numerico)
                .addComponent(jRes_testo)
                .addComponent(jRes_predefinido))
        );

        jPanel2.setBorder(javax.swing.BorderFactory.createTitledBorder("VALOR DE REFERENCIA"));

        txtAvalor_de_referencia.setColumns(20);
        txtAvalor_de_referencia.setRows(5);
        jScrollPane2.setViewportView(txtAvalor_de_referencia);

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jScrollPane2, javax.swing.GroupLayout.DEFAULT_SIZE, 412, Short.MAX_VALUE)
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jScrollPane2, javax.swing.GroupLayout.DEFAULT_SIZE, 274, Short.MAX_VALUE)
        );

        jPanel3.setBorder(javax.swing.BorderFactory.createTitledBorder("VALOR PREDEFINIDO"));

        tbllab_item_estudio_predefinido.setModel(new javax.swing.table.DefaultTableModel(
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
        tbllab_item_estudio_predefinido.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseReleased(java.awt.event.MouseEvent evt) {
                tbllab_item_estudio_predefinidoMouseReleased(evt);
            }
        });
        jScrollPane3.setViewportView(tbllab_item_estudio_predefinido);

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

        jLabel4.setText("NOM:");

        jLabel6.setText("ORD:");

        txtorden_predefinido.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        txtorden_predefinido.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                txtorden_predefinidoKeyPressed(evt);
            }
        });

        btneliminar_predefinido.setText("ELIMINAR");

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jScrollPane3, javax.swing.GroupLayout.DEFAULT_SIZE, 353, Short.MAX_VALUE)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel3Layout.createSequentialGroup()
                .addComponent(jLabel4)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(txtnombre_predefinido)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel6)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(txtorden_predefinido, javax.swing.GroupLayout.PREFERRED_SIZE, 55, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addComponent(btneliminar_predefinido)
                .addGap(0, 0, Short.MAX_VALUE))
            .addComponent(jLista_predefinido, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel3Layout.createSequentialGroup()
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(txtnombre_predefinido, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(jLabel4))
                    .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(txtorden_predefinido, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(jLabel6)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLista_predefinido, javax.swing.GroupLayout.PREFERRED_SIZE, 86, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane3, javax.swing.GroupLayout.DEFAULT_SIZE, 205, Short.MAX_VALUE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(btneliminar_predefinido))
        );

        jPanel4.setBorder(javax.swing.BorderFactory.createTitledBorder("Numero Decimal"));

        gru_deci.add(jRdeci_0);
        jRdeci_0.setSelected(true);
        jRdeci_0.setText("Ninguno");

        gru_deci.add(jRdeci_1);
        jRdeci_1.setText("0.9");

        gru_deci.add(jRdeci_2);
        jRdeci_2.setText("0.99");

        javax.swing.GroupLayout jPanel4Layout = new javax.swing.GroupLayout(jPanel4);
        jPanel4.setLayout(jPanel4Layout);
        jPanel4Layout.setHorizontalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jRdeci_0)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jRdeci_1)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jRdeci_2))
        );
        jPanel4Layout.setVerticalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                .addComponent(jRdeci_0)
                .addComponent(jRdeci_1)
                .addComponent(jRdeci_2))
        );

        jPanel5.setBorder(javax.swing.BorderFactory.createTitledBorder("VALIDAR ESTUDIO"));

        tbllab2.setModel(new javax.swing.table.DefaultTableModel(
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
        tbllab2.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseReleased(java.awt.event.MouseEvent evt) {
                tbllab2MouseReleased(evt);
            }
        });
        jScrollPane4.setViewportView(tbllab2);

        txtnombre5.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        txtnombre5.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                txtnombre5KeyPressed(evt);
            }
        });

        jCes_validado.setText("HABILITAR VALIDACION");

        javax.swing.GroupLayout jPanel5Layout = new javax.swing.GroupLayout(jPanel5);
        jPanel5.setLayout(jPanel5Layout);
        jPanel5Layout.setHorizontalGroup(
            jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jScrollPane4, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE)
            .addGroup(jPanel5Layout.createSequentialGroup()
                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(txtnombre5, javax.swing.GroupLayout.PREFERRED_SIZE, 97, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jCes_validado))
                .addGap(0, 186, Short.MAX_VALUE))
        );
        jPanel5Layout.setVerticalGroup(
            jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel5Layout.createSequentialGroup()
                .addComponent(jCes_validado)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(txtnombre5, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jScrollPane4, javax.swing.GroupLayout.PREFERRED_SIZE, 266, javax.swing.GroupLayout.PREFERRED_SIZE))
        );

        jPanel7.setBorder(javax.swing.BorderFactory.createTitledBorder("UNIDAD"));

        jLista_unidad.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jLista_unidad.setSelectionBackground(new java.awt.Color(255, 0, 0));
        jLista_unidad.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                jLista_unidadKeyPressed(evt);
            }
        });

        txtbuscar_unidad.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                txtbuscar_unidadKeyPressed(evt);
            }
            public void keyReleased(java.awt.event.KeyEvent evt) {
                txtbuscar_unidadKeyReleased(evt);
            }
        });

        javax.swing.GroupLayout jPanel7Layout = new javax.swing.GroupLayout(jPanel7);
        jPanel7.setLayout(jPanel7Layout);
        jPanel7Layout.setHorizontalGroup(
            jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(txtbuscar_unidad, javax.swing.GroupLayout.DEFAULT_SIZE, 187, Short.MAX_VALUE)
            .addGroup(jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addComponent(jLista_unidad, javax.swing.GroupLayout.DEFAULT_SIZE, 187, Short.MAX_VALUE))
        );
        jPanel7Layout.setVerticalGroup(
            jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel7Layout.createSequentialGroup()
                .addComponent(txtbuscar_unidad, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, Short.MAX_VALUE))
            .addGroup(jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel7Layout.createSequentialGroup()
                    .addGap(0, 35, Short.MAX_VALUE)
                    .addComponent(jLista_unidad, javax.swing.GroupLayout.PREFERRED_SIZE, 86, javax.swing.GroupLayout.PREFERRED_SIZE)))
        );

        jPanel8.setBorder(javax.swing.BorderFactory.createTitledBorder("PANEL"));

        jLista_panel.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jLista_panel.setSelectionBackground(new java.awt.Color(255, 0, 0));
        jLista_panel.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                jLista_panelKeyPressed(evt);
            }
        });

        txtbuscar_panel.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                txtbuscar_panelKeyPressed(evt);
            }
            public void keyReleased(java.awt.event.KeyEvent evt) {
                txtbuscar_panelKeyReleased(evt);
            }
        });

        javax.swing.GroupLayout jPanel8Layout = new javax.swing.GroupLayout(jPanel8);
        jPanel8.setLayout(jPanel8Layout);
        jPanel8Layout.setHorizontalGroup(
            jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(txtbuscar_panel)
            .addGroup(jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addComponent(jLista_panel, javax.swing.GroupLayout.DEFAULT_SIZE, 329, Short.MAX_VALUE))
        );
        jPanel8Layout.setVerticalGroup(
            jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel8Layout.createSequentialGroup()
                .addComponent(txtbuscar_panel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, Short.MAX_VALUE))
            .addGroup(jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel8Layout.createSequentialGroup()
                    .addGap(0, 35, Short.MAX_VALUE)
                    .addComponent(jLista_panel, javax.swing.GroupLayout.PREFERRED_SIZE, 86, javax.swing.GroupLayout.PREFERRED_SIZE)))
        );

        javax.swing.GroupLayout panel_insertarLayout = new javax.swing.GroupLayout(panel_insertar);
        panel_insertar.setLayout(panel_insertarLayout);
        panel_insertarLayout.setHorizontalGroup(
            panel_insertarLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panel_insertarLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(panel_insertarLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(panel_insertarLayout.createSequentialGroup()
                        .addGroup(panel_insertarLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                            .addGroup(panel_insertarLayout.createSequentialGroup()
                                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jPanel4, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                            .addGroup(panel_insertarLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                .addGroup(panel_insertarLayout.createSequentialGroup()
                                    .addComponent(btnnuevo)
                                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                    .addComponent(btnguardar)
                                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                    .addComponent(btneditar)
                                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                    .addComponent(btndeletar))
                                .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(jPanel3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(panel_insertarLayout.createSequentialGroup()
                        .addGroup(panel_insertarLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(txtnombre_corto, javax.swing.GroupLayout.PREFERRED_SIZE, 422, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGroup(panel_insertarLayout.createSequentialGroup()
                                .addGroup(panel_insertarLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jLabel1)
                                    .addComponent(jLabel2)
                                    .addComponent(jLabel3))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addGroup(panel_insertarLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(txtid, javax.swing.GroupLayout.PREFERRED_SIZE, 58, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(txtnombre_completo, javax.swing.GroupLayout.PREFERRED_SIZE, 422, javax.swing.GroupLayout.PREFERRED_SIZE))))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(jPanel7, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(panel_insertarLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jPanel5, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jPanel8, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
        );
        panel_insertarLayout.setVerticalGroup(
            panel_insertarLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panel_insertarLayout.createSequentialGroup()
                .addGroup(panel_insertarLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addGroup(panel_insertarLayout.createSequentialGroup()
                        .addContainerGap()
                        .addGroup(panel_insertarLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel1)
                            .addComponent(txtid, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(panel_insertarLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel2)
                            .addComponent(txtnombre_completo, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(panel_insertarLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel3)
                            .addComponent(txtnombre_corto, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(panel_insertarLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jPanel4, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addComponent(jPanel7, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jPanel8, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(panel_insertarLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(panel_insertarLayout.createSequentialGroup()
                        .addComponent(jPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(panel_insertarLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(btnnuevo)
                            .addComponent(btnguardar)
                            .addComponent(btneditar)
                            .addComponent(btndeletar)))
                    .addComponent(jPanel3, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jPanel5, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
        );

        jTabbedPane1.addTab("DATOS ESTUDIO", panel_insertar);

        panel_tabla.setBackground(new java.awt.Color(51, 204, 255));
        panel_tabla.setBorder(javax.swing.BorderFactory.createTitledBorder("TABLA"));

        tbllab.setModel(new javax.swing.table.DefaultTableModel(
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
        tbllab.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseReleased(java.awt.event.MouseEvent evt) {
                tbllabMouseReleased(evt);
            }
        });
        jScrollPane1.setViewportView(tbllab);

        jPanel6.setBorder(javax.swing.BorderFactory.createTitledBorder("TABLAS RELACIONADAS"));

        lbltabla_1.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        lbltabla_1.setText("jLabel4");

        txttabla_departamento.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        txttabla_departamento.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                txttabla_departamentoKeyPressed(evt);
            }
            public void keyReleased(java.awt.event.KeyEvent evt) {
                txttabla_departamentoKeyReleased(evt);
            }
        });

        lbltabla_2.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        lbltabla_2.setText("jLabel4");

        txttabla_area.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        txttabla_area.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                txttabla_areaKeyPressed(evt);
            }
            public void keyReleased(java.awt.event.KeyEvent evt) {
                txttabla_areaKeyReleased(evt);
            }
        });

        lbltabla_3.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        lbltabla_3.setText("jLabel4");

        txttabla_seccion.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        txttabla_seccion.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                txttabla_seccionKeyPressed(evt);
            }
            public void keyReleased(java.awt.event.KeyEvent evt) {
                txttabla_seccionKeyReleased(evt);
            }
        });

        lbltabla_4.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        lbltabla_4.setText("jLabel4");

        txttabla_grupovalor.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        txttabla_grupovalor.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                txttabla_grupovalorKeyPressed(evt);
            }
            public void keyReleased(java.awt.event.KeyEvent evt) {
                txttabla_grupovalorKeyReleased(evt);
            }
        });

        lbltabla_5.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        lbltabla_5.setText("jLabel4");

        txttabla_panel.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        txttabla_panel.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                txttabla_panelKeyPressed(evt);
            }
            public void keyReleased(java.awt.event.KeyEvent evt) {
                txttabla_panelKeyReleased(evt);
            }
        });

        lbltabla_6.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        lbltabla_6.setText("jLabel4");

        txttabla_muestra.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        txttabla_muestra.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                txttabla_muestraKeyPressed(evt);
            }
            public void keyReleased(java.awt.event.KeyEvent evt) {
                txttabla_muestraKeyReleased(evt);
            }
        });

        lbltabla_7.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        lbltabla_7.setText("jLabel4");

        txttabla_metodo.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        txttabla_metodo.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                txttabla_metodoKeyPressed(evt);
            }
            public void keyReleased(java.awt.event.KeyEvent evt) {
                txttabla_metodoKeyReleased(evt);
            }
        });

        javax.swing.GroupLayout jPanel6Layout = new javax.swing.GroupLayout(jPanel6);
        jPanel6.setLayout(jPanel6Layout);
        jPanel6Layout.setHorizontalGroup(
            jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel6Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(lbltabla_2, javax.swing.GroupLayout.DEFAULT_SIZE, 124, Short.MAX_VALUE)
                    .addComponent(lbltabla_1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(lbltabla_3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(lbltabla_4, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(txttabla_departamento, javax.swing.GroupLayout.DEFAULT_SIZE, 268, Short.MAX_VALUE)
                    .addComponent(txttabla_area)
                    .addComponent(txttabla_seccion)
                    .addComponent(txttabla_grupovalor, javax.swing.GroupLayout.Alignment.TRAILING))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                    .addComponent(lbltabla_6, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, 139, Short.MAX_VALUE)
                    .addComponent(lbltabla_5, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(lbltabla_7, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                    .addComponent(txttabla_muestra, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, 250, Short.MAX_VALUE)
                    .addComponent(txttabla_panel, javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(txttabla_metodo))
                .addContainerGap())
        );
        jPanel6Layout.setVerticalGroup(
            jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel6Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(lbltabla_1)
                    .addComponent(txttabla_departamento, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(lbltabla_5)
                    .addComponent(txttabla_panel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(lbltabla_2)
                    .addComponent(txttabla_area, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txttabla_muestra, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(lbltabla_6))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(lbltabla_3)
                    .addComponent(txttabla_seccion, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txttabla_metodo, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(lbltabla_7))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(lbltabla_4)
                    .addComponent(txttabla_grupovalor, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        jLista_aux.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jLista_aux.setSelectionBackground(new java.awt.Color(255, 0, 0));
        jLista_aux.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                jLista_auxKeyPressed(evt);
            }
        });

        btnguardar_relaciones.setText("GRADAR TODAS LAS RELACIONES");
        btnguardar_relaciones.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnguardar_relacionesActionPerformed(evt);
            }
        });

        jPanel9.setBorder(javax.swing.BorderFactory.createTitledBorder("BUSCAR"));

        jLabel5.setText("AREA:");

        txtbuscar_area.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                txtbuscar_areaKeyReleased(evt);
            }
        });

        jLabel7.setText("SECCION:");

        txtbuscar_seccion.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                txtbuscar_seccionKeyReleased(evt);
            }
        });

        jLabel8.setText("GRU. VALO:");

        txtbuscar_grupovalor.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                txtbuscar_grupovalorKeyReleased(evt);
            }
        });

        jLabel9.setText("ESTUDIO:");

        javax.swing.GroupLayout jPanel9Layout = new javax.swing.GroupLayout(jPanel9);
        jPanel9.setLayout(jPanel9Layout);
        jPanel9Layout.setHorizontalGroup(
            jPanel9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel9Layout.createSequentialGroup()
                .addComponent(jLabel5)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(txtbuscar_area, javax.swing.GroupLayout.PREFERRED_SIZE, 158, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel7)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(txtbuscar_seccion, javax.swing.GroupLayout.PREFERRED_SIZE, 158, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel8)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(txtbuscar_grupovalor, javax.swing.GroupLayout.PREFERRED_SIZE, 158, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel9)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(txtbuscar_estudio, javax.swing.GroupLayout.PREFERRED_SIZE, 158, javax.swing.GroupLayout.PREFERRED_SIZE))
        );
        jPanel9Layout.setVerticalGroup(
            jPanel9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                .addComponent(jLabel5)
                .addComponent(txtbuscar_area, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGroup(jPanel9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel7)
                    .addComponent(txtbuscar_seccion, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(jPanel9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(jLabel8)
                        .addComponent(txtbuscar_grupovalor, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGroup(jPanel9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel9)
                            .addComponent(txtbuscar_estudio, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))))
        );

        javax.swing.GroupLayout panel_tablaLayout = new javax.swing.GroupLayout(panel_tabla);
        panel_tabla.setLayout(panel_tablaLayout);
        panel_tablaLayout.setHorizontalGroup(
            panel_tablaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jScrollPane1)
            .addGroup(panel_tablaLayout.createSequentialGroup()
                .addComponent(jPanel6, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(panel_tablaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLista_aux, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(btnguardar_relaciones, javax.swing.GroupLayout.DEFAULT_SIZE, 305, Short.MAX_VALUE))
                .addContainerGap())
            .addGroup(panel_tablaLayout.createSequentialGroup()
                .addComponent(jPanel9, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, Short.MAX_VALUE))
        );
        panel_tablaLayout.setVerticalGroup(
            panel_tablaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, panel_tablaLayout.createSequentialGroup()
                .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 326, Short.MAX_VALUE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanel9, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(panel_tablaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jPanel6, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, panel_tablaLayout.createSequentialGroup()
                        .addComponent(jLista_aux, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(btnguardar_relaciones))))
        );

        jTabbedPane1.addTab("FILTRO DATO", panel_tabla);

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

    private void btnguardarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnguardarActionPerformed
        // TODO add your handling code here:
        boton_guardar();
    }//GEN-LAST:event_btnguardarActionPerformed

    private void formInternalFrameOpened(javax.swing.event.InternalFrameEvent evt) {//GEN-FIRST:event_formInternalFrameOpened
        // TODO add your handling code here:
        esdao.ancho_tabla_lab_estudio(tbllab);
    }//GEN-LAST:event_formInternalFrameOpened

    private void tbllabMouseReleased(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tbllabMouseReleased
        // TODO add your handling code here:
        seleccionar_tabla_lab_estudio();
    }//GEN-LAST:event_tbllabMouseReleased

    private void btneditarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btneditarActionPerformed
        // TODO add your handling code here:
        boton_editar();
    }//GEN-LAST:event_btneditarActionPerformed

    private void btnnuevoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnnuevoActionPerformed
        // TODO add your handling code here:
        boton_nuevo();
    }//GEN-LAST:event_btnnuevoActionPerformed

    private void txtnombre_completoKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtnombre_completoKeyPressed
        // TODO add your handling code here:
//        evejtf.saltar_campo_enter(evt, txtnombre, txtprecio_venta);
    }//GEN-LAST:event_txtnombre_completoKeyPressed

    private void txtnombre_cortoKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtnombre_cortoKeyPressed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtnombre_cortoKeyPressed

    private void tbllab_item_estudio_predefinidoMouseReleased(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tbllab_item_estudio_predefinidoMouseReleased
        // TODO add your handling code here:
    }//GEN-LAST:event_tbllab_item_estudio_predefinidoMouseReleased

    private void txtnombre_predefinidoKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtnombre_predefinidoKeyPressed
        // TODO add your handling code here:
        if (evejtf.seleccionar_lista(evt, jLista_predefinido)) {
            buscar_tabla_predefinido = true;
        }
    }//GEN-LAST:event_txtnombre_predefinidoKeyPressed

    private void tbllab2MouseReleased(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tbllab2MouseReleased
        // TODO add your handling code here:
    }//GEN-LAST:event_tbllab2MouseReleased

    private void txtnombre5KeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtnombre5KeyPressed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtnombre5KeyPressed

    private void jLista_auxKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jLista_auxKeyPressed
        // TODO add your handling code here:
        if (evt.getKeyCode() == KeyEvent.VK_ENTER) {
            if (buscar_tabla_1) {
                cargar_idtabla_1();
            }
            if (buscar_tabla_2) {
                cargar_idtabla_2();
            }
            if (buscar_tabla_3) {
                cargar_idtabla_3();
            }
            if (buscar_tabla_4) {
                cargar_idtabla_4();
            }
            if (buscar_tabla_5) {
                cargar_idtabla_5();
            }
            if (buscar_tabla_6) {
                cargar_idtabla_6();
            }
            if (buscar_tabla_7) {
                cargar_idtabla_7();
            }
        }
    }//GEN-LAST:event_jLista_auxKeyPressed

    private void txttabla_departamentoKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txttabla_departamentoKeyPressed
        // TODO add your handling code here:
        if (evejtf.seleccionar_lista(evt, jLista_aux)) {
            buscar_tabla_1 = true;
        }
        evejtf.saltar_campo_enter(evt, txttabla_departamento, txttabla_area);
    }//GEN-LAST:event_txttabla_departamentoKeyPressed

    private void txttabla_departamentoKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txttabla_departamentoKeyReleased
        // TODO add your handling code here:
        buscar_cargar_Jlista(txttabla_departamento, jLista_aux, tbl_or.getIdtabla_departamento());
    }//GEN-LAST:event_txttabla_departamentoKeyReleased

    private void txttabla_areaKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txttabla_areaKeyReleased
        // TODO add your handling code here:
        buscar_cargar_Jlista(txttabla_area, jLista_aux, tbl_or.getIdtabla_area());
    }//GEN-LAST:event_txttabla_areaKeyReleased

    private void txttabla_seccionKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txttabla_seccionKeyReleased
        // TODO add your handling code here:
        buscar_cargar_Jlista(txttabla_seccion, jLista_aux, tbl_or.getIdtabla_seccion());
    }//GEN-LAST:event_txttabla_seccionKeyReleased

    private void txttabla_grupovalorKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txttabla_grupovalorKeyReleased
        // TODO add your handling code here:
        buscar_cargar_Jlista(txttabla_grupovalor, jLista_aux, tbl_or.getIdtabla_grupo_valor());
    }//GEN-LAST:event_txttabla_grupovalorKeyReleased

    private void txttabla_panelKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txttabla_panelKeyReleased
        // TODO add your handling code here:
        buscar_cargar_Jlista(txttabla_panel, jLista_aux, tbl_or.getIdtabla_panel());
    }//GEN-LAST:event_txttabla_panelKeyReleased

    private void txttabla_muestraKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txttabla_muestraKeyReleased
        // TODO add your handling code here:
        buscar_cargar_Jlista(txttabla_muestra, jLista_aux, tbl_or.getIdtabla_muestra());
    }//GEN-LAST:event_txttabla_muestraKeyReleased

    private void txttabla_metodoKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txttabla_metodoKeyReleased
        // TODO add your handling code here:
        buscar_cargar_Jlista(txttabla_metodo, jLista_aux, tbl_or.getIdtabla_metodo());
    }//GEN-LAST:event_txttabla_metodoKeyReleased

    private void txttabla_areaKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txttabla_areaKeyPressed
        // TODO add your handling code here:
        if (evejtf.seleccionar_lista(evt, jLista_aux)) {
            buscar_tabla_2 = true;
        }
        evejtf.saltar_campo_enter(evt, txttabla_area, txttabla_seccion);
    }//GEN-LAST:event_txttabla_areaKeyPressed

    private void txttabla_seccionKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txttabla_seccionKeyPressed
        // TODO add your handling code here:
        if (evejtf.seleccionar_lista(evt, jLista_aux)) {
            buscar_tabla_3 = true;
        }
        evejtf.saltar_campo_enter(evt, txttabla_seccion, txttabla_grupovalor);
    }//GEN-LAST:event_txttabla_seccionKeyPressed

    private void txttabla_grupovalorKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txttabla_grupovalorKeyPressed
        // TODO add your handling code here:
        if (evejtf.seleccionar_lista(evt, jLista_aux)) {
            buscar_tabla_4 = true;
        }
        evejtf.saltar_campo_enter(evt, txttabla_grupovalor, txttabla_panel);
    }//GEN-LAST:event_txttabla_grupovalorKeyPressed

    private void txttabla_panelKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txttabla_panelKeyPressed
        // TODO add your handling code here:
        if (evejtf.seleccionar_lista(evt, jLista_aux)) {
            buscar_tabla_5 = true;
        }
        evejtf.saltar_campo_enter(evt, txttabla_panel, txttabla_muestra);
    }//GEN-LAST:event_txttabla_panelKeyPressed

    private void txttabla_muestraKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txttabla_muestraKeyPressed
        // TODO add your handling code here:
        if (evejtf.seleccionar_lista(evt, jLista_aux)) {
            buscar_tabla_6 = true;
        }
        evejtf.saltar_campo_enter(evt, txttabla_muestra, txttabla_metodo);
    }//GEN-LAST:event_txttabla_muestraKeyPressed

    private void txttabla_metodoKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txttabla_metodoKeyPressed
        // TODO add your handling code here:
        if (evejtf.seleccionar_lista(evt, jLista_aux)) {
            buscar_tabla_7 = true;
        }
        evejtf.saltar_campo_enter(evt, txttabla_metodo, txttabla_departamento);
    }//GEN-LAST:event_txttabla_metodoKeyPressed

    private void btnguardar_relacionesActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnguardar_relacionesActionPerformed
        // TODO add your handling code here:
        boton_guardar_item_lab_estudio();
    }//GEN-LAST:event_btnguardar_relacionesActionPerformed

    private void btndeletarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btndeletarActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_btndeletarActionPerformed

    private void jLista_predefinidoKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jLista_predefinidoKeyPressed
        // TODO add your handling code here:
        if (evt.getKeyCode() == KeyEvent.VK_ENTER) {
            if (buscar_tabla_predefinido) {
                idlab_estudio_predefinido = cargar_idtabla_predefinido();
                buscar_tabla_predefinido = false;
                evejtf.saltar_campo_directo(txtnombre_predefinido, txtorden_predefinido);
            }
        }
    }//GEN-LAST:event_jLista_predefinidoKeyPressed

    private void txtnombre_predefinidoKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtnombre_predefinidoKeyReleased
        // TODO add your handling code here:
        buscar_cargar_Jlista_predefinido();
    }//GEN-LAST:event_txtnombre_predefinidoKeyReleased

    private void txtorden_predefinidoKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtorden_predefinidoKeyPressed
        // TODO add your handling code here:
        if (evt.getKeyCode() == KeyEvent.VK_ENTER) {
            guardar_item_lab_estudio_predefinido();
        }
    }//GEN-LAST:event_txtorden_predefinidoKeyPressed

    private void jLista_unidadKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jLista_unidadKeyPressed
        // TODO add your handling code here:
        if (evt.getKeyCode() == KeyEvent.VK_ENTER) {
            if (buscar_tabla_unidad) {
                fk_idlab_unidad = cargar_idtabla_unidad();
                buscar_tabla_unidad = false;
//                evejtf.saltar_campo_directo(txtnombre_predefinido, txtorden_predefinido);
            }
        }
    }//GEN-LAST:event_jLista_unidadKeyPressed

    private void txtbuscar_unidadKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtbuscar_unidadKeyPressed
        // TODO add your handling code here:
        if (evejtf.seleccionar_lista(evt, jLista_unidad)) {
            buscar_tabla_unidad = true;
        }
    }//GEN-LAST:event_txtbuscar_unidadKeyPressed

    private void txtbuscar_unidadKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtbuscar_unidadKeyReleased
        // TODO add your handling code here:
        buscar_cargar_Jlista_unidad();
    }//GEN-LAST:event_txtbuscar_unidadKeyReleased

    private void jLista_panelKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jLista_panelKeyPressed
        // TODO add your handling code here:
        if (evt.getKeyCode() == KeyEvent.VK_ENTER) {
            if (buscar_tabla_panel) {
                cargar_idtabla_panel();
            }
        }
    }//GEN-LAST:event_jLista_panelKeyPressed

    private void txtbuscar_panelKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtbuscar_panelKeyPressed
        // TODO add your handling code here:
        if (evejtf.seleccionar_lista(evt, jLista_panel)) {
            buscar_tabla_panel = true;
        }
    }//GEN-LAST:event_txtbuscar_panelKeyPressed

    private void txtbuscar_panelKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtbuscar_panelKeyReleased
        // TODO add your handling code here:
        buscar_cargar_Jlista(txtbuscar_panel, jLista_panel, tbl_or.getIdtabla_panel());
    }//GEN-LAST:event_txtbuscar_panelKeyReleased

    private void txtbuscar_areaKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtbuscar_areaKeyReleased
        // TODO add your handling code here:
        buscar_estudio(txtbuscar_area, tbl_or.getIdtabla_area());
    }//GEN-LAST:event_txtbuscar_areaKeyReleased

    private void txtbuscar_seccionKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtbuscar_seccionKeyReleased
        // TODO add your handling code here:
        buscar_estudio(txtbuscar_seccion, tbl_or.getIdtabla_seccion());
    }//GEN-LAST:event_txtbuscar_seccionKeyReleased

    private void txtbuscar_grupovalorKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtbuscar_grupovalorKeyReleased
        // TODO add your handling code here:
        buscar_estudio(txtbuscar_grupovalor, tbl_or.getIdtabla_grupo_valor());
    }//GEN-LAST:event_txtbuscar_grupovalorKeyReleased


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btndeletar;
    private javax.swing.JButton btneditar;
    private javax.swing.JButton btneliminar_predefinido;
    private javax.swing.JButton btnguardar;
    private javax.swing.JButton btnguardar_relaciones;
    private javax.swing.JButton btnnuevo;
    private javax.swing.ButtonGroup gru_deci;
    private javax.swing.ButtonGroup gru_tDato;
    private javax.swing.JCheckBox jCes_validado;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JList<String> jLista_aux;
    private javax.swing.JList<String> jLista_panel;
    private javax.swing.JList<String> jLista_predefinido;
    private javax.swing.JList<String> jLista_unidad;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JPanel jPanel4;
    private javax.swing.JPanel jPanel5;
    private javax.swing.JPanel jPanel6;
    private javax.swing.JPanel jPanel7;
    private javax.swing.JPanel jPanel8;
    private javax.swing.JPanel jPanel9;
    private javax.swing.JRadioButton jRdeci_0;
    private javax.swing.JRadioButton jRdeci_1;
    private javax.swing.JRadioButton jRdeci_2;
    private javax.swing.JRadioButton jRes_numerico;
    private javax.swing.JRadioButton jRes_predefinido;
    private javax.swing.JRadioButton jRes_testo;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JScrollPane jScrollPane3;
    private javax.swing.JScrollPane jScrollPane4;
    private javax.swing.JTabbedPane jTabbedPane1;
    private javax.swing.JLabel lbltabla_1;
    private javax.swing.JLabel lbltabla_2;
    private javax.swing.JLabel lbltabla_3;
    private javax.swing.JLabel lbltabla_4;
    private javax.swing.JLabel lbltabla_5;
    private javax.swing.JLabel lbltabla_6;
    private javax.swing.JLabel lbltabla_7;
    private javax.swing.JPanel panel_insertar;
    private javax.swing.JPanel panel_tabla;
    private javax.swing.JTable tbllab;
    private javax.swing.JTable tbllab2;
    private javax.swing.JTable tbllab_item_estudio_predefinido;
    private javax.swing.JTextArea txtAvalor_de_referencia;
    private javax.swing.JTextField txtbuscar_area;
    private javax.swing.JTextField txtbuscar_estudio;
    private javax.swing.JTextField txtbuscar_grupovalor;
    private javax.swing.JTextField txtbuscar_panel;
    private javax.swing.JTextField txtbuscar_seccion;
    private javax.swing.JTextField txtbuscar_unidad;
    private javax.swing.JTextField txtid;
    private javax.swing.JTextField txtnombre5;
    private javax.swing.JTextField txtnombre_completo;
    private javax.swing.JTextField txtnombre_corto;
    private javax.swing.JTextField txtnombre_predefinido;
    private javax.swing.JTextField txtorden_predefinido;
    private javax.swing.JTextField txttabla_area;
    private javax.swing.JTextField txttabla_departamento;
    private javax.swing.JTextField txttabla_grupovalor;
    private javax.swing.JTextField txttabla_metodo;
    private javax.swing.JTextField txttabla_muestra;
    private javax.swing.JTextField txttabla_panel;
    private javax.swing.JTextField txttabla_seccion;
    // End of variables declaration//GEN-END:variables
}
