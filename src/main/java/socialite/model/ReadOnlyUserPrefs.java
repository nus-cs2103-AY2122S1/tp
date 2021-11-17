package socialite.model;

import java.nio.file.Path;

import socialite.commons.core.GuiSettings;

/**
 * Unmodifiable view of user prefs.
 */
public interface ReadOnlyUserPrefs {

    GuiSettings getGuiSettings();

    Path getContactListFilePath();

    Path getCommandHistoryFilePath();

}
