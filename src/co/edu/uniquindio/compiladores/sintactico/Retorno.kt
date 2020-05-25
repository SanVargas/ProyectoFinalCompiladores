package co.edu.uniquindio.compiladores.sintactico

import co.edu.uniquindio.compiladores.lexico.ErrorLexico
import co.edu.uniquindio.compiladores.lexico.Token
import co.edu.uniquindio.compiladores.semantico.TablaSimbolos
import javafx.scene.control.TreeItem

/**
 *
 * fun entero metodo (entero numero){
 * retorno numero°
 * }
 * Clase encargada de crear un retorno
 * @author Santiago Vargas - Sebastian Ceballos
 */
class Retorno(var palabraReservada: Token, var expresion: Expresion?, var finSentencia: Token) : Sentencia() {

    override fun toString(): String {
        return "Retorno(palabraReservada=$palabraReservada,expresion=$expresion,finSentencia=$finSentencia)"
    }

    override fun getArbolVisual(): TreeItem<String> {
        var raiz = TreeItem("Retorno")
        raiz.children.add(expresion?.getArbolVisual())
        return raiz
    }


    override fun llenarTablaSimbolos(
        tablaSimbolos: TablaSimbolos,
        listaErrores: ArrayList<ErrorLexico>,
        ambito: String
    ) {
    }


    override fun analizarSemantica(
        tablaSimbolos: TablaSimbolos,
        erroresSemanticos: ArrayList<ErrorLexico>,
        ambito: String
    ) {
        expresion!!.analizarSemantica(tablaSimbolos, erroresSemanticos, ambito)
        /**
         * comprovar que el tipo de la expresion a retornar sea el mismo que el del metodo
         */
    }
}