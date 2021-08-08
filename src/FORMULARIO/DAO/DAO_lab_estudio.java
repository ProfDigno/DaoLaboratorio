package FORMULARIO.DAO;

import BASEDATO.EvenConexion;
import FORMULARIO.ENTIDAD.lab_estudio;
import Evento.JasperReport.EvenJasperReport;
import Evento.Jtable.EvenJtable;
import Evento.Mensaje.EvenMensajeJoptionpane;
import Evento.Fecha.EvenFecha;
import FORMULARIO.ENTIDAD.tabla_orden_lab;
import java.sql.ResultSet;
import java.sql.Connection;
import java.sql.PreparedStatement;
import javax.swing.JTable;

public class DAO_lab_estudio {

    EvenConexion eveconn = new EvenConexion();
    EvenJtable evejt = new EvenJtable();
    EvenJasperReport rep = new EvenJasperReport();
    EvenMensajeJoptionpane evemen = new EvenMensajeJoptionpane();
    EvenFecha evefec = new EvenFecha();
    private tabla_orden_lab tbl_or = new tabla_orden_lab();
    private String mensaje_insert = "LAB_ESTUDIO GUARDADO CORRECTAMENTE";
    private String mensaje_update = "LAB_ESTUDIO MODIFICADO CORECTAMENTE";
    private String sql_insert = "INSERT INTO lab_estudio(idlab_estudio,nombre_completo,nombre_corto,numerico_decimal,"
            + "es_numerico,es_testo,es_predefinido,es_validado,"
            + "valor_de_referencia,fk_idlab_unidad,fk_idlab_grupo_estudio) VALUES (?,?,?,?,?,?,?,?,?,?,?);";
    private String sql_update = "UPDATE lab_estudio SET nombre_completo=?,nombre_corto=?,numerico_decimal=?,"
            + "es_numerico=?,es_testo=?,es_predefinido=?,es_validado=?,"
            + "valor_de_referencia=?,fk_idlab_unidad=?,fk_idlab_grupo_estudio=? WHERE idlab_estudio=?;";
    private String sql_select = "select les.idlab_estudio as idest,les.nombre_completo as nom_completo,\n"
            + "les.nombre_corto as nom_corto,lun.nombre as unid \n"
            + "from lab_estudio les,lab_unidad lun\n"
            + "where les.fk_idlab_unidad=lun.idlab_unidad\n"
            + "order by les.idlab_estudio desc;";

    private String sql_cargar = "SELECT idlab_estudio,nombre_completo,nombre_corto,numerico_decimal,"
            + "es_numerico,es_testo,es_predefinido,es_validado,"
            + "valor_de_referencia,fk_idlab_unidad,fk_idlab_grupo_estudio FROM lab_estudio WHERE idlab_estudio=";

    public void insertar_lab_estudio(Connection conn, lab_estudio lab_es) {
        lab_es.setC1idlab_estudio(eveconn.getInt_ultimoID_mas_uno(conn, lab_es.getTb_lab_estudio(), lab_es.getId_idlab_estudio()));
        String titulo = "insertar_lab_estudio";
        PreparedStatement pst = null;
        try {
            pst = conn.prepareStatement(sql_insert);
            pst.setInt(1, lab_es.getC1idlab_estudio());
            pst.setString(2, lab_es.getC2nombre_completo());
            pst.setString(3, lab_es.getC3nombre_corto());
            pst.setInt(4, lab_es.getC4numerico_decimal());
            pst.setBoolean(5, lab_es.getC5es_numerico());
            pst.setBoolean(6, lab_es.getC6es_testo());
            pst.setBoolean(7, lab_es.getC7es_predefinido());
            pst.setBoolean(8, lab_es.getC8es_validado());
            pst.setString(9, lab_es.getC9valor_de_referencia());
            pst.setInt(10, lab_es.getC10fk_idlab_unidad());
            pst.setInt(11, lab_es.getC11fk_idlab_grupo_estudio());
            pst.execute();
            pst.close();
            evemen.Imprimir_serial_sql(sql_insert + "\n" + lab_es.toString(), titulo);
            evemen.guardado_correcto(mensaje_insert, true);
        } catch (Exception e) {
            evemen.mensaje_error(e, sql_insert + "\n" + lab_es.toString(), titulo);
        }
    }

