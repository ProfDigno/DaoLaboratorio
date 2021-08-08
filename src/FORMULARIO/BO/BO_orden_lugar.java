	package FORMULARIO.BO;
	import BASEDATO.LOCAL.ConnPostgres;
	import Evento.Mensaje.EvenMensajeJoptionpane;
	import FORMULARIO.DAO.DAO_orden_lugar;
	import FORMULARIO.ENTIDAD.orden_lugar;
	import java.sql.Connection;
	import java.sql.SQLException;
	import javax.swing.JTable;
public class BO_orden_lugar {
private DAO_orden_lugar orlu_dao = new DAO_orden_lugar();
	EvenMensajeJoptionpane evmen = new EvenMensajeJoptionpane();

	public void insertar_orden_lugar(orden_lugar orlu, JTable tbltabla) {
		String titulo = "insertar_orden_lugar";
		Connection conn = ConnPostgres.getConnPosgres();
		try {
			if (conn.getAutoCommit()) {
				conn.setAutoCommit(false);
			}
			orlu_dao.insertar_orden_lugar(conn, orlu);
			orlu_dao.actualizar_tabla_orden_lugar(conn, tbltabla);
			conn.commit();
		} catch (SQLException e) {
			evmen.mensaje_error(e, orlu.toString(), titulo);
			try {
				conn.rollback();
			} catch (SQLException e1) {
				evmen.Imprimir_serial_sql_error(e1, orlu.toString(), titulo);
			}
		}
	}
	public void update_orden_lugar(orden_lugar orlu, JTable tbltabla) {
		if (evmen.MensajeGeneral_warning("ESTAS SEGURO DE MODIFICAR ORDEN_LUGAR", "MODIFICAR", "ACEPTAR", "CANCELAR")) {
			String titulo = "update_orden_lugar";
			Connection conn = ConnPostgres.getConnPosgres();
			try {
				if (conn.getAutoCommit()) {
					conn.setAutoCommit(false);
				}
				orlu_dao.update_orden_lugar(conn, orlu);
				orlu_dao.actualizar_tabla_orden_lugar(conn, tbltabla);
				conn.commit();
			} catch (SQLException e) {
				evmen.mensaje_error(e, orlu.toString(), titulo);
				try {
					conn.rollback();
				}catch (SQLException e1) {
					evmen.Imprimir_serial_sql_error(e1, orlu.toString(), titulo);
				}
			}
		}
	}
}
