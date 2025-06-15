package Ui;

import java.util.InputMismatchException;
import java.util.Scanner;

public class ConsoleUI {

    private final Scanner scanner;

    public ConsoleUI(){
        this.scanner = new Scanner(System.in);
    }



    public int mostrarMenu(String titulo, String[] opciones) {
        System.out.println("=========================================");
        System.out.println("     " + titulo);
        System.out.println("=========================================");
        for (int i = 0; i < opciones.length; i++) {
            System.out.printf("%d. %s\n", i + 1, opciones[i]);
        }
        System.out.println("-----------------------------------------");

        int seleccion = -1;
        while (seleccion < 1 || seleccion > opciones.length) {
            System.out.print("Elige una opción: ");
            try {
                seleccion = scanner.nextInt();
                if (seleccion < 1 || seleccion > opciones.length) {
                    System.out.println("Opción no válida. Inténtalo de nuevo.");
                }
            } catch (InputMismatchException e) {
                System.out.println("Entrada inválida. Por favor, introduce un número.");
                scanner.next(); // Limpiar el buffer del scanner
            }
        }
        scanner.nextLine(); // Consumir el salto de línea pendiente
        return seleccion;
    }

    public void mostrarTitulo(String titulo) {
        System.out.println("\n--- " + titulo + " ---\n");
    }

    public String leerCodigoMoneda(String prompt) {
        System.out.print(prompt + " ");
        return scanner.nextLine().toUpperCase();
    }

    public double leerDouble(String prompt) {
        while (true) {
            try {
                System.out.print(prompt + " ");
                double valor = scanner.nextDouble();
                scanner.nextLine();
                return valor;
            } catch (InputMismatchException e) {
                System.out.println("Entrada inválida. Introduce un valor numérico.");
                scanner.next();
            }
        }
    }

}

