package seedu.plannermd.model;

import java.nio.file.Path;

import seedu.plannermd.commons.core.GuiSettings;

/**
 * Unmodifiable view of user prefs.
 */
public interface ReadOnlyUserPrefs {

    GuiSettings getGuiSettings();

    Path getPlannerMdFilePath();

}
