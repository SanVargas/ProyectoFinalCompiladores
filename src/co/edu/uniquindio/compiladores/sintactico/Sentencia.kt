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
    fun getArbolVisual(): TreeItem<String> {

        var raiz = TreeItem("Funcion")
        raiz.children.add(TreeItem("Nombre: Funcion"))

        /** var raizExpresion = TreeItem("Expresion")
        for (p in lstExpresiones) {
        raizExpresion.children.add(p.getArbolVisual())
        }

        var raizSentencia = TreeItem("Sentencias")
        for (s in lstBloqueSentencias) {
        raizSentencia.children.add(s.getArbolVisual())
        }
        return raiz
        }
         */
        return raiz
    }
}

