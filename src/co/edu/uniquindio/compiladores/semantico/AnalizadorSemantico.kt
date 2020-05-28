package co.edu.uniquindio.compiladores.semantico

import co.edu.uniquindio.compiladores.lexico.ErrorLexico
import co.edu.uniquindio.compiladores.sintactico.UnidadDeCompilacion


/**
 * fun entero metodo ( decimal numero, entero numer) {}
 *
 */
class AnalizadorSemantico(var uc: UnidadDeCompilacion) {

    var erroresSemanticos: ArrayList<ErrorSemantico> = ArrayList()
    var tablaSimbolos: TablaSimbolos = TablaSimbolos(erroresSemanticos)

    fun llenarTablaSimbolos() {
        uc.llenarTablaSimbolos(tablaSimbolos, erroresSemanticos)
    }

    fun analizarSemantica() {
        uc.analizarSemantica(tablaSimbolos, erroresSemanticos)
    }



}