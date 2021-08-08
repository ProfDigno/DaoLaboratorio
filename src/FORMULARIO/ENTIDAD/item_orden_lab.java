	package FORMULARIO.ENTIDAD;
public class item_orden_lab {

//---------------DECLARAR VARIABLES---------------
private int C1iditem_orden_lab;
private String C2descripcion;
private int C3numerico_decimal;
private double C4valor_numerico;
private String C5valor_testo;
private int C6valor_predefinido;
private String C7valor_de_referencia;
private String C8unidad;
private boolean C9es_numerico;
private boolean C10es_testo;
private boolean C11es_predefinido;
private String C12estado_estudio;
private String C13nota;
private boolean C14cobertura;
private int C15fk_idorden_lab;
private int C16fk_idlab_estudio;
private static String nom_tabla;
private static String nom_idtabla;
//---------------TABLA-ID---------------
	public item_orden_lab() {
		setTb_item_orden_lab("item_orden_lab");
		setId_iditem_orden_lab("iditem_orden_lab");
	}
	public static String getTb_item_orden_lab(){
		return nom_tabla;
	}
	public static void setTb_item_orden_lab(String nom_tabla){
		item_orden_lab.nom_tabla = nom_tabla;
	}
	public static String getId_iditem_orden_lab(){
		return nom_idtabla;
	}
	public static void setId_iditem_orden_lab(String nom_idtabla){
		item_orden_lab.nom_idtabla = nom_idtabla;
	}
//---------------GET-SET-CAMPOS---------------
	public int getC1iditem_orden_lab(){
		return C1iditem_orden_lab;
	}
	public void setC1iditem_orden_lab(int C1iditem_orden_lab){
		this.C1iditem_orden_lab = C1iditem_orden_lab;
	}
	public String getC2descripcion(){
		return C2descripcion;
	}
	public void setC2descripcion(String C2descripcion){
		this.C2descripcion = C2descripcion;
	}
	public int getC3numerico_decimal(){
		return C3numerico_decimal;
	}
	public void setC3numerico_decimal(int C3numerico_decimal){
		this.C3numerico_decimal = C3numerico_decimal;
	}
	public double getC4valor_numerico(){
		return C4valor_numerico;
	}
	public void setC4valor_numerico(double C4valor_numerico){
		this.C4valor_numerico = C4valor_numerico;
	}
	public String getC5valor_testo(){
		return C5valor_testo;
	}
	public void setC5valor_testo(String C5valor_testo){
		this.C5valor_testo = C5valor_testo;
	}
	public int getC6valor_predefinido(){
		return C6valor_predefinido;
	}
	public void setC6valor_predefinido(int C6valor_predefinido){
		this.C6valor_predefinido = C6valor_predefinido;
	}
	public String getC7valor_de_referencia(){
		return C7valor_de_referencia;
	}
	public void setC7valor_de_referencia(String C7valor_de_referencia){
		this.C7valor_de_referencia = C7valor_de_referencia;
	}
	public String getC8unidad(){
		return C8unidad;
	}
	public void setC8unidad(String C8unidad){
		this.C8unidad = C8unidad;
	}
	public boolean getC9es_numerico(){
		return C9es_numerico;
	}
	public void setC9es_numerico(boolean C9es_numerico){
		this.C9es_numerico = C9es_numerico;
	}
	public boolean getC10es_testo(){
		return C10es_testo;
	}
	public void setC10es_testo(boolean C10es_testo){
		this.C10es_testo = C10es_testo;
	}
	public boolean getC11es_predefinido(){
		return C11es_predefinido;
	}
	public void setC11es_predefinido(boolean C11es_predefinido){
		this.C11es_predefinido = C11es_predefinido;
	}
	public String getC12estado_estudio(){
		return C12estado_estudio;
	}
	public void setC12estado_estudio(String C12estado_estudio){
		this.C12estado_estudio = C12estado_estudio;
	}
	public String getC13nota(){
		return C13nota;
	}
	public void setC13nota(String C13nota){
		this.C13nota = C13nota;
	}
	public boolean getC14cobertura(){
		return C14cobertura;
	}
	public void setC14cobertura(boolean C14cobertura){
		this.C14cobertura = C14cobertura;
	}
	public int getC15fk_idorden_lab(){
		return C15fk_idorden_lab;
	}
	public void setC15fk_idorden_lab(int C15fk_idorden_lab){
		this.C15fk_idorden_lab = C15fk_idorden_lab;
	}
	public int getC16fk_idlab_estudio(){
		return C16fk_idlab_estudio;
	}
	public void setC16fk_idlab_estudio(int C16fk_idlab_estudio){
		this.C16fk_idlab_estudio = C16fk_idlab_estudio;
	}
	public String toString() {
		return "item_orden_lab(" + ",iditem_orden_lab=" + C1iditem_orden_lab + " ,descripcion=" + C2descripcion + " ,numerico_decimal=" + C3numerico_decimal + " ,valor_numerico=" + C4valor_numerico + " ,valor_testo=" + C5valor_testo + " ,valor_predefinido=" + C6valor_predefinido + " ,valor_de_referencia=" + C7valor_de_referencia + " ,unidad=" + C8unidad + " ,es_numerico=" + C9es_numerico + " ,es_testo=" + C10es_testo + " ,es_predefinido=" + C11es_predefinido + " ,estado_estudio=" + C12estado_estudio + " ,nota=" + C13nota + " ,cobertura=" + C14cobertura + " ,fk_idorden_lab=" + C15fk_idorden_lab + " ,fk_idlab_estudio=" + C16fk_idlab_estudio + " )";
	}
}
