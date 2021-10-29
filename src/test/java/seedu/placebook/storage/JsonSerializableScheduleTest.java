package seedu.placebook.storage;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static seedu.placebook.testutil.Assert.assertThrows;

import java.nio.file.Path;
import java.nio.file.Paths;

import org.junit.jupiter.api.Test;

import seedu.placebook.commons.exceptions.IllegalValueException;
import seedu.placebook.commons.util.JsonUtil;
import seedu.placebook.model.schedule.Schedule;
import seedu.placebook.testutil.TypicalAppointment;

public class JsonSerializableScheduleTest {

    private static final Path TEST_DATA_FOLDER = Paths.get("src", "test", "data", "JsonSerializableScheduleTest");
    private static final Path TYPICAL_APPOINTMENT_FILE = TEST_DATA_FOLDER.resolve("typicalAppointmentSchedule.json");
    private static final Path DUPLICATE_APPOINTMENT_FILE =
            TEST_DATA_FOLDER.resolve("duplicateAppointmentSchedule.json");

    @Test
    public void toModelType_typicalPersonsFile_success() throws Exception {
        JsonSerializableSchedule dataFromFile = JsonUtil.readJsonFile(TYPICAL_APPOINTMENT_FILE,
                JsonSerializableSchedule.class).get();
        Schedule scheduleFromFile = dataFromFile.toModelType();
        Schedule typicalSchedule = TypicalAppointment.getTypicalSchedule();
        assertEquals(typicalSchedule, scheduleFromFile);

    }

    @Test
    public void toModelType_duplicateAppointment_throwsIllegalValueException() throws Exception {
        JsonSerializableSchedule dataFromFile = JsonUtil.readJsonFile(DUPLICATE_APPOINTMENT_FILE,
                JsonSerializableSchedule.class).get();
        assertThrows(IllegalValueException.class, JsonSerializableSchedule.MESSAGE_DUPLICATE_APPOINTMENT,
                dataFromFile::toModelType);
    }
}
