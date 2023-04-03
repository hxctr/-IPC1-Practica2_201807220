package clases;

import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.axis.CategoryAxis;
import org.jfree.chart.axis.ValueAxis;
import org.jfree.chart.plot.CategoryPlot;
import org.jfree.chart.plot.PlotOrientation;
import org.jfree.chart.renderer.category.BarRenderer;
import org.jfree.data.category.CategoryDataset;
import org.jfree.data.category.DefaultCategoryDataset;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

import static clases.VentanaPrincipal.vectorSegundaColumna;

public class AscendenteMediaShell extends Thread {

    private double vectorDouble[];
    private String vectorString[];
    private int cantFilas1;
    private JFrame ventana;

    public AscendenteMediaShell(double[] vectorDouble, String[] vectorString, int cantFilas1, JFrame ventana) {
        this.vectorDouble = vectorDouble;
        this.vectorString = vectorString;
        this.cantFilas1 = cantFilas1;
        this.ventana = ventana;
    }

    private CategoryDataset crearConjuntoDeDatos(double arregloDeNumeros[]) {

        DefaultCategoryDataset DCD = new DefaultCategoryDataset();

        for (int i = 1; i < cantFilas1; i++) {
            DCD.setValue(arregloDeNumeros[i], vectorString[i], vectorString[i]);
        }

        return DCD;
    }

    public ChartPanel crearChart(double arregloDeNumeros[]) {
        JFreeChart JFC = ChartFactory.createBarChart(VentanaPrincipal.txtGrafica.getText(), VentanaPrincipal.vectorString[0], VentanaPrincipal.vectorSegundaColumna[0], crearConjuntoDeDatos(arregloDeNumeros), PlotOrientation.VERTICAL, true, true, false);

        JFC.setBackgroundPaint(Color.black);//Este es para cambiar el color del fondo
        JFC.getPlot().setBackgroundPaint(Color.black);//Este es para cambiar el color del fondo de ploteado

        JFC.getTitle().setPaint(Color.WHITE);

        CategoryPlot barchrt = JFC.getCategoryPlot();//Cambia color en eje x
        CategoryAxis domain = barchrt.getDomainAxis();//Cambia color en eje x
        domain.setTickLabelPaint(Color.WHITE);//Cambia color en eje x

        ValueAxis axis = barchrt.getRangeAxis();//Cambia color en eje y
        axis.setTickLabelPaint(Color.WHITE);//Cambia color en eje y

        BarRenderer br = (BarRenderer) JFC.getCategoryPlot().getRenderer();//Este me sirvio para cambiar el grosor de las barritas
        br.setItemMargin(-4);//Este me sirvio para cambiar el grosor de las barritas
        barchrt.setRangeGridlinePaint(Color.ORANGE);


        ChartPanel CP = new ChartPanel(JFC);
        CP.setPreferredSize(new java.awt.Dimension(560, 367));

        BufferedImage image = JFC.createBufferedImage(600, 400);
        try {
            ImageIO.write(image, "png", new File("AscendenteMediaShell.png"));
        } catch (IOException e) {
            e.printStackTrace();
        }

        return CP;
    }


    static int contadorPasos = 0;
    static double segundosTotales;
    public void run() {
        int brinco;
        double asistente;
        boolean variar;

        long tiempoInicio = System.currentTimeMillis();
        for (brinco = vectorDouble.length / 2; brinco != 0; brinco /= 2) {
            variar = true;
            while (variar) {
                variar = false;
                for (int i = brinco; i < vectorDouble.length; i++) {
                    if (vectorDouble[i - brinco] > vectorDouble[i]) {
                        asistente = vectorDouble[i];
                        vectorDouble[i] = vectorDouble[i - brinco];
                        vectorDouble[i - brinco] = asistente;
                        variar = true;

                        ventana.setContentPane(crearChart(vectorDouble));
                        ventana.revalidate();
                        ventana.repaint();
                        try {
                            Thread.sleep(400);
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }
                    }
                    contadorPasos++;
                }
            }
        }
        long totalTiempo = System.currentTimeMillis() - tiempoInicio;
        segundosTotales = totalTiempo / 1000;

        JOptionPane.showMessageDialog(null, "ALGORITMO: ShellSort"
                + "\nVELOCIDAD: Media"
                + "\n ORDEN: Ascendente"
                + "\n TIEMPO: " + segundosTotales + " segundos"
                + "\n PASOS: " + contadorPasos);
        generarVector();

    }

    static String[][] matrizDePrimeraColumnaSinOrdenar = new String[5][10];
    static String[][] matrizDeSegundaColumnaSinOrdenar = new String[5][10];



    static double[] vectorEjeX = new double[VentanaPrincipal.filasTotales-1];//Este arreglo es del eje x pero sin el primero titulo
    static String[] vectorDeTitulosSinOrdenar = new String[VentanaPrincipal.filasTotales-1];

