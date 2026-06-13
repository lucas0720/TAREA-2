package src;
//  Integrantes: Lucas Araya, Francisco Carvajal, Rocio Villalobos
import javax.swing.*;
import java.awt.*;
import java.io.File;

public class InterfazKaraoke extends JFrame {
    private Reproductor reproductor;
    private JTextArea areaLetra;
    private JComboBox<String> comboMp3;
    private JComboBox<String> comboLrc;
    private JButton btnToggle;

    // Control de estados: 0 = Detenido, 1 = Sonando, 2 = Pausado
    private int estadoReproduccion = 0; 

    public InterfazKaraoke() {
        try { UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName()); } catch (Exception e) {}

        reproductor = new Reproductor();
        reproductor.setGui(this);

        setTitle("Karaoke Minimalista");
        setSize(600, 500);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setLayout(new BorderLayout(10, 10));
        getContentPane().setBackground(Color.DARK_GRAY);

        // --- ZONA SUPERIOR: SELECTOR DE CANCIONES ---
        JPanel panelInputs = new JPanel(new FlowLayout(FlowLayout.CENTER, 15, 10));
        panelInputs.setBackground(Color.DARK_GRAY);
        
        comboMp3 = new JComboBox<>();
        comboMp3.setEditable(true);
        comboMp3.setPreferredSize(new Dimension(150, 25));
        // Si el usuario cambia de canción, resetea el botón al estado inicial
        comboMp3.addActionListener(e -> resetearReproductor()); 

        comboLrc = new JComboBox<>();
        comboLrc.setEditable(true);
        comboLrc.setPreferredSize(new Dimension(150, 25));
        comboLrc.addActionListener(e -> resetearReproductor());

        cargarArchivosDirectorio();

        JLabel lblMp3 = new JLabel("MP3:"); lblMp3.setForeground(Color.WHITE);
        JLabel lblLrc = new JLabel("LRC:"); lblLrc.setForeground(Color.WHITE);
        
        panelInputs.add(lblMp3); panelInputs.add(comboMp3);
        panelInputs.add(lblLrc); panelInputs.add(comboLrc);
        add(panelInputs, BorderLayout.NORTH);

        // --- ZONA CENTRAL: LETRA ---
        areaLetra = new JTextArea();
        areaLetra.setEditable(false);
        areaLetra.setBackground(new Color(20, 20, 20));
        areaLetra.setForeground(new Color(0, 255, 127));
        areaLetra.setFont(new Font("Consolas", Font.BOLD, 18));
        areaLetra.setMargin(new Insets(10, 10, 10, 10));
        add(new JScrollPane(areaLetra), BorderLayout.CENTER);

        // --- ZONA INFERIOR: BOTÓN PLAY/STOP ---
        JPanel panelBotones = new JPanel(new FlowLayout(FlowLayout.CENTER, 0, 15));
        panelBotones.setBackground(Color.DARK_GRAY);
        
        btnToggle = new JButton("Play");
        btnToggle.setFont(new Font("Arial", Font.BOLD, 22)); // Letra más grande
        btnToggle.setPreferredSize(new Dimension(250, 50)); // Botón ancho y protagonista

        btnToggle.addActionListener(e -> manejarBotonToggle());
        
        panelBotones.add(btnToggle);
        add(panelBotones, BorderLayout.SOUTH);
    }

    // El cerebro del botón único (Versión a prueba de balas: Play/Stop)
    private void manejarBotonToggle() {
        if (estadoReproduccion == 0) {
            // Estado 0: Cargar la canción desde cero
            estadoReproduccion = 1;
            btnToggle.setText("Stop"); // Detiene por completo
            areaLetra.setText("Cargando pista...\n\n");
            
            new Thread(() -> {
                try {
                    String mp3Elegido = (String) comboMp3.getSelectedItem();
                    String lrcElegido = (String) comboLrc.getSelectedItem();
                    reproductor.cargarCancion(mp3Elegido, lrcElegido);
                    reproductor.Play();
                } catch (Exception ex) {
                    areaLetra.append("Error al cargar. Verifica los nombres.\n");
                    resetearReproductor();
                }
            }).start();

        } else if (estadoReproduccion == 1) {
            // Estado 1: Está sonando -> Lo DETENEMOS y reiniciamos
            resetearReproductor();
        }
    }
    // Detiene todo y devuelve el botón a su estado original
    private void resetearReproductor() {
        estadoReproduccion = 0;
        if (btnToggle != null) btnToggle.setText("Play");
        try { reproductor.Stop(); } catch(Exception ex) {}
    }

    private void cargarArchivosDirectorio() {
        File carpeta = new File("songs/");
        if (carpeta.exists() && carpeta.isDirectory()) {
            for (File archivo : carpeta.listFiles()) {
                if (archivo.getName().toLowerCase().endsWith(".mp3")) comboMp3.addItem(archivo.getName());
                else if (archivo.getName().toLowerCase().endsWith(".lrc")) comboLrc.addItem(archivo.getName());
            }
        }
    }

    public void mostrarLetra(String linea) {
        SwingUtilities.invokeLater(() -> {
            areaLetra.append(linea + "\n");
            areaLetra.setCaretPosition(areaLetra.getDocument().getLength());
        });
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> new InterfazKaraoke().setVisible(true));
    }
}