import Service.ApiExchange;

import java.util.Locale;
import java.util.Scanner;

public class ConverterApp {
private ApiExchange api;
private Scanner scanner;

    public ConverterApp(){
        this.api = new ApiExchange();
        this.scanner = new Scanner(System.in);
    }

    public void Init(){
        String[] opciones = {"Realizar Conversión", "Ayuda de Monedas", "Salir"};
        boolean continuar = true;
        while (continuar) {

            this.mostrarMenu("Conversor de Moneda Alura", opciones);
            int seleccion =Integer.parseInt(scanner.nextLine());

            switch (seleccion) {
                case 1 -> realizarConversion();
                case 2 -> System.out.println("mostrar ayuda");
                case 3 -> continuar = false;
            }
        }
    }

    private void realizarConversion() {
        String origen, destino;
        Double monto;
        System.out.println("\n--- " + "Realizar Conversion" + " ---\n");
        System.out.print("Introduce el codigo de la moneda de origen ");
        origen = scanner.nextLine().toUpperCase();
        System.out.print("Introduce el codigo de la moneda de destino");
        destino = scanner.nextLine().toUpperCase();
        System.out.print("Introduce la cantidad a convertir");
        monto = Double.parseDouble(scanner.nextLine());

        var data = api.GetData(origen);
        if (data != null){
            Double targetRate = data.conversionRates().get(destino);
            if (targetRate != null) {
                double convertedAmount = monto * targetRate;
                String resultado = String.format(Locale.US, "%.2f %s equivale a %.2f %s",
                        monto, origen, convertedAmount, destino);
                System.out.println(resultado);
            } else {
                System.out.println("ERROR --> El código de moneda de destino '" + destino + "' no fue encontrado en la respuesta.");
            }
        }
    }

    public void mostrarMenu(String titulo, String[] opciones){
        System.out.println("=========================================");
        System.out.println("     " + titulo);
        System.out.println("=========================================");
        for (int i = 0; i < opciones.length; i++) {
            System.out.printf("%d. %s\n", i + 1, opciones[i]);
        }
        System.out.println("-----------------------------------------");
    }
}
