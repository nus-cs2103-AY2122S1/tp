package seedu.address.model;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.model.Model.PREDICATE_SHOW_ALL_INTERVIEWS;
import static seedu.address.model.Model.PREDICATE_SHOW_ALL_PERSONS;
import static seedu.address.model.Model.PREDICATE_SHOW_ALL_POSITIONS;
import static seedu.address.testutil.Assert.assertThrows;
import static seedu.address.testutil.TypicalInterviews.ASSISTANT_INTERVIEW;
import static seedu.address.testutil.TypicalInterviews.BOOKKEEPER_INTERVIEW;
import static seedu.address.testutil.TypicalPersons.ALICE;
import static seedu.address.testutil.TypicalPersons.BENSON;
import static seedu.address.testutil.TypicalPositions.ADMIN_ASSISTANT;
import static seedu.address.testutil.TypicalPositions.BOOKKEEPER;

import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Arrays;

import org.junit.jupiter.api.Test;

import seedu.address.commons.core.GuiSettings;
import seedu.address.model.interview.PositionTitleContainsKeywordsPredicate;
import seedu.address.model.person.FindCandidateCommandPredicate;
import seedu.address.model.position.TitleContainsKeywordsPredicate;
import seedu.address.testutil.HrManagerBuilder;

public class ModelManagerTest {

    private ModelManager modelManager = new ModelManager();

    @Test
    public void constructor() {
        assertEquals(new UserPrefs(), modelManager.getUserPrefs());
        assertEquals(new GuiSettings(), modelManager.getGuiSettings());
        assertEquals(new HrManager(), new HrManager(modelManager.getHrManager()));
    }

