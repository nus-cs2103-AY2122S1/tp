package seedu.address.logic.commands.abcommand;

import static java.util.Objects.requireNonNull;
import static seedu.address.logic.commands.CommandResult.SpecialCommandResult.SWITCH_ADDRESSBOOK;

import java.nio.file.Path;

import seedu.address.logic.commands.CommandResult;
import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.model.Model;

public class AbSwitchCommand extends AbCommand {
    public static final String COMMAND_WORD = "switch";

    public static final String MESSAGE_USAGE = COMMAND_WORD + ": Switch between different address books\n"
            + "Parameters: switch {name of the addressbook}\n"
            + "Example: " + COMMAND_WORD + " addressbook2";

    public static final String MESSAGE_ADDRESSBOOK_NOT_FOUND = "Address Book with this name not found: %1$s";

    private final Path filePath;

    /**
     * @param filePath the filepath to the address book to switch to
     */
    public AbSwitchCommand(Path filePath) {
        this.filePath = filePath;
    }

    @Override
    public CommandResult execute(Model model) throws CommandException {
        requireNonNull(model);
        model.setAddressBookFilePath(filePath);

        return new CommandResult(String.format(MESSAGE_SWITCH_ADDRESSBOOK_SUCCESS, filePath), SWITCH_ADDRESSBOOK);
    }
}
