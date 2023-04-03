package clases;

import javax.imageio.ImageIO;
import javax.swing.*;
import javax.swing.filechooser.FileNameExtensionFilter;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;

import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.axis.CategoryAnchor;
import org.jfree.chart.axis.CategoryAxis;
import org.jfree.chart.axis.ValueAxis;
import org.jfree.chart.plot.CategoryPlot;
import org.jfree.chart.plot.PlotOrientation;
import org.jfree.chart.renderer.category.BarRenderer;
import org.jfree.chart.title.LegendTitle;
import org.jfree.data.category.DefaultCategoryDataset;


public class VentanaPrincipal extends JFrame implements ActionListener, Runnable {

    public VentanaPrincipal() {
        setSize(600, 500);
        setLocationRelativeTo(null);
        setTitle("USAC Processing Data");
        iniciarComponentes();
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
    }

    public void iniciarComponentes() {
        colocarPaneles();
        interfazPrincipal();
    }


    JPanel panelVP, panelGrafica;

    public void colocarPaneles() {
        panelVP = new JPanel();
        panelVP.setLayout(null);
        this.add(panelVP);

        panelGrafica = new JPanel();
        /*panelGrafica.setBackground(Color.LIGHT_GRAY);*/
        panelGrafica.setBounds(10, 190, 560, 250);
        panelGrafica.setLayout(new BorderLayout());
        panelVP.add(panelGrafica);
    }

    JButton btnGraficar, btnBuscar, btnAceptar;
    public static JTextField txtRuta, txtGrafica;
    JLabel lblRuta, lblGrafica;

    public void interfazPrincipal() {
        /*btnGraficar = new JButton();
        btnGraficar.setText("Graficar");
        btnGraficar.setBounds(100, 100, 125, 30);
        btnGraficar.addActionListener(this);
        panelVP.add(btnGraficar);*/

        //Labels
        lblRuta = new JLabel();
        lblRuta.setBounds(10, 15, 400, 30);
        lblRuta.setText("Ruta del archivo:");
        panelVP.add(lblRuta);

        lblGrafica = new JLabel();
        lblGrafica.setBounds(10, 70, 400, 30);
        lblGrafica.setText("Titulo para la grafica:");
        panelVP.add(lblGrafica);


        //Campo par la ruta
        txtRuta = new JTextField();
        txtRuta.setBounds(10, 40, 400, 30);
        panelVP.add(txtRuta);

        txtGrafica = new JTextField();
        txtGrafica.setBounds(10, 95, 560, 30);
        panelVP.add(txtGrafica);

        //Botones
        btnBuscar = new JButton();
        btnBuscar.setBounds(445, 40, 125, 30);
        btnBuscar.setText("Buscar");
        btnBuscar.addActionListener(this);
        panelVP.add(btnBuscar);

        btnAceptar = new JButton();
        btnAceptar.setBounds(445, 130, 125, 30);
        btnAceptar.setText("Aceptar");
        btnAceptar.addActionListener(this);
        panelVP.add(btnAceptar);


    }


    @Override
    public void actionPerformed(ActionEvent actionEvent) {
        if (actionEvent.getSource() == btnGraficar) {
            bntGraficarAccion();
        } else if (actionEvent.getSource() == btnBuscar) {
            btnBuscarAccion();
        } else if (actionEvent.getSource() == btnAceptar) {
            btnAceptarAccion();
        }

    }

    @Override
    public void run() {

    }


    public void bntGraficarAccion() {
        DefaultCategoryDataset barChartData = new DefaultCategoryDataset();
        barChartData.setValue(20000, "Contribution Amount", "January");
        barChartData.setValue(15000, "Contribution Amount", "February");
        barChartData.setValue(30000, "Contribution Amount", "March");

        JFreeChart barChart = ChartFactory.createBarChart("Church Contributions", "montly", "contributions amount", barChartData, PlotOrientation.VERTICAL, false, true, false);
        CategoryPlot barchrt = barChart.getCategoryPlot();
        barchrt.setRangeGridlinePaint(Color.ORANGE);

        BufferedImage image = barChart.createBufferedImage(600, 400);
        try {
            ImageIO.write(image, "png", new File("VentanaPrincipal.png"));
        } catch (IOException e) {
            e.printStackTrace();
        }


        ChartPanel barPanel = new ChartPanel(barChart);
        panelGrafica.removeAll();
        panelGrafica.add(barPanel, BorderLayout.CENTER);
        panelGrafica.validate();


    }


    String ruta;

    private void btnBuscarAccion() {
        if (txtRuta.getText().length() == 0) {
            JFileChooser fileChooser = new JFileChooser();
            fileChooser.setCurrentDirectory(new File(System.getProperty("user.home")));
            fileChooser.setAcceptAllFileFilterUsed(false);
            FileNameExtensionFilter filter = new FileNameExtensionFilter("Archivos csv", "csv");
            fileChooser.addChoosableFileFilter(filter);

            int op1 = fileChooser.showOpenDialog(this);
            if (op1 == JFileChooser.APPROVE_OPTION) {
                ruta = fileChooser.getSelectedFile().toString();
                txtRuta.setText(ruta);
            }
        } else {
            JFileChooser fileChooser = new JFileChooser();
            fileChooser.setCurrentDirectory(new File(System.getProperty("user.home")));
            fileChooser.setAcceptAllFileFilterUsed(false);
            FileNameExtensionFilter filter = new FileNameExtensionFilter("Archivos csv", "csv");
            fileChooser.addChoosableFileFilter(filter);

            int op1 = fileChooser.showOpenDialog(this);
            if (op1 == JFileChooser.APPROVE_OPTION) {
                txtRuta.setText(null);
                ruta = fileChooser.getSelectedFile().toString();
                txtRuta.setText(ruta);
            }


        }

    }


