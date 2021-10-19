package seedu.address.ui;

import java.awt.Desktop;
import java.io.File;
import java.io.IOException;
import java.net.URI;

/**
 * A class to handle opening the primary help window
 */
public class Help {
    private File helpWindow;

    /**
     * Constructor for Help Class
     */
    public Help() {
        helpWindow = new File("src/main/resources/images/commandSummary.png");
    }

    /**
     * Opens the png image of the Command Summary, else try opening online User Guide
     */
    public void openCommandSummary() throws IOException {
        try {
            Desktop.getDesktop().open(helpWindow);
        } catch (IOException | IllegalArgumentException | SecurityException ex) {
            // no application registered for PDFs
            openUserGuide();
        }
    }

    /**
     * Opens the online User Guide if possible, throw IOException otherwise
     */
    private void openUserGuide() throws IOException {
        Desktop.getDesktop().browse(
                URI.create("https://github.com/AY2122S1-CS2103T-T12-2/tp/blob/master/docs/UserGuide.md"));
    }
}
