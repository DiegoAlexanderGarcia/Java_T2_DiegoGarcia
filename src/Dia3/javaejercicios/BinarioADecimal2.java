package Dia3.javaejercicios;

import java.util.Scanner;

public class BinarioADecimal2 {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        System.out.print("Ingrese un número binario: ");
        String binario = sc.nextLine();
        
        int decimal = 0;
        int potencia = 0;
        
        for (int i = binario.length() - 1; i >= 0; i--) {
            char digito = binario.charAt(i);
            if (digito == '1') {
                decimal += Math.pow(2, potencia);
            }
            potencia++;
        }
        
        System.out.println("El número decimal equivalente es: " + decimal);
    }
}