package seedu.address.ui;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertTrue;

public class HelpWindowTest {

    @Test
    public void execute_outOfThread_initialization_failure() {
        boolean isThrown = false;
        try {
            HelpWindow helpWindow = HelpWindow.getWindow();
        } catch (ExceptionInInitializerError e) {
            isThrown = true;
        }
        assertTrue(isThrown);
    }
}
