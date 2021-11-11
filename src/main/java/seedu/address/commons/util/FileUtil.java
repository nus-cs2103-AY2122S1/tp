package seedu.address.commons.util;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.InvalidPathException;
import java.nio.file.Path;
import java.nio.file.Paths;

/**
 * Utility class for file operations
 */
public class FileUtil {

    private static final String CHARSET = "UTF-8";

    public static boolean isFileExists(Path file) {
        return Files.exists(file) && Files.isRegularFile(file);
    }

    /**
     * Returns true if {@code path} can be converted into a {@code Path} via {@link Paths#get(String)},
     * otherwise returns false.
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
     * @throws IOException if the file or directory cannot be created.
     */
    public static void createIfMissing(Path file) throws IOException {
        if (!isFileExists(file)) {
            createFile(file);
        }
    }

    /**
     * Creates a file if it does not exist along with its missing parent directories.
     */
    public static boolean createFile(Path file) throws IOException {
        if (Files.exists(file)) {
            return false;
        }

        createParentDirsOfFile(file);
        Files.createFile(file);
        return true;
    }

    /**
     * Creates a directory if it does not exist.
     */
    public static boolean createDirectoryIfEmpty(Path directory) throws IOException {
        if (Files.exists(directory)) {
            return false;
        }

        Files.createDirectory(directory);
        return true;
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
     * Will create the file if it does not exist yet.
     */
    public static void writeToFile(Path file, String content) throws IOException {
        Files.write(file, content.getBytes(CHARSET));
    }

    /**
     * Checks if two files have the same content.
     */
    public static boolean areFilesEqual(Path testedFile, Path expectedFile) throws IOException {
        BufferedReader bf1 = Files.newBufferedReader(testedFile);
        BufferedReader bf2 = Files.newBufferedReader(expectedFile);

        String line1;
        String line2;

        while ((line1 = bf1.readLine()) != null) {
            line2 = bf2.readLine();
            if (!line1.equals(line2)) {
                return false;
            }
        }

        return bf2.readLine() == null;
    }

    /**
     * Deletes the file specified in the filepath, regardless if it exists or not.
     */
    public static void deleteFile(Path filePath) {
        filePath.toFile().delete();
    }

    /**
     * Copies the source to the target if the target is missing.
     */
    public static boolean copyFileIfMissing(InputStream src, Path target) throws IOException {
        if (isFileExists(target)) {
            return false;
        }
        Files.copy(src, target);
        return true;
    }
}
