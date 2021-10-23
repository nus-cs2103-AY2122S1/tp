package seedu.address.ui.util;

import java.awt.Desktop;
import java.io.File;
import java.io.IOException;
import java.net.URI;

/**
 * A class to handle opening the primary help window.
 */
public class Help {
    private File helpWindow;

    /**
     * Constructor for Help Class.
     */
    public Help() {
        helpWindow = new File("src/main/resources/images/commandSummary.png");
    }

    /**
     * Opens the png image of the Command Summary.
     * @throws IOException if the specified file has no associated application or the associated application fails to
     * be launched.
     */
    public void openCommandSummary() throws IOException {
        Desktop.getDesktop().open(helpWindow);
    }

    /**
     * Opens the online User Guide if possible, throw IOException otherwise.
     * @throws IOException if the user default browser is not found, or it fails to be launched, or the default handler
     * application failed to be launched.
     */
    public void openUserGuide() throws IOException {
        Desktop.getDesktop().browse(
                URI.create("https://github.com/AY2122S1-CS2103T-T12-2/tp/blob/master/docs/UserGuide.md"));
    }
}
