package seedu.address.logic.commands;

import static java.util.Objects.requireNonNull;
import static seedu.address.logic.parser.CliSyntax.PREFIX_ACAD_LEVEL;
import static seedu.address.logic.parser.CliSyntax.PREFIX_ACAD_STREAM;
import static seedu.address.logic.parser.CliSyntax.PREFIX_ADDRESS;
import static seedu.address.logic.parser.CliSyntax.PREFIX_CANCEL;
import static seedu.address.logic.parser.CliSyntax.PREFIX_DATE;
import static seedu.address.logic.parser.CliSyntax.PREFIX_EMAIL;
import static seedu.address.logic.parser.CliSyntax.PREFIX_FIND_CONDITION;
import static seedu.address.logic.parser.CliSyntax.PREFIX_HOMEWORK;
import static seedu.address.logic.parser.CliSyntax.PREFIX_NAME;
import static seedu.address.logic.parser.CliSyntax.PREFIX_PARENT_EMAIL;
import static seedu.address.logic.parser.CliSyntax.PREFIX_PARENT_PHONE;
import static seedu.address.logic.parser.CliSyntax.PREFIX_PHONE;
import static seedu.address.logic.parser.CliSyntax.PREFIX_RATES;
import static seedu.address.logic.parser.CliSyntax.PREFIX_SCHOOL;
import static seedu.address.logic.parser.CliSyntax.PREFIX_SUBJECT;
import static seedu.address.logic.parser.CliSyntax.PREFIX_TAG;
import static seedu.address.logic.parser.CliSyntax.PREFIX_TIME;

import java.util.function.Predicate;

import seedu.address.model.person.Person;

/**
 * Finds and lists all persons in address book whose fields match the given argument keywords.
 * Keyword matching is case insensitive.
 */
public class FindCommand extends Command {

    public static final String COMMAND_ACTION = "Find Student";

    public static final String COMMAND_WORD = "find";

    public static final String COMMAND_PARAMETERS = "[" + PREFIX_FIND_CONDITION + "{all | any | none}] "
            + "[" + PREFIX_TAG + "TAG_KEYWORD]... "
            + "[" + PREFIX_NAME + "NAME_KEYWORDS] "
            + "[" + PREFIX_PHONE + "PHONE_KEYWORDS] "
            + "[" + PREFIX_EMAIL + "EMAIL_KEYWORDS] "
            + "[" + PREFIX_PARENT_PHONE + "PARENT_PHONE_KEYWORDS] "
            + "[" + PREFIX_PARENT_EMAIL + "PARENT_EMAIL_KEYWORDS] "
            + "[" + PREFIX_ADDRESS + "ADDRESS_KEYWORDS] "
            + "[" + PREFIX_SCHOOL + "SCHOOL_KEYWORDS] "
            + "[" + PREFIX_ACAD_STREAM + "ACAD_STREAM_KEYWORDS] "
            + "[" + PREFIX_ACAD_LEVEL + "ACAD_LEVEL_KEYWORDS] "
            + "[" + PREFIX_SUBJECT + "SUBJECT_KEYWORDS] "
            + "[" + PREFIX_TIME + "TIME_RANGE] "
            + "[" + PREFIX_DATE + "START_DATE] "
            + "[" + PREFIX_CANCEL + "CANCELLED_DATE] "
            + "[" + PREFIX_RATES + "LESSON_RATES_KEYWORDS] "
            + "[" + PREFIX_HOMEWORK + "HOMEWORK_KEYWORDS] ";

    public static final String COMMAND_FORMAT = COMMAND_WORD + " " + COMMAND_PARAMETERS;

    public static final String COMMAND_EXAMPLE = COMMAND_WORD + " "
            + PREFIX_FIND_CONDITION + "any "
            + PREFIX_NAME + "Alex Yeo "
            + PREFIX_TAG + "unpaid "
            + PREFIX_TIME + "1500-1600";

    public static final String MESSAGE_USAGE = COMMAND_WORD + ": Finds all students whose fields contain any of the "
            + "specified keywords (case-insensitive).\n"
            + "You can specify one of these find conditions: 'all' / 'any' / 'none'\n"
            + "It indicates that a student is only considered a match when all, any or none of the the fields "
            + "which you are searching for match their keywords. The default is 'all'.\n"
            + "Note: you must specify at least one parameter to find.\n"
            + "Parameters: " + COMMAND_PARAMETERS + "\n"
            + "Example: " + COMMAND_EXAMPLE;

    public static final String MESSAGE_CONDITION_CONSTRAINTS = "Find condition should take on one of these values: "
            + "all / any / none";

    public static final String MESSAGE_KEYWORD_CONSTRAINTS = "Keyword cannot be empty.";

    public static final String MESSAGE_TAG_KEYWORD_CONSTRAINTS = String.format("Tag can only take one keyword. "
            + "To search for multiple tags, use multiple %1$s \n"
            + "Example: find %1$sunpaid %1$szoom", PREFIX_TAG);

    public static final String MESSAGE_FIND_RESULTS = "%1$d student(s) found who %2$s";

    private final Predicate<Person> predicate;

    /**
     * Creates a FindCommand to find persons that match the given predicate.
     *
     * @param predicate Person predicate to filter the list of persons with.
     */
    public FindCommand(Predicate<Person> predicate) {
        this.predicate = predicate;
    }

    @Override
    public CommandResult execute() {
        requireNonNull(model);
        model.updateFilteredPersonList(predicate);
        return new CommandResult(
                String.format(MESSAGE_FIND_RESULTS, model.getFilteredPersonList().size(), predicate));
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof FindCommand // instanceof handles nulls
                && predicate.equals(((FindCommand) other).predicate)); // state check
    }

    /**
     * Represents a find condition that determines how a person is matched.
     */
    public enum FindCondition {
        ALL, // Person must match all fields specified
        ANY, // Person must match at least one of the fields specified
        NONE; // Person must match none of the fields specified

        /**
         * Returns the {@code FindCondition} with the specified name.
         * If the name is invalid, null is returned.
         *
         * @param name The name to be parsed.
         * @return FindCondition with the specified name.
         */
        public static FindCondition valueOfName(String name) {
            for (FindCondition c : values()) {
                if (c.name().equalsIgnoreCase(name)) {
                    return c;
                }
            }
            return null;
        }

        @Override
        public String toString() {
            return name().toLowerCase();
        }
    }
}
