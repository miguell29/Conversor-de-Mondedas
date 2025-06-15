import Helpers.MonedaHelper;
import Service.ApiExchange;
import Ui.ConsoleUI;

import java.util.Locale;

public class ConverterApp {
    private final ConsoleUI consola;
    private final ApiExchange api;

    public ConverterApp(){
        this.api = new ApiExchange();
        this.consola = new ConsoleUI();
    }

    public void Init(){
        String[] opciones = {"Realizar Conversión", "Ayuda de Monedas", "Salir"};
        boolean continuar = true;

        while (continuar) {

            int seleccion = this.consola.mostrarMenu("Conversor de Modedas Alura", opciones);

            switch (seleccion) {
                case 1 -> realizarConversion();
                case 2 -> MonedaHelper.mostrarAyuda(consola);
                case 3 -> {
                    continuar = false;
                    System.out.println("saliendo...");
                    }
            }
        }
    }

    private void realizarConversion() {

        this.consola.mostrarTitulo("Realizar Conversion");

        String monedaOrigen = this.consola.leerCodigoMoneda("Introduce el código de la moneda de origen (ej. USD):");
        String monedaDestino =this.consola.leerCodigoMoneda("Introduce el código de la moneda de destino (ej. EUR):");
        Double montoOrigen = this.consola.leerDouble("Introduce la cantidad a convertir");

        System.out.println("\nConsultando tasas de cambio, por favor espera...");

        var data = api.GetData(monedaOrigen);
        if (data.error() == null){
            Double tasaDeCambio = data.conversionRates().get(monedaDestino);
            if (tasaDeCambio != null) {
                double montoDestino = montoOrigen * tasaDeCambio;
                String resultado = String.format(Locale.US, "%.2f %s equivale a %.2f %s",
                        montoOrigen, monedaOrigen, montoDestino, monedaDestino);
                this.consola.mostrarResultado(resultado);
            } else {
                System.out.println("ERROR --> El código de moneda de destino '" + monedaDestino + "' no fue encontrado en la respuesta.");
            }
        }else {
            this.consola.mostrarError("No se pudieron obtener las tasas de cambio");
            System.out.println("\ttype-error:" + data.error() + "\n\n") ;
        }
        this.consola.esperarParaContinuar();
    }

}
