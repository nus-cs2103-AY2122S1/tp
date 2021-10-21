package seedu.notor.logic.commands.person;

import static seedu.notor.logic.parser.CliSyntax.PREFIX_GROUPNAME;
import static seedu.notor.logic.parser.CliSyntax.PREFIX_NAME;

import java.util.Arrays;
import java.util.List;

import seedu.notor.commons.core.index.Index;
import seedu.notor.logic.commands.CommandResult;
import seedu.notor.logic.executors.exceptions.ExecuteException;
import seedu.notor.logic.executors.person.PersonExecutor;
import seedu.notor.logic.executors.person.PersonRemoveGroupExecutor;

public class PersonRemoveGroupCommand extends PersonCommand {
    public static final String COMMAND_WORD = "remove";
    public static final List<String> COMMAND_WORDS = Arrays.asList("remove", "r");

    // TODO: Change this to fit correct command structure
    public static final String MESSAGE_USAGE = COMMAND_WORD + ": Removes a person from a group"
            + "Parameters: "
            + PREFIX_NAME + "NAME "
            + PREFIX_GROUPNAME + "GROUP\n"
            + "Example: " + COMMAND_WORD + " "
            + PREFIX_NAME + "John Doe "
            + PREFIX_GROUPNAME + "Team";

    protected String groupName;

    private final PersonExecutor executor;

    /**
     * Creates an PersonRemoveGroupCommand to add the specified {@code Person}
     */
    public PersonRemoveGroupCommand(Index index, String groupName) {
        super(index);
        this.groupName = groupName;
        this.executor = new PersonRemoveGroupExecutor(index, groupName);
    }

    @Override
    public CommandResult execute() throws ExecuteException {
        return executor.execute();
    }
}
