package co.edu.uniquindio.compiladores.sintactico
import co.edu.uniquindio.compiladores.lexico.Token
import javafx.scene.control.TreeItem
/**
 * Clase encargada de crear un termino
 * @author Santiago Vargas - Sebastian Ceballos
 */
class Termino(var termino: Token){

    override fun toString(): String {
        return "Termino(termino=$termino)"
    }

    fun getArbolVisual(): TreeItem<String> {
        return TreeItem("Termino: ${termino?.lexema}")
    }

}