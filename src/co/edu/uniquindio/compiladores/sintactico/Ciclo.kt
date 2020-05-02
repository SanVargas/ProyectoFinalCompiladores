package co.edu.uniquindio.compiladores.sintactico
import co.edu.uniquindio.compiladores.lexico.Token
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

        raiz.children.add(TreeItem("Expresion: ${expresionLogica}"))
        return raiz
    }

}