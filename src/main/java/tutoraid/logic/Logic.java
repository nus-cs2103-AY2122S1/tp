package tutoraid.logic;

import java.nio.file.Path;

import javafx.collections.ObservableList;
import tutoraid.commons.core.GuiSettings;
import tutoraid.logic.commands.CommandResult;
import tutoraid.logic.commands.exceptions.CommandException;
import tutoraid.logic.parser.exceptions.ParseException;
import tutoraid.model.Model;
import tutoraid.model.ReadOnlyLessonBook;
import tutoraid.model.ReadOnlyStudentBook;
import tutoraid.model.lesson.Lesson;
import tutoraid.model.student.Student;


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
     * Returns the StudentBook.
     *
     * @see Model#getStudentBook()
     */
    ReadOnlyStudentBook getStudentBook();

    /** Returns an unmodifiable view of the filtered list of students */
    ObservableList<Student> getFilteredStudentList();

    /**
     * Returns the user prefs' student book file path.
     */
    Path getStudentBookFilePath();

    /**
     * Returns the LessonBook.
     *
     * @see Model#getLessonBook()
     */
    ReadOnlyLessonBook getLessonBook();

    /** Returns an unmodifiable view of the filtered list of lessons */
    ObservableList<Lesson> getFilteredLessonList();

    /**
     * Returns the user prefs' lesson book file path.
     */
    Path getLessonBookFilePath();

    /**
     * Returns the user prefs' GUI settings.
     */
    GuiSettings getGuiSettings();

    /**
     * Set the user prefs' GUI settings.
     */
    void setGuiSettings(GuiSettings guiSettings);
}
