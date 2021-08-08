	package FORMULARIO.ENTIDAD;
public class persona {

    /**
     * @return the sta_tipo_pers_paciente
     */
    public static String getSta_tipo_pers_paciente() {
        return sta_tipo_pers_paciente;
    }

    /**
     * @return the sta_tipo_pers_medico
     */
    public static String getSta_tipo_pers_medico() {
        return sta_tipo_pers_medico;
    }

    /**
     * @return the sta_tipo_pers_represent
     */
    public static String getSta_tipo_pers_represent() {
        return sta_tipo_pers_represent;
    }

//---------------DECLARAR VARIABLES---------------
private int C1idpersona;
private String C2nombre;
private String C3apellido;
private String C4cedula;
private String C5telefono;
private String C6direccion;
private String C7fec_nac;
private String C8genero;
private String C9nro_tarjeta;
private int C10registro;
private String C11tipo_persona;
private int C12fk_idtipo_seguro;
private int C13fk_idplan_seguro;
private int C14fk_iddirec_barrio;
private int C15fk_iddirec_ciudad;
private static String nom_tabla;
private static String nom_idtabla;
private static String sta_tipo_persona;
private static String sta_tipo_pers_paciente="paciente";
private static String sta_tipo_pers_medico="medico";
private static String sta_tipo_pers_represent="representante";
//---------------TABLA-ID---------------
	public persona() {
		setTb_persona("persona");
		setId_idpersona("idpersona");
	}
	public static String getTb_persona(){
		return nom_tabla;
	}
	public static void setTb_persona(String nom_tabla){
		persona.nom_tabla = nom_tabla;
	}
	public static String getId_idpersona(){
		return nom_idtabla;
	}
	public static void setId_idpersona(String nom_idtabla){
		persona.nom_idtabla = nom_idtabla;
	}
//---------------GET-SET-CAMPOS---------------
	public int getC1idpersona(){
		return C1idpersona;
	}
	public void setC1idpersona(int C1idpersona){
		this.C1idpersona = C1idpersona;
	}
	public String getC2nombre(){
		return C2nombre;
	}
	public void setC2nombre(String C2nombre){
		this.C2nombre = C2nombre;
	}
	public String getC3apellido(){
		return C3apellido;
	}
	public void setC3apellido(String C3apellido){
		this.C3apellido = C3apellido;
	}
	public String getC4cedula(){
		return C4cedula;
	}
	public void setC4cedula(String C4cedula){
		this.C4cedula = C4cedula;
	}
	public String getC5telefono(){
		return C5telefono;
	}
	public void setC5telefono(String C5telefono){
		this.C5telefono = C5telefono;
	}
	public String getC6direccion(){
		return C6direccion;
	}
	public void setC6direccion(String C6direccion){
		this.C6direccion = C6direccion;
	}
	public String getC7fec_nac(){
		return C7fec_nac;
	}
	public void setC7fec_nac(String C7fec_nac){
		this.C7fec_nac = C7fec_nac;
	}
	public String getC8genero(){
		return C8genero;
	}
	public void setC8genero(String C8genero){
		this.C8genero = C8genero;
	}
	public String getC9nro_tarjeta(){
		return C9nro_tarjeta;
	}
	public void setC9nro_tarjeta(String C9nro_tarjeta){
		this.C9nro_tarjeta = C9nro_tarjeta;
	}
	public int getC10registro(){
		return C10registro;
	}
	public void setC10registro(int C10registro){
		this.C10registro = C10registro;
	}
	public String getC11tipo_persona(){
		return C11tipo_persona;
	}
	public void setC11tipo_persona(String C11tipo_persona){
		this.C11tipo_persona = C11tipo_persona;
	}
	public int getC12fk_idtipo_seguro(){
		return C12fk_idtipo_seguro;
	}
	public void setC12fk_idtipo_seguro(int C12fk_idtipo_seguro){
		this.C12fk_idtipo_seguro = C12fk_idtipo_seguro;
	}
	public int getC13fk_idplan_seguro(){
		return C13fk_idplan_seguro;
	}
	public void setC13fk_idplan_seguro(int C13fk_idplan_seguro){
		this.C13fk_idplan_seguro = C13fk_idplan_seguro;
	}
	public int getC14fk_iddirec_barrio(){
		return C14fk_iddirec_barrio;
	}
	public void setC14fk_iddirec_barrio(int C14fk_iddirec_barrio){
		this.C14fk_iddirec_barrio = C14fk_iddirec_barrio;
	}
	public String toString() {
		return "persona(" + ",idpersona=" + C1idpersona + " ,nombre=" + C2nombre + " ,apellido=" + C3apellido + " ,cedula=" + C4cedula + " ,telefono=" + C5telefono + " ,direccion=" + C6direccion + " ,fec_nac=" + C7fec_nac + " ,genero=" + C8genero + " ,nro_tarjeta=" + C9nro_tarjeta + " ,registro=" + C10registro + " ,tipo_persona=" + C11tipo_persona + " ,fk_idtipo_seguro=" + C12fk_idtipo_seguro + " ,fk_idplan_seguro=" + C13fk_idplan_seguro + " ,fk_iddirec_barrio=" + C14fk_iddirec_barrio + " )";
	}

    /**
     * @return the C15fk_iddirec_ciudad
     */
    public int getC15fk_iddirec_ciudad() {
        return C15fk_iddirec_ciudad;
    }

    /**
     * @param C15fk_iddirec_ciudad the C15fk_iddirec_ciudad to set
     */
    public void setC15fk_iddirec_ciudad(int C15fk_iddirec_ciudad) {
        this.C15fk_iddirec_ciudad = C15fk_iddirec_ciudad;
    }

    /**
     * @return the sta_tipo_persona
     */
    public static String getSta_tipo_persona() {
        return sta_tipo_persona;
    }

    /**
     * @param aSta_tipo_persona the sta_tipo_persona to set
     */
    public static void setSta_tipo_persona(String aSta_tipo_persona) {
        sta_tipo_persona = aSta_tipo_persona;
    }
}
