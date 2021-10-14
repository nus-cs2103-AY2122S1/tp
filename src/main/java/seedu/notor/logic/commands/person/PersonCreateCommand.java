package seedu.notor.logic.commands.person;

import static java.util.Objects.requireNonNull;
import static seedu.notor.logic.parser.CliSyntax.PREFIX_EMAIL;
import static seedu.notor.logic.parser.CliSyntax.PREFIX_NAME;
import static seedu.notor.logic.parser.CliSyntax.PREFIX_PHONE;
import static seedu.notor.logic.parser.CliSyntax.PREFIX_TAG;

import seedu.notor.commons.core.index.Index;
import seedu.notor.logic.commands.CommandResult;
import seedu.notor.logic.executors.exceptions.ExecuteException;
import seedu.notor.logic.executors.person.PersonCreateExecutor;
import seedu.notor.logic.parser.exceptions.ParseException;
import seedu.notor.model.person.Person;

/**
 * Adds a person to the address book.
 */
public class PersonCreateCommand extends PersonCommand {
    public static final String COMMAND_WORD = "add";

    public static final String MESSAGE_USAGE = COMMAND_WORD + ": Adds a person to the address book. "
            + "Parameters: "
            + PREFIX_NAME + "NAME "
            + PREFIX_PHONE + "PHONE "
            + PREFIX_EMAIL + "EMAIL "
            + "[" + PREFIX_TAG + "TAG]...\n"
            + "Example: " + COMMAND_WORD + " "
            + PREFIX_NAME + "John Doe "
            + PREFIX_PHONE + "98765432 "
            + PREFIX_EMAIL + "johnd@example.com "
            + PREFIX_TAG + "friends "
            + PREFIX_TAG + "owesMoney";

    private final PersonCreateExecutor executor;

    /**
     * Creates an AddCommand to add the specified {@code Person}
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
}
