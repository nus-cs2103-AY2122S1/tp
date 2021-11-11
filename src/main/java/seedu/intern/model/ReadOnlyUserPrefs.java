package seedu.intern.model;

import java.nio.file.Path;

import seedu.intern.commons.core.GuiSettings;

/**
 * Unmodifiable view of user prefs.
 */
public interface ReadOnlyUserPrefs {

    GuiSettings getGuiSettings();

    Path getInternWatcherFilePath();

}
