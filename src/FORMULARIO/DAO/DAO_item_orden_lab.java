package FORMULARIO.DAO;

import BASEDATO.EvenConexion;
import FORMULARIO.ENTIDAD.item_orden_lab;
import Evento.JasperReport.EvenJasperReport;
import Evento.Jtable.EvenJtable;
import Evento.Mensaje.EvenMensajeJoptionpane;
import Evento.Fecha.EvenFecha;
import FORMULARIO.BO.BO_lab_estudio;
import FORMULARIO.ENTIDAD.lab_estudio;
import FORMULARIO.ENTIDAD.orden_lab;
import FORMULARIO.ENTIDAD.tabla_orden_lab;
import java.sql.ResultSet;
import java.sql.Connection;
import java.sql.PreparedStatement;
import javax.swing.JTable;

public class DAO_item_orden_lab {

    EvenConexion eveconn = new EvenConexion();
    EvenJtable evejt = new EvenJtable();
    EvenJasperReport rep = new EvenJasperReport();
    EvenMensajeJoptionpane evemen = new EvenMensajeJoptionpane();
    EvenFecha evefec = new EvenFecha();
    private lab_estudio estu = new lab_estudio();
    private BO_lab_estudio esBO = new BO_lab_estudio();
    private DAO_lab_estudio esdao = new DAO_lab_estudio();
    private tabla_orden_lab tbl_or = new tabla_orden_lab();
    
    private String mensaje_insert = "ITEM_ORDEN_LAB GUARDADO CORRECTAMENTE";
    private String mensaje_update = "ITEM_ORDEN_LAB MODIFICADO CORECTAMENTE";
    private String sql_insert = "INSERT INTO public.item_orden_lab(iditem_orden_lab,descripcion,numerico_decimal,"
            + "valor_numerico,valor_testo,valor_predefinido,valor_de_referencia,unidad,"
            + "es_numerico,es_testo,es_predefinido,estado_estudio,nota,cobertura,"
            + "fk_idorden_lab,fk_idlab_estudio,sku,se_carga) VALUES (?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?);";
    private String sql_update = "UPDATE public.item_orden_lab SET descripcion=?,numerico_decimal=?,valor_numerico=?,valor_testo=?,"
            + "valor_predefinido=?,valor_de_referencia=?,unidad=?,"
            + "es_numerico=?,es_testo=?,es_predefinido=?,estado_estudio=?,nota=?,cobertura=?,"
            + "fk_idorden_lab=?,fk_idlab_estudio=?,sku=?,se_carga=? WHERE iditem_orden_lab=?;";
    private String sql_update_carga_numerico = "UPDATE public.item_orden_lab SET valor_numerico=?,estado_estudio=?,nota=? WHERE iditem_orden_lab=?;";
    private String sql_update_carga_testo = "UPDATE public.item_orden_lab SET valor_testo=?,estado_estudio=?,nota=? WHERE iditem_orden_lab=?;";
    private String sql_update_carga_predefinido = "UPDATE public.item_orden_lab SET valor_predefinido=?,estado_estudio=?,nota=? WHERE iditem_orden_lab=?;";

    private String sql_select = "SELECT iditem_orden_lab,descripcion,numerico_decimal,valor_numerico,valor_testo,valor_predefinido,valor_de_referencia,unidad,es_numerico,es_testo,es_predefinido,estado_estudio,nota,cobertura,fk_idorden_lab,fk_idlab_estudio,sku,se_carga FROM public.item_orden_lab order by 1 desc;";
    private String sql_cargar = "SELECT iditem_orden_lab,descripcion,numerico_decimal,valor_numerico,valor_testo,valor_predefinido,valor_de_referencia,unidad,es_numerico,es_testo,es_predefinido,estado_estudio,nota,cobertura,fk_idorden_lab,fk_idlab_estudio,sku,se_carga FROM public.item_orden_lab WHERE iditem_orden_lab=";

