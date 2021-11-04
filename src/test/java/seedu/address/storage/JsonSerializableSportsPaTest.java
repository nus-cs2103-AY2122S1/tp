package seedu.address.storage;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static seedu.address.testutil.Assert.assertThrows;

import java.nio.file.Path;
import java.nio.file.Paths;

import org.junit.jupiter.api.Test;

import seedu.address.commons.exceptions.IllegalValueException;
import seedu.address.commons.util.JsonUtil;
import seedu.address.model.SportsPa;
import seedu.address.testutil.TypicalSportsPa;

public class JsonSerializableSportsPaTest {

    private static final Path TEST_DATA_FOLDER = Paths.get("src", "test", "data", "JsonSerializableSportsPaTest");
    private static final Path TYPICAL_MEMBERS_FILE = TEST_DATA_FOLDER.resolve("typicalMembersSportsPa.json");
    private static final Path TYPICAL_FACILITIES_FILE = TEST_DATA_FOLDER.resolve("typicalFacilitiesSportsPa.json");
    private static final Path INVALID_MEMBER_FILE = TEST_DATA_FOLDER.resolve("invalidMemberSportsPa.json");
    private static final Path INVALID_FACILITY_FILE = TEST_DATA_FOLDER.resolve("invalidFacilitySportsPa.json");
    private static final Path DUPLICATE_MEMBER_FILE = TEST_DATA_FOLDER.resolve("duplicateMemberSportsPa.json");
    private static final Path DUPLICATE_FACILITY_FILE = TEST_DATA_FOLDER.resolve("duplicateFacilitySportsPa.json");

    @Test
    public void toModelType_typicalMembersFile_success() throws Exception {
        JsonSerializableSportsPa dataFromFile = JsonUtil.readJsonFile(TYPICAL_MEMBERS_FILE,
                JsonSerializableSportsPa.class).get();
        SportsPa sportsPaFromFile = dataFromFile.toModelType();
        SportsPa typicalMembersSportsPa = TypicalSportsPa.getTypicalSportsPaEmptyFacilityList();
        assertEquals(sportsPaFromFile.getMemberList(), typicalMembersSportsPa.getMemberList());
    }

    @Test
    public void toModelType_typicalFacilitiesFile_success() throws Exception {
        JsonSerializableSportsPa dataFromFile = JsonUtil.readJsonFile(TYPICAL_FACILITIES_FILE,
                JsonSerializableSportsPa.class).get();
        SportsPa sportsPaFromFile = dataFromFile.toModelType();
        SportsPa typicalFacilitiesSportsPa = TypicalSportsPa.getTypicalSportsPaEmptyMemberList();
        assertEquals(sportsPaFromFile.getFacilityList(), typicalFacilitiesSportsPa.getFacilityList());
    }

    @Test
    public void toModelType_invalidMemberFile_throwsIllegalValueException() throws Exception {
        JsonSerializableSportsPa dataFromFile = JsonUtil.readJsonFile(INVALID_MEMBER_FILE,
                JsonSerializableSportsPa.class).get();
        assertThrows(IllegalValueException.class, dataFromFile::toModelType);
    }

    @Test
    public void toModelType_invalidFacilitiesFile_throwsIllegalValueException() throws Exception {
        JsonSerializableSportsPa dataFromFile = JsonUtil.readJsonFile(INVALID_FACILITY_FILE,
                JsonSerializableSportsPa.class).get();
        assertThrows(IllegalValueException.class, dataFromFile::toModelType);
    }

    @Test
    public void toModelType_duplicateMembers_throwsIllegalValueException() throws Exception {
        JsonSerializableSportsPa dataFromFile = JsonUtil.readJsonFile(DUPLICATE_MEMBER_FILE,
                JsonSerializableSportsPa.class).get();
        assertThrows(IllegalValueException.class, JsonSerializableSportsPa.MESSAGE_DUPLICATE_MEMBER,
                dataFromFile::toModelType);
    }

    @Test
    public void toModelType_duplicateFacilities_throwsIllegalValueException() throws Exception {
        JsonSerializableSportsPa dataFromFile = JsonUtil.readJsonFile(DUPLICATE_FACILITY_FILE,
                JsonSerializableSportsPa.class).get();
        assertThrows(IllegalValueException.class, JsonSerializableSportsPa.MESSAGE_DUPLICATE_FACILITY,
                dataFromFile::toModelType);
    }

}
