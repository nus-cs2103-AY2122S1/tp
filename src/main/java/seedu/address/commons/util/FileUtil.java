package seedu.address.commons.util;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.InvalidPathException;
import java.nio.file.Path;
import java.nio.file.Paths;

/**
 * Writes and reads files
 */
public class FileUtil {

    private static final String CHARSET = "UTF-8";

    /**
     * Returns true if the provided file path exists.
     *
     * @param file Path of the file provided.
     * @return boolean representation of whether the provided
     * file path actually exists.
     */
    public static boolean isFileExists(Path file) {
        return Files.exists(file) && Files.isRegularFile(file);
    }

    /**
     * Returns true if {@code path} can be converted into a {@code Path} via {@link Paths#get(String)},
     * otherwise returns false.
     *
     * @param path A string representing the file path. Cannot be null.
     */
    public static boolean isValidPath(String path) {
        try {
            Paths.get(path);
        } catch (InvalidPathException ipe) {
            return false;
        }
        return true;
    }

    /**
     * Creates a file if it does not exist along with its missing parent directories.
     *
     * @param file Path of the file.
     * @throws IOException if the file or directory cannot be created.
     */
    public static void createIfMissing(Path file) throws IOException {
        if (!isFileExists(file)) {
            createFile(file);
        }
    }

    /**
     * Creates a file if it does not exist along with its missing parent directories.
     *
     * @param file Path of the file.
     * @throws IOException if the file or parent directory cannot be created.
     */
    public static void createFile(Path file) throws IOException {
        if (Files.exists(file)) {
            return;
        }

        createParentDirsOfFile(file);

        Files.createFile(file);
    }

    /**
     * Creates parent directories of file if it has a parent directory
     *
     * @param file Path of the file.
     * @throws IOException if the parent directory cannot be created.
     */
    public static void createParentDirsOfFile(Path file) throws IOException {
        Path parentDir = file.getParent();

        if (parentDir != null) {
            Files.createDirectories(parentDir);
        }
    }

    /**
     * Assumes file exists in the argument path.
     *
     * @param file Path of the file to be read from.
     * @throws IOException if unable to read from file.
     */
    public static String readFromFile(Path file) throws IOException {
        return new String(Files.readAllBytes(file), CHARSET);
    }

    /**
     * Writes given string to a file.
     * Will create the file if it does not exist yet.
     *
     * @param file Path of the file to be written to.
     * @param content String representation of content to be written to file.
     * @throws IOException if unable to write to file.
     */
    public static void writeToFile(Path file, String content) throws IOException {
        Files.write(file, content.getBytes(CHARSET));
    }

    /**
     * Deletes a file if it exists.
     *
     * @param file Path of file to be deleted.
     * @throws IOException If there was an error deleting the file.
     */
    public static void deleteFileIfExists(Path file) throws IOException {
        if (isFileExists(file)) {
            deleteFile(file);
        }
    }

    /**
     * Deletes a file.
     *
     * @param file Path of the file to be deleted.
     * @throws IOException If there was an error deleting the file.
     */
    public static void deleteFile(Path file) throws IOException {
        if (!Files.exists(file)) {
            return;
        }

        Files.delete(file);
    }
}
