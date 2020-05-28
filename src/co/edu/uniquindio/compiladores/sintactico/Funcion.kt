package co.edu.uniquindio.compiladores.sintactico

import co.edu.uniquindio.compiladores.lexico.ErrorLexico
import co.edu.uniquindio.compiladores.lexico.Token
import co.edu.uniquindio.compiladores.semantico.ErrorSemantico
import co.edu.uniquindio.compiladores.semantico.Simbolo
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

    fun llenarTablaSimbolos(
        tablaSimbolos: TablaSimbolos,
        erroresSemanticos: ArrayList<ErrorSemantico>,
        ambito: Simbolo
    ) {
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

            var ambitoFuncion: Simbolo = Simbolo(
                identificador.lexema,
                tipoRetorno.lexema,
                obtenerTipoDeParametros(),
                identificador.fila,
                identificador.columna
            )
            tablaSimbolos.guardarSimboloValor(
                parametro.nombre.lexema,
                parametro.tipoDato.lexema,
                ambitoFuncion,
                parametro.nombre.fila,
                parametro.nombre.columna
            )
        }

        for (s in lstSentencias) {
            var ambitoFuncion: Simbolo = Simbolo(
                identificador.lexema,
                tipoRetorno.lexema,
                obtenerTipoDeParametros(),
                identificador.fila,
                identificador.columna
            )
            s.llenarTablaSimbolos(tablaSimbolos, erroresSemanticos, ambitoFuncion)
        }
    }


    fun analizarSemantica(tablaSimbolos: TablaSimbolos, erroresSemanticos: ArrayList<ErrorSemantico>) {
        for (s in lstSentencias) {
            var ambitoFuncion: Simbolo = Simbolo(
                identificador.lexema,
                tipoRetorno.lexema,
                obtenerTipoDeParametros(),
                identificador.fila,
                identificador.columna
            )
            s.analizarSemantica(tablaSimbolos, erroresSemanticos, ambitoFuncion)
        }

    }
    fun getJavaCode(): String {
        var codigo: String = ""
        if (identificador.lexema == "main") {
            codigo = "public static void main(String[] args){"
        } else {
            codigo = "static " + tipoRetorno.getJavaCode() + " " + identificador.lexema
            if(lstParametros.size == 0) {
                codigo+="( ){"
            }else {
                codigo += "("
                for (p in lstParametros) {
                    codigo += p.getJavaCode() + ","
                }
                codigo = codigo.substring(0, codigo.length - 1)
                codigo += "){"
            }
        }
        for (s in lstSentencias) {
            codigo += s.getJavaCode()
        }
        codigo += "}"
        return codigo
    }

}


