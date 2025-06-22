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
        setContentPane(panelPrincipal); // Establece el panel principal como el contenido de la ventana
        setTitle("Registrar Resultado de Revisión"); // Asigna el título a la ventana
        setSize(1000,600); // Configura el tamaño de la ventana
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE); // Define el comportamiento de cierre
        setLocationRelativeTo(null); // Indica respecto a que se centre, al poner null es respecto al centro.
        setExtendedState(JFrame.NORMAL); // Define el estado extendido de la ventana
        setVisible(true); // Hace visible la ventana

        // Inicia ocultando estos componentes
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

        //Modifica el evento de cierre de la ventana
        addWindowListener(new java.awt.event.WindowAdapter() {
            @Override
            // Sobrescribimos el método windowClosing que se ejecuta cuando el usuario cierra una ventana
            public void windowClosing(java.awt.event.WindowEvent e) {
                tomarCancelacion(); // Al cerrar le ventana llama al método tomarCancelacion()
            }});
        // Al presionar el botón de Registrar Resultado Revisión, se llama al método opcRegResultadoES()
        opcRegRRevision.addActionListener(e -> {
            opcRegResultadoES();
        });

        // Agrega ActionListener para responder a eventos en el botón de seleccionar
        seleccionarButton.addActionListener(new ActionListener() {
            @Override
            // Método que se invoca al presionar el botón de selección
            public void actionPerformed(ActionEvent e) {
                String seleccionado = (String) eventosComboBox.getSelectedItem(); // Obtenemos el elemento seleccionado y lo convertimos a String
                // Verifica que se haya seleccionado un elemento
                if (seleccionado != null) {
                    // Si se selecciono un elemento, llama al método tomarSeleccionES() pasando como parámetro el evento seleccionado
                    tomarSeleccionES(seleccionado);
                } else {
                    // Si no se selecciono ningun evento, se muestra un mensaje
                    JOptionPane.showMessageDialog(null, "Debe seleccionar un evento antes de continuar.");}
            }
        });

        // Agrega ActionListener para responder a eventos en el botón de rechazar
        rechazarButton.addActionListener(new ActionListener() {
            @Override
            // Método que se ejecuta al presionar el boton de rechazar
            public void actionPerformed(ActionEvent e) {
                tomarSeleccionResultado("Rechazado"); // Se llama al método tomarSeleccionResultado() pasando "Rechazado" como parámetro
            }
        });

        // Agrega ActionListener para responder a eventos en el botón de confirmar
        confirmarButton.addActionListener(new ActionListener() {
            @Override
            // Método que se ejecuta al presionar el boton de confirmar
            public void actionPerformed(ActionEvent e) {
                tomarSeleccionResultado("Confirmado"); // Se llama al método tomarSeleccionResultado() pasando "Confirmado" como parámetro
            }
        });

        // Agrega ActionListener para responder a eventos en el botón de delegar a experto
        delegarAExpertoButton.addActionListener(new ActionListener() {
            @Override
            // Método que se ejecuta al presionar el boton de delegar a experto
            public void actionPerformed(ActionEvent e) {
                tomarSeleccionResultado("Derivado a Experto"); // Se llama al método tomarSeleccionResultado() pasando "Derivado a Experto" como parámetro
            }
        });

        // Agrega ActionListener para responder a eventos en el botón de modificar datos
        modificarDatosButton.addActionListener(new ActionListener() {
            @Override
            // Método que se ejecuta al presionar el boton de modificar datos
            public void actionPerformed(ActionEvent e) {
                tomarSolicitudModificacionDatos(); // Se llama al método que gestiona la solicitud para modificar los datos
            }
        });

        // Agrega ActionListener para responder a eventos en el botón de ver mapa
        verMapaButton.addActionListener(new ActionListener() {
            @Override
            // Método que se ejecuta al presionar el boton de ver mapa
            public void actionPerformed(ActionEvent e) {
                tomarSolicitudVerMapa(); // Se llama al método que gestiona la solicitud para ver mapa
            }
        });
    }

    // Método que se invoca al seleccionar la opción de registrar el resultado de la revisión
    public void opcRegResultadoES(){
        this.abrirPantalla(); // Se abre la pantalla para registrar el resultado
        gestor.nuevaRevisionES(this); // Se inicia al gestor pasandole la instancia de la pantalla actual
    }

    // Método de abrir la pantalla para registrar el resultado de la revisión
    public void abrirPantalla(){
        this.opcRegRRevision.setVisible(false); // Oculta la opción de registrar resultado de revisión
        this.eventosComboBox.setVisible(true); // Muestra la lista de eventos para selección
        this.origentxt.setVisible(true); // Muestra el campo de texto de origen de generación
        this.alcancetxt.setVisible(true); // Muestra el campo de texto de alcance
        this.clasificaciontxt.setVisible(true); // Muestra el campo de texto de clasficicación
        this.seleccionarButton.setVisible(true); // Muestra el botón de selección
        this.alcanceLabel.setVisible(true); // Muestra la etiqueta de alcance
        this.clasificacionLabel.setVisible(true); // Muestra la etiqueta de clasificación
        this.origenDeGeneracionLabel.setVisible(true); // Muestra la etiqueta de origen de generación
        this.verMapaButton.setVisible(true); // Muestra el botón de ver mapa
        this.modificarDatosButton.setVisible(true); // Muestra el botón de modificar datos
        this.confirmarButton.setVisible(true); // Muestra el botón de confirmar
        this.rechazarButton.setVisible(true); // Muestra el botón de rechazar
        this.delegarAExpertoButton.setVisible(true); // Muestra el botón de delegar a experto
        this.seleccioneResultadoLabel.setVisible(true); // Muestra la etiqueta de seleccione el resultado
    }

    // Método para cargar y mostrar en el comboBox los eventos sísmicos
    public void mostrarESParaSeleccion(ArrayList<String> eventosSismicos){

        // Crea un modelo para el comboBox
        DefaultComboBoxModel <String> eventos = new DefaultComboBoxModel<>();
        for (String evento: eventosSismicos) { // Se recorren los eventos de la lista
            eventos.addElement(evento); // Agrega el evento al modelo
        }
        eventosComboBox.setModel(eventos); // Asigna el modelo al comboBox
    }

    // Método que pasa el evento sísmico seleccionado al gestor
    public void tomarSeleccionES(String evento){
        gestor.tomarSeleccionES(evento);
    }

    // Método para mostrar los datos del evento sismico
    public void mostrarDatosEventoSismico(String nombreAlcance, String nombreOrigenGeneracion, String nombreClasificacion){
        this.alcancetxt.setText(nombreAlcance); // Muestra el alcance
        this.origentxt.setText(nombreOrigenGeneracion); // Muestra el origen
        this.clasificaciontxt.setText(nombreClasificacion); // Muestra la clasificación
    }

    // Método para habilitar la opción de ver mapa
    public void habilitarOpcVerMapa(){
        this.verMapaButton.setEnabled(true);
    }

    // Método para habilitar la opción de modificar datos
    public void habilitarOpcModificarDatosES(){
        this.modificarDatosButton.setEnabled(true);
    }

    // Método para pedir la selección del resultado del evento sísmico
    public void pedirSeleccionResultadoEvento(){
        this.seleccioneResultadoLabel.setEnabled(true); // Activa la etiqueta que indica se seleccione un resultado
        this.rechazarButton.setEnabled(true); // Habilita el botón de rechazar
        this.confirmarButton.setEnabled(true); // Habilita el botón de confirmar
        this.delegarAExpertoButton.setEnabled(true); // Habilita el botón de delegar a experto
    }

    // Método utilizado al cerrar una ventana
    public void tomarCancelacion(){
        dispose(); // Cierra la ventana
        gestor.cancelarCU(); // Cancela el CU en el gestor
    }

    // Método para informar que no hay eventos sísmicos no revisados
    public void informarNoHayESNoRevisados(){
        JOptionPane.showMessageDialog(this, "No hay sismos auto detectados que aún no han sido revisados"); // Muestra un mensaje
        this.dispose(); // Cierra la ventana
    }

    // Método para tomar la selección del resultado
    public void tomarSeleccionResultado(String seleccion){
        JOptionPane.showMessageDialog(this,"Resultado Registrado!"); // Muestra un mensaje
        dispose(); // Cierra la ventana
        gestor.tomarSeleccionResultado(seleccion); // Pasa al gestor la selección del resultado
    }

    // Método para tomar la solicitud de moficación de datos
    public void tomarSolicitudModificacionDatos(){
        JOptionPane.showMessageDialog(this,"Cargando..."); // Muestra un mensaje
    }

    // Método para tomar la solicitud de ver mapa
    public void tomarSolicitudVerMapa(){
        JOptionPane.showMessageDialog(this,"Cargando mapa..."); // Muestra un mensaje
    }
}