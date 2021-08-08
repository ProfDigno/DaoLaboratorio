	package FORMULARIO.DAO;
	import BASEDATO.EvenConexion;
	import FORMULARIO.ENTIDAD.usuario;
	import Evento.JasperReport.EvenJasperReport;
	import Evento.Jtable.EvenJtable;
	import Evento.Mensaje.EvenMensajeJoptionpane;
	import Evento.Fecha.EvenFecha;
	import java.sql.ResultSet;
	import java.sql.Connection;
	import java.sql.PreparedStatement;
	import javax.swing.JTable;
public class DAO_usuario {
	EvenConexion eveconn = new EvenConexion();
	EvenJtable evejt = new EvenJtable();
	EvenJasperReport rep = new EvenJasperReport();
	EvenMensajeJoptionpane evemen = new EvenMensajeJoptionpane();
	EvenFecha evefec = new EvenFecha();
	private String mensaje_insert = "USUARIO GUARDADO CORRECTAMENTE";
	private String mensaje_update = "USUARIO MODIFICADO CORECTAMENTE";
	private String sql_insert = "INSERT INTO usuario(idusuario,nombre,usuario,password,nivel) VALUES (?,?,?,?,?);";
	private String sql_update = "UPDATE usuario SET nombre=?,usuario=?,password=?,nivel=? WHERE idusuario=?;";
	private String sql_select = "SELECT idusuario,nombre,usuario,password,nivel FROM usuario order by 1 desc;";
	private String sql_cargar = "SELECT idusuario,nombre,usuario,password,nivel FROM usuario WHERE idusuario=";
	public void insertar_usuario(Connection conn, usuario usu){
		usu.setC1idusuario(eveconn.getInt_ultimoID_mas_uno(conn, usu.getTb_usuario(), usu.getId_idusuario()));
		String titulo = "insertar_usuario";
		PreparedStatement pst = null;
		try {
			pst = conn.prepareStatement(sql_insert);
			pst.setInt(1,usu.getC1idusuario());
			pst.setString(2,usu.getC2nombre());
			pst.setString(3,usu.getC3usuario());
			pst.setString(4,usu.getC4password());
			pst.setString(5,usu.getC5nivel());
			pst.execute();
			pst.close();
			evemen.Imprimir_serial_sql(sql_insert + "\n" + usu.toString(), titulo);
			evemen.guardado_correcto(mensaje_insert, true);
		} catch (Exception e) {
			evemen.mensaje_error(e, sql_insert + "\n" + usu.toString(), titulo);
		}
	}
	public void update_usuario(Connection conn, usuario usu){
		String titulo = "update_usuario";
		PreparedStatement pst = null;
		try {
			pst = conn.prepareStatement(sql_update);
			pst.setString(1,usu.getC2nombre());
			pst.setString(2,usu.getC3usuario());
			pst.setString(3,usu.getC4password());
			pst.setString(4,usu.getC5nivel());
			pst.setInt(5,usu.getC1idusuario());
			pst.execute();
			pst.close();
			evemen.Imprimir_serial_sql(sql_update + "\n" + usu.toString(), titulo);
			evemen.modificado_correcto(mensaje_update, true);
		} catch (Exception e) {
			evemen.mensaje_error(e, sql_update + "\n" + usu.toString(), titulo);
		}
	}
	public void cargar_usuario(Connection conn, usuario usu,int id){
		String titulo = "Cargar_usuario";
		try {
			ResultSet rs=eveconn.getResulsetSQL(conn,sql_cargar+id, titulo);
			if(rs.next()){
				usu.setC1idusuario(rs.getInt(1));
				usu.setC2nombre(rs.getString(2));
				usu.setC3usuario(rs.getString(3));
				usu.setC4password(rs.getString(4));
				usu.setC5nivel(rs.getString(5));
				evemen.Imprimir_serial_sql(sql_cargar + "\n" + usu.toString(), titulo);
			}
		} catch (Exception e) {
			evemen.mensaje_error(e, sql_cargar + "\n" + usu.toString(), titulo);
		}
	}
	public void actualizar_tabla_usuario(Connection conn,JTable tbltabla){
		eveconn.Select_cargar_jtable(conn, sql_select, tbltabla);
		ancho_tabla_usuario(tbltabla);
	}
	public void ancho_tabla_usuario(JTable tbltabla){
		int Ancho[]={20,20,20,20,20};
		evejt.setAnchoColumnaJtable(tbltabla, Ancho);
	}
}
