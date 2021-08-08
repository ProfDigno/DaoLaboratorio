package FORMULARIO.DAO;

import BASEDATO.EvenConexion;
import FORMULARIO.ENTIDAD.lab_grupo;
import Evento.JasperReport.EvenJasperReport;
import Evento.Jtable.EvenJtable;
import Evento.Mensaje.EvenMensajeJoptionpane;
import Evento.Fecha.EvenFecha;
import java.sql.ResultSet;
import java.sql.Connection;
import java.sql.PreparedStatement;
import javax.swing.JTable;

public class DAO_lab_grupo {

    EvenConexion eveconn = new EvenConexion();
    EvenJtable evejt = new EvenJtable();
    EvenJasperReport rep = new EvenJasperReport();
    EvenMensajeJoptionpane evemen = new EvenMensajeJoptionpane();
    EvenFecha evefec = new EvenFecha();
    private String mensaje_insert = "LAB_GRUPO GUARDADO CORRECTAMENTE";
    private String mensaje_update = "LAB_GRUPO MODIFICADO CORECTAMENTE";
    private String sql_insert = "INSERT INTO lab_grupo(idlab_grupo,nombre,descripcion) VALUES (?,?,?);";
    private String sql_update = "UPDATE lab_grupo SET nombre=?,descripcion=? WHERE idlab_grupo=?;";
    private String sql_select = "SELECT idlab_grupo,nombre,descripcion FROM lab_grupo order by 1 desc;";
    private String sql_cargar = "SELECT idlab_grupo,nombre,descripcion FROM lab_grupo WHERE idlab_grupo=";

    public void insertar_lab_grupo(Connection conn, lab_grupo lab_g) {
        lab_g.setC1idlab_grupo(eveconn.getInt_ultimoID_mas_uno(conn, lab_g.getTb_lab_grupo(), lab_g.getId_idlab_grupo()));
        String titulo = "insertar_lab_grupo";
        PreparedStatement pst = null;
        try {
            pst = conn.prepareStatement(sql_insert);
            pst.setInt(1, lab_g.getC1idlab_grupo());
            pst.setString(2, lab_g.getC2nombre());
            pst.setString(3, lab_g.getC3descripcion());
            pst.execute();
            pst.close();
            evemen.Imprimir_serial_sql(sql_insert + "\n" + lab_g.toString(), titulo);
            evemen.guardado_correcto(mensaje_insert, true);
        } catch (Exception e) {
            evemen.mensaje_error(e, sql_insert + "\n" + lab_g.toString(), titulo);
        }
    }

    public void update_lab_grupo(Connection conn, lab_grupo lab_g) {
        String titulo = "update_lab_grupo";
        PreparedStatement pst = null;
        try {
            pst = conn.prepareStatement(sql_update);
            pst.setString(1, lab_g.getC2nombre());
            pst.setString(2, lab_g.getC3descripcion());
            pst.setInt(3, lab_g.getC1idlab_grupo());
            pst.execute();
            pst.close();
            evemen.Imprimir_serial_sql(sql_update + "\n" + lab_g.toString(), titulo);
            evemen.modificado_correcto(mensaje_update, true);
        } catch (Exception e) {
            evemen.mensaje_error(e, sql_update + "\n" + lab_g.toString(), titulo);
        }
    }

    public void cargar_lab_grupo(Connection conn, lab_grupo lab_g, int id) {
        String titulo = "Cargar_lab_grupo";
        try {
            ResultSet rs = eveconn.getResulsetSQL(conn, sql_cargar + id, titulo);
            if (rs.next()) {
                lab_g.setC1idlab_grupo(rs.getInt(1));
                lab_g.setC2nombre(rs.getString(2));
                lab_g.setC3descripcion(rs.getString(3));
                evemen.Imprimir_serial_sql(sql_cargar + "\n" + lab_g.toString(), titulo);
            }
        } catch (Exception e) {
            evemen.mensaje_error(e, sql_cargar + "\n" + lab_g.toString(), titulo);
        }
    }

    public void actualizar_tabla_lab_grupo(Connection conn, JTable tbltabla) {
        eveconn.Select_cargar_jtable(conn, sql_select, tbltabla);
        ancho_tabla_lab_grupo(tbltabla);
    }

    public void ancho_tabla_lab_grupo(JTable tbltabla) {
        int Ancho[] = {10, 30,60};
        evejt.setAnchoColumnaJtable(tbltabla, Ancho);
    }
}
