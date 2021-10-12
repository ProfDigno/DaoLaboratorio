	package FORMULARIO.ENTIDAD;
public class tipo_seguro {

//---------------DECLARAR VARIABLES---------------
private int C1idtipo_seguro;
private String C2nombre;
private static String nom_tabla;
private static String nom_idtabla;
//---------------TABLA-ID---------------
	public tipo_seguro() {
		setTb_tipo_seguro("public.tipo_seguro");
		setId_idtipo_seguro("idtipo_seguro");
	}
	public static String getTb_tipo_seguro(){
		return nom_tabla;
	}
	public static void setTb_tipo_seguro(String nom_tabla){
		tipo_seguro.nom_tabla = nom_tabla;
	}
	public static String getId_idtipo_seguro(){
		return nom_idtabla;
	}
	public static void setId_idtipo_seguro(String nom_idtabla){
		tipo_seguro.nom_idtabla = nom_idtabla;
	}
//---------------GET-SET-CAMPOS---------------
	public int getC1idtipo_seguro(){
		return C1idtipo_seguro;
	}
	public void setC1idtipo_seguro(int C1idtipo_seguro){
		this.C1idtipo_seguro = C1idtipo_seguro;
	}
	public String getC2nombre(){
		return C2nombre;
	}
	public void setC2nombre(String C2nombre){
		this.C2nombre = C2nombre;
	}
	public String toString() {
		return "tipo_seguro(" + ",idtipo_seguro=" + C1idtipo_seguro + " ,nombre=" + C2nombre + " )";
	}
}
