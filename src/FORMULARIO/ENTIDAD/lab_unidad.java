	package FORMULARIO.ENTIDAD;
public class lab_unidad {

//---------------DECLARAR VARIABLES---------------
private int C1idlab_unidad;
private String C2nombre;
private static String nom_tabla;
private static String nom_idtabla;
//---------------TABLA-ID---------------
	public lab_unidad() {
		setTb_lab_unidad("lab_unidad");
		setId_idlab_unidad("idlab_unidad");
	}
	public static String getTb_lab_unidad(){
		return nom_tabla;
	}
	public static void setTb_lab_unidad(String nom_tabla){
		lab_unidad.nom_tabla = nom_tabla;
	}
	public static String getId_idlab_unidad(){
		return nom_idtabla;
	}
	public static void setId_idlab_unidad(String nom_idtabla){
		lab_unidad.nom_idtabla = nom_idtabla;
	}
//---------------GET-SET-CAMPOS---------------
	public int getC1idlab_unidad(){
		return C1idlab_unidad;
	}
	public void setC1idlab_unidad(int C1idlab_unidad){
		this.C1idlab_unidad = C1idlab_unidad;
	}
	public String getC2nombre(){
		return C2nombre;
	}
	public void setC2nombre(String C2nombre){
		this.C2nombre = C2nombre;
	}
	public String toString() {
		return "lab_unidad(" + ",idlab_unidad=" + C1idlab_unidad + " ,nombre=" + C2nombre + " )";
	}
}
