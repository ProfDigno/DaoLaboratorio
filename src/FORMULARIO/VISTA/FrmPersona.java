/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package FORMULARIO.VISTA;

import BASEDATO.EvenConexion;
import BASEDATO.LOCAL.ConnPostgres;
import Evento.Color.cla_color_pelete;
import Evento.Fecha.EvenFecha;
import Evento.JTextField.EvenJTextField;
import Evento.Jframe.EvenJFRAME;
import Evento.Jtable.EvenJtable;
import FORMULARIO.BO.*;
import FORMULARIO.DAO.*;
import FORMULARIO.ENTIDAD.*;
import java.awt.event.KeyEvent;
import java.sql.Connection;
import javax.swing.JTextField;

/**
 *
 * @author Digno
 */
public class FrmPersona extends javax.swing.JInternalFrame {
    EvenConexion eveconn = new EvenConexion();
    EvenJFRAME evetbl = new EvenJFRAME();
    EvenJtable eveJtab = new EvenJtable();
    private persona pers = new persona();
    private BO_persona persBO = new BO_persona();
    private DAO_persona persdao = new DAO_persona();
     private direc_barrio dbar = new direc_barrio();
    private DAO_direc_barrio dbardao = new DAO_direc_barrio();
    private direc_ciudad dciu = new direc_ciudad();
    private DAO_direc_ciudad dciudao = new DAO_direc_ciudad();
    private tipo_seguro tseg = new tipo_seguro();
    private DAO_tipo_seguro tsegdao = new DAO_tipo_seguro();
    private plan_seguro pseg = new plan_seguro();
    private DAO_plan_seguro psegdao = new DAO_plan_seguro();
    EvenJTextField evejtf = new EvenJTextField();
    Connection conn = ConnPostgres.getConnPosgres();
    cla_color_pelete clacolor= new cla_color_pelete();
    EvenFecha evefec = new EvenFecha();
    private int fk_idtipo_seguro;
    private int fk_idplan_seguro;
    private int fk_iddirec_barrio;
    private int fk_iddirec_ciudad;
    private boolean buscar_idciudad;
    private boolean buscar_idbarrio;
    private boolean buscar_idseguro;
    private boolean buscar_idplan_seguro;
    private void abrir_formulario() {
        this.setTitle("PERSONA");
        evetbl.centrar_formulario_internalframa(this);        
        reestableser();
        persdao.actualizar_tabla_persona(conn, tbllab);
        color_formulario();
    }
    private void color_formulario(){
        panel_tabla.setBackground(clacolor.getColor_tabla());
        panel_insertar.setBackground(clacolor.getColor_insertar_primario());
    }
    private boolean validar_guardar() {
        if (evejtf.getBoo_JTextField_vacio(txtnombre, "DEBE CARGAR UN NOMBRE")) {
            return false;
        }
        if (evejtf.getBoo_JTextField_vacio(txtapellido, "DEBE CARGAR UN APELLIDO")) {
            return false;
        }
        if (evejtf.getBoo_JTextField_vacio(txtcedula, "DEBE CARGAR UN CEDULA")) {
            return false;
        }
        if (evejtf.getBoo_JTextField_vacio(txttelefono, "DEBE CARGAR UN TELEFONO")) {
            return false;
        }
        if (evejtf.getBoo_JTextField_vacio(txtdireccion, "DEBE CARGAR UN DIRECCION")) {
            return false;
        }
        if (evejtf.getBoo_JTextField_vacio(txtfec_nac, "DEBE CARGAR UN FEC NACIMIENTO")) {
            return false;
        }
        if (evejtf.getBoo_JTextField_vacio(txtnro_tarjeta, "DEBE CARGAR UN NRO TARJETA")) {
            return false;
        }
        if (evejtf.getBoo_JTextField_vacio(txtregistro, "DEBE CARGAR UN REGISTRO")) {
            return false;
        }
        if (evejtf.getBoo_JTextField_vacio(txtidciudad, "DEBE CARGAR UNA CIUDAD")) {
            return false;
        }
        if (evejtf.getBoo_JTextField_vacio(txtidbarrio, "DEBE CARGAR UN BARRIO")) {
            return false;
        }
        if (evejtf.getBoo_JTextField_vacio(txtidseguro, "DEBE CARGAR UN SEGURO")) {
            return false;
        }
        if (evejtf.getBoo_JTextField_vacio(txtidplan_seguro, "DEBE CARGAR UN PLAN SEGURO")) {
            return false;
        }
        return true;
    }

