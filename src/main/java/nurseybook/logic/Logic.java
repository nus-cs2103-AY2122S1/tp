package nurseybook.logic;

import java.nio.file.Path;

import javafx.collections.ObservableList;
import nurseybook.commons.core.GuiSettings;
import nurseybook.logic.commands.CommandResult;
import nurseybook.logic.commands.exceptions.CommandException;
import nurseybook.logic.parser.exceptions.ParseException;
import nurseybook.model.Model;
import nurseybook.model.ReadOnlyNurseyBook;
import nurseybook.model.person.Elderly;
import nurseybook.model.task.Task;

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
     * Returns the NurseyBook.
     *
     * @see Model#getVersionedNurseyBook()
     */
    ReadOnlyNurseyBook getNurseyBook();

    /** Returns an unmodifiable view of the filtered list of elderlies */
    ObservableList<Elderly> getFilteredElderlyList();

    /**
     * Returns the user prefs' nursey book file path.
     */
    Path getNurseyBookFilePath();

    /**
     * Returns the user prefs' GUI settings.
     */
    GuiSettings getGuiSettings();

    /**
     * Set the user prefs' GUI settings.
     */
    void setGuiSettings(GuiSettings guiSettings);

    ObservableList<Task> getFilteredTaskList();

    /**
     * Gets elderly whose details are of interest.
     */
    Elderly getElderlyOfInterest();
}
