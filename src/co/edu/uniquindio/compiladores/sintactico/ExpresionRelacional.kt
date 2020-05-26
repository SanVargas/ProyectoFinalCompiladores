package co.edu.uniquindio.compiladores.sintactico

import co.edu.uniquindio.compiladores.lexico.ErrorLexico
import co.edu.uniquindio.compiladores.lexico.Token
import co.edu.uniquindio.compiladores.semantico.ErrorSemantico
import co.edu.uniquindio.compiladores.semantico.Simbolo
import co.edu.uniquindio.compiladores.semantico.TablaSimbolos
import javafx.scene.control.TreeItem

/**
 * Clase que permite crear una expresion Cadena
 * @param ea; ExpresionAritmetica de tipo ExpresionAritmetica
 * @param operador; operador de tipo token
 * @param ea1; ExpresionAritmetica de tipo ExpresionAritmetica
 */
class ExpresionRelacional(var eA: ExpresionAritmetica?, var operador: Token?, var eA1: ExpresionAritmetica?) :
    Expresion() {

    constructor(operador: Token?) : this(null, operador, null)

    override fun toString(): String {
        return "ExpresionRelacional(eA=$eA,operador=$operador,eA1=$eA1)"
    }

    override fun getArbolVisual(): TreeItem<String> {
        var raiz = TreeItem("Expresion Relacional")
        return raiz
    }

    override fun obtenerTipo(
        tablaSimbolos: TablaSimbolos,
        ambito: Simbolo

    ): String {
        return "log"
    }

    override fun analizarSemantica(
        tablaSimbolos: TablaSimbolos,
        erroresSemanticos: ArrayList<ErrorSemantico>,
        ambito: Simbolo
    ) {
        if (eA != null) {
            eA!!.analizarSemantica(tablaSimbolos, erroresSemanticos, ambito)
        } else if (eA != null && eA1 != null) {
            eA!!.analizarSemantica(tablaSimbolos, erroresSemanticos, ambito)
            eA1!!.analizarSemantica(tablaSimbolos, erroresSemanticos, ambito)
        }
    }
}