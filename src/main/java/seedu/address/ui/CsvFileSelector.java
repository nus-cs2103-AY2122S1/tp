package seedu.address.ui;

import static javafx.stage.FileChooser.ExtensionFilter;

import java.io.File;
import java.io.IOException;
import java.util.Arrays;

import javafx.stage.FileChooser;

public class CsvFileSelector implements FileSelector {
    public static final String MESSAGE_WRONG_FILE_TYPE = "File selected is not a csv file";
    public static final String MESSAGE_FILE_NOT_SELECTED = "File was not selected";
    private String defaultDirectory;

    public CsvFileSelector(String... defaultDirectory) {
        this.defaultDirectory = String.join(File.separator, Arrays.asList(defaultDirectory));
    }

    @Override
    public File selectFile() throws IOException {
        FileChooser chooser = new FileChooser();
        chooser.setTitle("Choose csv file to import");
        ExtensionFilter filter = new ExtensionFilter("Filter .csv files only", "csv");
        chooser.setSelectedExtensionFilter(filter);
        chooser.setInitialDirectory(new File(defaultDirectory));
        File csvFile = chooser.showOpenDialog(null);

        if (csvFile == null) {
            throw new IOException(MESSAGE_FILE_NOT_SELECTED);
        }

        if (!csvFile.getName().endsWith(".csv")) {
            throw new IOException(MESSAGE_WRONG_FILE_TYPE);
        }

        return csvFile;
    }
}
