	package FORMULARIO.ENTIDAD;
public class item_lab_estudio {

//---------------DECLARAR VARIABLES---------------
private int C1iditem_lab_estudio;
private int C2orden;
private int C3fk_idlab_estudio;
private int C4fk_idlab_grupo_estudio;
private static String nom_tabla;
private static String nom_idtabla;
//---------------TABLA-ID---------------
	public item_lab_estudio() {
		setTb_item_lab_estudio("public.item_lab_estudio");
		setId_iditem_lab_estudio("iditem_lab_estudio");
	}
	public static String getTb_item_lab_estudio(){
		return nom_tabla;
	}
	public static void setTb_item_lab_estudio(String nom_tabla){
		item_lab_estudio.nom_tabla = nom_tabla;
	}
	public static String getId_iditem_lab_estudio(){
		return nom_idtabla;
	}
	public static void setId_iditem_lab_estudio(String nom_idtabla){
		item_lab_estudio.nom_idtabla = nom_idtabla;
	}
//---------------GET-SET-CAMPOS---------------
	public int getC1iditem_lab_estudio(){
		return C1iditem_lab_estudio;
	}
	public void setC1iditem_lab_estudio(int C1iditem_lab_estudio){
		this.C1iditem_lab_estudio = C1iditem_lab_estudio;
	}
	public int getC2orden(){
		return C2orden;
	}
	public void setC2orden(int C2orden){
		this.C2orden = C2orden;
	}
	public int getC3fk_idlab_estudio(){
		return C3fk_idlab_estudio;
	}
	public void setC3fk_idlab_estudio(int C3fk_idlab_estudio){
		this.C3fk_idlab_estudio = C3fk_idlab_estudio;
	}
	public int getC4fk_idlab_grupo_estudio(){
		return C4fk_idlab_grupo_estudio;
	}
	public void setC4fk_idlab_grupo_estudio(int C4fk_idlab_grupo_estudio){
		this.C4fk_idlab_grupo_estudio = C4fk_idlab_grupo_estudio;
	}
	public String toString() {
		return "item_lab_estudio(" + ",iditem_lab_estudio=" + C1iditem_lab_estudio + " ,orden=" + C2orden + " ,fk_idlab_estudio=" + C3fk_idlab_estudio + " ,fk_idlab_grupo_estudio=" + C4fk_idlab_grupo_estudio + " )";
	}
}