    @Test
    public void setUserPrefs_nullUserPrefs_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> modelManager.setUserPrefs(null));
    }

    @Test
    public void setUserPrefs_validUserPrefs_copiesUserPrefs() {
        UserPrefs userPrefs = new UserPrefs();
        userPrefs.setHrManagerCandidatesFilePath(Paths.get("address/book/file/path"));
        userPrefs.setGuiSettings(new GuiSettings(1, 2, 3, 4));
        modelManager.setUserPrefs(userPrefs);
        assertEquals(userPrefs, modelManager.getUserPrefs());

        // Modifying userPrefs should not modify modelManager's userPrefs
        UserPrefs oldUserPrefs = new UserPrefs(userPrefs);
        userPrefs.setHrManagerCandidatesFilePath(Paths.get("new/address/book/file/path"));
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
    public void setHrManagerCandidateFilePath_nullPath_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> modelManager.setHrManagerCandidatesFilePath(null));
    }

    @Test
    public void setHrManagerCandidateFilePath_validPath_setsHrManagerCandidateFilePath() {
        Path path = Paths.get("address/book/file/path");
        modelManager.setHrManagerCandidatesFilePath(path);
        assertEquals(path, modelManager.getHrManagerCandidatesFilePath());
    }

    @Test
    public void setHrManagerPositionsFilePath_nullPath_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> modelManager.setHrManagerPositionsFilePath(null));
    }

    @Test
    public void setHrManagerPositionsFilePath_validPath_setsHrManagerPositionsFilePath() {
        Path path = Paths.get("address/book/file/path");
        modelManager.setHrManagerPositionsFilePath(path);
        assertEquals(path, modelManager.getHrManagerPositionsFilePath());
    }

    @Test
    public void setHrManagerInterviewsFilePath_nullPath_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> modelManager.setHrManagerInterviewsFilePath(null));
    }

    @Test
    public void setHrManagerInterviewsFilePath_validPath_setsHrManagerInterviewsFilePath() {
        Path path = Paths.get("address/book/file/path");
        modelManager.setHrManagerInterviewsFilePath(path);
        assertEquals(path, modelManager.getHrManagerInterviewsFilePath());
    }

    @Test
    public void hasPerson_nullPerson_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> modelManager.hasPerson(null));
    }

    @Test
    public void hasPerson_personNotInHrManager_returnsFalse() {
        assertFalse(modelManager.hasPerson(ALICE));
    }

    @Test
    public void hasPerson_personInHrManager_returnsTrue() {
        modelManager.addPerson(ALICE);
        assertTrue(modelManager.hasPerson(ALICE));
    }

    @Test
    public void getFilteredPersonList_modifyList_throwsUnsupportedOperationException() {
        assertThrows(UnsupportedOperationException.class, () -> modelManager.getFilteredPersonList().remove(0));
    }

    @Test
    public void hasPosition_nullPosition_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> modelManager.hasPosition(null));
    }

    @Test
    public void hasPosition_positionNotInHrManager_returnsFalse() {
        assertFalse(modelManager.hasPosition(ADMIN_ASSISTANT));
    }

    @Test
    public void hasPosition_positionInHrManager_returnsTrue() {
        modelManager.addPosition(ADMIN_ASSISTANT);
        assertTrue(modelManager.hasPosition(ADMIN_ASSISTANT));
    }

    @Test
    public void getFilteredPositionList_modifyList_throwsUnsupportedOperationException() {
        assertThrows(UnsupportedOperationException.class, () -> modelManager.getFilteredPositionList().remove(0));
    }

    @Test
    public void hasInterview_nullInterview_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> modelManager.hasInterview(null));
    }

    @Test
    public void hasInterview_interviewNotInHrManager_returnsFalse() {
        assertFalse(modelManager.hasInterview(ASSISTANT_INTERVIEW));
    }

    @Test
    public void hasInterview_interviewInHrManager_returnsTrue() {
        modelManager.addInterview(ASSISTANT_INTERVIEW);
        assertTrue(modelManager.hasInterview(ASSISTANT_INTERVIEW));
    }

    @Test
    public void getFilteredInterviewList_modifyList_throwsUnsupportedOperationException() {
        assertThrows(UnsupportedOperationException.class, () -> modelManager.getFilteredInterviewList().remove(0));
    }

    @Test
    public void equals() {
        HrManager hrManager = new HrManagerBuilder()
                .withPerson(ALICE).withPerson(BENSON)
                .withPosition(ADMIN_ASSISTANT).withPosition(BOOKKEEPER)
                .withInterview(ASSISTANT_INTERVIEW).withInterview(BOOKKEEPER_INTERVIEW)
                .build();
        HrManager differentHrManager = new HrManager();
        UserPrefs userPrefs = new UserPrefs();

        // same values -> returns true
        modelManager = new ModelManager(hrManager, userPrefs);
        ModelManager modelManagerCopy = new ModelManager(hrManager, userPrefs);
        assertTrue(modelManager.equals(modelManagerCopy));

        // same object -> returns true
        assertTrue(modelManager.equals(modelManager));

        // null -> returns false
        assertFalse(modelManager.equals(null));

        // different types -> returns false
        assertFalse(modelManager.equals(5));

        // different hrManager -> returns false
        assertFalse(modelManager.equals(new ModelManager(differentHrManager, userPrefs)));

        // different filteredList -> returns false
        String[] keywords = ALICE.getName().fullName.split("\\s+");
        modelManager.updateFilteredPersonList(new FindCandidateCommandPredicate(Arrays.asList(keywords)));
        assertFalse(modelManager.equals(new ModelManager(hrManager, userPrefs)));

        String[] keywordsTitle = ADMIN_ASSISTANT.getTitle().fullTitle.split("\\s+");
        modelManager.updateFilteredPositionList(new TitleContainsKeywordsPredicate(Arrays.asList(keywords)));
        assertFalse(modelManager.equals(new ModelManager(hrManager, userPrefs)));

        String[] keywordsInterviewPositionTitle = ADMIN_ASSISTANT.getTitle().fullTitle.split("\\s+");
        modelManager.updateFilteredInterviewList(new PositionTitleContainsKeywordsPredicate(Arrays.asList(keywords)));
        assertFalse(modelManager.equals(new ModelManager(hrManager, userPrefs)));

        // resets modelManager to initial state for upcoming tests
        modelManager.updateFilteredPersonList(PREDICATE_SHOW_ALL_PERSONS);
        modelManager.updateFilteredPositionList(PREDICATE_SHOW_ALL_POSITIONS);
        modelManager.updateFilteredInterviewList(PREDICATE_SHOW_ALL_INTERVIEWS);

        // different userPrefs -> returns false
        UserPrefs differentUserPrefs = new UserPrefs();
        differentUserPrefs.setHrManagerCandidatesFilePath(Paths.get("differentFilePath"));
        assertFalse(modelManager.equals(new ModelManager(hrManager, differentUserPrefs)));
    }
}
