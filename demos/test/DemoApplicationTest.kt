import com.sun.javafx.application.PlatformImpl
import javafx.stage.Stage
import java.util.concurrent.CountDownLatch
import kotlin.test.BeforeTest
import kotlin.test.Test
import kotlin.test.assertNotNull
import kotlin.test.assertNull

class DemoApplicationTest {
    init {
        // java version: 1.8.0_172
        println("java version: ${System.getProperty("java.version")}")

        val barrier = CountDownLatch(1)
        PlatformImpl.startup { barrier.countDown() }
        barrier.await()
    }


    @BeforeTest
    fun before() {
    }

    @Test
    fun `call start method` () {
        // given:
        val application = DemoApplication()

        // when:
        PlatformImpl.runAndWait {
            val stage = Stage()
            application.start(stage)
        }

        // then:
        assertNotNull(application.stage)
    }

    @Test
    fun `call start and stop methods` () {
        // given:
        val application = DemoApplication()

        // when:
        PlatformImpl.runAndWait {
            val stage = Stage()
            application.start(stage)
            application.stop()
        }

        // then:
        assertNull(application.stage)
    }
}
