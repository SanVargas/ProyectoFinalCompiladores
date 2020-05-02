package co.edu.uniquindio.compiladores.sintactico

import co.edu.uniquindio.compiladores.lexico.Token

class Ciclo(var palabraReservada:Token,  var parIzq:Token,  var expresionLogica:ExpresionLogica,  var parDer:Token, var llaveIzq:Token, var lstSentencias:ArrayList<Sentencia>,  var llaveDer:Token):Sentencia(){

    override fun toString(): String {
        return "Ciclo(palabraReservada=$palabraReservada, parIzq=$parIzq, expresionLogica=$expresionLogica, parDer=$parDer, llaveIzq=$llaveIzq, lstSentencias=$lstSentencias, laveDer=$llaveDer)"
    }

}