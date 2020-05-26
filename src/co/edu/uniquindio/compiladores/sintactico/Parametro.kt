package co.edu.uniquindio.compiladores.sintactico
import co.edu.uniquindio.compiladores.lexico.Token
import javafx.scene.control.TreeItem
/**
 * Clase encargada de crear un parametro
 * @author Santiago Vargas - Sebastian Ceballos
 */
class Parametro(var tipoDato:Token, var nombre:Token) {

    override fun toString(): String {
        return "Parametro(tipoDato=$tipoDato,nombre=$nombre)"
    }

    fun getArbolVisual(): TreeItem<String> {
        return TreeItem("Tipo: ${tipoDato?.lexema} : Identificador:  ${nombre?.lexema}")
    }

    fun getJavaCode():String{
        return tipoDato.getJavaCode() +" "+nombre.lexema
    }
}