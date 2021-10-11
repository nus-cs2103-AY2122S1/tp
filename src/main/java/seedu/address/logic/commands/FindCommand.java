package seedu.address.logic.commands;

import static java.util.Objects.requireNonNull;
import static seedu.address.logic.parser.CliSyntax.PREFIX_ADDRESS;
import static seedu.address.logic.parser.CliSyntax.PREFIX_EMAIL;
import static seedu.address.logic.parser.CliSyntax.PREFIX_FIND_CONDITION;
import static seedu.address.logic.parser.CliSyntax.PREFIX_NAME;
import static seedu.address.logic.parser.CliSyntax.PREFIX_PARENT_EMAIL;
import static seedu.address.logic.parser.CliSyntax.PREFIX_PARENT_PHONE;
import static seedu.address.logic.parser.CliSyntax.PREFIX_PHONE;
import static seedu.address.logic.parser.CliSyntax.PREFIX_TAG;

import java.util.function.Predicate;

import seedu.address.commons.core.Messages;
import seedu.address.model.Model;
import seedu.address.model.person.Person;

/**
 * Finds and lists all persons in address book whose fields match the given argument keywords.
 * Keyword matching is case insensitive.
 */
public class FindCommand extends Command {

    public static final String COMMAND_WORD = "find";

    public static final String COMMAND_PARAMETERS = "[" + PREFIX_FIND_CONDITION + "{all | any | none}] "
        + "[" + PREFIX_NAME + "NAME] "
        + "[" + PREFIX_ADDRESS + "ADDRESS] "
        + "[" + PREFIX_PHONE + "PHONE] "
        + "[" + PREFIX_EMAIL + "EMAIL] "
        + "[" + PREFIX_PARENT_PHONE + "PHONE] "
        + "[" + PREFIX_PARENT_EMAIL + "EMAIL] "
        + "[" + PREFIX_ADDRESS + "ADDRESS] "
        + "[" + PREFIX_TAG + "TAG]...";

    public static final String COMMAND_EXAMPLE = COMMAND_WORD + " "
        + PREFIX_FIND_CONDITION + "any "
        + PREFIX_NAME + "John Tan "
        + PREFIX_ADDRESS + "Clementi "
        + PREFIX_TAG + "unpaid " + PREFIX_TAG + "zoom";

    public static final String MESSAGE_USAGE = COMMAND_WORD + ": Finds all persons whose fields contain any of the "
        + "specified keywords (case-insensitive).\n"
        + "You can specify one of these find conditions: 'all' / 'any' / 'none'\n"
        + "It indicates that a student is only considered a match when all, any or none of the the fields "
        + "which you are searching for match their keywords. The default is 'all'.\n"
        + "Parameters: " + COMMAND_PARAMETERS + "\n"
        + "Example: " + COMMAND_EXAMPLE;

    public static final String MESSAGE_FIELD_REQUIRED = "You must provide at least one parameter to find.\n"
        + "Parameters: " + COMMAND_PARAMETERS + "\n"
        + "Example: " + COMMAND_EXAMPLE;

    public static final String MESSAGE_CONDITION_CONSTRAINTS = "Find condition should take on one of these values: "
        + "all / any / none";

    public static final String MESSAGE_INVALID_KEYWORD = "Keyword cannot be empty.";

    private final Predicate<Person> predicate;

    /**
     * Creates a FindCommand to find persons that match the given predicate.
     *
     * @param predicate
     */
    public FindCommand(Predicate<Person> predicate) {
        this.predicate = predicate;
    }

    @Override
    public CommandResult execute(Model model) {
        requireNonNull(model);
        model.updateFilteredPersonList(predicate);
        return new CommandResult(
            String.format(Messages.MESSAGE_PERSONS_LISTED_OVERVIEW, model.getFilteredPersonList().size()));
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
            return this.name().toLowerCase();
        }
    }
}
