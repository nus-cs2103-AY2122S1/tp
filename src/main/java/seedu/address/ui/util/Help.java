package seedu.address.ui.util;

import java.awt.Desktop;
import java.io.IOException;
import java.net.URI;

/**
 * A class to handle opening the primary help window.
 */
public class Help {

    /**
     * Constructor for Help Class.
     */
    public Help() {
    }

    /**
     * Opens an online Command Summary if possible, throw IOException otherwise.
     * @throws IOException if the user default browser is not found, or it fails to be launched, or the default handler
     * application failed to be launched.
     */
    public void openCommandSummary() throws IOException {
        Desktop.getDesktop().browse(
                URI.create("https://github.com/AY2122S1-CS2103T-T12-2/tp/blob/master/docs/"
                        + "UserGuide.md#:~:text=Action-,Format%2C%20Examples,-Add"));
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