    public void update_lab_estudio(Connection conn, lab_estudio lab_es) {
        String titulo = "update_lab_estudio";
        PreparedStatement pst = null;
        try {
            pst = conn.prepareStatement(sql_update);
            pst.setString(1, lab_es.getC2nombre_completo());
            pst.setString(2, lab_es.getC3nombre_corto());
            pst.setInt(3, lab_es.getC4numerico_decimal());
            pst.setBoolean(4, lab_es.getC5es_numerico());
            pst.setBoolean(5, lab_es.getC6es_testo());
            pst.setBoolean(6, lab_es.getC7es_predefinido());
            pst.setBoolean(7, lab_es.getC8es_validado());
            pst.setString(8, lab_es.getC9valor_de_referencia());
            pst.setInt(9, lab_es.getC10fk_idlab_unidad());
            pst.setInt(10, lab_es.getC11fk_idlab_grupo_estudio());
            pst.setInt(11, lab_es.getC1idlab_estudio());
            pst.execute();
            pst.close();
            evemen.Imprimir_serial_sql(sql_update + "\n" + lab_es.toString(), titulo);
            evemen.modificado_correcto(mensaje_update, true);
        } catch (Exception e) {
            evemen.mensaje_error(e, sql_update + "\n" + lab_es.toString(), titulo);
        }
    }

    public void cargar_lab_estudio(Connection conn, lab_estudio lab_es, int id) {
        String titulo = "Cargar_lab_estudio";
        try {
            ResultSet rs = eveconn.getResulsetSQL(conn, sql_cargar + id, titulo);
            if (rs.next()) {
                lab_es.setC1idlab_estudio(rs.getInt(1));
                lab_es.setC2nombre_completo(rs.getString(2));
                lab_es.setC3nombre_corto(rs.getString(3));
                lab_es.setC4numerico_decimal(rs.getInt(4));
                lab_es.setC5es_numerico(rs.getBoolean(5));
                lab_es.setC6es_testo(rs.getBoolean(6));
                lab_es.setC7es_predefinido(rs.getBoolean(7));
                lab_es.setC8es_validado(rs.getBoolean(8));
                lab_es.setC9valor_de_referencia(rs.getString(9));
                lab_es.setC10fk_idlab_unidad(rs.getInt(10));
                lab_es.setC11fk_idlab_grupo_estudio(rs.getInt(11));
                evemen.Imprimir_serial_sql(sql_cargar + "\n" + lab_es.toString(), titulo);
            }
        } catch (Exception e) {
            evemen.mensaje_error(e, sql_cargar + "\n" + lab_es.toString(), titulo);
        }
    }

