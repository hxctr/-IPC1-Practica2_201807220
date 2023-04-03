package clases;

public class CargaDeDatos {

    private String primeraColumna;
    private String segundaColumna;

    public CargaDeDatos(String primeraColumna, String segundaColumna) {
        this.primeraColumna = primeraColumna;
        this.segundaColumna = segundaColumna;
    }

    public CargaDeDatos() {
        primeraColumna = "";
        segundaColumna = "";
    }

    public String getPrimeraColumna() {
        return primeraColumna;
    }

    public void setPrimeraColumna(String primeraColumna) {
        this.primeraColumna = primeraColumna;
    }

    public String getSegundaColumna() {
        return segundaColumna;
    }

    public void setSegundaColumna(String segundaColumna) {
        this.segundaColumna = segundaColumna;
    }
}
