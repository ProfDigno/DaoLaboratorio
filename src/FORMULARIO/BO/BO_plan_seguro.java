	package FORMULARIO.BO;
	import BASEDATO.LOCAL.ConnPostgres;
	import Evento.Mensaje.EvenMensajeJoptionpane;
	import FORMULARIO.DAO.DAO_plan_seguro;
	import FORMULARIO.ENTIDAD.plan_seguro;
	import java.sql.Connection;
	import java.sql.SQLException;
	import javax.swing.JTable;
public class BO_plan_seguro {
private DAO_plan_seguro p_seg_dao = new DAO_plan_seguro();
	EvenMensajeJoptionpane evmen = new EvenMensajeJoptionpane();

	public void insertar_plan_seguro(plan_seguro p_seg, JTable tbltabla) {
		String titulo = "insertar_plan_seguro";
		Connection conn = ConnPostgres.getConnPosgres();
		try {
			if (conn.getAutoCommit()) {
				conn.setAutoCommit(false);
			}
			p_seg_dao.insertar_plan_seguro(conn, p_seg);
			p_seg_dao.actualizar_tabla_plan_seguro(conn, tbltabla);
			conn.commit();
		} catch (SQLException e) {
			evmen.mensaje_error(e, p_seg.toString(), titulo);
			try {
				conn.rollback();
			} catch (SQLException e1) {
				evmen.Imprimir_serial_sql_error(e1, p_seg.toString(), titulo);
			}
		}
	}
	public void update_plan_seguro(plan_seguro p_seg, JTable tbltabla) {
		if (evmen.MensajeGeneral_warning("ESTAS SEGURO DE MODIFICAR PLAN_SEGURO", "MODIFICAR", "ACEPTAR", "CANCELAR")) {
			String titulo = "update_plan_seguro";
			Connection conn = ConnPostgres.getConnPosgres();
			try {
				if (conn.getAutoCommit()) {
					conn.setAutoCommit(false);
				}
				p_seg_dao.update_plan_seguro(conn, p_seg);
				p_seg_dao.actualizar_tabla_plan_seguro(conn, tbltabla);
				conn.commit();
			} catch (SQLException e) {
				evmen.mensaje_error(e, p_seg.toString(), titulo);
				try {
					conn.rollback();
				}catch (SQLException e1) {
					evmen.Imprimir_serial_sql_error(e1, p_seg.toString(), titulo);
				}
			}
		}
	}
}
