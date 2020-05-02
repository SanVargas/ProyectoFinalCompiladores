package co.edu.uniquindio.compiladores.sintactico
import co.edu.uniquindio.compiladores.lexico.Token
import co.edu.uniquindio.compiladores.sintactico.Expresion
import javafx.scene.control.TreeItem

/**
 * Clase que permite crear una sentencia
 * @param nombre; nombre de la sentencia
 */
open abstract class Sentencia() {

    /**
    override fun toString(): String {
    return "Sentencia(nombre=$nombre, lstExpresiones=$lstExpresiones, lstBloqueSentencias=$lstBloqueSentencias)"
    }**/
    open fun getArbolVisual(): TreeItem<String> {
        var raiz = TreeItem("Sentencia")
        return raiz
    }

}

