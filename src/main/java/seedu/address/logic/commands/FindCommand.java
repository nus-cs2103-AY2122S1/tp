package seedu.address.logic.commands;

import static java.util.Objects.requireNonNull;

import java.util.function.Predicate;

import seedu.address.model.Model;
import seedu.address.model.person.Person;

/**
 * Finds and lists all persons in address book whose name contains any of the argument keywords.
 * Keyword matching is case insensitive.
 */
public class FindCommand extends Command {

    public static final String COMMAND_WORD = "find";

    public static final String MESSAGE_USAGE = COMMAND_WORD + ": Finds all persons whose names contain any of "
            + "the specified keywords (case-insensitive) or tags and displays them as a list with index numbers.\n"
            + "Parameters: KEYWORD [MORE_KEYWORDS]... t/TAG [MORE_TAGS] pr/PRICE RANGE\n"
            + "Example: " + COMMAND_WORD + " alice bob charlie t/colleague pr/<100.00";

    public static final String PRICE_USAGE = "Valid prices operators are =, <, >, <=, >=. \n"
            + "Prices must be in 2 decimal. Eg. pr/<100.00";

    public static final String SUCCESS_RESPONSE = "%d person(s) listed where %s";

    private final Predicate<Person> predicate;
    private final String message;

    /**
     * Creates FindCommand with a predicate an a message describing the query
     * @param predicate List filter
     * @param message Message of query
     */
    public FindCommand(Predicate<Person> predicate, String message) {
        this.predicate = predicate;
        this.message = message;
    }

    @Override
    public CommandResult execute(Model model) {
        requireNonNull(model);
        model.updateFilteredPersonList(predicate);
        return new CommandResult(String.format(SUCCESS_RESPONSE, model.getFilteredPersonList().size(), message));
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof FindCommand // instanceof handles nulls
                && predicate.equals(((FindCommand) other).predicate)
                && message.equals(((FindCommand) other).message)); // state check
    }

    public String getMessage() {
        return message;
    }
}
