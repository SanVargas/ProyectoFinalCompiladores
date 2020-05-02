package co.edu.uniquindio.compiladores.sintactico
import co.edu.uniquindio.compiladores.lexico.Token
import javafx.scene.control.TreeItem
/**
 * Clase encargada de crear una declaracion de variable
 * @author Santiago Vargas - Sebastian Ceballos
 */
class DeclaracionVariable(var tipoDato: Token, var identificador:Token, var finSentencia:Token): Sentencia(){

    override fun toString(): String {
        return "DeclaracionVariable(tipoDato=$tipoDato, identificador=$identificador, finSentencia=$finSentencia)"
    }

    override fun getArbolVisual(): TreeItem<String> {
        var raiz = TreeItem("Declaracion Variable")
        raiz.children.add(TreeItem("Tipo: ${tipoDato.lexema}"))
        raiz.children.add(TreeItem("Identificador: ${identificador.lexema}"))
        return raiz
    }

}