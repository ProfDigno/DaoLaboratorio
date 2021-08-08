	package FORMULARIO.ENTIDAD;
public class item_validar_estudio {

//---------------DECLARAR VARIABLES---------------
private int C1iditem_validar_estudio;
private int C2fk_idlab_estudio;
private int C3fk_idvalidar_estudio;
private static String nom_tabla;
private static String nom_idtabla;
//---------------TABLA-ID---------------
	public item_validar_estudio() {
		setTb_item_validar_estudio("item_validar_estudio");
		setId_iditem_validar_estudio("iditem_validar_estudio");
	}
	public static String getTb_item_validar_estudio(){
		return nom_tabla;
	}
	public static void setTb_item_validar_estudio(String nom_tabla){
		item_validar_estudio.nom_tabla = nom_tabla;
	}
	public static String getId_iditem_validar_estudio(){
		return nom_idtabla;
	}
	public static void setId_iditem_validar_estudio(String nom_idtabla){
		item_validar_estudio.nom_idtabla = nom_idtabla;
	}
//---------------GET-SET-CAMPOS---------------
	public int getC1iditem_validar_estudio(){
		return C1iditem_validar_estudio;
	}
	public void setC1iditem_validar_estudio(int C1iditem_validar_estudio){
		this.C1iditem_validar_estudio = C1iditem_validar_estudio;
	}
	public int getC2fk_idlab_estudio(){
		return C2fk_idlab_estudio;
	}
	public void setC2fk_idlab_estudio(int C2fk_idlab_estudio){
		this.C2fk_idlab_estudio = C2fk_idlab_estudio;
	}
	public int getC3fk_idvalidar_estudio(){
		return C3fk_idvalidar_estudio;
	}
	public void setC3fk_idvalidar_estudio(int C3fk_idvalidar_estudio){
		this.C3fk_idvalidar_estudio = C3fk_idvalidar_estudio;
	}
	public String toString() {
		return "item_validar_estudio(" + ",iditem_validar_estudio=" + C1iditem_validar_estudio + " ,fk_idlab_estudio=" + C2fk_idlab_estudio + " ,fk_idvalidar_estudio=" + C3fk_idvalidar_estudio + " )";
	}
}
