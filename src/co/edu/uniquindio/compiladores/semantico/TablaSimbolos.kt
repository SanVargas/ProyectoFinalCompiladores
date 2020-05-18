package co.edu.uniquindio.compiladores.semantico

import co.edu.uniquindio.compiladores.lexico.ErrorLexico


/**
 * Clase que permite la creacion de la tabla de simobolos
 * @author Sebastian Ceballos Arias,  Santiango Andres Vargas
 * @version 0.0.1
 */
class TablaSimbolos (var listaErrores: ArrayList<ErrorLexico>) {
    var listaSimbolos: ArrayList<Simbolo> = ArrayList()

    /**
     * Permite guardar un símbolo de tipo valor en la tabla de símbolos
     */
    fun guardarSimboloValor(nombre: String, tipo: String?, ambito: String, fila: Int, columna: Int): Simbolo? {
        val s = buscarSimboloValor(nombre, ambito)
        if (s == null) {
            val nuevo = Simbolo(nombre, tipo, false, ambito, fila, columna )
            listaSimbolos.add(nuevo)
            return nuevo
        } else {
           listaErrores.add(ErrorLexico("La variable $nombre  ya existe en el  $ambito  ambito" , fila, columna))
        }
        return null
    }
    /**
     * Permite guardar un símbolo de tipo función en la tabla de símbolos
     */
    fun guardarSimboloFuncion(nombre: String, tipo: String?, tipoParametros:
    ArrayList<String>, ambito: String, fila: Int, columna: Int): Simbolo? {
        var s = buscarSimboloFuncion(nombre, tipoParametros)
        if (s == null) {
            var nuevo = Simbolo(nombre, tipo, tipoParametros, ambito, fila, columna)
            listaSimbolos.add(nuevo)
            return nuevo
        } else {
            listaErrores.add(ErrorLexico("La función $nombre $tipoParametros ya existe", fila, columna))
        }
        return null
    }

    /**
     *Funcion que nos permite buscar en la tabla de simbolos  si se encuentra
     * el valor que buscamos
     */
    fun buscarSimboloValor(nombre: String, ambito: String): Simbolo? {
        for (simbolo in listaSimbolos) {
            if (simbolo.ambito != null) {
                if (nombre == simbolo.nombre && ambito == simbolo.ambito) {
                    return simbolo
                }
            }
        }
        return null
    }

    /**
     *Funcion que nos permite buscar en la tabla de funciones  si se encuentra
     * la funcion  que buscamos
     */
    fun buscarSimboloFuncion(nombre: String, tiposParametros: ArrayList<String>):
            Simbolo? {
        for (simbolo in listaSimbolos) {
            if (simbolo.tipoParametros != null) {
                if (nombre == simbolo.nombre && tiposParametros ==
                    simbolo.tipoParametros) {
                    return simbolo
                }

            }
        }
        return null
    }

    override fun toString(): String {
        return "TablaSimbolos(listaSimbolos=$listaSimbolos)"
    }
}