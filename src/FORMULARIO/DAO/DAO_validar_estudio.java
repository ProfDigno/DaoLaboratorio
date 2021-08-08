	package FORMULARIO.DAO;
	import BASEDATO.EvenConexion;
	import FORMULARIO.ENTIDAD.validar_estudio;
	import Evento.JasperReport.EvenJasperReport;
	import Evento.Jtable.EvenJtable;
	import Evento.Mensaje.EvenMensajeJoptionpane;
	import Evento.Fecha.EvenFecha;
	import java.sql.ResultSet;
	import java.sql.Connection;
	import java.sql.PreparedStatement;
	import javax.swing.JTable;
public class DAO_validar_estudio {
	EvenConexion eveconn = new EvenConexion();
	EvenJtable evejt = new EvenJtable();
	EvenJasperReport rep = new EvenJasperReport();
	EvenMensajeJoptionpane evemen = new EvenMensajeJoptionpane();
	EvenFecha evefec = new EvenFecha();
	private String mensaje_insert = "VALIDAR_ESTUDIO GUARDADO CORRECTAMENTE";
	private String mensaje_update = "VALIDAR_ESTUDIO MODIFICADO CORECTAMENTE";
	private String sql_insert = "INSERT INTO validar_estudio(idvalidar_estudio,genero,edad_minimo,edad_maximo,valor_minimo,valor_maximo) VALUES (?,?,?,?,?,?);";
	private String sql_update = "UPDATE validar_estudio SET genero=?,edad_minimo=?,edad_maximo=?,valor_minimo=?,valor_maximo=? WHERE idvalidar_estudio=?;";
	private String sql_select = "SELECT idvalidar_estudio,genero,edad_minimo,edad_maximo,valor_minimo,valor_maximo FROM validar_estudio order by 1 desc;";
	private String sql_cargar = "SELECT idvalidar_estudio,genero,edad_minimo,edad_maximo,valor_minimo,valor_maximo FROM validar_estudio WHERE idvalidar_estudio=";
	public void insertar_validar_estudio(Connection conn, validar_estudio vali){
		vali.setC1idvalidar_estudio(eveconn.getInt_ultimoID_mas_uno(conn, vali.getTb_validar_estudio(), vali.getId_idvalidar_estudio()));
		String titulo = "insertar_validar_estudio";
		PreparedStatement pst = null;
		try {
			pst = conn.prepareStatement(sql_insert);
			pst.setInt(1,vali.getC1idvalidar_estudio());
			pst.setString(2,vali.getC2genero());
			pst.setInt(3,vali.getC3edad_minimo());
			pst.setInt(4,vali.getC4edad_maximo());
			pst.setInt(5,vali.getC5valor_minimo());
			pst.setInt(6,vali.getC6valor_maximo());
			pst.execute();
			pst.close();
			evemen.Imprimir_serial_sql(sql_insert + "\n" + vali.toString(), titulo);
			evemen.guardado_correcto(mensaje_insert, true);
		} catch (Exception e) {
			evemen.mensaje_error(e, sql_insert + "\n" + vali.toString(), titulo);
		}
	}
	public void update_validar_estudio(Connection conn, validar_estudio vali){
		String titulo = "update_validar_estudio";
		PreparedStatement pst = null;
		try {
			pst = conn.prepareStatement(sql_update);
			pst.setString(1,vali.getC2genero());
			pst.setInt(2,vali.getC3edad_minimo());
			pst.setInt(3,vali.getC4edad_maximo());
			pst.setInt(4,vali.getC5valor_minimo());
			pst.setInt(5,vali.getC6valor_maximo());
			pst.setInt(6,vali.getC1idvalidar_estudio());
			pst.execute();
			pst.close();
			evemen.Imprimir_serial_sql(sql_update + "\n" + vali.toString(), titulo);
			evemen.modificado_correcto(mensaje_update, true);
		} catch (Exception e) {
			evemen.mensaje_error(e, sql_update + "\n" + vali.toString(), titulo);
		}
	}
	public void cargar_validar_estudio(Connection conn, validar_estudio vali,int id){
		String titulo = "Cargar_validar_estudio";
		try {
			ResultSet rs=eveconn.getResulsetSQL(conn,sql_cargar+id, titulo);
			if(rs.next()){
				vali.setC1idvalidar_estudio(rs.getInt(1));
				vali.setC2genero(rs.getString(2));
				vali.setC3edad_minimo(rs.getInt(3));
				vali.setC4edad_maximo(rs.getInt(4));
				vali.setC5valor_minimo(rs.getInt(5));
				vali.setC6valor_maximo(rs.getInt(6));
				evemen.Imprimir_serial_sql(sql_cargar + "\n" + vali.toString(), titulo);
			}
		} catch (Exception e) {
			evemen.mensaje_error(e, sql_cargar + "\n" + vali.toString(), titulo);
		}
	}
	public void actualizar_tabla_validar_estudio(Connection conn,JTable tbltabla){
		eveconn.Select_cargar_jtable(conn, sql_select, tbltabla);
		ancho_tabla_validar_estudio(tbltabla);
	}
	public void ancho_tabla_validar_estudio(JTable tbltabla){
		int Ancho[]={16,16,16,16,16,16};
		evejt.setAnchoColumnaJtable(tbltabla, Ancho);
	}
}
