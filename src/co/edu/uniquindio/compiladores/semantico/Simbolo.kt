package co.edu.uniquindio.compiladores.semantico

class Simbolo {

    var nombre: String? = ""
    var tipo: String? = ""
    var modificable: Boolean = false
    var fila:Int = 0
    var columna:Int = 0
    var ambito: String? = ""
    var tipoParametros: ArrayList<String>? = null
    constructor(nombre: String, tipo: String?,modificable:Boolean, ambito: String, fila:Int, columna:Int){
        this.nombre = nombre
        this.tipo = tipo
        this.modificable=modificable
        this.ambito = ambito
        this.fila = fila
        this.columna = columna
    }
    constructor(nombre: String, tipo: String?, tipoParametros: ArrayList<String>, ambito:String){
        this.nombre = nombre
        this.tipo = tipo
        this.tipoParametros = tipoParametros
        this.ambito=ambito
    }

    override fun toString(): String {
        return "Simbolo(identificador=$nombre, tipoDato=$tipo,modificable=$modificable, ambito=$ambito, fila=$fila, columna=$columna)"
    }
}