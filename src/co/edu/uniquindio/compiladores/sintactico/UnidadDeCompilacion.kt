package co.edu.uniquindio.compiladores.sintactico
import co.edu.uniquindio.compiladores.lexico.ErrorLexico
import co.edu.uniquindio.compiladores.semantico.ErrorSemantico
import co.edu.uniquindio.compiladores.semantico.Simbolo
import co.edu.uniquindio.compiladores.semantico.TablaSimbolos
import javafx.scene.control.TreeItem
/**
 * Clase encargada de crear la unidad de compilacion
 * @author Santiago Vargas - Sebastian Ceballos
 */
class UnidadDeCompilacion(var listaFunciones: ArrayList<Funcion>) {

    override fun toString(): String {
        return "UnidadDeCompilacion(listaFunciones=$listaFunciones)"
    }

    fun getArbolVisual(): TreeItem<String> {
        var raiz = TreeItem("Unidad de compilación")
        for (funcion in listaFunciones) {
            raiz.children.add(funcion.getArbolVisual())
        }
        return raiz
    }

    fun llenarTablaSimbolos(tablaSimbolos: TablaSimbolos, erroresSemanticos: ArrayList<ErrorSemantico>) {
        for (f in listaFunciones) {
            var ambitoFuncion : Simbolo = Simbolo("UnidadCompilacion",null,false,null,0,0)

            f.llenarTablaSimbolos(tablaSimbolos, erroresSemanticos,ambitoFuncion )
        }
    }
    fun analizarSemantica(tablaSimbolos: TablaSimbolos, erroresSemanticos: ArrayList<ErrorSemantico>) {
        for (f in listaFunciones) {
            f.analizarSemantica(tablaSimbolos, erroresSemanticos)
        }
    }

}