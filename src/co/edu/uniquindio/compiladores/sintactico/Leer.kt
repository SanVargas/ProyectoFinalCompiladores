package co.edu.uniquindio.compiladores.sintactico

import co.edu.uniquindio.compiladores.lexico.Token
import javafx.scene.control.TreeItem

class Leer(var palabraReservada:Token, var id:Token, var finSentencia:Token):Sentencia(){

    override fun toString(): String {
        return "Leer(palabraReservada=$palabraReservada, id=$id,finSentencia=$finSentencia )"
    }

    override fun getArbolVisual(): TreeItem<String> {
        var raiz = TreeItem("Leer")
        raiz.children.add(TreeItem("Identificador:  ${id.lexema}"))
        return raiz
    }

}