package co.edu.uniquindio.compiladores.sintactico

import co.edu.uniquindio.compiladores.lexico.ErrorLexico
import co.edu.uniquindio.compiladores.lexico.Token
import co.edu.uniquindio.compiladores.semantico.ErrorSemantico
import co.edu.uniquindio.compiladores.semantico.Simbolo
import co.edu.uniquindio.compiladores.semantico.TablaSimbolos
import javafx.scene.control.TreeItem
import kotlin.collections.ArrayList


/**
 * Clase encargada de crear una Invocacion de funcion
 *
 * @author Santiago Vargas - Sebastian Ceballos
 */
class InvocacionFuncion(
    var punto: Token,
    var id: Token,
    var parIzq: Token,
    var argumentos: ArrayList<Argumento>,
    var parDer: Token,
    var finSentencia: Token
) : Sentencia() {

    override fun toString(): String {
        return "InvocacionFuncion(punto=$punto, id=$id, parIzq=$parIzq, argumentos=$argumentos, parDer=$parDer, finSentencia=$finSentencia)"
    }

    override fun getArbolVisual(): TreeItem<String> {
        var raiz = TreeItem("Invocacion Funcion")
        raiz.children.add(TreeItem("Identificador:  ${id?.lexema}"))

        var raizArgumento = TreeItem("Argumentos")
        for (p in argumentos) {
            raiz.children.add(p.getArbolVisual())
        }
        raiz.children.add(raizArgumento)

        return raiz
    }

    fun obtenerTiposArgumentos(tablaSimbolos: TablaSimbolos, ambito: Simbolo): ArrayList<String> {
        var listaAgumentos = ArrayList<String>()
        for (a in argumentos) {
            listaAgumentos.add(a.obtenerTipo(tablaSimbolos, ambito))
        }
        return listaAgumentos
    }

    override fun analizarSemantica(
        tablaSimbolos: TablaSimbolos,
        erroresSemanticos: ArrayList<ErrorSemantico>,
        ambito: Simbolo
    ) {

        var listaArgumentos = obtenerTiposArgumentos(tablaSimbolos, ambito)
        var s = tablaSimbolos.buscarSimboloFuncion(id.lexema, listaArgumentos)

        if (s == null) {

            erroresSemanticos.add(
                ErrorSemantico(
                    "La funcion ${id.lexema} (${listaArgumentos}) no exite",
                    id.fila,
                    id.columna
                )
            )

        }
    }

    override fun getJavaCode(): String {
        var codigo=""
        codigo+="."+id.lexema+"("
        if(argumentos.isNotEmpty()){
            for(a in argumentos) {
                codigo += a.getJavaCode()
            }
        }
        codigo+=");"
        return codigo
    }


}