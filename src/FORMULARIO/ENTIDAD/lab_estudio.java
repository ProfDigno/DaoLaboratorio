	package FORMULARIO.ENTIDAD;
public class lab_estudio {

//---------------DECLARAR VARIABLES---------------
private int C1idlab_estudio;
private String C2nombre_completo;
private String C3nombre_corto;
private int C4numerico_decimal;
private boolean C5es_numerico;
private boolean C6es_testo;
private boolean C7es_predefinido;
private boolean C8es_validado;
private String C9valor_de_referencia;
private int C10fk_idlab_unidad;
private int C11fk_idlab_grupo_estudio;
private static String nom_tabla;
private static String nom_idtabla;
//---------------TABLA-ID---------------
	public lab_estudio() {
		setTb_lab_estudio("lab_estudio");
		setId_idlab_estudio("idlab_estudio");
	}
	public static String getTb_lab_estudio(){
		return nom_tabla;
	}
	public static void setTb_lab_estudio(String nom_tabla){
		lab_estudio.nom_tabla = nom_tabla;
	}
	public static String getId_idlab_estudio(){
		return nom_idtabla;
	}
	public static void setId_idlab_estudio(String nom_idtabla){
		lab_estudio.nom_idtabla = nom_idtabla;
	}
//---------------GET-SET-CAMPOS---------------
	public int getC1idlab_estudio(){
		return C1idlab_estudio;
	}
	public void setC1idlab_estudio(int C1idlab_estudio){
		this.C1idlab_estudio = C1idlab_estudio;
	}
	public String getC2nombre_completo(){
		return C2nombre_completo;
	}
	public void setC2nombre_completo(String C2nombre_completo){
		this.C2nombre_completo = C2nombre_completo;
	}
	public String getC3nombre_corto(){
		return C3nombre_corto;
	}
	public void setC3nombre_corto(String C3nombre_corto){
		this.C3nombre_corto = C3nombre_corto;
	}
	public int getC4numerico_decimal(){
		return C4numerico_decimal;
	}
	public void setC4numerico_decimal(int C4numerico_decimal){
		this.C4numerico_decimal = C4numerico_decimal;
	}
	public boolean getC5es_numerico(){
		return C5es_numerico;
	}
	public void setC5es_numerico(boolean C5es_numerico){
		this.C5es_numerico = C5es_numerico;
	}
	public boolean getC6es_testo(){
		return C6es_testo;
	}
	public void setC6es_testo(boolean C6es_testo){
		this.C6es_testo = C6es_testo;
	}
	public boolean getC7es_predefinido(){
		return C7es_predefinido;
	}
	public void setC7es_predefinido(boolean C7es_predefinido){
		this.C7es_predefinido = C7es_predefinido;
	}
	public boolean getC8es_validado(){
		return C8es_validado;
	}
	public void setC8es_validado(boolean C8es_validado){
		this.C8es_validado = C8es_validado;
	}
	public String getC9valor_de_referencia(){
		return C9valor_de_referencia;
	}
	public void setC9valor_de_referencia(String C9valor_de_referencia){
		this.C9valor_de_referencia = C9valor_de_referencia;
	}
	public int getC10fk_idlab_unidad(){
		return C10fk_idlab_unidad;
	}
	public void setC10fk_idlab_unidad(int C10fk_idlab_unidad){
		this.C10fk_idlab_unidad = C10fk_idlab_unidad;
	}
	public String toString() {
		return "lab_estudio(" + ",idlab_estudio=" + C1idlab_estudio + " ,nombre_completo=" + C2nombre_completo + " ,nombre_corto=" + C3nombre_corto + " ,numerico_decimal=" + C4numerico_decimal + " ,es_numerico=" + C5es_numerico + " ,es_testo=" + C6es_testo + " ,es_predefinido=" + C7es_predefinido + " ,es_validado=" + C8es_validado + " ,valor_de_referencia=" + C9valor_de_referencia + " ,fk_idlab_unidad=" + C10fk_idlab_unidad + " )";
	}

    /**
     * @return the C11fk_idlab_grupo_estudio
     */
    public int getC11fk_idlab_grupo_estudio() {
        return C11fk_idlab_grupo_estudio;
    }

    /**
     * @param C11fk_idlab_grupo_estudio the C11fk_idlab_grupo_estudio to set
     */
    public void setC11fk_idlab_grupo_estudio(int C11fk_idlab_grupo_estudio) {
        this.C11fk_idlab_grupo_estudio = C11fk_idlab_grupo_estudio;
    }
}
