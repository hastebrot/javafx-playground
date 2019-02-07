import com.sun.javafx.application.PlatformImpl
import javafx.stage.Stage
import java.util.concurrent.CountDownLatch
import kotlin.test.BeforeTest
import kotlin.test.Test
import kotlin.test.assertEquals

class DemoApplicationTest {
    init {
        val barrier = CountDownLatch(1)
        PlatformImpl.startup { barrier.countDown() }
        barrier.await()
    }


    @BeforeTest
    fun before() {
    }

    @Test
    fun `call start and stop methods` () {
        // given:
        val stage = Stage()
        val application = DemoApplication()

        // when:
        application.start(stage)
        application.stop()

        // then:
        assertEquals(4, 2 + 2)
    }

    @Test
    fun `call start and stop methods again` () {
        // given:
        val stage = Stage()
        val application = DemoApplication()

        // when:
        application.start(stage)
        application.stop()

        // then:
        assertEquals(4, 2 + 2)
    }
}
