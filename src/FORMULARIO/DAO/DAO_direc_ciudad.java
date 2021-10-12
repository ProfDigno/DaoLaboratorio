	package FORMULARIO.DAO;
	import BASEDATO.EvenConexion;
	import FORMULARIO.ENTIDAD.direc_ciudad;
	import Evento.JasperReport.EvenJasperReport;
	import Evento.Jtable.EvenJtable;
	import Evento.Mensaje.EvenMensajeJoptionpane;
	import Evento.Fecha.EvenFecha;
	import java.sql.ResultSet;
	import java.sql.Connection;
	import java.sql.PreparedStatement;
	import javax.swing.JTable;
public class DAO_direc_ciudad {
	EvenConexion eveconn = new EvenConexion();
	EvenJtable evejt = new EvenJtable();
	EvenJasperReport rep = new EvenJasperReport();
	EvenMensajeJoptionpane evemen = new EvenMensajeJoptionpane();
	EvenFecha evefec = new EvenFecha();
	private String mensaje_insert = "DIREC_CIUDAD GUARDADO CORRECTAMENTE";
	private String mensaje_update = "DIREC_CIUDAD MODIFICADO CORECTAMENTE";
	private String sql_insert = "INSERT INTO public.direc_ciudad(iddirec_ciudad,nombre) VALUES (?,?);";
	private String sql_update = "UPDATE public.direc_ciudad SET nombre=? WHERE iddirec_ciudad=?;";
	private String sql_select = "SELECT iddirec_ciudad,nombre FROM public.direc_ciudad order by 1 desc;";
	private String sql_cargar = "SELECT iddirec_ciudad,nombre FROM public.direc_ciudad WHERE iddirec_ciudad=";
	public void insertar_direc_ciudad(Connection conn, direc_ciudad dciu){
		dciu.setC1iddirec_ciudad(eveconn.getInt_ultimoID_mas_uno(conn, dciu.getTb_direc_ciudad(), dciu.getId_iddirec_ciudad()));
		String titulo = "insertar_direc_ciudad";
		PreparedStatement pst = null;
		try {
			pst = conn.prepareStatement(sql_insert);
			pst.setInt(1,dciu.getC1iddirec_ciudad());
			pst.setString(2,dciu.getC2nombre());
			pst.execute();
			pst.close();
			evemen.Imprimir_serial_sql(sql_insert + "\n" + dciu.toString(), titulo);
			evemen.guardado_correcto(mensaje_insert, true);
		} catch (Exception e) {
			evemen.mensaje_error(e, sql_insert + "\n" + dciu.toString(), titulo);
		}
	}
	public void update_direc_ciudad(Connection conn, direc_ciudad dciu){
		String titulo = "update_direc_ciudad";
		PreparedStatement pst = null;
		try {
			pst = conn.prepareStatement(sql_update);
			pst.setString(1,dciu.getC2nombre());
			pst.setInt(2,dciu.getC1iddirec_ciudad());
			pst.execute();
			pst.close();
			evemen.Imprimir_serial_sql(sql_update + "\n" + dciu.toString(), titulo);
			evemen.modificado_correcto(mensaje_update, true);
		} catch (Exception e) {
			evemen.mensaje_error(e, sql_update + "\n" + dciu.toString(), titulo);
		}
	}
	public void cargar_direc_ciudad(Connection conn, direc_ciudad dciu,int id){
		String titulo = "Cargar_direc_ciudad";
		try {
			ResultSet rs=eveconn.getResulsetSQL(conn,sql_cargar+id, titulo);
			if(rs.next()){
				dciu.setC1iddirec_ciudad(rs.getInt(1));
				dciu.setC2nombre(rs.getString(2));
				evemen.Imprimir_serial_sql(sql_cargar + "\n" + dciu.toString(), titulo);
			}
		} catch (Exception e) {
			evemen.mensaje_error(e, sql_cargar + "\n" + dciu.toString(), titulo);
		}
	}
	public void actualizar_tabla_direc_ciudad(Connection conn,JTable tbltabla){
		eveconn.Select_cargar_jtable(conn, sql_select, tbltabla);
		ancho_tabla_direc_ciudad(tbltabla);
	}
	public void ancho_tabla_direc_ciudad(JTable tbltabla){
		int Ancho[]={50,50};
		evejt.setAnchoColumnaJtable(tbltabla, Ancho);
	}
}
