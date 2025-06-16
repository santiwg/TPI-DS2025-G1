package org.example.Pantallas;

import org.example.Gestores.GestorRegRRes;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

public class PantRegRRes extends JFrame {
    private GestorRegRRes gestor;
    private JButton opcRegRRevision;
    private JComboBox eventosComboBox;
    private JTextField origentxt;
    private JTextField Alcancetxt;
    private JTextField clasificaciontxt;
    private JButton seleccionarButton;
    private JLabel AlcanceLabel;
    private JLabel ClasificacionLabel;
    private JLabel OrigenDeGeneracionLabel;
    private JButton rechazarButton;
    private JButton confirmarButton;
    private JButton delegarAExpertoButton;
    private JButton verMapaButton;
    private JButton modificarDatosButton;
    private JLabel seleccioneResultadoLabel;
    private JPanel panelPrincipal;

    public PantRegRRes(GestorRegRRes gestor) {
        this.gestor = gestor;
        setContentPane(panelPrincipal); //este linea va si o si, sino no podemos trabajar con el panel armado
        setTitle("Registrar Resultado de Revisión");  //configurar el título de la ventana
        setSize(600,600); //configurar el tamaño de la ventana
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE); //define el comportamiento de cierre (lo que hace cuando se toca la cruz)
        setLocationRelativeTo(null); //indicamos respecto a que se centre, al poner null es respecto al centro.
        setVisible(true); //esto es lo más importante, sin esto no va a abrir la ventana

// Ocultar componentes acá

        eventosComboBox.setVisible(false);
        origentxt.setVisible(false);
        Alcancetxt.setVisible(false);
        clasificaciontxt.setVisible(false);
        seleccionarButton.setVisible(false);
        AlcanceLabel.setVisible(false);
        ClasificacionLabel.setVisible(false);
        OrigenDeGeneracionLabel.setVisible(false);
        rechazarButton.setVisible(false);
        confirmarButton.setVisible(false);
        delegarAExpertoButton.setVisible(false);
        verMapaButton.setVisible(false);
        modificarDatosButton.setVisible(false);
        seleccioneResultadoLabel.setVisible(false);

        // Agregar WindowListener para el cierre
        addWindowListener(new java.awt.event.WindowAdapter() {
            @Override
            public void windowClosing(java.awt.event.WindowEvent e) {
                tomarCancelacion();
            }});
        opcRegRRevision.addActionListener(e -> {

            opcRegResultadoES();
        });
        seleccionarButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String seleccionado = (String) eventosComboBox.getSelectedItem();
                if (seleccionado != null) {
                    tomarSeleccionES(seleccionado);
                } else {
                    JOptionPane.showMessageDialog(null, "Debe seleccionar un evento antes de continuar.");}
                //agregar control para errores, es decir por si no seleccionaron nada
            }
        });

        rechazarButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                tomarSeleccionRechazo();
            }
        });
        confirmarButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                tomarSeleccionConfirmacion();
            }
        });
    }

    public void opcRegResultadoES(){
        this.abrirPantalla();
        gestor.nuevaRevisionES(this);
    }
    public void abrirPantalla(){
        opcRegRRevision.setVisible(false);
        eventosComboBox.setVisible(true);
        origentxt.setVisible(true);
        Alcancetxt.setVisible(true);
        clasificaciontxt.setVisible(true);
        seleccionarButton.setVisible(true);
        AlcanceLabel.setVisible(true);
        ClasificacionLabel.setVisible(true);
        OrigenDeGeneracionLabel.setVisible(true);
    }
    public void mostrarESParaSeleccion(ArrayList<String> eventosSismicos){

        DefaultComboBoxModel <String> eventos = new DefaultComboBoxModel<>();
        for (String evento: eventosSismicos) {
            eventos.addElement(evento);
        }
        eventosComboBox.setModel(eventos);

        //Agregar lógica para que de error si no hay eventos
    }
    public void tomarSeleccionES(String evento){
        gestor.tomarSeleccionES(evento);
    }

    public void mostrarDatosEventoSismico(String nombreAlcance, String nombreOrigenGeneracion, String nombreClasificacion){
        this.Alcancetxt.setText(nombreAlcance);
        this.origentxt.setText(nombreOrigenGeneracion);
        this.clasificaciontxt.setText(nombreClasificacion);
    }

    public void habilitarOpcVerMapa(){
        this.verMapaButton.setVisible(true);
    }
    public void habilitarOpcModificarDatosES(){
        this.modificarDatosButton.setVisible(true);
    }
    public void pedirSeleccionResultadoEvento(){
        this.confirmarButton.setVisible(true);
        this.rechazarButton.setVisible(true);
        this.delegarAExpertoButton.setVisible(true);
        this.seleccioneResultadoLabel.setVisible(true);
    }
    public void tomarSeleccionRechazo(){
        gestor.tomarSeleccionRechazo();
    }
    public void tomarSeleccionConfirmacion(){
        gestor.tomarSeleccionConfirmacion();
    }
    public void tomarCancelacion(){
        dispose();
        gestor.cancelarCU();
    }
    public void informarNoHayESNoRevisados(){
        JOptionPane.showMessageDialog(this, "No hay sismos auto detectados que aún no han sido revisados");
        this.dispose();
    }
}
