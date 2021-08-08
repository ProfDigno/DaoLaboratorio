	package FORMULARIO.ENTIDAD;
public class validar_estudio {

//---------------DECLARAR VARIABLES---------------
private int C1idvalidar_estudio;
private String C2genero;
private int C3edad_minimo;
private int C4edad_maximo;
private int C5valor_minimo;
private int C6valor_maximo;
private static String nom_tabla;
private static String nom_idtabla;
//---------------TABLA-ID---------------
	public validar_estudio() {
		setTb_validar_estudio("validar_estudio");
		setId_idvalidar_estudio("idvalidar_estudio");
	}
	public static String getTb_validar_estudio(){
		return nom_tabla;
	}
	public static void setTb_validar_estudio(String nom_tabla){
		validar_estudio.nom_tabla = nom_tabla;
	}
	public static String getId_idvalidar_estudio(){
		return nom_idtabla;
	}
	public static void setId_idvalidar_estudio(String nom_idtabla){
		validar_estudio.nom_idtabla = nom_idtabla;
	}
//---------------GET-SET-CAMPOS---------------
	public int getC1idvalidar_estudio(){
		return C1idvalidar_estudio;
	}
	public void setC1idvalidar_estudio(int C1idvalidar_estudio){
		this.C1idvalidar_estudio = C1idvalidar_estudio;
	}
	public String getC2genero(){
		return C2genero;
	}
	public void setC2genero(String C2genero){
		this.C2genero = C2genero;
	}
	public int getC3edad_minimo(){
		return C3edad_minimo;
	}
	public void setC3edad_minimo(int C3edad_minimo){
		this.C3edad_minimo = C3edad_minimo;
	}
	public int getC4edad_maximo(){
		return C4edad_maximo;
	}
	public void setC4edad_maximo(int C4edad_maximo){
		this.C4edad_maximo = C4edad_maximo;
	}
	public int getC5valor_minimo(){
		return C5valor_minimo;
	}
	public void setC5valor_minimo(int C5valor_minimo){
		this.C5valor_minimo = C5valor_minimo;
	}
	public int getC6valor_maximo(){
		return C6valor_maximo;
	}
	public void setC6valor_maximo(int C6valor_maximo){
		this.C6valor_maximo = C6valor_maximo;
	}
	public String toString() {
		return "validar_estudio(" + ",idvalidar_estudio=" + C1idvalidar_estudio + " ,genero=" + C2genero + " ,edad_minimo=" + C3edad_minimo + " ,edad_maximo=" + C4edad_maximo + " ,valor_minimo=" + C5valor_minimo + " ,valor_maximo=" + C6valor_maximo + " )";
	}
}
