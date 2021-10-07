package seedu.address.logic.commands;

import static java.util.Objects.requireNonNull;

import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.logic.parser.SortCommandParser;
import seedu.address.model.Model;

/**
 * Sorts the contacts in the address book.
 */
public class SortCommand extends Command {



    public static final String MESSAGE_SUCCESS = "Address book sorted.";
    public static final String MESSAGE_USAGE = "sort: Sorts the contact in the address book in alphabetical order.\n"
            + "Example: sort -a";

    private SortCommandParser.SortableField field;

    public SortCommand(SortCommandParser.SortableField field) {
        this.field = field;
    }

    @Override
    public CommandResult execute(Model model) throws CommandException {
        requireNonNull(model);
        model.sortAddressBook(field);
        return new CommandResult(MESSAGE_SUCCESS);
    }

    @Override
    public boolean equals(Object other) {
        return other == this || (other instanceof SortCommand);
    }
}
