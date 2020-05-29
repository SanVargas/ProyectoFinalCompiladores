package co.edu.uniquindio.compiladores.sintactico
import co.edu.uniquindio.compiladores.lexico.ErrorLexico
import co.edu.uniquindio.compiladores.lexico.Token
import co.edu.uniquindio.compiladores.semantico.ErrorSemantico
import co.edu.uniquindio.compiladores.semantico.Simbolo
import co.edu.uniquindio.compiladores.semantico.TablaSimbolos
import javafx.scene.control.TreeItem

/**
 * Clase encargada de crear un ciclo
 * @author Santiago Vargas - Sebastian Ceballos
 */
class Ciclo(var palabraReservada:Token,  var parIzq:Token,  var expresionLogica:ExpresionLogica?,  var parDer:Token, var llaveIzq:Token, var lstSentencias:ArrayList<Sentencia>,  var llaveDer:Token):Sentencia(){

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
       // raiz.children.add(expresionLogica!!.getArbolVisual())
        return raiz
    }
    fun obtenerIdentificador(): ArrayList<String> {
        var lista = ArrayList<String>()

        lista.add(""+(palabraReservada!!.fila))
        lista.add(""+(palabraReservada!!.columna))
        return lista
    }

    /**
     *
     */
    override fun llenarTablaSimbolos(
        tablaSimbolos: TablaSimbolos,
        listaErrores: ArrayList<ErrorSemantico>,
        ambito: Simbolo
    ) {

        var ambitoCiclo: Simbolo = Simbolo(palabraReservada!!.lexema,null,obtenerIdentificador(),palabraReservada!!.fila,palabraReservada!!.columna)

        for (s in lstSentencias){
            s.llenarTablaSimbolos(tablaSimbolos, listaErrores,ambitoCiclo)
        }
    }

    override fun analizarSemantica(
        tablaSimbolos: TablaSimbolos,
        erroresSemanticos: ArrayList<ErrorSemantico>,
        ambito: Simbolo
    ) {
        if(expresionLogica !=null){
        expresionLogica!!.analizarSemantica(tablaSimbolos, erroresSemanticos, ambito)
        }else {

            erroresSemanticos.add(ErrorSemantico("Hay un error con la expresion logica de la condicion en el ambito ${ambito.nombre}",palabraReservada.fila,palabraReservada.columna))
        }

        for (s in lstSentencias){
            s.analizarSemantica(tablaSimbolos, erroresSemanticos,ambito)
        }
    }


    override fun getJavaCode(): String {
        var codigo: String = "while (" + expresionLogica!!.getJavaCode() + "){"
        for (s in lstSentencias) {
            codigo += s.getJavaCode()
        }
         codigo += "}"
        return codigo
    }
}