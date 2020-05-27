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

) : Sentencia() {

    var identificador: Token? = null
    var opAsignacion: Token? = null
    var termino: Expresion? = null
    var invocacion: InvocacionFuncion?=null
    var finSentencia: Token? = null

    constructor(
        identificador: Token?,
        opAsignacion: Token?,
        termino: Expresion?,
        finSentencia: Token?
    ) : this() {
        this.identificador = identificador
        this.opAsignacion = opAsignacion
        this.termino = termino
        this.finSentencia = finSentencia


    }
    constructor(
        identificador: Token?,
        opAsignacion: Token?,
        invocacion: InvocacionFuncion?,
        finSentencia: Token?
    ) : this() {
        this.identificador = identificador
        this.opAsignacion = opAsignacion
        this.invocacion = invocacion
        this.finSentencia = finSentencia


    }


    override fun toString(): String {
        if(termino != null) {
            return "AsignacionVariable(identificador=$identificador,opAsignacion=$opAsignacion,termino=$termino,finSentencia=$finSentencia)"

        }else{

            return "AsignacionVariable(identificador=$identificador,opAsignacion=$opAsignacion,invocacion=$invocacion,finSentencia=$finSentencia)"

        }
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
            }else if(invocacion !=null){
               var funcion=tablaSimbolos.buscarSimboloFuncion(invocacion!!.id.lexema,obtenerTipoDeParametros(tablaSimbolos, ambito))
                if(tipo != funcion!!.tipo){

                    erroresSemanticos.add(
                        ErrorSemantico(
                            "El tipo de dato de la funcion ${funcion.nombre} (${funcion.tipo}) no coincide con el tipo de dato de la variable ${identificador!!.lexema} que es  $tipo",
                            identificador!!.fila,
                            identificador!!.columna
                        )
                    )
                }

            }
        }
    }

    fun obtenerTipoDeParametros(tablaSimbolos: TablaSimbolos,ambito: Simbolo): ArrayList<String> {
        var lista = ArrayList<String>()
        for (p in invocacion!!.argumentos) {
            lista.add(p.obtenerTipo(tablaSimbolos,ambito))
        }
        return lista
    }
}