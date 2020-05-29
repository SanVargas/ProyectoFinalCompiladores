package co.edu.uniquindio.compiladores.lexico

/**
 * Clase principal del analizador lexico
 * @author Sebastian Ceballos Arias, Andres Santiago Vargas
 */
class AnalizadorLexico(var codigoFuente: String) {

    /**
     * varibales globales de la clase
     */
    var posicionActual = 0
    var caracterActual = codigoFuente[0]
    var listaTokens = ArrayList<Token>()
    var listaErrores = ArrayList<ErrorLexico>()
    var finCodigo = 0.toChar()
    var filaActual = 0
    var columnaActual = 0

    /**
     * funcion que crea un token
     */
    fun almacenarToken(lexema: String, categoria: Categoria, fila: Int, columna: Int) {
        listaTokens.add(Token(lexema, categoria, fila, columna))
    }

    /**
     * funcion que permite solucionar problemas con backtraking
     */
    fun hacerBT(filaInicial: Int, columnaInicial: Int, posicionInicial: Int) {
        posicionActual = posicionInicial
        filaActual = filaInicial
        columnaActual = columnaInicial
        caracterActual = codigoFuente[posicionActual]
    }

    /**
     * Funcion que permite evitar porblemas sobre iteraciones
     * se encarga de aumentar y actualizar la posicion actual
     * se encarga de verificar que no hallan desbordes
     */
    fun obtenerSiguienteCaracter() {

        if (posicionActual == codigoFuente.length - 1) {
            caracterActual = finCodigo
        } else {
            if (caracterActual == '\n') {
                filaActual++
                columnaActual = 0
            } else {
                columnaActual++
            }
            posicionActual++
            caracterActual = codigoFuente[posicionActual]
        }
    }

    /**
     * funcion que nor permite ver analizar las exoresiones y clasificarlas
     */
    fun analizar() {

        while (caracterActual != finCodigo) {

            if (caracterActual == ' ' || caracterActual == '\t' || caracterActual == '\n') {
                obtenerSiguienteCaracter()
                continue
            }

            if (esEntero()) continue //Bueno
            if (esPalabraReservada()) continue //Bueno
            if (esDecimal()) continue //Bueno
            if (esOperadorAritmeticos()) continue //Bueno
            if (esOperadorAsignacion()) continue //Bueno
            if (esOperadorIncremento()) continue //Bueno
            if (esOperadorDecremento()) continue //Bueno
            if (esCaracter()) continue //Bueno
            if (esCadenaCaracteres()) continue //Bueno
            if (esLlaveDerecha()) continue //Bueno
            if (esLlaveIzquierda()) continue //Bueno
            if (esCorcheteDerecho()) continue //Bueno
            if (esCorcheteIzquierdo()) continue //Bueno
            if (esParentesisDerecho()) continue //Bueno
            if (esParentesisIzquierdo()) continue //Bueno
            if (esOperadorRelacional()) continue //Bueno
            if (esOperadorLogico()) continue //Bueno
            if (esSeperador()) continue //Bueno
            if (esPunto()) continue //Bueno
            if (esDosPuntos()) continue //Bueno
            if (esComentarioBloque()) continue //Bueno
            if (esFinSentencia()) continue //Bueno
            if (esComentarioLinea()) continue //Bueno


            almacenarToken(
                "" + caracterActual,
                Categoria.DESCONOCIDO, filaActual, columnaActual
            )
            obtenerSiguienteCaracter()
        }

    }


    /**
     * funcion que nos permite saber si un expresion es entera
     */
    fun esEntero(): Boolean {

        if (caracterActual.isDigit()) {

            var lexema = ""
            var filaInicial = filaActual
            var columnaInicial = columnaActual
            var posicionInicial = posicionActual
            lexema += caracterActual
            obtenerSiguienteCaracter()

            while (caracterActual.isDigit() || caracterActual == '.') {
                lexema += caracterActual
                if (caracterActual == '.') {
                    hacerBT(filaInicial, columnaInicial, posicionInicial)
                    return false
                }
                obtenerSiguienteCaracter()
            }


            almacenarToken(lexema, Categoria.ENTERO, filaInicial, columnaInicial)
            return true
        }
        return false
    }

