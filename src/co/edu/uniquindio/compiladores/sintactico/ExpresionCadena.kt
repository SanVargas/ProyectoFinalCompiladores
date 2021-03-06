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
class ExpresionCadena(): Expresion() {
    var cadena: Token?=null
    var mas: Token?=null
    var expresion: Expresion?=null

    constructor(cadena: Token?, mas: Token?, expresion: Expresion?):this(){
        this.cadena=cadena
        this.mas=mas
        this.expresion=expresion
    }
    constructor(cadena:Token):this(){
        this.cadena=cadena
    }
    override fun toString(): String {
        return "ExpresionCadena(cadena=$cadena, mas=$mas, expresion=$expresion)"
    }

    override fun getArbolVisual(): TreeItem<String> {
        var raiz = TreeItem("Expresion Cadena")
        return raiz
    }
    override fun obtenerTipo(tablaSimbolos: TablaSimbolos, ambito: Simbolo): String {
        return "cadena"
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

    override fun getJavaCode(): String {
        //codigo += cadena?.lexema
        var codigo = cadena?.lexema?.replace("«", "\"")
        codigo = codigo?.substring(0, codigo.length - 1)

        if(expresion!=null){
            var codigo2 = expresion?.getJavaCode()?.replace("»", "\"")
            codigo2 = codigo2?.substring(0, codigo2.length - 1)
            return codigo+"\"+"+codigo2+"\""
        }
        return ""+codigo+"\""

    }
}