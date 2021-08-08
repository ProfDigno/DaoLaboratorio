package FORMULARIO.DAO;

import BASEDATO.EvenConexion;
import FORMULARIO.ENTIDAD.tipo_seguro;
import Evento.JasperReport.EvenJasperReport;
import Evento.Jtable.EvenJtable;
import Evento.Mensaje.EvenMensajeJoptionpane;
import Evento.Fecha.EvenFecha;
import java.sql.ResultSet;
import java.sql.Connection;
import java.sql.PreparedStatement;
import javax.swing.JTable;

public class DAO_tipo_seguro {

    EvenConexion eveconn = new EvenConexion();
    EvenJtable evejt = new EvenJtable();
    EvenJasperReport rep = new EvenJasperReport();
    EvenMensajeJoptionpane evemen = new EvenMensajeJoptionpane();
    EvenFecha evefec = new EvenFecha();
    private String mensaje_insert = "TIPO_SEGURO GUARDADO CORRECTAMENTE";
    private String mensaje_update = "TIPO_SEGURO MODIFICADO CORECTAMENTE";
    private String sql_insert = "INSERT INTO tipo_seguro(idtipo_seguro,nombre) VALUES (?,?);";
    private String sql_update = "UPDATE tipo_seguro SET nombre=? WHERE idtipo_seguro=?;";
    private String sql_select = "SELECT idtipo_seguro,nombre FROM tipo_seguro order by 1 desc;";
    private String sql_cargar = "SELECT idtipo_seguro,nombre FROM tipo_seguro WHERE idtipo_seguro=";

    public void insertar_tipo_seguro(Connection conn, tipo_seguro t_seg) {
        t_seg.setC1idtipo_seguro(eveconn.getInt_ultimoID_mas_uno(conn, t_seg.getTb_tipo_seguro(), t_seg.getId_idtipo_seguro()));
        String titulo = "insertar_tipo_seguro";
        PreparedStatement pst = null;
        try {
            pst = conn.prepareStatement(sql_insert);
            pst.setInt(1, t_seg.getC1idtipo_seguro());
            pst.setString(2, t_seg.getC2nombre());
            pst.execute();
            pst.close();
            evemen.Imprimir_serial_sql(sql_insert + "\n" + t_seg.toString(), titulo);
            evemen.guardado_correcto(mensaje_insert, true);
        } catch (Exception e) {
            evemen.mensaje_error(e, sql_insert + "\n" + t_seg.toString(), titulo);
        }
    }

    public void update_tipo_seguro(Connection conn, tipo_seguro t_seg) {
        String titulo = "update_tipo_seguro";
        PreparedStatement pst = null;
        try {
            pst = conn.prepareStatement(sql_update);
            pst.setString(1, t_seg.getC2nombre());
            pst.setInt(2, t_seg.getC1idtipo_seguro());
            pst.execute();
            pst.close();
            evemen.Imprimir_serial_sql(sql_update + "\n" + t_seg.toString(), titulo);
            evemen.modificado_correcto(mensaje_update, true);
        } catch (Exception e) {
            evemen.mensaje_error(e, sql_update + "\n" + t_seg.toString(), titulo);
        }
    }

    public void cargar_tipo_seguro(Connection conn, tipo_seguro t_seg, int id) {
        String titulo = "Cargar_tipo_seguro";
        try {
            ResultSet rs = eveconn.getResulsetSQL(conn, sql_cargar + id, titulo);
            if (rs.next()) {
                t_seg.setC1idtipo_seguro(rs.getInt(1));
                t_seg.setC2nombre(rs.getString(2));
                evemen.Imprimir_serial_sql(sql_cargar + "\n" + t_seg.toString(), titulo);
            }
        } catch (Exception e) {
            evemen.mensaje_error(e, sql_cargar + "\n" + t_seg.toString(), titulo);
        }
    }

    public void actualizar_tabla_tipo_seguro(Connection conn, JTable tbltabla) {
        eveconn.Select_cargar_jtable(conn, sql_select, tbltabla);
        ancho_tabla_tipo_seguro(tbltabla);
    }

    public void ancho_tabla_tipo_seguro(JTable tbltabla) {
        int Ancho[] = {20, 80};
        evejt.setAnchoColumnaJtable(tbltabla, Ancho);
    }
}
