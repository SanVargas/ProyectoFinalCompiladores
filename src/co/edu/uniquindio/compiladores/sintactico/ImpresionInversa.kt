package co.edu.uniquindio.compiladores.sintactico
import co.edu.uniquindio.compiladores.lexico.Token
import javafx.scene.control.TreeItem

/**
 * Clase encargada de crear una impresion de retorno
 * @author Santiago Vargas - Sebastian Ceballos
 */
class ImpresionInversa(var palabraReservada: Token, var parIzq:Token, var exp:Expresion?, var parDer:Token, var finSentencia:Token):Sentencia() {

    override fun toString(): String {
    return "ImpresionInversa(palabraReservada=$palabraReservada, parIzq=$parIzq, exp=$exp, parDer=$parDer, finSentencia=$finSentencia)"
    }

    override fun getArbolVisual(): TreeItem<String> {
        var raiz = TreeItem("Impresion Inversa")
        raiz.children.add(exp?.getArbolVisual())
        return raiz
    }

}