	package FORMULARIO.ENTIDAD;
public class lab_grupo_estudio {

    /**
     * @return the C4orden
     */
    public int getC4orden() {
        return C4orden;
    }

    /**
     * @param C4orden the C4orden to set
     */
    public void setC4orden(int C4orden) {
        this.C4orden = C4orden;
    }

//---------------DECLARAR VARIABLES---------------
private int C1idlab_grupo_estudio;
private String C2nombre;
private int C3fk_idlab_grupo;
private int C4orden;
private static String nom_tabla;
private static String nom_idtabla;
//---------------TABLA-ID---------------
	public lab_grupo_estudio() {
		setTb_lab_grupo_estudio("lab_grupo_estudio");
		setId_idlab_grupo_estudio("idlab_grupo_estudio");
	}
	public static String getTb_lab_grupo_estudio(){
		return nom_tabla;
	}
	public static void setTb_lab_grupo_estudio(String nom_tabla){
		lab_grupo_estudio.nom_tabla = nom_tabla;
	}
	public static String getId_idlab_grupo_estudio(){
		return nom_idtabla;
	}
	public static void setId_idlab_grupo_estudio(String nom_idtabla){
		lab_grupo_estudio.nom_idtabla = nom_idtabla;
	}
//---------------GET-SET-CAMPOS---------------
	public int getC1idlab_grupo_estudio(){
		return C1idlab_grupo_estudio;
	}
	public void setC1idlab_grupo_estudio(int C1idlab_grupo_estudio){
		this.C1idlab_grupo_estudio = C1idlab_grupo_estudio;
	}
	public String getC2nombre(){
		return C2nombre;
	}
	public void setC2nombre(String C2nombre){
		this.C2nombre = C2nombre;
	}
	public int getC3fk_idlab_grupo(){
		return C3fk_idlab_grupo;
	}
	public void setC3fk_idlab_grupo(int C3fk_idlab_grupo){
		this.C3fk_idlab_grupo = C3fk_idlab_grupo;
	}
	public String toString() {
		return "lab_grupo_estudio(" + ",idlab_grupo_estudio=" + C1idlab_grupo_estudio + " ,nombre=" + C2nombre + " ,fk_idlab_grupo=" + C3fk_idlab_grupo + " )";
	}
}
