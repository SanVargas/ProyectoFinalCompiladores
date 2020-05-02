package co.edu.uniquindio.compiladores.sintactico

import co.edu.uniquindio.compiladores.lexico.Token
import javafx.scene.control.TreeItem
import java.util.ArrayList

class InvocacionFuncion(var punto: Token, var id:Token, var parIzq:Token, var argumentos: ArrayList<Argumento>, var parDer:Token, var finSentencia:Token):Sentencia() {

    override fun toString(): String {
        return "InvocacionFuncion(punto=$punto, id=$id, parIzq=$parIzq, argumentos=$argumentos, parDer=$parDer, finSentencia=$finSentencia)"
    }

    fun getArbolVisual(): TreeItem<String> {
        var raiz = TreeItem("Invocacion Funcion")
        raiz.children.add(TreeItem("Identificador:  ${id?.lexema}" ))

        var raizArgumento = TreeItem("Argumentos")
        for(p in argumentos){
            raizArgumento.children.add(p.getArbolVisual())
        }
        raiz.children.add(raizArgumento)

        return raiz
    }

}