    /**
     *  Funcion que permite saber si una expresion es decimal
     */
    fun esDecimal(): Boolean {

        if (caracterActual.isDigit() || caracterActual == '.') {

            var lexema = ""
            var filaInicial = filaActual
            var columnaInicial = columnaActual
            var posicionInicial = posicionActual

            if (caracterActual == '.') {
                lexema += caracterActual
                obtenerSiguienteCaracter()

                if (caracterActual.isDigit()) {

                    lexema += caracterActual
                    obtenerSiguienteCaracter()

                } else {
                    hacerBT(filaInicial, columnaInicial, posicionInicial)
                    return false
                }
            } else {

                lexema += caracterActual
                obtenerSiguienteCaracter()

                while (caracterActual.isDigit()) {

                    lexema += caracterActual
                    obtenerSiguienteCaracter()
                }

                if (caracterActual == '.') {
                    lexema += caracterActual
                    obtenerSiguienteCaracter()
                }
            }

            while (caracterActual.isDigit()) {

                lexema += caracterActual
                obtenerSiguienteCaracter()
            }
            if (caracterActual == '.') {
                hacerBT(filaInicial, columnaInicial, posicionInicial)
                return false

            }
            almacenarToken(
                lexema,
                Categoria.DECIMAL, filaInicial, columnaInicial
            )
            return true

        }

        return false
    }

    /**
     * Funcion que permite saber si un expresion es una palabra reservada
     */
    fun esPalabraReservada(): Boolean {

        if (caracterActual.isLetter()) {

            var lexema = ""
            var filaInicial = filaActual
            var columnaInicial = columnaActual
            var posicionInicial = posicionActual
            lexema += caracterActual
            obtenerSiguienteCaracter()

            while (caracterActual.isLetter()) {
                lexema += caracterActual


                obtenerSiguienteCaracter()

            }


            if (lexema == "fun" || lexema == "si"|| lexema == "imprimir" || lexema == "impInversa" || lexema == "retorno"
                || lexema == "leer" || lexema == "leerInverso"|| lexema=="entero"|| lexema=="decimal"|| lexema=="vacio"
                || lexema=="verdadero" || lexema=="falso"
                || lexema == "log" || lexema == "dom" || lexema == "mientras"|| lexema == "entonces"|| lexema == "ademas"|| lexema == "cadena" || lexema == "imp") {
                obtenerSiguienteCaracter()
                if (!caracterActual.isDigit() || !caracterActual.isLetter()) {

                    filaInicial = filaActual
                    columnaInicial = columnaActual
                    almacenarToken(lexema, Categoria.PALABRA_RESERVADA, filaInicial, columnaInicial)
                    return true
                }

            } else {

                filaInicial = filaActual
                columnaInicial = columnaActual
                almacenarToken(lexema, Categoria.IDENTIFICADOR_VARIABLE, filaInicial, columnaInicial)
                return true
            }

        }
        return false
    }


    /**
     *  Funcion que permite saber si una expresion es un operador aritmetico
     *
     */
    fun esOperadorAritmeticos(): Boolean {


        if (caracterActual == '+') {

            var lexema = ""
            var filaInicial = filaActual
            var columnaInicial = columnaActual
            var posicionInicial = posicionActual
            lexema += caracterActual
            obtenerSiguienteCaracter()

            if (caracterActual == '+' || caracterActual == '=' || caracterActual == '&') {
                hacerBT(filaInicial, columnaInicial, posicionInicial)
                return false

            } else {
                almacenarToken(
                    lexema,
                    Categoria.OPERADOR_ARITMETICO, filaInicial, columnaInicial
                )
                return true
            }

        } else if (caracterActual == '-') {

            var lexema = ""
            var filaInicial = filaActual
            var columnaInicial = columnaActual
            var posicionInicial = posicionActual
            lexema += caracterActual
            obtenerSiguienteCaracter()

            if (caracterActual == '-' || caracterActual == '=' || caracterActual == '&') {
                hacerBT(filaInicial, columnaInicial, posicionInicial)
                return false

            } else {
                almacenarToken(
                    lexema,
                    Categoria.OPERADOR_ARITMETICO, filaInicial, columnaInicial
                )
                return true
            }
        } else if (caracterActual == '/') {

            var lexema = ""
            var filaInicial = filaActual
            var columnaInicial = columnaActual
            var posicionInicial = posicionActual
            lexema += caracterActual
            obtenerSiguienteCaracter()

            if (caracterActual == '-' || caracterActual == '=' || caracterActual == '*') {
                hacerBT(filaInicial, columnaInicial, posicionInicial)
                return false

            } else {
                almacenarToken(
                    lexema,
                    Categoria.OPERADOR_ARITMETICO, filaInicial, columnaInicial
                )
                return true
            }
        } else if (caracterActual == '*' || caracterActual == '%') {

            var lexema = ""
            var filaInicial = filaActual
            var columnaInicial = columnaActual
            var posicionInicial = posicionActual
            lexema += caracterActual
            obtenerSiguienteCaracter()

            if (caracterActual == '=') {
                hacerBT(filaInicial, columnaInicial, posicionInicial)
                return false

            } else {
                almacenarToken(
                    lexema,
                    Categoria.OPERADOR_ARITMETICO, filaInicial, columnaInicial
                )
                return true
            }
        }
        return false
    }

