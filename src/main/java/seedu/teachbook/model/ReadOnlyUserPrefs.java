package seedu.teachbook.model;

import java.nio.file.Path;

import seedu.teachbook.commons.core.GuiSettings;

/**
 * Unmodifiable view of user prefs.
 */
public interface ReadOnlyUserPrefs {

    GuiSettings getGuiSettings();

    Path getTeachBookFilePath();

}
