package seedu.address.logic.commands;

import static java.util.Objects.requireNonNull;

import java.util.function.Predicate;

import seedu.address.commons.core.Messages;
import seedu.address.model.Model;
import seedu.address.model.person.Person;

/**
 * Finds and lists all persons in address book whose name contains any of the
 * argument keywords. Keyword matching is case insensitive.
 */
public class FindCommand extends Command {

    public static final String COMMAND_WORD = "find";

    public static final String MESSAGE_USAGE = COMMAND_WORD + ": Finds all persons whose fields contain any of "
            + "the specified keywords (case-insensitive) and displays them as a list with index numbers."
            + System.lineSeparator()
            + "Parameters: FLAG KEYWORDS [MORE_FLAGS]"
            + System.lineSeparator()
            + "Example: " + COMMAND_WORD + " -n alex -a serangoon";

    private final Predicate<Person> predicate;

    /**
     * Takes in a Predicate.
     *
     * @param predicate input Predicate
     */
    public FindCommand(Predicate<Person> predicate) {
        this.predicate = predicate;
    }

    @Override
    public CommandResult execute(Model model) {
        requireNonNull(model);
        model.updateFilteredPersonList(predicate);
        int numberOfPerson = model.getFilteredPersonList().size();
        return new CommandResult("Showing results for: "
                + System.lineSeparator()
                + predicate
                + System.lineSeparator()
                + (numberOfPerson == 1
                ? Messages.MESSAGE_ONE_PERSON_LISTED_OVERVIEW
                : String.format(Messages.MESSAGE_PERSONS_LISTED_OVERVIEW, numberOfPerson)));
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof FindCommand // instanceof handles nulls
                        && predicate.equals(((FindCommand) other).predicate)); // state check
    }
}
