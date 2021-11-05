package seedu.address.commons.util;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.testutil.Assert.assertThrows;

import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;

import org.junit.jupiter.api.Test;

public class FileUtilTest {

    public static final Path EXISTING_FILE_PATH = Paths.get("src", "test", "data", "FileUtilTest",
        "sampleFile.txt");

    public static final Path NEW_FILE_PATH = Paths.get("src", "test", "data", "FileUtilTest",
        "noSuchFile.txt");

    public static final String CONTACT_TO_STRING =
        "\n-------------------------------------------------------\n"
        + "Your WhereTourGo (WTG) Contacts have been exported below:\n"
        + "-------------------------------------------------------\n\n"
        + "Name: Marina Bay Sands\n"
        + "Category: ATT\n"
        + "Phone: 66888868\n"
        + "Email: marinabaysands@example.com\n"
        + "Address: 10 Bayfront Ave, Singapore 018956\n"
        + "Review: amazing\n"
        + "Tags: [casino][popular]\n"
        + "Rating: 4\n"
        + "-------------------------------------------------------\n\n";

    @Test
    public void isValidPath_success() {
        // valid path
        assertTrue(FileUtil.isValidPath("valid/file/path"));

        // invalid path (wrong use of backslash)
        assertFalse(FileUtil.isValidPath("a\0"));

        // null path -> throws NullPointerException
        assertThrows(NullPointerException.class, () -> FileUtil.isValidPath(null));
    }

    @Test
    public void clearFile_success() throws IOException {

        // existing path
        FileUtil.clearFile(EXISTING_FILE_PATH);
        assertTrue(FileUtil.readFromFile(EXISTING_FILE_PATH).equals(""));

        // new path -> create new file
        FileUtil.clearFile(NEW_FILE_PATH);
        assertTrue(FileUtil.readFromFile(NEW_FILE_PATH).equals(""));

        // reset file that was created during test
        FileUtil.deleteFileIfExists(NEW_FILE_PATH);
    }

    @Test
    public void appendToFile_success() throws IOException {

        // existing path
        FileUtil.clearFile(EXISTING_FILE_PATH);
        FileUtil.appendToFile(EXISTING_FILE_PATH, CONTACT_TO_STRING);
        assertEquals(FileUtil.readFromFile(EXISTING_FILE_PATH), CONTACT_TO_STRING);

        // new path -> create new file
        FileUtil.clearFile(NEW_FILE_PATH);
        FileUtil.appendToFile(NEW_FILE_PATH, CONTACT_TO_STRING);
        assertEquals(FileUtil.readFromFile(NEW_FILE_PATH), CONTACT_TO_STRING);

        // reset/clear files that were created/written during test
        FileUtil.deleteFileIfExists(NEW_FILE_PATH);
        FileUtil.clearFile(EXISTING_FILE_PATH);
    }

}
