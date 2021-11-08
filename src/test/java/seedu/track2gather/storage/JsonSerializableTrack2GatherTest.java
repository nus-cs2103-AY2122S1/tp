package seedu.track2gather.storage;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static seedu.track2gather.testutil.Assert.assertThrows;

import java.nio.file.Path;
import java.nio.file.Paths;

import org.junit.jupiter.api.Test;

import seedu.track2gather.commons.exceptions.IllegalValueException;
import seedu.track2gather.commons.util.JsonUtil;
import seedu.track2gather.model.Track2Gather;
import seedu.track2gather.testutil.TypicalPersons;

public class JsonSerializableTrack2GatherTest {

    private static final Path TEST_DATA_FOLDER = Paths.get("src", "test", "data", "JsonSerializableTrack2GatherTest");
    private static final Path TYPICAL_PERSONS_FILE = TEST_DATA_FOLDER.resolve("typicalPersonsTrack2Gather.json");
    private static final Path INVALID_PERSON_FILE = TEST_DATA_FOLDER.resolve("invalidPersonTrack2Gather.json");
    private static final Path DUPLICATE_PERSON_FILE = TEST_DATA_FOLDER.resolve("duplicatePersonTrack2Gather.json");

    @Test
    public void toModelType_typicalPersonsFile_success() throws Exception {
        JsonSerializableTrack2Gather dataFromFile = JsonUtil.readJsonFile(TYPICAL_PERSONS_FILE,
                JsonSerializableTrack2Gather.class).get();
        Track2Gather track2GatherFromFile = dataFromFile.toModelType();
        Track2Gather typicalPersonsTrack2Gather = TypicalPersons.getTypicalTrack2Gather();
        assertEquals(track2GatherFromFile, typicalPersonsTrack2Gather);
    }

    @Test
    public void toModelType_invalidPersonFile_throwsIllegalValueException() throws Exception {
        JsonSerializableTrack2Gather dataFromFile = JsonUtil.readJsonFile(INVALID_PERSON_FILE,
                JsonSerializableTrack2Gather.class).get();
        assertThrows(IllegalValueException.class, dataFromFile::toModelType);
    }

    @Test
    public void toModelType_duplicatePersons_throwsIllegalValueException() throws Exception {
        JsonSerializableTrack2Gather dataFromFile = JsonUtil.readJsonFile(DUPLICATE_PERSON_FILE,
                JsonSerializableTrack2Gather.class).get();
        assertThrows(IllegalValueException.class, JsonSerializableTrack2Gather.MESSAGE_DUPLICATE_PERSON,
                dataFromFile::toModelType);
    }

}
