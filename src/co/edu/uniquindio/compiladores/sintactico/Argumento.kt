package co.edu.uniquindio.compiladores.sintactico

import javafx.scene.control.TreeItem

/**
 * Clase encargada de crear un argumento
 * @author Santiago Vargas - Sebastian Ceballos
 */
class Argumento(var exp: Expresion?) {

    override fun toString(): String {
        return "Argumento(exp=$exp)"
    }

    fun getArbolVisual(): TreeItem<String> {
        var raiz = TreeItem("Argumento")
        raiz.children.add(exp?.getArbolVisual())
        return raiz
    }

}