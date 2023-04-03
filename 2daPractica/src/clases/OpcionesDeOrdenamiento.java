package clases;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

public class OpcionesDeOrdenamiento extends JFrame implements ActionListener {

    public OpcionesDeOrdenamiento() {
        setSize(530, 400);
        setLocationRelativeTo(null);
        setTitle("Opciones de ordenamiento");
        iniciarComponentes();
        setDefaultCloseOperation(DISPOSE_ON_CLOSE);
    }

    private void iniciarComponentes() {
        colocarPaneles();
        interfazPrimerPanel();
    }

    JPanel panelOO, panel1, panel2, panel3;

    private void colocarPaneles() {
        panelOO = new JPanel();
        panelOO.setBorder(BorderFactory.createLineBorder(Color.WHITE, 1));
        panelOO.setLayout(null);
        this.add(panelOO);

        panel1 = new JPanel();
        panel1.setBounds(10, 10, 495, 100);
        panel1.setBorder(BorderFactory.createLineBorder(Color.WHITE, 1));
        panel1.setLayout(null);
        panelOO.add(panel1);

        panel2 = new JPanel();
        panel2.setBounds(10, 115, 245, 155);
        panel2.setBorder(BorderFactory.createLineBorder(Color.WHITE, 1));
        panel2.setLayout(null);
        panelOO.add(panel2);

        panel3 = new JPanel();
        panel3.setBounds(260, 115, 245, 155);
        panel3.setBorder(BorderFactory.createLineBorder(Color.WHITE, 1));
        panel3.setLayout(null);
        panelOO.add(panel3);
    }

    JLabel lblTipo, lblVelocidad, lblAlgoritmo;
    JRadioButton rbtnAscendente, rbtnDescendente, rbtnBaja, rbtnMedia, rbtnAlta, rbtnBubble, rbtnQuick, rbtnShell;
    JButton btnCancelar, btnAceptar2;

    private void interfazPrimerPanel() {

        //interfaz del primer panel
        lblTipo = new JLabel();
        lblTipo.setText("Tipo de Ordenamiento");
        lblTipo.setBounds(10, 10, 200, 20);
        lblTipo.setFont(new Font("Arial", 0, 18));
        panel1.add(lblTipo);

        rbtnAscendente = new JRadioButton("Ascendente", true);
        rbtnAscendente.setBounds(75, 50, 100, 25);
        panel1.add(rbtnAscendente);

        rbtnDescendente = new JRadioButton("Descendente", false);
        rbtnDescendente.setBounds(320, 50, 150, 25);
        panel1.add(rbtnDescendente);

        ButtonGroup rbtnPanel1 = new ButtonGroup();
        rbtnPanel1.add(rbtnAscendente);
        rbtnPanel1.add(rbtnDescendente);

        //Interfaz del segundo panel
        lblVelocidad = new JLabel();
        lblVelocidad.setText("Velocidad de Ordenamiento");
        lblVelocidad.setBounds(10, 10, 300, 20);
        lblVelocidad.setFont(new Font("Arial", 0, 18));
        panel2.add(lblVelocidad);

        rbtnBaja = new JRadioButton("Baja", true);
        rbtnBaja.setFont(new Font("Arial", 0, 18));
        rbtnBaja.setBounds(30, 40, 100, 25);
        panel2.add(rbtnBaja);

        rbtnMedia = new JRadioButton("Media", false);
        rbtnMedia.setFont(new Font("Arial", 0, 18));
        rbtnMedia.setBounds(30, 75, 100, 25);
        panel2.add(rbtnMedia);

        rbtnAlta = new JRadioButton("Alta", false);
        rbtnAlta.setFont(new Font("Arial", 0, 18));
        rbtnAlta.setBounds(30, 110, 100, 25);
        panel2.add(rbtnAlta);

        ButtonGroup rbtnPanel2 = new ButtonGroup();
        rbtnPanel2.add(rbtnBaja);
        rbtnPanel2.add(rbtnMedia);
        rbtnPanel2.add(rbtnAlta);

        //Intefaz del segundo panel
        lblAlgoritmo = new JLabel();
        lblAlgoritmo.setText("Algoritmo de Ordenamiento");
        lblAlgoritmo.setBounds(10, 10, 300, 20);
        lblAlgoritmo.setFont(new Font("Arial", 0, 18));
        panel3.add(lblAlgoritmo);

        rbtnBubble = new JRadioButton("Bubble Sort", true);
        rbtnBubble.setFont(new Font("Arial", 0, 18));
        rbtnBubble.setBounds(30, 40, 150, 25);
        panel3.add(rbtnBubble);

        rbtnQuick = new JRadioButton("Quicksort", false);
        rbtnQuick.setFont(new Font("Arial", 0, 18));
        rbtnQuick.setBounds(30, 75, 150, 25);
        panel3.add(rbtnQuick);

        rbtnShell = new JRadioButton("Shellsort", false);
        rbtnShell.setFont(new Font("Arial", 0, 18));
        rbtnShell.setBounds(30, 110, 100, 25);
        panel3.add(rbtnShell);

        ButtonGroup rbtnPanel3 = new ButtonGroup();
        rbtnPanel3.add(rbtnBubble);
        rbtnPanel3.add(rbtnQuick);
        rbtnPanel3.add(rbtnShell);

        //Interfaz panel por defecto
        btnCancelar = new JButton();
        btnCancelar.setBounds(240, 300, 125, 30);
        btnCancelar.setText("Cancelar");
        btnCancelar.addActionListener(this);
        panelOO.add(btnCancelar);

        btnAceptar2 = new JButton();
        btnAceptar2.setBounds(380, 300, 125, 30);
        btnAceptar2.setText("Aceptar");
        btnAceptar2.addActionListener(this);
        panelOO.add(btnAceptar2);

    }

