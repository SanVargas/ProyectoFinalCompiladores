package co.edu.uniquindio.compiladores.sintactico
import co.edu.uniquindio.compiladores.lexico.ErrorLexico
import co.edu.uniquindio.compiladores.lexico.Token
import co.edu.uniquindio.compiladores.semantico.TablaSimbolos
import javafx.scene.control.TreeItem
import kotlin.collections.ArrayList

/**
 * Clase encargada de crear un arreglo
 * @author Santiago Vargas - Sebastian Ceballos
 */
class Arreglo(var tipo: Token, var identificador:Token, var lstArgumentos: ArrayList<Argumento>):Sentencia() {

    override fun toString(): String {
        return "Arreglo(tipo=$tipo,identificador=$identificador,lstArgumentos=$lstArgumentos)"
    }

   override fun getArbolVisual(): TreeItem<String> {
        var raiz = TreeItem("Arreglo")
        raiz.children.add(TreeItem("Tipo: ${tipo?.lexema}"))
        raiz.children.add(TreeItem("Identificador:  ${identificador?.lexema}" ))

        var raizArgumento = TreeItem("Argumento")
        for(a in lstArgumentos){
            raizArgumento.children.add(a.getArbolVisual())
        }
        raiz.children.add(raizArgumento)
        return raiz
    }

    override fun llenarTablaSimbolos(
        tablaSimbolos: TablaSimbolos,
        listaErrores: ArrayList<ErrorLexico>,
        ambito: String
    ) {
        tablaSimbolos.guardarSimboloValor(identificador.lexema,tipo.lexema,ambito,identificador.fila,identificador.columna)
    }

}