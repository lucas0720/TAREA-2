package src;
import java.util.ArrayList;
import java.util.List;

public class RepositorioLetras {
    
    private List<LineaLetra> listaSubtitulos;

    public RepositorioLetras() {
        this.listaSubtitulos = new ArrayList<>();
    }

    // Método para guardar una nueva línea
    public void agregarLinea(LineaLetra linea) {
        this.listaSubtitulos.add(linea);
    }

    // Método para recuperar toda la lista
    public List<LineaLetra> getListaSubtitulos() {
        return listaSubtitulos;
    }

    // Método para probar que estamos guardando bien en esta etapa
    public void mostrarTodo() {
        System.out.println("--- LETRAS CARGADAS EN MEMORIA ---");
        for (LineaLetra l : listaSubtitulos) {
            System.out.println(l.toString());
        }
    }
}