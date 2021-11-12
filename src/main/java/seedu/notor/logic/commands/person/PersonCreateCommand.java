package seedu.notor.logic.commands.person;

import static java.util.Objects.requireNonNull;
import static seedu.notor.logic.parser.CliSyntax.PREFIX_EMAIL;
import static seedu.notor.logic.parser.CliSyntax.PREFIX_GROUPINDEX;
import static seedu.notor.logic.parser.CliSyntax.PREFIX_PHONE;
import static seedu.notor.logic.parser.CliSyntax.PREFIX_TAG;

import java.util.Arrays;
import java.util.List;
import java.util.Objects;

import seedu.notor.commons.core.index.Index;
import seedu.notor.logic.commands.CommandResult;
import seedu.notor.logic.executors.exceptions.ExecuteException;
import seedu.notor.logic.executors.person.PersonCreateExecutor;
import seedu.notor.logic.executors.person.PersonExecutor;
import seedu.notor.logic.parser.exceptions.ParseException;
import seedu.notor.model.person.Person;

/**
 * Adds a person to Notor.
 */
public class PersonCreateCommand extends PersonCommand {
    public static final String COMMAND_WORD = "create";
    public static final List<String> COMMAND_WORDS = Arrays.asList("create", "c");
    private static final String COMMAND_DESCRIPTION = ": Creates a person in Notor.\n";

    public static final String MESSAGE_USAGE = PersonCommand.COMMAND_WORD + " NAME /" + COMMAND_WORD
            + COMMAND_DESCRIPTION
            + "Optional Parameters: "
            + PREFIX_PHONE + "PHONE "
            + PREFIX_EMAIL + "EMAIL "
            + PREFIX_TAG + "TAGS... "
            + PREFIX_GROUPINDEX + "\n"
            + "Example: " + PersonCommand.COMMAND_WORD
            + " John Doe /" + COMMAND_WORD + " "
            + PREFIX_PHONE + "98765432 "
            + PREFIX_EMAIL + "johnd@example.com "
            + PREFIX_TAG + "student, year1"
            + PREFIX_GROUPINDEX + 1;

    private final PersonExecutor executor;

    /**
     * Creates an CreateCommand to add the specified {@code Person}
     */
    public PersonCreateCommand(Index index, Person person) throws ParseException {
        super(index);
        requireNonNull(person);
        this.executor = new PersonCreateExecutor(index, person);
    }

    @Override
    public CommandResult execute() throws ExecuteException {
        return executor.execute();
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof PersonCreateCommand // instanceof handles nulls
                && executor.equals(((PersonCreateCommand) other).executor));
    }

    @Override
    public int hashCode() {
        return Objects.hash(executor);
    }
}
