package seedu.address.storage;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static seedu.address.storage.JsonAdaptedFolder.FOLDER_MISSING_FIELD_MESSAGE_FORMAT;
import static seedu.address.testutil.Assert.assertThrows;
import static seedu.address.testutil.TypicalFolders.CS2103;

import java.util.ArrayList;

import org.junit.jupiter.api.Test;

import seedu.address.commons.exceptions.IllegalValueException;
import seedu.address.model.folder.FolderName;

class JsonAdaptedFolderTest {
    private static final String INVALID_FOLDER_NAME = "@friends";

    @Test
    public void toModelType_validFolderDetails_returnsFolder() throws Exception {
        JsonAdaptedFolder folder = new JsonAdaptedFolder(CS2103);
        assertEquals(CS2103, folder.toModelType());
    }

    @Test
    public void toModelType_invalidFolderDetails_throwsIllegalValueException() {
        JsonAdaptedFolder folder = new JsonAdaptedFolder(INVALID_FOLDER_NAME, new ArrayList<>());
        String expectedMessage = FolderName.MESSAGE_CONSTRAINTS;
        assertThrows(IllegalValueException.class, expectedMessage, folder::toModelType);
    }

    @Test
    public void toModelType_nullFolderName_throwsIllegalValueException() {
        JsonAdaptedFolder folder = new JsonAdaptedFolder(null, new ArrayList<>());
        String expectedMessage = String.format(FOLDER_MISSING_FIELD_MESSAGE_FORMAT, FolderName.class.getSimpleName());
        assertThrows(IllegalValueException.class, expectedMessage, folder::toModelType);
    }
}
