package co.edu.uniquindio.compiladores.sintactico

import co.edu.uniquindio.compiladores.lexico.Categoria
import co.edu.uniquindio.compiladores.lexico.ErrorLexico
import co.edu.uniquindio.compiladores.lexico.Token
import co.edu.uniquindio.compiladores.semantico.ErrorSemantico
import co.edu.uniquindio.compiladores.semantico.Simbolo
import co.edu.uniquindio.compiladores.semantico.TablaSimbolos
import javafx.scene.control.TreeItem

/**
 * Clase encargada de crear una expresion aritmetica
 * @author Santiago Vargas - Sebastian Ceballos
 */
class ExpresionAritmetica() : Expresion() {
    var expresionAritmetica1: ExpresionAritmetica? = null
    var expresionAritmetica2: ExpresionAritmetica? = null
    var operador: Token? = null
    var valorNumerico: ValorNumerico? = null

    constructor(
        expresionAritmetica1: ExpresionAritmetica?,
        operador: Token?,
        expresionAritmetica2: ExpresionAritmetica?
    ) : this() {
        this.expresionAritmetica1 = expresionAritmetica1
        this.operador = operador
        this.expresionAritmetica2 = expresionAritmetica2
    }

    constructor(expresionAritmetica1: ExpresionAritmetica?) : this() {
        this.expresionAritmetica1 = expresionAritmetica1
    }

    constructor(valorNumerico: ValorNumerico?, operador: Token?, expresionAritmetica2: ExpresionAritmetica?) : this() {
        this.valorNumerico = valorNumerico
        this.operador = operador
        this.expresionAritmetica2 = expresionAritmetica2
    }

    constructor(valorNumerico: ValorNumerico?) : this() {
        this.valorNumerico = valorNumerico
    }

    override fun toString(): String {
        return "ExpresionAritmetica(expresionAritmetica1=$expresionAritmetica1, expresionAritmetica2=$expresionAritmetica2, operador=$operador, valorNumerico=$valorNumerico)"
    }

    override fun getArbolVisual(): TreeItem<String> {
        var raiz = TreeItem("Expresion Aritmetica")
        return raiz
    }


    override fun obtenerTipo(
        tablaSimbolos: TablaSimbolos,
        ambito: Simbolo
    ): String {
        if (expresionAritmetica1 != null && expresionAritmetica2 != null) {
            var tipo1 = expresionAritmetica1!!.obtenerTipo(tablaSimbolos, ambito)
            var tipo2 = expresionAritmetica2!!.obtenerTipo(tablaSimbolos, ambito)
            if (tipo1 == "decimal" || tipo2 == "decimal") {
                return "decimal"
            } else {
                return "entero"
            }
        } else if (expresionAritmetica1 != null) {
            return expresionAritmetica1!!.obtenerTipo(tablaSimbolos, ambito)
        } else if (valorNumerico != null
            && expresionAritmetica1 != null
        ) {
            var tipo1 = obtenerTipoCampo(valorNumerico, ambito, tablaSimbolos)

            var tipo2 = expresionAritmetica1!!.obtenerTipo(tablaSimbolos, ambito)
            if (tipo1 == "decimal" || tipo2 == "decimal") {
                return "decimal"
            } else {
                return "entero"
            }
        } else if (valorNumerico != null) {

            return obtenerTipoCampo(valorNumerico, ambito, tablaSimbolos)

        }
        return ""
    }

    fun obtenerTipoCampo(
        valorNumerico: ValorNumerico?,
        ambito: Simbolo,
        tablaSimbolos: TablaSimbolos
    ): String {
        if (valorNumerico!!.tipo.categoria == Categoria.ENTERO) {
            return "entero"
        } else if (valorNumerico!!.tipo.categoria == Categoria.DECIMAL) {
            return "decimal"
        } else {
            var simbolo = tablaSimbolos.buscarSimboloValor(valorNumerico!!.tipo.lexema, ambito)
            if (simbolo != null) {
                return simbolo.tipo!!
            }
        }

        return ""
    }

    override fun analizarSemantica(
        tablaSimbolos: TablaSimbolos,
        erroresSemanticos: ArrayList<ErrorSemantico>,
        ambito: Simbolo
    ) {
        if (valorNumerico != null) {
            if (valorNumerico!!.tipo.categoria == Categoria.IDENTIFICADOR_VARIABLE) {
                var simbolo = tablaSimbolos.buscarSimboloValor(valorNumerico!!.tipo.lexema, ambito)
                if (simbolo == null) {
// capturar el tipo y preguntar si es numerico
                    erroresSemanticos.add(
                        ErrorSemantico(
                            "El campo ${valorNumerico!!.tipo.lexema} no existe dentro del ambito ${ambito.nombre}",
                            valorNumerico!!.tipo.fila,
                            valorNumerico!!.tipo.columna
                        )
                    )
                }
            }
        }
        if (expresionAritmetica1 != null) {
            expresionAritmetica1!!.analizarSemantica(tablaSimbolos, erroresSemanticos, ambito)
        }
        if (expresionAritmetica2 != null) {
            expresionAritmetica2!!.analizarSemantica(tablaSimbolos, erroresSemanticos, ambito)
        }
    }

    /**
     * aritmetica1 opAritmetico arimetica2
    aritmetica1
    valornumerico opAritmetico aritmetica2
    valornumerico
     */
    override fun getJavaCode(): String {
        var codigo =""
        if(expresionAritmetica1!=null && expresionAritmetica2 !=null){
            return expresionAritmetica1?.getJavaCode()+operador?.lexema+expresionAritmetica2?.getJavaCode()
        }else if(expresionAritmetica1!=null){
            return ""+expresionAritmetica1?.getJavaCode()
        }else if(valorNumerico!=null) {
            return ""+valorNumerico?.getJavaCode()
        }
        return codigo
    }


}
