package clases;

import javax.swing.*;

public class Principal {
    public static void main(String[] args) {


        try {
            UIManager.setLookAndFeel("com.jtattoo.plaf.noire.NoireLookAndFeel");
        } catch (ClassNotFoundException e) {
            // e.printStackTrace();
        } catch (InstantiationException e) {
            //e.printStackTrace();
        } catch (IllegalAccessException e) {
            //  e.printStackTrace();
        } catch (UnsupportedLookAndFeelException e) {
            // e.printStackTrace();
        }


        VentanaPrincipal VP = new VentanaPrincipal();
        VP.setVisible(true);


       /* OpcionesDeOrdenamiento OO = new OpcionesDeOrdenamiento();
        OO.setVisible(true);*/




    }
}
