package src;

//  Integrantes: Lucas Araya, Francisco Carvajal, Rocio Villalobos

import java.util.ArrayList;
import java.util.List;

public class RepositorioLetras {
    
    // Lista que guarda todas las frases de la cancion con sus tiempos
    private List<LineaLetra> listaSubtitulos;

    // Hace la lista vacia al inicio
    public RepositorioLetras() {
        this.listaSubtitulos = new ArrayList<>();
    }

    // Añade una nueva frase a la lista
    public void agregarLinea(LineaLetra linea) {
        this.listaSubtitulos.add(linea);
    }

    // Entrega la lista completa con todas las frases guardadas
    public List<LineaLetra> getListaSubtitulos() {
        return listaSubtitulos;
    }

    // Imprime en la pantalla todas las frases para comprobar que se guardaron bien
    public void mostrarTodo() {
        System.out.println("--- LETRAS CARGADAS EN MEMORIA ---");
        for (LineaLetra l : listaSubtitulos) {
            System.out.println(l.toString());
        }
    }
}

/*  RESUMEN DE METODOS
   
   METODOS RepositorioLetras (Constructor)
   - Crea la lista vacía donde se guardarán las frases de la canción.

   METODOS agregarLinea
   - Recibe una frase específica junto con su tiempo.
   - Agrega esta frase al final de la lista.

   METODOS getListaSubtitulos
   - Toma la lista que contiene todas las frases ya guardadas.
   - Entrega esta lista completa para que otras partes del programa la puedan leer y usar.

   METODOS mostrarTodo
   - Escribe un título en la pantalla.
   - Luego revisa una por una todas las frases que están guardadas en la lista.
   - Finalmente muestra los datos de cada frase en la pantalla para comprobar que todo se guardó de forma correcta.
*/