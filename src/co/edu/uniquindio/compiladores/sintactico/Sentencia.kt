package co.edu.uniquindio.compiladores.sintactico

import co.edu.uniquindio.compiladores.lexico.ErrorLexico
import co.edu.uniquindio.compiladores.semantico.ErrorSemantico
import co.edu.uniquindio.compiladores.semantico.Simbolo
import co.edu.uniquindio.compiladores.semantico.TablaSimbolos
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

    open fun llenarTablaSimbolos(tablaSimbolos: TablaSimbolos, listaErrores: ArrayList<ErrorSemantico>, ambito: Simbolo) {}

    open fun analizarSemantica(tablaSimbolos: TablaSimbolos, erroresSemanticos: ArrayList<ErrorSemantico>, ambito: Simbolo) {}
}

