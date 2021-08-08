	package FORMULARIO.BO;
	import BASEDATO.LOCAL.ConnPostgres;
	import Evento.Mensaje.EvenMensajeJoptionpane;
	import FORMULARIO.DAO.DAO_direc_barrio;
	import FORMULARIO.ENTIDAD.direc_barrio;
	import java.sql.Connection;
	import java.sql.SQLException;
	import javax.swing.JTable;
public class BO_direc_barrio {
private DAO_direc_barrio d_ba_dao = new DAO_direc_barrio();
	EvenMensajeJoptionpane evmen = new EvenMensajeJoptionpane();

	public void insertar_direc_barrio(direc_barrio d_ba, JTable tbltabla) {
		String titulo = "insertar_direc_barrio";
		Connection conn = ConnPostgres.getConnPosgres();
		try {
			if (conn.getAutoCommit()) {
				conn.setAutoCommit(false);
			}
			d_ba_dao.insertar_direc_barrio(conn, d_ba);
			d_ba_dao.actualizar_tabla_direc_barrio(conn, tbltabla);
			conn.commit();
		} catch (SQLException e) {
			evmen.mensaje_error(e, d_ba.toString(), titulo);
			try {
				conn.rollback();
			} catch (SQLException e1) {
				evmen.Imprimir_serial_sql_error(e1, d_ba.toString(), titulo);
			}
		}
	}
	public void update_direc_barrio(direc_barrio d_ba, JTable tbltabla) {
		if (evmen.MensajeGeneral_warning("ESTAS SEGURO DE MODIFICAR DIREC_BARRIO", "MODIFICAR", "ACEPTAR", "CANCELAR")) {
			String titulo = "update_direc_barrio";
			Connection conn = ConnPostgres.getConnPosgres();
			try {
				if (conn.getAutoCommit()) {
					conn.setAutoCommit(false);
				}
				d_ba_dao.update_direc_barrio(conn, d_ba);
				d_ba_dao.actualizar_tabla_direc_barrio(conn, tbltabla);
				conn.commit();
			} catch (SQLException e) {
				evmen.mensaje_error(e, d_ba.toString(), titulo);
				try {
					conn.rollback();
				}catch (SQLException e1) {
					evmen.Imprimir_serial_sql_error(e1, d_ba.toString(), titulo);
				}
			}
		}
	}
}
