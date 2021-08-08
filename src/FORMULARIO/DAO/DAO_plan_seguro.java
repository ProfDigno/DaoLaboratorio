	package FORMULARIO.DAO;
	import BASEDATO.EvenConexion;
	import FORMULARIO.ENTIDAD.plan_seguro;
	import Evento.JasperReport.EvenJasperReport;
	import Evento.Jtable.EvenJtable;
	import Evento.Mensaje.EvenMensajeJoptionpane;
	import Evento.Fecha.EvenFecha;
	import java.sql.ResultSet;
	import java.sql.Connection;
	import java.sql.PreparedStatement;
	import javax.swing.JTable;
public class DAO_plan_seguro {
	EvenConexion eveconn = new EvenConexion();
	EvenJtable evejt = new EvenJtable();
	EvenJasperReport rep = new EvenJasperReport();
	EvenMensajeJoptionpane evemen = new EvenMensajeJoptionpane();
	EvenFecha evefec = new EvenFecha();
	private String mensaje_insert = "PLAN_SEGURO GUARDADO CORRECTAMENTE";
	private String mensaje_update = "PLAN_SEGURO MODIFICADO CORECTAMENTE";
	private String sql_insert = "INSERT INTO plan_seguro(idplan_seguro,nombre) VALUES (?,?);";
	private String sql_update = "UPDATE plan_seguro SET nombre=? WHERE idplan_seguro=?;";
	private String sql_select = "SELECT idplan_seguro,nombre FROM plan_seguro order by 1 desc;";
	private String sql_cargar = "SELECT idplan_seguro,nombre FROM plan_seguro WHERE idplan_seguro=";
	public void insertar_plan_seguro(Connection conn, plan_seguro p_seg){
		p_seg.setC1idplan_seguro(eveconn.getInt_ultimoID_mas_uno(conn, p_seg.getTb_plan_seguro(), p_seg.getId_idplan_seguro()));
		String titulo = "insertar_plan_seguro";
		PreparedStatement pst = null;
		try {
			pst = conn.prepareStatement(sql_insert);
			pst.setInt(1,p_seg.getC1idplan_seguro());
			pst.setString(2,p_seg.getC2nombre());
			pst.execute();
			pst.close();
			evemen.Imprimir_serial_sql(sql_insert + "\n" + p_seg.toString(), titulo);
			evemen.guardado_correcto(mensaje_insert, true);
		} catch (Exception e) {
			evemen.mensaje_error(e, sql_insert + "\n" + p_seg.toString(), titulo);
		}
	}
	public void update_plan_seguro(Connection conn, plan_seguro p_seg){
		String titulo = "update_plan_seguro";
		PreparedStatement pst = null;
		try {
			pst = conn.prepareStatement(sql_update);
			pst.setString(1,p_seg.getC2nombre());
			pst.setInt(2,p_seg.getC1idplan_seguro());
			pst.execute();
			pst.close();
			evemen.Imprimir_serial_sql(sql_update + "\n" + p_seg.toString(), titulo);
			evemen.modificado_correcto(mensaje_update, true);
		} catch (Exception e) {
			evemen.mensaje_error(e, sql_update + "\n" + p_seg.toString(), titulo);
		}
	}
	public void cargar_plan_seguro(Connection conn, plan_seguro p_seg,int id){
		String titulo = "Cargar_plan_seguro";
		try {
			ResultSet rs=eveconn.getResulsetSQL(conn,sql_cargar+id, titulo);
			if(rs.next()){
				p_seg.setC1idplan_seguro(rs.getInt(1));
				p_seg.setC2nombre(rs.getString(2));
				evemen.Imprimir_serial_sql(sql_cargar + "\n" + p_seg.toString(), titulo);
			}
		} catch (Exception e) {
			evemen.mensaje_error(e, sql_cargar + "\n" + p_seg.toString(), titulo);
		}
	}
	public void actualizar_tabla_plan_seguro(Connection conn,JTable tbltabla){
		eveconn.Select_cargar_jtable(conn, sql_select, tbltabla);
		ancho_tabla_plan_seguro(tbltabla);
	}
	public void ancho_tabla_plan_seguro(JTable tbltabla){
		int Ancho[]={20,80};
		evejt.setAnchoColumnaJtable(tbltabla, Ancho);
	}
}
