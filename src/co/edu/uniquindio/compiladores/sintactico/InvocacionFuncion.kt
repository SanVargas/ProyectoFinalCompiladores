package co.edu.uniquindio.compiladores.sintactico

import co.edu.uniquindio.compiladores.lexico.Token
import java.util.ArrayList

class InvocacionFuncion(var punto: Token, var id:Token, var parIzq:Token, var argumentos: ArrayList<Argumento>?, var parDer:Token, var finSentencia:Token):Sentencia() {

    override fun toString(): String {
        return "InvocacionFuncion(punto=$punto, id=$id, parIzq=$parIzq, argumentos=$argumentos, parDer=$parDer, finSentencia=$finSentencia)"
    }

}