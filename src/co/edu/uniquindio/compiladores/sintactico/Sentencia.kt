package co.edu.uniquindio.compiladores.sintactico
import javafx.scene.control.TreeItem
/**
 * Clase encargada de crear una sentencia
 * @author Santiago Vargas - Sebastian Ceballos
 */
open abstract class Sentencia() {

    open fun getArbolVisual(): TreeItem<String> {
        var raiz = TreeItem("Sentencia")
        return raiz
    }

}