    /**
     * Funcion que permite saber si la expresion es un operador de asignacion
     */
    fun esOperadorAsignacion(): Boolean {

        if (caracterActual == '=' || caracterActual == '+' || caracterActual == '-' || caracterActual == '*' || caracterActual == '/') {

            var lexema = ""
            var filaInicial = filaActual
            var columnaInicial = columnaActual
            var posicionInicial = posicionActual
            lexema += caracterActual

            if (caracterActual == '=') {
                obtenerSiguienteCaracter()
                if (caracterActual == '=') {
                    hacerBT(filaInicial, columnaInicial, posicionInicial)
                    return false
                } else {

                    filaInicial = filaActual
                    columnaInicial = columnaActual
                    almacenarToken(lexema, Categoria.OPERADOR_ASIGNACION, filaInicial, columnaInicial)
                    return true
                }
            }
            obtenerSiguienteCaracter()
            if (caracterActual == '=') {
                lexema+= caracterActual
                obtenerSiguienteCaracter()
                filaInicial = filaActual
                columnaInicial = columnaActual


                almacenarToken(lexema, Categoria.OPERADOR_ASIGNACION, filaInicial, columnaInicial)
                return true
            }

            hacerBT(filaInicial, columnaInicial, posicionInicial)
            return false

        }

        return false
    }

    /**
     * Funcion que permite saber si una expresion es un operador de incremento
     */
    fun esOperadorIncremento(): Boolean {
        if (caracterActual == '+') {
            var lexema = ""
            var filaInicial = filaActual
            var columnaInicial = columnaActual
            var posicionInicial = posicionActual
            lexema += caracterActual
            obtenerSiguienteCaracter()

            if (caracterActual == '&') {
                filaInicial = filaActual
                columnaInicial = columnaActual
                lexema += caracterActual
                obtenerSiguienteCaracter()

                if (caracterActual == '+') {
                    filaInicial = filaActual
                    columnaInicial = columnaActual
                    lexema += caracterActual
                    almacenarToken(
                        lexema,
                        Categoria.OPERADOR_INCREMENTO, filaInicial, columnaInicial
                    )
                    obtenerSiguienteCaracter()
                    return true
                }
            } else {
                hacerBT(filaInicial, columnaInicial, posicionInicial)
                return false
            }
        }
        return false
    }

    /**
     *
     * Funcion que permite saber su una expresion en un Operador de Decremento
     */
    fun esOperadorDecremento(): Boolean {

        if (caracterActual == '-') {

            var lexema = ""
            var filaInicial = filaActual
            var columnaInicial = columnaActual
            var posicionInicial = posicionActual
            lexema += caracterActual
            obtenerSiguienteCaracter()

            if (caracterActual == '&') {
                filaInicial = filaActual
                columnaInicial = columnaActual
                lexema += caracterActual
                obtenerSiguienteCaracter()

                if (caracterActual == '-') {
                    filaInicial = filaActual
                    columnaInicial = columnaActual
                    lexema += caracterActual
                    almacenarToken(
                        lexema,
                        Categoria.OPERADOR_DECREMENTO, filaInicial, columnaInicial
                    )
                    obtenerSiguienteCaracter()
                    return true

                }


            } else {
                hacerBT(filaInicial, columnaInicial, posicionInicial)
                return false
            }
        }
        return false
    }

