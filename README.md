# Lab2-TeoriaCompu

Laboratorio 2 
Giancarlo Sagastume - 24278 


## Problema 1

Problema resuelto en el cuaderno.

---

## Problema 2

Implementación en Java de un algoritmo para verificar el balanceo de expresiones regulares utilizando una pila (`Stack`).

El programa:

- Lee un archivo de texto con una expresión por línea.
- Verifica si los símbolos `()`, `[]` y `{}` están correctamente balanceados.
- Muestra paso a paso las operaciones realizadas sobre la pila.
- Indica si cada expresión está balanceada o no.

### Video

https://youtu.be/0pGZDKimtZo

---

## Problema 3

### Explicación

El algoritmo **Shunting Yard**, propuesto por Edsger Dijkstra, permite convertir expresiones escritas en notación infix a notación postfix utilizando una pila.

El algoritmo procesa la expresión de izquierda a derecha. Los operandos se envían directamente a la salida, mientras que los operadores se almacenan temporalmente en una pila respetando su precedencia y asociatividad. Cuando un operador de menor precedencia aparece, se desapilan los operadores correspondientes hasta mantener el orden correcto. Los paréntesis controlan el inicio y fin de cada subexpresión.

En esta implementación también se consideran operadores propios de expresiones regulares como `*`, `+`, `?`, `|`, el operador de concatenación y el manejo de caracteres escapados mediante `\`.

La salida del programa muestra:

- La expresión original.
- La expresión convertida a formato postfix.
- El estado de la pila y la salida parcial en cada paso de la conversión.

### Video

https://youtu.be/0pGZDKimtZo (Es el mismo video que el priemro)
