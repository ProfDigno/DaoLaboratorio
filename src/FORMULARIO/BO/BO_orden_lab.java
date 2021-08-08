package FORMULARIO.BO;

import BASEDATO.LOCAL.ConnPostgres;
import Evento.Mensaje.EvenMensajeJoptionpane;
import FORMULARIO.DAO.DAO_item_orden_lab;
import FORMULARIO.DAO.DAO_orden_lab;
import FORMULARIO.ENTIDAD.orden_lab;
import java.sql.Connection;
import java.sql.SQLException;
import javax.swing.JTable;

public class BO_orden_lab {

    private DAO_orden_lab ord_dao = new DAO_orden_lab();
    private DAO_item_orden_lab iord_dao=new DAO_item_orden_lab();
    EvenMensajeJoptionpane evmen = new EvenMensajeJoptionpane();

    public void insertar_orden_lab(orden_lab ord,JTable tblitem_orden_lab) {
        String titulo = "insertar_orden_lab";
        Connection conn = ConnPostgres.getConnPosgres();
        try {
            if (conn.getAutoCommit()) {
                conn.setAutoCommit(false);
            }
            ord_dao.insertar_orden_lab(conn, ord);
            iord_dao.insertar_item_orden_lab_de_orden(conn, tblitem_orden_lab, ord);
//            ord_dao.actualizar_tabla_orden_lab(conn, tbltabla);
            conn.commit();
        } catch (SQLException e) {
            evmen.mensaje_error(e, ord.toString(), titulo);
            try {
                conn.rollback();
            } catch (SQLException e1) {
                evmen.Imprimir_serial_sql_error(e1, ord.toString(), titulo);
            }
        }
    }

    public void update_orden_lab(orden_lab ord, JTable tbltabla) {
        if (evmen.MensajeGeneral_warning("ESTAS SEGURO DE MODIFICAR ORDEN_LAB", "MODIFICAR", "ACEPTAR", "CANCELAR")) {
            String titulo = "update_orden_lab";
            Connection conn = ConnPostgres.getConnPosgres();
            try {
                if (conn.getAutoCommit()) {
                    conn.setAutoCommit(false);
                }
                ord_dao.update_orden_lab(conn, ord);
                ord_dao.actualizar_tabla_orden_lab(conn, tbltabla);
                conn.commit();
            } catch (SQLException e) {
                evmen.mensaje_error(e, ord.toString(), titulo);
                try {
                    conn.rollback();
                } catch (SQLException e1) {
                    evmen.Imprimir_serial_sql_error(e1, ord.toString(), titulo);
                }
            }
        }
    }
    public void update_orden_lab_estado(orden_lab ord,String estado) {
        if (evmen.MensajeGeneral_warning("ESTAS SEGURO DE PASAR ORDEN COMO "+estado, "MODIFICAR ORDEN", "ACEPTAR", "CANCELAR")) {
            String titulo = "update_orden_lab_estado";
            Connection conn = ConnPostgres.getConnPosgres();
            try {
                if (conn.getAutoCommit()) {
                    conn.setAutoCommit(false);
                }
                ord_dao.update_orden_lab_estado(conn, ord);
                conn.commit();
            } catch (SQLException e) {
                evmen.mensaje_error(e, ord.toString(), titulo);
                try {
                    conn.rollback();
                } catch (SQLException e1) {
                    evmen.Imprimir_serial_sql_error(e1, ord.toString(), titulo);
                }
            }
        }
    }
}
