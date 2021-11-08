package seedu.address.storage;

import java.io.IOException;
import java.nio.file.Path;
import java.util.logging.Logger;

import seedu.address.commons.core.LogsCenter;
import seedu.address.commons.util.FileUtil;

public class ExportStorage {

    private static ExportStorage current;
    private static final Logger logger = LogsCenter.getLogger(ExportStorage.class);
    private static final String EXPORT_FILE_HEADER =
        "\n-------------------------------------------------------\n"
        + "Your WhereTourGo (WTG) Contacts have been exported below:"
        + "\n-------------------------------------------------------\n";
    private static final String MESSAGE_EXPORT_FILE_MODIFY_ERROR = "Could not modify export file.";

    private final Path exportFilePath;

    /**
     * Initialises a new {@code ExportStorage} object, and creates the export file.
     * @param exportFilePath file path specified by the user
     */
    public ExportStorage(Path exportFilePath) {
        this.exportFilePath = exportFilePath;
        current = this;
        initExportStorage();
    }

    private static void initExportStorage() {
        // clear the file, make directory
        FileUtil.createIfMissing(current.exportFilePath);
    }

    /**
     * Appends a {@code contact} to export file.
     * @param contactString of {@code contact} to be added
     */
    public static void addToStorage(String contactString) {
        assert FileUtil.isFileExists(current.exportFilePath);
        try {
            FileUtil.appendToFile(current.exportFilePath, contactString);
        } catch (IOException ignored) {
            logger.warning(MESSAGE_EXPORT_FILE_MODIFY_ERROR);
        }
    }

    /**
     * Clears the export file of all previous data.
     */
    public static void clearStorage() {
        try {
            FileUtil.clearFile(current.exportFilePath);
            FileUtil.appendToFile(current.exportFilePath, EXPORT_FILE_HEADER);
        } catch (IOException ignored) {
            logger.warning(MESSAGE_EXPORT_FILE_MODIFY_ERROR);
        }
    }

}
