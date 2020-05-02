package co.edu.uniquindio.compiladores.sintactico
import javafx.scene.control.TreeItem

/**
 * Clase que permite crear una expresion
 * @param nombre; nombre de tipo token
 */
open abstract class Expresion() {
    /**
     * Funcion toString de la clase Parametro
     */
  //  override fun toString(): String {
       // return "Expresion(nombre=$nombre)"
  //  }

    fun getArbolVisual(): TreeItem<String> {
        var raiz = TreeItem("Expresion")
        // raiz.children.add(TreeItem("Nombre:  ${nombre?.lexema}"))
        return raiz
    }

}