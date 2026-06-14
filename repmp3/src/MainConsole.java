package src;

import java.util.Scanner;

public class MainConsole {
    public static void main(String[] args) {
        
        Scanner scanner = new Scanner(System.in);
        Reproductor reproductor = new Reproductor();

        System.out.println("=========================================");
        System.out.println("      KARAOKE POR CONSOLA - TAREA 2");
        System.out.println("=========================================");
        System.out.println("Instruccion: Asegurate de que los archivos esten en la carpeta 'songs/'");

        System.out.print("\nIngresa el nombre del archivo MP3 (ej: miPerroChocolo.mp3): ");
        String mp3Nombre = scanner.nextLine();

        System.out.print("Ingresa el nombre del archivo LRC (ej: letra.lrc): ");
        String lrcNombre = scanner.nextLine();

        try {
            System.out.println("\nCargando y analizando archivos...");
            reproductor.cargarCancion(mp3Nombre, lrcNombre);
            System.out.println("[OK] Analisis lexico y sintactico exitoso. Archivos cargados.");

            boolean salir = false;
            
            while (!salir) {
                System.out.println("\n-----------------------------------------------------------");
                System.out.println("Opciones: [1] Play  [2] Pausa  [3] Continuar  [4] Stop  [5] Salir");
                System.out.print(">>> Escribe tu opcion y presiona Enter: ");
                String opcion = scanner.nextLine();

                switch (opcion) {
                    case "1":
                        System.out.println("\n--- REPRODUCIENDO ---");
                        reproductor.Play();
                        break;
                    case "2":
                        System.out.println("\n--- PAUSADO ---");
                        reproductor.Pausa();
                        break;
                    case "3":
                        System.out.println("\n--- CONTINUANDO ---");
                        reproductor.Continuar();
                        break;
                    case "4":
                        System.out.println("\n--- STOP ---");
                        reproductor.Stop();
                        break;
                    case "5":
                        reproductor.Stop();
                        salir = true;
                        System.out.println("Saliendo del karaoke...");
                        break;
                    default:
                        System.out.println("Opcion invalida.");
                }
            }
        } catch (Exception e) {
            System.out.println("\n[ERROR] Hubo un problema al cargar los archivos.");
            System.out.println("Motivo: " + e.getMessage());
            System.out.println("Por favor revisa que los nombres esten bien escritos y existan en 'songs/'.");
        }
        
        scanner.close();
    }
}