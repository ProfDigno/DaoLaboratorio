	package FORMULARIO.DAO;
	import BASEDATO.EvenConexion;
	import FORMULARIO.ENTIDAD.direc_barrio;
	import Evento.JasperReport.EvenJasperReport;
	import Evento.Jtable.EvenJtable;
	import Evento.Mensaje.EvenMensajeJoptionpane;
	import Evento.Fecha.EvenFecha;
	import java.sql.ResultSet;
	import java.sql.Connection;
	import java.sql.PreparedStatement;
	import javax.swing.JTable;
public class DAO_direc_barrio {
	EvenConexion eveconn = new EvenConexion();
	EvenJtable evejt = new EvenJtable();
	EvenJasperReport rep = new EvenJasperReport();
	EvenMensajeJoptionpane evemen = new EvenMensajeJoptionpane();
	EvenFecha evefec = new EvenFecha();
	private String mensaje_insert = "DIREC_BARRIO GUARDADO CORRECTAMENTE";
	private String mensaje_update = "DIREC_BARRIO MODIFICADO CORECTAMENTE";
	private String sql_insert = "INSERT INTO public.direc_barrio(iddirec_barrio,nombre) VALUES (?,?);";
	private String sql_update = "UPDATE public.direc_barrio SET nombre=? WHERE iddirec_barrio=?;";
	private String sql_select = "SELECT iddirec_barrio,nombre FROM public.direc_barrio order by 1 desc;";
	private String sql_cargar = "SELECT iddirec_barrio,nombre FROM public.direc_barrio WHERE iddirec_barrio=";
	public void insertar_direc_barrio(Connection conn, direc_barrio d_ba){
		d_ba.setC1iddirec_barrio(eveconn.getInt_ultimoID_mas_uno(conn, d_ba.getTb_direc_barrio(), d_ba.getId_iddirec_barrio()));
		String titulo = "insertar_direc_barrio";
		PreparedStatement pst = null;
		try {
			pst = conn.prepareStatement(sql_insert);
			pst.setInt(1,d_ba.getC1iddirec_barrio());
			pst.setString(2,d_ba.getC2nombre());
			pst.execute();
			pst.close();
			evemen.Imprimir_serial_sql(sql_insert + "\n" + d_ba.toString(), titulo);
			evemen.guardado_correcto(mensaje_insert, true);
		} catch (Exception e) {
			evemen.mensaje_error(e, sql_insert + "\n" + d_ba.toString(), titulo);
		}
	}
	public void update_direc_barrio(Connection conn, direc_barrio d_ba){
		String titulo = "update_direc_barrio";
		PreparedStatement pst = null;
		try {
			pst = conn.prepareStatement(sql_update);
			pst.setString(1,d_ba.getC2nombre());
			pst.setInt(2,d_ba.getC1iddirec_barrio());
			pst.execute();
			pst.close();
			evemen.Imprimir_serial_sql(sql_update + "\n" + d_ba.toString(), titulo);
			evemen.modificado_correcto(mensaje_update, true);
		} catch (Exception e) {
			evemen.mensaje_error(e, sql_update + "\n" + d_ba.toString(), titulo);
		}
	}
	public void cargar_direc_barrio(Connection conn, direc_barrio d_ba,int id){
		String titulo = "Cargar_direc_barrio";
		try {
			ResultSet rs=eveconn.getResulsetSQL(conn,sql_cargar+id, titulo);
			if(rs.next()){
				d_ba.setC1iddirec_barrio(rs.getInt(1));
				d_ba.setC2nombre(rs.getString(2));
				evemen.Imprimir_serial_sql(sql_cargar + "\n" + d_ba.toString(), titulo);
			}
		} catch (Exception e) {
			evemen.mensaje_error(e, sql_cargar + "\n" + d_ba.toString(), titulo);
		}
	}
	public void actualizar_tabla_direc_barrio(Connection conn,JTable tbltabla){
		eveconn.Select_cargar_jtable(conn, sql_select, tbltabla);
		ancho_tabla_direc_barrio(tbltabla);
	}
	public void ancho_tabla_direc_barrio(JTable tbltabla){
		int Ancho[]={20,80};
		evejt.setAnchoColumnaJtable(tbltabla, Ancho);
	}
}
