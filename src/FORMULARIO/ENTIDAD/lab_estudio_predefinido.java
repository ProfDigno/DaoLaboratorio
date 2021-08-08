	package FORMULARIO.ENTIDAD;
public class lab_estudio_predefinido {

//---------------DECLARAR VARIABLES---------------
private int C1idlab_estudio_predefinido;
private String C2nombre;
private boolean C3es_anormal;
private static String nom_tabla;
private static String nom_idtabla;
//---------------TABLA-ID---------------
	public lab_estudio_predefinido() {
		setTb_lab_estudio_predefinido("lab_estudio_predefinido");
		setId_idlab_estudio_predefinido("idlab_estudio_predefinido");
	}
	public static String getTb_lab_estudio_predefinido(){
		return nom_tabla;
	}
	public static void setTb_lab_estudio_predefinido(String nom_tabla){
		lab_estudio_predefinido.nom_tabla = nom_tabla;
	}
	public static String getId_idlab_estudio_predefinido(){
		return nom_idtabla;
	}
	public static void setId_idlab_estudio_predefinido(String nom_idtabla){
		lab_estudio_predefinido.nom_idtabla = nom_idtabla;
	}
//---------------GET-SET-CAMPOS---------------
	public int getC1idlab_estudio_predefinido(){
		return C1idlab_estudio_predefinido;
	}
	public void setC1idlab_estudio_predefinido(int C1idlab_estudio_predefinido){
		this.C1idlab_estudio_predefinido = C1idlab_estudio_predefinido;
	}
	public String getC2nombre(){
		return C2nombre;
	}
	public void setC2nombre(String C2nombre){
		this.C2nombre = C2nombre;
	}
	public boolean getC3es_anormal(){
		return C3es_anormal;
	}
	public void setC3es_anormal(boolean C3es_anormal){
		this.C3es_anormal = C3es_anormal;
	}
	public String toString() {
		return "lab_estudio_predefinido(" + ",idlab_estudio_predefinido=" + C1idlab_estudio_predefinido + " ,nombre=" + C2nombre + " ,es_anormal=" + C3es_anormal + " )";
	}
}
