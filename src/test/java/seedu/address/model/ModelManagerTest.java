package seedu.address.model;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.model.Model.PREDICATE_SHOW_ALL_PERSONS;
import static seedu.address.testutil.Assert.assertThrows;
import static seedu.address.testutil.TypicalModuleLessons.CS2100_LAB1;
import static seedu.address.testutil.TypicalPersons.ALICE;
import static seedu.address.testutil.TypicalPersons.BENSON;
import static seedu.address.testutil.TypicalPersons.BOB;

import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Arrays;

import org.junit.jupiter.api.Test;

import seedu.address.commons.core.GuiSettings;
import seedu.address.model.person.NameContainsKeywordsPredicate;
import seedu.address.testutil.ConthacksBuilder;

public class ModelManagerTest {

    private ModelManager modelManager = new ModelManager();

    @Test
    public void constructor() {
        assertEquals(new UserPrefs(), modelManager.getUserPrefs());
        assertEquals(new GuiSettings(), modelManager.getGuiSettings());
        assertEquals(new Conthacks(), new Conthacks(modelManager.getConthacks()));
    }

    @Test
    public void setUserPrefs_nullUserPrefs_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> modelManager.setUserPrefs(null));
    }

    @Test
    public void setUserPrefs_validUserPrefs_copiesUserPrefs() {
        UserPrefs userPrefs = new UserPrefs();
        userPrefs.setConthacksFilePath(Paths.get("address/book/file/path"));
        userPrefs.setGuiSettings(new GuiSettings(1, 2, 3, 4));
        modelManager.setUserPrefs(userPrefs);
        assertEquals(userPrefs, modelManager.getUserPrefs());

        // Modifying userPrefs should not modify modelManager's userPrefs
        UserPrefs oldUserPrefs = new UserPrefs(userPrefs);
        userPrefs.setConthacksFilePath(Paths.get("new/address/book/file/path"));
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
    public void setConthacksFilePath_nullPath_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> modelManager.setConthacksFilePath(null));
    }

    @Test
    public void setConthacksFilePath_validPath_setsConthacksFilePath() {
        Path path = Paths.get("address/book/file/path");
        modelManager.setConthacksFilePath(path);
        assertEquals(path, modelManager.getConthacksFilePath());
    }

    @Test
    public void hasPerson_nullPerson_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> modelManager.hasPerson(null));
    }

    @Test
    public void hasPerson_personNotInConthacks_returnsFalse() {
        assertFalse(modelManager.hasPerson(ALICE));
    }

    @Test
    public void hasPerson_personInConthacks_returnsTrue() {
        modelManager.addPerson(ALICE);
        assertTrue(modelManager.hasPerson(ALICE));
    }

    @Test
    public void getFilteredPersonList_modifyList_throwsUnsupportedOperationException() {
        assertThrows(UnsupportedOperationException.class, () -> modelManager.getFilteredPersonList().remove(0));
    }

    @Test
    public void hasModuleLessonInConthacks_returnstrue() {
        modelManager.addLesson(CS2100_LAB1);
        assertTrue(modelManager.hasModuleLesson(CS2100_LAB1));
    }

    @Test
    public void equals() {
        Conthacks conthacks = new ConthacksBuilder().withPerson(ALICE).withPerson(BENSON).build();
        Conthacks differentConthacks = new Conthacks();
        UserPrefs userPrefs = new UserPrefs();

        // same values -> returns true
        modelManager = new ModelManager(conthacks, userPrefs);
        ModelManager modelManagerCopy = new ModelManager(conthacks, userPrefs);
        assertTrue(modelManager.equals(modelManagerCopy));

        // same object -> returns true
        assertTrue(modelManager.equals(modelManager));

        // null -> returns false
        assertFalse(modelManager.equals(null));

        // different types -> returns false
        assertFalse(modelManager.equals(5));

        // different conthacks -> returns false
        assertFalse(modelManager.equals(new ModelManager(differentConthacks, userPrefs)));

        // different filteredList -> returns false
        String[] keywords = ALICE.getName().fullName.split("\\s+");
        modelManager.updateFilteredPersonList(new NameContainsKeywordsPredicate(Arrays.asList(keywords)));
        assertFalse(modelManager.equals(new ModelManager(conthacks, userPrefs)));

        // resets modelManager to initial state for upcoming tests
        modelManager.updateFilteredPersonList(PREDICATE_SHOW_ALL_PERSONS);

        // different userPrefs -> returns false
        UserPrefs differentUserPrefs = new UserPrefs();
        differentUserPrefs.setConthacksFilePath(Paths.get("differentFilePath"));
        assertFalse(modelManager.equals(new ModelManager(conthacks, differentUserPrefs)));
    }

    @Test
    public void sortConthacksByName_success() {
        Conthacks conthacks = new Conthacks();
        conthacks.addPerson(BOB);
        conthacks.addPerson(ALICE);
        modelManager.setConthacks(conthacks);

        Conthacks expectedConthacks = new Conthacks();
        expectedConthacks.addPerson(ALICE);
        expectedConthacks.addPerson(BOB);
        ModelManager expectedModelManager = new ModelManager();
        expectedModelManager.setConthacks(expectedConthacks);

        modelManager.sortConthacks();
        assertEquals(expectedModelManager, modelManager);
    }
}
