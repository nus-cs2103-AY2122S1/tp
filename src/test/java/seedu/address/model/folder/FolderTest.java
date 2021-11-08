package seedu.address.model.folder;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.logic.commands.CommandTestUtil.VALID_FOLDER_NAME_CCA;
import static seedu.address.testutil.TypicalFolders.CCA;
import static seedu.address.testutil.TypicalFolders.CS2103;
import static seedu.address.testutil.TypicalPersons.ALICE;

import java.util.HashSet;

import org.junit.jupiter.api.Test;

import seedu.address.model.person.Person;

class FolderTest {

    @Test
    public void constructor_validParameters_success() {
        Folder validFolder = new Folder(new FolderName(VALID_FOLDER_NAME_CCA), new HashSet<Person>());
        assertEquals(validFolder, CCA);
    }

    @Test
    public void constructor_nullParameters_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> new Folder(null, null));
    }

    @Test
    public void getContacts_success() {
        HashSet<Person> people = new HashSet<>();
        people.add(ALICE);
        Folder validFolder = new Folder(new FolderName(VALID_FOLDER_NAME_CCA), people);
        assertEquals(validFolder.getContacts(), people);
    }

    @Test
    public void isSameFolder() {
        // same object -> returns true
        assertTrue(CCA.isSameFolder(CCA));

        // null -> returns false
        assertFalse(CCA.isSameFolder(null));

        // same name, all other attributes different -> returns true
        Folder ccaDuplicate = new Folder(new FolderName(VALID_FOLDER_NAME_CCA));
        assertTrue(CCA.isSameFolder(ccaDuplicate));

        // name differs in case, all other attributes same -> returns false
        Folder editedCca = new Folder(new FolderName(VALID_FOLDER_NAME_CCA.toLowerCase()));
        assertFalse(CCA.isSameFolder(editedCca));

        // name has trailing spaces, all other attributes same -> returns false
        String nameWithTrailingSpaces = VALID_FOLDER_NAME_CCA + " ";
        editedCca = new Folder(new FolderName(nameWithTrailingSpaces));
        assertFalse(CCA.isSameFolder(editedCca));
    }

    @Test
    public void equals() {
        // same values -> returns true
        Folder ccaCopy = new Folder(new FolderName(VALID_FOLDER_NAME_CCA));
        assertTrue(CCA.equals(ccaCopy));

        // same object -> returns true
        assertTrue(CCA.equals(CCA));

        // null -> returns false
        assertFalse(CCA.equals(null));

        // different type -> returns false
        assertFalse(CCA.equals(5));

        // different folder -> returns false
        assertFalse(CCA.equals(CS2103));

        // different name -> returns false
        Folder editedCca = new Folder(new FolderName("ABC"));
        assertFalse(CCA.equals(editedCca));
    }
}
