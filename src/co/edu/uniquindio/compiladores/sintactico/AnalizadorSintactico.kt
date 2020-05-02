package co.edu.uniquindio.compiladores.sintactico
import co.edu.uniquindio.compiladores.lexico.Categoria
import co.edu.uniquindio.compiladores.lexico.ErrorLexico
import co.edu.uniquindio.compiladores.lexico.Token
import java.util.*
/**
 *Clase principal del analizador sintactico donde se maneja toda la estructura del compilador
 * @param listaTokens; lista de tokens previamente preparada por el analizador lexico
 * @author Sebastian Ceballos Arias, Andres Santiango Vargas
 * @version 0.001
 */
class AnalizadorSintactico(var listaTokens: ArrayList<Token>) {

    /**
     * Variables basicas para el funcionamiento del analizador Sintactico
     */
    var posicionActual = 0
    var tokenActual = listaTokens[0]
    var listaErrores = ArrayList<ErrorLexico>()

    /**
     * Funcion que nos permite ir cambiando de posicion en la lista de tokens
     */
    fun obtenerSiguienteToken() {
        posicionActual++;
        if (posicionActual < listaTokens.size) {
            tokenActual = listaTokens[posicionActual]
        }
    }

    /**
     * Funcion que nor permite listar nuestros errore sintacticos
     * @param mensaje; mensaje generado por el error
     */
    fun reportarErrores(mensaje: String) {
        listaErrores.add(ErrorLexico(mensaje, tokenActual.fila, tokenActual.columna))
    }

    /**
     * <unidadDeCompilacion> :: = <listaDeFunciones>
     */
    fun esUnidadDeCompilacion(): UnidadDeCompilacion? {
        var listaFunciones: ArrayList<Funcion> = esListaFunciones()
        return if (listaFunciones.size > 0) {
            UnidadDeCompilacion(listaFunciones)
        } else null
    }

    /**
     * Hacer backtracking, ya que no corresponde el término
     */
    fun hacerBTToken(posicionToken: Int) {
        posicionActual = posicionToken
        tokenActual = if (posicionToken < listaTokens.size) {
            listaTokens[posicionToken]
        } else {
            Token("", Categoria.ERROR, 0, 0)
        }
    }

    /**
     * <listaDeFunciones> :: = <funcion> [<listaDeFunciones>]
     */
    fun esListaFunciones(): ArrayList<Funcion> {
        var listaFunciones = ArrayList<Funcion>()
        var funcion = esFuncion()
        while (funcion != null) {
            listaFunciones.add(funcion)
            funcion = esFuncion()
        }
        return listaFunciones
    }

    /**
     * <Funcion>::=fun<esTipoDato>identificador"("[<esListaParametro>]")""{"[<esListaDeSentencia>]"}"
     */
    fun esFuncion(): Funcion? {
        if (tokenActual.categoria == Categoria.PALABRA_RESERVADA && tokenActual.lexema == "fun") {
            var func = tokenActual
            obtenerSiguienteToken()
            if (estipoDato()) {
                print("entro")
                var tipoRetorno = tokenActual
                obtenerSiguienteToken()
                if (tokenActual.categoria == Categoria.IDENTIFICADOR_VARIABLE) {
                    var identificador = tokenActual
                    obtenerSiguienteToken()
                    if (tokenActual.categoria == Categoria.PARENTESIS_IZQUIERDO) {
                        var parIzq = tokenActual
                        obtenerSiguienteToken()
                        var parametros: ArrayList<Parametro> = esListaDeParametro()
                        if (tokenActual.categoria == Categoria.PARENTESIS_DERECHO) {
                            var parDer = tokenActual
                            obtenerSiguienteToken()
                            if (tokenActual.categoria == Categoria.LLAVE_IZQUIERDA) {
                                var llaveIzq = tokenActual
                                obtenerSiguienteToken()
                                var sentencias = esListaSentencias()
                                if (tokenActual.categoria == Categoria.LLAVE_DERECHA) {
                                    var llaveDer = tokenActual
                                    obtenerSiguienteToken()
                                    return Funcion(func, tipoRetorno, identificador, parIzq, parametros, parDer, llaveIzq, sentencias, llaveDer)
                                } else {
                                    reportarErrores("Falta llave derechaa")
                                }
                            } else {
                                reportarErrores("Falta llave izquierda")
                            }
                        } else {
                            reportarErrores("Falta parentesis derecho")
                        }
                    } else {
                        reportarErrores("Falta parentesis izquierdo")
                    }
                } else {
                    reportarErrores("Falta el nombre de la función")
                }
            } else {
                reportarErrores("Falta tipo de retorno")
            }
        }
        return null
    }

