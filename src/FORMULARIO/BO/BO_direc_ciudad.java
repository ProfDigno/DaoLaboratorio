	package FORMULARIO.BO;
	import BASEDATO.LOCAL.ConnPostgres;
	import Evento.Mensaje.EvenMensajeJoptionpane;
	import FORMULARIO.DAO.DAO_direc_ciudad;
	import FORMULARIO.ENTIDAD.direc_ciudad;
	import java.sql.Connection;
	import java.sql.SQLException;
	import javax.swing.JTable;
public class BO_direc_ciudad {
private DAO_direc_ciudad dciu_dao = new DAO_direc_ciudad();
	EvenMensajeJoptionpane evmen = new EvenMensajeJoptionpane();

	public void insertar_direc_ciudad(direc_ciudad dciu, JTable tbltabla) {
		String titulo = "insertar_direc_ciudad";
		Connection conn = ConnPostgres.getConnPosgres();
		try {
			if (conn.getAutoCommit()) {
				conn.setAutoCommit(false);
			}
			dciu_dao.insertar_direc_ciudad(conn, dciu);
			dciu_dao.actualizar_tabla_direc_ciudad(conn, tbltabla);
			conn.commit();
		} catch (SQLException e) {
			evmen.mensaje_error(e, dciu.toString(), titulo);
			try {
				conn.rollback();
			} catch (SQLException e1) {
				evmen.Imprimir_serial_sql_error(e1, dciu.toString(), titulo);
			}
		}
	}
	public void update_direc_ciudad(direc_ciudad dciu, JTable tbltabla) {
		if (evmen.MensajeGeneral_warning("ESTAS SEGURO DE MODIFICAR DIREC_CIUDAD", "MODIFICAR", "ACEPTAR", "CANCELAR")) {
			String titulo = "update_direc_ciudad";
			Connection conn = ConnPostgres.getConnPosgres();
			try {
				if (conn.getAutoCommit()) {
					conn.setAutoCommit(false);
				}
				dciu_dao.update_direc_ciudad(conn, dciu);
				dciu_dao.actualizar_tabla_direc_ciudad(conn, tbltabla);
				conn.commit();
			} catch (SQLException e) {
				evmen.mensaje_error(e, dciu.toString(), titulo);
				try {
					conn.rollback();
				}catch (SQLException e1) {
					evmen.Imprimir_serial_sql_error(e1, dciu.toString(), titulo);
				}
			}
		}
	}
}
