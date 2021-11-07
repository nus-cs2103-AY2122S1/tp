package seedu.address.logic.commands;

import static java.util.Objects.requireNonNull;
import static seedu.address.logic.parser.CliSyntax.PREFIX_DASH_INDEX;
import static seedu.address.logic.parser.CliSyntax.PREFIX_DASH_NAME;

import javafx.collections.ObservableList;
import seedu.address.commons.core.Messages;
import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.model.Model;
import seedu.address.model.person.Person;
import seedu.address.model.person.predicates.NameContainsKeywordsPredicate;
import seedu.address.model.person.predicates.PersonContainsFieldsPredicate;
import seedu.address.model.person.predicates.StaffHasCorrectIndexPredicate;

/**
 * Finds and lists all persons in address book whose name contains any of the argument keywords.
 * Keyword matching is case-insensitive.
 */
public class FindCommand extends Command {

    public static final String COMMAND_WORD = "find";
    private static final int NAME_AND_FIELD_PREDICATE = -1;
    private static final int FIELD_PREDICATE_ONLY = -2;

    public static final String MESSAGE_USAGE = COMMAND_WORD + ": Finds all persons whose names contain any of "
            + "the specified keywords (case-insensitive) along with fields or the index specified and "
            + "displays them as a list with index numbers.\n\n"
            + "Parameters:\n"
            + PREFIX_DASH_INDEX + " INDEX or "
            + PREFIX_DASH_NAME + " KEYWORD [MORE_KEYWORDS]...\n\n"
            + "Examples:\n" + COMMAND_WORD + " "
            + PREFIX_DASH_INDEX + " 1\n"
            + COMMAND_WORD + " "
            + PREFIX_DASH_NAME + " alice bob charlie\n";

    public static final String INDEX_WITH_OTHER_INPUT = COMMAND_WORD + ": With index input, "
            + "Only the index is expected. No other field is needed.\n"
            + "Examples:\n"
            + COMMAND_WORD + " " + PREFIX_DASH_INDEX + " 2";
    public static final String NO_ONE_SATISFIES_QUERY = "Search conditions indicated is not"
        + " satisfied by anyone in staff'd";

    private final NameContainsKeywordsPredicate namePredicate;
    private final PersonContainsFieldsPredicate predicate;
    private final int index;
    private StringBuilder successMessage = new StringBuilder(Messages.MESSAGE_PERSONS_LISTED_OVERVIEW).append("\n");
    private StaffHasCorrectIndexPredicate indexPredicate = null;

    /**
     * Constructs a FindCommand object which searches by name
     *
     * @param namePredicate Predicate to filter the list by names that match a given name.
     */
    public FindCommand(NameContainsKeywordsPredicate namePredicate, PersonContainsFieldsPredicate predicate) {
        this.namePredicate = namePredicate;
        this.predicate = predicate;
        this.index = NAME_AND_FIELD_PREDICATE; // not used
    }

    /**
     * Constructs a FindCommand object which searches for the person at a specific index.
     *
     * @param index The index that the user searched for.
     */
    public FindCommand(int index, PersonContainsFieldsPredicate predicate) {
        assert index >= 0;
        this.namePredicate = NameContainsKeywordsPredicate.EMPTY;
        this.index = index;
        this.predicate = predicate;
    }

    /**
     * Constructs a FindCommand object which searches for the person with the specified fields.
     *
     * @param predicate The predicate to test if the person containes the specified fields.
     */
    public FindCommand(PersonContainsFieldsPredicate predicate) {
        assert predicate != null;
        assert !predicate.isEmpty();
        this.predicate = predicate;
        this.index = FIELD_PREDICATE_ONLY;
        this.namePredicate = NameContainsKeywordsPredicate.EMPTY;
    }

    @Override
    public CommandResult execute(Model model) throws CommandException {
        requireNonNull(model);
        //with name and field
        if (index == NAME_AND_FIELD_PREDICATE) {
            return executeNameAndFieldSearch(model);
        }
        //empty case cannot search
        if (index == FIELD_PREDICATE_ONLY) {
            return executeFieldSearch(model);
        }
        if (index >= 0) {
            checkIndex(model);
            indexPredicate = new StaffHasCorrectIndexPredicate(index, model);
            return executeIndexSearch(model);
        }
        throw new CommandException("Check if your input are correct: -n for name, -i for index,\n"
                + "and that the index given is correct!");


    }

    /**
     * Executes a search by name.
     *
     * @param model The model which contains the list to be searched on.
     * @return a CommandResult to be displayed.
     */
    private CommandResult executeNameAndFieldSearch(Model model) throws CommandException {
        model.updateFilteredPersonList(person -> namePredicate.test(person)
                && predicate.test(person));
        ObservableList<Person> staffs = model.getFilteredPersonList();
        int counter = 1;
        for (Person p : staffs) {
            successMessage.append(counter).append(". ").append(p.getName()).append("\n");
            counter++;
        }
        if (staffs.size() == 0) {
            throw new CommandException(NO_ONE_SATISFIES_QUERY);
        }
        return new CommandResult(
                String.format(successMessage.toString(), model.getFilteredPersonList().size()));
    }

    private CommandResult executeFieldSearch(Model model) throws CommandException {
        model.updateFilteredPersonList(person -> predicate.test(person));
        ObservableList<Person> staffs = model.getFilteredPersonList();
        int counter = 1;
        for (Person p : staffs) {
            successMessage.append(counter).append(". ").append(p.getName()).append("\n");
            counter++;
        }
        if (staffs.size() == 0) {
            throw new CommandException(NO_ONE_SATISFIES_QUERY);
        }
        return new CommandResult(
                String.format(successMessage.toString(), model.getFilteredPersonList().size()));
    }




    /**
     * Executes a search by index.
     *
     * @param model The model which contains the list to be searched on.
     * @return a CommandResult to be displayed.
     */
    private CommandResult executeIndexSearch(Model model) throws CommandException {
        model.updateFilteredPersonList(p -> indexPredicate.test(p) && predicate.test(p));
        ObservableList<Person> staffs = model.getFilteredPersonList();
        int counter = 1;
        for (Person p : staffs) {
            successMessage.append(counter).append(". ").append(p.getName()).append("\n");
            counter++;
        }
        if (staffs.size() == 0) {
            throw new CommandException(NO_ONE_SATISFIES_QUERY);
        }
        return new CommandResult(
                String.format(successMessage.toString(), model.getFilteredPersonList().size()));
    }

    /**
     * Checks if the index is within a suitable range for the list contained in the model.
     *
     * @param model The model which contains the list to be searched on.
     * @throws CommandException When the index inputted is not within range.
     */
    private void checkIndex(Model model) throws CommandException {
        int personListSize = model.getFilteredPersonList().size();
        if (index > personListSize - 1) { // -1 so that index starts from 0
            throw new CommandException(Messages.MESSAGE_INVALID_PERSON_DISPLAYED_INDEX);
        }
    }


    @Override
    public boolean equals(Object other) {
        if (other == null) {
            return false;
        }
        return other == this // short circuit if same object
                || (other instanceof FindCommand // instanceof handles nulls
                && ((FindCommand) other).namePredicate.equals(this.namePredicate)
                && ((FindCommand) other).index == index
                && predicate.equals(((FindCommand) other).predicate));
    }

}
