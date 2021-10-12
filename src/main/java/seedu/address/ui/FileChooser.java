package seedu.address.ui;

import java.io.File;
import java.io.IOException;

/**
 * File Chooser Interface.
 */
public interface FileChooser {

    File chooseFile(String defaultDirectory) throws IOException;

}
