import javafx.application.Application
import javafx.scene.Scene
import javafx.scene.control.Label
import javafx.stage.Stage

fun main() {
    Application.launch(DemoApplication::class.java)
}

class DemoApplication : Application() {
    var stage: Stage? = null

    override fun start(stage: Stage) {
        this.stage = stage
        stage.scene = Scene(Label("demo application"))
        stage.show()
    }

    override fun stop() {
        stage = null
    }
}
