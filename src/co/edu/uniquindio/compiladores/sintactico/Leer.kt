package co.edu.uniquindio.compiladores.sintactico

import co.edu.uniquindio.compiladores.lexico.Token

class Leer(var palabraReservada:Token, var id:Token, var finSentencia:Token):Sentencia(){

    override fun toString(): String {
        return "Leer(palabraReservada=$palabraReservada, id=$id,finSentencia=$finSentencia )"
    }

}