    /**
     *
     * Funcion que permite saber si una expresion es un caracter
     */
    fun esCaracter(): Boolean {
        if (caracterActual != '_') {
            return false
        }
        var posInicio = posicionActual
        var filaInicio = filaActual
        var columnaInicio = columnaActual
        var lexema = ""
        lexema += caracterActual
        obtenerSiguienteCaracter()

        if (caracterActual == '_') {
            listaErrores.add(
                ErrorLexico(
                    "No existe el caracter",
                    filaActual,
                    columnaActual
                )
            )
            return true
        }

        lexema += caracterActual
        obtenerSiguienteCaracter()

        if (caracterActual == finCodigo) {
            listaErrores.add(
                ErrorLexico(
                    "No se cerro el caracter con un _ ",
                    filaActual,
                    columnaActual
                )
            )
            return true
        }
        if (caracterActual != '_') {
            listaErrores.add(
                ErrorLexico(
                    "Un caracter es un solo simbolo y no cerro con un _",
                    filaActual,
                    columnaActual
                )
            )
            return true
        }

        posInicio = posicionActual
        filaInicio = filaActual
        columnaInicio = columnaActual
        lexema += caracterActual
        listaTokens.add(
            Token(
                lexema,
                Categoria.CARACTER,
                filaActual,
                columnaActual
            )
        )
        obtenerSiguienteCaracter()
        return true
    }

    /**
     *
     * Funcion que permite saber si una expresion es una cadena de caracteres
     */
    fun esCadenaCaracteres(): Boolean {


        if (caracterActual != '«') {
            return false
        }
        var posicionInicial = posicionActual
        var filaInicio = filaActual
        var columnaInicio = columnaActual
        var lexema = ""
        lexema += caracterActual
        obtenerSiguienteCaracter()


        while (caracterActual != '»') {
            lexema += caracterActual
            obtenerSiguienteCaracter()
            if (caracterActual == finCodigo) {
                listaErrores.add(
                    ErrorLexico(
                        "ERROR: DEBE CERRAR LA CADENA '!' ",
                        filaActual,
                        columnaActual
                    )
                )
                posicionInicial = posicionActual
                filaInicio = filaActual
                columnaInicio = columnaActual
                hacerBT(filaInicio, columnaInicio, posicionInicial)
                obtenerSiguienteCaracter()
                return true
            }
        }
        lexema += caracterActual
        listaTokens.add(
            Token(
                lexema,
                Categoria.CADENA_CARACTERES,
                filaActual,
                columnaActual
            )
        )
        obtenerSiguienteCaracter()
        return true
    }

    /**
     *
     * Funcion que permite saber si una expresion es un operador relacional
     */
    fun esOperadorRelacional(): Boolean {
        if (caracterActual != '!' && caracterActual != '=' && caracterActual != '<' && caracterActual != '>') {
            return false
        }
        val posInicio = posicionActual
        val filaInicio = filaActual
        val columnaInicio = columnaActual
        var lexema = ""
        lexema += caracterActual

        if (caracterActual == '<' || caracterActual == '>') {
            obtenerSiguienteCaracter()
            if (caracterActual == '=') {
                lexema += caracterActual
                listaTokens.add(
                    Token(
                        lexema,
                        Categoria.OPERADOR_RELACIONAL,
                        filaActual,
                        columnaActual
                    )
                )
                obtenerSiguienteCaracter()
                return true
            }
            listaTokens.add(
                Token(
                    lexema,
                    Categoria.OPERADOR_RELACIONAL,
                    filaActual,
                    columnaActual
                )
            )
            return true
        }

        obtenerSiguienteCaracter()
        if (caracterActual == '=') {
            lexema += caracterActual
            listaTokens.add(
                Token(
                    lexema!!,
                    Categoria.OPERADOR_RELACIONAL,
                    filaActual,
                    columnaActual
                )
            )
            obtenerSiguienteCaracter()
            return true
        }
        hacerBT(posInicio, filaInicio, columnaInicio)
        return false
    }

