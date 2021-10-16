package seedu.siasa.model;

import java.nio.file.Path;

import seedu.siasa.commons.core.GuiSettings;

/**
 * Unmodifiable view of user prefs.
 */
public interface ReadOnlyUserPrefs {

    GuiSettings getGuiSettings();

    Path getSiasaFilePath();

}
