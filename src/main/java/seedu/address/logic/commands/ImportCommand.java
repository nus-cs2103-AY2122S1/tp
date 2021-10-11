package seedu.address.logic.commands;

import seedu.address.commons.exceptions.DataConversionException;
import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.model.Model;

import java.nio.file.Path;

import static java.util.Objects.requireNonNull;
import static seedu.address.commons.util.CollectionUtil.requireAllNonNull;

public class ImportCommand extends Command {

    public static final String COMMAND_WORD = "import";

    public static final String MESSAGE_USAGE = COMMAND_WORD
            + ": Imports the json file identified into the ProfBook.";

    public static final String MESSAGE_NOT_IMPLEMENTED_YET = "Import command not implemented yet";

    public static final String MESSAGE_ARGUMENTS = "File Path: %1$s";

    public static final String MESSAGE_SUCCESS = "%1$s imported, duplicates ignored.";

    private final Path filePath;

    public ImportCommand(Path filePath){
        requireAllNonNull(filePath);
        this.filePath = filePath;
    }

    @Override
    public CommandResult execute(Model model) throws CommandException, DataConversionException {
        requireNonNull(model);
        model.importFile(filePath);
        return new CommandResult(String.format(MESSAGE_SUCCESS, filePath.toString()));
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
