package seedu.address.logic.commands;

import static java.util.Objects.requireNonNull;

import seedu.address.commons.core.Messages;
import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.logic.parser.CliSyntax;
import seedu.address.model.Model;
import seedu.address.model.person.predicates.NameContainsKeywordsPredicate;
import seedu.address.model.person.predicates.StaffHasCorrectIndexPredicate;

/**
 * Finds and lists all persons in address book whose name contains any of the argument keywords.
 * Keyword matching is case-insensitive.
 */
public class FindCommand extends Command {

    public static final String COMMAND_WORD = "find";
    public static final int INVALID_INDEX = -1;

    public static final String MESSAGE_USAGE = COMMAND_WORD + ": Finds all persons whose names contain any of "
            + "the specified keywords (case-insensitive) or the index specified and "
            + "displays them as a list with index numbers.\n"
            + "Name Search Parameters: " + CliSyntax.PREFIX_DASH_NAME.getPrefix() + " KEYWORD [MORE_KEYWORDS]...\n"
            + "Example: " + COMMAND_WORD + " alice bob charlie\n"
            + "Index Search Parameters: " + CliSyntax.PREFIX_DASH_INDEX + " INDEX\n"
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
        this.index = INVALID_INDEX; // not used
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
            checkIndex(model); // throws an exception if index is out of range
            indexPredicate = new StaffHasCorrectIndexPredicate(index, model);
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
        int personListSize = model.getFilteredPersonList().size();
        if (index > personListSize - 1) { // -1 so that index starts from 0
            throw new CommandException(Messages.MESSAGE_INVALID_PERSON_DISPLAYED_INDEX);
        }
    }

    /**
     * Returns the index of the FindCommand object.
     *
     * @return index
     */
    public int getIndex() {
        return this.index;
    }

    /**
     * Returns the namePredicate of the FindCommand object.
     *
     * @return namePredicate
     */
    public NameContainsKeywordsPredicate getNamePredicate() {
        return this.namePredicate;
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof FindCommand // instanceof handles nulls
                && ((findByNameIsEquals((FindCommand) other)) || findByIndexIsEquals((FindCommand) other)));
    }

    /**
     * Checks if another FindCommand object which searches by name is equal to the current FindCommand object.
     *
     * @param otherFind The other FindCommand object to be checked
     * @return Whether the otherFind is equal to this
     */
    public boolean findByNameIsEquals(FindCommand otherFind) {
        return (otherFind.getIndex() == INVALID_INDEX && this.index == INVALID_INDEX)
                && (otherFind.getNamePredicate() != null && this.namePredicate != null)
                && (this.namePredicate.equals(otherFind.getNamePredicate()));
    }

    /**
     * Checks if another FindCommand object which searches by index is equal to the current FindCommand object.
     *
     * @param otherFind The other FindCommand object to be checked
     * @return Whether the otherFind is equal to this
     */
    public boolean findByIndexIsEquals(FindCommand otherFind) {
        return (otherFind.namePredicate == null && this.namePredicate == null)
                && (otherFind.getIndex() != INVALID_INDEX && this.index != INVALID_INDEX)
                && (this.index == otherFind.getIndex());
    }
}
