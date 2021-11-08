package seedu.address.commons.util;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.testutil.Assert.assertThrows;

import org.junit.jupiter.api.Test;

public class FileUtilTest {

    @Test
    public void isValidPath() {
        // valid path
        assertTrue(FileUtil.isValidPath("valid/file/path"));

        // invalid path
        assertFalse(FileUtil.isValidPath("a\0"));

        // null path -> throws NullPointerException
        assertThrows(NullPointerException.class, () -> FileUtil.isValidPath(null));
    }

    @Test
    public void isValidJsonFileName() {
        // valid JSON file name
        assertTrue(FileUtil.isValidJsonFileName("import.json"));

        // contains underscore
        assertTrue(FileUtil.isValidJsonFileName("import_underscore.json"));

        // contains dash
        assertTrue(FileUtil.isValidJsonFileName("import-dash.json"));

        // contains special characters
        assertFalse(FileUtil.isValidJsonFileName("*.json"));

        // contains special characters
        assertFalse(FileUtil.isValidJsonFileName("!@#$%^&*().json"));
    }

}
