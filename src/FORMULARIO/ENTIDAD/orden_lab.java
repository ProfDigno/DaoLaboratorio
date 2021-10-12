	package FORMULARIO.ENTIDAD;
public class orden_lab {

//---------------DECLARAR VARIABLES---------------
private int C1idorden_lab;
private String C2fecha_inicio;
private String C3fecha_fin;
private String C4estado;
private String C5observacion;
private double C6visacion;
private int C7fk_idpersona1;
private int C8fk_idpersona2;
private int C9fk_idpersona3;
private int C10fk_idorden_lugar;
private int C11fk_idusuario;
private int C12nro_documento;
private static String nom_tabla;
private static String nom_idtabla;
//---------------TABLA-ID---------------
	public orden_lab() {
		setTb_orden_lab("public.orden_lab");
		setId_idorden_lab("idorden_lab");
	}
	public static String getTb_orden_lab(){
		return nom_tabla;
	}
	public static void setTb_orden_lab(String nom_tabla){
		orden_lab.nom_tabla = nom_tabla;
	}
	public static String getId_idorden_lab(){
		return nom_idtabla;
	}
	public static void setId_idorden_lab(String nom_idtabla){
		orden_lab.nom_idtabla = nom_idtabla;
	}
//---------------GET-SET-CAMPOS---------------
	public int getC1idorden_lab(){
		return C1idorden_lab;
	}
	public void setC1idorden_lab(int C1idorden_lab){
		this.C1idorden_lab = C1idorden_lab;
	}
	public String getC2fecha_inicio(){
		return C2fecha_inicio;
	}
	public void setC2fecha_inicio(String C2fecha_inicio){
		this.C2fecha_inicio = C2fecha_inicio;
	}
	public String getC3fecha_fin(){
		return C3fecha_fin;
	}
	public void setC3fecha_fin(String C3fecha_fin){
		this.C3fecha_fin = C3fecha_fin;
	}
	public String getC4estado(){
		return C4estado;
	}
	public void setC4estado(String C4estado){
		this.C4estado = C4estado;
	}
	public String getC5observacion(){
		return C5observacion;
	}
	public void setC5observacion(String C5observacion){
		this.C5observacion = C5observacion;
	}
	public double getC6visacion(){
		return C6visacion;
	}
	public void setC6visacion(double C6visacion){
		this.C6visacion = C6visacion;
	}
	public int getC7fk_idpersona1(){
		return C7fk_idpersona1;
	}
	public void setC7fk_idpersona1(int C7fk_idpersona1){
		this.C7fk_idpersona1 = C7fk_idpersona1;
	}
	public int getC8fk_idpersona2(){
		return C8fk_idpersona2;
	}
	public void setC8fk_idpersona2(int C8fk_idpersona2){
		this.C8fk_idpersona2 = C8fk_idpersona2;
	}
	public int getC9fk_idpersona3(){
		return C9fk_idpersona3;
	}
	public void setC9fk_idpersona3(int C9fk_idpersona3){
		this.C9fk_idpersona3 = C9fk_idpersona3;
	}
	public int getC10fk_idorden_lugar(){
		return C10fk_idorden_lugar;
	}
	public void setC10fk_idorden_lugar(int C10fk_idorden_lugar){
		this.C10fk_idorden_lugar = C10fk_idorden_lugar;
	}
	public int getC11fk_idusuario(){
		return C11fk_idusuario;
	}
	public void setC11fk_idusuario(int C11fk_idusuario){
		this.C11fk_idusuario = C11fk_idusuario;
	}
	public String toString() {
		return "orden_lab(" + ",idorden_lab=" + C1idorden_lab + " ,fecha_inicio=" + C2fecha_inicio + " ,fecha_fin=" + C3fecha_fin + " ,estado=" + C4estado + " ,observacion=" + C5observacion + " ,visacion=" + C6visacion + " ,fk_idpersona1=" + C7fk_idpersona1 + " ,fk_idpersona2=" + C8fk_idpersona2 + " ,fk_idpersona3=" + C9fk_idpersona3 + " ,fk_idorden_lugar=" + C10fk_idorden_lugar + " ,fk_idusuario=" + C11fk_idusuario + " )";
	}

    /**
     * @return the C12nro_documento
     */
    public int getC12nro_documento() {
        return C12nro_documento;
    }

    /**
     * @param C12nro_documento the C12nro_documento to set
     */
    public void setC12nro_documento(int C12nro_documento) {
        this.C12nro_documento = C12nro_documento;
    }
}
