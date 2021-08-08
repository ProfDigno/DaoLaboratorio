package FORMULARIO.BO;

import BASEDATO.LOCAL.ConnPostgres;
import Evento.Mensaje.EvenMensajeJoptionpane;
import FORMULARIO.DAO.DAO_lab_estudio;
import FORMULARIO.ENTIDAD.lab_estudio;
import java.sql.Connection;
import java.sql.SQLException;
import javax.swing.JTable;

public class BO_lab_estudio {

    private DAO_lab_estudio lab_es_dao = new DAO_lab_estudio();
    EvenMensajeJoptionpane evmen = new EvenMensajeJoptionpane();

    public void insertar_lab_estudio(lab_estudio lab_es, JTable tbltabla) {
        String titulo = "insertar_lab_estudio";
        Connection conn = ConnPostgres.getConnPosgres();
        try {
            if (conn.getAutoCommit()) {
                conn.setAutoCommit(false);
            }
            lab_es_dao.insertar_lab_estudio(conn, lab_es);
//            lab_es_dao.actualizar_tabla_lab_estudio(conn, tbltabla);
            conn.commit();
        } catch (SQLException e) {
            evmen.mensaje_error(e, lab_es.toString(), titulo);
            try {
                conn.rollback();
            } catch (SQLException e1) {
                evmen.Imprimir_serial_sql_error(e1, lab_es.toString(), titulo);
            }
        }
    }

    public void update_lab_estudio(lab_estudio lab_es, JTable tbltabla) {
        if (evmen.MensajeGeneral_warning("ESTAS SEGURO DE MODIFICAR LAB_ESTUDIO", "MODIFICAR", "ACEPTAR", "CANCELAR")) {
            String titulo = "update_lab_estudio";
            Connection conn = ConnPostgres.getConnPosgres();
            try {
                if (conn.getAutoCommit()) {
                    conn.setAutoCommit(false);
                }
                lab_es_dao.update_lab_estudio(conn, lab_es);
//                lab_es_dao.actualizar_tabla_lab_estudio(conn, tbltabla);
                conn.commit();
            } catch (SQLException e) {
                evmen.mensaje_error(e, lab_es.toString(), titulo);
                try {
                    conn.rollback();
                } catch (SQLException e1) {
                    evmen.Imprimir_serial_sql_error(e1, lab_es.toString(), titulo);
                }
            }
        }
    }
}
