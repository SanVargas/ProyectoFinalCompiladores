package co.edu.uniquindio.compiladores.sintactico
import co.edu.uniquindio.compiladores.lexico.Token
import javafx.scene.control.TreeItem
/**
 * Clase encargada de crear una condicion
 * @author Santiago Vargas - Sebastian Ceballos
 */
class Condicion(var palabraReservada:Token?, var parIzq:Token?, var expresionLogica:ExpresionLogica?, var parDer:Token?, var llaIzq:Token?,
                 var sentencias:ArrayList<Sentencia>, var llaDer:Token?): Sentencia(){

    override fun toString(): String {
        return "Condicion(palabraReservada=$palabraReservada,parIzq=$parIzq,expresionLogica=$expresionLogica, parDer=$parDer, llaIzq=$llaIzq" +
                " sentencias=$sentencias, llaDer=$llaDer)"
    }

    override fun getArbolVisual(): TreeItem<String> {
        var raiz = TreeItem("Condicion")

        var raizSentencia = TreeItem("Sentencias")
        for(s in sentencias){
            raizSentencia.children.add(s.getArbolVisual())
        }
        raiz.children.add(raizSentencia)

        raiz.children.add(TreeItem("Expresion: ${expresionLogica}"))
        return raiz
    }

}
