package co.edu.uniquindio.compiladores.sintactico
import co.edu.uniquindio.compiladores.lexico.Token

open class ExpresionAritmeticaAuxiliar(var  operador:Token , var  eA:ExpresionAritmetica?, var  eAux:ExpresionAritmeticaAuxiliar?):Expresion(){

    override fun toString(): String {
        return "ExpresionAritmeticaAuxiliar(operador=$operador, eA=$eA, eAux=$eAux)"
    }
}