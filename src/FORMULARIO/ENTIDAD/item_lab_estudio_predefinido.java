	package FORMULARIO.ENTIDAD;
public class item_lab_estudio_predefinido {

//---------------DECLARAR VARIABLES---------------
private int C1iditem_lab_estudio_predefinido;
private int C2orden;
private int C3fk_idlab_estudio_predefinido;
private int C4fk_idlab_estudio;
private static String nom_tabla;
private static String nom_idtabla;
//---------------TABLA-ID---------------
	public item_lab_estudio_predefinido() {
		setTb_item_lab_estudio_predefinido("item_lab_estudio_predefinido");
		setId_iditem_lab_estudio_predefinido("iditem_lab_estudio_predefinido");
	}
	public static String getTb_item_lab_estudio_predefinido(){
		return nom_tabla;
	}
	public static void setTb_item_lab_estudio_predefinido(String nom_tabla){
		item_lab_estudio_predefinido.nom_tabla = nom_tabla;
	}
	public static String getId_iditem_lab_estudio_predefinido(){
		return nom_idtabla;
	}
	public static void setId_iditem_lab_estudio_predefinido(String nom_idtabla){
		item_lab_estudio_predefinido.nom_idtabla = nom_idtabla;
	}
//---------------GET-SET-CAMPOS---------------
	public int getC1iditem_lab_estudio_predefinido(){
		return C1iditem_lab_estudio_predefinido;
	}
	public void setC1iditem_lab_estudio_predefinido(int C1iditem_lab_estudio_predefinido){
		this.C1iditem_lab_estudio_predefinido = C1iditem_lab_estudio_predefinido;
	}
	public int getC2orden(){
		return C2orden;
	}
	public void setC2orden(int C2orden){
		this.C2orden = C2orden;
	}
	public int getC3fk_idlab_estudio_predefinido(){
		return C3fk_idlab_estudio_predefinido;
	}
	public void setC3fk_idlab_estudio_predefinido(int C3fk_idlab_estudio_predefinido){
		this.C3fk_idlab_estudio_predefinido = C3fk_idlab_estudio_predefinido;
	}
	public int getC4fk_idlab_estudio(){
		return C4fk_idlab_estudio;
	}
	public void setC4fk_idlab_estudio(int C4fk_idlab_estudio){
		this.C4fk_idlab_estudio = C4fk_idlab_estudio;
	}
	public String toString() {
		return "item_lab_estudio_predefinido(" + ",iditem_lab_estudio_predefinido=" + C1iditem_lab_estudio_predefinido + " ,orden=" + C2orden + " ,fk_idlab_estudio_predefinido=" + C3fk_idlab_estudio_predefinido + " ,fk_idlab_estudio=" + C4fk_idlab_estudio + " )";
	}
}
