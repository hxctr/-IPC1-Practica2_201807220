package clases;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class OrdenarGrafica extends JFrame implements ActionListener {

    public OrdenarGrafica(){
        setSize(300, 150);
        setLocationRelativeTo(null);
        setTitle("Ordenamiento");

        iniciarComponentes();
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
    }

    private void iniciarComponentes(){
        colocarPanel();
        intefazPrincipal();
    }

    JPanel panelOG;
    private void colocarPanel(){
        panelOG = new JPanel();
        panelOG.setBorder(BorderFactory.createLineBorder(Color.BLACK, 2));
        panelOG.setLayout(null);
        this.add(panelOG);
    }

    JButton btnNo, btnSi;
    JLabel lblOrdenar, lblLinea1, lblLinea2;
    private void intefazPrincipal(){
        //etiquetas
        lblOrdenar = new JLabel();
        lblOrdenar.setText("¿Ordenar Grafica?");
        lblOrdenar.setFont(new java.awt.Font("Arial", 0, 18));
        lblOrdenar.setBounds(75, 10, 200, 40);
        panelOG.add(lblOrdenar);

        lblLinea1 = new JLabel();
        lblLinea1.setText("Se desplegarán las opciones de ordenamiento");
        lblLinea1.setBounds(19, 30, 400, 40);
        panelOG.add(lblLinea1);

        lblLinea2 = new JLabel();
        lblLinea2.setText("disponibles");
        lblLinea2.setBounds(115, 50, 100, 40);
        panelOG.add(lblLinea2);


        //botones

        btnNo = new JButton();
        btnNo.setBounds(40, 85, 100, 30);
        btnNo.setText("No");
        btnNo.addActionListener(this);
        panelOG.add(btnNo);

        btnSi = new JButton();
        btnSi.setBounds(160, 85, 100, 30);
        btnSi.setText("Si");
        btnSi.addActionListener(this);
        panelOG.add(btnSi);
    }

    private void btnNoAccion(){
        this.dispose();
    }

    private void btnSiAccion(){
        OpcionesDeOrdenamiento OO = new OpcionesDeOrdenamiento();
        OO.setVisible(true);
        this.dispose();
    }

    @Override
    public void actionPerformed(ActionEvent actionEvent) {
        if (actionEvent.getSource() == btnNo){
            btnNoAccion();
        }else if (actionEvent.getSource() == btnSi){
            btnSiAccion();
        }
    }
}
