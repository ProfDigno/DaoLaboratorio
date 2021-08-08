	package FORMULARIO.BO;
	import BASEDATO.LOCAL.ConnPostgres;
	import Evento.Mensaje.EvenMensajeJoptionpane;
	import FORMULARIO.DAO.DAO_validar_estudio;
	import FORMULARIO.ENTIDAD.validar_estudio;
	import java.sql.Connection;
	import java.sql.SQLException;
	import javax.swing.JTable;
public class BO_validar_estudio {
private DAO_validar_estudio vali_dao = new DAO_validar_estudio();
	EvenMensajeJoptionpane evmen = new EvenMensajeJoptionpane();

	public void insertar_validar_estudio(validar_estudio vali, JTable tbltabla) {
		String titulo = "insertar_validar_estudio";
		Connection conn = ConnPostgres.getConnPosgres();
		try {
			if (conn.getAutoCommit()) {
				conn.setAutoCommit(false);
			}
			vali_dao.insertar_validar_estudio(conn, vali);
			vali_dao.actualizar_tabla_validar_estudio(conn, tbltabla);
			conn.commit();
		} catch (SQLException e) {
			evmen.mensaje_error(e, vali.toString(), titulo);
			try {
				conn.rollback();
			} catch (SQLException e1) {
				evmen.Imprimir_serial_sql_error(e1, vali.toString(), titulo);
			}
		}
	}
	public void update_validar_estudio(validar_estudio vali, JTable tbltabla) {
		if (evmen.MensajeGeneral_warning("ESTAS SEGURO DE MODIFICAR VALIDAR_ESTUDIO", "MODIFICAR", "ACEPTAR", "CANCELAR")) {
			String titulo = "update_validar_estudio";
			Connection conn = ConnPostgres.getConnPosgres();
			try {
				if (conn.getAutoCommit()) {
					conn.setAutoCommit(false);
				}
				vali_dao.update_validar_estudio(conn, vali);
				vali_dao.actualizar_tabla_validar_estudio(conn, tbltabla);
				conn.commit();
			} catch (SQLException e) {
				evmen.mensaje_error(e, vali.toString(), titulo);
				try {
					conn.rollback();
				}catch (SQLException e1) {
					evmen.Imprimir_serial_sql_error(e1, vali.toString(), titulo);
				}
			}
		}
	}
}
