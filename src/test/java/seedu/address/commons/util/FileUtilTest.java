package seedu.address.commons.util;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.commons.util.CsvWriterTest.EXPECTED_FILE_PATH_ALL_FIELDS;
import static seedu.address.logic.commands.ExportCommandIntegrationTest.EXPECTED_FILE_PATH_NAMES_ONLY;
import static seedu.address.testutil.Assert.assertThrows;

import java.io.File;
import java.io.FileInputStream;
import java.nio.file.Paths;

import org.junit.jupiter.api.Test;

public class FileUtilTest {

    public static final String TEST_DATA_DIRECTORY = "src"
            + File.separator
            + "test"
            + File.separator
            + "data"
            + File.separator
            + "FileUtilTest";

    public static final String EXISTING_DIRECTORY = TEST_DATA_DIRECTORY
            + File.separator
            + "ExistingDirectory";

    public static final String NEW_DIRECTORY = TEST_DATA_DIRECTORY
            + File.separator
            + "NewDirectory";

    public static final String EXISTING_FILE = EXISTING_DIRECTORY + File.separator + "ExistingFile.txt";
    public static final String NEW_FILE = NEW_DIRECTORY + File.separator + "NewFile.txt";
    public static final String COPIED_FILE = TEST_DATA_DIRECTORY + File.separator + "CopiedFile.txt";

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
    public void createDirectoryIfEmpty() throws Exception {
        // Directory already exists
        assertFalse(FileUtil.createDirectoryIfEmpty(Paths.get(EXISTING_DIRECTORY)));

        // Directory created
        assertTrue(FileUtil.createDirectoryIfEmpty(Paths.get(NEW_DIRECTORY)));

        // Cleanup
        FileUtil.deleteFile(Paths.get(NEW_DIRECTORY));
    }

    @Test
    public void createFile() throws Exception {
        // File already exists
        assertFalse(FileUtil.createFile(Paths.get(EXISTING_FILE)));

        // File created
        assertTrue(FileUtil.createFile(Paths.get(NEW_FILE)));

        // Cleanup
        FileUtil.deleteFile(Paths.get(NEW_FILE));
        FileUtil.deleteFile(Paths.get(NEW_DIRECTORY));
    }

    @Test
    public void copyFileIfMissing() throws Exception {
        // Destination file already exist
        assertFalse(FileUtil.copyFileIfMissing(new FileInputStream(new File(EXISTING_FILE)), Paths.get(EXISTING_FILE)));

        // File copied successfully
        assertTrue(FileUtil.copyFileIfMissing(new FileInputStream(new File(EXISTING_FILE)), Paths.get(COPIED_FILE)));

        // Cleanup
        FileUtil.deleteFile(Paths.get(COPIED_FILE));
    }

    @Test
    public void areFilesEqual() throws Exception {
        // Different files
        assertFalse(FileUtil.areFilesEqual(Paths.get(EXPECTED_FILE_PATH_NAMES_ONLY),
                Paths.get(EXPECTED_FILE_PATH_ALL_FIELDS)));

        // Same file
        assertTrue(FileUtil.areFilesEqual(Paths.get(EXPECTED_FILE_PATH_NAMES_ONLY),
                Paths.get(EXPECTED_FILE_PATH_NAMES_ONLY)));
    }

}
