	package FORMULARIO.ENTIDAD;
public class usuario {

//---------------DECLARAR VARIABLES---------------
private int C1idusuario;
private String C2nombre;
private String C3usuario;
private String C4password;
private String C5nivel;
private static String nom_tabla;
private static String nom_idtabla;
//---------------TABLA-ID---------------
	public usuario() {
		setTb_usuario("usuario");
		setId_idusuario("idusuario");
	}
	public static String getTb_usuario(){
		return nom_tabla;
	}
	public static void setTb_usuario(String nom_tabla){
		usuario.nom_tabla = nom_tabla;
	}
	public static String getId_idusuario(){
		return nom_idtabla;
	}
	public static void setId_idusuario(String nom_idtabla){
		usuario.nom_idtabla = nom_idtabla;
	}
//---------------GET-SET-CAMPOS---------------
	public int getC1idusuario(){
		return C1idusuario;
	}
	public void setC1idusuario(int C1idusuario){
		this.C1idusuario = C1idusuario;
	}
	public String getC2nombre(){
		return C2nombre;
	}
	public void setC2nombre(String C2nombre){
		this.C2nombre = C2nombre;
	}
	public String getC3usuario(){
		return C3usuario;
	}
	public void setC3usuario(String C3usuario){
		this.C3usuario = C3usuario;
	}
	public String getC4password(){
		return C4password;
	}
	public void setC4password(String C4password){
		this.C4password = C4password;
	}
	public String getC5nivel(){
		return C5nivel;
	}
	public void setC5nivel(String C5nivel){
		this.C5nivel = C5nivel;
	}
	public String toString() {
		return "usuario(" + ",idusuario=" + C1idusuario + " ,nombre=" + C2nombre + " ,usuario=" + C3usuario + " ,password=" + C4password + " ,nivel=" + C5nivel + " )";
	}
}
