package seedu.address.ui;

import java.io.File;

import seedu.address.ui.exceptions.FileSelectorException;

/**
 * File Chooser Interface.
 */
public interface FileSelector {
    File selectFile() throws FileSelectorException;
}
