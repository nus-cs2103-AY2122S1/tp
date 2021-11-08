package seedu.gamebook.model;

import java.nio.file.Path;

import seedu.gamebook.commons.core.GuiSettings;

/**
 * Unmodifiable view of user prefs.
 */
public interface ReadOnlyUserPrefs {

    GuiSettings getGuiSettings();

    Path getGameBookFilePath();

}
