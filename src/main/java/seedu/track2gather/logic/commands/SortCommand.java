package seedu.track2gather.logic.commands;

import static java.util.Objects.requireNonNull;
import static seedu.track2gather.commons.util.CollectionUtil.requireAllNonNull;
import static seedu.track2gather.logic.parser.CliSyntax.PREFIX_CASE_NUMBER;
import static seedu.track2gather.logic.parser.CliSyntax.PREFIX_NAME;
import static seedu.track2gather.logic.parser.CliSyntax.PREFIX_SHN_PERIOD_END;
import static seedu.track2gather.logic.parser.CliSyntax.PREFIX_SHN_PERIOD_START;

import java.time.LocalDate;
import java.util.Arrays;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

import seedu.track2gather.logic.commands.exceptions.CommandException;
import seedu.track2gather.logic.parser.Prefix;
import seedu.track2gather.model.Model;
import seedu.track2gather.model.person.Person;
import seedu.track2gather.model.person.attributes.Period;

/**
 * Sorts the persons list based on the field(s) specified by the user.
 */
public class SortCommand extends Command {

    public static final String COMMAND_WORD = "sort";

    public static final String MESSAGE_USAGE = COMMAND_WORD + ": Sorts the persons list based on the field(s) "
            + "specified by the user. "
            + "At least one field prefix must be provided. Specifying the sort direction is optional. "
            + "Default sort direction is ascending.\n"
            + "Direction \"" + Direction.ASCENDING + "\" indicates ascending order and \""
            + Direction.DESCENDING + "\" indicates descending order.\n"
            + "Parameters: "
            + "[" + PREFIX_NAME + "DIRECTION] "
            + "[" + PREFIX_CASE_NUMBER + "DIRECTION] "
            + "[" + PREFIX_SHN_PERIOD_START + "DIRECTION] "
            + "[" + PREFIX_SHN_PERIOD_END + "DIRECTION]\n"
            + "Examples: \"" + COMMAND_WORD + " " + PREFIX_NAME + "\""
            + " \"" + COMMAND_WORD + " " + PREFIX_SHN_PERIOD_END + Direction.DESCENDING + "\""
            + " \"" + COMMAND_WORD + " " + PREFIX_SHN_PERIOD_START + " " + PREFIX_CASE_NUMBER + Direction.ASCENDING
            + "\"";

    public static final String MESSAGE_SUCCESS = "All persons sorted by %s";

    public static final HashMap<Prefix, String> SORTING_FIELD_STRINGS;

    static {
        SORTING_FIELD_STRINGS = new HashMap<Prefix, String>();
        SORTING_FIELD_STRINGS.put(PREFIX_NAME, "name");
        SORTING_FIELD_STRINGS.put(PREFIX_CASE_NUMBER, "case number");
        SORTING_FIELD_STRINGS.put(PREFIX_SHN_PERIOD_START, "SHN start date");
        SORTING_FIELD_STRINGS.put(PREFIX_SHN_PERIOD_END, "SHN end date");
    }

    public static final HashMap<Direction, String> SORTING_DIRECTION_STRINGS;

    static {
        SORTING_DIRECTION_STRINGS = new HashMap<Direction, String>();
        SORTING_DIRECTION_STRINGS.put(Direction.ASCENDING, " in ascending order");
        SORTING_DIRECTION_STRINGS.put(Direction.DESCENDING, " in descending order");
    }

    public static final List<Prefix> SUPPORTED_PREFIXES = Arrays.asList(PREFIX_NAME, PREFIX_CASE_NUMBER,
            PREFIX_SHN_PERIOD_START, PREFIX_SHN_PERIOD_END);
    public static final Comparator<Person> COMPARATOR_PERSON_NAME = Comparator.comparing(Person::getName);
    public static final Comparator<Person> COMPARATOR_PERSON_CASE_NUMBER = Comparator.comparing(Person::getCaseNumber);
    public static final Comparator<Person> COMPARATOR_PERSON_SHN_PERIOD_START_ASC = Comparator.comparing(person ->
            person.getShnPeriod().value.map(Period::getStartDate).orElse(LocalDate.MAX));
    public static final Comparator<Person> COMPARATOR_PERSON_SHN_PERIOD_START_DSC = Comparator.comparing(person ->
            person.getShnPeriod().value.map(Period::getStartDate).orElse(LocalDate.MIN));
    public static final Comparator<Person> COMPARATOR_PERSON_SHN_PERIOD_END_ASC = Comparator.comparing(person ->
            person.getShnPeriod().value.map(Period::getEndDate).orElse(LocalDate.MAX));
    public static final Comparator<Person> COMPARATOR_PERSON_SHN_PERIOD_END_DSC = Comparator.comparing(person ->
            person.getShnPeriod().value.map(Period::getEndDate).orElse(LocalDate.MIN));

