package seedu.address.logic.commands;

import static java.util.Objects.requireNonNull;
import static seedu.address.logic.parser.CliSyntax.PREFIX_COMMAND_WORD;
import static seedu.address.logic.parser.CliSyntax.PREFIX_SHORTCUT;

import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.model.Model;
import seedu.address.model.alias.Alias;

/**
 * Adds a user-defined alias to SportsPA user preferences.
 */
public class AddAliasCommand extends Command {

    public static final String COMMAND_WORD = "alias";
    public static final String MESSAGE_USAGE = COMMAND_WORD + ": creates an alias for the specified command word.\n"
            + "Parameters: "
            + PREFIX_SHORTCUT + "SHORTCUT "
            + PREFIX_COMMAND_WORD + "COMMAND\n"
            + "Example: " + COMMAND_WORD + " "
            + PREFIX_SHORTCUT + "lf "
            + PREFIX_COMMAND_WORD + "listf";
    public static final String MESSAGE_SUCCESS = "Alias for %1$s successfully defined as %2$s";

    private final Alias alias;

    /**
     * Creates {@code AddAliasCommand} object.
     */
    public AddAliasCommand(Alias alias) {
        requireNonNull(alias);
        this.alias = alias;
    }

    @Override
    public CommandResult execute(Model model) throws CommandException {
        requireNonNull(model);
        model.addAlias(alias);
        return new CommandResult(String.format(MESSAGE_SUCCESS, alias.getCommandWord(), alias.getShortcut()));
    }

    @Override
    public boolean equals(Object obj) {
        return (obj == this)
                || (obj instanceof AddAliasCommand
                && alias.equals(((AddAliasCommand) obj).alias));
    }
}
