	package FORMULARIO.BO;
	import BASEDATO.LOCAL.ConnPostgres;
	import Evento.Mensaje.EvenMensajeJoptionpane;
	import FORMULARIO.DAO.DAO_tipo_seguro;
	import FORMULARIO.ENTIDAD.tipo_seguro;
	import java.sql.Connection;
	import java.sql.SQLException;
	import javax.swing.JTable;
public class BO_tipo_seguro {
private DAO_tipo_seguro t_seg_dao = new DAO_tipo_seguro();
	EvenMensajeJoptionpane evmen = new EvenMensajeJoptionpane();

	public void insertar_tipo_seguro(tipo_seguro t_seg, JTable tbltabla) {
		String titulo = "insertar_tipo_seguro";
		Connection conn = ConnPostgres.getConnPosgres();
		try {
			if (conn.getAutoCommit()) {
				conn.setAutoCommit(false);
			}
			t_seg_dao.insertar_tipo_seguro(conn, t_seg);
			t_seg_dao.actualizar_tabla_tipo_seguro(conn, tbltabla);
			conn.commit();
		} catch (SQLException e) {
			evmen.mensaje_error(e, t_seg.toString(), titulo);
			try {
				conn.rollback();
			} catch (SQLException e1) {
				evmen.Imprimir_serial_sql_error(e1, t_seg.toString(), titulo);
			}
		}
	}
	public void update_tipo_seguro(tipo_seguro t_seg, JTable tbltabla) {
		if (evmen.MensajeGeneral_warning("ESTAS SEGURO DE MODIFICAR TIPO_SEGURO", "MODIFICAR", "ACEPTAR", "CANCELAR")) {
			String titulo = "update_tipo_seguro";
			Connection conn = ConnPostgres.getConnPosgres();
			try {
				if (conn.getAutoCommit()) {
					conn.setAutoCommit(false);
				}
				t_seg_dao.update_tipo_seguro(conn, t_seg);
				t_seg_dao.actualizar_tabla_tipo_seguro(conn, tbltabla);
				conn.commit();
			} catch (SQLException e) {
				evmen.mensaje_error(e, t_seg.toString(), titulo);
				try {
					conn.rollback();
				}catch (SQLException e1) {
					evmen.Imprimir_serial_sql_error(e1, t_seg.toString(), titulo);
				}
			}
		}
	}
}
