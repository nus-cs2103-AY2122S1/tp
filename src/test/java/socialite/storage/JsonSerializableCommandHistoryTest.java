package socialite.storage;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.nio.file.Path;
import java.nio.file.Paths;

import org.junit.jupiter.api.Test;

import socialite.commons.util.JsonUtil;
import socialite.model.CommandHistory;
import socialite.testutil.TypicalCommandHistory;

public class JsonSerializableCommandHistoryTest {
    private static final Path TEST_DATA_FOLDER = Paths.get("src", "test", "data", "JsonSerializableCommandHistoryTest");
    private static final Path TYPICAL_COMMAND_HISTORY_FILE = TEST_DATA_FOLDER.resolve("typicalCommandHistory.json");

    @Test
    public void toModelType_commandHistoryFile_success() throws Exception {
        JsonSerializableCommandHistory dataFromFile = JsonUtil.readJsonFile(TYPICAL_COMMAND_HISTORY_FILE,
                JsonSerializableCommandHistory.class).get();
        CommandHistory commandHistoryFromFile = dataFromFile.toModelType();
        CommandHistory typicalCommandHistory = TypicalCommandHistory.getTypicalCommandHistory();
        assertEquals(commandHistoryFromFile, typicalCommandHistory);
    }
}
