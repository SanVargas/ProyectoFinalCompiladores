package co.edu.uniquindio.compiladores.sintactico
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

}