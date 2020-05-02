package co.edu.uniquindio.compiladores.sintactico
import co.edu.uniquindio.compiladores.lexico.Token

/**
 * Clase que permite crear una expresion Cadena
 * @param ea; ExpresionAritmetica de tipo ExpresionAritmetica
 * @param operador; operador de tipo token
 * @param ea1; ExpresionAritmetica de tipo ExpresionAritmetica
 */
class ExpresionRelacional(var eA:ExpresionAritmetica?, var operador:Token?, var eA1:ExpresionAritmetica?):Expresion() {

    constructor(operador: Token?):this(null, operador ,null)

    override fun toString(): String {
        return "ExpresionRelacional(eA=$eA,operador=$operador,eA1=$eA1)"
    }
}