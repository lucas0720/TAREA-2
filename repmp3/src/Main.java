package src;

import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        
        Scanner scanner = new Scanner(System.in);
        Reproductor reproductor = new Reproductor();

        System.out.println("=========================================");
        System.out.println("      KARAOKE POR CONSOLA - TAREA 2");
        System.out.println("=========================================");
        System.out.println("Instruccion: Asegurate de que los archivos esten en la carpeta 'songs/'");

        // Carga la primera canción al iniciar
        cargarNuevaCancion(scanner, reproductor);

        boolean salir = false;
        
        while (!salir) {
            System.out.println(" ----------------------------------------------------------- ");
            System.out.println(" Opciones: [1] Play  [2] Pausa  [3] Continuar  [4] Stop  [5] Cambiar Cancion  [6] Salir");
            System.out.print(" Escribe tu opcion y presiona Enter: ");
            String opcion = scanner.nextLine();

            try {
                switch (opcion) {
                    case "1":
                        System.out.println("--- REPRODUCIENDO ---");
                        reproductor.Play();
                        break;
                    case "2":
                        System.out.println("--- PAUSADO ---");
                        reproductor.Pausa();
                        break;
                    case "3":
                        System.out.println("--- CONTINUANDO ---");
                        reproductor.Continuar();
                        break;
                    case "4":
                        System.out.println("--- STOP ---");
                        reproductor.Stop();
                        break;
                    case "5":
                        // Si cambiamos de canción, primero detenemos la que está sonando
                        reproductor.Stop();
                        System.out.println("--- CAMBIANDO CANCION ---");
                        cargarNuevaCancion(scanner, reproductor);
                        break;
                    case "6":
                        reproductor.Stop();
                        salir = true;
                        System.out.println("Saliendo del karaoke...");
                        break;
                    default:
                        System.out.println("Opcion invalida.");
                }
            } catch (Exception e) {
                System.out.println("(ERROR) Ocurrio un problema en la reproduccion.");
                System.out.println("Motivo: " + e.getMessage());
            }
        }
        
        scanner.close();
    }

    // Nuevo método para reutilizar la carga de archivos
    private static void cargarNuevaCancion(Scanner scanner, Reproductor reproductor) {
        System.out.print(" Ingresa el nombre del archivo MP3 (ej: elperrochocolo.mp3): ");
        String mp3Nombre = scanner.nextLine();

        System.out.print("Ingresa el nombre del archivo LRC (ej: letra el perro chocolo.lrc): ");
        String lrcNombre = scanner.nextLine();

        try {
            System.out.println(" Cargando y analizando archivos ");
            reproductor.cargarCancion(mp3Nombre, lrcNombre);
            System.out.println("(OK) Analisis lexico y sintactico exitoso. Archivos cargados. ");
        } catch (Exception e) {
            System.out.println(" (ERROR) Hubo un problema al cargar los archivos. ");
            System.out.println(" Motivo: " + e.getMessage());
            System.out.println(" Por favor revisa que los nombres esten bien escritos y existan en 'songs/'. ");
        }
    }
}