    /**
     * Metodo que me dice si el token es un tipo de dato
     * @return true de ser un tipo de dato, false de no ser un tipo de dato
     */
    fun estipoDato(): Boolean {
        if (tokenActual.categoria == Categoria.PALABRA_RESERVADA && tokenActual.lexema == "vacio" || tokenActual.categoria == Categoria.PALABRA_RESERVADA && tokenActual.lexema == "entero" || (tokenActual.categoria == Categoria.PALABRA_RESERVADA
                    && tokenActual.lexema == "decimal") || tokenActual.categoria == Categoria.PALABRA_RESERVADA && tokenActual.lexema == "cadena" || (tokenActual.categoria == Categoria.PALABRA_RESERVADA
                    && tokenActual.lexema == "logico")){
            return true
        }
        return false

    }

    /**
	 * <esListaDeParametro>::= <parametro>[","<esListaDeParametro>]
	 */
    fun esListaDeParametro(): ArrayList<Parametro> {
        var parametros = ArrayList<Parametro>()
        var p = esParametro()
        while (p != null) {
            parametros.add(p)
            if(tokenActual.categoria == Categoria.SEPARADOR) {
                obtenerSiguienteToken()
                p = esParametro()
            } else {
                p = null
            }
        }
        return parametros
    }

    /**
     * <esParametro>::= <esTipoDato> identificador </esTipoDato></esParametro>
     */
    fun esParametro(): Parametro? {
        if (estipoDato()) {
            var tipoDato = tokenActual
            obtenerSiguienteToken()
            if (tokenActual.categoria == Categoria.IDENTIFICADOR_VARIABLE) {
                var identificador = tokenActual
                obtenerSiguienteToken()
                return Parametro(tipoDato, identificador)
            }
        }
        return null
    }

    /**
     *<ListaParametros> :: = <Sentencia> [ <ListaSentencia> ]
     */
    fun esListaSentencias(): ArrayList<Sentencia> {
        var listaSentencias = ArrayList<Sentencia>()
        var sentencia = esSentencia()
        while (sentencia != null) {
            listaSentencias.add(sentencia)
            sentencia = esSentencia()
        }
        return listaSentencias
    }

    /**
     * <esSentencia>::=<esCondicion>|<esDeclaracionDeVariable>|<esAsignacion>|<esImpresion>|<esRetorno>|
     * <esLectura>|<esCiclo>|<esInvocacion>
     * </esInvocacion></esCiclo></esLectura></esRetorno></esImpresion></esAsignacion></esDeclaracionDeVariable></esCondicion></esSentencia>
     */
    fun esSentencia(): Sentencia? {
        var c : Condicion? = esCondicion()
        if (c != null) {
            return c
        }
        var dV: DeclaracionVariable? = esDeclaracionVariable()
        if (dV != null) {
            return dV
        }
        var aV: AsignacionVariable? = esAsignacionVariable()
        if (aV != null) {
            return aV
        }
        var i: Impresion? = esImpresion()
        if (i != null) {
            return i
        }
        var iI: ImpresionInversa? = esImpresionInversa()
        if (iI != null) {
            return iI
        }
        var r:Retorno? = esRetorno()
        if (r != null) {
            return r
        }
        var l: Leer? = esLectura()
        if (l != null) {
            return l
        }
        var lI: LeerInverso? = esLecturaInversa()
        if (lI != null) {
            return lI
        }
        var ci: Ciclo? = esCiclo()
        if (ci != null) {
            return ci
        }
        var iF: InvocacionFuncion? = esInvocacion()
        if (iF != null) {
            return iF
        }else{
            return esArreglo()
        }
        return null
    }

