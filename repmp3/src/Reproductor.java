package src;

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
    private Timer timerGlobal;

    public Reproductor(){
        player = new BasicPlayer();
    }

    public void setGui(InterfazKaraoke gui) { this.gui = gui; }

    public void Play() throws Exception { player.play(); }
    public void Pausa() throws Exception { player.pause(); }
    public void Continuar() throws Exception { player.resume(); }
    public void Stop() throws Exception { 
        player.stop(); 
        if(timerGlobal != null) timerGlobal.cancel();
    }

    public void cargarCancion(String mp3Nombre, String lrcNombre) throws Exception {
        if(timerGlobal != null) timerGlobal.cancel();
        timerGlobal = new Timer();

        String rutaLrc = "songs/" + lrcNombre;

        // 1. Parsing directo del archivo original (¡La nueva gramática ignora la basura!)
        Lexer lexer = new Lexer(new java.io.PushbackReader(new java.io.FileReader(rutaLrc), 1024));
        Parser parser = new Parser(lexer);
        Start tree = parser.parse();
        
        RepositorioLetras miRepositorio = new RepositorioLetras();
        VisitadorLetras visitador = new VisitadorLetras(miRepositorio);
        tree.apply(visitador);
        
        // 2. Carga Audio
        player.open(new java.io.File("songs/" + mp3Nombre));
        long delayAudio = 1500;
        // 3. Agendar eventos
        for (LineaLetra subtitulo : miRepositorio.getListaSubtitulos()) {
            new Reminder(subtitulo.getTiempo() + delayAudio, subtitulo.getTexto());
        }

    }

    public class Reminder {
        String texto;
        public Reminder(long ms, String texto) {
            this.texto = texto;
            timerGlobal.schedule(new RemindTask(), ms);
        }
        class RemindTask extends TimerTask {
            public void run() {
                if (gui != null) gui.mostrarLetra(texto);
            }
        }
    }
}