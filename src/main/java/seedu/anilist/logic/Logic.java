package seedu.anilist.logic;

import java.nio.file.Path;

import javafx.collections.ObservableList;
import seedu.anilist.commons.core.GuiSettings;
import seedu.anilist.logic.commands.CommandResult;
import seedu.anilist.logic.commands.exceptions.CommandException;
import seedu.anilist.logic.parser.exceptions.ParseException;
import seedu.anilist.model.ReadOnlyAnimeList;
import seedu.anilist.model.anime.Anime;
import seedu.anilist.model.stats.Stats;
import seedu.anilist.ui.TabOption;

/**
 * API of the Logic component
 */
public interface Logic {
    /**
     * Executes the command and returns the result.
     * @param commandText The command as entered by the user.
     * @return the result of the command execution.
     * @throws CommandException If an error occurs during command execution.
     * @throws ParseException If an error occurs during parsing.
     */
    CommandResult execute(String commandText) throws CommandException, ParseException;

    /**
     * Returns the AniList.
     *
     * @see seedu.anilist.model.Model#getAnimeList()
     */
    ReadOnlyAnimeList getAnimeList();

    /** Returns an unmodifiable view of the filtered list of animes */
    ObservableList<Anime> getFilteredAnimeList();

    /**
     * Returns the user prefs' anime list file path.
     */
    Path getAnimeListFilePath();

    /**
     * Returns the user prefs' GUI settings.
     */
    GuiSettings getGuiSettings();

    /**
     * Set the user prefs' GUI settings.
     */
    void setGuiSettings(GuiSettings guiSettings);

    /**
     * Sets the current tab.
     */
    void setCurrentTab(TabOption.TabOptions currentTab);

    /**
     * Returns current tab.
     */
    TabOption getCurrentTab();

    /**
     * Returns the current user statistics.
     */
    Stats getStats();

    /**
     * Returns theme css file name as a string.
     */
    String getThemeCss();

    /**
     * Sets the theme css file.
     */
    void setThemeCss(String themeCss);
}
