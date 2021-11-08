package seedu.smartnus.model;

import javafx.collections.ObservableList;
import seedu.smartnus.model.note.Note;
import seedu.smartnus.model.question.Question;
import seedu.smartnus.model.statistic.TagStatistic;


/**
 * Unmodifiable view of a SmartNus
 */
public interface ReadOnlySmartNus {

    /**
     * Returns an unmodifiable view of the questions list.
     * This list will not contain any duplicate questions.
     */
    ObservableList<Question> getQuestionList();
    ObservableList<Note> getNoteList();
    ObservableList<TagStatistic> getTagStatistic();
}
