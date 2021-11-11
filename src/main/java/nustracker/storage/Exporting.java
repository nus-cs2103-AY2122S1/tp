package nustracker.storage;

import java.io.IOException;
import java.nio.file.Path;
import java.util.List;

import nustracker.commons.util.FileUtil;
import nustracker.logic.commands.exceptions.CommandException;
import nustracker.model.student.Email;

public class Exporting {

    /**
     * Exports a given email list to a given destination.
     *
     * @param pathToExport path to save the file in
     * @param listOfEmails list of emails to save
     * @throws CommandException command exception
     */
    public static void exportEmails(Path pathToExport, List<Email> listOfEmails) throws CommandException {
        try {
            FileUtil.createIfMissing(pathToExport);
            String stringToSave = listOfEmails.toString();
            stringToSave = stringToSave.substring(1, stringToSave.length() - 1);
            // Overwrites File all the time
            FileUtil.writeToFile(
                    pathToExport,
                    stringToSave);
        } catch (IOException ioe) {
            throw new CommandException("Could not export data properly: " + ioe, ioe);
        }
    }
}
