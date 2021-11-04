package seedu.address.logic.commands;

import static java.util.Objects.requireNonNull;
import static seedu.address.commons.util.CollectionUtil.requireAllNonNull;
import static seedu.address.logic.parser.CliSyntax.PREFIX_ALIAS;
import static seedu.address.logic.parser.CliSyntax.PREFIX_COMMAND;

import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.logic.parser.CommandAliases;
import seedu.address.model.Model;


/**
 * Creates a shortcut name for a command.
 */
public class AliasCommand extends Command {

    public static final String COMMAND_WORD = "alias";

    public static final String MESSAGE_USAGE = COMMAND_WORD
            + ": Creates a shortcut name for a command.\n"
            + "Parameters: "
            + PREFIX_ALIAS + "ALIAS "
            + PREFIX_COMMAND + "COMMAND\n"
            + "Example: a/findPartner c/ g/m nat/Korean";

    public static final String MESSAGE_SUCCESS = "Alias successfully added. Mapped `%s` to the command `%s`.";
    public static final String MESSAGE_INVALID_ALIAS = "Invalid alias. Command words cannot be used as an alias.";

    private final String alias;
    private final Command command;
    private final String rawCommand;

    /**
     * Creates an AliasCommand to map the specified {@code alias} to {@code command}
     */
    public AliasCommand(String alias, Command command, String rawCommand) {
        requireAllNonNull(alias, command, rawCommand);

        this.alias = alias;
        this.command = command;
        this.rawCommand = rawCommand;
    }

    @Override
    public CommandResult execute(Model model) throws CommandException {
        requireNonNull(model);

        CommandAliases.put(alias, command);

        return new CommandResult(String.format(MESSAGE_SUCCESS, alias, rawCommand));
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof AliasCommand // instanceof handles nulls
                && alias.equals(((AliasCommand) other).alias)
                && command.equals(((AliasCommand) other).command));
    }
}