    int contadorDeDatos = 0;
    public static String vectorPrimeraColumna[] = new String[1000], vectorSegundaColumna[] = new String[1000];
    private int x = 0;
    CargaDeDatos[] datosDelArchivo = new CargaDeDatos[1000];

    private void btnAceptarAccion() {

        if (ruta.length() != 0) {

            int cantFilas = 0;
            try {
                FileReader fr = new FileReader(ruta);
                BufferedReader br = new BufferedReader(fr);


                while ((br.readLine()) != null) {
                    cantFilas++;
                }
                br.close();
                System.out.println("La cantidad de filas es: " + cantFilas);
            } catch (Exception e) {
                System.out.println("El archivo no existe");
            }

            datosDelArchivo = new CargaDeDatos[1000];
            //lineas que leen el archivo
            try {


                FileReader fr = new FileReader(ruta);
                BufferedReader br = new BufferedReader(fr);

                String contenido;
                while ((contenido = br.readLine()) != null) {
                    String[] spliteado = contenido.split(",");
                    String primeraColumna = spliteado[0];
                    String segundaColumna = spliteado[1];


                    //Creare un vector de titulos
                    vectorPrimeraColumna[x] = primeraColumna;
                    //vector de productos
                    vectorSegundaColumna[x] = segundaColumna;
                    x++;


                    datosDelArchivo[contadorDeDatos] = new CargaDeDatos(primeraColumna, segundaColumna);
                    contadorDeDatos++;
                }
                JOptionPane.showMessageDialog(null, "El archivo .csv fue cargado correctamente");
                for (int i = 0; i < 10; i++) {
                    System.out.println(vectorPrimeraColumna[i]);
                }
                generarVectoresYcasteo(cantFilas);
                mostrarGrafica(cantFilas);

                OrdenarGrafica OG = new OrdenarGrafica();
                OG.setVisible(true);

            } catch (Exception e) {
                e.printStackTrace();
            }


        } else {
            ruta = txtRuta.getText();


        }


    }

    public static String[] vectorString;
    public static double[] vectorDouble;

    public void generarVectoresYcasteo(int canFilas) {
        System.out.println("La otra cant filas es " + canFilas);
        vectorString = new String[canFilas];
        vectorDouble = new double[canFilas];

        for (int i = 0; i < canFilas; i++) {
            vectorString[i] = vectorPrimeraColumna[i];
        }

        for (int k = 1; k < canFilas; k++) {
            vectorDouble[k] = Double.parseDouble(vectorSegundaColumna[k]);
        }

        for (int i = 0; i < 10; i++) {
            System.out.println(vectorString[i]);
        }

        for (int i = 0; i < 10; i++) {
            System.out.println(vectorDouble[i]);
        }

    }



    public static int filasTotales;
    private void mostrarGrafica(int cantFilas) {
        filasTotales = cantFilas;

        DefaultCategoryDataset barChartData = new DefaultCategoryDataset();

        for (int i = 1; i < cantFilas; i++) {
            barChartData.setValue(vectorDouble[i], vectorString[i], vectorString[i]);
        }


        JFreeChart barChart = ChartFactory.createBarChart(txtGrafica.getText(), vectorString[0], vectorSegundaColumna[0], barChartData, PlotOrientation.VERTICAL, true, false, false);

        barChart.setBackgroundPaint(Color.black);//Este es para cambiar el color del fondo
        barChart.getPlot().setBackgroundPaint(Color.black);//Este es para cambiar el color del fondo de ploteado

        barChart.getTitle().setPaint(Color.WHITE);


        CategoryPlot barchrt = barChart.getCategoryPlot();//Cambia color en eje x
        CategoryAxis domain = barchrt.getDomainAxis();//Cambia color en eje x
        domain.setTickLabelPaint(Color.WHITE);//Cambia color en eje x

        ValueAxis axis = barchrt.getRangeAxis();//Cambia color en eje y
        axis.setTickLabelPaint(Color.WHITE);//Cambia color en eje y

        BarRenderer br = (BarRenderer) barChart.getCategoryPlot().getRenderer();
        br.setItemMargin(-4);
        barchrt.setRangeGridlinePaint(Color.ORANGE);


        BufferedImage image = barChart.createBufferedImage(600, 400);
        try {
            ImageIO.write(image, "png", new File("VentanaPrincipal.png"));
        } catch (IOException e) {
            e.printStackTrace();
        }

        ChartPanel barPanel = new ChartPanel(barChart);
        panelGrafica.removeAll();
        panelGrafica.add(barPanel, BorderLayout.CENTER);
        panelGrafica.validate();

    }


}
