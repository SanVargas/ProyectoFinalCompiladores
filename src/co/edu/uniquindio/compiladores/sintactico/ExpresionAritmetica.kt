package co.edu.uniquindio.compiladores.sintactico
import javafx.scene.control.TreeItem
/**
 * Clase encargada de crear una expresion aritmetica
 * @author Santiago Vargas - Sebastian Ceballos
 */
open class ExpresionAritmetica(var  eA:ExpresionAritmetica?, var vN:ValorNumerico?, var  eAux: ExpresionAritmeticaAuxiliar?): Expresion(){
    constructor(eA:ExpresionAritmetica?, eAux: ExpresionAritmeticaAuxiliar?):this(eA,null,eAux)
    constructor(vN: ValorNumerico?, eAux:ExpresionAritmeticaAuxiliar?):this(null,vN,eAux)

    override fun toString(): String {
        return "ExpresionAritmetica(Valor=$vN)"
    }

    override fun getArbolVisual(): TreeItem<String> {
        var raiz = TreeItem("Expresion Aritmetica")
        return raiz
    }


}