    public void actualizar_tabla_lab_estudio(Connection conn, JTable tbltabla,String filtro) {
        String sql_select_buscar = "select les.idlab_estudio as idest,\n"
            + "case when (select lge.nombre as gru_valor from item_lab_estudio ile1,lab_grupo_estudio lge\n"
            + "where ile1.fk_idlab_grupo_estudio=lge.idlab_grupo_estudio\n"
            + "and lge.fk_idlab_grupo="+tbl_or.getIdtabla_area()+"\n"
                + "and ile1.fk_idlab_estudio=les.idlab_estudio) is not null \n"
            + "then (select lge.nombre as gru_valor from item_lab_estudio ile1,lab_grupo_estudio lge\n"
            + "where ile1.fk_idlab_grupo_estudio=lge.idlab_grupo_estudio\n"
            + "and lge.fk_idlab_grupo="+tbl_or.getIdtabla_area()+"\n"
                + "and ile1.fk_idlab_estudio=les.idlab_estudio)\n"
            + "else '----------' end as area,\n"
            + "case when (select lge.nombre as gru_valor from item_lab_estudio ile1,lab_grupo_estudio lge\n"
            + "where ile1.fk_idlab_grupo_estudio=lge.idlab_grupo_estudio\n"
            + "and lge.fk_idlab_grupo="+tbl_or.getIdtabla_seccion()+"\n"
                + "and ile1.fk_idlab_estudio=les.idlab_estudio) is not null \n"
            + "then (select lge.nombre as gru_valor from item_lab_estudio ile1,lab_grupo_estudio lge\n"
            + "where ile1.fk_idlab_grupo_estudio=lge.idlab_grupo_estudio\n"
            + "and lge.fk_idlab_grupo="+tbl_or.getIdtabla_seccion()+"\n"
                + "and ile1.fk_idlab_estudio=les.idlab_estudio)\n"
            + "else '----------' end as seccion,\n"
            + "case when (select lge.nombre as gru_valor from item_lab_estudio ile1,lab_grupo_estudio lge\n"
            + "where ile1.fk_idlab_grupo_estudio=lge.idlab_grupo_estudio\n"
            + "and lge.fk_idlab_grupo="+tbl_or.getIdtabla_grupo_valor()+"\n"
                + "and ile1.fk_idlab_estudio=les.idlab_estudio) is not null \n"
            + "then (select lge.nombre as gru_valor from item_lab_estudio ile1,lab_grupo_estudio lge\n"
            + "where ile1.fk_idlab_grupo_estudio=lge.idlab_grupo_estudio\n"
            + "and lge.fk_idlab_grupo="+tbl_or.getIdtabla_grupo_valor()+"\n"
                + "and ile1.fk_idlab_estudio=les.idlab_estudio)\n"
            + "else '----------' end as grupo_valor,\n"
            + "case when (select ile1.orden as gru_valor from item_lab_estudio ile1,lab_grupo_estudio lge\n"
            + "where ile1.fk_idlab_grupo_estudio=lge.idlab_grupo_estudio\n"
            + "and lge.fk_idlab_grupo="+tbl_or.getIdtabla_grupo_valor()+"\n"
                + "and ile1.fk_idlab_estudio=les.idlab_estudio) is not null \n"
            + "then (select ile1.orden as gru_valor from item_lab_estudio ile1,lab_grupo_estudio lge\n"
            + "where ile1.fk_idlab_grupo_estudio=lge.idlab_grupo_estudio\n"
            + "and lge.fk_idlab_grupo="+tbl_or.getIdtabla_grupo_valor()+"\n"
                + "and ile1.fk_idlab_estudio=les.idlab_estudio)\n"
            + "else 0 end as orden,\n"
            + "les.nombre_completo as nom_completo,\n"
            + "lun.nombre as unid \n"
            + "from lab_estudio les,lab_unidad lun\n"
            + "where les.fk_idlab_unidad=lun.idlab_unidad\n"+filtro
            + " order by 2 asc,3 desc,4 desc,5 asc;";
        eveconn.Select_cargar_jtable(conn, sql_select_buscar, tbltabla);
        ancho_tabla_lab_estudio(tbltabla);
    }

    public void ancho_tabla_lab_estudio(JTable tbltabla) {
        int Ancho[] = {5,15,20,20,5,30,5};
        evejt.setAnchoColumnaJtable(tbltabla, Ancho);
    }

    public void actualizar_tabla_lab_estudio_ordenar(Connection conn, JTable tbltabla, int fk_idlab_grupo_estudio) {
        String sql = "select ile.iditem_lab_estudio as idile,ile.orden,\n"
                + "le.nombre_completo as estudio,lu.nombre as unid \n"
                + "from item_lab_estudio ile, lab_estudio le,lab_unidad lu\n"
                + "where ile.fk_idlab_estudio=le.idlab_estudio\n"
                + "and le.fk_idlab_unidad=lu.idlab_unidad\n"
                + "and ile.fk_idlab_grupo_estudio=" + fk_idlab_grupo_estudio
                + " order by ile.orden asc;";
        eveconn.Select_cargar_jtable(conn, sql, tbltabla);
        ancho_tabla_lab_estudio_ordenar(tbltabla);
    }

    public void ancho_tabla_lab_estudio_ordenar(JTable tbltabla) {
        int Ancho[] = {10, 10, 70, 10};
        evejt.setAnchoColumnaJtable(tbltabla, Ancho);
    }
}
