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
        raiz.children.add(TreeItem("Expresion1: ${eA}"))
        raiz.children.add(TreeItem("Expresion2: ${eAux}"))
        raiz.children.add(TreeItem("Tipo: ${vN?.tipo?.lexema}"))
        return raiz
    }


}