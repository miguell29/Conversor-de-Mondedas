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
                case 2 -> System.out.println("mostrar ayuda");
                case 3 -> continuar = false;
            }
        }
    }

    private void realizarConversion() {
        this.consola.mostrarTitulo("Realizar Conversion");

        String monedaOrigen = this.consola.leerCodigoMoneda("Introduce el código de la moneda de origen (ej. USD):");
        String monedaDestino =this.consola.leerCodigoMoneda("Introduce el código de la moneda de destino (ej. EUR):");
        Double montoOrigen = this.consola.leerDouble("Introduce la cantidad a convertir");

        var data = api.GetData(monedaOrigen);
        if (data != null){
            Double valorCambio = data.conversionRates().get(monedaDestino);
            if (valorCambio != null) {
                double montoDestino = montoOrigen * valorCambio;
                String resultado = String.format(Locale.US, "%.2f %s equivale a %.2f %s",
                        montoOrigen, monedaOrigen, montoDestino, monedaDestino);
                System.out.println(resultado);
            } else {
                System.out.println("ERROR --> El código de moneda de destino '" + monedaDestino + "' no fue encontrado en la respuesta.");
            }
        }
    }

}
