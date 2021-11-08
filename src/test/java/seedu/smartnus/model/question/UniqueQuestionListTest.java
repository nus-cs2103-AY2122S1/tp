package seedu.smartnus.model.question;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.smartnus.logic.commands.CommandTestUtil.VALID_TAG_3;
import static seedu.smartnus.testutil.Assert.assertThrows;
import static seedu.smartnus.testutil.TypicalQuestions.MCQ_QUESTION_1;
import static seedu.smartnus.testutil.TypicalQuestions.STORAGE_QUESTION_2;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import org.junit.jupiter.api.Test;

import seedu.smartnus.model.question.exceptions.DuplicateQuestionException;
import seedu.smartnus.model.question.exceptions.QuestionNotFoundException;
import seedu.smartnus.testutil.QuestionBuilder;

public class UniqueQuestionListTest {

    private final UniqueQuestionList uniqueQuestionList = new UniqueQuestionList();

    @Test
    public void contains_nullQuestion_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> uniqueQuestionList.contains(null));
    }

    @Test
    public void contains_questionNotInList_returnsFalse() {
        assertFalse(uniqueQuestionList.contains(MCQ_QUESTION_1));
    }

    @Test
    public void contains_questionInList_returnsTrue() {
        uniqueQuestionList.add(MCQ_QUESTION_1);
        assertTrue(uniqueQuestionList.contains(MCQ_QUESTION_1));
    }

    @Test
    public void contains_questionWithSameIdentityFieldsInList_returnsTrue() {
        uniqueQuestionList.add(MCQ_QUESTION_1);
        Question editedAlice = new QuestionBuilder(MCQ_QUESTION_1).withTags(VALID_TAG_3)
                .build();
        assertTrue(uniqueQuestionList.contains(editedAlice));
    }

    @Test
    public void add_nullQuestion_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> uniqueQuestionList.add(null));
    }

    @Test
    public void add_duplicateQuestion_throwsDuplicateQuestionException() {
        uniqueQuestionList.add(MCQ_QUESTION_1);
        assertThrows(DuplicateQuestionException.class, () -> uniqueQuestionList.add(MCQ_QUESTION_1));
    }

    @Test
    public void setQuestion_nullTargetQuestion_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> uniqueQuestionList.setQuestion(null, MCQ_QUESTION_1));
    }

    @Test
    public void setQuestion_nullEditedQuestion_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> uniqueQuestionList.setQuestion(MCQ_QUESTION_1, null));
    }

    @Test
    public void setQuestion_targetQuestionNotInList_throwsQuestionNotFoundException() {
        assertThrows(QuestionNotFoundException.class, () ->
                uniqueQuestionList.setQuestion(MCQ_QUESTION_1, MCQ_QUESTION_1));
    }

    @Test
    public void setQuestion_editedQuestionIsSameQuestion_success() {
        uniqueQuestionList.add(MCQ_QUESTION_1);
        uniqueQuestionList.setQuestion(MCQ_QUESTION_1, MCQ_QUESTION_1);
        UniqueQuestionList expectedUniqueQuestionList = new UniqueQuestionList();
        expectedUniqueQuestionList.add(MCQ_QUESTION_1);
        assertEquals(expectedUniqueQuestionList, uniqueQuestionList);
    }

    @Test
    public void setQuestion_editedQuestionHasSameIdentity_success() {
        uniqueQuestionList.add(MCQ_QUESTION_1);
        Question editedAlice = new QuestionBuilder(MCQ_QUESTION_1).withTags(VALID_TAG_3)
                .build();
        uniqueQuestionList.setQuestion(MCQ_QUESTION_1, editedAlice);
        UniqueQuestionList expectedUniqueQuestionList = new UniqueQuestionList();
        expectedUniqueQuestionList.add(editedAlice);
        assertEquals(expectedUniqueQuestionList, uniqueQuestionList);
    }

    @Test
    public void setQuestion_editedQuestionHasDifferentIdentity_success() {
        uniqueQuestionList.add(MCQ_QUESTION_1);
        uniqueQuestionList.setQuestion(MCQ_QUESTION_1, STORAGE_QUESTION_2);
        UniqueQuestionList expectedUniqueQuestionList = new UniqueQuestionList();
        expectedUniqueQuestionList.add(STORAGE_QUESTION_2);
        assertEquals(expectedUniqueQuestionList, uniqueQuestionList);
    }

    @Test
    public void setQuestion_editedQuestionHasNonUniqueIdentity_throwsDuplicateQuestionException() {
        uniqueQuestionList.add(MCQ_QUESTION_1);
        uniqueQuestionList.add(STORAGE_QUESTION_2);
        assertThrows(DuplicateQuestionException.class, () ->
                uniqueQuestionList.setQuestion(MCQ_QUESTION_1, STORAGE_QUESTION_2));
    }

    @Test
    public void remove_nullQuestion_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> uniqueQuestionList.remove(null));
    }

    @Test
    public void remove_questionDoesNotExist_throwsQuestionNotFoundException() {
        assertThrows(QuestionNotFoundException.class, () -> uniqueQuestionList.remove(MCQ_QUESTION_1));
    }

    @Test
    public void remove_existingQuestion_removesQuestion() {
        uniqueQuestionList.add(MCQ_QUESTION_1);
        uniqueQuestionList.remove(MCQ_QUESTION_1);
        UniqueQuestionList expectedUniqueQuestionList = new UniqueQuestionList();
        assertEquals(expectedUniqueQuestionList, uniqueQuestionList);
    }

    @Test
    public void setQuestions_nullUniqueQuestionList_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> uniqueQuestionList.setQuestions((UniqueQuestionList) null));
    }

    @Test
    public void setQuestions_uniqueQuestionList_replacesOwnListWithProvidedUniqueQuestionList() {
        uniqueQuestionList.add(MCQ_QUESTION_1);
        UniqueQuestionList expectedUniqueQuestionList = new UniqueQuestionList();
        expectedUniqueQuestionList.add(STORAGE_QUESTION_2);
        uniqueQuestionList.setQuestions(expectedUniqueQuestionList);
        assertEquals(expectedUniqueQuestionList, uniqueQuestionList);
    }

    @Test
    public void setQuestions_nullList_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> uniqueQuestionList.setQuestions((List<Question>) null));
    }

    @Test
    public void setQuestions_list_replacesOwnListWithProvidedList() {
        uniqueQuestionList.add(MCQ_QUESTION_1);
        List<Question> questionList = Collections.singletonList(STORAGE_QUESTION_2);
        uniqueQuestionList.setQuestions(questionList);
        UniqueQuestionList expectedUniqueQuestionList = new UniqueQuestionList();
        expectedUniqueQuestionList.add(STORAGE_QUESTION_2);
        assertEquals(expectedUniqueQuestionList, uniqueQuestionList);
    }

    @Test
    public void setQuestions_listWithDuplicateQuestions_throwsDuplicateQuestionException() {
        List<Question> listWithDuplicateQuestions = Arrays.asList(MCQ_QUESTION_1, MCQ_QUESTION_1);
        assertThrows(DuplicateQuestionException.class, () -> uniqueQuestionList
                .setQuestions(listWithDuplicateQuestions));
    }

    @Test
    public void asUnmodifiableObservableList_modifyList_throwsUnsupportedOperationException() {
        assertThrows(UnsupportedOperationException.class, ()
            -> uniqueQuestionList.asUnmodifiableObservableList().remove(0));
    }
}
