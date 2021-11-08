package seedu.siasa.storage;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.siasa.testutil.Assert.assertThrows;
import static seedu.siasa.testutil.TypicalContacts.HOON;
import static seedu.siasa.testutil.TypicalContacts.IDA;
import static seedu.siasa.testutil.TypicalSiasa.getTypicalSiasa;

import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.io.TempDir;

import seedu.siasa.commons.exceptions.DataConversionException;
import seedu.siasa.model.ReadOnlySiasa;
import seedu.siasa.model.Siasa;
import seedu.siasa.model.contact.Contact;
import seedu.siasa.model.policy.Policy;

public class JsonSiasaStorageTest {
    private static final Path TEST_DATA_FOLDER = Paths.get("src", "test", "data", "JsonSiasaStorageTest");

    @TempDir
    public Path testFolder;

    @Test
    public void readAddressBook_nullFilePath_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> readAddressBook(null));
    }

    private java.util.Optional<ReadOnlySiasa> readAddressBook(String filePath) throws Exception {
        return new JsonSiasaStorage(Paths.get(filePath)).readSiasa(addToTestDataPathIfNotNull(filePath));
    }

    private Path addToTestDataPathIfNotNull(String prefsFileInTestDataFolder) {
        return prefsFileInTestDataFolder != null
            ? TEST_DATA_FOLDER.resolve(prefsFileInTestDataFolder)
            : null;
    }

    @Test
    public void read_missingFile_emptyResult() throws Exception {
        assertFalse(readAddressBook("NonExistentFile.json").isPresent());
    }

    @Test
    public void read_notJsonFormat_exceptionThrown() {
        assertThrows(DataConversionException.class, () -> readAddressBook("notJsonFormatSiasa.json"));
    }

    @Test
    public void readAddressBook_invalidContactSiasa_throwDataConversionException() {
        assertThrows(DataConversionException.class, () -> readAddressBook("invalidContactSiasa.json"));
    }

    @Test
    public void readAddressBook_invalidAndValidContactSiasa_throwDataConversionException() {
        assertThrows(DataConversionException.class, () -> readAddressBook("invalidAndValidContactsSiasa.json"));
    }

    /*
    TODO: Fix Tests
    @Test
    public void readAddressBook_invalidPolicyAddressBook_throwDataConversionException() {
        assertThrows(DataConversionException.class, () -> readAddressBook("invalidPolicySiasa.json"));
    }

    @Test
    public void readAddressBook_invalidAndValidPolicyAddressBook_throwDataConversionException() {
        assertThrows(DataConversionException.class, () -> readAddressBook("invalidAndValidPolicySiasa.json"));
    }
    */

    @Test
    public void readAndSaveSiasa_allInOrder_success() throws Exception {
        Path filePath = testFolder.resolve("TempAddressBook.json");
        Siasa original = getTypicalSiasa();
        JsonSiasaStorage jsonAddressBookStorage = new JsonSiasaStorage(filePath);

        // Save in new file and read back
        jsonAddressBookStorage.saveSiasa(original, filePath);
        ReadOnlySiasa readBack = jsonAddressBookStorage.readSiasa(filePath).get();
        for (Contact contact : readBack.getContactList()) {
            assertTrue(original.hasContact(contact));
        }
        for (Policy policy : readBack.getPolicyList()) {
            assertTrue(original.hasPolicy(policy));
        }

        // Modify data, overwrite exiting file, and read back
        original.addContact(HOON);
        jsonAddressBookStorage.saveSiasa(original, filePath);
        original.removeContactAndAssociatedPolicies(HOON);
        jsonAddressBookStorage.saveSiasa(original, filePath);
        readBack = jsonAddressBookStorage.readSiasa(filePath).get();
        for (Contact contact : readBack.getContactList()) {
            assertTrue(original.hasContact(contact));
        }
        for (Policy policy : readBack.getPolicyList()) {
            assertTrue(original.hasPolicy(policy));
        }

        // Save and read without specifying file path
        original.addContact(IDA);
        jsonAddressBookStorage.saveSiasa(original); // file path not specified
        readBack = jsonAddressBookStorage.readSiasa().get(); // file path not specified
        for (Contact contact : readBack.getContactList()) {
            assertTrue(original.hasContact(contact));
        }
        for (Policy policy : readBack.getPolicyList()) {
            assertTrue(original.hasPolicy(policy));
        }

    }

    @Test
    public void saveSiasa_nullSiasa_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> saveSiasa(null, "SomeFile.json"));
    }

    /**
     * Saves {@code addressBook} at the specified {@code filePath}.
     */
    private void saveSiasa(ReadOnlySiasa addressBook, String filePath) {
        try {
            new JsonSiasaStorage(Paths.get(filePath))
                .saveSiasa(addressBook, addToTestDataPathIfNotNull(filePath));
        } catch (IOException ioe) {
            throw new AssertionError("There should not be an error writing to the file.", ioe);
        }
    }
}
