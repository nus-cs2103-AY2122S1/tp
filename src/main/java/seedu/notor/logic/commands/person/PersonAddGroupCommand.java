package seedu.notor.logic.commands.person;

import static seedu.notor.logic.parser.CliSyntax.PREFIX_GROUPNAME;

import java.util.Arrays;
import java.util.List;

import seedu.notor.commons.core.index.Index;
import seedu.notor.logic.commands.CommandResult;
import seedu.notor.logic.executors.exceptions.ExecuteException;
import seedu.notor.logic.executors.person.PersonAddGroupExecutor;
import seedu.notor.logic.executors.person.PersonExecutor;

public class PersonAddGroupCommand extends PersonCommand {
    public static final String COMMAND_WORD = "add";
    public static final List<String> COMMAND_WORDS = Arrays.asList("add", "a");
    private static final String COMMAND_DESCRIPTION = ": Adds a person to a group or subgroup\n";

    public static final String MESSAGE_USAGE = PersonCommand.COMMAND_WORD + " INDEX /" + COMMAND_WORD
            + COMMAND_DESCRIPTION
            + "Parameters: "
            + PREFIX_GROUPNAME + "GROUP_NAME" + "\n"
            + "Example: " + PersonCommand.COMMAND_WORD + " 1 /" + COMMAND_WORD
            + " " + PREFIX_GROUPNAME + "Team 1";

    protected String groupName;

    private final PersonExecutor executor;

    /**
     * Creates an PersonAddGroupCommand to add the specified {@code Person}
     */
    public PersonAddGroupCommand(Index index, String groupName) {
        super(index);
        this.groupName = groupName;
        executor = new PersonAddGroupExecutor(index, groupName);
    }

    @Override
    public CommandResult execute() throws ExecuteException {
        return executor.execute();
    }
}
