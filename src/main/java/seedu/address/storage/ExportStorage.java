package seedu.address.storage;

import java.io.IOException;
import java.nio.file.Path;

import seedu.address.commons.util.FileUtil;

public class ExportStorage {

    private static ExportStorage current;

    private Path exportFilePath;

    /**
     * Initialises a new {@code ExportStorage} object, and creates the export file.
     * @param exportFilePath file path specified by the user
     * @throws IOException if file cannot be created
     */
    public ExportStorage(Path exportFilePath) throws IOException {
        this.exportFilePath = exportFilePath;
        current = this;
        initExportStorage();
    }

    private static void initExportStorage() throws IOException {
        // clear the file, make directory
        FileUtil.createIfMissing(current.exportFilePath);
    }

    /**
     * Appends a {@code contact} to export file.
     * @param personString of {@code contact} to be added
     */
    public static void addToStorage(String personString) {
        try {
            FileUtil.appendToFile(current.exportFilePath, personString);
        } catch (IOException ignored) {
            System.out.println("");
        }

    }

    /**
     * Clears the export file of all previous data.
     */
    public static void clearStorage() {
        try {
            FileUtil.clearFile(current.exportFilePath);
        } catch (IOException ignored) {
            System.out.println("");
        }
    }

}
