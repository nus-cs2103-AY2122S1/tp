package seedu.track2gather.model;

import java.nio.file.Path;

import seedu.track2gather.commons.core.GuiSettings;

/**
 * Unmodifiable view of user prefs.
 */
public interface ReadOnlyUserPrefs {

    GuiSettings getGuiSettings();

    Path getTrack2GatherFilePath();

}
