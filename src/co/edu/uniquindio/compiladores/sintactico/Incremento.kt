package co.edu.uniquindio.compiladores.sintactico

import javafx.scene.control.TreeItem

class Incremento(): Sentencia() {

    override fun getArbolVisual(): TreeItem<String> {
        var raiz = TreeItem("Incremento Variable")
        return raiz
    }
}