package seedu.unify.logic;

import java.nio.file.Path;

import javafx.collections.ObservableList;
import seedu.unify.commons.core.GuiSettings;
import seedu.unify.logic.commands.CommandResult;
import seedu.unify.logic.commands.exceptions.CommandException;
import seedu.unify.logic.parser.exceptions.ParseException;
import seedu.unify.model.ReadOnlyUniFy;
import seedu.unify.model.task.Task;
import seedu.unify.model.task.WeeklyTasks;

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
     * Returns the UniFy.
     *
     * @see seedu.unify.model.Model#getUniFy()
     */
    ReadOnlyUniFy getUniFy();

    /** Returns an unmodifiable view of the filtered list of tasks */
    ObservableList<Task> getFilteredTaskList();

    /** Returns an unmodifiable view of the filtered list of tasks */
    WeeklyTasks getWeeklyTaskList();

    /**
     * Returns the user prefs' address book file path.
     */
    Path getAddressBookFilePath();

    /**
     * Returns the user prefs' GUI settings.
     */
    GuiSettings getGuiSettings();

    /**
     * Set the user prefs' GUI settings.
     */
    void setGuiSettings(GuiSettings guiSettings);
}