    @Override
    public void actionPerformed(ActionEvent actionEvent) {
        if (actionEvent.getSource() == btnAceptar2) {
            btnAceptarAccion();
        } else if (actionEvent.getSource() == btnCancelar) {
            btnCancelarAccion();
        }
    }

    public void cerrar() {
        setDefaultCloseOperation(DO_NOTHING_ON_CLOSE);
        this.addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent windowEvent) {
                OrdenarGrafica OG = new OrdenarGrafica();
                OG.setVisible(true);
                dispose();
            }
        });
    }

    private void btnAceptarAccion() {
        //Creando combinaciones de radioBotones
        if ((rbtnAscendente.isSelected()) && (rbtnBaja.isSelected()) && (rbtnBubble.isSelected())) {
            JFrame frame = new JFrame();
            frame.setTitle("Ascendente Baja Bubble");
            frame.setSize(600, 500);
            frame.setVisible(true);
            frame.setLocationRelativeTo(null);
            cerrar();


            int cant = VentanaPrincipal.vectorDouble.length - 1;
            AscendenteBajaBubble ABB = new AscendenteBajaBubble(VentanaPrincipal.vectorDouble, VentanaPrincipal.vectorString, VentanaPrincipal.filasTotales, frame);
            ABB.start();

            /*JOptionPane.showMessageDialog(null, "La cantidad de pasos es de: "+AscendenteBajaBubble.contadorPasos);*/
            this.dispose();


            for (int i = 0; i < VentanaPrincipal.vectorDouble.length; i++) {
                System.out.print(VentanaPrincipal.vectorDouble[i] + ", ");
            }
            System.out.println("");
            for (int i = 0; i < VentanaPrincipal.vectorString.length; i++) {
                System.out.print(VentanaPrincipal.vectorString[i] + ", ");
            }


        } else if ((rbtnAscendente.isSelected()) && (rbtnMedia.isSelected()) && (rbtnBubble.isSelected())) {
            JFrame frame = new JFrame();
            frame.setTitle("Ascendente Media Bubble");
            frame.setSize(600, 500);
            frame.setVisible(true);
            frame.setLocationRelativeTo(null);
            AscendenteMediaBubble AMB = new AscendenteMediaBubble(VentanaPrincipal.vectorDouble, VentanaPrincipal.vectorString, VentanaPrincipal.filasTotales, frame);
            AMB.start();
            this.dispose();

        } else if ((rbtnAscendente.isSelected()) && (rbtnAlta.isSelected()) && (rbtnBubble.isSelected())) {
            JFrame frame = new JFrame();
            frame.setTitle("Ascendente Alta Bubble");
            frame.setSize(600, 500);
            frame.setVisible(true);
            frame.setLocationRelativeTo(null);
            AscendenteAltaBubble AAB = new AscendenteAltaBubble(VentanaPrincipal.vectorDouble, VentanaPrincipal.vectorString, VentanaPrincipal.filasTotales, frame);
            AAB.start();
            this.dispose();
        } else if ((rbtnDescendente.isSelected()) && (rbtnBaja.isSelected()) && (rbtnBubble.isSelected())) {
            JFrame frame = new JFrame();
            frame.setTitle("Descendente Baja Bubble");
            frame.setSize(600, 500);
            frame.setVisible(true);
            frame.setLocationRelativeTo(null);
            DescendenteBajaBubble DBB = new DescendenteBajaBubble(VentanaPrincipal.vectorDouble, VentanaPrincipal.vectorString, VentanaPrincipal.filasTotales, frame);
            DBB.start();
            this.dispose();
        } else if ((rbtnDescendente.isSelected()) && (rbtnMedia.isSelected()) && (rbtnBubble.isSelected())) {
            JFrame frame = new JFrame();
            frame.setTitle("Descendente Media Bubble");
            frame.setSize(600, 500);
            frame.setVisible(true);
            frame.setLocationRelativeTo(null);
            DescendenteMediaBubble DBB = new DescendenteMediaBubble(VentanaPrincipal.vectorDouble, VentanaPrincipal.vectorString, VentanaPrincipal.filasTotales, frame);
            DBB.start();
            this.dispose();
        } else if ((rbtnDescendente.isSelected()) && (rbtnAlta.isSelected()) && (rbtnBubble.isSelected())) {
            JFrame frame = new JFrame();
            frame.setTitle("Descendente Alta Bubble");
            frame.setSize(600, 500);
            frame.setVisible(true);
            frame.setLocationRelativeTo(null);
            DescendenteAltaBubble DAB = new DescendenteAltaBubble(VentanaPrincipal.vectorDouble, VentanaPrincipal.vectorString, VentanaPrincipal.filasTotales, frame);
            DAB.start();
            this.dispose();
        } else if ((rbtnAscendente.isSelected()) && (rbtnBaja.isSelected()) && (rbtnShell.isSelected())) {
            JFrame ventana = new JFrame();
            ventana.setTitle("Ascendente Baja Shell");
            ventana.setSize(600, 500);
            ventana.setVisible(true);
            ventana.setLocationRelativeTo(null);
            AscendenteBajaShell ABS = new AscendenteBajaShell(VentanaPrincipal.vectorDouble, VentanaPrincipal.vectorString, VentanaPrincipal.filasTotales, ventana);
            ABS.start();
            this.dispose();
        } else if ((rbtnAscendente.isSelected()) && (rbtnMedia.isSelected()) && (rbtnShell.isSelected())) {
            JFrame ventana = new JFrame();
            ventana.setTitle("Ascendente Media Shell");
            ventana.setSize(600, 500);
            ventana.setVisible(true);
            ventana.setLocationRelativeTo(null);
            AscendenteMediaShell AMS = new AscendenteMediaShell(VentanaPrincipal.vectorDouble, VentanaPrincipal.vectorString, VentanaPrincipal.filasTotales, ventana);
            AMS.start();
            this.dispose();
        } else if ((rbtnAscendente.isSelected()) && (rbtnAlta.isSelected()) && (rbtnShell.isSelected())) {
            JFrame ventana = new JFrame();
            ventana.setTitle("Ascendente Alta Shell");
            ventana.setSize(600, 500);
            ventana.setVisible(true);
            ventana.setLocationRelativeTo(null);
            AscendenteAltaShell AAS = new AscendenteAltaShell(VentanaPrincipal.vectorDouble, VentanaPrincipal.vectorString, VentanaPrincipal.filasTotales, ventana);
            AAS.start();
            this.dispose();
        } else if ((rbtnDescendente.isSelected()) && (rbtnBaja.isSelected()) && (rbtnShell.isSelected())) {
            JFrame ventana = new JFrame();
            ventana.setTitle("Descendente Baja Shell");
            ventana.setSize(600, 500);
            ventana.setVisible(true);
            ventana.setLocationRelativeTo(null);
            DescendenteBajaShell DBS = new DescendenteBajaShell(VentanaPrincipal.vectorDouble, VentanaPrincipal.vectorString, VentanaPrincipal.filasTotales, ventana);
            DBS.start();
            this.dispose();
        } else if ((rbtnDescendente.isSelected()) && (rbtnMedia.isSelected()) && (rbtnShell.isSelected())) {
            JFrame ventana = new JFrame();
            ventana.setTitle("Descendente media Shell");
            ventana.setSize(600, 500);
            ventana.setVisible(true);
            ventana.setLocationRelativeTo(null);
            DescendenteMediaShell DMS = new DescendenteMediaShell(VentanaPrincipal.vectorDouble, VentanaPrincipal.vectorString, VentanaPrincipal.filasTotales, ventana);
            DMS.start();
            this.dispose();
        }else if ((rbtnDescendente.isSelected()) && (rbtnAlta.isSelected()) && (rbtnShell.isSelected())){
            JFrame ventana = new JFrame();
            ventana.setTitle("Descendente Alta Shell");
            ventana.setSize(600, 500);
            ventana.setVisible(true);
            ventana.setLocationRelativeTo(null);
            DescendenteAltaShell DAS = new DescendenteAltaShell(VentanaPrincipal.vectorDouble, VentanaPrincipal.vectorString, VentanaPrincipal.filasTotales, ventana);
            DAS.start();
            this.dispose();
            //--------------------------------------------------
        }else if ((rbtnAscendente.isSelected()) && (rbtnBaja.isSelected()) && (rbtnQuick.isSelected())) {
            JFrame ventana = new JFrame();
            ventana.setTitle("Ascendente Baja Quick");
            ventana.setSize(600, 500);
            ventana.setVisible(true);
            ventana.setLocationRelativeTo(null);
            AscendenteBajaQuick ABQ = new AscendenteBajaQuick(VentanaPrincipal.vectorDouble, VentanaPrincipal.vectorString, VentanaPrincipal.filasTotales, ventana);
            ABQ.start();
            this.dispose();
        } else if ((rbtnAscendente.isSelected()) && (rbtnMedia.isSelected()) && (rbtnQuick.isSelected())) {//
            JFrame ventana = new JFrame();
            ventana.setTitle("Ascendente Media Quick");
            ventana.setSize(600, 500);
            ventana.setVisible(true);
            ventana.setLocationRelativeTo(null);
            AscendenteMediaQuick AMQ = new AscendenteMediaQuick(VentanaPrincipal.vectorDouble, VentanaPrincipal.vectorString, VentanaPrincipal.filasTotales, ventana);
            AMQ.start();
            this.dispose();
        } else if ((rbtnAscendente.isSelected()) && (rbtnAlta.isSelected()) && (rbtnQuick.isSelected())) {//
            JFrame ventana = new JFrame();
            ventana.setTitle("Ascendente Alta Quick");
            ventana.setSize(600, 500);
            ventana.setVisible(true);
            ventana.setLocationRelativeTo(null);
            AscendenteAltaQuick AAQ = new AscendenteAltaQuick(VentanaPrincipal.vectorDouble, VentanaPrincipal.vectorString, VentanaPrincipal.filasTotales, ventana);
            AAQ.start();
            this.dispose();
        } else if ((rbtnDescendente.isSelected()) && (rbtnBaja.isSelected()) && (rbtnQuick.isSelected())) {//
            JFrame ventana = new JFrame();
            ventana.setTitle("Descendente Baja Quick");
            ventana.setSize(600, 500);
            ventana.setVisible(true);
            ventana.setLocationRelativeTo(null);
            DescendenteBajaQuick DBQ = new DescendenteBajaQuick(VentanaPrincipal.vectorDouble, VentanaPrincipal.vectorString, VentanaPrincipal.filasTotales, ventana);
            DBQ.start();
            this.dispose();
        } else if ((rbtnDescendente.isSelected()) && (rbtnMedia.isSelected()) && (rbtnQuick.isSelected())) {//
            JFrame ventana = new JFrame();
            ventana.setTitle("Descendente media Quick");
            ventana.setSize(600, 500);
            ventana.setVisible(true);
            ventana.setLocationRelativeTo(null);
            DescendenteMediaQuick DMQ = new DescendenteMediaQuick(VentanaPrincipal.vectorDouble, VentanaPrincipal.vectorString, VentanaPrincipal.filasTotales, ventana);
            DMQ.start();
            this.dispose();
        }else if ((rbtnDescendente.isSelected()) && (rbtnAlta.isSelected()) && (rbtnQuick.isSelected())){
            JFrame ventana = new JFrame();
            ventana.setTitle("Descendente Alta Quick");
            ventana.setSize(600, 500);
            ventana.setVisible(true);
            ventana.setLocationRelativeTo(null);
            DescendentaAltaQuick DAQ = new DescendentaAltaQuick(VentanaPrincipal.vectorDouble, VentanaPrincipal.vectorString, VentanaPrincipal.filasTotales, ventana);
            DAQ.start();
            this.dispose();
        }
    }

    private void btnCancelarAccion() {
        this.dispose();
    }
}