    /**
     * <esCondicion>::= si "(" <esExpresionLogica> ")" "{" [<esListaSentencias>] "}"
     * </esListaSentencias></esExoresuinLogica></esCondicion>
     */
    fun esCondicion(): Condicion? {
        if (tokenActual.categoria == Categoria.PALABRA_RESERVADA && tokenActual.lexema == "si") {
            var palabraReservada = tokenActual
            obtenerSiguienteToken()
            if (tokenActual.categoria == Categoria.PARENTESIS_IZQUIERDO) {
                var parIzq = tokenActual
                obtenerSiguienteToken()
                var expLog = esExpresionLogica()
                if (expLog != null) {
                    obtenerSiguienteToken()
                    if (tokenActual.categoria == Categoria.PARENTESIS_DERECHO) {
                        var parDer = tokenActual
                        obtenerSiguienteToken()
                        if (tokenActual.categoria == Categoria.LLAVE_IZQUIERDA) {
                            var llaveIzq = tokenActual
                            obtenerSiguienteToken()
                            var sentencias = esListaSentencias()
                            if (tokenActual.categoria == Categoria.LLAVE_DERECHA) {
                                var llaveDer = tokenActual
                                obtenerSiguienteToken()
                                return Condicion(
                                    palabraReservada, parIzq, expLog, parDer, llaveIzq,
                                    sentencias, llaveDer
                                )
                            } else {
                                reportarErrores("Falta llave derecha en la condicion")
                            }
                        } else {
                            reportarErrores("Falta llave izquierda en la condicion")
                        }
                    } else {
                        reportarErrores("Falta parentesis derecho en la condicion")
                    }
                } else {
                    reportarErrores("Falta expresion logica en la condicion")
                }
            } else {
                reportarErrores("Falta parentesis izquierdo en la condicion")
            }
        }
        return null
    }

    /**
     * <esDeclaracionDeVariable>::= <esTopoDato> identificador "°"
     * </esTopoDato></esDeclaracionDeVariable>
     */
    fun esDeclaracionVariable(): DeclaracionVariable? {
        if (estipoDato()) {
            var tipoDato = tokenActual
            obtenerSiguienteToken()
            if (tokenActual.categoria == Categoria.IDENTIFICADOR_VARIABLE) {
                var identificador = tokenActual
                obtenerSiguienteToken()
                if (tokenActual.categoria == Categoria.FIN_SENTENCIA) {
                    var finSentencia = tokenActual
                    obtenerSiguienteToken()
                    return DeclaracionVariable(tipoDato, identificador, finSentencia)
                } else {
                    reportarErrores("Falta el fin de sentencia en la declaracion de variable")
                }
            } else {
                reportarErrores("Falta el identificador de la declaracion de variable")
            }
        }
        return null
    }

    /**
     * <esAdignacion>::= identificador <operadorAsignacion> <esTermino> "°"
     * </esTermino></operadorAsignacion></esAdignacion>
     */
    fun esAsignacionVariable(): AsignacionVariable? {
        if (tokenActual.categoria == Categoria.IDENTIFICADOR_VARIABLE) {
            var id = tokenActual
            obtenerSiguienteToken()
            if (tokenActual.categoria == Categoria.OPERADOR_ASIGNACION) {
                var opAsignacion = tokenActual
                obtenerSiguienteToken()
                var t: Termino? = esTermino()
                if (t != null) {
                    obtenerSiguienteToken()
                    if (tokenActual.categoria == Categoria.FIN_SENTENCIA) {
                        var finSentencia = tokenActual
                        obtenerSiguienteToken()
                        return AsignacionVariable(id, opAsignacion, t, finSentencia)
                    } else {
                        reportarErrores("Falta fin de sentencia en la asginacion")
                    }
                } else {
                    reportarErrores("Falta el termino que se asigna en la asginacion")
                }
            } else {
                reportarErrores("Falta operador de asignacion en la asginacion")
            }
        }
        return null
    }

