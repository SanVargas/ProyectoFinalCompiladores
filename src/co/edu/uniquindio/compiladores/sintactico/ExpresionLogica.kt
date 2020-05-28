package co.edu.uniquindio.compiladores.sintactico

import co.edu.uniquindio.compiladores.lexico.ErrorLexico
import co.edu.uniquindio.compiladores.lexico.Token
import co.edu.uniquindio.compiladores.semantico.Simbolo
import co.edu.uniquindio.compiladores.semantico.TablaSimbolos
import javafx.scene.control.TreeItem

/**
 * Clase encargada de crear una expresion logica
 * @author Santiago Vargas - Sebastian Ceballos
 */
class ExpresionLogica() : Expresion() {
    var valorLogico: ExpresionRelacional? = null
    var operadorLogico: Token? = null
    var expresionLogica: ExpresionLogica? = null

    constructor(valorLogico: ExpresionRelacional?, operadorLogico: Token?, expresionLogica: ExpresionLogica?) : this() {
        this.valorLogico = valorLogico
        this.operadorLogico = operadorLogico
        this.expresionLogica = expresionLogica
    }

    constructor(valorLogico: ExpresionRelacional?) : this() {
        this.valorLogico = valorLogico
    }

    override fun toString(): String {
        return "Expresion Logica (valorLogico=$valorLogico, operadorLogico=$operadorLogico, expresionLogica=$expresionLogica)"
    }

    override fun getArbolVisual(): TreeItem<String> {
        var raiz = TreeItem("Expresion Logica")
        return raiz
    }

    override fun obtenerTipo(tablaSimbolos: TablaSimbolos, ambito:Simbolo): String {
        return "log"
    }

    override fun getJavaCode(): String {
        return super.getJavaCode()
    }

}