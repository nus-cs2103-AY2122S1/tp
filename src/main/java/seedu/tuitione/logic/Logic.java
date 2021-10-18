package seedu.tuitione.logic;

import java.nio.file.Path;

import javafx.collections.ObservableList;
import seedu.tuitione.commons.core.GuiSettings;
import seedu.tuitione.logic.commands.CommandResult;
import seedu.tuitione.logic.commands.exceptions.CommandException;
import seedu.tuitione.logic.parser.exceptions.ParseException;
import seedu.tuitione.model.ReadOnlyTuitione;
import seedu.tuitione.model.lesson.Lesson;
import seedu.tuitione.model.student.Student;

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
     * Returns the Tuitione.
     *
     * @see seedu.tuitione.model.Model#getTuitione()
     */
    ReadOnlyTuitione getTuitione();

    /** Returns an unmodifiable view of the filtered list of students */
    ObservableList<Student> getFilteredStudentList();

    /** Returns an unmodifiable view of the filtered list of lessons */
    ObservableList<Lesson> getFilteredLessonList();

    /**
     * Returns the user prefs' tuitione book file path.
     */
    Path getTuitioneFilePath();

    /**
     * Returns the user prefs' GUI settings.
     */
    GuiSettings getGuiSettings();

    /**
     * Set the user prefs' GUI settings.
     */
    void setGuiSettings(GuiSettings guiSettings);
}