    /**
     *
     * Funcion que permite saber si una expresion es un operador logico
     */
    fun esOperadorLogico(): Boolean {
        if (caracterActual != '!' && caracterActual != '&' && caracterActual != '|') {
            return false
        }
        val posInicio = posicionActual
        val filaInicio = filaActual
        val columnaInicio = columnaActual
        var lexema = ""
        lexema += caracterActual
        if (caracterActual == '!') {
            obtenerSiguienteCaracter()
            if (caracterActual == '=') {
                hacerBT(posInicio, filaInicio, columnaInicio)
                return false
            }
            listaTokens.add(
                Token(
                    lexema,
                    Categoria.OPERADOR_LOGICO,
                    filaActual,
                    columnaActual
                )
            )
            obtenerSiguienteCaracter()
            return true
        }
        if (caracterActual == '&') {
            obtenerSiguienteCaracter()
            if (caracterActual == '&') {
                lexema += caracterActual
                listaTokens.add(
                    Token(
                        lexema,
                        Categoria.OPERADOR_LOGICO,
                        filaActual,
                        columnaActual
                    )
                )
                obtenerSiguienteCaracter()
                return true
            }
            hacerBT(posInicio, filaInicio, columnaInicio)
            return false
        }
        if (caracterActual == '|') {
            obtenerSiguienteCaracter()
            if (caracterActual == '|') {
                lexema += caracterActual
                listaTokens.add(
                    Token(
                        lexema,
                        Categoria.OPERADOR_LOGICO,
                        filaActual,
                        columnaActual
                    )
                )
                obtenerSiguienteCaracter()
                return true
            }
            hacerBT(posInicio, filaInicio, columnaInicio)
        }
        return false
    }

    /**
     *
     * Funcion que permite saber si una expresion es un operador logico
     */
    fun esSeperador(): Boolean {
        if (caracterActual != ',') {
            return false
        }
        val posInicio = posicionActual
        val filaInicio = filaActual
        val columnaInicio = columnaActual
        var lexema = ""
        lexema += caracterActual
        listaTokens.add(
            Token(
                lexema,
                Categoria.SEPARADOR,
                filaInicio,
                columnaInicio
            )
        )
        obtenerSiguienteCaracter()
        return true
    }

    /**
     * Funcion que permite saber si una expresion es un punto
     */
    fun esPunto(): Boolean {
        if (caracterActual != '.') {
            return false
        }
        val posInicio = posicionActual
        val filaInicio = filaActual
        val columnaInicio = columnaActual
        var lexema = ""
        lexema += caracterActual
        obtenerSiguienteCaracter()
        if (Character.isDigit(caracterActual)) {
            hacerBT(posInicio, filaInicio, columnaInicio)
            return false
        }
        listaTokens.add(
            Token(
                lexema,
                Categoria.PUNTO,
                filaActual,
                columnaActual
            )
        )
        return true
    }

    /**
     * Funcion que permite saber si una expresion es dos puntos
     */
    fun esDosPuntos(): Boolean {
        if (caracterActual != ':') {
            return false
        }
        val posInicio = posicionActual
        val filaInicio = filaActual
        val columnaInicio = columnaActual
        var lexema = ""
        lexema += caracterActual
        obtenerSiguienteCaracter()
        listaTokens.add(
            Token(lexema, Categoria.DOS_PUNTOS, filaActual, columnaActual)
        )
        return true
    }

    /**
     * Funcion que permite saber si una expresion es comentario de bloque
     */
    fun esComentarioBloque(): Boolean {
        if (caracterActual != '~') {
            return false
        }
        var posicionInicial = posicionActual
        var filaInicio = filaActual
        var columnaInicio = columnaActual
        var lexema = ""
        lexema += caracterActual
        obtenerSiguienteCaracter()


        while (caracterActual != '~') {
            lexema += caracterActual
            obtenerSiguienteCaracter()
            if (caracterActual == finCodigo) {
                listaErrores.add(
                    ErrorLexico(
                        "ERROR: DEBE CERRAR LA CADENA '!' ",
                        filaActual,
                        columnaActual
                    )
                )
                posicionInicial = posicionActual
                filaInicio = filaActual
                columnaInicio = columnaActual
                hacerBT(filaInicio, columnaInicio, posicionInicial)
                obtenerSiguienteCaracter()
                return true
            }
        }
        lexema += caracterActual
        listaTokens.add(
            Token(
                lexema,
                Categoria.COMENTARIO_BLOQUE,
                filaActual,
                columnaActual
            )
        )
        obtenerSiguienteCaracter()
        return true
    }

