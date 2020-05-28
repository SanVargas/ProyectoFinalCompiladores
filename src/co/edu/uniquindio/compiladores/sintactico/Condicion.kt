package co.edu.uniquindio.compiladores.sintactico

import co.edu.uniquindio.compiladores.lexico.ErrorLexico
import co.edu.uniquindio.compiladores.lexico.Token
import co.edu.uniquindio.compiladores.semantico.ErrorSemantico
import co.edu.uniquindio.compiladores.semantico.Simbolo
import co.edu.uniquindio.compiladores.semantico.TablaSimbolos
import javafx.scene.control.TreeItem

/**
 * Clase encargada de crear una condicion
 * @author Santiago Vargas - Sebastian Ceballos
 */
class Condicion(
    var palabraReservada: Token?,
    var parIzq: Token?,
    var expresionLogica: ExpresionLogica?,
    var parDer: Token?,
    var llaIzq: Token?,
    var sentencias: ArrayList<Sentencia>,
    var llaDer: Token?
) : Sentencia() {

    override fun toString(): String {
        return "Condicion(palabraReservada=$palabraReservada,parIzq=$parIzq,expresionLogica=$expresionLogica, parDer=$parDer, llaIzq=$llaIzq" +
                " sentencias=$sentencias, llaDer=$llaDer)"
    }

    override fun getArbolVisual(): TreeItem<String> {
        var raiz = TreeItem("Condicion")
        var exp = TreeItem("Expresion")
        exp.children.add(expresionLogica?.getArbolVisual())
        raiz.children.add(exp)
        if (sentencias != null) {
            var raizSentencia = TreeItem("Sentencias")
            for (s in sentencias) {
                raizSentencia.children.add(s.getArbolVisual())
            }
            raiz.children.add(raizSentencia)
        } else {
            raiz.children.add(TreeItem("Sentencias : Sin Sentencias"))
        }
        return raiz
    }

    override fun llenarTablaSimbolos(
        tablaSimbolos: TablaSimbolos,
        listaErrores: ArrayList<ErrorSemantico>,
        ambito: Simbolo
    ) {

        var ambitoCondicion: Simbolo = Simbolo(palabraReservada!!.lexema,null,obtenerIdentificador(),palabraReservada!!.fila,palabraReservada!!.columna)

        for (s in sentencias) {
            s.llenarTablaSimbolos(tablaSimbolos, listaErrores, ambitoCondicion)
        }

        //hay que programar el else de la condicion
    }


    override fun analizarSemantica(
        tablaSimbolos: TablaSimbolos,
        erroresSemanticos: ArrayList<ErrorSemantico>,
        ambito: Simbolo
    ) {

        expresionLogica!!.analizarSemantica(tablaSimbolos, erroresSemanticos, ambito)
        var ambitoCondicion: Simbolo = Simbolo(palabraReservada!!.lexema,null,obtenerIdentificador(),palabraReservada!!.fila,palabraReservada!!.columna)

        for (s in sentencias) {
            s.analizarSemantica(tablaSimbolos, erroresSemanticos, ambitoCondicion)
        }

        if (sentencias != null) {
            for (s in sentencias) {
                s.analizarSemantica(tablaSimbolos, erroresSemanticos, ambitoCondicion)
            }
        }
    }


    fun obtenerIdentificador(): ArrayList<String> {
        var lista = ArrayList<String>()

        lista.add(""+(palabraReservada!!.fila))
        lista.add(""+(palabraReservada!!.columna))
        return lista
    }

}
