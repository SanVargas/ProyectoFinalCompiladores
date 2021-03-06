package co.edu.uniquindio.compiladores.sintactico

import co.edu.uniquindio.compiladores.lexico.ErrorLexico
import co.edu.uniquindio.compiladores.lexico.Token
import co.edu.uniquindio.compiladores.semantico.ErrorSemantico
import co.edu.uniquindio.compiladores.semantico.Simbolo
import co.edu.uniquindio.compiladores.semantico.TablaSimbolos
import javafx.scene.control.TreeItem
/**
 * Clase encargada de crear una impresion
 * @author Santiago Vargas - Sebastian Ceballos
 */
class Impresion(var palabraReser: Token?, var parIzq: Token?, var expresion: Expresion?, var parDer: Token?, var finSentencia: Token?) : Sentencia() {

    override fun toString(): String {
        return "Impresion(palabraReser=$palabraReser,parIzq=$parIzq,expresion=$expresion,parDer0$parDer,finSentencia=$finSentencia)"
    }

    override fun getArbolVisual(): TreeItem<String> {
        var raiz = TreeItem("Impresion")
        raiz.children.add(expresion?.getArbolVisual())
        return raiz
    }


    override fun analizarSemantica(
        tablaSimbolos: TablaSimbolos,
        erroresSemanticos: ArrayList<ErrorSemantico>,
        ambito: Simbolo
    ) {
        expresion!!.analizarSemantica(tablaSimbolos, erroresSemanticos, ambito)
    }

    override fun getJavaCode(): String {
        return palabraReser?.getJavaCode() +expresion?.getJavaCode()+");"
    }
}