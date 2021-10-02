package seedu.address.model.folder;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;
import static seedu.address.logic.commands.CommandTestUtil.*;
import static seedu.address.testutil.TypicalPersons.*;

class FolderTest {

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
        Folder editedCCA = new Folder(new FolderName(VALID_FOLDER_NAME_CCA.toLowerCase()));
        assertFalse(CCA.isSameFolder(editedCCA));

        // name has trailing spaces, all other attributes same -> returns false
        String nameWithTrailingSpaces = VALID_FOLDER_NAME_CCA + " ";
        editedCCA = new Folder(new FolderName(nameWithTrailingSpaces));
        assertFalse(CCA.isSameFolder(editedCCA));
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
        Folder editedCCA = new Folder(new FolderName("ABC"));
        assertFalse(CCA.equals(editedCCA));
    }
}
