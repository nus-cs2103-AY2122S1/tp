package seedu.address.ui;

import static javafx.stage.FileChooser.ExtensionFilter;

import java.io.File;
import java.util.Arrays;

import javafx.stage.FileChooser;

import seedu.address.ui.exceptions.FileSelectorException;

public class CsvFileSelector implements FileSelector {
    public static final String MESSAGE_FILE_NOT_SELECTED = "File was not selected";
    private final String defaultDirectory;

    public CsvFileSelector(String... defaultDirectory) {
        this.defaultDirectory = String.join(File.separator, Arrays.asList(defaultDirectory));
    }

    @Override
    public File selectFile() throws FileSelectorException {
        FileChooser chooser = new FileChooser();
        chooser.setTitle("Choose csv file to import");
        ExtensionFilter filter = new ExtensionFilter("CSV Files", "*.csv");
        chooser.setInitialDirectory(new File(defaultDirectory));
        chooser.getExtensionFilters().add(filter);
        chooser.setSelectedExtensionFilter(filter);
        File csvFile = chooser.showOpenDialog(null);

        if (csvFile == null) {
            throw new FileSelectorException(MESSAGE_FILE_NOT_SELECTED);
        }

        return csvFile;
    }
}
