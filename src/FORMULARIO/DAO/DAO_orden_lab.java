package FORMULARIO.DAO;

import BASEDATO.EvenConexion;
import FORMULARIO.ENTIDAD.orden_lab;
import Evento.JasperReport.EvenJasperReport;
import Evento.Jtable.EvenJtable;
import Evento.Mensaje.EvenMensajeJoptionpane;
import Evento.Fecha.EvenFecha;
import FORMULARIO.ENTIDAD.tabla_orden_lab;
import java.sql.ResultSet;
import java.sql.Connection;
import java.sql.PreparedStatement;
import javax.swing.JTable;

public class DAO_orden_lab {

    EvenConexion eveconn = new EvenConexion();
    EvenJtable evejt = new EvenJtable();
    EvenJasperReport rep = new EvenJasperReport();
    EvenMensajeJoptionpane evemen = new EvenMensajeJoptionpane();
    EvenFecha evefec = new EvenFecha();
    private tabla_orden_lab tbl_or = new tabla_orden_lab();
//    EvenJasperReport rp=new EvenJasperReport()
    private String mensaje_insert = "ORDEN_LAB GUARDADO CORRECTAMENTE";
    private String mensaje_update = "ORDEN_LAB MODIFICADO CORECTAMENTE";
    private String sql_insert = "INSERT INTO public.orden_lab(idorden_lab,fecha_inicio,fecha_fin,estado,observacion,visacion,fk_idpersona1,fk_idpersona2,fk_idpersona3,fk_idorden_lugar,fk_idusuario,nro_documento) VALUES (?,?,?,?,?,?,?,?,?,?,?,?);";
    private String sql_update = "UPDATE public.orden_lab SET fecha_inicio=?,fecha_fin=?,estado=?,observacion=?,visacion=?,fk_idpersona1=?,fk_idpersona2=?,fk_idpersona3=?,fk_idorden_lugar=?,fk_idusuario=?,nro_documento=? WHERE idorden_lab=?;";
//    private String sql_select = "SELECT idorden_lab,fecha_inicio,fecha_fin,estado,observacion,visacion,fk_idpersona1,fk_idpersona2,fk_idpersona3,fk_idorden_lugar,fk_idusuario FROM orden_lab order by 1 desc;";
    private String sql_cargar = "SELECT idorden_lab,fecha_inicio,fecha_fin,estado,observacion,visacion,"
            + "fk_idpersona1,fk_idpersona2,fk_idpersona3,fk_idorden_lugar,fk_idusuario,nro_documento FROM public.orden_lab WHERE idorden_lab=";
    private String sql_update_estado = "UPDATE public.orden_lab SET fecha_fin=?,estado=? WHERE idorden_lab=?;";

    public void insertar_orden_lab(Connection conn, orden_lab ord) {
        ord.setC1idorden_lab(eveconn.getInt_ultimoID_mas_uno(conn, ord.getTb_orden_lab(), ord.getId_idorden_lab()));
        String titulo = "insertar_orden_lab";
        PreparedStatement pst = null;
        try {
            pst = conn.prepareStatement(sql_insert);
            pst.setInt(1, ord.getC1idorden_lab());
            pst.setTimestamp(2, evefec.getTimestamp_sistema());
            pst.setTimestamp(3, evefec.getTimestamp_sistema());
            pst.setString(4, ord.getC4estado());
            pst.setString(5, ord.getC5observacion());
            pst.setDouble(6, ord.getC6visacion());
            pst.setInt(7, ord.getC7fk_idpersona1());
            pst.setInt(8, ord.getC8fk_idpersona2());
            pst.setInt(9, ord.getC9fk_idpersona3());
            pst.setInt(10, ord.getC10fk_idorden_lugar());
            pst.setInt(11, ord.getC11fk_idusuario());
            pst.setInt(12, ord.getC12nro_documento());
            pst.execute();
            pst.close();
            evemen.Imprimir_serial_sql(sql_insert + "\n" + ord.toString(), titulo);
            evemen.guardado_correcto(mensaje_insert, true);
        } catch (Exception e) {
            evemen.mensaje_error(e, sql_insert + "\n" + ord.toString(), titulo);
        }
    }

