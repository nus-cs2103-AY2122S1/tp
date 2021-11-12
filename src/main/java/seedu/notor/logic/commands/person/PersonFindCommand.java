package seedu.notor.logic.commands.person;

import static java.util.Objects.requireNonNull;

import java.util.Arrays;
import java.util.List;

import seedu.notor.logic.commands.CommandResult;
import seedu.notor.logic.executors.exceptions.ExecuteException;
import seedu.notor.logic.executors.person.PersonExecutor;
import seedu.notor.logic.executors.person.PersonFindExecutor;
import seedu.notor.model.person.PersonContainsPredicate;

/**
 * Finds a person via searching their name for a given query
 */
public class PersonFindCommand extends PersonCommand {
    public static final String COMMAND_WORD = "find";
    public static final List<String> COMMAND_WORDS = Arrays.asList("find", "f");

    private static final String COMMAND_DESCRIPTION = ": Finds a person via searching their name for a given query.\n"
                    + "Returns the person list filtered by those who match the query\n";

    public static final String MESSAGE_USAGE = PersonCommand.COMMAND_WORD + " /" + COMMAND_WORD + " "
            + COMMAND_DESCRIPTION
            + "Parameters: n:NAME_QUERY and/or t:TAG_1, TAG_2 \n"
            + "Example: "
            + PersonCommand.COMMAND_WORD + " /" + COMMAND_WORD + " n:Alex t:graduated, engineering";

    private final PersonExecutor executor;

    /**
     * Constructor for a PersonFindCommand.
     */
    public PersonFindCommand(PersonContainsPredicate predicate) {
        super(null);
        requireNonNull(predicate);
        this.executor = new PersonFindExecutor(predicate);
    }

    @Override
    public CommandResult execute() throws ExecuteException {
        return executor.execute();
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof PersonFindCommand // instanceof handles nulls
                && executor.equals(((PersonFindCommand) other).executor)); // state check
    }
}
