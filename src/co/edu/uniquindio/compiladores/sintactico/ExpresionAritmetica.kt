package co.edu.uniquindio.compiladores.sintactico
import co.edu.uniquindio.compiladores.sintactico.Expresion
import co.edu.uniquindio.compiladores.sintactico.ValorNumerico
import co.edu.uniquindio.compiladores.sintactico.ExpresionAritmeticaAuxiliar


open class ExpresionAritmetica(var  eA:ExpresionAritmetica? , var Vn:ValorNumerico?,var  eAux: ExpresionAritmeticaAuxiliar?): Expresion(){
    constructor(eA:ExpresionAritmetica?, eAux: ExpresionAritmeticaAuxiliar?):this(eA,null,eAux)
    constructor(vN: ValorNumerico?, eAux:ExpresionAritmeticaAuxiliar?):this(null,vN,eAux)
    override fun toString(): String {
        return "ExpresionAritmetica(eA=$eA, eAux=$eAux)"
    }
}