    public enum Direction {
        ASCENDING("asc"),
        DESCENDING("dsc");

        final String code;

        Direction(String code) {
            this.code = code;
        }

        @Override
        public String toString() {
            return code;
        }
    }

    private final List<Prefix> prefixes;
    private final List<Direction> directions;

    /**
     * Creates a SortCommand to sort by the specified fields in {@code args}
     *
     * Takes in sorted unique lists {@code prefixes}, {@code directions}
     */
    public SortCommand(List<Prefix> prefixes, List<Direction> directions) {
        requireAllNonNull(prefixes);
        requireAllNonNull(directions);
        assert prefixes.size() > 0; // prefixes is non-empty
        assert prefixes.size() == prefixes.stream().distinct().count(); // prefixes are distinct
        assert SUPPORTED_PREFIXES.containsAll(prefixes); // all prefixes are supported
        assert prefixes.size() == directions.size(); // prefixes and directions are of the same size

        this.prefixes = prefixes;
        this.directions = directions;
    }

    @Override
    public CommandResult execute(Model model) throws CommandException {
        requireNonNull(model);

        Comparator<Person> comparator = IntStream.range(0, prefixes.size())
                .mapToObj(i -> buildComparator(prefixes.get(i), directions.get(i)))
                .reduce(Comparator.naturalOrder(), (accComparator, nextComparator)
                    -> nextComparator.thenComparing(accComparator));
        model.updateSortedPersonList(comparator);

        String sortsString = IntStream.range(0, prefixes.size())
                .mapToObj(i -> SORTING_FIELD_STRINGS.get(prefixes.get(i))
                        + SORTING_DIRECTION_STRINGS.get(directions.get(i)))
                .collect(Collectors.joining(", then by "));

        return new CommandResult(String.format(MESSAGE_SUCCESS, sortsString));
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof SortCommand // instanceof handles nulls
                && prefixes.equals(((SortCommand) other).prefixes)
                && directions.equals(((SortCommand) other).directions));
    }

    /**
     * Builds a {@code Comparator<Person>} based on the natural order of the
     * specified {@code prefix} and its respective {@code direction}.
     */
    private static Comparator<Person> buildComparator(Prefix prefix, Direction direction) {
        requireAllNonNull(prefix, direction);
        assert SUPPORTED_PREFIXES.contains(prefix);

        Comparator<Person> comparator;
        if (prefix.equals(PREFIX_NAME)) {
            comparator = COMPARATOR_PERSON_NAME;
        } else if (prefix.equals(PREFIX_CASE_NUMBER)) {
            comparator = COMPARATOR_PERSON_CASE_NUMBER;
        } else if (prefix.equals(PREFIX_SHN_PERIOD_START)) {
            comparator = direction == Direction.ASCENDING
                    ? COMPARATOR_PERSON_SHN_PERIOD_START_ASC
                    : COMPARATOR_PERSON_SHN_PERIOD_START_DSC;
        } else if (prefix.equals(PREFIX_SHN_PERIOD_END)) {
            comparator = direction == Direction.ASCENDING
                    ? COMPARATOR_PERSON_SHN_PERIOD_END_ASC
                    : COMPARATOR_PERSON_SHN_PERIOD_END_DSC;
        } else {
            throw new IllegalStateException("Prefix %s is not supported.");
        }

        return direction == Direction.ASCENDING ? comparator : comparator.reversed();
    }
}
