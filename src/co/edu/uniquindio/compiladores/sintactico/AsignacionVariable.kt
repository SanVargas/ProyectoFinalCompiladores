package co.edu.uniquindio.compiladores.sintactico
import co.edu.uniquindio.compiladores.lexico.Token
import javafx.scene.control.TreeItem
/**
 * Clase encargada de crear un argumento
 * @author Santiago Vargas - Sebastian Ceballos
 */
class AsignacionVariable(var identificador: Token?, var opAsignacion:Token?, var termino:Termino?, var finSentencia:Token?):Sentencia() {

    override fun toString(): String {
        return "AsignacionVariable(identificador=$identificador,opAsignacion=$opAsignacion,termino=$termino,finSentencia=$finSentencia)"
    }

    override fun getArbolVisual(): TreeItem<String> {
        var raiz = TreeItem("Asignacion Variable")
        raiz.children.add(TreeItem("Identificador: ${identificador?.lexema}"))
        raiz.children.add(TreeItem("Termino: ${termino?.termino?.lexema}"))
        return raiz
    }

}