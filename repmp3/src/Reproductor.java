package src;

// Integrantes: Lucas Araya, Francisco Carvajal, Rocio Villalobos

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
    
    private BasicPlayer player;
    private Timer timerGlobal; 
    private RepositorioLetras miRepositorio;
    
    private long tiempoInicio = 0;
    private long tiempoDesplazamiento = 0; 
    private long delayAudio = 1500; 
    
    // NUEVO: Controlamos el estado para que BasicPlayer no se vuelva loco
    private boolean enPausa = false; 

    public Reproductor(){
        player = new BasicPlayer();
    }

    public void cargarCancion(String mp3Nombre, String lrcNombre) throws Exception {
        String rutaLrc = "songs/" + lrcNombre;
        String rutaMp3 = "songs/" + mp3Nombre;

        if (timerGlobal != null) timerGlobal.cancel();
        tiempoDesplazamiento = 0; 
        enPausa = false;

        FileReader lectorArchivo = new FileReader(rutaLrc);
        PushbackReader lectorRetroceso = new PushbackReader(lectorArchivo, 1024);

        Lexer lexer = new Lexer(lectorRetroceso);
        Parser parser = new Parser(lexer);
        Start tree = parser.parse();
        
        miRepositorio = new RepositorioLetras();
        VisitadorLetras visitador = new VisitadorLetras(miRepositorio);
        tree.apply(visitador);
        
        player.open(new File(rutaMp3));
    }

    public void Play() throws Exception { 
        // Si aprietan Play estando pausado, lo reanudamos porque osino se bugiaba
        if (enPausa) {
            Continuar();
            return;
        }
        player.play(); 
        tiempoDesplazamiento = 0; 
        enPausa = false;
        programarLetras(); 
    }
    
    public void Pausa() throws Exception { 
        if (!enPausa) { // Solo pausa si está sonando
            player.pause(); 
            enPausa = true;
            if(timerGlobal != null) timerGlobal.cancel(); 
            tiempoDesplazamiento += (System.currentTimeMillis() - tiempoInicio);
        }
    }
    
    public void Continuar() throws Exception { 
        if (enPausa) { // Solo continúa si de verdad estaba pausado
            player.resume();
            enPausa = false;
            programarLetras(); 
        }
    }
    
    public void Stop() throws Exception { 
        player.stop(); 
        enPausa = false;
        if(timerGlobal != null) timerGlobal.cancel();
        tiempoDesplazamiento = 0;
    }

    private void programarLetras() {
        if (timerGlobal != null) timerGlobal.cancel();
        timerGlobal = new Timer();
        tiempoInicio = System.currentTimeMillis();

        for (LineaLetra subtitulo : miRepositorio.getListaSubtitulos()) {
            long tiempoAparicion = subtitulo.getTiempo() + delayAudio;
            long tiempoRestante = tiempoAparicion - tiempoDesplazamiento;

            if (tiempoRestante > 0) {
                new Reminder(tiempoRestante, subtitulo.getTexto());
            }
        }
    }

    // Tu clase original intacta
    public class Reminder {
        String texto;
        public Reminder(long ms, String texto) {
            this.texto = texto;
            timerGlobal.schedule(new RemindTask(), ms);
        }
        class RemindTask extends TimerTask {
            public void run() {
                System.out.println("\n[🎵] >> " + texto); 
            }
        }
    }
}

/*   RESUMEN DE METODO
   ATRIBUTOS 
    - gui: Es la ventana visual donde se mostrarán las letras. 
    - player: Es la herramienta que se encarga de reproducir el audio.
    - timerGlobal: Es la herramienta que se encarga de controlar los tiempos
   METODO Reproductor (Constructor)
   - Pprepara la herramienta de código que reproduce

   METODO Play, Pausa, Continuar, Stop
   - Inician, pausan o continúan el audio.
   - En el caso de detener (Stop), también cancela la programación de los tiempos para que dejen de salir las letras.

   METODO cargarCancion
   - Cancela cualquier cuenta de tiempo anterior y crea una nueva.
   - Lee el archivo de texto, procesa su contenido y guarda las frases en una lista.
   - Carga el archivo de audio para que esté listo para sonar.
   - Recorre todas las frases guardadas y programa el momento exacto en que debe mostrarse cada una, sumando un pequeño tiempo de espera.

   Clase Java.ioFileReader y PushbackReader: ocupamos la clase java.io. porque era algo que sableCC usa para leer los archivoss. aprendimos 
   que java.io es una clase que se ocupa para manejar archivos mover datos y para no tener problemas ocupamos lo mismo.

   java.io.FileReader: es para leet el archivo de texto en este caso los arhcivo.lrc de la carpeta songs
   java.ioPushbackReader: se ocupa porque por lo visto tiene la particularidad de que permite que el lexer , tome un archivo lo examine y 
   si este determina que ese caracterejemplo no es parte del token que contruya , pueda devolverlo al flujo de lectura 

   se ocupa porque era requisito tecnico del sablecc para que el proceso de analizar el lexico no pieda indo

   y la preferencia de ocupar esta a diferencia de otras que conociamos como BufferedReader es que 

   creamos un objeto de Lexer parser y tree, 

   Lexer, es el analizador lexico , recibe los caractereres y forma los tokens
   parser , aqui recive los tokens y ve si tiene tentido segun los archivos , si la cancion tiene la estructura de la gramatica funciona bien sino no
   tree, le dice al parser que trabaje , t devuelve un objeto tree , que es una representacion de memoria de toda la estructura de la cancion, el 
   visitadorLetras lo ocupa para extraler los tiempos y textos

   tree.apply(visitador); aqui hacemos que el visitador entre al arvol , si al visitar el arbol para por un nodo que le parece bien  , se activa el metodo de la clase
   VisitadorLetras, y asi gud

   player.open , da la orden que el reproductor basicPlayer de buscar el archivo de audio en la carpeta songs y cargarlo para que este listo para sonar


   CLASE Reminder y su METODO run
   - Recibe el tiempo y el texto de una frase de la canción.
   - Programa la tarea para que se ejecute en ese tiempo específico.
   - Al cumplirse el tiempo, envía la frase a la interfaz para que se vea en la pantalla.
*/