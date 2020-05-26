package co.edu.uniquindio.compiladores.sintactico
import co.edu.uniquindio.compiladores.lexico.ErrorLexico
import co.edu.uniquindio.compiladores.lexico.Token
import co.edu.uniquindio.compiladores.semantico.ErrorSemantico
import co.edu.uniquindio.compiladores.semantico.Simbolo
import co.edu.uniquindio.compiladores.semantico.TablaSimbolos
import javafx.scene.control.TreeItem
/**
 * Clase encargada de crear una expresion cadena
 * @author Santiago Vargas - Sebastian Ceballos
 */
class ExpresionCadena(var cadena: Token?, var mas: Token?, var expresion: Expresion?): Expresion() {
    constructor(cadena:Token):this(cadena,null,null){}

    override fun toString(): String {
        return "ExpresionCadena(cadena=$cadena, mas=$mas, expresion=$expresion)"
    }

    override fun getArbolVisual(): TreeItem<String> {
        var raiz = TreeItem("Expresion Cadena")
        return raiz
    }
    override fun obtenerTipo(tablaSimbolos: TablaSimbolos, ambito: Simbolo): String {
        return "cad"
    }

    override fun analizarSemantica(
        tablaSimbolos: TablaSimbolos,
        erroresSemanticos: ArrayList<ErrorSemantico>,
        ambito: Simbolo
    ) {
        if(expresion!= null){

            expresion!!.analizarSemantica(tablaSimbolos, erroresSemanticos, ambito)
        }
    }
}