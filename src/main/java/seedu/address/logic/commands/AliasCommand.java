package seedu.address.logic.commands;

import static java.util.Objects.requireNonNull;
import static seedu.address.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
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
            + "Example: "
            + COMMAND_WORD
            + " a/findPartner c/find g/m nat/South Korean";

    public static final String MESSAGE_SUCCESS = "Alias successfully added. Mapped `%s` to the command `%s`.";
    public static final String MESSAGE_INVALID_ALIAS = "Invalid alias. Command words cannot be used as an alias.";
    public static final String MESSAGE_INVALID_COMMAND_FORMAT_ALIAS_ABSENT = String.format(
            MESSAGE_INVALID_COMMAND_FORMAT, "No alias was entered. \n%1$s");
    public static final String MESSAGE_INVALID_COMMAND_FORMAT_COMMAND_ABSENT = String.format(
            MESSAGE_INVALID_COMMAND_FORMAT, "No command was entered. \n%1$s");
    public static final String MESSAGE_INVALID_COMMAND_FORMAT_PREAMBLE_PRESENT = String.format(
            MESSAGE_INVALID_COMMAND_FORMAT, "An input was entered without prefix. \n%1$s");

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