    public void update_orden_lab(Connection conn, orden_lab ord) {
        String titulo = "update_orden_lab";
        PreparedStatement pst = null;
        try {
            pst = conn.prepareStatement(sql_update);
            pst.setTimestamp(1, evefec.getTimestamp_sistema());
            pst.setTimestamp(2, evefec.getTimestamp_sistema());
            pst.setString(3, ord.getC4estado());
            pst.setString(4, ord.getC5observacion());
            pst.setDouble(5, ord.getC6visacion());
            pst.setInt(6, ord.getC7fk_idpersona1());
            pst.setInt(7, ord.getC8fk_idpersona2());
            pst.setInt(8, ord.getC9fk_idpersona3());
            pst.setInt(9, ord.getC10fk_idorden_lugar());
            pst.setInt(10, ord.getC11fk_idusuario());
            pst.setInt(11, ord.getC1idorden_lab());
            pst.setInt(12, ord.getC12nro_documento());
            pst.execute();
            pst.close();
            evemen.Imprimir_serial_sql(sql_update + "\n" + ord.toString(), titulo);
            evemen.modificado_correcto(mensaje_update, true);
        } catch (Exception e) {
            evemen.mensaje_error(e, sql_update + "\n" + ord.toString(), titulo);
        }
    }

    public void update_orden_lab_estado(Connection conn, orden_lab ord) {
        String titulo = "update_orden_lab_estado";
        PreparedStatement pst = null;
        try {
            pst = conn.prepareStatement(sql_update_estado);
            pst.setTimestamp(1, evefec.getTimestamp_sistema());
            pst.setString(2, ord.getC4estado());
            pst.setInt(3, ord.getC1idorden_lab());
            pst.execute();
            pst.close();
            evemen.Imprimir_serial_sql(sql_update_estado + "\n" + ord.toString(), titulo);
            evemen.modificado_correcto(mensaje_update, true);
        } catch (Exception e) {
            evemen.mensaje_error(e, sql_update_estado + "\n" + ord.toString(), titulo);
        }
    }

    public void cargar_orden_lab(Connection conn, orden_lab ord, int id) {
        String titulo = "Cargar_orden_lab";
        try {
            ResultSet rs = eveconn.getResulsetSQL(conn, sql_cargar + id, titulo);
            if (rs.next()) {
                ord.setC1idorden_lab(rs.getInt(1));
                ord.setC2fecha_inicio(rs.getString(2));
                ord.setC3fecha_fin(rs.getString(3));
                ord.setC4estado(rs.getString(4));
                ord.setC5observacion(rs.getString(5));
                ord.setC6visacion(rs.getDouble(6));
                ord.setC7fk_idpersona1(rs.getInt(7));
                ord.setC8fk_idpersona2(rs.getInt(8));
                ord.setC9fk_idpersona3(rs.getInt(9));
                ord.setC10fk_idorden_lugar(rs.getInt(10));
                ord.setC11fk_idusuario(rs.getInt(11));
                ord.setC12nro_documento(rs.getInt(12));
                evemen.Imprimir_serial_sql(sql_cargar + "\n" + ord.toString(), titulo);
            }
        } catch (Exception e) {
            evemen.mensaje_error(e, sql_cargar + "\n" + ord.toString(), titulo);
        }
    }

