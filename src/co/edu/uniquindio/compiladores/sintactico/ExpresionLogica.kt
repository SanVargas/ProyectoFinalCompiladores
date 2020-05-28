package co.edu.uniquindio.compiladores.sintactico

import co.edu.uniquindio.compiladores.lexico.Token
import co.edu.uniquindio.compiladores.semantico.ErrorSemantico
import co.edu.uniquindio.compiladores.semantico.Simbolo
import co.edu.uniquindio.compiladores.semantico.TablaSimbolos
import javafx.scene.control.TreeItem

/**
 * Clase encargada de crear una expresion logica
 * @author Santiago Vargas - Sebastian Ceballos
 */
class ExpresionLogica() : Expresion() {
    var expresionRelacional: ExpresionRelacional? = null
    var operadorLogico: Token? = null
    var expresionLogica: ExpresionLogica? = null

    constructor(valorLogico: ExpresionRelacional?, operadorLogico: Token?, expresionLogica: ExpresionLogica?) : this() {
        this.expresionRelacional = valorLogico
        this.operadorLogico = operadorLogico
        this.expresionLogica = expresionLogica
    }

    constructor(valorLogico: ExpresionRelacional?) : this() {
        this.expresionRelacional = valorLogico
    }

    override fun toString(): String {
        return "Expresion Logica (valorLogico=$expresionRelacional, operadorLogico=$operadorLogico, expresionLogica=$expresionLogica)"
    }

    override fun getArbolVisual(): TreeItem<String> {
        var raiz = TreeItem("Expresion Logica")
        return raiz
    }

    override fun obtenerTipo(tablaSimbolos: TablaSimbolos, ambito:Simbolo): String {
        return "log"
    }



    override fun analizarSemantica(
        tablaSimbolos: TablaSimbolos,
        erroresSemanticos: ArrayList<ErrorSemantico>,
        ambito: Simbolo
    ) {
        if(expresionRelacional != null && expresionLogica == null){
            expresionRelacional!!.analizarSemantica(tablaSimbolos, erroresSemanticos, ambito)
        }else if (expresionRelacional!= null &&expresionLogica!= null){
            expresionRelacional!!.analizarSemantica(tablaSimbolos, erroresSemanticos, ambito)
            expresionLogica!!.analizarSemantica(tablaSimbolos, erroresSemanticos, ambito)
        }
    }

    /**
     * LOGICA
    relacional opLogico(&& ||) logica
    relacional opLogico(!) logica
    relacional
     */
    override fun getJavaCode(): String {
        if(expresionRelacional!=null && expresionLogica!=null) {
            return expresionRelacional?.getJavaCode() + operadorLogico?.lexema + expresionLogica?.getJavaCode()
        }
        return ""+expresionRelacional?.getJavaCode()
    }


}