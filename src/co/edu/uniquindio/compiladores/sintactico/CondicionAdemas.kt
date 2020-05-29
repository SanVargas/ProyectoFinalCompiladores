package co.edu.uniquindio.compiladores.sintactico

import co.edu.uniquindio.compiladores.lexico.Token
import co.edu.uniquindio.compiladores.semantico.ErrorSemantico
import co.edu.uniquindio.compiladores.semantico.Simbolo
import co.edu.uniquindio.compiladores.semantico.TablaSimbolos

class CondicionAdemas(
    var ademas: Token?,
    var llaIzqAdemas: Token?,
    var sentenciasAdemas: ArrayList<Sentencia>,
    var llaDerAdemas: Token?
) : Sentencia() {

    override fun toString(): String {
        return "CondicionAdemas(ademas=$ademas, llaIzqAdemas=$llaIzqAdemas, sentenciasAdemas=$sentenciasAdemas, llaDerAdemas=$llaDerAdemas)"
    }

    override fun llenarTablaSimbolos(
        tablaSimbolos: TablaSimbolos,
        listaErrores: ArrayList<ErrorSemantico>,
        ambito: Simbolo
    ) {

        var ambitoCondicionAdemas: Simbolo = Simbolo(
            ademas!!.lexema,
            null,
            obtenerIdentificador(),
            ademas!!.fila,
            ademas!!.columna
        )

        for (s in sentenciasAdemas) {
            s.llenarTablaSimbolos(tablaSimbolos, listaErrores, ambitoCondicionAdemas)
        }

    }

    override fun analizarSemantica(
        tablaSimbolos: TablaSimbolos,
        erroresSemanticos: ArrayList<ErrorSemantico>,
        ambito: Simbolo
    ) {

        var ambitoCondicionAdemas: Simbolo = Simbolo(
            ademas!!.lexema,
            null,
            obtenerIdentificador(),
            ademas!!.fila,
            ademas!!.columna
        )

        for (s in sentenciasAdemas) {
            s.analizarSemantica(tablaSimbolos, erroresSemanticos, ambitoCondicionAdemas)
        }

        if (sentenciasAdemas != null) {
            for (s in sentenciasAdemas) {
                s.analizarSemantica(tablaSimbolos, erroresSemanticos, ambitoCondicionAdemas)
            }
        }
    }

    fun obtenerIdentificador(): ArrayList<String> {
        var lista = ArrayList<String>()

        lista.add("" + (ademas!!.fila))
        lista.add("" + (ademas!!.columna))
        return lista
    }

    override fun getJavaCode(): String {
        var codigo=""
       /** for(s in sentenciasAdemas) {
            codigo+=s.getJavaCode()
            return codigo
        }*/
        return codigo
    }
}