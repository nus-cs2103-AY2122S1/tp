package seedu.address.logic.commands;

import static java.util.Objects.requireNonNull;
import static seedu.address.logic.parser.CliSyntax.PREFIX_CASE_NUMBER;
import static seedu.address.logic.parser.CliSyntax.PREFIX_NAME;

import java.util.Arrays;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Stream;

import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.logic.parser.Prefix;
import seedu.address.model.Model;
import seedu.address.model.person.Person;

/**
 * Sorts all persons in the address book based on specified fields.
 */
public class SortCommand extends Command {

    public static final String COMMAND_WORD = "sort";

    public static final String MESSAGE_USAGE = COMMAND_WORD + ": Sorts all persons in the contact list. "
            + "At least one prefix must be provided.\n"
            + "Parameters: "
            + "[" + PREFIX_NAME + "] "
            + "[" + PREFIX_CASE_NUMBER + "]\n"
            + "Example: " + COMMAND_WORD + " "
            + PREFIX_NAME + " "
            + PREFIX_CASE_NUMBER;

    public static final String MESSAGE_SUCCESS = "All persons sorted by %s";

    public static final List<Prefix> SUPPORTED_PREFIXES = Arrays.asList(PREFIX_NAME, PREFIX_CASE_NUMBER);

    private final List<Prefix> prefixes;

    /**
     * Creates a SortCommand to sort by the specified fields in {@code args}
     */
    public SortCommand(List<Prefix> prefixes) {
        assert prefixes.size() > 0; // prefixes is non-empty
        assert SUPPORTED_PREFIXES.containsAll(prefixes); // all prefixes are supported
        assert prefixes.size() == prefixes.stream().distinct().count(); // all prefixes are distinct
        this.prefixes = prefixes;
    }

    @Override
    public CommandResult execute(Model model) throws CommandException {
        requireNonNull(model);

        Stream<Comparator<Person>> sortComparators = prefixes.stream().map(SortCommand::convertPrefix);
        Comparator<Person> comparator = sortComparators.reduce(Comparator.naturalOrder(), (
            accComparator,
            nextComparator
        ) -> nextComparator.thenComparing(accComparator));
        model.updateSortedPersonList(comparator);

        return new CommandResult(String.format(MESSAGE_SUCCESS, prefixes));
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof SortCommand // instanceof handles nulls
                && prefixes.equals(((SortCommand) other).prefixes));
    }

    /**
     * Converts {@code prefix} into a {@code Comparator<Person>} based on the natural order of
     * the respective field.
     */
    private static Comparator<Person> convertPrefix(Prefix prefix) {
        requireNonNull(prefix);

        if (!SUPPORTED_PREFIXES.contains(prefix)) {
            throw new IllegalArgumentException(String.format("Sorting by prefix %s is not supported.", prefix));
        }

        if (prefix.equals(PREFIX_NAME)) {
            return Comparator.comparing(Person::getName);
        } else if (prefix.equals(PREFIX_CASE_NUMBER)) {
            return Comparator.comparing(Person::getCaseNumber);
        }

        throw new IllegalStateException(String.format("Prefix %s should match one of the supported prefixes.", prefix));
    }
}
