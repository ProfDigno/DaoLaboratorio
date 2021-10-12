package FORMULARIO.DAO;

import BASEDATO.EvenConexion;
import FORMULARIO.ENTIDAD.orden_lugar;
import Evento.JasperReport.EvenJasperReport;
import Evento.Jtable.EvenJtable;
import Evento.Mensaje.EvenMensajeJoptionpane;
import Evento.Fecha.EvenFecha;
import java.sql.ResultSet;
import java.sql.Connection;
import java.sql.PreparedStatement;
import javax.swing.JTable;

public class DAO_orden_lugar {

    EvenConexion eveconn = new EvenConexion();
    EvenJtable evejt = new EvenJtable();
    EvenJasperReport rep = new EvenJasperReport();
    EvenMensajeJoptionpane evemen = new EvenMensajeJoptionpane();
    EvenFecha evefec = new EvenFecha();
    private String mensaje_insert = "ORDEN_LUGAR GUARDADO CORRECTAMENTE";
    private String mensaje_update = "ORDEN_LUGAR MODIFICADO CORECTAMENTE";
    private String sql_insert = "INSERT INTO public.orden_lugar(idorden_lugar,nombre) VALUES (?,?);";
    private String sql_update = "UPDATE public.orden_lugar SET nombre=? WHERE idorden_lugar=?;";
    private String sql_select = "SELECT idorden_lugar,nombre FROM public.orden_lugar order by 1 desc;";
    private String sql_cargar = "SELECT idorden_lugar,nombre FROM public.orden_lugar WHERE idorden_lugar=";

    public void insertar_orden_lugar(Connection conn, orden_lugar orlu) {
        orlu.setC1idorden_lugar(eveconn.getInt_ultimoID_mas_uno(conn, orlu.getTb_orden_lugar(), orlu.getId_idorden_lugar()));
        String titulo = "insertar_orden_lugar";
        PreparedStatement pst = null;
        try {
            pst = conn.prepareStatement(sql_insert);
            pst.setInt(1, orlu.getC1idorden_lugar());
            pst.setString(2, orlu.getC2nombre());
            pst.execute();
            pst.close();
            evemen.Imprimir_serial_sql(sql_insert + "\n" + orlu.toString(), titulo);
            evemen.guardado_correcto(mensaje_insert, true);
        } catch (Exception e) {
            evemen.mensaje_error(e, sql_insert + "\n" + orlu.toString(), titulo);
        }
    }

    public void update_orden_lugar(Connection conn, orden_lugar orlu) {
        String titulo = "update_orden_lugar";
        PreparedStatement pst = null;
        try {
            pst = conn.prepareStatement(sql_update);
            pst.setString(1, orlu.getC2nombre());
            pst.setInt(2, orlu.getC1idorden_lugar());
            pst.execute();
            pst.close();
            evemen.Imprimir_serial_sql(sql_update + "\n" + orlu.toString(), titulo);
            evemen.modificado_correcto(mensaje_update, true);
        } catch (Exception e) {
            evemen.mensaje_error(e, sql_update + "\n" + orlu.toString(), titulo);
        }
    }

    public void cargar_orden_lugar(Connection conn, orden_lugar orlu, int id) {
        String titulo = "Cargar_orden_lugar";
        try {
            ResultSet rs = eveconn.getResulsetSQL(conn, sql_cargar + id, titulo);
            if (rs.next()) {
                orlu.setC1idorden_lugar(rs.getInt(1));
                orlu.setC2nombre(rs.getString(2));
                evemen.Imprimir_serial_sql(sql_cargar + "\n" + orlu.toString(), titulo);
            }
        } catch (Exception e) {
            evemen.mensaje_error(e, sql_cargar + "\n" + orlu.toString(), titulo);
        }
    }

    public void actualizar_tabla_orden_lugar(Connection conn, JTable tbltabla) {
        eveconn.Select_cargar_jtable(conn, sql_select, tbltabla);
        ancho_tabla_orden_lugar(tbltabla);
    }

    public void ancho_tabla_orden_lugar(JTable tbltabla) {
        int Ancho[] = {20, 80};
        evejt.setAnchoColumnaJtable(tbltabla, Ancho);
    }
}
