/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package FORMULARIO.ENTIDAD;

/**
 *
 * @author Digno
 */
public class tabla_orden_lab {
     private int idtabla_departamento = 2;//departamento
    private int idtabla_area = 3;//area
    private int idtabla_seccion = 4;
    private int idtabla_grupo_valor = 5;
    private int idtabla_panel = 6;
    private int idtabla_muestra = 7;
    private int idtabla_metodo = 8;
    private String estado_pedido="PEDIDO";
    private String estado_cargado="CARGADO";
    private String estado_cancelado="CANCELADO";
    

    public int getIdtabla_departamento() {
        return idtabla_departamento;
    }

    public int getIdtabla_area() {
        return idtabla_area;
    }

    public int getIdtabla_seccion() {
        return idtabla_seccion;
    }

    public int getIdtabla_grupo_valor() {
        return idtabla_grupo_valor;
    }

    public int getIdtabla_panel() {
        return idtabla_panel;
    }

    public int getIdtabla_muestra() {
        return idtabla_muestra;
    }

    public int getIdtabla_metodo() {
        return idtabla_metodo;
    }

    /**
     * @return the estado_pedido
     */
    public String getEstado_pedido() {
        return estado_pedido;
    }

    /**
     * @return the estado_cargado
     */
    public String getEstado_cargado() {
        return estado_cargado;
    }

    /**
     * @return the estado_cancelado
     */
    public String getEstado_cancelado() {
        return estado_cancelado;
    }

    /**
     * @param estado_cancelado the estado_cancelado to set
     */
    public void setEstado_cancelado(String estado_cancelado) {
        this.estado_cancelado = estado_cancelado;
    }
    
}
