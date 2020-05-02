package co.edu.uniquindio.compiladores.sintactico
import co.edu.uniquindio.compiladores.lexico.Token
import javafx.scene.control.TreeItem
/**
 * Clase encargada de crear una funcion
 * @author Santiago Vargas - Sebastian Ceballos
 */
class Funcion(var palabraFun:Token?, var tipoRetorno:Token?, var identificador:Token?, var parIzq:Token?,
              var lstParametros:ArrayList<Parametro>, var parDer:Token?, var llaveIzq:Token?, var  lstSentencias:ArrayList<Sentencia>,
              var llaveDer:Token?) {

    override fun toString(): String {
        return "Funcion(palabraFun=$palabraFun, tipoRetrono=$tipoRetorno, identificador=$identificador, parIzq=$parIzq, " +
                "parametros=$lstParametros, parDer=$parDer, llaveIzq=$llaveIzq, bloqueSentencias=$lstSentencias, laveDer=$llaveDer)"
    }

    fun getArbolVisual(): TreeItem<String> {
        var raiz = TreeItem("Funcion")
        raiz.children.add(TreeItem("Identificador:  ${identificador?.lexema}" ))
        raiz.children.add(TreeItem("Retorno:  ${tipoRetorno?.lexema}" ))

        var raizParametro = TreeItem("Parametro")
        for(p in lstParametros){
            raizParametro.children.add(p.getArbolVisual())
        }

        var raizSentencia = TreeItem("Sentencias")
        for(s in lstSentencias){
            raizSentencia.children.add(s.getArbolVisual())
        }
        raiz.children.add(raizParametro)
        raiz.children.add(raizSentencia)
        return raiz
    }

}