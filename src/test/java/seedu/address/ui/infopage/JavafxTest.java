package seedu.address.ui.infopage;

import java.util.concurrent.CountDownLatch;
import java.util.concurrent.TimeUnit;

import org.junit.jupiter.api.BeforeAll;

import javafx.application.Platform;


public abstract class JavafxTest {
    private static boolean isInitialized = false;
    @BeforeAll
    public static void setUp_javaFX_runtime() throws InterruptedException {
        if (!isInitialized) {
            CountDownLatch countDownLatch = new CountDownLatch(0);
            Platform.startup(() -> {
                countDownLatch.countDown();
            });
            countDownLatch.await(3, TimeUnit.SECONDS);
            isInitialized = true;
        }
    }
}
