package src;

//  Integrantes: Lucas Araya, Francisco Carvajal, Rocio Villalobos

import javazoom.jlgui.basicplayer.BasicPlayer;
import java.io.File;
import java.io.FileReader;
import java.io.PushbackReader;
import java.util.Timer;
import java.util.TimerTask;
import lyrics.lexer.Lexer;
import lyrics.parser.Parser;
import lyrics.node.Start;

public class Reproductor {
    private InterfazKaraoke gui;
    private BasicPlayer player;
    private Timer timerGlobal; // Controla los tiempos en los que se debe mostrar cada frase

    // Prepara la herramienta que reproduce el audio
    public Reproductor(){
        player = new BasicPlayer();
    }

    // Asigna la ventana visual a este reproductor 
    public void setGui(InterfazKaraoke gui) { this.gui = gui; }

    // Controles de reproducción del audio
    public void Play() throws Exception { player.play(); }
    public void Pausa() throws Exception { player.pause(); }
    public void Continuar() throws Exception { player.resume(); }
    public void Stop() throws Exception { 
        player.stop(); 
        // Si se detiene el audio entonces tambien se cancelan los tiempos programados
        if(timerGlobal != null) timerGlobal.cancel();
    }

    public void cargarCancion(String mp3Nombre, String lrcNombre) throws Exception {
        
        // Cancela programaciones anteriores e inicia una nueva cuenta de tiempo
        if(timerGlobal != null) timerGlobal.cancel();
        timerGlobal = new Timer();

        String rutaLrc = "songs/" + lrcNombre;

        // Lee el archivo de texto y procesa las lineas
        Lexer lexer = new Lexer(new java.io.PushbackReader(new java.io.FileReader(rutaLrc), 1024));
        Parser parser = new Parser(lexer);
        Start tree = parser.parse();
        
        // Extrae las frases y las guarda en la lista
        RepositorioLetras miRepositorio = new RepositorioLetras();
        VisitadorLetras visitador = new VisitadorLetras(miRepositorio);
        tree.apply(visitador);
        
        // Carga el archivo de audio mp3
        player.open(new java.io.File("songs/" + mp3Nombre));
        long delayAudio = 1500;

        // Recorre las frases guardadas y programa el momento de mostrar cada una
        for (LineaLetra subtitulo : miRepositorio.getListaSubtitulos()) {
            new Reminder(subtitulo.getTiempo() + delayAudio, subtitulo.getTexto());
        }

    }

    // Mostrar una frase en un momento especifico
    public class Reminder {
        String texto;
        public Reminder(long ms, String texto) {
            this.texto = texto;
            timerGlobal.schedule(new RemindTask(), ms); // Da la salida para el tiempo indicado
        }
        class RemindTask extends TimerTask {
            public void run() {
                if (gui != null) gui.mostrarLetra(texto); // Muestra el texto en la pantalla
            }
        }
    }
}

/*   RESUMEN DE MÉTODOS
   
   MÉTODO Reproductor (Constructor)
   - Pprepara la herramienta de código que reproduce

   MÉTODOS Play, Pausa, Continuar, Stop
   - Inician, pausan o continúan el audio.
   - En el caso de detener (Stop), también cancela la programación de los tiempos para que dejen de salir las letras.

   MÉTODO cargarCancion
   - Cancela cualquier cuenta de tiempo anterior y crea una nueva.
   - Lee el archivo de texto, procesa su contenido y guarda las frases en una lista.
   - Carga el archivo de audio para que esté listo para sonar.
   - Recorre todas las frases guardadas y programa el momento exacto en que debe mostrarse cada una, sumando un pequeño tiempo de espera.

   CLASE Reminder y su MÉTODO run
   - Recibe el tiempo y el texto de una frase de la canción.
   - Programa la tarea para que se ejecute en ese tiempo específico.
   - Al cumplirse el tiempo, envía la frase a la interfaz para que se vea en la pantalla.
*/