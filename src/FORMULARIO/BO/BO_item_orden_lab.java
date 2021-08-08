package FORMULARIO.BO;

import BASEDATO.LOCAL.ConnPostgres;
import Evento.Mensaje.EvenMensajeJoptionpane;
import FORMULARIO.DAO.DAO_item_orden_lab;
import FORMULARIO.ENTIDAD.item_orden_lab;
import java.sql.Connection;
import java.sql.SQLException;
import javax.swing.JTable;

public class BO_item_orden_lab {

    private DAO_item_orden_lab iord_dao = new DAO_item_orden_lab();
    EvenMensajeJoptionpane evmen = new EvenMensajeJoptionpane();

    public void insertar_item_orden_lab(item_orden_lab iord, JTable tbltabla) {
        String titulo = "insertar_item_orden_lab";
        Connection conn = ConnPostgres.getConnPosgres();
        try {
            if (conn.getAutoCommit()) {
                conn.setAutoCommit(false);
            }
//			iord_dao.insertar_item_orden_lab(conn, iord);
            iord_dao.actualizar_tabla_item_orden_lab(conn, tbltabla);
            conn.commit();
        } catch (SQLException e) {
            evmen.mensaje_error(e, iord.toString(), titulo);
            try {
                conn.rollback();
            } catch (SQLException e1) {
                evmen.Imprimir_serial_sql_error(e1, iord.toString(), titulo);
            }
        }
    }

    public void update_item_orden_lab(item_orden_lab iord,int tipo) {
//        if (evmen.MensajeGeneral_warning("ESTAS SEGURO DE MODIFICAR ITEM_ORDEN_LAB", "MODIFICAR", "ACEPTAR", "CANCELAR")) {
            String titulo = "update_item_orden_lab";
            Connection conn = ConnPostgres.getConnPosgres();
            try {
                if (conn.getAutoCommit()) {
                    conn.setAutoCommit(false);
                }
                if(tipo==1){
                iord_dao.update_item_orden_lab_cargado_numerico(conn, iord);
                }if(tipo==2){
                iord_dao.update_item_orden_lab_cargado_testo(conn, iord);
                }if(tipo==3){
                iord_dao.update_item_orden_lab_cargado_predefino(conn, iord);
                }
//                iord_dao.actualizar_tabla_item_orden_lab(conn, tbltabla);
                conn.commit();
            } catch (SQLException e) {
                evmen.mensaje_error(e, iord.toString(), titulo);
                try {
                    conn.rollback();
                } catch (SQLException e1) {
                    evmen.Imprimir_serial_sql_error(e1, iord.toString(), titulo);
                }
            }
//        }
    }
}
