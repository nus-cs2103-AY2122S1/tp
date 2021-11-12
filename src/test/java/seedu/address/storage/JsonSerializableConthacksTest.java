package seedu.address.storage;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static seedu.address.testutil.Assert.assertThrows;

import java.nio.file.Path;
import java.nio.file.Paths;

import org.junit.jupiter.api.Test;

import seedu.address.commons.exceptions.IllegalValueException;
import seedu.address.commons.util.JsonUtil;
import seedu.address.model.Conthacks;
import seedu.address.testutil.TypicalModuleLessons;
import seedu.address.testutil.TypicalPersons;

public class JsonSerializableConthacksTest {

    private static final Path TEST_DATA_FOLDER = Paths.get("src", "test", "data", "JsonSerializableConthacksTest");
    private static final Path TYPICAL_PERSONS_FILE = TEST_DATA_FOLDER.resolve("typicalPersonsConthacks.json");
    private static final Path TYPICAL_MODULE_LESSON_FILE = TEST_DATA_FOLDER
            .resolve("typicalModuleLessonsConthacks.json");
    private static final Path INVALID_PERSON_FILE = TEST_DATA_FOLDER.resolve("invalidPersonConthacks.json");
    private static final Path INVALID_MODULE_LESSON_FILE = TEST_DATA_FOLDER
            .resolve("invalidModuleLessonConthacks.json");
    private static final Path DUPLICATE_PERSON_FILE = TEST_DATA_FOLDER.resolve("duplicatePersonConthacks.json");
    private static final Path DUPLICATE_MODULE_LESSON_FILE = TEST_DATA_FOLDER.resolve(
            "duplicateModuleLessonConthacks.json");

    @Test
    public void toModelType_typicalPersonsFile_success() throws Exception {
        JsonSerializableConthacks dataFromFile = JsonUtil.readJsonFile(TYPICAL_PERSONS_FILE,
                JsonSerializableConthacks.class).get();
        Conthacks conthacksFromFile = dataFromFile.toModelType();
        Conthacks typicalPersonsConthacks = TypicalPersons.getTypicalConthacks();
        assertEquals(conthacksFromFile, typicalPersonsConthacks);
    }

    @Test
    public void toModelType_typicalModuleLessonsFile_success() throws Exception {
        JsonSerializableConthacks dataFromFile = JsonUtil.readJsonFile(TYPICAL_MODULE_LESSON_FILE,
                JsonSerializableConthacks.class).get();
        Conthacks conthacksFromFile = dataFromFile.toModelType();
        Conthacks typicalModuleClasses = TypicalModuleLessons.getTypicalConthacks();
        assertEquals(conthacksFromFile, typicalModuleClasses);
    }

    @Test
    public void toModelType_invalidPersonFile_throwsIllegalValueException() throws Exception {
        JsonSerializableConthacks dataFromFile = JsonUtil.readJsonFile(INVALID_PERSON_FILE,
                JsonSerializableConthacks.class).get();
        assertThrows(IllegalValueException.class, dataFromFile::toModelType);
    }

    @Test
    public void toModelType_invalidModuleLesson_throwsIllegalArgument() throws Exception {
        JsonSerializableConthacks dataFromFile = JsonUtil.readJsonFile(INVALID_MODULE_LESSON_FILE,
                JsonSerializableConthacks.class).get();
        assertThrows(IllegalArgumentException.class, dataFromFile::toModelType);
    }

    @Test
    public void toModelType_duplicatePersons_throwsIllegalValueException() throws Exception {
        JsonSerializableConthacks dataFromFile = JsonUtil.readJsonFile(DUPLICATE_PERSON_FILE,
                JsonSerializableConthacks.class).get();
        assertThrows(IllegalValueException.class, JsonSerializableConthacks.MESSAGE_DUPLICATE_PERSON,
                dataFromFile::toModelType);
    }

    @Test
    public void toModelType_duplicateModuleLessons_throwsIllegalValueException() throws Exception {
        JsonSerializableConthacks dataFromFile = JsonUtil.readJsonFile(DUPLICATE_MODULE_LESSON_FILE,
                JsonSerializableConthacks.class).get();
        assertThrows(IllegalValueException.class, JsonSerializableConthacks.MESSAGE_DUPLICATE_LESSON,
                dataFromFile::toModelType);
    }

}
