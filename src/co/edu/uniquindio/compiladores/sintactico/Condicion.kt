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
    var finCodigo: Token?,
    var llaIzq: Token?,
    var sentencias: ArrayList<Sentencia>,
    var llaDer: Token?,
    var ademas: CondicionAdemas?

) : Sentencia() {


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


        ademas!!.llenarTablaSimbolos(tablaSimbolos, listaErrores, ambito)

        var ambitoCondicion: Simbolo = Simbolo(
            palabraReservada!!.lexema,
            null,
            obtenerIdentificador(),
            palabraReservada!!.fila,
            palabraReservada!!.columna
        )


        for (s in sentencias) {
            s.llenarTablaSimbolos(tablaSimbolos, listaErrores, ambitoCondicion)
        }


    }


    override fun analizarSemantica(
        tablaSimbolos: TablaSimbolos,
        erroresSemanticos: ArrayList<ErrorSemantico>,
        ambito: Simbolo
    ) {
        ademas!!.analizarSemantica(tablaSimbolos, erroresSemanticos, ambito)

        if (expresionLogica != null) {
            expresionLogica!!.analizarSemantica(tablaSimbolos, erroresSemanticos, ambito)
        } else {

            erroresSemanticos.add(
                ErrorSemantico(
                    "Hay un error con la expresion logica de la condicion en el ambito ${ambito.nombre}",
                    palabraReservada!!.fila,
                    palabraReservada!!.columna
                )
            )
        }

        var ambitoCondicion: Simbolo = Simbolo(
            palabraReservada!!.lexema,
            null,
            obtenerIdentificador(),
            palabraReservada!!.fila,
            palabraReservada!!.columna
        )

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

        lista.add("" + (palabraReservada!!.fila))
        lista.add("" + (palabraReservada!!.columna))
        return lista
    }

    override fun getJavaCode(): String {
        var codigo: String = "if (" + expresionLogica?.getJavaCode() + "){"
        for (s in sentencias) {
            codigo += s.getJavaCode()
        }
        codigo+="}else{"
        if(ademas!!.sentenciasAdemas.isNotEmpty())
        for(cA in ademas!!.sentenciasAdemas) {
            codigo += cA.getJavaCode()
        }
        codigo+="}"
        return codigo
    }

    override fun toString(): String {
        return "Condicion(palabraReservada=$palabraReservada, parIzq=$parIzq, expresionLogica=$expresionLogica, parDer=$parDer, finCodigo=$finCodigo, llaIzq=$llaIzq, sentencias=$sentencias, llaDer=$llaDer)"
    }


}
