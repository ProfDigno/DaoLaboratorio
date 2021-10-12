package FORMULARIO.DAO;

import BASEDATO.EvenConexion;
import FORMULARIO.ENTIDAD.lab_grupo_estudio;
import Evento.JasperReport.EvenJasperReport;
import Evento.Jtable.EvenJtable;
import Evento.Mensaje.EvenMensajeJoptionpane;
import Evento.Fecha.EvenFecha;
import java.sql.ResultSet;
import java.sql.Connection;
import java.sql.PreparedStatement;
import javax.swing.JTable;
import javax.swing.JTextField;

public class DAO_lab_grupo_estudio {

    EvenConexion eveconn = new EvenConexion();
    EvenJtable evejt = new EvenJtable();
    EvenJasperReport rep = new EvenJasperReport();
    EvenMensajeJoptionpane evemen = new EvenMensajeJoptionpane();
    EvenFecha evefec = new EvenFecha();
    private String mensaje_insert = "LAB_GRUPO_ESTUDIO GUARDADO CORRECTAMENTE";
    private String mensaje_update = "LAB_GRUPO_ESTUDIO MODIFICADO CORECTAMENTE";
    private String sql_insert = "INSERT INTO public.lab_grupo_estudio(idlab_grupo_estudio,nombre,fk_idlab_grupo,orden) VALUES (?,?,?,?);";
    private String sql_update = "UPDATE public.lab_grupo_estudio SET nombre=?,fk_idlab_grupo=?,orden=? WHERE idlab_grupo_estudio=?;";
    private String sql_select = "select lge.idlab_grupo_estudio as idlge,lge.nombre as nombre,\n"
            + "('('||lg.idlab_grupo||')-'||lg.nombre) as grupo,lge.orden \n"
            + "from public.lab_grupo_estudio lge,public.lab_grupo lg\n"
            + "where lge.fk_idlab_grupo=lg.idlab_grupo\n"
            + "order by lg.idlab_grupo desc,lge.orden asc ;";
    private String sql_cargar = "SELECT idlab_grupo_estudio,nombre,fk_idlab_grupo,orden FROM public.lab_grupo_estudio WHERE idlab_grupo_estudio=";

    public void insertar_lab_grupo_estudio(Connection conn, lab_grupo_estudio lab_ge) {
        lab_ge.setC1idlab_grupo_estudio(eveconn.getInt_ultimoID_mas_uno(conn, lab_ge.getTb_lab_grupo_estudio(), lab_ge.getId_idlab_grupo_estudio()));
        String titulo = "insertar_lab_grupo_estudio";
        PreparedStatement pst = null;
        try {
            pst = conn.prepareStatement(sql_insert);
            pst.setInt(1, lab_ge.getC1idlab_grupo_estudio());
            pst.setString(2, lab_ge.getC2nombre());
            pst.setInt(3, lab_ge.getC3fk_idlab_grupo());
            pst.setInt(4, lab_ge.getC4orden());
            pst.execute();
            pst.close();
            evemen.Imprimir_serial_sql(sql_insert + "\n" + lab_ge.toString(), titulo);
            evemen.guardado_correcto(mensaje_insert, true);
        } catch (Exception e) {
            evemen.mensaje_error(e, sql_insert + "\n" + lab_ge.toString(), titulo);
        }
    }

    public void update_lab_grupo_estudio(Connection conn, lab_grupo_estudio lab_ge) {
        String titulo = "update_lab_grupo_estudio";
        PreparedStatement pst = null;
        try {
            pst = conn.prepareStatement(sql_update);
            pst.setString(1, lab_ge.getC2nombre());
            pst.setInt(2, lab_ge.getC3fk_idlab_grupo());
            pst.setInt(3, lab_ge.getC4orden());
            pst.setInt(4, lab_ge.getC1idlab_grupo_estudio());
            pst.execute();
            pst.close();
            evemen.Imprimir_serial_sql(sql_update + "\n" + lab_ge.toString(), titulo);
            evemen.modificado_correcto(mensaje_update, true);
        } catch (Exception e) {
            evemen.mensaje_error(e, sql_update + "\n" + lab_ge.toString(), titulo);
        }
    }

    public void cargar_lab_grupo_estudio(Connection conn, lab_grupo_estudio lab_ge, int id) {
        String titulo = "Cargar_lab_grupo_estudio";
        try {
            ResultSet rs = eveconn.getResulsetSQL(conn, sql_cargar + id, titulo);
            if (rs.next()) {
                lab_ge.setC1idlab_grupo_estudio(rs.getInt(1));
                lab_ge.setC2nombre(rs.getString(2));
                lab_ge.setC3fk_idlab_grupo(rs.getInt(3));
                lab_ge.setC4orden(rs.getInt(4));
                evemen.Imprimir_serial_sql(sql_cargar + "\n" + lab_ge.toString(), titulo);
            }
        } catch (Exception e) {
            evemen.mensaje_error(e, sql_cargar + "\n" + lab_ge.toString(), titulo);
        }
    }

    public void actualizar_tabla_lab_grupo_estudio(Connection conn, JTable tbltabla) {
        eveconn.Select_cargar_jtable(conn, sql_select, tbltabla);
        ancho_tabla_lab_grupo_estudio(tbltabla);
    }

    public void actualizar_tabla_lab_grupo_estudio_buscar(Connection conn, JTable tbltabla, JTextField txtbuscar) {
        String buscar = txtbuscar.getText();
        String sql = "select lge.idlab_grupo_estudio as idlge,lge.nombre as nombre,\n"
                + "('('||lg.idlab_grupo||')-'||lg.nombre) as grupo,lge.orden \n"
                + "from public.lab_grupo_estudio lge,public.lab_grupo lg\n"
                + "where lge.fk_idlab_grupo=lg.idlab_grupo\n"
                + " and lge.nombre ilike'%" + buscar + "%' "
                + "order by lg.idlab_grupo desc,lge.orden asc ;";
        eveconn.Select_cargar_jtable(conn, sql, tbltabla);
        ancho_tabla_lab_grupo_estudio(tbltabla);
    }

    public void ancho_tabla_lab_grupo_estudio(JTable tbltabla) {
        int Ancho[] = {10, 45, 35, 10};
        evejt.setAnchoColumnaJtable(tbltabla, Ancho);
    }
}
