	package FORMULARIO.ENTIDAD;
public class direc_ciudad {

//---------------DECLARAR VARIABLES---------------
private int C1iddirec_ciudad;
private String C2nombre;
private static String nom_tabla;
private static String nom_idtabla;
//---------------TABLA-ID---------------
	public direc_ciudad() {
		setTb_direc_ciudad("public.direc_ciudad");
		setId_iddirec_ciudad("iddirec_ciudad");
	}
	public static String getTb_direc_ciudad(){
		return nom_tabla;
	}
	public static void setTb_direc_ciudad(String nom_tabla){
		direc_ciudad.nom_tabla = nom_tabla;
	}
	public static String getId_iddirec_ciudad(){
		return nom_idtabla;
	}
	public static void setId_iddirec_ciudad(String nom_idtabla){
		direc_ciudad.nom_idtabla = nom_idtabla;
	}
//---------------GET-SET-CAMPOS---------------
	public int getC1iddirec_ciudad(){
		return C1iddirec_ciudad;
	}
	public void setC1iddirec_ciudad(int C1iddirec_ciudad){
		this.C1iddirec_ciudad = C1iddirec_ciudad;
	}
	public String getC2nombre(){
		return C2nombre;
	}
	public void setC2nombre(String C2nombre){
		this.C2nombre = C2nombre;
	}
	public String toString() {
		return "direc_ciudad(" + ",iddirec_ciudad=" + C1iddirec_ciudad + " ,nombre=" + C2nombre + " )";
	}
}
