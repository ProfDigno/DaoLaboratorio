package FORMULARIO.BO;

import BASEDATO.LOCAL.ConnPostgres;
import Evento.Mensaje.EvenMensajeJoptionpane;
import FORMULARIO.DAO.DAO_lab_unidad;
import FORMULARIO.ENTIDAD.lab_unidad;
import java.sql.Connection;
import java.sql.SQLException;
import javax.swing.JTable;

public class BO_lab_unidad {

    private DAO_lab_unidad lab_u_dao = new DAO_lab_unidad();
    EvenMensajeJoptionpane evmen = new EvenMensajeJoptionpane();

    public void insertar_lab_unidad(lab_unidad lab_u, JTable tbltabla) {
        String titulo = "insertar_lab_unidad";
        Connection conn = ConnPostgres.getConnPosgres();
        try {
            if (conn.getAutoCommit()) {
                conn.setAutoCommit(false);
            }
            lab_u_dao.insertar_lab_unidad(conn, lab_u);
            lab_u_dao.actualizar_tabla_lab_unidad(conn, tbltabla);
            conn.commit();
        } catch (SQLException e) {
            evmen.mensaje_error(e, lab_u.toString(), titulo);
            try {
                conn.rollback();
            } catch (SQLException e1) {
                evmen.Imprimir_serial_sql_error(e1, lab_u.toString(), titulo);
            }
        }
    }

    public void update_lab_unidad(lab_unidad lab_u, JTable tbltabla) {
        if (evmen.MensajeGeneral_warning("ESTAS SEGURO DE MODIFICAR LAB_UNIDAD", "MODIFICAR", "ACEPTAR", "CANCELAR")) {
            String titulo = "update_lab_unidad";
            Connection conn = ConnPostgres.getConnPosgres();
            try {
                if (conn.getAutoCommit()) {
                    conn.setAutoCommit(false);
                }
                lab_u_dao.update_lab_unidad(conn, lab_u);
                lab_u_dao.actualizar_tabla_lab_unidad(conn, tbltabla);
                conn.commit();
            } catch (SQLException e) {
                evmen.mensaje_error(e, lab_u.toString(), titulo);
                try {
                    conn.rollback();
                } catch (SQLException e1) {
                    evmen.Imprimir_serial_sql_error(e1, lab_u.toString(), titulo);
                }
            }
        }
    }
}
