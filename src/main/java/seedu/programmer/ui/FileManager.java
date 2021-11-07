package seedu.programmer.ui;

import java.io.File;

import javafx.stage.DirectoryChooser;
import javafx.stage.FileChooser;
import javafx.stage.Stage;

public class FileManager {

    private static final String DEFAULT_DOWNLOAD_FILE_NAME = "programmerError.csv";
    private final Stage primaryStage;

    public FileManager(Stage primaryStage) {
        this.primaryStage = primaryStage;
    }

    /**
     * Creates a File object based on user's chosen directory.
     *
     * @return File object with a file name appended to the chosen directory
     */
    public File promptUserForFileDestination() {
        DirectoryChooser dirChooser = new DirectoryChooser();
        File chosenDir = dirChooser.showDialog(primaryStage);
        return chosenDir == null ? null : new File(chosenDir, DEFAULT_DOWNLOAD_FILE_NAME);
    }

    /**
     * Shows user a dialog to choose a CSV file.
     *
     * @return chosen CSV file
     */
    public File promptUserForCsvFile() {
        FileChooser fileChooser = new FileChooser();
        configureFileChooser(fileChooser);
        return fileChooser.showOpenDialog(primaryStage);
    }

    /**
     * Configures file chooser to accept only CSV files.
     *
     * @param fileChooser FileChooser object
     */
    private static void configureFileChooser(final FileChooser fileChooser) {
        fileChooser.setTitle("Select CSV file");
        FileChooser.ExtensionFilter csvFilter = new FileChooser.ExtensionFilter("All CSVs", "*.csv");
        fileChooser.getExtensionFilters().add(csvFilter);
    }
}