    static String[] vectorDeSegundacolumna = new String[VentanaPrincipal.filasTotales];


    public void generarVector(){

        for (int i = 0; i < vectorEjeX.length; i++){
            vectorEjeX[i] = VentanaPrincipal.vectorDouble[i+1];//Este me va a servir para encontrar el minimo y maximo, y aplicar burbuja y mostrar los datos ordenados
        }


        for (int i = 0; i < vectorDeTitulosSinOrdenar.length; i++){
            vectorDeTitulosSinOrdenar[i] = VentanaPrincipal.vectorString[i+1];//Este me va a servir para hacer burbuja y mostrar los datos ordenados
        }



        for (int i = 0; i < vectorDeTitulosSinOrdenar.length; i++){
            vectorDeSegundacolumna[i] = VentanaPrincipal.vectorSegundaColumna[i+1];//Este me va a servir para hacer burbuja y mostrar los datos ordenados
        }


        //para imprimir el vector

        int vectorPos = 0;


        for (int i = 0; i < matrizDePrimeraColumnaSinOrdenar.length; i++) {

            for (int j = 0; j < matrizDePrimeraColumnaSinOrdenar[i].length; j++) {
                if(vectorPos < vectorDeTitulosSinOrdenar.length)
                    matrizDePrimeraColumnaSinOrdenar[i][j] = vectorDeTitulosSinOrdenar[vectorPos++];
            }
        }


        int vectorPos2 = 0;
        for (int i = 0; i < matrizDeSegundaColumnaSinOrdenar.length; i++) {

            for (int j = 0; j < matrizDeSegundaColumnaSinOrdenar[i].length; j++) {
                if(vectorPos2 < vectorDeSegundacolumna.length)
                    matrizDeSegundaColumnaSinOrdenar[i][j] = vectorDeSegundacolumna[vectorPos2++];
            }
        }





        burbuja(vectorEjeX, vectorDeTitulosSinOrdenar);








    }

    private static void burbuja(double[] arreglo, String[] array) {
        for (int x = 0; x < arreglo.length; x++) {

            for (int y = 0; y < arreglo.length - 1; y++) {

                double elementoActual = arreglo[y], elementoSiguiente = arreglo[y + 1];

                if (elementoActual > elementoSiguiente) {

                    arreglo[y] = elementoSiguiente;
                    arreglo[y + 1] = elementoActual;

                }

            }

        }
        //-----------------------------------------------------------------------------
        for (int x = 0; x < array.length; x++) {

            for (int y = 0; y < array.length - 1; y++) {
                String elementoActual = array[y],
                        elementoSiguiente = array[y + 1];
                if (elementoActual.compareTo(elementoSiguiente) > 0) {
                    array[y] = elementoSiguiente;
                    array[y + 1] = elementoActual;
                }
            }
        }



        reporte(arreglo, array);


    }


