package nustracker.logic;

import java.nio.file.Path;

import javafx.collections.ObservableList;
import nustracker.commons.core.GuiSettings;
import nustracker.logic.commands.CommandResult;
import nustracker.logic.commands.exceptions.CommandException;
import nustracker.logic.parser.exceptions.ParseException;
import nustracker.model.ReadOnlyAddressBook;
import nustracker.model.event.Event;
import nustracker.model.student.Student;
import nustracker.ui.MainWindow.CurrentlyShownList;

/**
 * API of the Logic component
 */
public interface Logic {
    /**
     * Executes the command and returns the result.
     * @param commandText The command as entered by the user.
     * @param currentlyShownList The list that is currently being shown in the Main Window.
     * @return the result of the command execution.
     * @throws CommandException If an error occurs during command execution.
     * @throws ParseException If an error occurs during parsing.
     */
    CommandResult execute(String commandText,
                          CurrentlyShownList currentlyShownList) throws CommandException, ParseException;

    /**
     * Returns the AddressBook.
     *
     * @see nustracker.model.Model#getAddressBook()
     */
    ReadOnlyAddressBook getAddressBook();

    /** Returns an unmodifiable view of the filtered list of students */
    ObservableList<Student> getFilteredStudentList();

    /** Returns an unmodifiable view of the filtered list of events */
    ObservableList<Event> getFilteredEventList();

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
