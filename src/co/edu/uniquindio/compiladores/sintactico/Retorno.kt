package co.edu.uniquindio.compiladores.sintactico
import co.edu.uniquindio.compiladores.lexico.Token
import javafx.scene.control.TreeItem

class Retorno(var palabraReservada:Token, var expresion:Expresion?, var finSentencia:Token):Sentencia() {

    override fun toString(): String {
        return "Retorno(palabraReservada=$palabraReservada,expresion=$expresion,finSentencia=$finSentencia)"
    }

    override fun getArbolVisual(): TreeItem<String> {
        var raiz = TreeItem("Retorno")
        raiz.children.add(expresion?.getArbolVisual())
        return raiz
    }

}