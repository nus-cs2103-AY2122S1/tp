package safeforhall.logic.commands;

import static java.util.Objects.requireNonNull;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import com.opencsv.CSVWriter;

import safeforhall.logic.commands.exceptions.CommandException;
import safeforhall.model.Model;
import safeforhall.model.person.Person;

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

    /**
     * Constructs an ExportCommand.
     *
     * @param filename filename of csv to be created.
     */
    public ExportCommand(String filename) {
        this.filename = "data/exports/" + filename + ".csv";
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
                throw new CommandException(MESSAGE_DUPLICATE_FILE_ERROR);
            } else {
                CSVWriter writer = new CSVWriter(new FileWriter(filename));
                writer.writeAll(content);
                writer.close();
            }
        } catch (IOException e) {
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
