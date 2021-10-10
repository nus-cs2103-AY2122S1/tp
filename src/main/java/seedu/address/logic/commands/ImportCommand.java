package seedu.address.logic.commands;

import static java.util.Objects.requireNonNull;

import seedu.address.model.Model;

public class ImportCommand extends Command {

    public static final String COMMAND_WORD = "import";

    public static final String MESSAGE_USAGE = COMMAND_WORD
            + ": Imports contacts from a specified JSON file.\n"
            + "Parameters: FILENAME\n"
            + "Example: " + COMMAND_WORD + " friends";

    public static final String MESSAGE_IMPORT_SUCCESS = "Imported from file: %s.json";

    public static final String MESSAGE_IMPORT_FAILURE = "File with name &s.json could not be found!";

    private final String testPath;
    private final String fileName;


    /**
     * Creates an ImportCommand to import contacts from a specified file.
     *
     * @param fileName Name of the JSON file.
     */
    public ImportCommand(String fileName) {
        requireNonNull(fileName);
        this.testPath = "";
        this.fileName = fileName;
    }

    /**
     * Creates an ImportCommand with a custom filePath for testing purposes.
     *
     * @param testPath Path to the folder where the JSON file will be exported to.
     * @param fileName Name of the JSON file.
     */
    public ImportCommand(String testPath, String fileName) {
        requireNonNull(fileName);
        this.testPath = testPath;
        this.fileName = fileName;
    }

    @Override
    public CommandResult execute(Model model) {
        return new CommandResult("Hello from import");
    }
}
