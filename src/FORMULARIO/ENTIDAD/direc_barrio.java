	package FORMULARIO.ENTIDAD;
public class direc_barrio {

//---------------DECLARAR VARIABLES---------------
private int C1iddirec_barrio;
private String C2nombre;
private static String nom_tabla;
private static String nom_idtabla;
//---------------TABLA-ID---------------
	public direc_barrio() {
		setTb_direc_barrio("direc_barrio");
		setId_iddirec_barrio("iddirec_barrio");
	}
	public static String getTb_direc_barrio(){
		return nom_tabla;
	}
	public static void setTb_direc_barrio(String nom_tabla){
		direc_barrio.nom_tabla = nom_tabla;
	}
	public static String getId_iddirec_barrio(){
		return nom_idtabla;
	}
	public static void setId_iddirec_barrio(String nom_idtabla){
		direc_barrio.nom_idtabla = nom_idtabla;
	}
//---------------GET-SET-CAMPOS---------------
	public int getC1iddirec_barrio(){
		return C1iddirec_barrio;
	}
	public void setC1iddirec_barrio(int C1iddirec_barrio){
		this.C1iddirec_barrio = C1iddirec_barrio;
	}
	public String getC2nombre(){
		return C2nombre;
	}
	public void setC2nombre(String C2nombre){
		this.C2nombre = C2nombre;
	}
	public String toString() {
		return "direc_barrio(" + ",iddirec_barrio=" + C1iddirec_barrio + " ,nombre=" + C2nombre + " )";
	}
}
