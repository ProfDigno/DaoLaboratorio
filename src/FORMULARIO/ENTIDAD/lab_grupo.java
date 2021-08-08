	package FORMULARIO.ENTIDAD;
public class lab_grupo {

//---------------DECLARAR VARIABLES---------------
private int C1idlab_grupo;
private String C2nombre;
private String C3descripcion;
private static String nom_tabla;
private static String nom_idtabla;
//---------------TABLA-ID---------------
	public lab_grupo() {
		setTb_lab_grupo("lab_grupo");
		setId_idlab_grupo("idlab_grupo");
	}
	public static String getTb_lab_grupo(){
		return nom_tabla;
	}
	public static void setTb_lab_grupo(String nom_tabla){
		lab_grupo.nom_tabla = nom_tabla;
	}
	public static String getId_idlab_grupo(){
		return nom_idtabla;
	}
	public static void setId_idlab_grupo(String nom_idtabla){
		lab_grupo.nom_idtabla = nom_idtabla;
	}
//---------------GET-SET-CAMPOS---------------
	public int getC1idlab_grupo(){
		return C1idlab_grupo;
	}
	public void setC1idlab_grupo(int C1idlab_grupo){
		this.C1idlab_grupo = C1idlab_grupo;
	}
	public String getC2nombre(){
		return C2nombre;
	}
	public void setC2nombre(String C2nombre){
		this.C2nombre = C2nombre;
	}
	public String toString() {
		return "lab_grupo(" + ",idlab_grupo=" + C1idlab_grupo + " ,nombre=" + C2nombre + " )";
	}

    /**
     * @return the C3descripcion
     */
    public String getC3descripcion() {
        return C3descripcion;
    }

    /**
     * @param C3descripcion the C3descripcion to set
     */
    public void setC3descripcion(String C3descripcion) {
        this.C3descripcion = C3descripcion;
    }
}
