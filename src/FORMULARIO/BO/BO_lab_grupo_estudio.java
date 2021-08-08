	package FORMULARIO.BO;
	import BASEDATO.LOCAL.ConnPostgres;
	import Evento.Mensaje.EvenMensajeJoptionpane;
	import FORMULARIO.DAO.DAO_lab_grupo_estudio;
	import FORMULARIO.ENTIDAD.lab_grupo_estudio;
	import java.sql.Connection;
	import java.sql.SQLException;
	import javax.swing.JTable;
public class BO_lab_grupo_estudio {
private DAO_lab_grupo_estudio lab_ge_dao = new DAO_lab_grupo_estudio();
	EvenMensajeJoptionpane evmen = new EvenMensajeJoptionpane();

	public void insertar_lab_grupo_estudio(lab_grupo_estudio lab_ge, JTable tbltabla) {
		String titulo = "insertar_lab_grupo_estudio";
		Connection conn = ConnPostgres.getConnPosgres();
		try {
			if (conn.getAutoCommit()) {
				conn.setAutoCommit(false);
			}
			lab_ge_dao.insertar_lab_grupo_estudio(conn, lab_ge);
			lab_ge_dao.actualizar_tabla_lab_grupo_estudio(conn, tbltabla);
			conn.commit();
		} catch (SQLException e) {
			evmen.mensaje_error(e, lab_ge.toString(), titulo);
			try {
				conn.rollback();
			} catch (SQLException e1) {
				evmen.Imprimir_serial_sql_error(e1, lab_ge.toString(), titulo);
			}
		}
	}
	public void update_lab_grupo_estudio(lab_grupo_estudio lab_ge, JTable tbltabla) {
		if (evmen.MensajeGeneral_warning("ESTAS SEGURO DE MODIFICAR LAB_GRUPO_ESTUDIO", "MODIFICAR", "ACEPTAR", "CANCELAR")) {
			String titulo = "update_lab_grupo_estudio";
			Connection conn = ConnPostgres.getConnPosgres();
			try {
				if (conn.getAutoCommit()) {
					conn.setAutoCommit(false);
				}
				lab_ge_dao.update_lab_grupo_estudio(conn, lab_ge);
				lab_ge_dao.actualizar_tabla_lab_grupo_estudio(conn, tbltabla);
				conn.commit();
			} catch (SQLException e) {
				evmen.mensaje_error(e, lab_ge.toString(), titulo);
				try {
					conn.rollback();
				}catch (SQLException e1) {
					evmen.Imprimir_serial_sql_error(e1, lab_ge.toString(), titulo);
				}
			}
		}
	}
}
