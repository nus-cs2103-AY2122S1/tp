package seedu.address.ui;

import java.io.File;
import java.io.IOException;

/**
 * File Chooser Interface.
 */
public interface FileChooser {
    File chooseFile() throws IOException;
}
