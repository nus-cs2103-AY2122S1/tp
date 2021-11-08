package safeforhall.logic.commands;

import static java.util.Objects.requireNonNull;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Logger;

import com.opencsv.CSVWriter;

import safeforhall.commons.core.LogsCenter;
import safeforhall.logic.commands.exceptions.CommandException;
import safeforhall.model.Model;
import safeforhall.model.person.Person;

/**
 * Exports a list of email addresses of the last filtered list of residents as csv file
 */
public class ExportCommand extends Command {

    public static final String COMMAND_WORD = "export";
    public static final String PARAMETERS = "FILE_NAME";
    public static final String MESSAGE_USAGE = COMMAND_WORD + ": Exports the current list of residents'"
            + "email addresses into a csv file. \n"
            + "Parameters: " + PARAMETERS + "\n"
            + "Example: " + COMMAND_WORD + "safeforhall";

    public static final String MESSAGE_SUCCESS = "Exported residents' email to csv file";
    public static final String MESSAGE_DUPLICATE_FILE_ERROR = "This filename already exists";
    public static final String MESSAGE_CONSTRAINTS = "Filename should be a single word";

    private final String filename;
    private final Logger logger = LogsCenter.getLogger(ExportCommand.class);

    /**
     * Constructs an ExportCommand.
     *
     * @param filename filename of csv to be created.
     */
    public ExportCommand(String filename) {
        this.filename = "data/exports/" + filename + ".csv";
    }

    /**
     * Constructs an ExportCommand for testing.
     *
     * @param filename filename of csv to be created.
     */
    public ExportCommand(String filepath, String filename) {
        this.filename = filepath + filename + ".csv";
    }

    /**
     * Executes the command and returns the result message.
     *
     * @param model {@code Model} which the command should operate on.
     * @return feedback message of the operation result for display
     * @throws CommandException If an error occurs during command execution.
     */
    @Override
    public CommandResult execute(Model model) throws CommandException {
        requireNonNull(model);
        List<Person> filteredPersonList = model.getFilteredPersonList();
        ArrayList<String[]> emailArr = getEmailArr(filteredPersonList);
        writeCsv(emailArr);
        return new CommandResult(MESSAGE_SUCCESS);
    }

    /**
     * Converts list of persons into arraylist of emails
     * @param filteredList the list of persons
     * @return arraylist of arrays each containing a email string
     */
    public ArrayList<String[]> getEmailArr(List<Person> filteredList) {
        ArrayList<String[]> emailArray = new ArrayList<>();
        for (Person p : filteredList) {
            String[] email = new String[]{p.getEmail().toString()};
            emailArray.add(email);
        }
        return emailArray;
    }

    /**
     * Writes information to a specified csv file
     * @param content information to be written to csv file
     * @throws CommandException If an error occurs during command execution.
     */
    public void writeCsv(ArrayList<String[]> content) throws CommandException {
        try {
            File f = new File(filename);
            if (f.exists() && !f.isDirectory()) {
                logger.warning("File already exists");
                throw new CommandException(MESSAGE_DUPLICATE_FILE_ERROR);
            } else {
                CSVWriter writer = new CSVWriter(new FileWriter(filename));
                writer.writeAll(content);
                writer.close();
            }
        } catch (IOException e) {
            logger.warning("Issue with file creation");
            throw new CommandException(e.getMessage());
        }
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof ExportCommand // instanceof handles nulls
                && this.filename.equals(((ExportCommand) other).filename)); // state check
    }

}
