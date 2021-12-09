package seedu.times.logic;

import java.nio.file.Path;

import javafx.collections.ObservableList;
import seedu.times.commons.core.GuiSettings;
import seedu.times.logic.commands.CommandResult;
import seedu.times.logic.commands.exceptions.CommandException;
import seedu.times.logic.parser.exceptions.ParseException;
import seedu.times.model.ReadOnlyTimesTable;
import seedu.times.model.person.Student;
import seedu.times.model.tuitionclass.TuitionClass;

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
     * Returns the TimesTable.
     *
     * @see seedu.times.model.Model#getTimesTable()
     */
    ReadOnlyTimesTable getTimesTable();

    /** Returns an unmodifiable view of the filtered list of persons */
    ObservableList<Student> getFilteredStudentList();

    /**
     * Returns the user prefs' address book file path.
     */
    Path getTimesTableFilePath();

    /**
     * Returns the user prefs' GUI settings.
     */
    GuiSettings getGuiSettings();

    /**
     * Set the user prefs' GUI settings.
     */
    void setGuiSettings(GuiSettings guiSettings);


    ObservableList<TuitionClass> getFilteredTuitionClassList();

    void setCommandObserver(CommandObserver commandObserver);
}
