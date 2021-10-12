package seedu.address.ui;

import seedu.address.ui.exceptions.FileSelectorException;

import java.io.File;

/**
 * File Chooser Interface.
 */
public interface FileSelector {
    File selectFile() throws FileSelectorException;
}
