package seedu.address.logic.commands;

import static java.util.Objects.requireNonNull;
import static seedu.address.logic.parser.CliSyntax.PREFIX_ASC;
import static seedu.address.logic.parser.CliSyntax.PREFIX_CREDIT;
import static seedu.address.logic.parser.CliSyntax.PREFIX_DESC;
import static seedu.address.logic.parser.CliSyntax.PREFIX_MEMBER;

import seedu.address.commons.status.SortStatus;
import seedu.address.model.Model;
import seedu.address.model.member.CreditSortComparator;

/**
 * Sorts and lists all members in ezFoodie by credit.
 */
public class SortCommand extends Command {

    /**
     * Stands for sort command.
     */
    public static final String COMMAND_WORD = "sort";

    /**
     * Stands for the message of Sort command.
     */
    public static final String MESSAGE_USAGE = COMMAND_WORD
            + ": Sorts all members by credit in ascending or descending "
            + "and displays them as a list with index numbers.\n"
            + "Parameters:\n"
            + "Sort credit in ascending: " + PREFIX_MEMBER + " " + PREFIX_CREDIT + " " + PREFIX_ASC + "\n"
            + "Sort credit in descending: " + PREFIX_MEMBER + " " + PREFIX_CREDIT + " " + PREFIX_DESC + "\n"
            + "Example:\n"
            + "Sort credit in ascending: "
            + COMMAND_WORD + " " + PREFIX_MEMBER + " " + PREFIX_CREDIT + " " + PREFIX_ASC + "\n"
            + "Sort credit in descending: "
            + COMMAND_WORD + " " + PREFIX_MEMBER + " " + PREFIX_CREDIT + " " + PREFIX_DESC;

    /**
     * Stands for the message of sorted in ascending.
     */
    public static final String MESSAGE_SORT_ASC = "Members sorted by credit in ascending!";

    /**
     * Stands for the message of sorted in descending.
     */
    public static final String MESSAGE_SORT_DESC = "Members sorted by credit in descending!";

    private final CreditSortComparator comparator;

    /**
     * Constructs SortCommand by {@code comparator}.
     */
    public SortCommand(CreditSortComparator comparator) {
        this.comparator = comparator;
    }

    /**
     * Overrides and executes the model.
     *
     * @param model {@code Model} which the command should operate on.
     * @return CommandResult related to sort command.
     */
    @Override
    public CommandResult execute(Model model) {
        requireNonNull(model);
        model.updateSortedMemberList(comparator);
        if (comparator.getSortStatus() == SortStatus.DESC) {
            return new CommandResult(MESSAGE_SORT_DESC);
        } else {
            return new CommandResult(MESSAGE_SORT_ASC);
        }
    }

    /**
     * Overrides the equals method.
     */
    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof SortCommand // instanceof handles nulls
                && comparator.equals(((SortCommand) other).comparator)); // state check
    }
}
