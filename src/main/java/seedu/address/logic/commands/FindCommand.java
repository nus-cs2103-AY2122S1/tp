package seedu.address.logic.commands;

import static java.util.Objects.requireNonNull;

import java.util.ArrayList;
import java.util.function.Predicate;

import seedu.address.commons.core.Messages;
import seedu.address.model.Model;
import seedu.address.model.person.Person;

/**
 * Finds and lists all persons in address book whose name contains any of the argument keywords.
 * Keyword matching is case insensitive.
 */
public class FindCommand extends Command {

    public static final String COMMAND_WORD = "find";

    public static final String MESSAGE_USAGE = COMMAND_WORD + ": Finds all persons whose fields contain any of "
            + "the specified keywords (case-insensitive) under their respected prefixes, "
            + "and displays them as a list with index numbers.\n"
            + "Parameters: PREFIX/KEYWORDS [MORE_PREFIXES/MORE_KEYWORDS]...\n"
            + "Example: " + COMMAND_WORD + " n/alice bob charlie p/99999999";

    private final ArrayList<Predicate<Person>> predicates;

    /**
     * Constructor for FindCommand.
     * <p>
     * predicates should not be null.
     *
     * @param predicates A list of {@code Predicate}s to filter {@code Person}s by.
     */
    public FindCommand(ArrayList<Predicate<Person>> predicates) {
        requireNonNull(predicates);
        this.predicates = predicates;
    }

    @Override
    public CommandResult execute(Model model) {

        assert predicates != null : "predicates should not be null";

        requireNonNull(model);

        // Accumulate the predicates via stream
        Predicate<Person> combinedPredicate = predicates.stream().reduce(person -> true, Predicate::and);

        model.updateFilteredPersonList(combinedPredicate);
        return new CommandResult(
                String.format(Messages.MESSAGE_PERSONS_LISTED_OVERVIEW, model.getFilteredPersonList().size()));
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof FindCommand // instanceof handles nulls
                && predicates.equals(((FindCommand) other).predicates)); // state check
    }
}
