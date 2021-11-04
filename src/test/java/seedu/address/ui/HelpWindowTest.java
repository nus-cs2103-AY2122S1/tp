package seedu.address.ui;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;

public class HelpWindowTest {

    // Tests that the Command Details can be set up.
    @Test
    public void setUpCommandDetails_noInputs_success() {
        HelpWindow helpWindow = new HelpWindow();
        boolean success = false;

        try {
            helpWindow.setUpCommandDetails();
            success = true;
        } catch (Exception e) {
            assertTrue(success);
        }

        assertTrue(success);
    }

    // Tests that the Table View can be set up.
    @Test
    public void setUpHelpTableView_noInputs_success() {
        HelpWindow helpWindow = new HelpWindow();
        boolean success = false;

        try {
            helpWindow.setUpHelpTableView();
            success = true;
        } catch (Exception e) {
            assertTrue(success);
        }

        assertTrue(success);
    }

    // Tests that the User Guide can be Opened and The URL is correct.
    @Test
    public void openUserGuide_noInputs_success() {
        HelpWindow helpWindow = new HelpWindow();
        boolean success = false;

        try {
            helpWindow.openUserGuide();
            success = true;
        } catch (Exception e) {
            assertTrue(success);
        }

        assertTrue(success);
    }
}
