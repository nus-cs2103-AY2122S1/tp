package seedu.address.logic.commands;

import static java.util.Objects.requireNonNull;
import static seedu.address.logic.parser.CliSyntax.PREFIX_SORT;

import seedu.address.model.Model;

/**
 * Changes the remark of an existing person in the address book.
 */
public class SortCommand extends Command {

    public static final String COMMAND_WORD = "sort";

    private String MESSAGE_SUCCESS = "Sorted all persons";

    public static final String MESSAGE_USAGE = COMMAND_WORD + ": Sorts the list of persons "
            + "by the alphabetical order of their name.\n"
            + "Parameters: [-r]\n"
            + "Example: " + COMMAND_WORD
            + PREFIX_SORT;

    private final boolean reverseOrder;

    public SortCommand(boolean reverseOrder) {
        this.reverseOrder = reverseOrder;
    }

    @Override
    public CommandResult execute(Model model) {
        if (reverseOrder) {
            MESSAGE_SUCCESS = MESSAGE_SUCCESS + " in reverse order";
        }
        requireNonNull(model);
        model.updateSortedPersonList(reverseOrder);
        return new CommandResult(MESSAGE_SUCCESS);
    }

}
