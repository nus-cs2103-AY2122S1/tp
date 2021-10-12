package seedu.address.ui;

import java.io.File;
import java.io.IOException;
import java.util.Arrays;
import javax.swing.JFileChooser;
import javax.swing.filechooser.FileNameExtensionFilter;

public class CsvFileChooser implements FileChooser {
    public static final String MESSAGE_WRONG_FILE_TYPE = "File selected is not a csv file";
    public static final String MESSAGE_FILE_NOT_SELECTED = "File was not selected";
    private String defaultDirectory;

    public CsvFileChooser(String... defaultDirectory) {
        this.defaultDirectory = String.join(File.separator, Arrays.asList(defaultDirectory));
    }

    @Override
    public File chooseFile() throws IOException {
        JFileChooser chooser = new JFileChooser(defaultDirectory);
        FileNameExtensionFilter filter = new FileNameExtensionFilter("Filter .csv files only", "csv");
        chooser.setFileFilter(filter);
        chooser.setFileSelectionMode(JFileChooser.FILES_ONLY);

        int response = chooser.showOpenDialog(null);

        if (response != JFileChooser.APPROVE_OPTION) {
            throw new IOException(MESSAGE_FILE_NOT_SELECTED);
        }

        File csvFile = chooser.getSelectedFile();

        if (!csvFile.getName().endsWith(".csv")) {
            throw new IOException(MESSAGE_WRONG_FILE_TYPE);
        }

        return csvFile;
    }

}
