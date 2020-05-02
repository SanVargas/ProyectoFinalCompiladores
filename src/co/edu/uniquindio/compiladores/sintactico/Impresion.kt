package co.edu.uniquindio.compiladores.sintactico

import co.edu.uniquindio.compiladores.lexico.Token
import javafx.scene.control.TreeItem
/**
 * Clase encargada de crear una impresion
 * @author Santiago Vargas - Sebastian Ceballos
 */
class Impresion(var palabraReser: Token?, var parIzq: Token?, var expresion: Expresion?, var parDer: Token?, var finSentencia: Token?) : Sentencia() {

    override fun toString(): String {
        return "Impresion(palabraReser=$palabraReser,parIzq=$parIzq,expresion=$expresion,parDer0$parDer,finSentencia=$finSentencia)"
    }

    override fun getArbolVisual(): TreeItem<String> {
        var raiz = TreeItem("Impresion")
        raiz.children.add(expresion?.getArbolVisual())
        return raiz
    }

}