package co.edu.uniquindio.compiladores.sintactico
import co.edu.uniquindio.compiladores.lexico.Token

open class ExpresionLogica(var eR:ExpresionRelacional?,var eR1:ExpresionRelacional?,var operador:Token?): Expresion() {

    constructor(eR:ExpresionRelacional, operador:Token?):this(eR,null,operador)

    override fun toString(): String {
        return "ExpresionLogica(operador=$operador, eR=$eR, eR1=$eR1)"
    }
}