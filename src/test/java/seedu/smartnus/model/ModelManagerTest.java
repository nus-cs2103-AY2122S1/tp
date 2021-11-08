package seedu.smartnus.model;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.smartnus.testutil.Assert.assertThrows;
import static seedu.smartnus.testutil.TypicalNotes.CS2103T_NOTE;
import static seedu.smartnus.testutil.TypicalQuestions.MCQ_QUESTION_1;
import static seedu.smartnus.testutil.TypicalQuestions.MCQ_QUESTION_2;

import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Arrays;
import java.util.function.Predicate;

import org.junit.jupiter.api.Test;

import seedu.smartnus.commons.core.GuiSettings;
import seedu.smartnus.model.question.comparator.QuestionsDefaultComparator;
import seedu.smartnus.model.question.predicates.NameContainsKeywordsPredicate;
import seedu.smartnus.model.statistic.TagStatistic;
import seedu.smartnus.testutil.SmartNusBuilder;

public class ModelManagerTest {

    private ModelManager modelManager = new ModelManager();

    @Test
    public void constructor() {
        assertEquals(new UserPrefs(), modelManager.getUserPrefs());
        assertEquals(new GuiSettings(), modelManager.getGuiSettings());
        assertEquals(new SmartNus(), new SmartNus(modelManager.getSmartNus()));
    }

    @Test
    public void setUserPrefs_nullUserPrefs_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> modelManager.setUserPrefs(null));
    }

    @Test
    public void setUserPrefs_validUserPrefs_copiesUserPrefs() {
        UserPrefs userPrefs = new UserPrefs();
        userPrefs.setSmartNusFilePath(Paths.get("smart/nus/file/path"));
        userPrefs.setGuiSettings(new GuiSettings(1, 2, 3, 4));
        modelManager.setUserPrefs(userPrefs);
        assertEquals(userPrefs, modelManager.getUserPrefs());

        // Modifying userPrefs should not modify modelManager's userPrefs
        UserPrefs oldUserPrefs = new UserPrefs(userPrefs);
        userPrefs.setSmartNusFilePath(Paths.get("new/smart/nus/file/path"));
        assertEquals(oldUserPrefs, modelManager.getUserPrefs());
    }

    @Test
    public void setGuiSettings_nullGuiSettings_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> modelManager.setGuiSettings(null));
    }

    @Test
    public void setGuiSettings_validGuiSettings_setsGuiSettings() {
        GuiSettings guiSettings = new GuiSettings(1, 2, 3, 4);
        modelManager.setGuiSettings(guiSettings);
        assertEquals(guiSettings, modelManager.getGuiSettings());
    }

    @Test
    public void setSmartNusFilePath_nullPath_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> modelManager.setSmartNusFilePath(null));
    }

    @Test
    public void setSmartNusFilePath_validPath_setsSmartNusFilePath() {
        Path path = Paths.get("smart/nus/file/path");
        modelManager.setSmartNusFilePath(path);
        assertEquals(path, modelManager.getSmartNusFilePath());
    }

    @Test
    public void hasQuestion_nullQuestion_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> modelManager.hasQuestion(null));
    }

    @Test
    public void hasQuestion_questionNotInSmartNus_returnsFalse() {
        assertFalse(modelManager.hasQuestion(MCQ_QUESTION_1));
    }

    @Test
    public void hasNote_noteInSmartNus_returnsTrue() {
        modelManager.addNote(CS2103T_NOTE);
        assertTrue(modelManager.hasNote(CS2103T_NOTE));
    }

    @Test
    public void hasNote_nullNote_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> modelManager.hasNote(null));
    }

    @Test
    public void hasNote_noteNotInSmartNus_returnsFalse() {
        assertFalse(modelManager.hasNote(CS2103T_NOTE));
    }

    @Test
    public void getFilteredQuestionList_modifyList_throwsUnsupportedOperationException() {
        assertThrows(UnsupportedOperationException.class, () -> modelManager.getFilteredQuestionList().remove(0));
    }

    @Test
    public void getFilteredNoteList_modifyList_throwsUnsupportedOperationException() {
        assertThrows(UnsupportedOperationException.class, () -> modelManager.getFilteredNoteList().remove(0));
    }

    @Test
    public void equals() {
        SmartNus smartNus = new SmartNusBuilder().withQuestion(MCQ_QUESTION_1).withQuestion(MCQ_QUESTION_2).build();
        SmartNus differentSmartNus = new SmartNus();
        UserPrefs userPrefs = new UserPrefs();

        // same values -> returns true
        modelManager = new ModelManager(smartNus, userPrefs);
        ModelManager modelManagerCopy = new ModelManager(smartNus, userPrefs);
        assertTrue(modelManager.equals(modelManagerCopy));

        // same object -> returns true
        assertTrue(modelManager.equals(modelManager));

        // null -> returns false
        assertFalse(modelManager.equals(null));

        // different types -> returns false
        assertFalse(modelManager.equals(5));

        // different smartNus -> returns false
        assertFalse(modelManager.equals(new ModelManager(differentSmartNus, userPrefs)));

        // different filteredList -> returns false
        String[] keywords = MCQ_QUESTION_1.getName().fullName.split("\\s+");
        modelManager.updateFilteredQuestionList(new NameContainsKeywordsPredicate(Arrays.asList(keywords)));
        assertFalse(modelManager.equals(new ModelManager(smartNus, userPrefs)));

        // resets modelManager to initial state for upcoming tests
        modelManager.resetFilteredQuestionList();

        // different userPrefs -> returns false
        UserPrefs differentUserPrefs = new UserPrefs();
        differentUserPrefs.setSmartNusFilePath(Paths.get("differentFilePath"));
        assertFalse(modelManager.equals(new ModelManager(smartNus, differentUserPrefs)));

        // sorted list -> returns false
        modelManager.sortFilteredQuizQuestionList(new QuestionsDefaultComparator());
        assertFalse(modelManager.equals(new ModelManager(smartNus, userPrefs)));

        // Same object -> returns true
        modelManager.updateFilteredTagStatistic(new Predicate<TagStatistic>() {
            @Override
            public boolean test(TagStatistic tagStatistic) {
                return true;
            }
        });
        assertTrue(modelManager.getTagStatistic() != null);
    }

    @Test
    public void setNote_nullArgs_throwsNullPointerException() {
        modelManager.addNote(CS2103T_NOTE);
        assertThrows(NullPointerException.class, () -> modelManager.setNote(CS2103T_NOTE, null));
        assertThrows(NullPointerException.class, () -> modelManager.setNote(null, CS2103T_NOTE));
    }

    @Test
    public void getTagStatistic_correct() {
        modelManager.addQuestion(MCQ_QUESTION_1);
        assertNotEquals(null, modelManager.getTagStatistic());
    }
}
