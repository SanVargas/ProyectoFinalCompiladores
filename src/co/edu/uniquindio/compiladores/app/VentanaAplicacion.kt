package co.edu.uniquindio.compiladores.app
import javafx.application.Application
import javafx.fxml.FXMLLoader
import javafx.scene.Parent
import javafx.scene.Scene
import javafx.stage.Stage

/**
 * Clase principal donde se inicializa la ventana y la clase del
 * analizador lexico
 * @author Sebastian Ceballos Arias, Andres Santiago Vargas
 */
class VentanaAplicacion : Application(){

    override fun start(primaryStage:  Stage?){
        val loader = FXMLLoader(VentanaAplicacion::class.java.getResource( "/inicio.fxml"))
        val parent:Parent = loader.load()
        val scene = Scene(parent)
        primaryStage?.scene = scene
        primaryStage?.title = "S & S"
        primaryStage?.show()
        }

    companion object {
        @JvmStatic
        fun main(args: Array<String>) {
            launch(VentanaAplicacion::class.java)
        }
    }
}