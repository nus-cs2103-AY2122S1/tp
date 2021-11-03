package seedu.notor.logic.commands.person;

import static seedu.notor.logic.parser.CliSyntax.PREFIX_GROUPNAME;
import static seedu.notor.logic.parser.CliSyntax.PREFIX_NAME;
import static seedu.notor.logic.parser.CliSyntax.PREFIX_SUBGROUP;

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

    private static final String COMMAND_DESCRIPTION = ": Removes a person from a group or subgroup. \n";

    // TODO: Change this to fit correct command structure
    public static final String MESSAGE_USAGE = PersonCommand.COMMAND_WORD + " INDEX /" + COMMAND_WORD
            + COMMAND_DESCRIPTION
            + "Parameters: "
            + PREFIX_NAME + "NAME "
            + PREFIX_GROUPNAME + "GROUP" + " [" + PREFIX_SUBGROUP + "SUBGROUP" + "]" + "\n"
            + "Example: " + PersonCommand.COMMAND_WORD
            + " 1 /" + COMMAND_WORD + " "
            + PREFIX_GROUPNAME + "Team" + "\n"
            + "Example: " + PersonCommand.COMMAND_WORD
            + " 1 /" + COMMAND_WORD + " "
            + PREFIX_GROUPNAME + "Team " + PREFIX_SUBGROUP + "A";


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
