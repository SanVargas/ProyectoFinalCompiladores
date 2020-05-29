package co.edu.uniquindio.compiladores.sintactico

import co.edu.uniquindio.compiladores.lexico.ErrorLexico
import co.edu.uniquindio.compiladores.lexico.Token
import co.edu.uniquindio.compiladores.semantico.ErrorSemantico
import co.edu.uniquindio.compiladores.semantico.Simbolo
import co.edu.uniquindio.compiladores.semantico.TablaSimbolos
import javafx.scene.control.TreeItem


/**
 * Clase encargada de crear una lectura
 * @author Santiago Vargas - Sebastian Ceballos
 */
class Leer(var palabraReservada: Token, var id: Token, var finSentencia: Token) : Sentencia() {

    override fun toString(): String {
        return "Leer(palabraReservada=$palabraReservada, id=$id,finSentencia=$finSentencia )"
    }

    override fun getArbolVisual(): TreeItem<String> {
        var raiz = TreeItem("Leer")
        raiz.children.add(TreeItem("Identificador:  ${id.lexema}"))
        return raiz
    }

    override fun analizarSemantica(
        tablaSimbolos: TablaSimbolos,
        erroresSemanticos: ArrayList<ErrorSemantico>,
        ambito: Simbolo
    ) {
        var s = tablaSimbolos.buscarSimboloValor(id.lexema, ambito)
        if (s == null) {
            erroresSemanticos.add(
                ErrorSemantico(
                    "El campo ${id.lexema} no existe dentro del ambito ${ambito}",
                    id.fila,
                    id.columna
                )
            )
        }
    }

    override fun getJavaCode(): String {
        return "JOptionPane.showInputDialog(null,"+id.lexema+");"
    }


}