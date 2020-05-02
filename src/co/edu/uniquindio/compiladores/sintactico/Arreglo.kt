package co.edu.uniquindio.compiladores.sintactico

import co.edu.uniquindio.compiladores.lexico.Token

class Arreglo(var tipo: Token, var identificador:Token, var lstArgumentos:ArrayList<Argumento?>?):Sentencia() {

    override fun toString(): String {
        return "Arreglo(tipo=$tipo,identificador=$identificador,lstArgumentos=$lstArgumentos)"
    }

}