package FORMULARIO.BO;

import BASEDATO.LOCAL.ConnPostgres;
import Evento.Mensaje.EvenMensajeJoptionpane;
import FORMULARIO.DAO.DAO_persona;
import FORMULARIO.ENTIDAD.persona;
import java.sql.Connection;
import java.sql.SQLException;
import javax.swing.JTable;

public class BO_persona {

    private DAO_persona per_dao = new DAO_persona();
    EvenMensajeJoptionpane evmen = new EvenMensajeJoptionpane();

    public void insertar_persona(persona per, JTable tbltabla) {
        String titulo = "insertar_persona";
        Connection conn = ConnPostgres.getConnPosgres();
        try {
            if (conn.getAutoCommit()) {
                conn.setAutoCommit(false);
            }
            per_dao.insertar_persona(conn, per);
            per_dao.actualizar_tabla_persona(conn, tbltabla);
            conn.commit();
        } catch (SQLException e) {
            evmen.mensaje_error(e, per.toString(), titulo);
            try {
                conn.rollback();
            } catch (SQLException e1) {
                evmen.Imprimir_serial_sql_error(e1, per.toString(), titulo);
            }
        }
    }

    public void update_persona(persona per, JTable tbltabla) {
        if (evmen.MensajeGeneral_warning("ESTAS SEGURO DE MODIFICAR PERSONA", "MODIFICAR", "ACEPTAR", "CANCELAR")) {
            String titulo = "update_persona";
            Connection conn = ConnPostgres.getConnPosgres();
            try {
                if (conn.getAutoCommit()) {
                    conn.setAutoCommit(false);
                }
                per_dao.update_persona(conn, per);
                per_dao.actualizar_tabla_persona(conn, tbltabla);
                conn.commit();
            } catch (SQLException e) {
                evmen.mensaje_error(e, per.toString(), titulo);
                try {
                    conn.rollback();
                } catch (SQLException e1) {
                    evmen.Imprimir_serial_sql_error(e1, per.toString(), titulo);
                }
            }
        }
    }
}
