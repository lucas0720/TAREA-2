package src;
import lyrics.analysis.DepthFirstAdapter;
import lyrics.node.*;

public class VisitadorLetras extends DepthFirstAdapter {
    
    private RepositorioLetras repositorio;

    public VisitadorLetras(RepositorioLetras repo) {
        this.repositorio = repo;
    }

    // Este método se ejecuta al pasar por cada línea de tiempo en el árbol
    @Override
    public void outATiempoLinea(ATiempoLinea node) {
        
        // 1. Sacamos los números
        int min = Integer.parseInt(node.getM().getText().trim());
        int seg = Integer.parseInt(node.getS().getText().trim());
        int cen = Integer.parseInt(node.getC().getText().trim());

        // 2. Juntamos las palabras de la frase
        StringBuilder frase = new StringBuilder();
        for (PElemento elemento : node.getLetra()) {
            frase.append(elemento.toString().trim()).append(" ");
        }

        // 3. Creamos el molde y lo guardamos en el repositorio
        LineaLetra linea = new LineaLetra(min, seg, cen, frase.toString().trim());
        repositorio.agregarLinea(linea);
    }
}