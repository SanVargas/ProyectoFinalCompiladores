package co.edu.uniquindio.compiladores.sintactico
import co.edu.uniquindio.compiladores.lexico.ErrorLexico
import co.edu.uniquindio.compiladores.lexico.Token
import co.edu.uniquindio.compiladores.semantico.TablaSimbolos
import javafx.scene.control.TreeItem

/**
 * Clase encargada de crear un ciclo
 * @author Santiago Vargas - Sebastian Ceballos
 */
class Ciclo(var palabraReservada:Token,  var parIzq:Token,  var expresionLogica:ExpresionLogica,  var parDer:Token, var llaveIzq:Token, var lstSentencias:ArrayList<Sentencia>,  var llaveDer:Token):Sentencia(){

    override fun toString(): String {
        return "Ciclo(palabraReservada=$palabraReservada, parIzq=$parIzq, expresionLogica=$expresionLogica, parDer=$parDer, llaveIzq=$llaveIzq, lstSentencias=$lstSentencias, laveDer=$llaveDer)"
    }

    override fun getArbolVisual(): TreeItem<String> {
        var raiz = TreeItem("Ciclo")

        var raizSentencia = TreeItem("Sentencias")
        for(s in lstSentencias){
            raizSentencia.children.add(s.getArbolVisual())
        }
        raiz.children.add(raizSentencia)

        raiz.children.add(expresionLogica.getArbolVisual())
        return raiz
    }

    /**
     *
     */
    override fun llenarTablaSimbolos(
        tablaSimbolos: TablaSimbolos,
        listaErrores: ArrayList<ErrorLexico>,
        ambito: String
    ) {

        for (s in lstSentencias){
            s.llenarTablaSimbolos(tablaSimbolos, listaErrores,ambito)
        }
    }

    override fun analizarSemantica(
        tablaSimbolos: TablaSimbolos,
        erroresSemanticos: ArrayList<ErrorLexico>,
        ambito: String
    ) {
        expresionLogica.analizarSemantica(tablaSimbolos, erroresSemanticos, ambito)
        for (s in lstSentencias){
            s.analizarSemantica(tablaSimbolos, erroresSemanticos,ambito)
        }
    }

}