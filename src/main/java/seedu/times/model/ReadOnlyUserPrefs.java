package seedu.times.model;

import java.nio.file.Path;

import seedu.times.commons.core.GuiSettings;

/**
 * Unmodifiable view of user prefs.
 */
public interface ReadOnlyUserPrefs {

    GuiSettings getGuiSettings();

    Path getTimesTableFilePath();

}
