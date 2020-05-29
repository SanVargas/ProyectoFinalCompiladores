package co.edu.uniquindio.compiladores.sintactico

import co.edu.uniquindio.compiladores.lexico.ErrorLexico
import co.edu.uniquindio.compiladores.lexico.Token
import co.edu.uniquindio.compiladores.semantico.ErrorSemantico
import co.edu.uniquindio.compiladores.semantico.Simbolo
import co.edu.uniquindio.compiladores.semantico.TablaSimbolos
import javafx.scene.control.TreeItem
import kotlin.collections.ArrayList

/**
 * Clase encargada de crear un arreglo
 * @author Santiago Vargas - Sebastian Ceballos
 */
class Arreglo(var tipo: Token, var identificador: Token, var lstArgumentos: ArrayList<Argumento>) : Sentencia() {

    override fun toString(): String {
        return "Arreglo(tipo=$tipo,identificador=$identificador,lstArgumentos=$lstArgumentos)"
    }

    override fun getArbolVisual(): TreeItem<String> {
        var raiz = TreeItem("Arreglo")
        raiz.children.add(TreeItem("Tipo: ${tipo?.lexema}"))
        raiz.children.add(TreeItem("Identificador:  ${identificador?.lexema}"))

        var raizArgumento = TreeItem("Argumento")
        for (a in lstArgumentos) {
            raizArgumento.children.add(a.getArbolVisual())
        }
        raiz.children.add(raizArgumento)
        return raiz
    }

    override fun llenarTablaSimbolos(
        tablaSimbolos: TablaSimbolos,
        listaErrores: ArrayList<ErrorSemantico>,
        ambito: Simbolo
    ) {
        tablaSimbolos.guardarSimboloValor(
            identificador.lexema,
            tipo.lexema,
            ambito,
            identificador.fila,
            identificador.columna
        )
    }

    override fun analizarSemantica(
        tablaSimbolos: TablaSimbolos,
        erroresSemanticos: ArrayList<ErrorSemantico>,
        ambito: Simbolo
    ) {
        for (e in lstArgumentos) {

            e!!.analizarSemantica(tablaSimbolos, erroresSemanticos, ambito)
            var tipoDato = e.obtenerTipo(tablaSimbolos, ambito)


          if (tipoDato != tipo.lexema) {
                erroresSemanticos.add(
                    ErrorSemantico(
                        "El tipo de datos de la expresion ($tipoDato) no coincide con el tipo de dato del arreglo (${tipo.lexema}) ",
                        identificador.fila,
                        identificador.columna
                    )
                )
            }
        }
    }

    override fun getJavaCode(): String {
        var codigo = tipo.getJavaCode() + " " + identificador.lexema + " " + "= {"
        for (a in lstArgumentos) {
            codigo += a.getJavaCode() + ","
        }
        codigo = codigo.substring(0, codigo.length - 1)
        codigo += "};"
        return codigo
    }
}