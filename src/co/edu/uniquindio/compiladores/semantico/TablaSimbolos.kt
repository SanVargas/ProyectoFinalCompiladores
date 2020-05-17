package co.edu.uniquindio.compiladores.semantico

import co.edu.uniquindio.compiladores.lexico.ErrorLexico

class TablaSimbolos (var listaErrores: ArrayList<ErrorLexico>) {
    var listaSimbolos: ArrayList<Simbolo> = ArrayList()

    /**
     * Permite guardar un símbolo de tipo variable en la tabla de símbolos
     */
    fun guardarSimboloVariable(nombre: String, tipo: String?, ambito: String, fila: Int, columna: Int): Simbolo? {
        val s = buscarSimboloVariable(nombre, ambito)
        if (s == null) {
            val nuevo = Simbolo(nombre, tipo, false, ambito, fila, columna )
            listaSimbolos.add(nuevo)
            return nuevo
        } else {
           // listaErrores.add("La variable $nombre ya existe en el ámbito $ambito")
        }
        return null
    }
    /**
     * Permite guardar un símbolo de tipo función en la tabla de símbolos
     */
    fun guardarSimboloFuncion(nombre: String, tipo: String?, tipoParametros:
    ArrayList<String>, ambito: String): Simbolo? {
        var s = buscarSimboloFuncion(nombre, tipoParametros)
        if (s == null) {
            var nuevo = Simbolo(nombre, tipo, tipoParametros, ambito)
            listaSimbolos.add(nuevo)
            return nuevo
        } else {
            //listaErrores.add("La función $nombre $tipoParametros ya existe")
        }
        return null
    }
    fun buscarSimboloVariable(nombre: String, ambito: String): Simbolo? {
        for (simbolo in listaSimbolos) {
            if (simbolo.ambito != null) {
                if (nombre == simbolo.nombre && ambito == simbolo.ambito) {
                    return simbolo
                }
            }
        }
        return null
    }
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