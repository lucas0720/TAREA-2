package src;

//  Integrantes: Lucas Araya, Francisco Carvajal, Rocio Villalobos

public class LineaLetra {
    // Variables para guardar el tiempo calculado y el texto de la frase
    private int tiempoEnMilisegundos; 
    private String texto;

    // Constructor que recibe los datos de tiempo separados y el texto completo
    public LineaLetra(int min, int seg, int cen, String texto) {
        // Cambia los minutos, segundos y centesimas a una sola medida de tiempo
        this.tiempoEnMilisegundos = (min * 60 * 1000) + (seg * 1000) + (cen * 10);
        this.texto = texto;
    }

    // Entrega el tiempo guardado
    public int getTiempo() {
        return tiempoEnMilisegundos;
    }

    // Entrega el texto guardado
    public String getTexto() {
        return texto;
    }

    // Toma los datos para ser mostrados en pantalla
    @Override
    public String toString() {
        return "[" + tiempoEnMilisegundos + " ms] -> " + texto;
    }
}

/*  RESUMEN DE MÉTODOS
   
   MÉTODO LineaLetra (Constructor)
   - Recibe los números de los minutos, segundos y centésimas, junto con el texto de la frase.
   - Realiza una multiplicación y suma para convertir todos esos números de tiempo a milisegundos.
   - Guarda este tiempo total y el texto de la frase dentro de la clase para usarlos después.

   MÉTODO getTiempo
   - Lee el número de tiempo en milisegundos que está guardado.
   - Finalmente entrega este número para que otras partes del programa sepan en qué momento mostrar la frase.

   MÉTODO getTexto
   - Lee el texto de la frase que está guardado.
   - Finalmente entrega este texto para que pueda ser enviado a la pantalla.

   MÉTODO toString
   - Junta el número de tiempo con el texto de la frase.
   - Entrega todo junto con un formato ordenado para que se pueda leer de forma clara al hacer pruebas.
*/