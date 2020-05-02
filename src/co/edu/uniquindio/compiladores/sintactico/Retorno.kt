package co.edu.uniquindio.compiladores.sintactico
import co.edu.uniquindio.compiladores.lexico.Token

class Retorno(var palabraReservada:Token, var expresion:Expresion?, var finSentencia:Token):Sentencia() {

    override fun toString(): String {
        return "Retorno(palabraReservada=$palabraReservada,expresion=$expresion,finSentencia=$finSentencia)"
    }

}