    public void actualizar_tabla_orden_lab(Connection conn, JTable tbltabla,String filtro) {
        String sql = "select ol.idorden_lab as ido,ol.nro_documento as doc,to_char(ol.fecha_inicio,'yyyy-MM-dd HH24:MI') as fec_inicio,\n"
                + "ol.estado,olu.nombre as pedido_de,ol.visacion as visa,ts.nombre as seguro,\n"
                + "(p1.nombre||' '||p1.apellido) as pac_nombre,p1.cedula as pac_ci,\n"
                + "(p2.nombre||' '||p2.apellido) as medico\n"
                + "from public.orden_lab ol,public.persona p1,public.persona p2,public.orden_lugar olu,public.tipo_seguro ts\n"
                + "where ol.fk_idpersona1=p1.idpersona\n"
                + "and p1.fk_idtipo_seguro=ts.idtipo_seguro\n"
                + "and ol.fk_idpersona2=p2.idpersona\n"
                + "and ol.fk_idorden_lugar=olu.idorden_lugar\n"
                + " "+filtro
                + " order by 1 desc";
        eveconn.Select_cargar_jtable(conn, sql, tbltabla);
        ancho_tabla_orden_lab(tbltabla);
    }

    public void ancho_tabla_orden_lab(JTable tbltabla) {
        int Ancho[] = {7,7, 15, 6, 8, 8, 8, 20, 6, 15};
        evejt.setAnchoColumnaJtable(tbltabla, Ancho);
    }

