package seedu.address.ui;

import java.io.File;
import javax.swing.JFileChooser;
import javax.swing.filechooser.FileNameExtensionFilter;

import seedu.address.logic.commands.exceptions.CommandException;




public class CsvFileChooser implements FileChooser {
    public static final String MESSAGE_WRONG_FILE_TYPE = "File selected is not a csv file";
    public static final String MESSAGE_FILE_NOT_SELECTED = "File was not selected";

    @Override
    public File chooseFile(String defaultDirectory) throws CommandException {
        JFileChooser chooser = new JFileChooser(defaultDirectory);
        FileNameExtensionFilter filter = new FileNameExtensionFilter("Filter .csv files only", "csv");
        chooser.setFileFilter(filter);
        chooser.setFileSelectionMode(JFileChooser.FILES_ONLY);

        int response = chooser.showOpenDialog(null);

        if (response != JFileChooser.APPROVE_OPTION) {
            throw new CommandException(MESSAGE_FILE_NOT_SELECTED);
        }

        File csvFile = chooser.getSelectedFile();

        if (!csvFile.getName().endsWith(".csv")) {
            throw new CommandException(MESSAGE_WRONG_FILE_TYPE);
        }

        return csvFile;
    }

}
