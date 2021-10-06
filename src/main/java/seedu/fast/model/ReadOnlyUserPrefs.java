package seedu.fast.model;

import java.nio.file.Path;

import seedu.fast.commons.core.GuiSettings;

/**
 * Unmodifiable view of user prefs.
 */
public interface ReadOnlyUserPrefs {

    GuiSettings getGuiSettings();

    Path getFastFilePath();

}
