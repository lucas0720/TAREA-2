package src;
public class LineaLetra {
    private int tiempoEnMilisegundos; 
    private String texto;

    // Constructor
    public LineaLetra(int min, int seg, int cen, String texto) {
        // Convertimos el tiempo a milisegundos para sincronizarlo fácil con el MP3
        this.tiempoEnMilisegundos = (min * 60 * 1000) + (seg * 1000) + (cen * 10);
        this.texto = texto;
    }

    // Getters
    public int getTiempo() {
        return tiempoEnMilisegundos;
    }

    public String getTexto() {
        return texto;
    }

    // Para poder imprimirlo en consola y probar
    @Override
    public String toString() {
        return "[" + tiempoEnMilisegundos + " ms] -> " + texto;
    }
}