    private void boton_guardar() {
        if (validar_guardar()) {
            pers.setC2nombre(txtnombre.getText());
            pers.setC3apellido(txtapellido.getText());
            pers.setC4cedula(txtcedula.getText());
            pers.setC5telefono(txttelefono.getText());
            pers.setC6direccion(txtdireccion.getText());
            pers.setC7fec_nac(txtfec_nac.getText());
            pers.setC8genero(getgenero());
            pers.setC9nro_tarjeta(txtnro_tarjeta.getText());
            pers.setC10registro(Integer.parseInt(txtregistro.getText()));
            pers.setC11tipo_persona(gettipo_persona());
            pers.setC12fk_idtipo_seguro(fk_idtipo_seguro);
            pers.setC13fk_idplan_seguro(fk_idplan_seguro);
            pers.setC14fk_iddirec_barrio(fk_iddirec_barrio);
            pers.setC15fk_iddirec_ciudad(fk_iddirec_ciudad);
            persBO.insertar_persona(pers, tbllab);
            reestableser();
        }
    }
    private String getgenero(){
        String genero="";
        if(jRmasculino.isSelected()){
            genero="M";
        }
        if(jRfemenino.isSelected()){
            genero="F";
        }
        return genero;
    }
    private String gettipo_persona(){
        String tipo="";
        if(jRpaciente.isSelected()){
            tipo=pers.getSta_tipo_pers_paciente();
        }
        if(jRmedico.isSelected()){
            tipo=pers.getSta_tipo_pers_medico();
        }
        if(jRresponsable.isSelected()){
            tipo=pers.getSta_tipo_pers_represent();
        }
        return tipo;
    }
    private void boton_editar() {
        if (validar_guardar()) {
            pers.setC1idpersona(Integer.parseInt(txtid.getText()));
            pers.setC2nombre(txtnombre.getText());
            pers.setC3apellido(txtapellido.getText());
            pers.setC4cedula(txtcedula.getText());
            pers.setC5telefono(txttelefono.getText());
            pers.setC6direccion(txtdireccion.getText());
            pers.setC7fec_nac(txtfec_nac.getText());
            pers.setC8genero(getgenero());
            pers.setC9nro_tarjeta(txtnro_tarjeta.getText());
            pers.setC10registro(Integer.parseInt(txtregistro.getText()));
            pers.setC11tipo_persona(gettipo_persona());
            pers.setC12fk_idtipo_seguro(fk_idtipo_seguro);
            pers.setC13fk_idplan_seguro(fk_idplan_seguro);
            pers.setC14fk_iddirec_barrio(fk_iddirec_barrio);
            pers.setC15fk_iddirec_ciudad(fk_iddirec_ciudad);
            persBO.update_persona(pers, tbllab);
        }
    }

