package co.edu.uniquindio.compiladores.sintactico
import co.edu.uniquindio.compiladores.lexico.Token

class ImpresionInversa(var palabraReservada: Token, var parIzq:Token, var exp:Expresion?, var parDer:Token, var finSentencia:Token):Sentencia() {

    override fun toString(): String {
    return "ImpresionInversa(palabraReservada=$palabraReservada, parIzq=$parIzq, exp=$exp, parDer=$parDer, finSentencia=$finSentencia)"
    }

}