package seedu.smartnus.logic;

import java.nio.file.Path;

import javafx.collections.ObservableList;
import seedu.smartnus.commons.core.GuiSettings;
import seedu.smartnus.commons.core.theme.Theme;
import seedu.smartnus.logic.commands.CommandResult;
import seedu.smartnus.logic.commands.exceptions.CommandException;
import seedu.smartnus.logic.parser.exceptions.ParseException;
import seedu.smartnus.model.ReadOnlySmartNus;
import seedu.smartnus.model.note.Note;
import seedu.smartnus.model.question.Question;
import seedu.smartnus.model.quiz.QuizManager;
import seedu.smartnus.model.statistic.TagStatistic;

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
     * Executes the command during a quiz and returns the result.
     * @param commandText The command as entered by the user.
     * @return the result of the command execution.
     * @throws CommandException If an error occurs during command execution.
     * @throws ParseException If an error occurs during parsing.
     */
    CommandResult execute(String commandText, QuizManager quizManager) throws CommandException, ParseException;

    /**
     * Returns the SmartNus.
     *
     * @see seedu.smartnus.model.Model#getSmartNus()
     */
    ReadOnlySmartNus getSmartNus();

    /** Returns an unmodifiable view of the filtered list of questions */
    ObservableList<Question> getFilteredQuestionList();


    /** Returns an unmodifiable view of the filtered list of questions */
    ObservableList<Note> getFilteredNoteList();

    /** Returns an unmodifiable view of the filtered list of questions for quizzes */
    ObservableList<Question> getFilteredQuizQuestionList();

    /** Returns an unmodifiable view of the filtered list of questions for quizzes */
    ObservableList<TagStatistic> getFilteredTagStatisticList();


    /**
     * Returns the user prefs' SmartNUS file path.
     */
    Path getSmartNusFilePath();

    /**
     * Returns the user prefs' GUI settings.
     */
    GuiSettings getGuiSettings();

    /**
     * Set the user prefs' GUI settings.
     */
    void setGuiSettings(GuiSettings guiSettings);

    /**
     * Returns the user pref's theme.
     */
    Theme getTheme();

    /**
     * Sets the user pref's theme.
     */
    void setTheme(Theme theme);

    /**
     * Sets the current panel to be shown.
     */
    void setPanel(String panel);

    /**
     * Returns the current panel to be shown.
     */
    String getPanel();
}
