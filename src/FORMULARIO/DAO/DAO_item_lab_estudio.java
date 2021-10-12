package FORMULARIO.DAO;

import BASEDATO.EvenConexion;
import FORMULARIO.ENTIDAD.item_lab_estudio;
import Evento.JasperReport.EvenJasperReport;
import Evento.Jtable.EvenJtable;
import Evento.Mensaje.EvenMensajeJoptionpane;
import Evento.Fecha.EvenFecha;
import FORMULARIO.ENTIDAD.lab_grupo_estudio;
import java.sql.ResultSet;
import java.sql.Connection;
import java.sql.PreparedStatement;
import javax.swing.JTable;

public class DAO_item_lab_estudio {

    EvenConexion eveconn = new EvenConexion();
    EvenJtable evejt = new EvenJtable();
    EvenJasperReport rep = new EvenJasperReport();
    EvenMensajeJoptionpane evemen = new EvenMensajeJoptionpane();
    EvenFecha evefec = new EvenFecha();
    private String mensaje_insert = "ITEM_LAB_ESTUDIO GUARDADO CORRECTAMENTE";
    private String mensaje_update = "ITEM_LAB_ESTUDIO MODIFICADO CORECTAMENTE";
    private String sql_insert = "INSERT INTO public.item_lab_estudio(iditem_lab_estudio,orden,fk_idlab_estudio,fk_idlab_grupo_estudio) VALUES (?,?,?,?);";
    private String sql_update = "UPDATE public.item_lab_estudio SET orden=?,fk_idlab_estudio=?,fk_idlab_grupo_estudio=? WHERE iditem_lab_estudio=?;";
    private String sql_select = "SELECT iditem_lab_estudio,orden,fk_idlab_estudio,fk_idlab_grupo_estudio FROM public.item_lab_estudio order by 1 desc;";
    private String sql_cargar = "SELECT iditem_lab_estudio,orden,fk_idlab_estudio,fk_idlab_grupo_estudio FROM public.item_lab_estudio WHERE iditem_lab_estudio=";
    private String sql_update_orden = "UPDATE public.item_lab_estudio SET orden=? WHERE iditem_lab_estudio=?;";
    private String sql_delete_re="DELETE FROM public.item_lab_estudio iles\n" +
"USING lab_grupo_estudio AS lges\n" +
"WHERE iles.fk_idlab_grupo_estudio=lges.idlab_grupo_estudio \n" +
"and lges.fk_idlab_grupo=?  and iles.fk_idlab_estudio=?";
    private String mensaje_delete="ITEM_LAB_ESTUDIO ELIMINADO CORRECTAMENTE";
 
    public void insertar_item_lab_estudio(Connection conn, item_lab_estudio ilab_es) {
        ilab_es.setC1iditem_lab_estudio(eveconn.getInt_ultimoID_mas_uno(conn, ilab_es.getTb_item_lab_estudio(), ilab_es.getId_iditem_lab_estudio()));
        String titulo = "insertar_item_lab_estudio";
        PreparedStatement pst = null;
        try {
            pst = conn.prepareStatement(sql_insert);
            pst.setInt(1, ilab_es.getC1iditem_lab_estudio());
            pst.setInt(2, ilab_es.getC2orden());
            pst.setInt(3, ilab_es.getC3fk_idlab_estudio());
            pst.setInt(4, ilab_es.getC4fk_idlab_grupo_estudio());
            pst.execute();
            pst.close();
            evemen.Imprimir_serial_sql(sql_insert + "\n" + ilab_es.toString(), titulo);
            evemen.guardado_correcto(mensaje_insert, false);
        } catch (Exception e) {
            evemen.mensaje_error(e, sql_insert + "\n" + ilab_es.toString(), titulo);
        }
    }

    public void update_item_lab_estudio(Connection conn, item_lab_estudio ilab_es) {
        String titulo = "update_item_lab_estudio";
        PreparedStatement pst = null;
        try {
            pst = conn.prepareStatement(sql_update);
            pst.setInt(1, ilab_es.getC2orden());
            pst.setInt(2, ilab_es.getC3fk_idlab_estudio());
            pst.setInt(3, ilab_es.getC4fk_idlab_grupo_estudio());
            pst.setInt(4, ilab_es.getC1iditem_lab_estudio());
            pst.execute();
            pst.close();
            evemen.Imprimir_serial_sql(sql_update + "\n" + ilab_es.toString(), titulo);
            evemen.modificado_correcto(mensaje_update, true);
        } catch (Exception e) {
            evemen.mensaje_error(e, sql_update + "\n" + ilab_es.toString(), titulo);
        }
    }
public void update_item_lab_estudio_orden(Connection conn, item_lab_estudio ilab_es) {
        String titulo = "update_item_lab_estudio_orden";
        PreparedStatement pst = null;
        try {
            pst = conn.prepareStatement(sql_update_orden);
            pst.setInt(1, ilab_es.getC2orden());
            pst.setInt(2, ilab_es.getC1iditem_lab_estudio());
            pst.execute();
            pst.close();
            evemen.Imprimir_serial_sql(sql_update_orden + "\n" + ilab_es.toString(), titulo);
            evemen.modificado_correcto(mensaje_update, true);
        } catch (Exception e) {
            evemen.mensaje_error(e, sql_update_orden + "\n" + ilab_es.toString(), titulo);
        }
    }
public void delete_item_lab_estudio_orden_por_estudio(Connection conn,int fk_idlab_estudio,int fk_idlab_grupo) {
        String titulo = "delete_item_lab_estudio_orden_por_estudio";
        PreparedStatement pst = null;
        try {
            pst = conn.prepareStatement(sql_delete_re);
            pst.setInt(1, fk_idlab_grupo);
            pst.setInt(2, fk_idlab_estudio);
            pst.execute();
            pst.close();
            evemen.Imprimir_serial_sql(sql_delete_re + "\n" , titulo);
            evemen.modificado_correcto(mensaje_delete, true);
        } catch (Exception e) {
            evemen.mensaje_error(e, sql_delete_re + "\n" , titulo);
        }
    }
    public void cargar_item_lab_estudio(Connection conn, item_lab_estudio ilab_es, int iditem_lab_estudio) {
        String titulo = "Cargar_item_lab_estudio";
        try {
            ResultSet rs = eveconn.getResulsetSQL(conn, sql_cargar + iditem_lab_estudio, titulo);
            if (rs.next()) {
                ilab_es.setC1iditem_lab_estudio(rs.getInt(1));
                ilab_es.setC2orden(rs.getInt(2));
                ilab_es.setC3fk_idlab_estudio(rs.getInt(3));
                ilab_es.setC4fk_idlab_grupo_estudio(rs.getInt(4));
                evemen.Imprimir_serial_sql(sql_cargar + "\n" + ilab_es.toString(), titulo);
            }
        } catch (Exception e) {
            evemen.mensaje_error(e, sql_cargar + "\n" + ilab_es.toString(), titulo);
        }
    }

    public void actualizar_tabla_item_lab_estudio(Connection conn, JTable tbltabla) {
        eveconn.Select_cargar_jtable(conn, sql_select, tbltabla);
        ancho_tabla_item_lab_estudio(tbltabla);
    }

    public void ancho_tabla_item_lab_estudio(JTable tbltabla) {
        int Ancho[] = {25, 25, 25, 25};
        evejt.setAnchoColumnaJtable(tbltabla, Ancho);
    }
}
