package seedu.address.storage;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.nio.file.Path;
import java.nio.file.Paths;

import org.junit.jupiter.api.Test;

import seedu.address.commons.util.JsonUtil;
import seedu.address.model.Shortcut;
import seedu.address.testutil.TypicalPersons;

public class JsonSerializableShortcutTest {

    private static final Path TEST_DATA_FOLDER = Paths.get("src", "test", "data", "JsonSerializableShortcutTest");
    private static final Path TYPICAL_SHORTCUT_FILE = TEST_DATA_FOLDER.resolve("typicalShortcut.json");

    @Test
    public void toModelType_typicalShortcutFile_success() throws Exception {
        JsonSerializableShortcut dataFromFile = JsonUtil.readJsonFile(TYPICAL_SHORTCUT_FILE,
                JsonSerializableShortcut.class).get();
        Shortcut shortcutFromFile = dataFromFile.toModelType();
        Shortcut typicalShortcut = TypicalPersons.getTypicalShortcut();
        assertEquals(shortcutFromFile, typicalShortcut);
    }

}
