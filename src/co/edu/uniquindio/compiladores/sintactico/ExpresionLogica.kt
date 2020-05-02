package co.edu.uniquindio.compiladores.sintactico
import co.edu.uniquindio.compiladores.lexico.Token
import javafx.scene.control.TreeItem
/**
 * Clase encargada de crear una expresion logica
 * @author Santiago Vargas - Sebastian Ceballos
 */
open class ExpresionLogica(var eR:ExpresionRelacional?,var eR1:ExpresionRelacional?,var operador:Token?): Expresion() {

    constructor(eR:ExpresionRelacional, operador:Token?):this(eR,null,operador)

    override fun toString(): String {
        return "ExpresionLogica(operador=$operador, eR=$eR, eR1=$eR1)"
    }

    override fun getArbolVisual(): TreeItem<String> {
        var raiz = TreeItem("Expresion Logica")
        return raiz
    }

}