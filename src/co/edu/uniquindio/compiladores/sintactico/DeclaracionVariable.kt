package co.edu.uniquindio.compiladores.sintactico
import co.edu.uniquindio.compiladores.lexico.Token

class DeclaracionVariable(var tipoDato: Token, var identificador:Token, var finSentencia:Token): Sentencia(){

    override fun toString(): String {
        return "DeclaracionVariable(tipoDato=$tipoDato, identificador=$identificador, finSentencia=$finSentencia)"
    }

}