    /**
     * <esImpresion>::= imprimir "(" [<esExpresion>] ")" "°"
     * </esExpresion></esImpresion>
     */
    fun esImpresion(): Impresion? {
        if (tokenActual.categoria == Categoria.PALABRA_RESERVADA && tokenActual.lexema == "imprimir") {
            var palabraReservada = tokenActual
            obtenerSiguienteToken()
            if (tokenActual.categoria == Categoria.PARENTESIS_IZQUIERDO) {
                var parIzq = tokenActual
                obtenerSiguienteToken()
                var exp = esExpresion()
                if (tokenActual.categoria == Categoria.PARENTESIS_DERECHO) {
                    var parDer = tokenActual
                    obtenerSiguienteToken()
                    if (tokenActual.categoria == Categoria.FIN_SENTENCIA) {
                        var finSentencia = tokenActual
                        obtenerSiguienteToken()
                        return Impresion(palabraReservada, parIzq, exp, parDer, finSentencia)
                    } else {
                        reportarErrores("Falta el final de sentencia en la impresion")
                    }
                } else {
                    reportarErrores("Falta parentesis derecho en la impresion")
                }
            } else {
                reportarErrores("Falta parentesis izquierdo en la impresion")
            }
        }
        return null
    }

    /**
     * <esImpresion>::= imprimirInverso "(" [<esExpresion>] ")" "°"
     * </esExpresion></esImpresion>
     */
    fun esImpresionInversa(): ImpresionInversa? {
        if (tokenActual.categoria == Categoria.PALABRA_RESERVADA && tokenActual.lexema == "impInversa") {
            var palabraReservada = tokenActual
            obtenerSiguienteToken()
            if (tokenActual.categoria == Categoria.PARENTESIS_IZQUIERDO) {
                var parIzq = tokenActual
                obtenerSiguienteToken()
                var exp = esExpresion()
                if (tokenActual.categoria == Categoria.PARENTESIS_DERECHO) {
                    var parDer = tokenActual
                    obtenerSiguienteToken()
                    if (tokenActual.categoria == Categoria.FIN_SENTENCIA) {
                        var finSentencia = tokenActual
                        obtenerSiguienteToken()
                        return ImpresionInversa(palabraReservada, parIzq, exp, parDer, finSentencia)
                    } else {
                        reportarErrores("Falta el final de sentencia en la impresion")
                    }
                } else {
                    reportarErrores("Falta parentesis derecho en la impresion")
                }
            } else {
                reportarErrores("Falta parentesis izquierdo en la impresion")
            }
        }
        return null
    }

    /**
     * <esRetorno>::= retornar <es Expresion> "°"
     * </es></esRetorno>
     */
    fun esRetorno(): Retorno? {
        if (tokenActual.categoria == Categoria.PALABRA_RESERVADA && tokenActual.lexema == "retorno") {
            var palabraReservada = tokenActual
            obtenerSiguienteToken()
            var expresion = esExpresion()
            if (expresion != null) {
                if (tokenActual.categoria == Categoria.FIN_SENTENCIA) {
                    var finSentencia = tokenActual
                    obtenerSiguienteToken()
                    return Retorno(palabraReservada, expresion, finSentencia)
                } else {
                    reportarErrores("Falta el final de sentencia en el retorno")
                }
            } else {
                reportarErrores("Falta la expresion en el retorno")
            }
        }
        return null
    }

    /**
     * <esLectura> ::= leer identificador "°"
     * </esLectura>
     */
    fun esLectura(): Leer? {
        if (tokenActual.categoria == Categoria.PALABRA_RESERVADA && tokenActual.lexema == "leer") {
            var palabraReservada = tokenActual
            obtenerSiguienteToken()
            if (tokenActual.categoria == Categoria.IDENTIFICADOR_VARIABLE) {
                var id = tokenActual
                obtenerSiguienteToken()
                if (tokenActual.categoria == Categoria.FIN_SENTENCIA) {
                    var finSentencia = tokenActual
                    obtenerSiguienteToken()
                    return Leer(palabraReservada, id, finSentencia)
                } else {
                    reportarErrores("Falta el final de sentencia en el leer")
                }
            } else {
                reportarErrores("Falta el identificador de leer")
            }
        }
        return null
    }

