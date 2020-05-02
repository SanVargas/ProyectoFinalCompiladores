package co.edu.uniquindio.compiladores.sintactico
import co.edu.uniquindio.compiladores.lexico.Token
/**
 * Clase que permite crear una expresion Cadena
 * @param cadena; cadena de tipo token
 * @param mas; mas de tipo token
 * @param expresion; expresion de tipo expresion
 */
class ExpresionCadena(var cadena: Token?, var mas: Token?, var expresion: Expresion?): Expresion() {
    constructor(cadena:Token):this(cadena,null,null){}
    /**
     * Funcion toString de la clase expresion cadena
     */
    override fun toString(): String {
        return "ExpresionCadena(cadena=$cadena, mas=$mas, expresion=$expresion)"
    }
}