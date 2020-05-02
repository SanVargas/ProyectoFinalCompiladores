package co.edu.uniquindio.compiladores.sintactico
import co.edu.uniquindio.compiladores.lexico.Token
import javafx.scene.control.TreeItem

/**
 *
 * Clase que permite crear una funcion
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
        raiz.children.add(TreeItem("Nombre:${palabraFun?.lexema}"))
        raiz.children.add(TreeItem("Nombre:  ${tipoRetorno?.lexema}" ))
        var raizParametro = TreeItem("Parametro")
        for(p in lstParametros){
            raizParametro.children.add(p.getArbolVisual())
        }

        var raizSentencia = TreeItem("Sentencias")

        for(s in lstSentencias){
            // raizSentencia.children.add(s.getArbolVisual())
        }
        return raiz
    }




}