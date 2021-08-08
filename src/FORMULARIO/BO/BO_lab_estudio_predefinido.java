	package FORMULARIO.BO;
	import BASEDATO.LOCAL.ConnPostgres;
	import Evento.Mensaje.EvenMensajeJoptionpane;
	import FORMULARIO.DAO.DAO_lab_estudio_predefinido;
	import FORMULARIO.ENTIDAD.lab_estudio_predefinido;
	import java.sql.Connection;
	import java.sql.SQLException;
	import javax.swing.JTable;
public class BO_lab_estudio_predefinido {
private DAO_lab_estudio_predefinido lep_dao = new DAO_lab_estudio_predefinido();
	EvenMensajeJoptionpane evmen = new EvenMensajeJoptionpane();

	public void insertar_lab_estudio_predefinido(lab_estudio_predefinido lep, JTable tbltabla) {
		String titulo = "insertar_lab_estudio_predefinido";
		Connection conn = ConnPostgres.getConnPosgres();
		try {
			if (conn.getAutoCommit()) {
				conn.setAutoCommit(false);
			}
			lep_dao.insertar_lab_estudio_predefinido(conn, lep);
			lep_dao.actualizar_tabla_lab_estudio_predefinido(conn, tbltabla);
			conn.commit();
		} catch (SQLException e) {
			evmen.mensaje_error(e, lep.toString(), titulo);
			try {
				conn.rollback();
			} catch (SQLException e1) {
				evmen.Imprimir_serial_sql_error(e1, lep.toString(), titulo);
			}
		}
	}
	public void update_lab_estudio_predefinido(lab_estudio_predefinido lep, JTable tbltabla) {
		if (evmen.MensajeGeneral_warning("ESTAS SEGURO DE MODIFICAR LAB_ESTUDIO_PREDEFINIDO", "MODIFICAR", "ACEPTAR", "CANCELAR")) {
			String titulo = "update_lab_estudio_predefinido";
			Connection conn = ConnPostgres.getConnPosgres();
			try {
				if (conn.getAutoCommit()) {
					conn.setAutoCommit(false);
				}
				lep_dao.update_lab_estudio_predefinido(conn, lep);
				lep_dao.actualizar_tabla_lab_estudio_predefinido(conn, tbltabla);
				conn.commit();
			} catch (SQLException e) {
				evmen.mensaje_error(e, lep.toString(), titulo);
				try {
					conn.rollback();
				}catch (SQLException e1) {
					evmen.Imprimir_serial_sql_error(e1, lep.toString(), titulo);
				}
			}
		}
	}
}
