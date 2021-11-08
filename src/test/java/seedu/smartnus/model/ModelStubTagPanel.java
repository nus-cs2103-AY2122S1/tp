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
import seedu.smartnus.ui.panel.StatisticListPanel;

/**
 * Model stub not in the tag panel
 */
public class ModelStubTagPanel implements Model {
    @Override
    public void setUserPrefs(ReadOnlyUserPrefs userPrefs) {
        throw new AssertionError("This method should not be called.");
    }

    @Override
    public ReadOnlyUserPrefs getUserPrefs() {
        throw new AssertionError("This method should not be called.");
    }

    @Override
    public GuiSettings getGuiSettings() {
        throw new AssertionError("This method should not be called.");
    }

    @Override
    public void setGuiSettings(GuiSettings guiSettings) {
        throw new AssertionError("This method should not be called.");
    }

    @Override
    public Path getSmartNusFilePath() {
        throw new AssertionError("This method should not be called.");
    }

    @Override
    public void setSmartNusFilePath(Path smartNusFilePath) {
        throw new AssertionError("This method should not be called.");
    }

    @Override
    public void addQuestion(Question question) {
        throw new AssertionError("This method should not be called.");
    }

    @Override
    public void setSmartNus(ReadOnlySmartNus smartNus) {
        throw new AssertionError("This method should not be called.");
    }

    @Override
    public ReadOnlySmartNus getSmartNus() {
        throw new AssertionError("This method should not be called.");
    }

    @Override
    public boolean hasQuestion(Question question) {
        throw new AssertionError("This method should not be called.");
    }

    @Override
    public void deleteQuestion(Question target) {
        throw new AssertionError("This method should not be called.");
    }

    @Override
    public void setQuestion(Question target, Question editedQuestion) {
        throw new AssertionError("This method should not be called.");
    }

    @Override
    public ObservableList<Question> getFilteredQuestionList() {
        throw new AssertionError("This method should not be called.");
    }

    @Override
    public ObservableList<Question> getFilteredQuizQuestionList() {
        throw new AssertionError("This method should not be called.");
    }

    @Override
    public void updateFilteredQuestionList(Predicate<Question> predicate) {
        throw new AssertionError("This method should not be called.");
    }

    @Override
    public void resetFilteredQuestionList() {
        throw new AssertionError("This method should not be called.");
    }

    @Override
    public void sortFilteredQuizQuestionList(Comparator<Question> comparator) {
        throw new AssertionError("This method should not be called.");
    }

    @Override
    public void setTheme(Theme theme) {
        throw new AssertionError("This method should not be called.");
    }

    @Override
    public Theme getTheme() {
        throw new AssertionError("This method should not be called.");
    }

    @Override
    public ObservableList<TagStatistic> getTagStatistic() {
        throw new AssertionError("This method should not be called.");
    }

    @Override
    public void updateFilteredTagStatistic(Predicate<TagStatistic> predicate) {
        throw new AssertionError("This method should not be called.");
    }

    @Override
    public void setPanel(String panel) {
        throw new AssertionError("This method should not be called.");
    }

    @Override
    public String getPanel() {
        return StatisticListPanel.STATISTIC_PANEL;
    }

    @Override
    public void addNote(Note note) {
        throw new AssertionError("This method should not be called.");
    }

    @Override
    public void deleteNote(Note target) {
        throw new AssertionError("This method should not be called.");
    }

    @Override
    public boolean hasNote(Note note) {
        throw new AssertionError("This method should not be called.");
    }

    @Override
    public void setNote(Note target, Note editedNote) {
        throw new AssertionError("This method should not be called.");
    }

    @Override
    public ObservableList<Note> getFilteredNoteList() {
        throw new AssertionError("This method should not be called.");
    }

    @Override
    public void updateFilteredNoteList(Predicate<Note> predicate) {
        throw new AssertionError("This method should not be called.");
    }

    @Override
    public void updateFilteredQuizQuestionList(Predicate<Question> predicate) {
        throw new AssertionError("This method should not be called.");
    }

    @Override
    public void updateFilteredQuizQuestionListWithCurrent(Predicate<Question> predicate) {
        throw new AssertionError("This method should not be called.");
    }
}
