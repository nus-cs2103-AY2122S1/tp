package seedu.address.commons.util;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.io.Writer;
import java.nio.file.Files;
import java.nio.file.InvalidPathException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.logging.Logger;

import seedu.address.commons.core.LogsCenter;

/**
 * Writes and reads files
 */
public class FileUtil {

    private static final Logger logger = LogsCenter.getLogger(FileUtil.class);

    private static final String CHARSET = "UTF-8";

    public static boolean isFileExists(Path file) {
        return Files.exists(file) && Files.isRegularFile(file);
    }

    /**
     * Returns true if {@code path} can be converted into a {@code Path},
     * otherwise returns false.
     * @param path A string representing the file path. Cannot be null.
     */
    public static boolean isValidPath(String path) throws InvalidPathException {
        try {
            Paths.get(path);
        } catch (InvalidPathException ipe) {
            return false;
        }
        return true;
    }

    /**
     * Creates a file if it does not exist along with its missing parent directories.
     * @throws IOException if the file or directory cannot be created.
     */
    public static void createIfMissing(Path file) {
        try {
            if (!isFileExists(file)) {
                logger.info("No such file exists, creating new one");
                createFile(file);
            }
        } catch (IOException ignored) {
            logger.warning("Unable to create file");
        }

    }

    /**
     * Creates a file if it does not exist along with its missing parent directories.
     * @throws IOException if the file or directory cannot be created.
     */
    public static void createFile(Path file) throws IOException {
        assert !Files.exists(file);
        createParentDirsOfFile(file);
        Files.createFile(file);
        logger.info("New file created: " + file);
    }

    /**
     * Creates parent directories of file if it has a parent directory
     */
    public static void createParentDirsOfFile(Path file) throws IOException {
        Path parentDir = file.getParent();

        if (parentDir != null) {
            Files.createDirectories(parentDir);
        }
    }

    /**
     * Assumes file exists
     */
    public static String readFromFile(Path file) throws IOException {
        return new String(Files.readAllBytes(file), CHARSET);
    }

    /**
     * Writes given string to a file.
     */
    public static void writeToFile(Path file, String content) throws IOException {
        logger.info("Writing to file: " + file);
        Files.write(file, content.getBytes(CHARSET));
    }

    /**
     * Appends given string to a file.
     */
    public static void appendToFile(Path file, String content) throws IOException {
        Writer output = new BufferedWriter(new FileWriter(file.toString(), true));
        logger.info("Appending to file: " + file);
        output.append(content);
        output.close();
    }

    /**
     * Clears given file.
     */
    public static void clearFile(Path file) throws IOException {
        writeToFile(file, "");
    }

    /**
     * Deletes given file if it exists.
     */
    public static void deleteFileIfExists(Path file) throws IOException {
        Files.deleteIfExists(file);
    }

}
