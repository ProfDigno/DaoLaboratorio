package FORMULARIO.BO;

import BASEDATO.LOCAL.ConnPostgres;
import Evento.Mensaje.EvenMensajeJoptionpane;
import FORMULARIO.DAO.DAO_item_lab_estudio;
import FORMULARIO.ENTIDAD.item_lab_estudio;
import java.sql.Connection;
import java.sql.SQLException;
import javax.swing.JTable;

public class BO_item_lab_estudio {

    private DAO_item_lab_estudio ilab_es_dao = new DAO_item_lab_estudio();
    EvenMensajeJoptionpane evmen = new EvenMensajeJoptionpane();

    public void insertar_item_lab_estudio(item_lab_estudio ilab_es) {
        String titulo = "insertar_item_lab_estudio";
        Connection conn = ConnPostgres.getConnPosgres();
        try {
            if (conn.getAutoCommit()) {
                conn.setAutoCommit(false);
            }
            ilab_es_dao.insertar_item_lab_estudio(conn, ilab_es);
//            ilab_es_dao.actualizar_tabla_item_lab_estudio(conn, tbltabla);
            conn.commit();
        } catch (SQLException e) {
            evmen.mensaje_error(e, ilab_es.toString(), titulo);
            try {
                conn.rollback();
            } catch (SQLException e1) {
                evmen.Imprimir_serial_sql_error(e1, ilab_es.toString(), titulo);
            }
        }
    }

    public void update_item_lab_estudio(item_lab_estudio ilab_es, JTable tbltabla) {
        if (evmen.MensajeGeneral_warning("ESTAS SEGURO DE MODIFICAR ITEM_LAB_ESTUDIO", "MODIFICAR", "ACEPTAR", "CANCELAR")) {
            String titulo = "update_item_lab_estudio";
            Connection conn = ConnPostgres.getConnPosgres();
            try {
                if (conn.getAutoCommit()) {
                    conn.setAutoCommit(false);
                }
                ilab_es_dao.update_item_lab_estudio(conn, ilab_es);
                ilab_es_dao.actualizar_tabla_item_lab_estudio(conn, tbltabla);
                conn.commit();
            } catch (SQLException e) {
                evmen.mensaje_error(e, ilab_es.toString(), titulo);
                try {
                    conn.rollback();
                } catch (SQLException e1) {
                    evmen.Imprimir_serial_sql_error(e1, ilab_es.toString(), titulo);
                }
            }
        }
    }
    public void update_item_lab_estudio_orden(item_lab_estudio ilab_es) {
            String titulo = "update_item_lab_estudio_orden";
            Connection conn = ConnPostgres.getConnPosgres();
            try {
                if (conn.getAutoCommit()) {
                    conn.setAutoCommit(false);
                }
                ilab_es_dao.update_item_lab_estudio_orden(conn, ilab_es);
                conn.commit();
            } catch (SQLException e) {
                evmen.mensaje_error(e, ilab_es.toString(), titulo);
                try {
                    conn.rollback();
                } catch (SQLException e1) {
                    evmen.Imprimir_serial_sql_error(e1, ilab_es.toString(), titulo);
                }
            }
    }
    public void delete_item_lab_estudio_orden_por_estudio(Connection conn,int fk_idlab_estudio,int fk_idlab_grupo) {
         if (evmen.MensajeGeneral_warning("ESTAS SEGURO DE ELIMINAR ITEM_LAB_ESTUDIO", "ELIMINAR", "ELIMINAR", "CANCELAR")) {
            String titulo = "delete_item_lab_estudio_orden_por_estudio";
//            Connection conn = ConnPostgres.getConnPosgres();
            try {
                if (conn.getAutoCommit()) {
                    conn.setAutoCommit(false);
                }
                ilab_es_dao.delete_item_lab_estudio_orden_por_estudio(conn, fk_idlab_estudio, fk_idlab_grupo);
                conn.commit();
            } catch (SQLException e) {
                evmen.mensaje_error(e,"error1", titulo);
                try {
                    conn.rollback();
                } catch (SQLException e1) {
                    evmen.Imprimir_serial_sql_error(e1, "error2", titulo);
                }
            }
         }
    }
}
