package co.edu.uniquindio.compiladores.sintactico
import co.edu.uniquindio.compiladores.lexico.Token
import javafx.scene.control.TreeItem

class LeerInverso(var palabraReservada:Token, var id:Token, var finSentencia:Token):Sentencia() {

    override fun toString(): String {
        return "LeerInverso(palabraReservada=$palabraReservada, id=$id,finSentencia=$finSentencia )"
    }

    override fun getArbolVisual(): TreeItem<String> {
        var raiz = TreeItem("Leer Inverso")
        raiz.children.add(TreeItem("Identificador:  ${id.lexema}"))
        return raiz
    }

}