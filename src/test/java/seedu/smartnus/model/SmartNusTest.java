package seedu.smartnus.model;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.smartnus.logic.commands.CommandTestUtil.VALID_TAG_3;
import static seedu.smartnus.logic.commands.CommandTestUtil.VALID_TAG_4;
import static seedu.smartnus.testutil.Assert.assertThrows;
import static seedu.smartnus.testutil.TypicalNotes.CS2100_NOTE;
import static seedu.smartnus.testutil.TypicalNotes.CS2103T_NOTE;
import static seedu.smartnus.testutil.TypicalQuestions.MCQ_QUESTION_1;
import static seedu.smartnus.testutil.TypicalQuestions.STORAGE_QUESTION_2;
import static seedu.smartnus.testutil.TypicalQuestions.TF_QUESTION_2;
import static seedu.smartnus.testutil.TypicalSmartNus.getTypicalSmartNus;

import java.util.Arrays;
import java.util.Collection;
import java.util.Collections;
import java.util.List;

import org.junit.jupiter.api.Test;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import seedu.smartnus.model.note.Note;
import seedu.smartnus.model.question.Question;
import seedu.smartnus.model.question.exceptions.DuplicateQuestionException;
import seedu.smartnus.model.statistic.TagStatistic;
import seedu.smartnus.testutil.NoteBuilder;
import seedu.smartnus.testutil.QuestionBuilder;

public class SmartNusTest {

    private final SmartNus smartNus = new SmartNus();
    private final List<Question> genericQuestions = List.of(MCQ_QUESTION_1);
    private final List<Note> genericNotes = List.of(CS2103T_NOTE);

    @Test
    public void constructor() {
        assertEquals(Collections.emptyList(), smartNus.getQuestionList());
    }

    @Test
    public void resetData_null_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> smartNus.resetData(null));
    }

    @Test
    public void setNote_null_throwsNullPointerException() {
        smartNus.addNote(CS2103T_NOTE);
        assertThrows(NullPointerException.class, () -> smartNus.setNote(CS2103T_NOTE, null));
    }

    @Test
    public void setNote_withValidNote_replacesNote() {
        smartNus.addNote(CS2103T_NOTE);
        smartNus.setNote(CS2103T_NOTE, CS2100_NOTE);
        // smartNus contains the note
        assertTrue(smartNus.hasNote(CS2100_NOTE));
        // original note has been deleted
        assertFalse(smartNus.hasNote(CS2103T_NOTE));
    }

    @Test
    public void resetData_withValidReadOnlySmartNus_replacesData() {
        SmartNus newData = getTypicalSmartNus();
        smartNus.resetData(newData);
        assertEquals(newData, smartNus);
    }

    @Test
    public void resetData_withDuplicateQuestions_throwsDuplicateQuestionException() {
        // Two questions with the same identity fields
        Question editedAlice = new QuestionBuilder(MCQ_QUESTION_1).withTags(VALID_TAG_3)
                .build();
        List<Question> newQuestions = Arrays.asList(MCQ_QUESTION_1, editedAlice);
        SmartNusStub newData = new SmartNusStub(newQuestions, genericNotes);

        assertThrows(DuplicateQuestionException.class, () -> smartNus.resetData(newData));
    }

    @Test
    public void hasQuestion_nullQuestion_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> smartNus.hasQuestion(null));
    }

    @Test
    public void hasQuestion_questionNotInSmartNus_returnsFalse() {
        assertFalse(smartNus.hasQuestion(MCQ_QUESTION_1));
    }

    @Test
    public void hasQuestion_questionInSmartNus_returnsTrue() {
        smartNus.addQuestion(MCQ_QUESTION_1);
        assertTrue(smartNus.hasQuestion(MCQ_QUESTION_1));
        assertNotEquals(null, smartNus.getQuestionsAsList());
        assertNotEquals(0, smartNus.hashCode());
    }

    @Test
    public void hasQuestion_questionWithSameIdentityFieldsInSmartNus_returnsTrue() {
        smartNus.addQuestion(MCQ_QUESTION_1);
        Question editedAlice = new QuestionBuilder(MCQ_QUESTION_1).withTags(VALID_TAG_3)
                .build();
        assertTrue(smartNus.hasQuestion(editedAlice));
    }

    @Test
    public void getQuestionList_modifyList_throwsUnsupportedOperationException() {
        assertThrows(UnsupportedOperationException.class, () -> smartNus.getQuestionList().remove(0));
    }

    @Test
    public void hasNote_nullNote_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> smartNus.hasNote(null));
    }

    @Test
    public void hasNote_noteNotInSmartNus_returnsFalse() {
        assertFalse(smartNus.hasNote(CS2103T_NOTE));
    }

    @Test
    public void hasNote_noteInSmartNus_returnsTrue() {
        smartNus.addNote(CS2103T_NOTE);
        assertTrue(smartNus.hasNote(CS2103T_NOTE));
        assertNotEquals(null, smartNus.getNotesAsList());
        assertNotEquals(0, smartNus.hashCode());
    }

    @Test
    public void hasNote_noteWithSameIdentityFieldsInSmartNus_returnsTrue() {
        smartNus.addNote(CS2103T_NOTE);
        Note editedAlice = new NoteBuilder(CS2103T_NOTE).build();
        assertTrue(smartNus.hasNote(editedAlice));
    }

    @Test
    public void getNoteList_modifyList_throwsUnsupportedOperationException() {
        assertThrows(UnsupportedOperationException.class, () -> smartNus.getNoteList().remove(0));
    }

    @Test
    public void tagStatistic_equals() {
        Question editedAlice = new QuestionBuilder(MCQ_QUESTION_1).withTags(VALID_TAG_4).build();
        Question editedBob = new QuestionBuilder(STORAGE_QUESTION_2).withTags(VALID_TAG_3).build();
        Question editedCarl = new QuestionBuilder(TF_QUESTION_2).withTags(VALID_TAG_3).build();
        smartNus.addQuestion(editedAlice);
        smartNus.addQuestion(editedBob);
        smartNus.addQuestion(editedCarl);
        ObservableList<TagStatistic> tagStatisticMap = smartNus.getTagStatistic();

        assertEquals(2, tagStatisticMap.size());
    }

    /**
     * A stub ReadOnlySmartNus whose questions list can violate interface constraints.
     */
    private static class SmartNusStub implements ReadOnlySmartNus {
        private final ObservableList<Question> questions = FXCollections.observableArrayList();
        private final ObservableList<Note> notes = FXCollections.observableArrayList();
        private final ObservableList<TagStatistic> tagStatisticMap = FXCollections.observableArrayList();

        SmartNusStub(Collection<Question> questions, Collection<Note> notes) {
            this.questions.setAll(questions);
            this.notes.setAll(notes);
        }

        @Override
        public ObservableList<Question> getQuestionList() {
            return questions;
        }

        @Override
        public ObservableList<Note> getNoteList() {
            return notes;
        }

        @Override
        public ObservableList<TagStatistic> getTagStatistic() {
            return tagStatisticMap;
        }
    }

}