    /**
     * <esLecturaInversa> ::= leerInverso identificador "°"
     * </esLecturaInversa>
     */
    fun esLecturaInversa(): LeerInverso? {
        if (tokenActual.categoria == Categoria.PALABRA_RESERVADA && tokenActual.lexema == "leerInverso") {
            var palabraReservada = tokenActual
            obtenerSiguienteToken()
            if (tokenActual.categoria == Categoria.IDENTIFICADOR_VARIABLE) {
                var id = tokenActual
                obtenerSiguienteToken()
                if (tokenActual.categoria == Categoria.FIN_SENTENCIA) {
                    var finSentencia = tokenActual
                    obtenerSiguienteToken()
                    return LeerInverso(palabraReservada, id, finSentencia)
                } else {
                    reportarErrores("Falta el final de sentencia en el leer")
                }
            } else {
                reportarErrores("Falta el identificador de leer")
            }
        }
        return null
    }

    /**
     * <esCliclo> ::= mientras "(" <esExpresionLogica> ")" "{" [<esListaSentencias>] "}"
     * </esListaSentencias></esExpresionLogica></esCliclo>
     */
    fun esCiclo(): Ciclo? {
        if (tokenActual.categoria == Categoria.PALABRA_RESERVADA && tokenActual.lexema == "mientras") {
            var palabraReservada = tokenActual
            obtenerSiguienteToken()
            if (tokenActual.categoria == Categoria.PARENTESIS_IZQUIERDO) {
                var parIzq = tokenActual
                obtenerSiguienteToken()
                var expLog = esExpresionLogica()
                if (expLog != null) {
                    obtenerSiguienteToken()
                    if (tokenActual.categoria == Categoria.PARENTESIS_DERECHO) {
                        var parDer = tokenActual
                        obtenerSiguienteToken()
                        if (tokenActual.categoria == Categoria.LLAVE_IZQUIERDA) {
                            var llaveIzq = tokenActual
                            obtenerSiguienteToken()
                            var sentencias = esListaSentencias()
                            if (tokenActual.categoria == Categoria.LLAVE_DERECHA) {
                                var llaveDer = tokenActual
                                obtenerSiguienteToken()
                                return Ciclo(palabraReservada, parIzq, expLog, parDer, llaveIzq, sentencias, llaveDer)
                            } else {
                                reportarErrores("Falta llave derecha en el ciclo")
                            }
                        } else {
                            reportarErrores("Falta llave izquierda en en el ciclo")
                        }
                    } else {
                        reportarErrores("Falta parentesis derecho en el ciclo")
                    }
                } else {
                    reportarErrores("Falta expresion logica en ciclo")
                }
            } else {
                reportarErrores("Falta parentesis izquierdo en el ciclo")
            }
        }
        return null
    }

    /**
     * <esInvocacion>::= "." identificador "(" [<esListaArgumentos>] ")" "°"
     * </esListaArgumentos></esInvocacion>
     */
    fun esInvocacion(): InvocacionFuncion? {
        if (tokenActual.categoria == Categoria.PUNTO) {
            var inv = tokenActual
            obtenerSiguienteToken()
            if (tokenActual.categoria == Categoria.IDENTIFICADOR_VARIABLE) {
                var id = tokenActual
                obtenerSiguienteToken()
                if (tokenActual.categoria == Categoria.PARENTESIS_IZQUIERDO) {
                    var parIzq = tokenActual
                    obtenerSiguienteToken()
                    var argumentos: ArrayList<Argumento>? = null
                  //  if (tokenActual.categoria != Categoria.PARENTESIS_DERECHO) {
                        argumentos = esListaArgumentos()
                 //   }
                    if (tokenActual.categoria == Categoria.PARENTESIS_DERECHO) {
                        var parDer = tokenActual
                        obtenerSiguienteToken()
                        if (tokenActual.categoria == Categoria.FIN_SENTENCIA) {
                            var finSentencia = tokenActual
                            obtenerSiguienteToken()
                            return InvocacionFuncion(inv, id, parIzq, argumentos, parDer, finSentencia)
                        } else {
                            reportarErrores("Falta el finsentencia en la funcion")
                        }
                    } else {
                        reportarErrores("Falta parentisis derecho en la funcion")
                    }
                } else {
                    reportarErrores("Falta parentisis izquierdo en la funcion")
                }
            } else {
                reportarErrores("Falta el identificador de la funcion a invocar")
            }
        }
        return null
    }

