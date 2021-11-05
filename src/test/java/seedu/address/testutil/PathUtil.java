package seedu.address.testutil;

import java.nio.file.Path;

public class PathUtil {
    /**
     * Prepends the {@code testDataFolder} to {@code fileInTestDataFolder}.
     * If {@code testDataFolder} is null, return null.
     * This method is used by storage tests to generate file paths for storing test json data.
     */
    public static Path addToPath(Path testDataFolder, String fileInTestDataFolder) {
        return fileInTestDataFolder != null
                ? testDataFolder.resolve(fileInTestDataFolder)
                : null;
    }
}
