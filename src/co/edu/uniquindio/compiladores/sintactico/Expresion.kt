package co.edu.uniquindio.compiladores.sintactico
import javafx.scene.control.TreeItem
/**
 * Clase encargada de crear una expresion
 * @author Santiago Vargas - Sebastian Ceballos
 */
open abstract class Expresion() {

   open fun getArbolVisual(): TreeItem<String> {
        var raiz = TreeItem("Expresion")
        return raiz
    }

}