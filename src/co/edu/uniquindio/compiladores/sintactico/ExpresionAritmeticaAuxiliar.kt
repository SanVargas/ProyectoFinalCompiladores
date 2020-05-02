package co.edu.uniquindio.compiladores.sintactico
import co.edu.uniquindio.compiladores.lexico.Token
import javafx.scene.control.TreeItem
/**
 * Clase encargada de crear una expresion aritmetica auxiliar
 * @author Santiago Vargas - Sebastian Ceballos
 */
open class ExpresionAritmeticaAuxiliar(var  operador:Token , var  eA:ExpresionAritmetica?, var  eAux:ExpresionAritmeticaAuxiliar?):Expresion(){

    override fun toString(): String {
        return "ExpresionAritmeticaAuxiliar( eA=$eA, operador=$operador, eAux=$eAux)"
    }

    override fun getArbolVisual(): TreeItem<String> {
        var raiz = TreeItem("Expresion Aritmetica Auxiliar")
        raiz.children.add(TreeItem("Expresion1: ${eA}"))
        raiz.children.add(TreeItem("Expresion2: ${eAux}"))
        return raiz
    }

}