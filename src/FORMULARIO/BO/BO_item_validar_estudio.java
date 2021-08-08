	package FORMULARIO.BO;
	import BASEDATO.LOCAL.ConnPostgres;
	import Evento.Mensaje.EvenMensajeJoptionpane;
	import FORMULARIO.DAO.DAO_item_validar_estudio;
	import FORMULARIO.ENTIDAD.item_validar_estudio;
	import java.sql.Connection;
	import java.sql.SQLException;
	import javax.swing.JTable;
public class BO_item_validar_estudio {
private DAO_item_validar_estudio ivali_dao = new DAO_item_validar_estudio();
	EvenMensajeJoptionpane evmen = new EvenMensajeJoptionpane();

	public void insertar_item_validar_estudio(item_validar_estudio ivali, JTable tbltabla) {
		String titulo = "insertar_item_validar_estudio";
		Connection conn = ConnPostgres.getConnPosgres();
		try {
			if (conn.getAutoCommit()) {
				conn.setAutoCommit(false);
			}
			ivali_dao.insertar_item_validar_estudio(conn, ivali);
			ivali_dao.actualizar_tabla_item_validar_estudio(conn, tbltabla);
			conn.commit();
		} catch (SQLException e) {
			evmen.mensaje_error(e, ivali.toString(), titulo);
			try {
				conn.rollback();
			} catch (SQLException e1) {
				evmen.Imprimir_serial_sql_error(e1, ivali.toString(), titulo);
			}
		}
	}
	public void update_item_validar_estudio(item_validar_estudio ivali, JTable tbltabla) {
		if (evmen.MensajeGeneral_warning("ESTAS SEGURO DE MODIFICAR ITEM_VALIDAR_ESTUDIO", "MODIFICAR", "ACEPTAR", "CANCELAR")) {
			String titulo = "update_item_validar_estudio";
			Connection conn = ConnPostgres.getConnPosgres();
			try {
				if (conn.getAutoCommit()) {
					conn.setAutoCommit(false);
				}
				ivali_dao.update_item_validar_estudio(conn, ivali);
				ivali_dao.actualizar_tabla_item_validar_estudio(conn, tbltabla);
				conn.commit();
			} catch (SQLException e) {
				evmen.mensaje_error(e, ivali.toString(), titulo);
				try {
					conn.rollback();
				}catch (SQLException e1) {
					evmen.Imprimir_serial_sql_error(e1, ivali.toString(), titulo);
				}
			}
		}
	}
}
