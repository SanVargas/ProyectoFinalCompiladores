package co.edu.uniquindio.compiladores.semantico


/**
 *Clase simbolo la cual permite clasificar cada simbolo
 * @author Sebastian Ceballos Arias ,  Santiango Andres Vargar
 * @version 0.0.1
 */
class Simbolo {

    /**
     * Variables necesarias para la creacion de los tipos de simbolos
     */
    var nombre: String? = ""
    var tipo: String? = ""
    var modificable: Boolean = false
    var fila: Int = 0
    var columna: Int = 0
    var ambito: String? = ""
    var tipoParametros: ArrayList<String>? = null

    /**
     * Constructor para crear un simbolo de tipo valor
     */
    constructor(nombre: String?, tipo: String?, modificable: Boolean, ambito: String?, fila: Int, columna: Int){
        this.nombre = nombre
        this.tipo = tipo
        this.modificable = modificable
        this.ambito = ambito
        this.fila = fila
        this.columna = columna
    }

    /**
     * Constructor para crear uin simbolo de tipo metodo
     */
    constructor(nombre: String, tipo: String?, tipoParametros: ArrayList<String>, ambito: String, fila: Int, columna: Int){
        this.nombre = nombre
        this.tipo = tipo
        this.tipoParametros = tipoParametros
        this.ambito = ambito
        this.fila = fila
        this.columna = columna
    }

    override fun toString(): String {

        return if (tipoParametros == null) {
            "Simbolo(identificador=$nombre, tipoDato=$tipo,modificable=$modificable, ambito=$ambito, fila=$fila, columna=$columna)"

        } else {

            "Simbolo(identificador=$nombre, tipoDato=$tipo, ambito=$ambito, tiposParametros=$tipoParametros, fila=$fila, columna=$columna)"
        }
    }
}