package co.edu.uniquindio.compiladores.sintactico
import co.edu.uniquindio.compiladores.lexico.ErrorLexico
import co.edu.uniquindio.compiladores.lexico.Token
import co.edu.uniquindio.compiladores.semantico.ErrorSemantico
import co.edu.uniquindio.compiladores.semantico.Simbolo
import co.edu.uniquindio.compiladores.semantico.TablaSimbolos
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

    override fun analizarSemantica(
        tablaSimbolos: TablaSimbolos,
        erroresSemanticos: ArrayList<ErrorSemantico>,
        ambito: Simbolo
    ) {
        exp!!.analizarSemantica(tablaSimbolos, erroresSemanticos, ambito)
    }

    override fun getJavaCode(): String {
        var codigo=""
        var variable = exp?.getJavaCode()?.replace(";", "")
        codigo+=" String invertida =\"\";\n for (int i ="+ variable+".length()-1; i>=0; i--){\n" +
        "       invertida += "+variable+".charAt(i);\n}\nSystem.out.println(invertida);"
        return codigo
    }
}