    public void insertar_item_orden_lab_de_orden(Connection conn, JTable tblitem_producto, orden_lab ordl) {
        item_orden_lab iord = new item_orden_lab();
        for (int row = 0; row < tblitem_producto.getRowCount(); row++) {
            String idlab_estudio = ((tblitem_producto.getModel().getValueAt(row, 0).toString()));
            String fk_idlab_grupo_estudio = ((tblitem_producto.getModel().getValueAt(row, 1).toString()));
            String descripcion = ((tblitem_producto.getModel().getValueAt(row, 2).toString()));
            String unidad = ((tblitem_producto.getModel().getValueAt(row, 3).toString()));
            String cobertura = ((tblitem_producto.getModel().getValueAt(row, 4).toString()));
            boolean setcobertura = false;
            if (cobertura.equals("true")) {
                setcobertura = true;
            }
            if (Integer.parseInt(fk_idlab_grupo_estudio) == 0) {
                int fk_idlab_estudio = Integer.parseInt(idlab_estudio);
                esdao.cargar_lab_estudio(conn, estu, fk_idlab_estudio);
                iord.setC2descripcion(descripcion);
                iord.setC3numerico_decimal(estu.getC4numerico_decimal());
                iord.setC4valor_numerico(0);
                iord.setC5valor_testo(" ");
                iord.setC6valor_predefinido(0);
                iord.setC7valor_de_referencia(estu.getC9valor_de_referencia());
                iord.setC8unidad(unidad);
                iord.setC9es_numerico(estu.getC5es_numerico());
                iord.setC10es_testo(estu.getC6es_testo());
                iord.setC11es_predefinido(estu.getC7es_predefinido());
                iord.setC12estado_estudio(tbl_or.getEstado_pedido());
                iord.setC13nota(" ");
                iord.setC14cobertura(setcobertura);
                iord.setC15fk_idorden_lab(ordl.getC1idorden_lab());
                iord.setC16fk_idlab_estudio(fk_idlab_estudio);
                iord.setC17sku(estu.getC12sku());
                iord.setC18se_carga(estu.isC13se_carga());
                insertar_item_orden_lab(conn, iord);
            } else {
                String titulo = "cargar_item_orden_lab_estudio_panel";
                String sql = "select le.idlab_estudio,le.nombre_completo,le.numerico_decimal,\n"
                        + "le.es_numerico,le.es_testo,le.es_predefinido,le.valor_de_referencia,lu.nombre as unidad, \n"
                        + "le.sku,le.se_carga "
                        + "from public.item_lab_estudio ile, public.lab_estudio le,public.lab_unidad lu \n"
                        + "where ile.fk_idlab_estudio=le.idlab_estudio\n"
                        + "and le.fk_idlab_unidad=lu.idlab_unidad\n"
                        + "and ile.fk_idlab_grupo_estudio="+fk_idlab_grupo_estudio;
                        //+ " and le.fk_idlab_grupo_estudio=0";
                try {
                    ResultSet rs = eveconn.getResulsetSQL(conn, sql, titulo);
                    while (rs.next()) {
                        iord.setC2descripcion(rs.getString("nombre_completo"));
                        iord.setC3numerico_decimal(rs.getInt("numerico_decimal"));
                        iord.setC4valor_numerico(0);
                        iord.setC5valor_testo(" ");
                        iord.setC6valor_predefinido(0);
                        iord.setC7valor_de_referencia(rs.getString("valor_de_referencia"));
                        iord.setC8unidad(rs.getString("unidad"));
                        iord.setC9es_numerico(rs.getBoolean("es_numerico"));
                        iord.setC10es_testo(rs.getBoolean("es_testo"));
                        iord.setC11es_predefinido(rs.getBoolean("es_predefinido"));
                        iord.setC12estado_estudio(tbl_or.getEstado_pedido());
                        iord.setC13nota(" ");
                        iord.setC14cobertura(setcobertura);
                        iord.setC15fk_idorden_lab(ordl.getC1idorden_lab());
                        iord.setC16fk_idlab_estudio(rs.getInt("idlab_estudio"));
                        iord.setC17sku(rs.getInt("sku"));
                        iord.setC18se_carga(rs.getBoolean("se_carga"));
                        insertar_item_orden_lab(conn, iord);
                    }
                } catch (Exception e) {
                    evemen.mensaje_error(e, sql_cargar + "\n" + iord.toString(), titulo);
                }
            }
        }
    }

