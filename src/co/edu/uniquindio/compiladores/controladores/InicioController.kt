package co.edu.uniquindio.compiladores.controladores

import co.edu.uniquindio.compiladores.sintactico.AnalizadorSintactico
import co.edu.uniquindio.compiladores.lexico.AnalizadorLexico
import co.edu.uniquindio.compiladores.lexico.ErrorLexico
import co.edu.uniquindio.compiladores.lexico.Token
import co.edu.uniquindio.compiladores.semantico.AnalizadorSemantico
import co.edu.uniquindio.compiladores.semantico.ErrorSemantico
import co.edu.uniquindio.compiladores.sintactico.ErrorSintactico
import co.edu.uniquindio.compiladores.sintactico.UnidadDeCompilacion
import javafx.collections.FXCollections
import javafx.event.ActionEvent
import javafx.fxml.FXML
import javafx.fxml.Initializable
import javafx.scene.control.*
import javafx.scene.control.cell.PropertyValueFactory
import javafx.scene.text.TextFlow
import java.io.File
import java.net.URL
import java.util.*
import kotlin.collections.ArrayList

class InicioController : Initializable {
    lateinit var lexico: AnalizadorLexico
    lateinit var sintactico: AnalizadorSintactico
    lateinit var semantico: AnalizadorSemantico
    lateinit var uC: UnidadDeCompilacion

    @FXML
    lateinit var txtCodigo: TextArea
    @FXML
    lateinit var txtJavaCodigo: TextArea

    @FXML
    lateinit var tablaPrincipal: TableView<Token>
    @FXML
    lateinit var colFila: TableColumn<Token, String>
    @FXML
    lateinit var colColumna: TableColumn<Token, String>
    @FXML
    lateinit var colCategoria: TableColumn<Token, String>
    @FXML
    lateinit var colLexema: TableColumn<Token, String>

    @FXML
    lateinit var tablaError: TableView<ErrorLexico>
    @FXML
    lateinit var colError: TableColumn<ErrorLexico, String>
    @FXML
    lateinit var colFilaError: TableColumn<ErrorLexico, String>
    @FXML
    lateinit var colColumnaError: TableColumn<ErrorLexico, String>

    @FXML
    lateinit var tablaErrorSintactico: TableView<ErrorSintactico>
    @FXML
    lateinit var colErrSintactico: TableColumn<ErrorSintactico, String>
    @FXML
    lateinit var colFilaSintactico: TableColumn<ErrorSintactico, String>
    @FXML
    lateinit var colColSintactico: TableColumn<ErrorSintactico, String>

    @FXML
    lateinit var tablaErrorSemantico: TableView<ErrorSemantico>
    @FXML
    lateinit var colErrSemantico: TableColumn<ErrorSemantico, String>
    @FXML
    lateinit var colFilaSemantico: TableColumn<ErrorSemantico, String>
    @FXML
    lateinit var colColSemantico: TableColumn<ErrorSemantico, String>

    @FXML
    lateinit var arbolVisual: TreeView<String>


    override fun initialize(location: URL?, resources: ResourceBundle?) {
        colFila.cellValueFactory = PropertyValueFactory("fila")
        colColumna.cellValueFactory = PropertyValueFactory("columna")
        colCategoria.cellValueFactory = PropertyValueFactory("categoria")
        colLexema.cellValueFactory = PropertyValueFactory("lexema")

        colFilaError.cellValueFactory = PropertyValueFactory("fila")
        colColumnaError.cellValueFactory = PropertyValueFactory("columna")
        colError.cellValueFactory = PropertyValueFactory("mensaje")

        colFilaSintactico.cellValueFactory = PropertyValueFactory("fila")
        colColSintactico.cellValueFactory = PropertyValueFactory("columna")
        colErrSintactico.cellValueFactory = PropertyValueFactory("mensaje")

        colFilaSemantico.cellValueFactory = PropertyValueFactory("fila")
        colColSemantico.cellValueFactory = PropertyValueFactory("columna")
        colErrSemantico.cellValueFactory = PropertyValueFactory("mensaje")
    }

    @FXML
    fun analizar(e: ActionEvent) {
        if (txtCodigo.text.length > 0) {
            lexico = AnalizadorLexico(txtCodigo.text)
            lexico.analizar()
            tablaPrincipal.items = FXCollections.observableArrayList(lexico.listaTokens)
            tablaError.items = FXCollections.observableArrayList(lexico.listaErrores)
            if (lexico.listaErrores.isEmpty()) {
                sintactico = AnalizadorSintactico(lexico.listaTokens)
                uC = sintactico.esUnidadDeCompilacion()!!
                tablaErrorSintactico.items = FXCollections.observableArrayList(sintactico.listaErrores)
                if (sintactico.listaErrores.isEmpty() && uC!=null) {
                    arbolVisual.root = uC.getArbolVisual()
                    semantico = AnalizadorSemantico(uC)
                    semantico.llenarTablaSimbolos()
                    semantico.analizarSemantica()
                    tablaErrorSemantico.items = FXCollections.observableArrayList(semantico.erroresSemanticos)
                } else {
                    var alerta = Alert(Alert.AlertType.WARNING)
                    alerta.headerText = "CUIDADO"
                    alerta.contentText = "Hay errores sintacticos en el codigo fuente"
                    alerta.show()
                }
            }else {
                var alerta = Alert(Alert.AlertType.WARNING)
                alerta.headerText = "CUIDADO"
                alerta.contentText = "Hay errores lexicos en el codigo fuente"
                alerta.show()
            }
        }else {
            var alerta = Alert(Alert.AlertType.WARNING)
            alerta.headerText = "CUIDADO"
            alerta.contentText = "No hay codigo fuente"
            alerta.show()
        }
    }

    @FXML
    fun traducirCodigo(e: ActionEvent) {
        if (lexico.listaErrores.isEmpty() && sintactico.listaErrores.isEmpty() && semantico.erroresSemanticos.isEmpty()) {
            var borrar = 1
            var codigo: String = uC.getJavaCode()

            File("src/Principal.java").writeText(codigo)
            var runTime = Runtime.getRuntime().exec("java src/Principal.java")
            runTime.waitFor()
            Runtime.getRuntime().exec("java Principal", null, File("src"))

            txtJavaCodigo.appendText(codigo)
        }else{
            var alerta = Alert(Alert.AlertType.WARNING)
            alerta.headerText = "Error"
            alerta.contentText = "Verifique los errores del codigo fuente"
            alerta.show()
        }
    }
    @FXML
    fun limpiar(e: ActionEvent) {
        tablaPrincipal.items.clear()
        tablaError.items.clear()
        tablaErrorSintactico.items.clear()
        tablaErrorSemantico.items.clear()
        txtJavaCodigo.clear()
        txtCodigo.clear()
    }
}