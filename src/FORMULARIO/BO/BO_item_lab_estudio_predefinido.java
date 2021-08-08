package FORMULARIO.BO;

import BASEDATO.LOCAL.ConnPostgres;
import Evento.Mensaje.EvenMensajeJoptionpane;
import FORMULARIO.DAO.DAO_item_lab_estudio_predefinido;
import FORMULARIO.ENTIDAD.item_lab_estudio_predefinido;
import java.sql.Connection;
import java.sql.SQLException;
import javax.swing.JTable;

public class BO_item_lab_estudio_predefinido {

    private DAO_item_lab_estudio_predefinido ilep_dao = new DAO_item_lab_estudio_predefinido();
    EvenMensajeJoptionpane evmen = new EvenMensajeJoptionpane();

    public void insertar_item_lab_estudio_predefinido(item_lab_estudio_predefinido ilep) {
        String titulo = "insertar_item_lab_estudio_predefinido";
        Connection conn = ConnPostgres.getConnPosgres();
        try {
            if (conn.getAutoCommit()) {
                conn.setAutoCommit(false);
            }
            ilep_dao.insertar_item_lab_estudio_predefinido(conn, ilep);
//            ilep_dao.actualizar_tabla_item_lab_estudio_predefinido(conn, tbltabla);
            conn.commit();
        } catch (SQLException e) {
            evmen.mensaje_error(e, ilep.toString(), titulo);
            try {
                conn.rollback();
            } catch (SQLException e1) {
                evmen.Imprimir_serial_sql_error(e1, ilep.toString(), titulo);
            }
        }
    }

    public void update_item_lab_estudio_predefinido(item_lab_estudio_predefinido ilep, JTable tbltabla) {
        if (evmen.MensajeGeneral_warning("ESTAS SEGURO DE MODIFICAR ITEM_LAB_ESTUDIO_PREDEFINIDO", "MODIFICAR", "ACEPTAR", "CANCELAR")) {
            String titulo = "update_item_lab_estudio_predefinido";
            Connection conn = ConnPostgres.getConnPosgres();
            try {
                if (conn.getAutoCommit()) {
                    conn.setAutoCommit(false);
                }
                ilep_dao.update_item_lab_estudio_predefinido(conn, ilep);
//                ilep_dao.actualizar_tabla_item_lab_estudio_predefinido(conn, tbltabla);
                conn.commit();
            } catch (SQLException e) {
                evmen.mensaje_error(e, ilep.toString(), titulo);
                try {
                    conn.rollback();
                } catch (SQLException e1) {
                    evmen.Imprimir_serial_sql_error(e1, ilep.toString(), titulo);
                }
            }
        }
    }
}