    /**
	 * <Arreglo>::= "[" "]"TipoDeDato identificador "{" [<ListaArgumentos] "}"
	 */
    fun esArreglo(): Sentencia? {
        if (tokenActual.categoria == Categoria.CORCHETE_IZQUIERDO) {
            obtenerSiguienteToken()
            if (tokenActual.categoria == Categoria.CORCHETE_DERECHO) {
                obtenerSiguienteToken()
                var dato= estipoDato()
                if (tokenActual.categoria == Categoria.PALABRA_RESERVADA && dato != null) {
                    var tipo = tokenActual
                    obtenerSiguienteToken()
                    if (tokenActual.categoria == Categoria.IDENTIFICADOR_VARIABLE) {
                        var identificador = tokenActual
                        obtenerSiguienteToken()
                        if (tokenActual.categoria == Categoria.LLAVE_IZQUIERDA) {
                            obtenerSiguienteToken()
                            var argumentos = esListaArgumentos()
                            if (tokenActual.categoria == Categoria.LLAVE_DERECHA) {
                                obtenerSiguienteToken()
                                if (tokenActual.categoria == Categoria.FIN_SENTENCIA) {
                                    obtenerSiguienteToken()
                                    return Arreglo(tipo, identificador, argumentos)
                                } else {
                                    reportarErrores("Falta fin de sentencia")
                                }
                            } else {
                                reportarErrores("Falta cerrar llave del arreglo")
                            }
                        } else {
                            reportarErrores("Falta abrir llave del arreglo")
                        }
                    } else {
                        hacerBTToken(posicionActual - 1)
                        return null
                    }
                } else {
                    reportarErrores("Falta corchete de cierre del arreglo")
                }
            } else {
                reportarErrores("Falta corchete de apertura del arreglo")
            }
        }
        return null
    }


    /**
     * <esExpresion> :: =
     * <esExpresionAritmetica> | <esExpresionRelacional> | <esExpresionCadena> || <esExpresionLogica>
     */
    fun esExpresion(): Expresion? {
        var posTokenAux = posicionActual
        var esAritmetica: ExpresionAritmetica? = esExpresionAritmetica()

        if (tokenActual.categoria != Categoria.OPERADOR_RELACIONAL) {
            if (esAritmetica != null) {
                return esAritmetica
            }
        } else {
            hacerBTToken(posTokenAux);
        }

        var esRelacional: ExpresionRelacional? = esExpresionRelacional()
        if (tokenActual.categoria != Categoria.OPERADOR_LOGICO) {
            if (esRelacional != null) {
                return esRelacional
            }
        } else {
            hacerBTToken(posTokenAux);
        }

        var esCadena: ExpresionCadena? = esExpresionCadena()
        if (esCadena != null) {
            return esCadena
        }

        var esLogica: ExpresionLogica? = esExpresionLogica()
        if (esLogica != null) {
            return esLogica
        }

        return null
    }

    /**
     * <esExpresionCadena> ::= <cadena> | <cadena> "+" <expresion>
     * </expresion></cadena></cadena></esExpresionCadena>
     */
    fun esExpresionCadena(): ExpresionCadena? {
        if (tokenActual.categoria == Categoria.CADENA_CARACTERES) {
            val cadena = tokenActual
            obtenerSiguienteToken()
            if (tokenActual.categoria != Categoria.OPERADOR_ARITMETICO && tokenActual.lexema != "+") {
                return ExpresionCadena(cadena)
            } else {
                val mas = tokenActual
                obtenerSiguienteToken()
                val ex = esExpresion()
                if (ex != null) {
                    return ExpresionCadena(cadena, mas, ex)
                } else {
                    reportarErrores("Falta la expresion")
                }
            }
        }
        return null
    }

