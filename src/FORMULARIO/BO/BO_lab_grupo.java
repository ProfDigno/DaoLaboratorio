	package FORMULARIO.BO;
	import BASEDATO.LOCAL.ConnPostgres;
	import Evento.Mensaje.EvenMensajeJoptionpane;
	import FORMULARIO.DAO.DAO_lab_grupo;
	import FORMULARIO.ENTIDAD.lab_grupo;
	import java.sql.Connection;
	import java.sql.SQLException;
	import javax.swing.JTable;
public class BO_lab_grupo {
private DAO_lab_grupo lab_g_dao = new DAO_lab_grupo();
	EvenMensajeJoptionpane evmen = new EvenMensajeJoptionpane();

	public void insertar_lab_grupo(lab_grupo lab_g, JTable tbltabla) {
		String titulo = "insertar_lab_grupo";
		Connection conn = ConnPostgres.getConnPosgres();
		try {
			if (conn.getAutoCommit()) {
				conn.setAutoCommit(false);
			}
			lab_g_dao.insertar_lab_grupo(conn, lab_g);
			lab_g_dao.actualizar_tabla_lab_grupo(conn, tbltabla);
			conn.commit();
		} catch (SQLException e) {
			evmen.mensaje_error(e, lab_g.toString(), titulo);
			try {
				conn.rollback();
			} catch (SQLException e1) {
				evmen.Imprimir_serial_sql_error(e1, lab_g.toString(), titulo);
			}
		}
	}
	public void update_lab_grupo(lab_grupo lab_g, JTable tbltabla) {
		if (evmen.MensajeGeneral_warning("ESTAS SEGURO DE MODIFICAR LAB_GRUPO", "MODIFICAR", "ACEPTAR", "CANCELAR")) {
			String titulo = "update_lab_grupo";
			Connection conn = ConnPostgres.getConnPosgres();
			try {
				if (conn.getAutoCommit()) {
					conn.setAutoCommit(false);
				}
				lab_g_dao.update_lab_grupo(conn, lab_g);
				lab_g_dao.actualizar_tabla_lab_grupo(conn, tbltabla);
				conn.commit();
			} catch (SQLException e) {
				evmen.mensaje_error(e, lab_g.toString(), titulo);
				try {
					conn.rollback();
				}catch (SQLException e1) {
					evmen.Imprimir_serial_sql_error(e1, lab_g.toString(), titulo);
				}
			}
		}
	}
}
