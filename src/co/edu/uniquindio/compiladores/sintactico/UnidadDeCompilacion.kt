package co.edu.uniquindio.compiladores.sintactico

import javafx.scene.control.TreeItem


/**
 * Clase que permite crear la unidad de compilacion
 * @param listaFunciones; lista de funciones
 */
class UnidadDeCompilacion(var listaFunciones:ArrayList<Funcion>) {

    /**
     * Funcion toString de la clase unidad de compilacion
     */
    override fun toString(): String {
        return "UnidadDeCompilacion(listaFunciones=$listaFunciones)"
    }

    fun getArbolVisual(): TreeItem<String> {
        val raiz = TreeItem("Unidad de compilación")
        for (funcion in listaFunciones) {
            raiz.children.add(funcion.getArbolVisual())
        }
        return raiz
    }

}