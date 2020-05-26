package co.edu.uniquindio.compiladores.sintactico

import co.edu.uniquindio.compiladores.lexico.ErrorLexico
import co.edu.uniquindio.compiladores.lexico.Token
import co.edu.uniquindio.compiladores.semantico.ErrorSemantico
import co.edu.uniquindio.compiladores.semantico.Simbolo
import co.edu.uniquindio.compiladores.semantico.TablaSimbolos
import javafx.scene.control.TreeItem

/**
 * Clase encargada de crear un argumento
 * @author Santiago Vargas - Sebastian Ceballos
 */
class AsignacionVariable(
    var identificador: Token?,
    var opAsignacion: Token?,
    var termino: Expresion?,
    var finSentencia: Token?
) : Sentencia() {

    override fun toString(): String {
        return "AsignacionVariable(identificador=$identificador,opAsignacion=$opAsignacion,termino=$termino,finSentencia=$finSentencia)"
    }

    override fun getArbolVisual(): TreeItem<String> {
        var raiz = TreeItem("Asignacion Variable")
        raiz.children.add(TreeItem("Identificador: ${identificador?.lexema}"))
        //  raiz.children.add(TreeItem("Termino: ${termino?.termino?.lexema}"))
        return raiz
    }


    override fun analizarSemantica(
        tablaSimbolos: TablaSimbolos,
        erroresSemanticos: ArrayList<ErrorSemantico>,
        ambito: Simbolo
    ) {

        var s = tablaSimbolos.buscarSimboloValor(identificador!!.lexema, ambito)
        if (s == null) {
            erroresSemanticos.add(
                ErrorSemantico(
                    "El campo ${identificador!!.lexema} no exite dentro del ambito ${ambito}",
                    identificador!!.fila,
                    identificador!!.columna
                )
            )
        } else {
            var tipo = s.tipo
            if (termino != null) {
                termino!!.analizarSemantica(tablaSimbolos, erroresSemanticos, ambito)
                var tipoExpresion = termino!!.obtenerTipo(tablaSimbolos, ambito)
                if (tipoExpresion != tipo) {
                    erroresSemanticos.add(
                        ErrorSemantico(
                            "El tipo de dato de la expresion $tipoExpresion no coincide con el tipo de dato del campo ${identificador!!.lexema} que es $tipo",
                            identificador!!.fila,
                            identificador!!.columna
                        )
                    )
                }
            }
            // falta validar el tipo de retorno de la invocacion de una funcion
            // como no tenemos estructurado el codigo de esta forma no se puede hacer esto
            // nuestra asignacion siempre es una expresion y no se puede hacer una asiganacion de invocacion
        }
    }
}