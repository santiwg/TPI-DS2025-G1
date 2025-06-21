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
    private JTextField alcancetxt;
    private JTextField clasificaciontxt;
    private JButton seleccionarButton;
    private JLabel alcanceLabel;
    private JLabel clasificacionLabel;
    private JLabel origenDeGeneracionLabel;
    private JButton rechazarButton;
    private JButton confirmarButton;
    private JButton delegarAExpertoButton;
    private JButton verMapaButton;
    private JButton modificarDatosButton;
    private JLabel seleccioneResultadoLabel;
    private JPanel panelPrincipal;

    public PantRegRRes(GestorRegRRes gestor) {
        this.gestor = gestor;
        setContentPane(panelPrincipal); // Esta linea va si o si, sino no podemos trabajar con el panel armado
        setTitle("Registrar Resultado de Revisión"); // Configura el título de la ventana
        setSize(1000,600); // Configura el tamaño de la ventana
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE); // Define el comportamiento de cierre (lo que hace cuando se toca la cruz)
        setLocationRelativeTo(null); // Indica respecto a que se centre, al poner null es respecto al centro.
        setExtendedState(JFrame.NORMAL);
        setVisible(true); // Esto es lo más importante, sin esto no va a abrir la ventana

// Ocultar componentes acá

        eventosComboBox.setVisible(false);
        origentxt.setVisible(false);
        alcancetxt.setVisible(false);
        clasificaciontxt.setVisible(false);
        seleccionarButton.setVisible(false);
        alcanceLabel.setVisible(false);
        clasificacionLabel.setVisible(false);
        origenDeGeneracionLabel.setVisible(false);
        rechazarButton.setVisible(false);
        confirmarButton.setVisible(false);
        delegarAExpertoButton.setVisible(false);
        verMapaButton.setVisible(false);
        modificarDatosButton.setVisible(false);
        seleccioneResultadoLabel.setVisible(false);


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
            }
        });

        rechazarButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                tomarSeleccionResultado("Rechazado");
            }
        });

        confirmarButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                tomarSeleccionResultado("Confirmado");
            }
        });

        delegarAExpertoButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                tomarSeleccionResultado("Derivado a Experto");
            }
        });

        modificarDatosButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                tomarSolicitudModificacionDatos();
            }
        });

        verMapaButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                tomarSolicitudVerMapa();
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
        alcancetxt.setVisible(true);
        clasificaciontxt.setVisible(true);
        seleccionarButton.setVisible(true);
        alcanceLabel.setVisible(true);
        clasificacionLabel.setVisible(true);
        origenDeGeneracionLabel.setVisible(true);
    }

    public void mostrarESParaSeleccion(ArrayList<String> eventosSismicos){

        DefaultComboBoxModel <String> eventos = new DefaultComboBoxModel<>();
        for (String evento: eventosSismicos) {
            eventos.addElement(evento);
        }
        eventosComboBox.setModel(eventos);
    }

    public void tomarSeleccionES(String evento){
        gestor.tomarSeleccionES(evento);
    }

    public void mostrarDatosEventoSismico(String nombreAlcance, String nombreOrigenGeneracion, String nombreClasificacion){
        this.alcancetxt.setText(nombreAlcance);
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

    public void tomarCancelacion(){
        dispose();
        gestor.cancelarCU();
    }

    public void informarNoHayESNoRevisados(){
        JOptionPane.showMessageDialog(this, "No hay sismos auto detectados que aún no han sido revisados");
        this.dispose();
    }

    public void tomarSeleccionResultado(String seleccion){
        JOptionPane.showMessageDialog(this,"Resultado Registrado!");
        dispose();
        gestor.tomarSeleccionResultado(seleccion);
    }

    public void tomarSolicitudModificacionDatos(){
        JOptionPane.showMessageDialog(this,"Cargando...");
    }

    public void tomarSolicitudVerMapa(){
        JOptionPane.showMessageDialog(this,"Cargando mapa...");
    }

    private void createUIComponents() {
        // TODO: place custom component creation code here
    }
}
