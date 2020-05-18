package co.edu.uniquindio.compiladores.controladores
import co.edu.uniquindio.compiladores.sintactico.AnalizadorSintactico
import co.edu.uniquindio.compiladores.lexico.AnalizadorLexico
import co.edu.uniquindio.compiladores.lexico.ErrorLexico
import co.edu.uniquindio.compiladores.lexico.Token
import co.edu.uniquindio.compiladores.semantico.AnalizadorSemantico
import co.edu.uniquindio.compiladores.sintactico.ErrorSintactico
import javafx.collections.FXCollections
import javafx.event.ActionEvent
import javafx.fxml.FXML
import javafx.fxml.Initializable
import javafx.scene.control.*
import javafx.scene.control.cell.PropertyValueFactory
import java.net.URL
import java.util.*
import kotlin.collections.ArrayList

class InicioController : Initializable{
    var listaCodigo = ArrayList<ArrayList<Token>>()
    @FXML lateinit var txtCodigo:TextArea

    @FXML lateinit var tablaPrincipal: TableView<Token>
    @FXML lateinit var colFila: TableColumn<Token, String>
    @FXML lateinit var colColumna: TableColumn<Token, String>
    @FXML lateinit var colCategoria: TableColumn<Token, String>
    @FXML lateinit var colLexema: TableColumn<Token, String>

    @FXML lateinit var tablaError: TableView<ErrorLexico>
    @FXML lateinit var colError: TableColumn<ErrorLexico, String>
    @FXML lateinit var colFilaError: TableColumn<ErrorLexico, String>
    @FXML lateinit var colColumnaError: TableColumn<ErrorLexico, String>

    @FXML lateinit var tablaErrorSintactico: TableView<ErrorSintactico>
    @FXML lateinit var colErrSintactico: TableColumn<ErrorSintactico, String>
    @FXML lateinit var colFilaSintactico: TableColumn<ErrorSintactico, String>
    @FXML lateinit var colColSintactico: TableColumn<ErrorSintactico, String>

    @FXML lateinit var arbolVisual: TreeView<String>


    override fun initialize(location: URL?, resources: ResourceBundle?){
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
    }

    @FXML
    fun analizar(e: ActionEvent) {
        if(txtCodigo.text.length>0){
            var lexico = AnalizadorLexico(txtCodigo.text)
            lexico.analizar()

            tablaPrincipal.items = FXCollections.observableArrayList(lexico.listaTokens)
            tablaError.items = FXCollections.observableArrayList(lexico.listaErrores)
          //  print(lexico.listaTokens)
          //  print(lexico.listaErrores)
            if(lexico.listaErrores.isEmpty()){
                var sintactico = AnalizadorSintactico(lexico.listaTokens)
                var uC = sintactico.esUnidadDeCompilacion()
                tablaErrorSintactico.items = FXCollections.observableArrayList(sintactico.listaErrores)
             //   print(lexico.listaErrores)
                if(uC != null){
                    arbolVisual.root = uC.getArbolVisual()
                    var semantico = AnalizadorSemantico(uC)
                    semantico.llenarTablaSimbolos()
                    print(semantico.tablaSimbolos)
                    print(semantico.erroresSemanticos)
                }else{
                    var alerta = Alert(Alert.AlertType.WARNING)
                    alerta.headerText = "CUIDADO"
                    alerta.contentText="Hay errores lexicos en el codigo fuente"
                }
            }
        }
    }

    @FXML
    fun limpiar(e: ActionEvent) {
        txtCodigo.clear()
    }
}