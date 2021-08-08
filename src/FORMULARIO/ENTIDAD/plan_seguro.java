	package FORMULARIO.ENTIDAD;
public class plan_seguro {

//---------------DECLARAR VARIABLES---------------
private int C1idplan_seguro;
private String C2nombre;
private static String nom_tabla;
private static String nom_idtabla;
//---------------TABLA-ID---------------
	public plan_seguro() {
		setTb_plan_seguro("plan_seguro");
		setId_idplan_seguro("idplan_seguro");
	}
	public static String getTb_plan_seguro(){
		return nom_tabla;
	}
	public static void setTb_plan_seguro(String nom_tabla){
		plan_seguro.nom_tabla = nom_tabla;
	}
	public static String getId_idplan_seguro(){
		return nom_idtabla;
	}
	public static void setId_idplan_seguro(String nom_idtabla){
		plan_seguro.nom_idtabla = nom_idtabla;
	}
//---------------GET-SET-CAMPOS---------------
	public int getC1idplan_seguro(){
		return C1idplan_seguro;
	}
	public void setC1idplan_seguro(int C1idplan_seguro){
		this.C1idplan_seguro = C1idplan_seguro;
	}
	public String getC2nombre(){
		return C2nombre;
	}
	public void setC2nombre(String C2nombre){
		this.C2nombre = C2nombre;
	}
	public String toString() {
		return "plan_seguro(" + ",idplan_seguro=" + C1idplan_seguro + " ,nombre=" + C2nombre + " )";
	}
}
