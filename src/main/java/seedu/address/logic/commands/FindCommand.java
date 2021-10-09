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
            + "Name Search Parameters: " + COMMAND_TAG_NAME + " KEYWORD [MORE_KEYWORDS]...\n"
            + "Example: " + COMMAND_WORD + " alice bob charlie\n"
            + "Index Search Parameters: " + COMMAND_TAG_INDEX + " INDEX\n"
            + "Example: " + COMMAND_WORD + " 1";

    private final NameContainsKeywordsPredicate namePredicate;
    private final int index;
    private StaffHasCorrectIndexPredicate indexPredicate = null;

    /**
     * Constructs a FindCommand object which searches by name
     *
     * @param namePredicate Predicate to filter the list by names that match a given name.
     */
    public FindCommand(NameContainsKeywordsPredicate namePredicate) {
        this.namePredicate = namePredicate;
        this.index = -1; // not used
    }

    /**
     * Constructs a FindCommand object which searches for the person at a specific index.
     *
     * @param index The index that the user searched for.
     */
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
            checkIndex(model);
            return executeIndexSearch(model);

        } else {
            throw new CommandException("Check if your input are correct: -n for name, -i for index,\n"
                    + "and that the index given is correct!");
        }
    }

    /**
     * Executes a search by name.
     *
     * @param model The model which contains the list to be searched on.
     * @return a CommandResult to be displayed.
     */
    public CommandResult executeNameSearch(Model model) {
        model.updateFilteredPersonList(namePredicate);
        return new CommandResult(
                String.format(Messages.MESSAGE_PERSONS_LISTED_OVERVIEW, model.getFilteredPersonList().size()));
    }

    /**
     * Executes a search by index.
     *
     * @param model The model which contains the list to be searched on.
     * @return a CommandResult to be displayed.
     */
    public CommandResult executeIndexSearch(Model model) {
        model.updateFilteredPersonList(indexPredicate);
        return new CommandResult(
                String.format(Messages.MESSAGE_PERSONS_LISTED_OVERVIEW, model.getFilteredPersonList().size()));
    }

    /**
     * Checks if the index is within a suitable range for the list contained in the model.
     *
     * @param model The model which contains the list to be searched on.
     * @throws CommandException When the index inputted is not within range.
     */
    public void checkIndex(Model model) throws CommandException {
        int personListSize = model.getAddressBook().getPersonList().size();
        if (index > personListSize) {
            throw new CommandException(String.format(
                    "The index you are trying to access is out of bounds!\n" + "Please input an index from %d to %d",
                    model.getFilteredPersonList().isEmpty() ? 0 : 1, personListSize));
        }
        indexPredicate = new StaffHasCorrectIndexPredicate(index, model);
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof FindCommand // instanceof handles nulls
                && (namePredicate == null || namePredicate.equals(((FindCommand) other).namePredicate))
                && (indexPredicate == null || indexPredicate.equals(((FindCommand) other).indexPredicate)));
    }
}
