package co.edu.uniquindio.compiladores.sintactico

import co.edu.uniquindio.compiladores.lexico.Token
import javafx.scene.control.TreeItem

class ValorNumerico( var  signo:Token?, var  tipo:Token?) {

    override fun toString(): String {
        return "ValorNumerico(signo=$signo, tipo=$tipo)"
    }

    fun getArbolVisual(): TreeItem<String> {
        return TreeItem("Tipo: ${tipo?.lexema} : Signo:  ${signo?.lexema} ")
    }

}