    /**
     * <esExpresionRelacional> :: = "(" <esExpresionAritmetica> <operadorRelacional>
     * <esExpresionAritmetica>  | falso | verdadero
     */
    fun esExpresionRelacional(): ExpresionRelacional? {
        if (tokenActual.categoria == Categoria.PARENTESIS_IZQUIERDO) {
            obtenerSiguienteToken()
            var operador: Token? = null
            var ea: ExpresionAritmetica? = esExpresionAritmetica()

            if (ea != null) {
                if (tokenActual.categoria == Categoria.OPERADOR_RELACIONAL) {
                    operador = tokenActual
                    obtenerSiguienteToken()
                    var ea1: ExpresionAritmetica? = esExpresionAritmetica()
                    if (ea1 != null) {
                        if (tokenActual.categoria == Categoria.PARENTESIS_DERECHO) {
                            obtenerSiguienteToken()
                           return ExpresionRelacional(ea, operador, ea1)
                        } else {
                            reportarErrores(" falta parentesis derecho ")
                        }

                    } else {
                        reportarErrores(" falta la expresion aritmetica ")
                    }

                } else {
                    reportarErrores(" Falta operador relacional ")
                }
            } else {

                if (tokenActual.categoria == Categoria.PALABRA_RESERVADA
                    && tokenActual.lexema == " verdadero "
                    || tokenActual.categoria == Categoria.PALABRA_RESERVADA
                    && tokenActual.lexema == " falso "
                ) {
                    return ExpresionRelacional(tokenActual)
                }
            }
        } else {
            var operador: Token? = null
            var ea: ExpresionAritmetica? = esExpresionAritmetica()

            if (ea != null) {
                if (tokenActual.categoria == Categoria.OPERADOR_RELACIONAL) {
                    operador = tokenActual
                    obtenerSiguienteToken();
                    var ea1: ExpresionAritmetica? = esExpresionAritmetica()
                    if (ea1 != null) {
                       return ExpresionRelacional(ea, operador, ea1)
                    } else {
                        reportarErrores(" falta la expresion aritmetica ")
                    }
                } else {
                    reportarErrores(" Falta operador relacional ")
                }
            } else {
                if (tokenActual.categoria == Categoria.PALABRA_RESERVADA && tokenActual.lexema == " verdadero "
                    || tokenActual.categoria == Categoria.PALABRA_RESERVADA && tokenActual.lexema == " falso ")
                else {
                    return ExpresionRelacional(tokenActual)
                }
            }
        }
        return null
    }

    /**
     * <ExpreAritmetica>::="(" <ExpreAritmetica> ")" [<EAAX>] | <ValorNumerico>
     * [<EAAX>]
     * </EAAX></ValorNumerico></EAAX></ExpreAritmetica></ExpreAritmetica>
     */
    fun esExpresionAritmetica(): ExpresionAritmetica? {
        if (tokenActual.categoria == Categoria.PARENTESIS_IZQUIERDO) {
            obtenerSiguienteToken()
            val eA: ExpresionAritmetica? = esExpresionAritmetica()
            if (eA != null) {
                if (tokenActual.categoria == Categoria.PARENTESIS_DERECHO) {
                    obtenerSiguienteToken()
                    val eAux: ExpresionAritmeticaAuxiliar? = esExpresionAritmeticaAuxiliar()
                   return ExpresionAritmetica(eA, eAux)
                }
            }
        }
        val vl: ValorNumerico? = esValorNumerico()
        if (vl != null) {
            obtenerSiguienteToken()
            val eAux: ExpresionAritmeticaAuxiliar? = esExpresionAritmeticaAuxiliar()
             return ExpresionAritmetica(vl,eAux)
        }
        return null
    }

