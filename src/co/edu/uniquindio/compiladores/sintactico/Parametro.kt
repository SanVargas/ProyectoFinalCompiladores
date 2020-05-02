package co.edu.uniquindio.compiladores.sintactico
import co.edu.uniquindio.compiladores.lexico.Token
import javafx.scene.control.TreeItem

/**
 * Clase que permite crear un parametro
 * @param nombre; nombre de tipos token
 */
class Parametro(var tipoDato:Token, var nombre:Token) {
    /**
     * Funcion toString de la clase Parametro
     */
    override fun toString(): String {
        return "Parametro(tipoDato=$tipoDato,nombre=$nombre)"
    }
    fun getArbolVisual(): TreeItem<String> {
        return TreeItem("Tipo: ${tipoDato?.lexema} : Identificador:  ${nombre?.lexema}")
    }
}