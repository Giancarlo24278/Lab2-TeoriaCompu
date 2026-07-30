import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.Stack;

public class ShuntingYard {

    public static void main(String[] args) {

        String archivo = "expresiones.txt";

        try (BufferedReader br = new BufferedReader(new FileReader(archivo))) {

            String expresion;
            int numero = 1;

            while ((expresion = br.readLine()) != null) {

                if (expresion.trim().isEmpty())
                    continue;

                System.out.println("==================================================");
                System.out.println("EXPRESION " + numero);
                System.out.println("Infix Original : " + expresion);

                // Preprocesamiento
                String procesada = expandirOperadores(expresion);
                procesada = insertarConcatenacion(procesada);

                System.out.println("Procesada      : " + procesada);
                System.out.println();

                String postfix = convertirPostfix(procesada);

                System.out.println();
                System.out.println("--------------------------------------------");
                System.out.println("POSTFIX:");
                System.out.println(postfix);
                System.out.println("--------------------------------------------");
                System.out.println();

                numero++;
            }

        } catch (IOException e) {
            System.out.println("No fue posible leer el archivo.");
        }

    }

    //==========================================================
    // SHUNTING YARD
    //==========================================================

    public static String convertirPostfix(String expresion) {

        Stack<Character> pila = new Stack<>();
        StringBuilder salida = new StringBuilder();

        System.out.printf("%-12s %-20s %-20s%n",
                "TOKEN",
                "PILA",
                "SALIDA");

        System.out.println("-------------------------------------------------------------");

        for (int i = 0; i < expresion.length(); i++) {

            char c = expresion.charAt(i);

            // Caracter escapado
            if (c == '\\') {

                if (i + 1 < expresion.length()) {

                    salida.append(c);
                    salida.append(expresion.charAt(i + 1));

                    i++;

                    imprimirPaso(
                            "\\" + expresion.charAt(i),
                            pila,
                            salida.toString());

                    continue;
                }
            }

            // Operando
            if (esOperando(c)) {

                salida.append(c);

            }

            // (
            else if (c == '(') {

                pila.push(c);

            }

            // )
            else if (c == ')') {

                while (!pila.isEmpty() && pila.peek() != '(') {

                    salida.append(pila.pop());

                }

                if (!pila.isEmpty()) {

                    pila.pop();

                }

            }

            // Operador
            else {

                while (!pila.isEmpty()
                        && pila.peek() != '('
                        && prioridad(pila.peek()) >= prioridad(c)) {

                    salida.append(pila.pop());

                }

                pila.push(c);

            }

            imprimirPaso(
                    String.valueOf(c),
                    pila,
                    salida.toString());

        }

        while (!pila.isEmpty()) {

            salida.append(pila.pop());

        }

        return salida.toString();

    }

    //==========================================================
    // IMPRIME LOS PASOS
    //==========================================================

    public static void imprimirPaso(
            String token,
            Stack<Character> pila,
            String salida) {

        System.out.printf("%-12s %-20s %-20s%n",
                token,
                pila,
                salida);

    }

    //==========================================================
    // PRIORIDAD DE OPERADORES
    //==========================================================

    public static int prioridad(char c) {

        switch (c) {

            case '*':
            case '+':
            case '?':
                return 3;

            case '.':
                return 2;

            case '|':
                return 1;

            default:
                return 0;

        }

    }

    //==========================================================
    // OPERANDO
    //==========================================================

    public static boolean esOperando(char c) {

        return Character.isLetterOrDigit(c)
                || c == '_'
                || c == '['
                || c == ']';

    }
        //==========================================================
    // EXPANDE LOS OPERADORES + Y ?
    //==========================================================

    public static String expandirOperadores(String expresion) {

        StringBuilder resultado = new StringBuilder();

        for (int i = 0; i < expresion.length(); i++) {

            char c = expresion.charAt(i);

            // Mantener caracteres escapados juntos
            if (c == '\\') {

                resultado.append(c);

                if (i + 1 < expresion.length()) {
                    resultado.append(expresion.charAt(i + 1));
                    i++;
                }

                continue;
            }

            // Expansión de +
            if (c == '+') {

                if (resultado.length() > 0) {

                    char ultimo = resultado.charAt(resultado.length() - 1);

                    resultado.append(ultimo);
                    resultado.append('*');

                }

                continue;
            }

            // Expansión de ?
            if (c == '?') {

                if (resultado.length() > 0) {

                    char ultimo = resultado.charAt(resultado.length() - 1);

                    resultado.deleteCharAt(resultado.length() - 1);

                    resultado.append('(');
                    resultado.append(ultimo);
                    resultado.append('|');
                    resultado.append('ε');
                    resultado.append(')');

                }

                continue;
            }

            resultado.append(c);

        }

        return resultado.toString();

    }

    //==========================================================
    // INSERTA CONCATENACIÓN EXPLÍCITA
    //==========================================================

    public static String insertarConcatenacion(String expresion) {

        StringBuilder resultado = new StringBuilder();

        for (int i = 0; i < expresion.length(); i++) {

            char actual = expresion.charAt(i);

            resultado.append(actual);

            if (i == expresion.length() - 1)
                continue;

            char siguiente = expresion.charAt(i + 1);

            if (debeConcatenar(actual, siguiente)) {

                resultado.append('.');

            }

        }

        return resultado.toString();

    }

    //==========================================================
    // REGLAS DE CONCATENACIÓN
    //==========================================================

    public static boolean debeConcatenar(char a, char b) {

        boolean primero =
                esOperando(a)
                || a == ')'
                || a == '*'
                || a == '+'
                || a == '?';

        boolean segundo =
                esOperando(b)
                || b == '('
                || b == '\\';

        return primero && segundo;

    }
}