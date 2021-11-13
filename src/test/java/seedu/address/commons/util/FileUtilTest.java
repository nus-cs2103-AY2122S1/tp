package seedu.address.commons.util;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.testutil.Assert.assertThrows;

import java.nio.file.Path;

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
    public void convertToAddressBookName() {
        // valid path
        assertEquals("name", FileUtil.convertToAddressBookName(Path.of("data\\name.json")));
        assertEquals("name", FileUtil.convertToAddressBookName(Path.of("data\\data\\data\\name.json")));

        // null path -> throws NullPointerException
        assertThrows(NullPointerException.class, () -> FileUtil.convertToAddressBookName(null));
    }

    @Test
    public void isJsonFile() {
        // path is json file
        assertTrue(FileUtil.isJsonFile(Path.of("data.json")));
        assertTrue(FileUtil.isJsonFile(Path.of("data\\data\\data\\name.json")));

        // path is not json file
        assertFalse(FileUtil.isJsonFile(Path.of("data.notjson")));

        // null path -> throws NullPointerException
        assertThrows(NullPointerException.class, () -> FileUtil.isJsonFile(null));
    }
}
