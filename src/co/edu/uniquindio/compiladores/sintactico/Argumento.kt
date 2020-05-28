package co.edu.uniquindio.compiladores.sintactico

import co.edu.uniquindio.compiladores.lexico.ErrorLexico
import co.edu.uniquindio.compiladores.semantico.ErrorSemantico
import co.edu.uniquindio.compiladores.semantico.Simbolo
import co.edu.uniquindio.compiladores.semantico.TablaSimbolos
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

    open fun obtenerTipo(tablaSimbolos: TablaSimbolos, ambito: Simbolo): String {
        return exp!!.obtenerTipo(tablaSimbolos, ambito)
    }

    open fun analizarSemantica(
        tablaSimbolos: TablaSimbolos,
        erroresSemanticos: ArrayList<ErrorSemantico>,
        ambito: Simbolo
    ) {
        return exp!!.analizarSemantica(tablaSimbolos, erroresSemanticos, ambito)

    }
    //fun getJavaCode(): String {
    //  return "" + exp.getJavaCode()
    //}
}