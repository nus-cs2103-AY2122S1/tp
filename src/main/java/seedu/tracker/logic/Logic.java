package seedu.tracker.logic;

import java.nio.file.Path;

import javafx.collections.ObservableList;
import seedu.tracker.commons.core.GuiSettings;
import seedu.tracker.logic.commands.CommandResult;
import seedu.tracker.logic.commands.exceptions.CommandException;
import seedu.tracker.logic.parser.exceptions.ParseException;
import seedu.tracker.model.Model;
import seedu.tracker.model.ReadOnlyModuleTracker;
import seedu.tracker.model.module.Module;

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
     * Returns the ModuleTracker.
     *
     * @see Model#getModuleTracker()
     */
    ReadOnlyModuleTracker getModuleTracker();

    /** Returns an unmodifiable view of the filtered list of modules */
    ObservableList<Module> getFilteredModuleList();

    /**
     * Returns the user prefs' module tracker file path.
     */
    Path getModuleTrackerFilePath();

    /**
     * Returns the user prefs' GUI settings.
     */
    GuiSettings getGuiSettings();

    /**
     * Set the user prefs' GUI settings.
     */
    void setGuiSettings(GuiSettings guiSettings);
}
