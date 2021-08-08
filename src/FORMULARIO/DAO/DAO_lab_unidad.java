package FORMULARIO.DAO;

import BASEDATO.EvenConexion;
import FORMULARIO.ENTIDAD.lab_unidad;
import Evento.JasperReport.EvenJasperReport;
import Evento.Jtable.EvenJtable;
import Evento.Mensaje.EvenMensajeJoptionpane;
import Evento.Fecha.EvenFecha;
import java.sql.ResultSet;
import java.sql.Connection;
import java.sql.PreparedStatement;
import javax.swing.JTable;

public class DAO_lab_unidad {

    EvenConexion eveconn = new EvenConexion();
    EvenJtable evejt = new EvenJtable();
    EvenJasperReport rep = new EvenJasperReport();
    EvenMensajeJoptionpane evemen = new EvenMensajeJoptionpane();
    EvenFecha evefec = new EvenFecha();
    private String mensaje_insert = "LAB_UNIDAD GUARDADO CORRECTAMENTE";
    private String mensaje_update = "LAB_UNIDAD MODIFICADO CORECTAMENTE";
    private String sql_insert = "INSERT INTO lab_unidad(idlab_unidad,nombre) VALUES (?,?);";
    private String sql_update = "UPDATE lab_unidad SET nombre=? WHERE idlab_unidad=?;";
    private String sql_select = "SELECT idlab_unidad,nombre FROM lab_unidad order by 1 desc;";
    private String sql_cargar = "SELECT idlab_unidad,nombre FROM lab_unidad WHERE idlab_unidad=";

    public void insertar_lab_unidad(Connection conn, lab_unidad lab_u) {
        lab_u.setC1idlab_unidad(eveconn.getInt_ultimoID_mas_uno(conn, lab_u.getTb_lab_unidad(), lab_u.getId_idlab_unidad()));
        String titulo = "insertar_lab_unidad";
        PreparedStatement pst = null;
        try {
            pst = conn.prepareStatement(sql_insert);
            pst.setInt(1, lab_u.getC1idlab_unidad());
            pst.setString(2, lab_u.getC2nombre());
            pst.execute();
            pst.close();
            evemen.Imprimir_serial_sql(sql_insert + "\n" + lab_u.toString(), titulo);
            evemen.guardado_correcto(mensaje_insert, true);
        } catch (Exception e) {
            evemen.mensaje_error(e, sql_insert + "\n" + lab_u.toString(), titulo);
        }
    }

    public void update_lab_unidad(Connection conn, lab_unidad lab_u) {
        String titulo = "update_lab_unidad";
        PreparedStatement pst = null;
        try {
            pst = conn.prepareStatement(sql_update);
            pst.setString(1, lab_u.getC2nombre());
            pst.setInt(2, lab_u.getC1idlab_unidad());
            pst.execute();
            pst.close();
            evemen.Imprimir_serial_sql(sql_update + "\n" + lab_u.toString(), titulo);
            evemen.modificado_correcto(mensaje_update, true);
        } catch (Exception e) {
            evemen.mensaje_error(e, sql_update + "\n" + lab_u.toString(), titulo);
        }
    }

    public void cargar_lab_unidad(Connection conn, lab_unidad lab_u, int id) {
        String titulo = "Cargar_lab_unidad";
        try {
            ResultSet rs = eveconn.getResulsetSQL(conn, sql_cargar + id, titulo);
            if (rs.next()) {
                lab_u.setC1idlab_unidad(rs.getInt(1));
                lab_u.setC2nombre(rs.getString(2));
                evemen.Imprimir_serial_sql(sql_cargar + "\n" + lab_u.toString(), titulo);
            }
        } catch (Exception e) {
            evemen.mensaje_error(e, sql_cargar + "\n" + lab_u.toString(), titulo);
        }
    }

    public void actualizar_tabla_lab_unidad(Connection conn, JTable tbltabla) {
        eveconn.Select_cargar_jtable(conn, sql_select, tbltabla);
        ancho_tabla_lab_unidad(tbltabla);
    }

    public void ancho_tabla_lab_unidad(JTable tbltabla) {
        int Ancho[] = {20, 80};
        evejt.setAnchoColumnaJtable(tbltabla, Ancho);
    }
}
