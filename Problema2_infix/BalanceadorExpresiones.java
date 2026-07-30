import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.Stack;

public class BalanceadorExpresiones {

    public static void main(String[] args) {

        String archivo = "expresiones.txt";

        try (BufferedReader br = new BufferedReader(new FileReader(archivo))) {

            String linea;
            int numeroLinea = 1;

            while ((linea = br.readLine()) != null) {

                System.out.println("===========================================");
                System.out.println("Expresion " + numeroLinea + ": " + linea);

                boolean balanceada = verificarBalance(linea);

                if (balanceada)
                    System.out.println("Resultado: EXPRESION BALANCEADA");
                else
                    System.out.println("Resultado: EXPRESION NO BALANCEADA");

                numeroLinea++;
                System.out.println();

            }

        } catch (IOException e) {
            System.out.println("Error al leer el archivo.");
        }

    }

    public static boolean verificarBalance(String expresion) {

        Stack<Character> pila = new Stack<>();

        for (int i = 0; i < expresion.length(); i++) {

            char c = expresion.charAt(i);

            // Si es símbolo de apertura
            if (c == '(' || c == '[' || c == '{') {

                pila.push(c);

                System.out.println(
                        "Paso " + (i + 1)
                                + " -> Encontro '" + c
                                + "'  PUSH"
                                + "   Pila: " + pila);

            }

            // Si es símbolo de cierre
            else if (c == ')' || c == ']' || c == '}') {

                System.out.println(
                        "Paso " + (i + 1)
                                + " -> Encontro '" + c + "'");

                if (pila.isEmpty()) {

                    System.out.println("   ERROR: La pila esta vacia.");
                    return false;

                }

                char tope = pila.pop();

                System.out.println(
                        "   POP '" + tope + "'"
                                + "   Pila: " + pila);

                if (!esPar(tope, c)) {

                    System.out.println("   ERROR: No corresponde el cierre.");
                    return false;

                }

            }

        }

        if (!pila.isEmpty()) {

            System.out.println("Quedaron simbolos sin cerrar: " + pila);

            return false;

        }

        return true;

    }

    public static boolean esPar(char apertura, char cierre) {

        return (apertura == '(' && cierre == ')')
                || (apertura == '[' && cierre == ']')
                || (apertura == '{' && cierre == '}');

    }

}