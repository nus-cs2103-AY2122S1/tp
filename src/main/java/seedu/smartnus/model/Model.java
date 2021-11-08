package seedu.smartnus.model;

import java.nio.file.Path;
import java.util.Comparator;
import java.util.function.Predicate;

import javafx.collections.ObservableList;
import seedu.smartnus.commons.core.GuiSettings;
import seedu.smartnus.commons.core.theme.Theme;
import seedu.smartnus.model.note.Note;
import seedu.smartnus.model.question.Question;
import seedu.smartnus.model.statistic.TagStatistic;

/**
 * The API of the Model component.
 */
public interface Model {
    /** {@code Predicate} that always evaluate to true */
    Predicate<Question> PREDICATE_SHOW_ALL_QUESTIONS = unused -> true;
    Predicate<Note> PREDICATE_SHOW_ALL_NOTES = unused -> true;
    Predicate<TagStatistic> PREDICATE_SHOW_ALL_STATISTICS = unused -> true;

    /**
     * Replaces user prefs data with the data in {@code userPrefs}.
     */
    void setUserPrefs(ReadOnlyUserPrefs userPrefs);

    /**
     * Returns the user prefs.
     */
    ReadOnlyUserPrefs getUserPrefs();

    /**
     * Returns the user prefs' GUI settings.
     */
    GuiSettings getGuiSettings();

    /**
     * Sets the user prefs' GUI settings.
     */
    void setGuiSettings(GuiSettings guiSettings);

    /**
     * Returns the user prefs' SmartNus file path.
     */
    Path getSmartNusFilePath();

    /**
     * Sets the user prefs' SmartNus file path.
     */
    void setSmartNusFilePath(Path smartNusFilePath);

    /**
     * Replaces SmartNus data with the data in {@code smartNus}.
     */
    void setSmartNus(ReadOnlySmartNus smartNus);

    /** Returns the SmartNus */
    ReadOnlySmartNus getSmartNus();

    /**
     * Returns true if a question with the same identity as {@code question} exists in SmartNus.
     */
    boolean hasQuestion(Question question);

    /**
     * Deletes the given question.
     * The question must exist in SmartNus.
     */
    void deleteQuestion(Question target);

    /**
     * Adds the given question.
     * {@code question} must not already exist in SmartNus.
     */
    void addQuestion(Question question);

    /**
     * Replaces the given question {@code target} with {@code editedQuestion}.
     * {@code target} must exist in SmartNus.
     * The question identity of {@code editedQuestion} must not be the same as another existing question
     * in SmartNus.
     */
    void setQuestion(Question target, Question editedQuestion);

    /** Returns an unmodifiable view of the filtered question list */
    ObservableList<Question> getFilteredQuestionList();

    /** Returns an unmodifiable view of the filtered quiz question list */
    ObservableList<Question> getFilteredQuizQuestionList();

    /**
     * Updates the filter of the filtered question list to filter by the given {@code predicate}.
     * @throws NullPointerException if {@code predicate} is null.
     */
    void updateFilteredQuestionList(Predicate<Question> predicate);

    /**
     * Resets the filtered question list to contain all questions.
     */
    void resetFilteredQuestionList();

    /**
     * Adds the given note.
     */
    void addNote(Note note);

    /**
     * Deletes the given note.
     * The note must exist in SmartNus.
     */
    void deleteNote(Note target);

    /**
     * Returns true if a note with the same identity as {@code note} exists in SmartNus.
     */
    boolean hasNote(Note note);

    /**
     * Replaces the given note {@code target} with {@code editedNote}.
     * {@code target} must exist in SmartNus.
     * The note identity of {@code editedNote} must not be the same as another existing note
     * in SmartNus.
     */
    void setNote(Note target, Note editedNote);

    /** Returns an unmodifiable view of the filtered note list */
    ObservableList<Note> getFilteredNoteList();

    /**
     * Updates the filter of the filtered note list to filter by the given {@code predicate}.
     * @throws NullPointerException if {@code predicate} is null.
     */
    void updateFilteredNoteList(Predicate<Note> predicate);

    /**
     * Updates the filter of the filtered quiz question list to filter by the given {@code predicate}.
     * @throws NullPointerException if {@code predicate} is null.
     */
    void updateFilteredQuizQuestionList(Predicate<Question> predicate);

    /**
     * Updates the filter of the filtered quiz list to filter by the given {@code predicate} with current state.
     * @throws NullPointerException if {@code predicate} is null.
     */
    void updateFilteredQuizQuestionListWithCurrent(Predicate<Question> predicate);

    /**
     * Sorts the filtered quiz question list using the given {@code comparator}
     * @throws NullPointerException if {@code comparator} is null.
     */
    void sortFilteredQuizQuestionList(Comparator<Question> comparator);

    /**
     * Sets the Theme.
     */
    void setTheme(Theme theme);

    /**
     * Returns the Theme.
     */
    Theme getTheme();

    /**
     * Sets the current panel to be shown.
     */
    void setPanel(String panel);

    /**
     * Returns the current panel to be shown.
     */
    String getPanel();

    /**
     * Returns the tag-to-statistic map.
     */
    ObservableList<TagStatistic> getTagStatistic();

    /**
     * Updates the filter of the filtered tag statistic list to filter by the given {@code predicate}.
     * @throws NullPointerException if {@code predicate} is null.
     */
    void updateFilteredTagStatistic(Predicate<TagStatistic> predicate);
}
