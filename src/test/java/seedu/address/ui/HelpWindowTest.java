package seedu.address.ui;

import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;

public class HelpWindowTest {

    @Test
    public void execute_outOfThreadInitialization_throwsError() {
        boolean isThrown = false;
        try {
            HelpWindow helpWindow = HelpWindow.getWindow();
        } catch (ExceptionInInitializerError e) {
            isThrown = true;
        }
        assertTrue(isThrown);
    }
}
