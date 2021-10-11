package seedu.address.commons.util;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.io.IOException;
import java.nio.file.Path;

import org.junit.jupiter.api.Test;

import seedu.address.testutil.SerializableTestClass;
import seedu.address.testutil.TestUtil;

/**
 * Tests JSON Read and Write
 */
public class EncryptedJsonUtilTest {

    private static final Path SERIALIZATION_FILE = TestUtil.getFilePathInSandboxFolder("serialize");

    @Test
    public void serializeObjectToEncryptedJsonFile_noExceptionThrown() throws IOException {
        SerializableTestClass serializableTestClass = new SerializableTestClass();
        serializableTestClass.setTestValues();

        assertEquals(FileUtil.readFromFile(SERIALIZATION_FILE),
                SerializableTestClass.ENCRYPTED_JSON_STRING_REPRESENTATION);
    }

    @Test
    public void deserializeObjectFromEncryptedJsonFile_noExceptionThrown() throws IOException {
        FileUtil.writeToEncryptedFile(SERIALIZATION_FILE,
                EncryptionUtil.encryptSerializableObject(SerializableTestClass.JSON_STRING_REPRESENTATION));

        SerializableTestClass serializableTestClass = EncryptedJsonUtil
                .deserializeObjectFromEncryptedJsonFile(SERIALIZATION_FILE, SerializableTestClass.class);

        assertEquals(serializableTestClass.getName(), SerializableTestClass.getNameTestValue());
        assertEquals(serializableTestClass.getListOfLocalDateTimes(), SerializableTestClass.getListTestValues());
        assertEquals(serializableTestClass.getMapOfIntegerToString(), SerializableTestClass.getHashMapTestValues());
    }

    //TODO: @Test jsonUtil_readJsonStringToObjectInstance_correctObject()

    //TODO: @Test jsonUtil_writeThenReadObjectToJson_correctObject()
}
