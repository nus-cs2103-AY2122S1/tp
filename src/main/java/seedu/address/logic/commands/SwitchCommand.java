package seedu.address.logic.commands;

import static java.util.Objects.requireNonNull;
import static seedu.address.commons.core.Messages.MESSAGE_SWITCH_SUCCESS;


import java.nio.file.Path;

import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.model.Model;

public class SwitchCommand extends Command {
    public static final String COMMAND_WORD = "switch";

    public static final String MESSAGE_USAGE = COMMAND_WORD + ": Switch between different address books\n"
            + "Parameters: switch {name of the addressbook}\n"
            + "Example: " + COMMAND_WORD + " addressbook2";

    private final Path filePath;

    /**
     * @param filePath the filepath to the address book to switch to
     */
    public SwitchCommand(Path filePath) {
        this.filePath = filePath;
    }

    @Override
    public CommandResult execute(Model model) throws CommandException {
        requireNonNull(model);
        model.setAddressBookFilePath(filePath);

        return new CommandResult(String.format(MESSAGE_SWITCH_SUCCESS, filePath));
    }

    public Path getFilePath() {
        return this.filePath;
    }
}
