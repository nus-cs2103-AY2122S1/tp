package seedu.smartnus.model;

import java.nio.file.Path;

import seedu.smartnus.commons.core.GuiSettings;
import seedu.smartnus.commons.core.theme.Theme;

/**
 * Unmodifiable view of user prefs.
 */
public interface ReadOnlyUserPrefs {
    Theme getTheme();

    GuiSettings getGuiSettings();

    Path getSmartNusFilePath();

}
