/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package utilidades;

/**
 *
 * @author Damian0
 */

import java.text.DecimalFormat;

public class pasar_a_letras {

    private final String[] unidades = {
        "", "uno", "dos", "tres", "cuatro", "cinco",
        "seis", "siete", "ocho", "nueve", "diez", "once",
        "doce", "trece", "catorce", "quince", "dieciséis",
        "diecisiete", "dieciocho", "diecinueve", "veinte"
    };

    private final String[] decenas = {
        "", "", "veinti", "treinta", "cuarenta", "cincuenta",
        "sesenta", "setenta", "ochenta", "noventa"
    };

    private final String[] centenas = {
        "", "ciento", "doscientos", "trescientos", "cuatrocientos",
        "quinientos", "seiscientos", "setecientos", "ochocientos", "novecientos"
    };

    public String convertir(int numero) {
        if (numero == 0) return "cero";
        if (numero == 100) return "cien";

        String letras = "";
        if (numero > 999) {
            int miles = numero / 1000;
            letras += (miles == 1 ? "mil" : convertir(miles) + " mil");
            numero %= 1000;
            if (numero > 0) letras += " ";
        }

        if (numero > 99) {
            letras += centenas[numero / 100];
            numero %= 100;
            if (numero > 0) letras += " ";
        }

        if (numero <= 20) {
            letras += unidades[numero];
        } else {
            letras += decenas[numero / 10];
            if (numero % 10 > 0) {
                if (numero >= 30) letras += " y ";
                letras += unidades[numero % 10];
            }
        }

        return letras.trim();
    }
}
