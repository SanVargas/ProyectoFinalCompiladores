package co.edu.uniquindio.compiladores.sintactico

/**
 * Clase encargada de crear un error
 * @author Santiago Vargas - Sebastian Ceballos
 */
class ErrorSintactico(var mensaje: String, var fila: Int, var columna: Int) {

    override fun toString(): String {
        return "Error Sintactico [mensaje=$mensaje, fila=$fila, columna=$columna]"
    }

}