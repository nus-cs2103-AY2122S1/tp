package seedu.address.logic.commands;

import static java.util.Objects.requireNonNull;
import static seedu.address.commons.util.CollectionUtil.requireAllNonNull;

import java.nio.file.Path;

import seedu.address.commons.exceptions.DataConversionException;
import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.model.Model;

/**
 * Imports and Merges a Json file to address book
 */

public class ImportCommand extends Command {

    public static final String COMMAND_WORD = "import";

    public static final String MESSAGE_USAGE = COMMAND_WORD
            + ": Imports the json file identified into the ProfBook.\n"
            + "Parameters: FILEPATH\n"
            + "Example: import students.json";

    public static final String MESSAGE_FILEPATH_NOT_EXIST = "File path %1$s does not exist!";

    public static final String MESSAGE_SUCCESS = "%1$s imported, duplicates ignored.";

    public static final String MESSAGE_JSON_ERROR = "JSON file not following correct format. \n"
            + "Please refer to profbook.json for correct formatting";

    private final Path filePath;

    /**
     * Creates a Import Command to merge a json file with the address book
     */
    public ImportCommand(Path filePath) {
        requireAllNonNull(filePath);
        this.filePath = filePath;
    }

    @Override
    public CommandResult execute(Model model) throws CommandException, DataConversionException {
        requireNonNull(model);
        try {
            model.importFile(filePath);
            return new CommandResult(String.format(MESSAGE_SUCCESS, filePath.toString()));
        } catch (DataConversionException de) {
            throw new DataConversionException(MESSAGE_JSON_ERROR);
        }
    }

    @Override
    public boolean equals(Object other) {
        if (other == this) {
            return true;
        }

        if (!(other instanceof ImportCommand)) {
            return false;
        }

        ImportCommand e = (ImportCommand) other;
        return filePath.equals(e.filePath);
    }
}