    static String[][] matrizDePrimeraColumnaOrdenado = new String[5][10];
    static double[][] matrizDeSegundaColumnaOrdenado = new double[5][10];
    public static void reporte(double[] vectorDouble, String[] vectorString) {

        int vectorPos = 0;


        for (int i = 0; i < matrizDePrimeraColumnaOrdenado.length; i++) {

            for (int j = 0; j < matrizDePrimeraColumnaOrdenado[i].length; j++) {
                if (vectorPos < vectorString.length)
                    matrizDePrimeraColumnaOrdenado[i][j] = vectorString[vectorPos++];
            }
        }


        int vectorPos2 = 0;
        for (int i = 0; i < matrizDeSegundaColumnaOrdenado.length; i++) {

            for (int j = 0; j < matrizDeSegundaColumnaOrdenado[i].length; j++) {
                if (vectorPos2 < vectorDouble.length)
                    matrizDeSegundaColumnaOrdenado[i][j] = vectorDouble[vectorPos2++];
            }
        }


        double total = 0;
        double maxValue = 0;
        double minValue = 100;
        double contador = 0;

        for (int i = 0; i < vectorDouble.length; i++) {
            if (vectorDouble[i] < minValue) {
                minValue = vectorDouble[i];
            }
            if (vectorDouble[i] > maxValue) {
                maxValue = vectorDouble[i];
            }
            total += vectorDouble[i];
            contador++;
        }

        System.out.println("Valor maximo: " + maxValue);
        System.out.println("Valor minino: " + minValue);


        int pos = -1;
        for (int i = 0; i < vectorDouble.length; i++){
            if (vectorDouble[i] == maxValue){
                pos = i;
            }
        }

        if (pos == -1){
            System.out.println("No se encontro el numero");
        }else{
            System.out.println("La posicion del dato maximo "+maxValue+" es: "+pos);
        }

        //------------------------------
        int pos2 = -1;
        for (int i = 0; i < vectorDouble.length; i++){
            if (vectorDouble[i] == minValue){
                pos2 = i;
            }
        }

        if (pos2 == -1){
            System.out.println("No se encontro el numero");
        }else{
            System.out.println("La posicion del dato minimo "+minValue+" es: "+pos2);
        }


//----------------------------------------------------------------------------------------------------------------------
        String rutaReporte = "AscendenteMediaShell.html";

        try {

            FileWriter archivo2 = new FileWriter(rutaReporte);

            archivo2.write("<!DOCTYPE html>\n"
                    + "<html>\n"
                    + "<meta charset=\"utf-8\" />\n"
                    + "<title>Reporte</title>\n"
                    +"<link rel=\"stylesheet\" type=\"text/css\" href=\"style.css\" media=\"all\">"
                    + "</head>"
                    + "<body bgcolor=\"black\">"
                    + " <h2 align=\"center\"><font color=\"white\">Estudiante Hector Ponsoy</font></h1>\n"
                    + " <h4 align=\"center\"><font color=\"white\">201807220</font></h4>\n"
                    + "<hr>\n"
                    + "<hr>\n"
                    + "<h3><font color=\"white\">Algoritmo: Shellsort</font></h3>\n"
                    + "<h3><font color=\"white\">Velocidad: Media</font></h3>\n"
                    + "<h3><font color=\"white\">Orden: Ascendente</font></h3>\n"
                    + "<h3><font color=\"white\">Tiempo: "+segundosTotales+" segundos</font></h3>\n"
                    + "<h3><font color=\"white\">Pasos: "+contadorPasos+"</font></h3>\n");
            //-----------------------------------------------------------------------------------




            archivo2.write("<hr>\n"
                    + "<h3><font color=\"white\">El dato minimo es de "+vectorDeTitulosSinOrdenar[pos2]+" con un valor de "+minValue+"</font></h3>\n"
                    + "<h3><font color=\"white\">El dato maximo es de "+vectorDeTitulosSinOrdenar[pos]+" con un valor de "+maxValue+"</font></h3>\n");


            //---------------------------------------------------
            archivo2.write("<hr>\n"
                    + "<h2 align=\"center\"><font color=\"white\">Datos Desordenados</font></h1>\n");


            archivo2.write("<table>\n");
            for (int i = 0; i < matrizDePrimeraColumnaSinOrdenar.length; i++) {
                archivo2.write("<tr>\n");
                for (int j = 0; j < matrizDePrimeraColumnaSinOrdenar[i].length; j++) {
                    archivo2.write("<th><font color=\"white\">" + matrizDePrimeraColumnaSinOrdenar[i][j] + "</font></th>\n");
                }
                archivo2.write("</tr>\n");
            }
            archivo2.write("</table>\n");


            //---------------------------------------------------


            archivo2.write("<table>\n");
            for (int i = 0; i < matrizDeSegundaColumnaSinOrdenar.length; i++) {
                archivo2.write("<tr>\n");
                for (int j = 0; j < matrizDeSegundaColumnaSinOrdenar[i].length; j++) {
                    archivo2.write("<th><font color=\"white\">" + matrizDeSegundaColumnaSinOrdenar[i][j] + "</th>\n");
                }
                archivo2.write("</tr>\n");
            }
            archivo2.write("</table>\n");


            archivo2.write("<div align=\"center\"><img src=\"VentanaPrincipal.png\"></div>");





            //---------------------------------------------------


            archivo2.write("<hr>\n"
                    + "<h2 align=\"center\"><font color=\"white\">Datos ordenados</font></h1>\n");


            archivo2.write("<table>\n");
            for (int i = 0; i < matrizDePrimeraColumnaOrdenado.length; i++) {
                archivo2.write("<tr>\n");
                for (int j = 0; j < matrizDePrimeraColumnaOrdenado[i].length; j++) {
                    archivo2.write("<th><font color=\"white\">" + matrizDePrimeraColumnaOrdenado[i][j] + "</th>\n");
                }
                archivo2.write("</tr>\n");
            }
            archivo2.write("</table>\n");




            //---------------------------------------------------

            archivo2.write("<table>\n");
            for (int i = 0; i < matrizDeSegundaColumnaOrdenado.length; i++) {
                archivo2.write("<tr>\n");
                for (int j = 0; j < matrizDeSegundaColumnaOrdenado[i].length; j++) {
                    archivo2.write("<th><font color=\"white\">" + matrizDeSegundaColumnaOrdenado[i][j] + "</th>\n");
                }
                archivo2.write("</tr>\n");
            }
            archivo2.write("</table>\n");

            archivo2.write("<div align=\"center\"><img src=\"AscendenteMediaShell.png\"></div>");


            //---------------------------------------------------
            archivo2.write("</body>\n"
                    + "</html>\n");
            archivo2.close();
            System.out.println("*****Reporte creado con exito*****");
            System.out.println("\n");
        } catch (IOException e) {
            System.out.println("Algo salio mal");
        }
    }
}
