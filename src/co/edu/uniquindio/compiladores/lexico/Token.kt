package co.edu.uniquindio.compiladores.lexico

import co.edu.uniquindio.compiladores.lexico.Categoria

/**
 * Clase indentificadora del token
 */
class Token(var lexema:String, var categoria: Categoria, var fila:Int, var columna:Int){

    override fun toString(): String {
        return "Token(lexema='$lexema', categoria=$categoria, fila=$fila, columna=$columna)"
    }

    fun getJavaCode():String{
        if(categoria==Categoria.PALABRA_RESERVADA){
            when(lexema){
                "entero" -> {return "int"}
                "decimal" -> {return "double"}
                "log" -> {return "boolean"}
                "si" -> {return "if"}
                "imprimir" -> {return "print"}
                "retorno" -> {return "return"}
                "vacio" -> {return "void"}
                "verdadero" -> {return "true"}
                "falso" -> {return "false"}
                "mientras" -> {return "while"}
                "cad" -> {return "String"}
            }
            }else if(categoria==Categoria.FIN_SENTENCIA){
            return ";"
        }else if(categoria==Categoria.CADENA_CARACTERES){
            lexema.replace("«", "\"")
            lexema.replace("»", "\"")
            return lexema;
        }else if(categoria==Categoria.CARACTER){
            lexema.replace("_", "\"")
            return lexema
        }
        return "null"
        }
    }
/**
 * "impInversa"
 * "leer"
 * "leerInverso"
 */