    private void seleccionar_tabla() {
        int idproducto = eveJtab.getInt_select_id(tbllab);
        persdao.cargar_persona(conn,pers, idproducto);
        txtid.setText(String.valueOf(pers.getC1idpersona()));
        txtnombre.setText(pers.getC2nombre());
        txtapellido.setText(pers.getC3apellido());
        txtcedula.setText(pers.getC4cedula());
        txttelefono.setText(pers.getC5telefono());
        txtdireccion.setText(pers.getC6direccion());
        txtfec_nac.setText(pers.getC7fec_nac());
        if(pers.getC8genero().equals("M")){
            jRmasculino.setSelected(true);
        }
        if(pers.getC8genero().equals("F")){
            jRfemenino.setSelected(true);
        }
        txtnro_tarjeta.setText(String.valueOf(pers.getC9nro_tarjeta()));
        txtregistro.setText(String.valueOf(pers.getC10registro()));
        if(pers.getC11tipo_persona().equals(pers.getSta_tipo_pers_paciente())){
            jRpaciente.setSelected(true);
        }
        if(pers.getC11tipo_persona().equals(pers.getSta_tipo_pers_medico())){
            jRmedico.setSelected(true);
        }
        if(pers.getC11tipo_persona().equals(pers.getSta_tipo_pers_represent())){
            jRresponsable.setSelected(true);
        }
        fk_iddirec_ciudad=pers.getC15fk_iddirec_ciudad();
        fk_iddirec_barrio=pers.getC14fk_iddirec_barrio();
        fk_idplan_seguro=pers.getC13fk_idplan_seguro();
        fk_idtipo_seguro=pers.getC12fk_idtipo_seguro();
        dbardao.cargar_direc_barrio(conn, dbar,fk_iddirec_barrio);
        txtidbarrio.setText(dbar.getC1iddirec_barrio()+"-"+dbar.getC2nombre());
        tsegdao.cargar_tipo_seguro(conn, tseg,fk_idtipo_seguro);
        txtidseguro.setText(tseg.getC1idtipo_seguro()+"-"+tseg.getC2nombre());
        psegdao.cargar_plan_seguro(conn, pseg,fk_idplan_seguro);
        txtidplan_seguro.setText(pseg.getC1idplan_seguro()+"-"+pseg.getC2nombre());
        dciudao.cargar_direc_ciudad(conn, dciu,fk_iddirec_ciudad);
        txtidciudad.setText(dciu.getC1iddirec_ciudad()+"-"+dciu.getC2nombre());
        btnguardar.setEnabled(false);
        btneditar.setEnabled(true);
    }
    private void reestableser(){
        txtid.setText(null);
        txtnombre.setText(null);
        txtapellido.setText(null);
        txtcedula.setText(null);
        txttelefono.setText(null);
        txtdireccion.setText(null);
        txtfec_nac.setText(null);
        txtnro_tarjeta.setText(null);
        txtregistro.setText("0");
        txtidciudad.setText(null);
        txtidbarrio.setText(null);
        txtidseguro.setText(null);
        txtidplan_seguro.setText(null);
        jRfemenino.setSelected(true);
        jRpaciente.setSelected(true);
        btnguardar.setEnabled(true);
        btneditar.setEnabled(false);
        btndeletar.setEnabled(false);
        txtnombre.grabFocus();
    }
    private int cargar_idtabla(JTextField txttabla,String tabla,String idtabla) {
        int id = eveconn.getInt_seleccionar_JLista(conn, txttabla, jLista_aux, false,
                tabla,
                "concat("+idtabla+",'-',nombre)",
                "("+idtabla+"||'-'||nombre) as nom_tabla", "nom_tabla", idtabla);
        return id;
    }
    private void cargar_idciudad() {
        fk_iddirec_ciudad = cargar_idtabla(txtidciudad,"public.direc_ciudad","iddirec_ciudad");
        buscar_idciudad = false;
        evejtf.saltar_campo_directo(txtidciudad, txtidbarrio);
    }
    private void cargar_idbarrio() {
        fk_iddirec_barrio = cargar_idtabla(txtidbarrio,"public.direc_barrio","iddirec_barrio");
        buscar_idbarrio = false;
        evejtf.saltar_campo_directo(txtidbarrio, txtidseguro);
    }
    private void cargar_idseguro() {
        fk_idtipo_seguro = cargar_idtabla(txtidseguro,"public.tipo_seguro","idtipo_seguro");
        buscar_idseguro = false;
        evejtf.saltar_campo_directo(txtidseguro, txtidplan_seguro);
    }
    private void cargar_idplan_seguro() {
        fk_idplan_seguro = cargar_idtabla(txtidplan_seguro,"public.plan_seguro","idplan_seguro");
        buscar_idplan_seguro = false;
        evejtf.saltar_campo_directo(txtidplan_seguro, txtnombre);
    }
    private void buscar_lista(JTextField txttabla,String tabla,String idtabla){
        eveconn.buscar_cargar_Jlista(conn, txttabla, jLista_aux, 
                tabla,"concat("+idtabla+",'-',nombre)",
                "("+idtabla+"||'-'||nombre) as nom_tabla", 5);
    }
    private void boton_nuevo(){
        reestableser();
    }
    public FrmPersona() {
        initComponents();
        abrir_formulario();
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        gru_gen = new javax.swing.ButtonGroup();
        gru_pers = new javax.swing.ButtonGroup();
        jTabbedPane1 = new javax.swing.JTabbedPane();
        panel_insertar = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        txtid = new javax.swing.JTextField();
        jLabel2 = new javax.swing.JLabel();
        txtnombre = new javax.swing.JTextField();
        btnnuevo = new javax.swing.JButton();
        btnguardar = new javax.swing.JButton();
        btneditar = new javax.swing.JButton();
        btndeletar = new javax.swing.JButton();
        jLabel3 = new javax.swing.JLabel();
        txtapellido = new javax.swing.JTextField();
        jLabel4 = new javax.swing.JLabel();
        txtcedula = new javax.swing.JTextField();
        jLabel5 = new javax.swing.JLabel();
        txttelefono = new javax.swing.JTextField();
        jLabel6 = new javax.swing.JLabel();
        txtdireccion = new javax.swing.JTextField();
        jLabel7 = new javax.swing.JLabel();
        txtfec_nac = new javax.swing.JTextField();
        jLabel8 = new javax.swing.JLabel();
        txtnro_tarjeta = new javax.swing.JTextField();
        jLabel9 = new javax.swing.JLabel();
        txtregistro = new javax.swing.JTextField();
        jPanel1 = new javax.swing.JPanel();
        jRmasculino = new javax.swing.JRadioButton();
        jRfemenino = new javax.swing.JRadioButton();
        jPanel2 = new javax.swing.JPanel();
        jRpaciente = new javax.swing.JRadioButton();
        jRmedico = new javax.swing.JRadioButton();
        jRresponsable = new javax.swing.JRadioButton();
        jLabel10 = new javax.swing.JLabel();
        jPanel3 = new javax.swing.JPanel();
        jLabel11 = new javax.swing.JLabel();
        txtidciudad = new javax.swing.JTextField();
        jLabel12 = new javax.swing.JLabel();
        txtidbarrio = new javax.swing.JTextField();
        jLabel13 = new javax.swing.JLabel();
        txtidseguro = new javax.swing.JTextField();
        jLabel14 = new javax.swing.JLabel();
        txtidplan_seguro = new javax.swing.JTextField();
        jSeparator1 = new javax.swing.JSeparator();
        jLista_aux = new javax.swing.JList<>();
        jLabel18 = new javax.swing.JLabel();
        panel_tabla = new javax.swing.JPanel();
        jScrollPane1 = new javax.swing.JScrollPane();
        tbllab = new javax.swing.JTable();
        jLabel15 = new javax.swing.JLabel();
        txtbuscar_nombre = new javax.swing.JTextField();
        txtbuscar_apellido = new javax.swing.JTextField();
        jLabel16 = new javax.swing.JLabel();
        txtbuscar_cedula = new javax.swing.JTextField();
        jLabel17 = new javax.swing.JLabel();

        setClosable(true);
        setIconifiable(true);
        addInternalFrameListener(new javax.swing.event.InternalFrameListener() {
            public void internalFrameActivated(javax.swing.event.InternalFrameEvent evt) {
            }
            public void internalFrameClosed(javax.swing.event.InternalFrameEvent evt) {
            }
            public void internalFrameClosing(javax.swing.event.InternalFrameEvent evt) {
            }
            public void internalFrameDeactivated(javax.swing.event.InternalFrameEvent evt) {
            }
            public void internalFrameDeiconified(javax.swing.event.InternalFrameEvent evt) {
            }
            public void internalFrameIconified(javax.swing.event.InternalFrameEvent evt) {
            }
            public void internalFrameOpened(javax.swing.event.InternalFrameEvent evt) {
                formInternalFrameOpened(evt);
            }
        });

        panel_insertar.setBackground(new java.awt.Color(153, 204, 255));
        panel_insertar.setBorder(javax.swing.BorderFactory.createTitledBorder("CREAR DATO"));

        jLabel1.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        jLabel1.setText("ID:");

        txtid.setEditable(false);
        txtid.setBackground(new java.awt.Color(204, 204, 204));
        txtid.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N

        jLabel2.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jLabel2.setText("NOMBRE:");

        txtnombre.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        txtnombre.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                txtnombreKeyPressed(evt);
            }
        });

        btnnuevo.setIcon(new javax.swing.ImageIcon(getClass().getResource("/iconos/ABM/nuevo.png"))); // NOI18N
        btnnuevo.setText("NUEVO");
        btnnuevo.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        btnnuevo.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        btnnuevo.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnnuevoActionPerformed(evt);
            }
        });

        btnguardar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/iconos/ABM/guardar.png"))); // NOI18N
        btnguardar.setText("GUARDAR");
        btnguardar.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        btnguardar.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        btnguardar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnguardarActionPerformed(evt);
            }
        });

        btneditar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/iconos/ABM/modificar.png"))); // NOI18N
        btneditar.setText("EDITAR");
        btneditar.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        btneditar.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        btneditar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btneditarActionPerformed(evt);
            }
        });

        btndeletar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/iconos/ABM/eliminar.png"))); // NOI18N
        btndeletar.setText("DELETAR");
        btndeletar.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        btndeletar.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);

        jLabel3.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jLabel3.setText("APELLIDO:");

        txtapellido.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        txtapellido.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                txtapellidoKeyPressed(evt);
            }
        });

        jLabel4.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jLabel4.setText("CEDULA:");

        txtcedula.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        txtcedula.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                txtcedulaKeyPressed(evt);
            }
        });

        jLabel5.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jLabel5.setText("TELEFONO:");

        txttelefono.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        txttelefono.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                txttelefonoKeyPressed(evt);
            }
        });

        jLabel6.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jLabel6.setText("DIRECCION:");

        txtdireccion.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        txtdireccion.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                txtdireccionKeyPressed(evt);
            }
        });

        jLabel7.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jLabel7.setText("FEC. NAC.:");

        txtfec_nac.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        txtfec_nac.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                txtfec_nacKeyPressed(evt);
            }
            public void keyReleased(java.awt.event.KeyEvent evt) {
                txtfec_nacKeyReleased(evt);
            }
        });

        jLabel8.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jLabel8.setText("Nro TARJETA:");

        txtnro_tarjeta.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        txtnro_tarjeta.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                txtnro_tarjetaKeyPressed(evt);
            }
        });

        jLabel9.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jLabel9.setText("REGISTRO:");

        txtregistro.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        txtregistro.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                txtregistroKeyPressed(evt);
            }
        });

        jPanel1.setBorder(javax.swing.BorderFactory.createTitledBorder("GENERO"));

        gru_gen.add(jRmasculino);
        jRmasculino.setSelected(true);
        jRmasculino.setText("MASCULINO");

        gru_gen.add(jRfemenino);
        jRfemenino.setText("FEMENINO");

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jRmasculino)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jRfemenino)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jRmasculino)
                    .addComponent(jRfemenino))
                .addGap(0, 5, Short.MAX_VALUE))
        );

        jPanel2.setBorder(javax.swing.BorderFactory.createTitledBorder("TIPO PERSONA"));

        gru_pers.add(jRpaciente);
        jRpaciente.setSelected(true);
        jRpaciente.setText("PACIENTE");

        gru_pers.add(jRmedico);
        jRmedico.setText("MEDICO");

        gru_pers.add(jRresponsable);
        jRresponsable.setText("RESPONSABLE");

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jRpaciente)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jRmedico)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jRresponsable)
                .addContainerGap(18, Short.MAX_VALUE))
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                .addComponent(jRpaciente)
                .addComponent(jRmedico, javax.swing.GroupLayout.PREFERRED_SIZE, 23, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addComponent(jRresponsable, javax.swing.GroupLayout.PREFERRED_SIZE, 23, javax.swing.GroupLayout.PREFERRED_SIZE))
        );

        jLabel10.setText("Solo Medico");

        jPanel3.setBorder(javax.swing.BorderFactory.createTitledBorder("DIRECCION / SEGURO"));

        jLabel11.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jLabel11.setText("CIUDAD:");

        txtidciudad.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        txtidciudad.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                txtidciudadKeyPressed(evt);
            }
            public void keyReleased(java.awt.event.KeyEvent evt) {
                txtidciudadKeyReleased(evt);
            }
        });

        jLabel12.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jLabel12.setText("BARRIO:");

        txtidbarrio.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        txtidbarrio.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                txtidbarrioKeyPressed(evt);
            }
            public void keyReleased(java.awt.event.KeyEvent evt) {
                txtidbarrioKeyReleased(evt);
            }
        });

        jLabel13.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jLabel13.setText("SEGURO:");

        txtidseguro.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        txtidseguro.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                txtidseguroKeyPressed(evt);
            }
            public void keyReleased(java.awt.event.KeyEvent evt) {
                txtidseguroKeyReleased(evt);
            }
        });

        jLabel14.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jLabel14.setText("PLAN MEDICO:");

        txtidplan_seguro.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        txtidplan_seguro.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                txtidplan_seguroKeyPressed(evt);
            }
            public void keyReleased(java.awt.event.KeyEvent evt) {
                txtidplan_seguroKeyReleased(evt);
            }
        });

        jLista_aux.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jLista_aux.setSelectionBackground(new java.awt.Color(255, 0, 0));
        jLista_aux.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                jLista_auxKeyPressed(evt);
            }
        });

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel12)
                    .addComponent(jLabel11))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(txtidbarrio)
                    .addComponent(txtidciudad))
                .addGap(10, 10, 10))
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel14)
                    .addComponent(jLabel13))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(txtidseguro)
                    .addComponent(txtidplan_seguro, javax.swing.GroupLayout.DEFAULT_SIZE, 319, Short.MAX_VALUE))
                .addContainerGap())
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addComponent(jSeparator1)
                .addContainerGap())
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel3Layout.createSequentialGroup()
                .addComponent(jLista_aux, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel11)
                    .addComponent(txtidciudad, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel12)
                    .addComponent(txtidbarrio, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(10, 10, 10)
                .addComponent(jSeparator1, javax.swing.GroupLayout.PREFERRED_SIZE, 10, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel13)
                    .addComponent(txtidseguro, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel14)
                    .addComponent(txtidplan_seguro, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLista_aux, javax.swing.GroupLayout.PREFERRED_SIZE, 194, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        jLabel18.setText("aaaa-mm-dd");

        javax.swing.GroupLayout panel_insertarLayout = new javax.swing.GroupLayout(panel_insertar);
        panel_insertar.setLayout(panel_insertarLayout);
        panel_insertarLayout.setHorizontalGroup(
            panel_insertarLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panel_insertarLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(panel_insertarLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(panel_insertarLayout.createSequentialGroup()
                        .addGroup(panel_insertarLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel1)
                            .addComponent(jLabel2)
                            .addComponent(jLabel3)
                            .addComponent(jLabel4)
                            .addComponent(jLabel5)
                            .addComponent(jLabel6)
                            .addComponent(jLabel7)
                            .addComponent(jLabel8)
                            .addComponent(jLabel9))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(panel_insertarLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(txtnro_tarjeta, javax.swing.GroupLayout.PREFERRED_SIZE, 314, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(txtdireccion, javax.swing.GroupLayout.PREFERRED_SIZE, 314, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGroup(panel_insertarLayout.createSequentialGroup()
                                .addComponent(txtregistro, javax.swing.GroupLayout.PREFERRED_SIZE, 134, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jLabel10))
                            .addComponent(txtapellido, javax.swing.GroupLayout.PREFERRED_SIZE, 314, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(txtnombre, javax.swing.GroupLayout.PREFERRED_SIZE, 314, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGroup(panel_insertarLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                                .addComponent(txttelefono, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, 143, Short.MAX_VALUE)
                                .addComponent(txtcedula, javax.swing.GroupLayout.Alignment.LEADING))
                            .addGroup(panel_insertarLayout.createSequentialGroup()
                                .addComponent(txtfec_nac, javax.swing.GroupLayout.PREFERRED_SIZE, 130, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jLabel18))
                            .addComponent(txtid, javax.swing.GroupLayout.PREFERRED_SIZE, 205, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addGroup(panel_insertarLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                        .addComponent(jPanel2, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(jPanel1, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addGroup(panel_insertarLayout.createSequentialGroup()
                        .addComponent(btnnuevo)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(btnguardar)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(btneditar)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(btndeletar)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanel3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        panel_insertarLayout.setVerticalGroup(
            panel_insertarLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panel_insertarLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(panel_insertarLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(panel_insertarLayout.createSequentialGroup()
                        .addGroup(panel_insertarLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel1)
                            .addComponent(txtid, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(panel_insertarLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel2)
                            .addComponent(txtnombre, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(panel_insertarLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel3)
                            .addComponent(txtapellido, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(panel_insertarLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel4)
                            .addComponent(txtcedula, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(panel_insertarLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel5)
                            .addComponent(txttelefono, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(panel_insertarLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel6)
                            .addComponent(txtdireccion, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(panel_insertarLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel7)
                            .addComponent(txtfec_nac, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel18))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(panel_insertarLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel8)
                            .addComponent(txtnro_tarjeta, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(panel_insertarLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel9)
                            .addComponent(txtregistro, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel10))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(panel_insertarLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(btnnuevo)
                            .addComponent(btnguardar)
                            .addComponent(btneditar)
                            .addComponent(btndeletar)))
                    .addComponent(jPanel3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(0, 118, Short.MAX_VALUE))
        );

        jTabbedPane1.addTab("DATOS PERSONA", panel_insertar);

        panel_tabla.setBackground(new java.awt.Color(51, 204, 255));
        panel_tabla.setBorder(javax.swing.BorderFactory.createTitledBorder("TABLA"));

        tbllab.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null}
            },
            new String [] {
                "Title 1", "Title 2", "Title 3", "Title 4"
            }
        ));
        tbllab.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseReleased(java.awt.event.MouseEvent evt) {
                tbllabMouseReleased(evt);
            }
        });
        jScrollPane1.setViewportView(tbllab);

        jLabel15.setText("NOMBRE:");

        txtbuscar_nombre.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                txtbuscar_nombreKeyReleased(evt);
            }
        });

        txtbuscar_apellido.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                txtbuscar_apellidoKeyReleased(evt);
            }
        });

        jLabel16.setText("APELLIDO:");

        txtbuscar_cedula.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                txtbuscar_cedulaKeyReleased(evt);
            }
        });

        jLabel17.setText("CEDULA:");

        javax.swing.GroupLayout panel_tablaLayout = new javax.swing.GroupLayout(panel_tabla);
        panel_tabla.setLayout(panel_tablaLayout);
        panel_tablaLayout.setHorizontalGroup(
            panel_tablaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 863, Short.MAX_VALUE)
            .addGroup(panel_tablaLayout.createSequentialGroup()
                .addComponent(jLabel15)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(txtbuscar_nombre, javax.swing.GroupLayout.PREFERRED_SIZE, 169, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel16)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(txtbuscar_apellido, javax.swing.GroupLayout.PREFERRED_SIZE, 169, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel17)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(txtbuscar_cedula, javax.swing.GroupLayout.PREFERRED_SIZE, 169, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, Short.MAX_VALUE))
        );
        panel_tablaLayout.setVerticalGroup(
            panel_tablaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panel_tablaLayout.createSequentialGroup()
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 474, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(panel_tablaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(panel_tablaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(jLabel17)
                        .addComponent(txtbuscar_cedula, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(panel_tablaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(jLabel16)
                        .addComponent(txtbuscar_apellido, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(panel_tablaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(jLabel15)
                        .addComponent(txtbuscar_nombre, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGap(0, 58, Short.MAX_VALUE))
        );

        jTabbedPane1.addTab("FILTRO PERSONA", panel_tabla);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jTabbedPane1)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(jTabbedPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 614, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void btnguardarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnguardarActionPerformed
        // TODO add your handling code here:
        boton_guardar();
    }//GEN-LAST:event_btnguardarActionPerformed

    private void formInternalFrameOpened(javax.swing.event.InternalFrameEvent evt) {//GEN-FIRST:event_formInternalFrameOpened
        // TODO add your handling code here:
        persdao.ancho_tabla_persona(tbllab);
    }//GEN-LAST:event_formInternalFrameOpened

    private void tbllabMouseReleased(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tbllabMouseReleased
        // TODO add your handling code here:
        seleccionar_tabla();
    }//GEN-LAST:event_tbllabMouseReleased

    private void btneditarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btneditarActionPerformed
        // TODO add your handling code here:
        boton_editar();
    }//GEN-LAST:event_btneditarActionPerformed

    private void btnnuevoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnnuevoActionPerformed
        // TODO add your handling code here:
        boton_nuevo();
    }//GEN-LAST:event_btnnuevoActionPerformed

    private void txtnombreKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtnombreKeyPressed
        // TODO add your handling code here:
//        evejtf.saltar_campo_enter(evt, txtnombre, txtprecio_venta);
    }//GEN-LAST:event_txtnombreKeyPressed

    private void txtapellidoKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtapellidoKeyPressed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtapellidoKeyPressed

    private void txtcedulaKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtcedulaKeyPressed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtcedulaKeyPressed

    private void txttelefonoKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txttelefonoKeyPressed
        // TODO add your handling code here:
    }//GEN-LAST:event_txttelefonoKeyPressed

    private void txtdireccionKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtdireccionKeyPressed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtdireccionKeyPressed

    private void txtfec_nacKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtfec_nacKeyPressed
        // TODO add your handling code here:
        if (evt.getKeyCode() == KeyEvent.VK_ENTER) {
            txtfec_nac.setText(evefec.getString_validar_fecha(txtfec_nac.getText()));
        }
    }//GEN-LAST:event_txtfec_nacKeyPressed

    private void txtnro_tarjetaKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtnro_tarjetaKeyPressed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtnro_tarjetaKeyPressed

    private void txtregistroKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtregistroKeyPressed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtregistroKeyPressed

    private void txtidciudadKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtidciudadKeyPressed
        // TODO add your handling code here:
        if (evejtf.seleccionar_lista(evt, jLista_aux)) {
            buscar_idciudad = true;
        }
    }//GEN-LAST:event_txtidciudadKeyPressed

    private void txtidbarrioKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtidbarrioKeyPressed
        // TODO add your handling code here:
        if (evejtf.seleccionar_lista(evt, jLista_aux)) {
            buscar_idbarrio = true;
        }
    }//GEN-LAST:event_txtidbarrioKeyPressed

    private void txtidseguroKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtidseguroKeyPressed
        // TODO add your handling code here:
        if (evejtf.seleccionar_lista(evt, jLista_aux)) {
            buscar_idseguro = true;
        }
    }//GEN-LAST:event_txtidseguroKeyPressed

    private void txtidplan_seguroKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtidplan_seguroKeyPressed
        // TODO add your handling code here:
        if (evejtf.seleccionar_lista(evt, jLista_aux)) {
            buscar_idplan_seguro = true;
        }
    }//GEN-LAST:event_txtidplan_seguroKeyPressed

    private void jLista_auxKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jLista_auxKeyPressed
        // TODO add your handling code here:
        if (evt.getKeyCode() == KeyEvent.VK_ENTER) {
            if (buscar_idciudad) {
                cargar_idciudad();
            }
            if (buscar_idbarrio) {
                cargar_idbarrio();
            }
            if (buscar_idseguro) {
                cargar_idseguro();
            }
            if (buscar_idplan_seguro) {
                cargar_idplan_seguro();
            }
        }
    }//GEN-LAST:event_jLista_auxKeyPressed

    private void txtidciudadKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtidciudadKeyReleased
        // TODO add your handling code here:
        buscar_lista(txtidciudad,"public.direc_ciudad", "iddirec_ciudad");
    }//GEN-LAST:event_txtidciudadKeyReleased

    private void txtidbarrioKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtidbarrioKeyReleased
        // TODO add your handling code here:
        buscar_lista(txtidbarrio,"public.direc_barrio", "iddirec_barrio");
    }//GEN-LAST:event_txtidbarrioKeyReleased

    private void txtidseguroKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtidseguroKeyReleased
        // TODO add your handling code here:
        buscar_lista(txtidseguro,"public.tipo_seguro", "idtipo_seguro");
    }//GEN-LAST:event_txtidseguroKeyReleased

    private void txtidplan_seguroKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtidplan_seguroKeyReleased
        // TODO add your handling code here:
        buscar_lista(txtidplan_seguro,"public.plan_seguro", "idplan_seguro");
    }//GEN-LAST:event_txtidplan_seguroKeyReleased

    private void txtfec_nacKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtfec_nacKeyReleased
        // TODO add your handling code here:
        evejtf.verificar_fecha(evt, txtfec_nac);
    }//GEN-LAST:event_txtfec_nacKeyReleased

    private void txtbuscar_nombreKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtbuscar_nombreKeyReleased
        // TODO add your handling code here:
        persdao.actualizar_buscar_persona(conn, tbllab, txtbuscar_nombre,"nombre");
    }//GEN-LAST:event_txtbuscar_nombreKeyReleased

    private void txtbuscar_apellidoKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtbuscar_apellidoKeyReleased
        // TODO add your handling code here:
         persdao.actualizar_buscar_persona(conn, tbllab, txtbuscar_apellido,"apellido");
    }//GEN-LAST:event_txtbuscar_apellidoKeyReleased

    private void txtbuscar_cedulaKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtbuscar_cedulaKeyReleased
        // TODO add your handling code here:
         persdao.actualizar_buscar_persona(conn, tbllab, txtbuscar_cedula,"cedula");
    }//GEN-LAST:event_txtbuscar_cedulaKeyReleased


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btndeletar;
    private javax.swing.JButton btneditar;
    private javax.swing.JButton btnguardar;
    private javax.swing.JButton btnnuevo;
    private javax.swing.ButtonGroup gru_gen;
    private javax.swing.ButtonGroup gru_pers;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel12;
    private javax.swing.JLabel jLabel13;
    private javax.swing.JLabel jLabel14;
    private javax.swing.JLabel jLabel15;
    private javax.swing.JLabel jLabel16;
    private javax.swing.JLabel jLabel17;
    private javax.swing.JLabel jLabel18;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JList<String> jLista_aux;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JRadioButton jRfemenino;
    private javax.swing.JRadioButton jRmasculino;
    private javax.swing.JRadioButton jRmedico;
    private javax.swing.JRadioButton jRpaciente;
    private javax.swing.JRadioButton jRresponsable;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JSeparator jSeparator1;
    private javax.swing.JTabbedPane jTabbedPane1;
    private javax.swing.JPanel panel_insertar;
    private javax.swing.JPanel panel_tabla;
    private javax.swing.JTable tbllab;
    private javax.swing.JTextField txtapellido;
    private javax.swing.JTextField txtbuscar_apellido;
    private javax.swing.JTextField txtbuscar_cedula;
    private javax.swing.JTextField txtbuscar_nombre;
    private javax.swing.JTextField txtcedula;
    private javax.swing.JTextField txtdireccion;
    private javax.swing.JTextField txtfec_nac;
    private javax.swing.JTextField txtid;
    private javax.swing.JTextField txtidbarrio;
    private javax.swing.JTextField txtidciudad;
    private javax.swing.JTextField txtidplan_seguro;
    private javax.swing.JTextField txtidseguro;
    private javax.swing.JTextField txtnombre;
    private javax.swing.JTextField txtnro_tarjeta;
    private javax.swing.JTextField txtregistro;
    private javax.swing.JTextField txttelefono;
    // End of variables declaration//GEN-END:variables
}
