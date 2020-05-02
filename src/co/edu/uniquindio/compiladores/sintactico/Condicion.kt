package co.edu.uniquindio.compiladores.sintactico
import co.edu.uniquindio.compiladores.sintactico.Sentencia
import co.edu.uniquindio.compiladores.lexico.Token

class Condicion(var palabraReservada:Token?, var parIzq:Token?, var expresionLogica:ExpresionLogica?, var parDer:Token?, var llaIzq:Token?,
                 var sentencias:ArrayList<Sentencia>?, var llaDer:Token?): Sentencia(){

    override fun toString(): String {
        return "Condicion(palabraReservada=$palabraReservada,parIzq=$parIzq,expresionLogica=$expresionLogica, parDer=$parDer, llaIzq=$llaIzq" +
                " sentencias=$sentencias, llaDer=$llaDer)"
    }

}