    /**
     * <expresionAritmeticaAuxiliar>::=operadorAritmetico<ExpresionAritmetica>[<expresionAritmeticaAuxiliar>]
     * </expresionAritmeticaAuxiliar></ExpresionAritmetica></expresionAritmeticaAuxiliar>
     */
    fun esExpresionAritmeticaAuxiliar(): ExpresionAritmeticaAuxiliar? {
        if (tokenActual.categoria == Categoria.OPERADOR_ARITMETICO) {
            val operador = tokenActual
            obtenerSiguienteToken()
            val eA = esExpresionAritmetica()
            if (eA != null) {
                val eAux: ExpresionAritmeticaAuxiliar? = esExpresionAritmeticaAuxiliar()
                return ExpresionAritmeticaAuxiliar(operador, eA, eAux)
            } else {
                reportarErrores("Falta un termino de la expresion aritmetica")
            }
        }
        return null
    }

    /**
     * <esExpresionLogica>::= "!" <esExpresionRelacional> | <esExpresionRelacional><OperadorLogico> <esExpresionRelacional>
     * </esExpresionRelacional></OperadorLogico></esExpresionRelacional></esExpresionRelacional></esExpresionLogica>
     */
    fun esExpresionLogica(): ExpresionLogica? {
        if (tokenActual.categoria == Categoria.OPERADOR_LOGICO && tokenActual.lexema == "!") {
            val operador = tokenActual
            obtenerSiguienteToken()
            val eR = esExpresionRelacional()
            if (eR != null) {
                return ExpresionLogica(eR, operador)
            } else {
                reportarErrores("falta expresion relacional")
            }
        } else {
            val er = esExpresionRelacional()
            if (er != null) {
                if (tokenActual.categoria == Categoria.OPERADOR_LOGICO && tokenActual.lexema != "!") {
                    val operador = tokenActual
                    obtenerSiguienteToken()
                    val er1 = esExpresionRelacional()
                    if (er1 != null) {
                        return ExpresionLogica(er, er1, operador)
                    } else {
                        reportarErrores("falta la expresion relacional")
                    }
                } else {
                    reportarErrores("falta el operador logico")
                }
            }
        }
        return null
    }

    /**
     * <ValorNumerico>::=[<signo>] entero | [<signo>] real | identificar
     * </signo></signo></ValorNumerico>
     */
    fun esValorNumerico(): ValorNumerico? {
        var Signo: Token? = null
        if (tokenActual.lexema == "+" || tokenActual.lexema == "-") {
            Signo = tokenActual
            obtenerSiguienteToken()
            if (tokenActual.categoria == Categoria.ENTERO || tokenActual.categoria == Categoria.REAL) {
                val Valor = tokenActual
                return ValorNumerico(Signo, Valor)
            } else {
                reportarErrores("falta el valor numerico")
            }
        } else {
            if (tokenActual.categoria == Categoria.ENTERO || tokenActual.categoria == Categoria.REAL) {
                val Valor = tokenActual
                return ValorNumerico(Signo, Valor)
            } else {
                if (tokenActual.categoria == Categoria.IDENTIFICADOR_VARIABLE) {
                    val Valor = tokenActual
                    return ValorNumerico(Signo, Valor)
                }
            }
        }
        return null
    }

    /**
     * <esTermino>::= numeroEntero|identificador|cadenaCaracteres
     * </esTermino>
     */
    fun esTermino(): Termino? {
        if(tokenActual.categoria == Categoria.ENTERO || tokenActual.categoria == Categoria.IDENTIFICADOR_VARIABLE || tokenActual.categoria == Categoria.CADENA_CARACTERES) {
            return Termino(tokenActual)
        } else {
            return null
        }
    }

    /**
     * <ListaArgumentos> ::= <Argumento>[","<ListaArgumentos>]
     * </ListaArgumentos></Argumento></ListaArgumentos>
     */
    fun esListaArgumentos(): ArrayList<Argumento> {
        var argumentos = ArrayList<Argumento>()
        var a: Argumento? = esArgumento()
        while (a != null) {
            argumentos.add(a)
            if (tokenActual.categoria == Categoria.SEPARADOR) {
                obtenerSiguienteToken()
                a = esArgumento()
            } else {
                a = null
            }
        }
        return argumentos
    }

    /**
     * <Argumento> ::= <Expresion>
     * </Expresion></Argumento>
     */
    fun esArgumento(): Argumento? {
        var expresion:Expresion? = esExpresion()
        if(expresion!=null){
            return Argumento(expresion)
        }
        return null
    }

}