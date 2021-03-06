package co.edu.uniquindio.compiladores.lexico

/**
 * Clase Enum la cual contiene todas las categorias lexicas del analizador
 */
enum class Categoria {
    ENTERO,DECIMAL,IDENTIFICADOR_VARIABLE,PALABRA_RESERVADA,OPERADOR_ARITMETICO,OPERADOR_ASIGNACION,OPERADOR_INCREMENTO,
    OPERADOR_DECREMENTO,CARACTER,CADENA_CARACTERES,SEPARADOR,PUNTO,DOS_PUNTOS,OPERADOR_RELACIONAL,OPERADOR_LOGICO,DESCONOCIDO,
    COMENTARIO_BLOQUE,COMENTARIO_LINEA,ERROR,REAL,FIN_CODIGO,
    LLAVE_IZQUIERDA,LLAVE_DERECHA,CORCHETE_DERECHO,CORCHETE_IZQUIERDO,PARENTESIS_DERECHO,PARENTESIS_IZQUIERDO,FIN_SENTENCIA
}