    public void cargar_item_orden_lab_estudio_panel(Connection conn, item_orden_lab iord, int id) {
        String titulo = "cargar_item_orden_lab_estudio_panel";
        try {
            ResultSet rs = eveconn.getResulsetSQL(conn, sql_cargar + id, titulo);
            if (rs.next()) {
                iord.setC1iditem_orden_lab(rs.getInt(1));
                iord.setC2descripcion(rs.getString(2));
                iord.setC3numerico_decimal(rs.getInt(3));
                iord.setC4valor_numerico(rs.getDouble(4));
                iord.setC5valor_testo(rs.getString(5));
                iord.setC6valor_predefinido(rs.getInt(6));
                iord.setC7valor_de_referencia(rs.getString(7));
                iord.setC8unidad(rs.getString(8));
                iord.setC9es_numerico(rs.getBoolean(9));
                iord.setC10es_testo(rs.getBoolean(10));
                iord.setC11es_predefinido(rs.getBoolean(11));
                iord.setC12estado_estudio(rs.getString(12));
                iord.setC13nota(rs.getString(13));
                iord.setC14cobertura(rs.getBoolean(14));
                iord.setC15fk_idorden_lab(rs.getInt(15));
                iord.setC16fk_idlab_estudio(rs.getInt(16));
                iord.setC17sku(rs.getInt(17));
                evemen.Imprimir_serial_sql(sql_cargar + "\n" + iord.toString(), titulo);
            }
        } catch (Exception e) {
            evemen.mensaje_error(e, sql_cargar + "\n" + iord.toString(), titulo);
        }
    }

    private void insertar_item_orden_lab(Connection conn, item_orden_lab iord) {
        iord.setC1iditem_orden_lab(eveconn.getInt_ultimoID_mas_uno(conn, iord.getTb_item_orden_lab(), iord.getId_iditem_orden_lab()));
        String titulo = "insertar_item_orden_lab";
        PreparedStatement pst = null;
        try {
            pst = conn.prepareStatement(sql_insert);
            pst.setInt(1, iord.getC1iditem_orden_lab());
            pst.setString(2, iord.getC2descripcion());
            pst.setInt(3, iord.getC3numerico_decimal());
            pst.setDouble(4, iord.getC4valor_numerico());
            pst.setString(5, iord.getC5valor_testo());
            pst.setInt(6, iord.getC6valor_predefinido());
            pst.setString(7, iord.getC7valor_de_referencia());
            pst.setString(8, iord.getC8unidad());
            pst.setBoolean(9, iord.getC9es_numerico());
            pst.setBoolean(10, iord.getC10es_testo());
            pst.setBoolean(11, iord.getC11es_predefinido());
            pst.setString(12, iord.getC12estado_estudio());
            pst.setString(13, iord.getC13nota());
            pst.setBoolean(14, iord.getC14cobertura());
            pst.setInt(15, iord.getC15fk_idorden_lab());
            pst.setInt(16, iord.getC16fk_idlab_estudio());
            pst.setInt(17,iord.getC17sku());
            pst.setBoolean(18, iord.isC18se_carga());
            pst.execute();
            pst.close();
            evemen.Imprimir_serial_sql(sql_insert + "\n" + iord.toString(), titulo);
            evemen.guardado_correcto(mensaje_insert, false);
        } catch (Exception e) {
            evemen.mensaje_error(e, sql_insert + "\n" + iord.toString(), titulo);
        }
    }

