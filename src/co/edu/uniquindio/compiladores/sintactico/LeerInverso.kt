package co.edu.uniquindio.compiladores.sintactico
import co.edu.uniquindio.compiladores.lexico.ErrorLexico
import co.edu.uniquindio.compiladores.lexico.Token
import co.edu.uniquindio.compiladores.semantico.TablaSimbolos
import javafx.scene.control.TreeItem


/**
 * Clase encargada de crear leer inverso
 * @author Santiago Vargas - Sebastian Ceballos
 */
class LeerInverso(var palabraReservada:Token, var id:Token, var finSentencia:Token):Sentencia() {

    override fun toString(): String {
        return "LeerInverso(palabraReservada=$palabraReservada, id=$id,finSentencia=$finSentencia )"
    }

    override fun getArbolVisual(): TreeItem<String> {
        var raiz = TreeItem("Leer Inverso")
        raiz.children.add(TreeItem("Identificador:  ${id.lexema}"))
        return raiz
    }

    override fun analizarSemantica(
        tablaSimbolos: TablaSimbolos,
        erroresSemanticos: ArrayList<ErrorLexico>,
        ambito: String
    ) {
        var s = tablaSimbolos.buscarSimboloValor(id.lexema, ambito)
        if (s == null) {
            erroresSemanticos.add(
                ErrorLexico(
                    "El campo ${id.lexema} no exite dentro del ambito ${ambito}",
                    id.fila,
                    id.columna
                )
            )
        }

    }

}