# Lab2-TeoriaCompu

Laboratorio 2 
Giancarlo Sagastume - 24278 


## Problema 1

Problema resuelto en el cuaderno.

<img width="1164" height="1440" alt="image" src="https://github.com/user-attachments/assets/c3094854-7c8a-48f4-8bfb-057692386d0a" />

<img width="1161" height="1447" alt="image" src="https://github.com/user-attachments/assets/c832e6d6-b9d0-4512-8b20-a8402f087704" />

<img width="1156" height="1347" alt="image" src="https://github.com/user-attachments/assets/66832d5a-d684-4876-a5c5-c5dce9e6c1e8" />

<img width="1138" height="1468" alt="image" src="https://github.com/user-attachments/assets/d7a0b74a-8445-4e05-a9e2-63476044074e" />

<img width="1180" height="1432" alt="image" src="https://github.com/user-attachments/assets/64d5ca1f-a117-4c0c-9055-a7d510467835" />


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
