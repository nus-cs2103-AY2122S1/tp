package seedu.address.ui;

import java.io.File;

import seedu.address.logic.commands.exceptions.CommandException;

/**
 * File Chooser Interface.
 */
public interface FileChooser {

    File chooseFile(String defaultDirectory) throws CommandException;

}