    /**
     * Funcion que permite saber si una expresion es comentario de linea
     */
    fun esComentarioLinea(): Boolean {
        if (caracterActual == '©') {

            var lexema = ""
            val posInicio = posicionActual
            val filaInicial = filaActual
            val columnaInicial = columnaActual
            lexema += caracterActual



            almacenarToken(
                lexema,
                Categoria.COMENTARIO_LINEA, filaInicial, columnaInicial
            )
            obtenerSiguienteCaracter()
            return true
        }

        return false
    }


    /**
     * Funcion que permite saber si una expresion es un agrupador llave izquierda
     */
    fun esLlaveIzquierda(): Boolean {

        if (caracterActual != '{') {
            return false
        }

        val posInicio = posicionActual
        val filaInicio = filaActual
        val columnaInicio = columnaActual
        var lexema = ""
        lexema += caracterActual
        almacenarToken(
            lexema,
            Categoria.LLAVE_IZQUIERDA, filaInicio, columnaInicio
        )
        obtenerSiguienteCaracter()
        return true
    }

    /**
     * Funcion que permite saber s una expresion es un agrupador llave derecha
     */
    fun esLlaveDerecha(): Boolean {

        if (caracterActual != '}') {
            return false
        }

        val posInicio = posicionActual
        val filaInicio = filaActual
        val columnaInicio = columnaActual
        var lexema = ""
        lexema += caracterActual
        almacenarToken(
            lexema,
            Categoria.LLAVE_DERECHA, filaInicio, columnaInicio
        )
        obtenerSiguienteCaracter();
        return true

    }

    /**
     * funcion que permite saber su una expresion es un agrupador corchete izquierdo
     */
    fun esCorcheteIzquierdo(): Boolean {
        if (caracterActual != '[') {
            return false
        }
        val posInicio = posicionActual
        val filaInicio = filaActual
        val columnaInicio = columnaActual
        var lexema = ""
        lexema += caracterActual
        almacenarToken(
            lexema,
            Categoria.CORCHETE_IZQUIERDO, filaInicio, columnaInicio
        )
        obtenerSiguienteCaracter()
        return true
    }

    /**
     *  funcion que permite saber su una expresion es un agrupador corchete derecho
     */
    fun esCorcheteDerecho(): Boolean {
        if (caracterActual != ']') {
            return false
        }
        val posInicio = posicionActual
        val filaInicio = filaActual
        val columnaInicio = columnaActual
        var lexema = ""
        lexema += caracterActual
        almacenarToken(
            lexema,
            Categoria.CORCHETE_DERECHO, filaInicio, columnaInicio
        )
        obtenerSiguienteCaracter()
        return true
    }

    /**
     *  funcion que permite saber su una expresion es un agrupador parentesis izquierdo
     */
    fun esParentesisIzquierdo(): Boolean {
        if (caracterActual != '(') {
            return false
        }
        val posInicio = posicionActual
        val filaInicio = filaActual
        val columnaInicio = columnaActual
        var lexema = ""
        lexema += caracterActual
        almacenarToken(
            lexema,
            Categoria.PARENTESIS_IZQUIERDO, filaInicio, columnaInicio
        )
        obtenerSiguienteCaracter()
        return true
    }

    /**
     *  funcion que permite saber su una expresion es un agrupador parentesis derecho
     */
    fun esParentesisDerecho(): Boolean {
        if (caracterActual != ')') {
            return false
        }
        val posInicio = posicionActual
        val filaInicio = filaActual
        val columnaInicio = columnaActual
        var lexema = ""
        lexema += caracterActual
        almacenarToken(
            lexema,
            Categoria.PARENTESIS_DERECHO, filaInicio, columnaInicio
        )
        obtenerSiguienteCaracter()
        return true
    }


    /**
     *Funcion que permite saber si una expresion es un fin de sentencia
     */
    fun esFinSentencia(): Boolean {

        if (caracterActual == '°') {

            var lexema = ""
            var filaInicial = filaActual
            var columnaInicial = columnaActual
            var posicionInicial = posicionActual
            lexema += caracterActual
            obtenerSiguienteCaracter()
            almacenarToken(
                lexema,
                Categoria.FIN_SENTENCIA, filaInicial, columnaInicial
            )
            return true
        }

        return false
    }


}