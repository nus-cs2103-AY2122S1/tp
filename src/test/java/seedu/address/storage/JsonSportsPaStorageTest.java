package seedu.address.storage;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static seedu.address.testutil.Assert.assertThrows;
import static seedu.address.testutil.TypicalFacilities.UNIVERSITY_TOWN_SPORTS_HALL_1_COURT_1;
import static seedu.address.testutil.TypicalFacilities.UNIVERSITY_TOWN_SPORTS_HALL_1_COURT_2;
import static seedu.address.testutil.TypicalMembers.ALICE;
import static seedu.address.testutil.TypicalMembers.HOON;
import static seedu.address.testutil.TypicalMembers.IDA;
import static seedu.address.testutil.TypicalSportsPa.getTypicalSportsPa;

import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.io.TempDir;

import seedu.address.commons.exceptions.DataConversionException;
import seedu.address.model.ReadOnlySportsPa;
import seedu.address.model.SportsPa;

public class JsonSportsPaStorageTest {
    private static final Path TEST_DATA_FOLDER = Paths.get("src", "test", "data", "JsonSportsPaStorageTest");

    @TempDir
    public Path testFolder;

    @Test
    public void readSportsPa_nullFilePath_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> readSportsPa(null));
    }

    private java.util.Optional<ReadOnlySportsPa> readSportsPa(String filePath) throws Exception {
        return new JsonSportsPaStorage(Paths.get(filePath)).readSportsPa(addToTestDataPathIfNotNull(filePath));
    }

    private Path addToTestDataPathIfNotNull(String prefsFileInTestDataFolder) {
        return prefsFileInTestDataFolder != null
                ? TEST_DATA_FOLDER.resolve(prefsFileInTestDataFolder)
                : null;
    }

    @Test
    public void read_missingFile_emptyResult() throws Exception {
        assertFalse(readSportsPa("NonExistentFile.json").isPresent());
    }

    @Test
    public void read_notJsonFormat_exceptionThrown() {
        assertThrows(DataConversionException.class, () -> readSportsPa("notJsonFormatSportsPa.json"));
    }

    @Test
    public void readAddressBook_invalidMemberAddressBook_throwDataConversionException() {
        assertThrows(DataConversionException.class, () -> readSportsPa("invalidMemberSportsPa.json"));
    }

    @Test
    public void readAddressBook_invalidAndValidMemberAddressBook_throwDataConversionException() {
        assertThrows(DataConversionException.class, () -> readSportsPa("invalidAndValidMemberSportsPa.json"));
    }

    @Test
    public void readAddressBook_invalidFacilityAddressBook_throwDataConversionException() {
        assertThrows(DataConversionException.class, () -> readSportsPa("invalidFacilitySportsPa.json"));
    }

    @Test
    public void readAddressBook_invalidAndValidFacilityAddressBook_throwDataConversionException() {
        assertThrows(DataConversionException.class, () -> readSportsPa("invalidAndValidFacilitySportsPa.json"));
    }

    @Test
    public void readAndSaveSportsPa_allInOrder_success() throws Exception {
        Path filePath = testFolder.resolve("TempSportsPa.json");
        SportsPa original = getTypicalSportsPa();
        JsonSportsPaStorage jsonAddressBookStorage = new JsonSportsPaStorage(filePath);

        // Save in new file and read back
        jsonAddressBookStorage.saveSportsPa(original, filePath);
        ReadOnlySportsPa readBack = jsonAddressBookStorage.readSportsPa(filePath).get();
        assertEquals(original, new SportsPa(readBack));

        // Modify data, overwrite exiting file, and read back
        original.addMember(HOON);
        original.addFacility(UNIVERSITY_TOWN_SPORTS_HALL_1_COURT_1);
        original.removePerson(ALICE);
        jsonAddressBookStorage.saveSportsPa(original, filePath);
        readBack = jsonAddressBookStorage.readSportsPa(filePath).get();
        assertEquals(original, new SportsPa(readBack));

        // Save and read without specifying file path
        original.addMember(IDA);
        original.addFacility(UNIVERSITY_TOWN_SPORTS_HALL_1_COURT_2);
        jsonAddressBookStorage.saveSportsPa(original); // file path not specified
        readBack = jsonAddressBookStorage.readSportsPa().get(); // file path not specified
        assertEquals(original, new SportsPa(readBack));

    }

    @Test
    public void saveSportsPa_nullSportsPa_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> saveSportsPa(null, "SomeFile.json"));
    }

    /**
     * Saves {@code sportsPa} at the specified {@code filePath}.
     */
    private void saveSportsPa(ReadOnlySportsPa sportsPa, String filePath) {
        try {
            new JsonSportsPaStorage(Paths.get(filePath))
                    .saveSportsPa(sportsPa, addToTestDataPathIfNotNull(filePath));
        } catch (IOException ioe) {
            throw new AssertionError("There should not be an error writing to the file.", ioe);
        }
    }

    @Test
    public void saveSportsPa_nullFilePath_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> saveSportsPa(new SportsPa(), null));
    }
}
