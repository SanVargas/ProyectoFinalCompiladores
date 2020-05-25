package co.edu.uniquindio.compiladores.sintactico

import co.edu.uniquindio.compiladores.lexico.ErrorLexico
import co.edu.uniquindio.compiladores.semantico.TablaSimbolos
import javafx.scene.control.TreeItem

/**
 * Clase encargada de crear una expresion
 * @author Santiago Vargas - Sebastian Ceballos
 */
open abstract class Expresion() {

    open fun getArbolVisual(): TreeItem<String>? {
        return null
    }
    open fun obtenerTipo(tablaSimbolos: TablaSimbolos,ambito:String): String {
        return ""
    }

    open fun analizarSemantica(tablaSimbolos: TablaSimbolos,erroresSemanticos: ArrayList<ErrorLexico>,ambito: String){
    }
}