package Helpers;

import Ui.ConsoleUI;

import java.util.Map;

public class MonedaHelper {

    private static final Map<String, String> MONEDAS_MUNDO = Map.of(
            "USD", "Dólar Estadounidense",
            "EUR", "Euro",
            "JPY", "Yen Japonés",
            "GBP", "Libra Esterlina",
            "CHF", "Franco Suizo",
            "CAD", "Dólar Canadiense",
            "AUD", "Dólar Australiano",
            "CNY", "Yuan Chino"
    );

    private static final Map<String, String> MONEDAS_LATAM = Map.of(
            "ARS", "Peso Argentino",
            "BOB", "Boliviano",
            "BRL", "Real Brasileño",
            "CLP", "Peso Chileno",
            "COP", "Peso Colombiano",
            "MXN", "Peso Mexicano",
            "PEN", "Sol Peruano",
            "UYU", "Peso Uruguayo",
            "VES", "Bolívar Venezolano"
    );

    public static void mostrarAyuda(ConsoleUI consola) {
        consola.mostrarTitulo("AYUDA DE CÓDIGOS DE MONEDA");
        consola.mostrarListaMonedas("Monedas Principales del Mundo:", MONEDAS_MUNDO);
        consola.mostrarListaMonedas("Monedas de América Latina:", MONEDAS_LATAM);
        consola.esperarParaContinuar();
    }
}