    public void update_item_orden_lab(Connection conn, item_orden_lab iord) {
        String titulo = "update_item_orden_lab";
        PreparedStatement pst = null;
        try {
            pst = conn.prepareStatement(sql_update);
            pst.setString(1, iord.getC2descripcion());
            pst.setInt(2, iord.getC3numerico_decimal());
            pst.setDouble(3, iord.getC4valor_numerico());
            pst.setString(4, iord.getC5valor_testo());
            pst.setInt(5, iord.getC6valor_predefinido());
            pst.setString(6, iord.getC7valor_de_referencia());
            pst.setString(7, iord.getC8unidad());
            pst.setBoolean(8, iord.getC9es_numerico());
            pst.setBoolean(9, iord.getC10es_testo());
            pst.setBoolean(10, iord.getC11es_predefinido());
            pst.setString(11, iord.getC12estado_estudio());
            pst.setString(12, iord.getC13nota());
            pst.setBoolean(13, iord.getC14cobertura());
            pst.setInt(14, iord.getC15fk_idorden_lab());
            pst.setInt(15, iord.getC16fk_idlab_estudio());
            pst.setInt(16,iord.getC17sku());
            pst.setBoolean(17, iord.isC18se_carga());
            pst.setInt(18, iord.getC1iditem_orden_lab());
            
            pst.execute();
            pst.close();
            evemen.Imprimir_serial_sql(sql_update + "\n" + iord.toString(), titulo);
            evemen.modificado_correcto(mensaje_update, true);
        } catch (Exception e) {
            evemen.mensaje_error(e, sql_update + "\n" + iord.toString(), titulo);
        }
    }
   public void update_item_orden_lab_cargado_numerico(Connection conn, item_orden_lab iord) {
        String titulo = "update_item_orden_lab_cargado_numerico";
        PreparedStatement pst = null;
        try {
            pst = conn.prepareStatement(sql_update_carga_numerico);
            pst.setDouble(1, iord.getC4valor_numerico());
            pst.setString(2, iord.getC12estado_estudio());
            pst.setString(3, iord.getC13nota());
            pst.setInt(4, iord.getC1iditem_orden_lab());
            pst.execute();
            pst.close();
            evemen.Imprimir_serial_sql(sql_update_carga_numerico + "\n" + iord.toString(), titulo);
            evemen.modificado_correcto(mensaje_update, true);
        } catch (Exception e) {
            evemen.mensaje_error(e, sql_update_carga_numerico + "\n" + iord.toString(), titulo);
        }
    }
   public void update_item_orden_lab_cargado_testo(Connection conn, item_orden_lab iord) {
        String titulo = "update_item_orden_lab_cargado_testo";
        PreparedStatement pst = null;
        try {
            pst = conn.prepareStatement(sql_update_carga_testo);
            pst.setString(1, iord.getC5valor_testo());
            pst.setString(2, iord.getC12estado_estudio());
            pst.setString(3, iord.getC13nota());
            pst.setInt(4, iord.getC1iditem_orden_lab());
            pst.execute();
            pst.close();
            evemen.Imprimir_serial_sql(sql_update_carga_testo + "\n" + iord.toString(), titulo);
            evemen.modificado_correcto(mensaje_update, true);
        } catch (Exception e) {
            evemen.mensaje_error(e, sql_update_carga_testo + "\n" + iord.toString(), titulo);
        }
    }
   public void update_item_orden_lab_cargado_predefino(Connection conn, item_orden_lab iord) {
        String titulo = "update_item_orden_lab_cargado_predefino";
        PreparedStatement pst = null;
        try {
            pst = conn.prepareStatement(sql_update_carga_predefinido);
            pst.setInt(1, iord.getC6valor_predefinido());
            pst.setString(2, iord.getC12estado_estudio());
            pst.setString(3, iord.getC13nota());
            pst.setInt(4, iord.getC1iditem_orden_lab());
            pst.execute();
            pst.close();
            evemen.Imprimir_serial_sql(sql_update_carga_predefinido + "\n" + iord.toString(), titulo);
            evemen.modificado_correcto(mensaje_update, true);
        } catch (Exception e) {
            evemen.mensaje_error(e, sql_update_carga_predefinido + "\n" + iord.toString(), titulo);
        }
    }
    public void cargar_item_orden_lab(Connection conn, item_orden_lab iord, int id) {
        String titulo = "Cargar_item_orden_lab";
        try {
            ResultSet rs = eveconn.getResulsetSQL(conn, sql_cargar + id, titulo);
            if (rs.next()) {
                iord.setC1iditem_orden_lab(rs.getInt(1));
                iord.setC2descripcion(rs.getString(2));
                iord.setC3numerico_decimal(rs.getInt(3));
                iord.setC4valor_numerico(rs.getDouble(4));
                iord.setC5valor_testo(rs.getString(5));
                iord.setC6valor_predefinido(rs.getInt(6));
                iord.setC7valor_de_referencia(rs.getString(7));
                iord.setC8unidad(rs.getString(8));
                iord.setC9es_numerico(rs.getBoolean(9));
                iord.setC10es_testo(rs.getBoolean(10));
                iord.setC11es_predefinido(rs.getBoolean(11));
                iord.setC12estado_estudio(rs.getString(12));
                iord.setC13nota(rs.getString(13));
                iord.setC14cobertura(rs.getBoolean(14));
                iord.setC15fk_idorden_lab(rs.getInt(15));
                iord.setC16fk_idlab_estudio(rs.getInt(16));
                iord.setC17sku(rs.getInt(17));
                iord.setC18se_carga(rs.getBoolean(18));
                evemen.Imprimir_serial_sql(sql_cargar + "\n" + iord.toString(), titulo);
            }
        } catch (Exception e) {
            evemen.mensaje_error(e, sql_cargar + "\n" + iord.toString(), titulo);
        }
    }

