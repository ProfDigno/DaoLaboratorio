package FORMULARIO.DAO;

import BASEDATO.EvenConexion;
import FORMULARIO.ENTIDAD.lab_estudio_predefinido;
import Evento.JasperReport.EvenJasperReport;
import Evento.Jtable.EvenJtable;
import Evento.Mensaje.EvenMensajeJoptionpane;
import Evento.Fecha.EvenFecha;
import java.sql.ResultSet;
import java.sql.Connection;
import java.sql.PreparedStatement;
import javax.swing.JTable;

public class DAO_lab_estudio_predefinido {

    EvenConexion eveconn = new EvenConexion();
    EvenJtable evejt = new EvenJtable();
    EvenJasperReport rep = new EvenJasperReport();
    EvenMensajeJoptionpane evemen = new EvenMensajeJoptionpane();
    EvenFecha evefec = new EvenFecha();
    private String mensaje_insert = "LAB_ESTUDIO_PREDEFINIDO GUARDADO CORRECTAMENTE";
    private String mensaje_update = "LAB_ESTUDIO_PREDEFINIDO MODIFICADO CORECTAMENTE";
    private String sql_insert = "INSERT INTO public.lab_estudio_predefinido(idlab_estudio_predefinido,nombre,es_anormal) VALUES (?,?,?);";
    private String sql_update = "UPDATE public.lab_estudio_predefinido SET nombre=?,es_anormal=? WHERE idlab_estudio_predefinido=?;";
    private String sql_select = "SELECT idlab_estudio_predefinido,nombre,es_anormal FROM public.lab_estudio_predefinido order by 1 desc;";
    private String sql_cargar = "SELECT idlab_estudio_predefinido,nombre,es_anormal FROM public.lab_estudio_predefinido WHERE idlab_estudio_predefinido=";

    public void insertar_lab_estudio_predefinido(Connection conn, lab_estudio_predefinido lep) {
        lep.setC1idlab_estudio_predefinido(eveconn.getInt_ultimoID_mas_uno(conn, lep.getTb_lab_estudio_predefinido(), lep.getId_idlab_estudio_predefinido()));
        String titulo = "insertar_lab_estudio_predefinido";
        PreparedStatement pst = null;
        try {
            pst = conn.prepareStatement(sql_insert);
            pst.setInt(1, lep.getC1idlab_estudio_predefinido());
            pst.setString(2, lep.getC2nombre());
            pst.setBoolean(3, lep.getC3es_anormal());
            pst.execute();
            pst.close();
            evemen.Imprimir_serial_sql(sql_insert + "\n" + lep.toString(), titulo);
            evemen.guardado_correcto(mensaje_insert, true);
        } catch (Exception e) {
            evemen.mensaje_error(e, sql_insert + "\n" + lep.toString(), titulo);
        }
    }

    public void update_lab_estudio_predefinido(Connection conn, lab_estudio_predefinido lep) {
        String titulo = "update_lab_estudio_predefinido";
        PreparedStatement pst = null;
        try {
            pst = conn.prepareStatement(sql_update);
            pst.setString(1, lep.getC2nombre());
            pst.setBoolean(2, lep.getC3es_anormal());
            pst.setInt(3, lep.getC1idlab_estudio_predefinido());
            pst.execute();
            pst.close();
            evemen.Imprimir_serial_sql(sql_update + "\n" + lep.toString(), titulo);
            evemen.modificado_correcto(mensaje_update, true);
        } catch (Exception e) {
            evemen.mensaje_error(e, sql_update + "\n" + lep.toString(), titulo);
        }
    }

    public void cargar_lab_estudio_predefinido(Connection conn, lab_estudio_predefinido lep, int id) {
        String titulo = "Cargar_lab_estudio_predefinido";
        try {
            ResultSet rs = eveconn.getResulsetSQL(conn, sql_cargar + id, titulo);
            if (rs.next()) {
                lep.setC1idlab_estudio_predefinido(rs.getInt(1));
                lep.setC2nombre(rs.getString(2));
                lep.setC3es_anormal(rs.getBoolean(3));
                evemen.Imprimir_serial_sql(sql_cargar + "\n" + lep.toString(), titulo);
            }
        } catch (Exception e) {
            evemen.mensaje_error(e, sql_cargar + "\n" + lep.toString(), titulo);
        }
    }

    public void actualizar_tabla_lab_estudio_predefinido(Connection conn, JTable tbltabla) {
        eveconn.Select_cargar_jtable(conn, sql_select, tbltabla);
        ancho_tabla_lab_estudio_predefinido(tbltabla);
    }

    public void ancho_tabla_lab_estudio_predefinido(JTable tbltabla) {
        int Ancho[] = {15,60,25};
        evejt.setAnchoColumnaJtable(tbltabla, Ancho);
    }
}
