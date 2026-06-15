package src;
//  Integrantes: Lucas Araya, Francisco Carvajal, Rocio Villalobos
import lyrics.analysis.DepthFirstAdapter;
import lyrics.node.*;

public class VisitadorLetras extends DepthFirstAdapter {
    
    private RepositorioLetras repositorio;

    // En donde se guardan todas las letras
    public VisitadorLetras(RepositorioLetras repo) {
        this.repositorio = repo;
    }

    // Este método se ejecuta al pasar por cada línea de tiempo en el árbol
    @Override
    public void outATiempoLinea(ATiempoLinea node) {
        
        // Se limpian los espacios en blanco y convierte el texto del tiempo en numeros
        int min = Integer.parseInt(node.getM().getText().trim());
        int seg = Integer.parseInt(node.getS().getText().trim());
        int cen = Integer.parseInt(node.getC().getText().trim());

        // Se juntan todas las palabras sueltas para formar las frases completas
        StringBuilder frase = new StringBuilder();
        for (PElemento elemento : node.getLetra()) {
            frase.append(elemento.toString().trim()).append(" ");
        }

        // Se toma la frase terminada junto a su tiempo y se guarda 
        LineaLetra linea = new LineaLetra(min, seg, cen, frase.toString().trim());
        repositorio.agregarLinea(linea);
    }
}

/* RESUMEN DE MÉTODOS
   
   METODOS VisitadorLetras (Constructor)
   - Recibe el espacio (repositorio) donde se guardarán las letras.
   - Finalmente guarda este espacio de forma interna en la clase para poder usarlo en los demás pasos.

   METODOS outATiempoLinea
   - Toma los textos de los minutos, segundos y centésimas que encontró el programa y los transforma en números de verdad.
   - Toma todas las palabras o símbolos separados de la canción y los va uniendo uno por uno para formar una sola oración de corrido.
   - Junta los números del tiempo con la oración terminada y los envía a la lista para dejarlos guardados en la memoria.
*/
//EY EY EY EY AAAAQUI el rich eeeeeen minecraft yyyyy hoy les traigo .....