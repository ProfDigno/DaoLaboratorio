package FORMULARIO.DAO;

import BASEDATO.EvenConexion;
import FORMULARIO.ENTIDAD.persona;
import Evento.JasperReport.EvenJasperReport;
import Evento.Jtable.EvenJtable;
import Evento.Mensaje.EvenMensajeJoptionpane;
import Evento.Fecha.EvenFecha;
import java.sql.ResultSet;
import java.sql.Connection;
import java.sql.PreparedStatement;
import javax.swing.JTable;

public class DAO_persona {

    EvenConexion eveconn = new EvenConexion();
    EvenJtable evejt = new EvenJtable();
    EvenJasperReport rep = new EvenJasperReport();
    EvenMensajeJoptionpane evemen = new EvenMensajeJoptionpane();
    EvenFecha evefec = new EvenFecha();
    private String mensaje_insert = "PERSONA GUARDADO CORRECTAMENTE";
    private String mensaje_update = "PERSONA MODIFICADO CORECTAMENTE";
    private String sql_insert = "INSERT INTO persona(idpersona,nombre,apellido,cedula,telefono,direccion,fec_nac,genero,nro_tarjeta,registro,tipo_persona,fk_idtipo_seguro,fk_idplan_seguro,fk_iddirec_barrio,fk_iddirec_ciudad) VALUES (?,?,?,?,?,?,?,?,?,?,?,?,?,?,?);";
    private String sql_update = "UPDATE persona SET nombre=?,apellido=?,cedula=?,telefono=?,direccion=?,fec_nac=?,genero=?,nro_tarjeta=?,registro=?,tipo_persona=?,fk_idtipo_seguro=?,fk_idplan_seguro=?,fk_iddirec_barrio=?,fk_iddirec_ciudad=? WHERE idpersona=?;";
    private String sql_select = "SELECT idpersona,nombre,apellido,cedula,telefono,direccion,fec_nac,genero,nro_tarjeta,registro,tipo_persona,fk_idtipo_seguro,fk_idplan_seguro,fk_iddirec_barrio,fk_iddirec_ciudad FROM persona order by 1 desc limit 20;";
    private String sql_cargar = "SELECT idpersona,nombre,apellido,cedula,telefono,direccion,fec_nac,genero,nro_tarjeta,registro,tipo_persona,fk_idtipo_seguro,fk_idplan_seguro,fk_iddirec_barrio,fk_iddirec_ciudad FROM persona WHERE idpersona=";

    public void insertar_persona(Connection conn, persona per) {
        per.setC1idpersona(eveconn.getInt_ultimoID_mas_uno(conn, per.getTb_persona(), per.getId_idpersona()));
        String titulo = "insertar_persona";
        PreparedStatement pst = null;
        java.sql.Date dateCumple = java.sql.Date.valueOf(per.getC7fec_nac());
        try {
            pst = conn.prepareStatement(sql_insert);
            pst.setInt(1, per.getC1idpersona());
            pst.setString(2, per.getC2nombre());
            pst.setString(3, per.getC3apellido());
            pst.setString(4, per.getC4cedula());
            pst.setString(5, per.getC5telefono());
            pst.setString(6, per.getC6direccion());
            pst.setDate(7, dateCumple);
            pst.setString(8, per.getC8genero());
            pst.setString(9, per.getC9nro_tarjeta());
            pst.setInt(10, per.getC10registro());
            pst.setString(11, per.getC11tipo_persona());
            pst.setInt(12, per.getC12fk_idtipo_seguro());
            pst.setInt(13, per.getC13fk_idplan_seguro());
            pst.setInt(14, per.getC14fk_iddirec_barrio());
            pst.setInt(15, per.getC15fk_iddirec_ciudad());
            pst.execute();
            pst.close();
            evemen.Imprimir_serial_sql(sql_insert + "\n" + per.toString(), titulo);
            evemen.guardado_correcto(mensaje_insert, true);
        } catch (Exception e) {
            evemen.mensaje_error(e, sql_insert + "\n" + per.toString(), titulo);
        }
    }

    public void update_persona(Connection conn, persona per) {
        String titulo = "update_persona";
        PreparedStatement pst = null;
        java.sql.Date dateCumple = java.sql.Date.valueOf(per.getC7fec_nac());
        try {
            pst = conn.prepareStatement(sql_update);
            pst.setString(1, per.getC2nombre());
            pst.setString(2, per.getC3apellido());
            pst.setString(3, per.getC4cedula());
            pst.setString(4, per.getC5telefono());
            pst.setString(5, per.getC6direccion());
            pst.setDate(6, dateCumple);
            pst.setString(7, per.getC8genero());
            pst.setString(8, per.getC9nro_tarjeta());
            pst.setInt(9, per.getC10registro());
            pst.setString(10, per.getC11tipo_persona());
            pst.setInt(11, per.getC12fk_idtipo_seguro());
            pst.setInt(12, per.getC13fk_idplan_seguro());
            pst.setInt(13, per.getC14fk_iddirec_barrio());
            pst.setInt(14, per.getC15fk_iddirec_ciudad());
            pst.setInt(15, per.getC1idpersona());
            pst.execute();
            pst.close();
            evemen.Imprimir_serial_sql(sql_update + "\n" + per.toString(), titulo);
            evemen.modificado_correcto(mensaje_update, true);
        } catch (Exception e) {
            evemen.mensaje_error(e, sql_update + "\n" + per.toString(), titulo);
        }
    }

