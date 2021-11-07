package seedu.address.logic.commands;

import static java.util.Objects.requireNonNull;

import seedu.address.model.Model;

/**
 * Changes the remark of an existing person in the address book.
 */
public class SortCommand extends Command {

    public static final String COMMAND_WORD = "sort";

    public static final String MESSAGE_USAGE = COMMAND_WORD + ": Sorts the list of persons "
            + "by the alphabetical order of their name.\n"
            + "Parameters: [-r]\n"
            + "Example: " + COMMAND_WORD
            + " -r";

    public static final String DESCRIPTION = "Sorts the list of persons";

    private static String messageSuccess = "Sorted all persons";

    private final boolean isReverseOrder;

    /**
     * Constructor for a SortCommand to sort the list of persons.
     *
     * @param isReverseOrder The boolean that determines the sorted order of the list of persons.
     */
    public SortCommand(boolean isReverseOrder) {
        this.isReverseOrder = isReverseOrder;
    }

    @Override
    public CommandResult execute(Model model) {
        if (isReverseOrder) {
            messageSuccess = messageSuccess + " in reverse order";
        }
        requireNonNull(model);
        model.updateSortedPersonList(isReverseOrder);
        return new CommandResult(messageSuccess);
    }

    @Override
    public boolean equals(Object other) {
        return other == this
                || (other instanceof SortCommand && isReverseOrder == ((SortCommand) other).isReverseOrder);
    }

    public String getCommand() {
        return COMMAND_WORD;
    }

    public String getDescription() {
        return DESCRIPTION;
    }
}
