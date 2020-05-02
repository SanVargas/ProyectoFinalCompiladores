package co.edu.uniquindio.compiladores.sintactico

import co.edu.uniquindio.compiladores.lexico.Token

class ValorNumerico( var  signo:Token?, var  tipo:Token?) {

    override fun toString(): String {
        return "ValorNumerico(signo=$signo, tipo=$tipo)"
    }
}