    public void cargar_persona(Connection conn, persona per, int id) {
        String titulo = "Cargar_persona";
        try {
            ResultSet rs = eveconn.getResulsetSQL(conn, sql_cargar + id, titulo);
            if (rs.next()) {
                per.setC1idpersona(rs.getInt(1));
                per.setC2nombre(rs.getString(2));
                per.setC3apellido(rs.getString(3));
                per.setC4cedula(rs.getString(4));
                per.setC5telefono(rs.getString(5));
                per.setC6direccion(rs.getString(6));
                per.setC7fec_nac(rs.getString(7));
                per.setC8genero(rs.getString(8));
                per.setC9nro_tarjeta(rs.getString(9));
                per.setC10registro(rs.getInt(10));
                per.setC11tipo_persona(rs.getString(11));
                per.setC12fk_idtipo_seguro(rs.getInt(12));
                per.setC13fk_idplan_seguro(rs.getInt(13));
                per.setC14fk_iddirec_barrio(rs.getInt(14));
                per.setC15fk_iddirec_ciudad(rs.getInt(15));
                evemen.Imprimir_serial_sql(sql_cargar + "\n" + per.toString(), titulo);
            }
        } catch (Exception e) {
            evemen.mensaje_error(e, sql_cargar + "\n" + per.toString(), titulo);
        }
    }

    public void actualizar_tabla_persona(Connection conn, JTable tbltabla) {
        eveconn.Select_cargar_jtable(conn, sql_select, tbltabla);
        ancho_tabla_persona(tbltabla);
    }

    public void ancho_tabla_persona(JTable tbltabla) {
        int Ancho[] = {7, 7, 7, 7, 7, 7, 7, 7, 7, 7, 7, 7, 7, 7, 7};
        evejt.setAnchoColumnaJtable(tbltabla, Ancho);
    }

    public void actualizar_tabla_buscar_persona(Connection conn, JTable tbltabla,String campo,String buscar,String tipo_persona) {
        String sql = "select p.idpersona as idp,p.nombre,p.apellido,\n"
                + "p.cedula,tp.nombre as seguro,ps.nombre as planseguro \n"
                + "from persona p,tipo_seguro tp,plan_seguro ps \n"
                + "where p.fk_idtipo_seguro=tp.idtipo_seguro\n"
                + "and p.fk_idplan_seguro=ps.idplan_seguro\n"
                + "and p.tipo_persona='"+tipo_persona+"' \n"
                + "and "+campo+" ilike'%"+buscar+"%' "
                + "order by p.apellido asc limit 50;";
        eveconn.Select_cargar_jtable(conn, sql, tbltabla);
        ancho_tabla_buscar_persona(tbltabla);
    }
    public int  getInt_buscar_idpersona(Connection conn,String campo,String buscar,String tipo_persona) {
        int idpersona=0;
        String sql = "select p.idpersona as idp,p.nombre,p.apellido,\n"
                + "p.cedula,tp.nombre as seguro,ps.nombre as planseguro \n"
                + "from persona p,tipo_seguro tp,plan_seguro ps \n"
                + "where p.fk_idtipo_seguro=tp.idtipo_seguro\n"
                + "and p.fk_idplan_seguro=ps.idplan_seguro\n"
                + "and p.tipo_persona='"+tipo_persona+"' \n"
                + "and "+campo+" ilike'%"+buscar+"%' "
                + " ";
        String titulo = "getInt_buscar_idpersona";
        try {
            ResultSet rs = eveconn.getResulsetSQL(conn, sql, titulo);
            int cantidad=0;
            while (rs.next()) {
                cantidad++;
                idpersona=rs.getInt("idp");
                System.out.println("CANTIDAD:"+cantidad);
            }
              if(cantidad>1){
                  idpersona=0;
              }
        } catch (Exception e) {
            evemen.mensaje_error(e, sql_cargar + "\n" , titulo);
        }
        
        return idpersona;
    }
    public void ancho_tabla_buscar_persona(JTable tbltabla) {
        int Ancho[] = {10,20,20,10,10,30};
        evejt.setAnchoColumnaJtable(tbltabla, Ancho);
    }
}
