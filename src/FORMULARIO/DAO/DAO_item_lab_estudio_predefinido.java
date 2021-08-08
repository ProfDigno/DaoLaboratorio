package FORMULARIO.DAO;

import BASEDATO.EvenConexion;
import FORMULARIO.ENTIDAD.item_lab_estudio_predefinido;
import Evento.JasperReport.EvenJasperReport;
import Evento.Jtable.EvenJtable;
import Evento.Mensaje.EvenMensajeJoptionpane;
import Evento.Fecha.EvenFecha;
import java.sql.ResultSet;
import java.sql.Connection;
import java.sql.PreparedStatement;
import javax.swing.JTable;

public class DAO_item_lab_estudio_predefinido {

    EvenConexion eveconn = new EvenConexion();
    EvenJtable evejt = new EvenJtable();
    EvenJasperReport rep = new EvenJasperReport();
    EvenMensajeJoptionpane evemen = new EvenMensajeJoptionpane();
    EvenFecha evefec = new EvenFecha();
    private String mensaje_insert = "ITEM_LAB_ESTUDIO_PREDEFINIDO GUARDADO CORRECTAMENTE";
    private String mensaje_update = "ITEM_LAB_ESTUDIO_PREDEFINIDO MODIFICADO CORECTAMENTE";
    private String sql_insert = "INSERT INTO item_lab_estudio_predefinido(iditem_lab_estudio_predefinido,orden,fk_idlab_estudio_predefinido,fk_idlab_estudio) VALUES (?,?,?,?);";
    private String sql_update = "UPDATE item_lab_estudio_predefinido SET orden=?,fk_idlab_estudio_predefinido=?,fk_idlab_estudio=? WHERE iditem_lab_estudio_predefinido=?;";
//    private String sql_select = "SELECT iditem_lab_estudio_predefinido,orden,fk_idlab_estudio_predefinido,fk_idlab_estudio FROM item_lab_estudio_predefinido order by 1 desc;";
    private String sql_cargar = "SELECT iditem_lab_estudio_predefinido,orden,fk_idlab_estudio_predefinido,fk_idlab_estudio FROM item_lab_estudio_predefinido WHERE iditem_lab_estudio_predefinido=";
    private String sql_select = "select ilep.iditem_lab_estudio_predefinido as idi,ilep.orden as ord,lep.nombre as resultado \n"
            + "from item_lab_estudio_predefinido ilep,lab_estudio_predefinido lep\n"
            + "where ilep.fk_idlab_estudio_predefinido=lep.idlab_estudio_predefinido\n"
            + "and ilep.fk_idlab_estudio=";

    public void insertar_item_lab_estudio_predefinido(Connection conn, item_lab_estudio_predefinido ilep) {
        ilep.setC1iditem_lab_estudio_predefinido(eveconn.getInt_ultimoID_mas_uno(conn, ilep.getTb_item_lab_estudio_predefinido(), ilep.getId_iditem_lab_estudio_predefinido()));
        String titulo = "insertar_item_lab_estudio_predefinido";
        PreparedStatement pst = null;
        try {
            pst = conn.prepareStatement(sql_insert);
            pst.setInt(1, ilep.getC1iditem_lab_estudio_predefinido());
            pst.setInt(2, ilep.getC2orden());
            pst.setInt(3, ilep.getC3fk_idlab_estudio_predefinido());
            pst.setInt(4, ilep.getC4fk_idlab_estudio());
            pst.execute();
            pst.close();
            evemen.Imprimir_serial_sql(sql_insert + "\n" + ilep.toString(), titulo);
            evemen.guardado_correcto(mensaje_insert, true);
        } catch (Exception e) {
            evemen.mensaje_error(e, sql_insert + "\n" + ilep.toString(), titulo);
        }
    }

    public void update_item_lab_estudio_predefinido(Connection conn, item_lab_estudio_predefinido ilep) {
        String titulo = "update_item_lab_estudio_predefinido";
        PreparedStatement pst = null;
        try {
            pst = conn.prepareStatement(sql_update);
            pst.setInt(1, ilep.getC2orden());
            pst.setInt(2, ilep.getC3fk_idlab_estudio_predefinido());
            pst.setInt(3, ilep.getC4fk_idlab_estudio());
            pst.setInt(4, ilep.getC1iditem_lab_estudio_predefinido());
            pst.execute();
            pst.close();
            evemen.Imprimir_serial_sql(sql_update + "\n" + ilep.toString(), titulo);
            evemen.modificado_correcto(mensaje_update, true);
        } catch (Exception e) {
            evemen.mensaje_error(e, sql_update + "\n" + ilep.toString(), titulo);
        }
    }

    public void cargar_item_lab_estudio_predefinido(Connection conn, item_lab_estudio_predefinido ilep, int id) {
        String titulo = "Cargar_item_lab_estudio_predefinido";
        try {
            ResultSet rs = eveconn.getResulsetSQL(conn, sql_cargar + id, titulo);
            if (rs.next()) {
                ilep.setC1iditem_lab_estudio_predefinido(rs.getInt(1));
                ilep.setC2orden(rs.getInt(2));
                ilep.setC3fk_idlab_estudio_predefinido(rs.getInt(3));
                ilep.setC4fk_idlab_estudio(rs.getInt(4));
                evemen.Imprimir_serial_sql(sql_cargar + "\n" + ilep.toString(), titulo);
            }
        } catch (Exception e) {
            evemen.mensaje_error(e, sql_cargar + "\n" + ilep.toString(), titulo);
        }
    }

    public void actualizar_tabla_item_lab_estudio_predefinido(Connection conn, JTable tbltabla,int fk_idlab_estudio) {
        eveconn.Select_cargar_jtable(conn, sql_select+fk_idlab_estudio+" order by ilep.orden asc;", tbltabla);
        ancho_tabla_item_lab_estudio_predefinido(tbltabla);
    }

    public void ancho_tabla_item_lab_estudio_predefinido(JTable tbltabla) {
        int Ancho[] = {10,10,80};
        evejt.setAnchoColumnaJtable(tbltabla, Ancho);
    }
}
