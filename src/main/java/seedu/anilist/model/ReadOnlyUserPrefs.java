package seedu.anilist.model;

import java.nio.file.Path;

import seedu.anilist.commons.core.GuiSettings;

/**
 * Unmodifiable view of user prefs.
 */
public interface ReadOnlyUserPrefs {

    GuiSettings getGuiSettings();

    Path getAnimeListFilePath();

    String getThemeCss();

}