    public void imprimir_orden(Connection conn,int idorden_lab) {
        String sql = "select iol.fk_idorden_lab as idorden,\n"
                + "case when (select lge.orden as orden from public.item_lab_estudio ile1,public.lab_grupo_estudio lge\n"
                + "where ile1.fk_idlab_grupo_estudio=lge.idlab_grupo_estudio\n"
                + "and lge.fk_idlab_grupo="+tbl_or.getIdtabla_area()
                + " and ile1.fk_idlab_estudio=iol.fk_idlab_estudio) is not null \n"
                + "then (select lge.orden as orden from public.item_lab_estudio ile1,public.lab_grupo_estudio lge\n"
                + "where ile1.fk_idlab_grupo_estudio=lge.idlab_grupo_estudio\n"
                + "and lge.fk_idlab_grupo="+tbl_or.getIdtabla_area()
                + " and ile1.fk_idlab_estudio=iol.fk_idlab_estudio)\n"
                    + "else 99 end as ord_a,\n"
                + "case when (select lge.nombre as gru_valor from public.item_lab_estudio ile1,public.lab_grupo_estudio lge\n"
                + "where ile1.fk_idlab_grupo_estudio=lge.idlab_grupo_estudio\n"
                + "and lge.fk_idlab_grupo="+tbl_or.getIdtabla_area()
                + " and ile1.fk_idlab_estudio=iol.fk_idlab_estudio) is not null \n"
                + "then (select lge.nombre as gru_valor from public.item_lab_estudio ile1,public.lab_grupo_estudio lge\n"
                + "where ile1.fk_idlab_grupo_estudio=lge.idlab_grupo_estudio\n"
                + "and lge.fk_idlab_grupo="+tbl_or.getIdtabla_area()
                + " and ile1.fk_idlab_estudio=iol.fk_idlab_estudio)\n"
                    + "else '' end as area,\n"
                + "case when (select lge.orden as orden from public.item_lab_estudio ile1,public.lab_grupo_estudio lge\n"
                + "where ile1.fk_idlab_grupo_estudio=lge.idlab_grupo_estudio\n"
                + "and lge.fk_idlab_grupo="+tbl_or.getIdtabla_seccion()
                + " and ile1.fk_idlab_estudio=iol.fk_idlab_estudio) is not null \n"
                + "then (select lge.orden as orden from public.item_lab_estudio ile1,public.lab_grupo_estudio lge\n"
                + "where ile1.fk_idlab_grupo_estudio=lge.idlab_grupo_estudio\n"
                + "and lge.fk_idlab_grupo="+tbl_or.getIdtabla_seccion()
                + " and ile1.fk_idlab_estudio=iol.fk_idlab_estudio)\n"
                    + "else 99 end as ord_se,\n"
                + "case when (select lge.nombre as gru_valor from public.item_lab_estudio ile1,public.lab_grupo_estudio lge\n"
                + "where ile1.fk_idlab_grupo_estudio=lge.idlab_grupo_estudio\n"
                + "and lge.fk_idlab_grupo="+tbl_or.getIdtabla_seccion()
                + " and ile1.fk_idlab_estudio=iol.fk_idlab_estudio) is not null \n"
                + "then (select lge.nombre as gru_valor from public.item_lab_estudio ile1,public.lab_grupo_estudio lge\n"
                + "where ile1.fk_idlab_grupo_estudio=lge.idlab_grupo_estudio\n"
                + "and lge.fk_idlab_grupo="+tbl_or.getIdtabla_seccion()
                + " and ile1.fk_idlab_estudio=iol.fk_idlab_estudio)\n"
                    + "else '' end as seccion,\n"
                + "case when (select lge.orden as orden from public.item_lab_estudio ile1,public.lab_grupo_estudio lge\n"
                + "where ile1.fk_idlab_grupo_estudio=lge.idlab_grupo_estudio\n"
                + "and lge.fk_idlab_grupo="+tbl_or.getIdtabla_grupo_valor()
                + " and ile1.fk_idlab_estudio=iol.fk_idlab_estudio) is not null \n"
                + "then (select lge.orden as orden from public.item_lab_estudio ile1,public.lab_grupo_estudio lge\n"
                + "where ile1.fk_idlab_grupo_estudio=lge.idlab_grupo_estudio\n"
                + "and lge.fk_idlab_grupo="+tbl_or.getIdtabla_grupo_valor()
                + " and ile1.fk_idlab_estudio=iol.fk_idlab_estudio)\n"
                    + "else 99 end as ord_gv,\n"
                + "case when (select lge.nombre as gru_valor from public.item_lab_estudio ile1,public.lab_grupo_estudio lge\n"
                + "where ile1.fk_idlab_grupo_estudio=lge.idlab_grupo_estudio\n"
                + "and lge.fk_idlab_grupo="+tbl_or.getIdtabla_grupo_valor()
                + " and ile1.fk_idlab_estudio=iol.fk_idlab_estudio) is not null \n"
                + "then (select lge.nombre as gru_valor from public.item_lab_estudio ile1,public.lab_grupo_estudio lge\n"
                + "where ile1.fk_idlab_grupo_estudio=lge.idlab_grupo_estudio\n"
                + "and lge.fk_idlab_grupo="+tbl_or.getIdtabla_grupo_valor()
                + " and ile1.fk_idlab_estudio=iol.fk_idlab_estudio)\n"
                + "else '' end as gru_valor,\n"
                
                + "case when (select ile1.orden as gru_valor from public.item_lab_estudio ile1,public.lab_grupo_estudio lge\n"
                + "where ile1.fk_idlab_grupo_estudio=lge.idlab_grupo_estudio\n"
                + "and lge.fk_idlab_grupo="+tbl_or.getIdtabla_grupo_valor()
                + " and ile1.fk_idlab_estudio=iol.fk_idlab_estudio) is not null \n"
                + "then (select ile1.orden as gru_valor from public.item_lab_estudio ile1,public.lab_grupo_estudio lge\n"
                + "where ile1.fk_idlab_grupo_estudio=lge.idlab_grupo_estudio\n"
                + "and lge.fk_idlab_grupo="+tbl_or.getIdtabla_grupo_valor()
                + " and ile1.fk_idlab_estudio=iol.fk_idlab_estudio)\n"
                + "else 0 end as orden,\n"
                + "case \n"
                + "when (select lge.nombre as gru_valor from public.item_lab_estudio ile1,public.lab_grupo_estudio lge\n"
                + "where ile1.fk_idlab_grupo_estudio=lge.idlab_grupo_estudio\n"
                + "and lge.fk_idlab_grupo="+tbl_or.getIdtabla_muestra()
                + " and ile1.fk_idlab_estudio=iol.fk_idlab_estudio) is not null \n"
                + "and (select lge.nombre as gru_valor from public.item_lab_estudio ile1,public.lab_grupo_estudio lge\n"
                + "where ile1.fk_idlab_grupo_estudio=lge.idlab_grupo_estudio\n"
                + "and lge.fk_idlab_grupo="+tbl_or.getIdtabla_metodo()
                + " and ile1.fk_idlab_estudio=iol.fk_idlab_estudio) is not null\n"
                + "then concat(iol.descripcion,\n"
                + "chr(13),' >>Mues.:',(select lge.nombre as gru_valor from public.item_lab_estudio ile1,public.lab_grupo_estudio lge\n"
                + "where ile1.fk_idlab_grupo_estudio=lge.idlab_grupo_estudio\n"
                + "and lge.fk_idlab_grupo="+tbl_or.getIdtabla_muestra()
                + " and ile1.fk_idlab_estudio=iol.fk_idlab_estudio),\n"
                + "chr(13),' >>Met.:',(select lge.nombre as gru_valor from public.item_lab_estudio ile1,public.lab_grupo_estudio lge\n"
                + "where ile1.fk_idlab_grupo_estudio=lge.idlab_grupo_estudio\n"
                + "and lge.fk_idlab_grupo="+tbl_or.getIdtabla_metodo()
                + " and ile1.fk_idlab_estudio=iol.fk_idlab_estudio))\n"
                + "\n"
                + "when (select lge.nombre as gru_valor from public.item_lab_estudio ile1,public.lab_grupo_estudio lge\n"
                + "where ile1.fk_idlab_grupo_estudio=lge.idlab_grupo_estudio\n"
                + "and lge.fk_idlab_grupo="+tbl_or.getIdtabla_muestra()
                + " and ile1.fk_idlab_estudio=iol.fk_idlab_estudio) is null \n"
                + "and (select lge.nombre as gru_valor from public.item_lab_estudio ile1,public.lab_grupo_estudio lge\n"
                + "where ile1.fk_idlab_grupo_estudio=lge.idlab_grupo_estudio\n"
                + "and lge.fk_idlab_grupo="+tbl_or.getIdtabla_metodo()
                + " and ile1.fk_idlab_estudio=iol.fk_idlab_estudio) is not null\n"
                + "then concat(iol.descripcion,\n"
                + "chr(13),' >>Met.:',(select lge.nombre as gru_valor from public.item_lab_estudio ile1,public.lab_grupo_estudio lge\n"
                + "where ile1.fk_idlab_grupo_estudio=lge.idlab_grupo_estudio\n"
                + "and lge.fk_idlab_grupo="+tbl_or.getIdtabla_metodo()
                + " and ile1.fk_idlab_estudio=iol.fk_idlab_estudio))\n"
                + "\n"
                + "when (select lge.nombre as gru_valor from public.item_lab_estudio ile1,public.lab_grupo_estudio lge\n"
                + "where ile1.fk_idlab_grupo_estudio=lge.idlab_grupo_estudio\n"
                + "and lge.fk_idlab_grupo="+tbl_or.getIdtabla_muestra()
                + " and ile1.fk_idlab_estudio=iol.fk_idlab_estudio) is not null \n"
                + "and (select lge.nombre as gru_valor from public.item_lab_estudio ile1,public.lab_grupo_estudio lge\n"
                + "where ile1.fk_idlab_grupo_estudio=lge.idlab_grupo_estudio\n"
                + "and lge.fk_idlab_grupo="+tbl_or.getIdtabla_metodo()
                + " and ile1.fk_idlab_estudio=iol.fk_idlab_estudio) is null\n"
                + "then concat(iol.descripcion,\n"
                + "chr(13),' >>Mues.:',(select lge.nombre as gru_valor from public.item_lab_estudio ile1,public.lab_grupo_estudio lge\n"
                + "where ile1.fk_idlab_grupo_estudio=lge.idlab_grupo_estudio\n"
                + "and lge.fk_idlab_grupo="+tbl_or.getIdtabla_muestra()
                + " and ile1.fk_idlab_estudio=iol.fk_idlab_estudio))\n"
                + "\n"
                + "else iol.descripcion end as descrip_estudio,\n"
                + "case when (iol.es_numerico=true and iol.estado_estudio='"+tbl_or.getEstado_pedido()+"') then null\n"
                + "when (iol.es_testo=true and iol.estado_estudio='"+tbl_or.getEstado_pedido()+"') then null\n"
                + "when (iol.es_predefinido=true and iol.estado_estudio='"+tbl_or.getEstado_pedido()+"') then null\n"
                + "when (iol.es_numerico=true and iol.estado_estudio='"+tbl_or.getEstado_cargado()+"' and iol.numerico_decimal=0 and iol.valor_numerico>=0) then ((to_char(iol.valor_numerico,'999G999G999'))||' '||iol.nota)\n"
                + "when (iol.es_numerico=true and iol.estado_estudio='"+tbl_or.getEstado_cargado()+"' and iol.numerico_decimal=1 and iol.valor_numerico>=1) then ((to_char(iol.valor_numerico,'999G999G999D9'))||' '||iol.nota)\n"
                + "when (iol.es_numerico=true and iol.estado_estudio='"+tbl_or.getEstado_cargado()+"' and iol.numerico_decimal=2 and iol.valor_numerico>=1) then ((to_char(iol.valor_numerico,'999G999G999D99'))||' '||iol.nota)\n"
                + "when (iol.es_numerico=true and iol.estado_estudio='"+tbl_or.getEstado_cargado()+"' and iol.numerico_decimal=1) then ((to_char(iol.valor_numerico,'0.9'))||' '||iol.nota)\n"
                + "when (iol.es_numerico=true and iol.estado_estudio='"+tbl_or.getEstado_cargado()+"' and iol.numerico_decimal=2) then ((to_char(iol.valor_numerico,'0.99'))||' '||iol.nota)\n"
                + "when (iol.es_testo=true and iol.estado_estudio='"+tbl_or.getEstado_cargado()+"') then (iol.valor_testo)\n"
                + "when (iol.es_predefinido=true and iol.estado_estudio='"+tbl_or.getEstado_cargado()+"') then "
                + "(select lep.nombre from public.lab_estudio_predefinido lep where lep.idlab_estudio_predefinido=iol.valor_predefinido)\n"
                + "else 'mas_tipo' end as resultado,\n"
                + "case when (iol.unidad='*') then ' ' else iol.unidad end as unid, "
                + "case when iol.valor_de_referencia='*' then ' ' \n"
                + "else iol.valor_de_referencia end as referencia,"
                + "to_char(ol.fecha_inicio,'yyyy-MM-dd HH24:MI') as fec_inicio,to_char(ol.fecha_fin,'yyyy-MM-dd HH24:MI') as fec_fin,\n"
                + "p1.idpersona as idpac,(p1.nombre||' '||p1.apellido) as paciente,p1.cedula as ci_pac,to_char(p1.fec_nac,'yyyy-MM-dd') as fec_nac_pac ,\n"
                + "to_char(age(current_date, p1.fec_nac), 'YY\"A\"-mm\"M\"-DD\"D\"') as edad_pac,\n"
                + "(p2.nombre||' '||p2.apellido) as medico,(p3.nombre||' '||p3.apellido) as nom_respo,p3.registro as regis_respo,olu.nombre as pedido_de\n"
                + "from public.item_orden_lab iol, public.orden_lab ol, public.persona p1, public.persona p2, public.persona p3,public.orden_lugar olu\n"
                + "where iol.fk_idorden_lab="+idorden_lab+"\n"
                + "and iol.estado_estudio='"+tbl_or.getEstado_cargado()+"' "
                + " and iol.fk_idorden_lab=ol.idorden_lab\n" 
                + "and ol.fk_idorden_lugar=olu.idorden_lugar\n"
                + "and ol.fk_idpersona1=p1.idpersona\n"
                + "and ol.fk_idpersona2=p2.idpersona\n"
                + "and ol.fk_idpersona3=p3.idpersona\n"
                + "and iol.se_carga=true \n"
                + "order by 2 asc,4 asc,6 asc,8 asc;";
        String titulo="RESULTADO";
        String direccion="src/REPORTE/RESULTADO/repResultado_1.jrxml";
        rep.imprimirjasper(conn, sql, titulo, direccion);
    }
}
