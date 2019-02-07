import javafx.application.Application
import javafx.scene.Scene
import javafx.scene.control.Label
import javafx.stage.Stage

fun main() {
    Application.launch(DemoApplication::class.java)
}

class DemoApplication : Application() {
    override fun start(stage: Stage) {
        stage.scene = Scene(Label("demo application"))
        stage.show()
    }
}
