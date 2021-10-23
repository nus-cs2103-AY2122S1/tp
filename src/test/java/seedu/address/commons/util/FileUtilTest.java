package seedu.address.commons.util;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.testutil.Assert.assertThrows;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

import org.junit.jupiter.api.Test;

public class FileUtilTest {

    public static final Path VALID_FILE_PATH = Paths.get("src", "test",
        "data", "FileUtilTest", "samplefile.txt");

    public static final Path INVALID_FILE_PATH = Paths.get("src", "test",
        "data", "FileUtilTest", "xxxsamplefile.txt");

    public static final String CONTACT_TO_STRING = "Marina Bay Sands; Category: ATT; "
        + "Phone: 66888868; Email: marinabaysands@example.com; "
        + "Address: 10 Bayfront Ave, Singapore 018956; Review: jrdhtkj; "
        + "Tags: [casino][popular]; Rating: 5";

    @Test
    public void isValidPath_success() {
        // valid path
        assertTrue(FileUtil.isValidPath("valid/file/path"));

        // invalid path
        assertFalse(FileUtil.isValidPath("a\0"));

        // null path -> throws NullPointerException
        assertThrows(NullPointerException.class, () -> FileUtil.isValidPath(null));
    }

    @Test
    public void clearFile_success() throws IOException {

        // valid path
        FileUtil.clearFile(VALID_FILE_PATH);
        assertTrue(FileUtil.readFromFile(VALID_FILE_PATH).equals(""));

        // invalid path -> create new file
        FileUtil.clearFile(INVALID_FILE_PATH);
        assertTrue(FileUtil.readFromFile(INVALID_FILE_PATH).equals(""));

        Files.deleteIfExists(INVALID_FILE_PATH);
    }

    @Test
    public void appendToFile_success() throws IOException {

        // valid append
        FileUtil.clearFile(VALID_FILE_PATH);
        FileUtil.appendToFile(VALID_FILE_PATH, CONTACT_TO_STRING);
        assertEquals(FileUtil.readFromFile(VALID_FILE_PATH), CONTACT_TO_STRING + "\n");

        // invalid path -> create new file
        FileUtil.clearFile(INVALID_FILE_PATH);
        FileUtil.appendToFile(INVALID_FILE_PATH, CONTACT_TO_STRING);
        assertEquals(FileUtil.readFromFile(INVALID_FILE_PATH), CONTACT_TO_STRING + "\n");

        Files.deleteIfExists(INVALID_FILE_PATH);
        FileUtil.clearFile(VALID_FILE_PATH);
    }

}
