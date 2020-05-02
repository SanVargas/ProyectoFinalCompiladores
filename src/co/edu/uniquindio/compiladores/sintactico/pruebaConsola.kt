package co.edu.uniquindio.compiladores.sintactico

import co.edu.uniquindio.compiladores.lexico.AnalizadorLexico

fun main(){
    val lexico=AnalizadorLexico("fun vacio metodo(){imprimir ()°}")
    lexico.analizar()

    val sintactico=AnalizadorSintactico(lexico.listaTokens)
    print(sintactico.esUnidadDeCompilacion())
    print(sintactico.listaErrores)
}