    public void actualizar_tabla_item_orden_lab(Connection conn, JTable tbltabla) {
        eveconn.Select_cargar_jtable(conn, sql_select, tbltabla);
        ancho_tabla_item_orden_lab(tbltabla);
    }

    public void ancho_tabla_item_orden_lab(JTable tbltabla) {
        int Ancho[] = {6, 6, 6, 6, 6, 6, 6, 6, 6, 6, 6, 6, 6, 6, 6, 6};
        evejt.setAnchoColumnaJtable(tbltabla, Ancho);
    }

    public void actualizar_tabla_item_orden_lab_pedido(Connection conn, JTable tbltabla, int idorden_lab) {
        String sql = "select iol.iditem_orden_lab,\n"
                + "case when (select lge.orden as orden from public.item_lab_estudio ile1,public.lab_grupo_estudio lge\n"
                + "where ile1.fk_idlab_grupo_estudio=lge.idlab_grupo_estudio\n"
                + "and lge.fk_idlab_grupo="+tbl_or.getIdtabla_seccion()
                + " and ile1.fk_idlab_estudio=iol.fk_idlab_estudio) is not null \n"
                + "then (select lge.orden as orden from public.item_lab_estudio ile1,public.lab_grupo_estudio lge\n"
                + "where ile1.fk_idlab_grupo_estudio=lge.idlab_grupo_estudio\n"
                + "and lge.fk_idlab_grupo="+tbl_or.getIdtabla_seccion()
                + " and ile1.fk_idlab_estudio=iol.fk_idlab_estudio)\n"
                    + "else 99 end as os,\n"
                + "case when (select lge.nombre as nombre from public.item_lab_estudio ile1,public.lab_grupo_estudio lge\n"
                + "where ile1.fk_idlab_grupo_estudio=lge.idlab_grupo_estudio\n"
                + "and lge.fk_idlab_grupo="+tbl_or.getIdtabla_seccion()
                + " and ile1.fk_idlab_estudio=iol.fk_idlab_estudio) is not null \n"
                + "then (select lge.nombre as gru_valor from public.item_lab_estudio ile1,public.lab_grupo_estudio lge\n"
                + "where ile1.fk_idlab_grupo_estudio=lge.idlab_grupo_estudio\n"
                + "and lge.fk_idlab_grupo="+tbl_or.getIdtabla_seccion()
                + " and ile1.fk_idlab_estudio=iol.fk_idlab_estudio)\n"
                    + "else '----------' end as seccion,\n"
                + "case when (select lge.orden as orden from public.item_lab_estudio ile1,public.lab_grupo_estudio lge\n"
                + "where ile1.fk_idlab_grupo_estudio=lge.idlab_grupo_estudio\n"
                + "and lge.fk_idlab_grupo="+tbl_or.getIdtabla_grupo_valor()
                + " and ile1.fk_idlab_estudio=iol.fk_idlab_estudio) is not null \n"
                + "then (select lge.orden as orden from public.item_lab_estudio ile1,public.lab_grupo_estudio lge\n"
                + "where ile1.fk_idlab_grupo_estudio=lge.idlab_grupo_estudio\n"
                + "and lge.fk_idlab_grupo="+tbl_or.getIdtabla_grupo_valor()
                + " and ile1.fk_idlab_estudio=iol.fk_idlab_estudio)\n"
                    + "else 99 end as og,\n"
                + "case when (select lge.nombre as nombre from public.item_lab_estudio ile1,public.lab_grupo_estudio lge\n"
                + "where ile1.fk_idlab_grupo_estudio=lge.idlab_grupo_estudio\n"
                + "and lge.fk_idlab_grupo="+tbl_or.getIdtabla_grupo_valor()
                + " and ile1.fk_idlab_estudio=iol.fk_idlab_estudio) is not null \n"
                + "then (select lge.nombre as gru_valor from public.item_lab_estudio ile1,public.lab_grupo_estudio lge\n"
                + "where ile1.fk_idlab_grupo_estudio=lge.idlab_grupo_estudio\n"
                + "and lge.fk_idlab_grupo="+tbl_or.getIdtabla_grupo_valor()
                + " and ile1.fk_idlab_estudio=iol.fk_idlab_estudio)\n"
                    + "else '----------' end as gru_valor,\n"
                + "case when (select ile1.orden as nombre from public.item_lab_estudio ile1,public.lab_grupo_estudio lge\n"
                + "where ile1.fk_idlab_grupo_estudio=lge.idlab_grupo_estudio\n"
                + "and lge.fk_idlab_grupo="+tbl_or.getIdtabla_grupo_valor()
                + " and ile1.fk_idlab_estudio=iol.fk_idlab_estudio) is not null \n"
                + "then (select ile1.orden as gru_valor from public.item_lab_estudio ile1,public.lab_grupo_estudio lge\n"
                + "where ile1.fk_idlab_grupo_estudio=lge.idlab_grupo_estudio\n"
                + "and lge.fk_idlab_grupo="+tbl_or.getIdtabla_grupo_valor()
                + " and ile1.fk_idlab_estudio=iol.fk_idlab_estudio)\n"
                + "else 0 end as ord_gv,\n"
                + "iol.fk_idlab_estudio as idle,iol.descripcion,\n"
                + "case when (iol.es_numerico=true and iol.estado_estudio='"+tbl_or.getEstado_pedido()+"') then null\n"
                + "when (iol.es_testo=true and iol.estado_estudio='"+tbl_or.getEstado_pedido()+"') then null\n"
                + "when (iol.es_predefinido=true and iol.estado_estudio='"+tbl_or.getEstado_pedido()+"') then null\n"
                + "when (iol.es_numerico=true and iol.estado_estudio='"+tbl_or.getEstado_cargado()+"' and iol.numerico_decimal=0 and iol.valor_numerico>=0) then (to_char(iol.valor_numerico,'999G999G999'))\n"
                + "when (iol.es_numerico=true and iol.estado_estudio='"+tbl_or.getEstado_cargado()+"' and iol.numerico_decimal=1 and iol.valor_numerico>=1) then (to_char(iol.valor_numerico,'999G999G999D9'))\n"
                + "when (iol.es_numerico=true and iol.estado_estudio='"+tbl_or.getEstado_cargado()+"' and iol.numerico_decimal=2 and iol.valor_numerico>=1) then (to_char(iol.valor_numerico,'999G999G999D99'))\n"
                    + "when (iol.es_numerico=true and iol.estado_estudio='"+tbl_or.getEstado_cargado()+"' and iol.numerico_decimal=1) then (to_char(iol.valor_numerico,'0.9'))\n"
                    + "when (iol.es_numerico=true and iol.estado_estudio='"+tbl_or.getEstado_cargado()+"' and iol.numerico_decimal=2) then (to_char(iol.valor_numerico,'0.99'))\n"
                + "when (iol.es_testo=true and iol.estado_estudio='"+tbl_or.getEstado_cargado()+"') then (iol.valor_testo)\n"
                + "when (iol.es_predefinido=true and iol.estado_estudio='"+tbl_or.getEstado_cargado()+"') then "
                + "(select lep.nombre from public.lab_estudio_predefinido lep where lep.idlab_estudio_predefinido=iol.valor_predefinido)\n"
                + "else 'error' end as tipodato,iol.estado_estudio as estado,\n"
                + "case when iol.cobertura=true then 'SI'\n"
                + "when iol.cobertura=false then 'NO'\n"
                + "else 'otro' end as cober\n"
                + "from public.item_orden_lab iol\n"
                + "where iol.fk_idorden_lab="+idorden_lab+"\n "
                + "and iol.se_carga=true \n"
                + " order by 2 asc,4 asc,6 asc;\n";
        eveconn.Select_cargar_jtable(conn, sql, tbltabla);
        ancho_tabla_item_orden_lab_pedido(tbltabla);
    }

    public void ancho_tabla_item_orden_lab_pedido(JTable tbltabla) {
        int Ancho[] = {4,4,18,4,18,4,4,20,10,8,5};
        evejt.setAnchoColumnaJtable(tbltabla, Ancho);
    }
}
