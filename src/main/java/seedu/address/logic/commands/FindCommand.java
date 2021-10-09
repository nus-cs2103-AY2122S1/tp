package seedu.address.logic.commands;

import static java.util.Objects.requireNonNull;

import seedu.address.commons.core.Messages;
import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.model.Model;
import seedu.address.model.person.predicates.NameContainsKeywordsPredicate;
import seedu.address.model.person.predicates.StaffHasCorrectIndexPredicate;

/**
 * Finds and lists all persons in address book whose name contains any of the argument keywords.
 * Keyword matching is case-insensitive.
 */
public class FindCommand extends Command {

    public static final String COMMAND_WORD = "find";
    public static final String COMMAND_TAG_INDEX = "-i";
    public static final String COMMAND_TAG_NAME = "-n";

    public static final String MESSAGE_USAGE = COMMAND_WORD + ": Finds all persons whose names contain any of "
            + "the specified keywords (case-insensitive) or the index specified and "
            + "displays them as a list with index numbers.\n"
            + "Name Search Parameters: -n KEYWORD [MORE_KEYWORDS]...\n"
            + "Example: " + COMMAND_WORD + " alice bob charlie\n"
            + "Index Search Parameters: -i INDEX\n"
            + "Example: " + COMMAND_WORD + " 1";

    private final NameContainsKeywordsPredicate namePredicate;
    private final int index;
    private StaffHasCorrectIndexPredicate indexPredicate = null;

    public FindCommand(NameContainsKeywordsPredicate namePredicate) {
        this.namePredicate = namePredicate;
        this.index = -1; // not used
    }

    public FindCommand(int index) {
        this.namePredicate = null;
        this.index = index;
    }

    @Override
    public CommandResult execute(Model model) throws CommandException {
        requireNonNull(model);

        if (namePredicate != null) {
            return executeNameSearch(model);

        } else if (index > -1) {
            indexPredicate = new StaffHasCorrectIndexPredicate(index, model);
            return executeIndexSearch(model);

        } else {
            throw new CommandException("Check if your input are correct: -n for name, -i for index, and check that " +
                    "your index stated is in range!");
        }
    }

    public CommandResult executeNameSearch(Model model) {
        model.updateFilteredPersonList(namePredicate);
        return new CommandResult(
                String.format(Messages.MESSAGE_PERSONS_LISTED_OVERVIEW, model.getFilteredPersonList().size()));
    }

    public CommandResult executeIndexSearch(Model model) {
        model.updateFilteredPersonList(indexPredicate);
        return new CommandResult(
                String.format(Messages.MESSAGE_PERSONS_LISTED_OVERVIEW, model.getFilteredPersonList().size()));
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof FindCommand // instanceof handles nulls
                && (namePredicate == null || namePredicate.equals(((FindCommand) other).namePredicate))
                && (indexPredicate == null || indexPredicate.equals(((FindCommand) other).indexPredicate)));
    }
}
