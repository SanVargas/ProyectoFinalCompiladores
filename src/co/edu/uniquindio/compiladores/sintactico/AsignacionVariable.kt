package co.edu.uniquindio.compiladores.sintactico
import co.edu.uniquindio.compiladores.lexico.Token

class AsignacionVariable(var identificador: Token?, var opAsignacion:Token?, var termino:Termino?, var finSentencia:Token?):Sentencia() {

    override fun toString(): String {
        return "AsignacionVariable(identificador=$identificador,opAsignacion=$opAsignacion,termino=$termino,finSentencia=$finSentencia)"
    }
}