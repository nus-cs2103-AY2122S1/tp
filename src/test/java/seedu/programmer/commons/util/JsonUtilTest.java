package seedu.programmer.commons.util;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertThrows;

import java.io.File;
import java.io.IOException;
import java.nio.file.Path;

import org.json.JSONArray;
import org.json.JSONException;
import org.junit.jupiter.api.Test;

import seedu.programmer.testutil.SerializableTestClass;
import seedu.programmer.testutil.TestUtil;

/**
 * Tests JSON Read and Write
 */
public class JsonUtilTest {

    private static final String TEST_DATA_FOLDER = "src/test/data/JsonUtilTest/";
    private static final Path SERIALIZATION_FILE = TestUtil.getFilePathInSandboxFolder("serialize.json");
    private static final String VALID_JSON = TEST_DATA_FOLDER + "programmerError.json";
    private static final String INVALID_JSON = TEST_DATA_FOLDER + "invalidProgrammerError.json";
    private static final int DEFAULT_NUM_OF_STUDENTS = 36;

    @Test
    public void serializeObjectToJsonFile_noExceptionThrown() throws IOException {
        SerializableTestClass serializableTestClass = new SerializableTestClass();
        serializableTestClass.setTestValues();

        JsonUtil.serializeObjectToJsonFile(SERIALIZATION_FILE, serializableTestClass);

        assertEquals(FileUtil.readFromFile(SERIALIZATION_FILE), SerializableTestClass.JSON_STRING_REPRESENTATION);
    }

    @Test
    public void deserializeObjectFromJsonFile_noExceptionThrown() throws IOException {
        FileUtil.writeToFile(SERIALIZATION_FILE, SerializableTestClass.JSON_STRING_REPRESENTATION);

        SerializableTestClass serializableTestClass = JsonUtil
                .deserializeObjectFromJsonFile(SERIALIZATION_FILE, SerializableTestClass.class);

        assertEquals(serializableTestClass.getName(), SerializableTestClass.getNameTestValue());
        assertEquals(serializableTestClass.getListOfLocalDateTimes(), SerializableTestClass.getListTestValues());
        assertEquals(serializableTestClass.getMapOfIntegerToString(), SerializableTestClass.getHashMapTestValues());
    }

    @Test
    public void getJsonData_validJson_returnsCorrectNumberOfElements() {
        JSONArray result = JsonUtil.getJsonData(VALID_JSON);
        assert result != null;
        assertEquals(result.length(), DEFAULT_NUM_OF_STUDENTS);
    }

    @Test
    public void getJsonData_invalidJson_returnsNull() {
        JSONArray result = JsonUtil.getJsonData(INVALID_JSON);
        assertNull(result);
    }

    @Test
    public void writeJsonToCsv_invalidJsonData_throwsException() throws JSONException {
        File testCsvFile = new File("writeJsonTest.csv");
        assertThrows(NullPointerException.class, () -> JsonUtil.writeJsonToCsv(null, testCsvFile));
    }
}
