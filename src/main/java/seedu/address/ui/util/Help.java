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
                URI.create("https://ay2122s1-cs2103t-t12-2.github.io/tp/UserGuide.html#:~:text=Back%20to%20top-"
                        + ",Command%20summary,-Action"));
    }

    /**
     * Opens the online User Guide if possible, throw IOException otherwise.
     * @throws IOException if the user default browser is not found, or it fails to be launched, or the default handler
     * application failed to be launched.
     */
    public void openUserGuide() throws IOException {
        Desktop.getDesktop().browse(
                URI.create("https://ay2122s1-cs2103t-t12-2.github.io/tp/UserGuide.html"));
    }
}
