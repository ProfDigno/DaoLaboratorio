	package FORMULARIO.ENTIDAD;
public class orden_lugar {

//---------------DECLARAR VARIABLES---------------
private int C1idorden_lugar;
private String C2nombre;
private static String nom_tabla;
private static String nom_idtabla;
//---------------TABLA-ID---------------
	public orden_lugar() {
		setTb_orden_lugar("orden_lugar");
		setId_idorden_lugar("idorden_lugar");
	}
	public static String getTb_orden_lugar(){
		return nom_tabla;
	}
	public static void setTb_orden_lugar(String nom_tabla){
		orden_lugar.nom_tabla = nom_tabla;
	}
	public static String getId_idorden_lugar(){
		return nom_idtabla;
	}
	public static void setId_idorden_lugar(String nom_idtabla){
		orden_lugar.nom_idtabla = nom_idtabla;
	}
//---------------GET-SET-CAMPOS---------------
	public int getC1idorden_lugar(){
		return C1idorden_lugar;
	}
	public void setC1idorden_lugar(int C1idorden_lugar){
		this.C1idorden_lugar = C1idorden_lugar;
	}
	public String getC2nombre(){
		return C2nombre;
	}
	public void setC2nombre(String C2nombre){
		this.C2nombre = C2nombre;
	}
	public String toString() {
		return "orden_lugar(" + ",idorden_lugar=" + C1idorden_lugar + " ,nombre=" + C2nombre + " )";
	}
}
