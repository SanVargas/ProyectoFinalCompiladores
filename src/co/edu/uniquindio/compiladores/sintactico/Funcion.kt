package co.edu.uniquindio.compiladores.sintactico

import co.edu.uniquindio.compiladores.lexico.ErrorLexico
import co.edu.uniquindio.compiladores.lexico.Token
import co.edu.uniquindio.compiladores.semantico.TablaSimbolos
import javafx.scene.control.TreeItem

/**
 * Clase encargada de crear una funcion
 * @author Santiago Vargas - Sebastian Ceballos
 */
class Funcion(
    var palabraFun: Token?,
    var tipoRetorno: Token,
    var identificador: Token,
    var parIzq: Token?,
    var lstParametros: ArrayList<Parametro>,
    var parDer: Token?,
    var llaveIzq: Token?,
    var lstSentencias: ArrayList<Sentencia>,
    var llaveDer: Token?
) {

    override fun toString(): String {
        return "Funcion(palabraFun=$palabraFun, tipoRetrono=$tipoRetorno, identificador=$identificador, parIzq=$parIzq, " +
                "parametros=$lstParametros, parDer=$parDer, llaveIzq=$llaveIzq, bloqueSentencias=$lstSentencias, laveDer=$llaveDer)"
    }

    fun getArbolVisual(): TreeItem<String> {
        var raiz = TreeItem("Funcion")
        raiz.children.add(TreeItem("Identificador:  ${identificador?.lexema}"))
        raiz.children.add(TreeItem("Retorno:  ${tipoRetorno?.lexema}"))

        var raizParametro = TreeItem("Parametro")
        for (p in lstParametros) {
            raizParametro.children.add(p.getArbolVisual())
        }

        var raizSentencia = TreeItem("Sentencias")
        for (s in lstSentencias) {
            raizSentencia.children.add(s.getArbolVisual())
        }
        raiz.children.add(raizParametro)
        raiz.children.add(raizSentencia)
        return raiz
    }

    fun obtenerTipoDeParametros(): ArrayList<String> {
        var lista = ArrayList<String>()
        for (p in lstParametros) {
            lista.add(p.tipoDato.lexema)
        }
        return lista
    }

    fun llenarTablaSimbolos(tablaSimbolos: TablaSimbolos, erroresSemanticos: ArrayList<ErrorLexico>, ambito: String) {
        if (tipoRetorno != null) {
            tablaSimbolos.guardarSimboloFuncion(
                identificador.lexema,
                tipoRetorno.lexema,
                obtenerTipoDeParametros(),
                ambito,
                identificador.fila,
                identificador.columna
            )
        } else {
            tablaSimbolos.guardarSimboloFuncion(
                identificador.lexema,
                null,
                obtenerTipoDeParametros(),
                ambito,
                identificador.fila,
                identificador.columna
            )
        }

        for (parametro in lstParametros) {
            tablaSimbolos.guardarSimboloValor(
                parametro.nombre.lexema,
                parametro.tipoDato.lexema,
                identificador.lexema,
                parametro.nombre.fila,
                parametro.nombre.columna
            )
        }

        for (s in lstSentencias) {
            s.llenarTablaSimbolos(tablaSimbolos, erroresSemanticos, identificador.lexema)
        }
    }

    /**
     * fun entero metodo (){
    entero numero°
    numero=9°
    }
     */
    fun analizarSemantica(tablaSimbolos: TablaSimbolos, erroresSemanticos: ArrayList<ErrorLexico>) {
        for (s in lstSentencias) {
            s.analizarSemantica(tablaSimbolos, erroresSemanticos, identificador.lexema)
        }

    }
}


