package seedu.notor.model;

import java.nio.file.Path;

import seedu.notor.commons.core.GuiSettings;

/**
 * Unmodifiable view of user prefs.
 */
public interface ReadOnlyUserPrefs {

    GuiSettings getGuiSettings();

    Path getNotorFilePath();

}
