	package FORMULARIO.DAO;
	import BASEDATO.EvenConexion;
	import FORMULARIO.ENTIDAD.item_validar_estudio;
	import Evento.JasperReport.EvenJasperReport;
	import Evento.Jtable.EvenJtable;
	import Evento.Mensaje.EvenMensajeJoptionpane;
	import Evento.Fecha.EvenFecha;
	import java.sql.ResultSet;
	import java.sql.Connection;
	import java.sql.PreparedStatement;
	import javax.swing.JTable;
public class DAO_item_validar_estudio {
	EvenConexion eveconn = new EvenConexion();
	EvenJtable evejt = new EvenJtable();
	EvenJasperReport rep = new EvenJasperReport();
	EvenMensajeJoptionpane evemen = new EvenMensajeJoptionpane();
	EvenFecha evefec = new EvenFecha();
	private String mensaje_insert = "ITEM_VALIDAR_ESTUDIO GUARDADO CORRECTAMENTE";
	private String mensaje_update = "ITEM_VALIDAR_ESTUDIO MODIFICADO CORECTAMENTE";
	private String sql_insert = "INSERT INTO item_validar_estudio(iditem_validar_estudio,fk_idlab_estudio,fk_idvalidar_estudio) VALUES (?,?,?);";
	private String sql_update = "UPDATE item_validar_estudio SET fk_idlab_estudio=?,fk_idvalidar_estudio=? WHERE iditem_validar_estudio=?;";
	private String sql_select = "SELECT iditem_validar_estudio,fk_idlab_estudio,fk_idvalidar_estudio FROM item_validar_estudio order by 1 desc;";
	private String sql_cargar = "SELECT iditem_validar_estudio,fk_idlab_estudio,fk_idvalidar_estudio FROM item_validar_estudio WHERE iditem_validar_estudio=";
	public void insertar_item_validar_estudio(Connection conn, item_validar_estudio ivali){
		ivali.setC1iditem_validar_estudio(eveconn.getInt_ultimoID_mas_uno(conn, ivali.getTb_item_validar_estudio(), ivali.getId_iditem_validar_estudio()));
		String titulo = "insertar_item_validar_estudio";
		PreparedStatement pst = null;
		try {
			pst = conn.prepareStatement(sql_insert);
			pst.setInt(1,ivali.getC1iditem_validar_estudio());
			pst.setInt(2,ivali.getC2fk_idlab_estudio());
			pst.setInt(3,ivali.getC3fk_idvalidar_estudio());
			pst.execute();
			pst.close();
			evemen.Imprimir_serial_sql(sql_insert + "\n" + ivali.toString(), titulo);
			evemen.guardado_correcto(mensaje_insert, true);
		} catch (Exception e) {
			evemen.mensaje_error(e, sql_insert + "\n" + ivali.toString(), titulo);
		}
	}
	public void update_item_validar_estudio(Connection conn, item_validar_estudio ivali){
		String titulo = "update_item_validar_estudio";
		PreparedStatement pst = null;
		try {
			pst = conn.prepareStatement(sql_update);
			pst.setInt(1,ivali.getC2fk_idlab_estudio());
			pst.setInt(2,ivali.getC3fk_idvalidar_estudio());
			pst.setInt(3,ivali.getC1iditem_validar_estudio());
			pst.execute();
			pst.close();
			evemen.Imprimir_serial_sql(sql_update + "\n" + ivali.toString(), titulo);
			evemen.modificado_correcto(mensaje_update, true);
		} catch (Exception e) {
			evemen.mensaje_error(e, sql_update + "\n" + ivali.toString(), titulo);
		}
	}
	public void cargar_item_validar_estudio(Connection conn, item_validar_estudio ivali,int id){
		String titulo = "Cargar_item_validar_estudio";
		try {
			ResultSet rs=eveconn.getResulsetSQL(conn,sql_cargar+id, titulo);
			if(rs.next()){
				ivali.setC1iditem_validar_estudio(rs.getInt(1));
				ivali.setC2fk_idlab_estudio(rs.getInt(2));
				ivali.setC3fk_idvalidar_estudio(rs.getInt(3));
				evemen.Imprimir_serial_sql(sql_cargar + "\n" + ivali.toString(), titulo);
			}
		} catch (Exception e) {
			evemen.mensaje_error(e, sql_cargar + "\n" + ivali.toString(), titulo);
		}
	}
	public void actualizar_tabla_item_validar_estudio(Connection conn,JTable tbltabla){
		eveconn.Select_cargar_jtable(conn, sql_select, tbltabla);
		ancho_tabla_item_validar_estudio(tbltabla);
	}
	public void ancho_tabla_item_validar_estudio(JTable tbltabla){
		int Ancho[]={33,33,33};
		evejt.setAnchoColumnaJtable(tbltabla, Ancho);
	}
}
