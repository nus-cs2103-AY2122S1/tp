package seedu.address.logic.commands;

import static java.util.Objects.requireNonNull;
import static seedu.address.commons.util.CollectionUtil.requireAllNonNull;
import static seedu.address.logic.parser.CliSyntax.PREFIX_ALIAS;
import static seedu.address.logic.parser.CliSyntax.PREFIX_COMMAND_WORD;

import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.model.Model;

/**
 * Adds a user-defined alias to SportsPA user preferences.
 */
public class AddAliasCommand extends Command {

    public static final String COMMAND_WORD = "alias";
    public static final String MESSAGE_USAGE = COMMAND_WORD + ": creates an alias for the specified command word.\n"
            + "Parameters: "
            + PREFIX_ALIAS + "ALIAS "
            + PREFIX_COMMAND_WORD + "COMMAND\n"
            + "Example: " + COMMAND_WORD + " "
            + PREFIX_ALIAS + "lf "
            + PREFIX_COMMAND_WORD + "listf";
    public static final String MESSAGE_INVALID_COMMAND_WORD = "This command is unknown. Alias cannot be created.";
    public static final String MESSAGE_SUCCESS = "Alias for %1$s successfully defined as %2$s";

    private final String alias;
    private final String commandWord;

    public AddAliasCommand(String alias, String commandWord) {
        requireAllNonNull(alias, commandWord);
        this.alias = alias;
        this.commandWord = commandWord;
    }

    @Override
    public CommandResult execute(Model model) throws CommandException {
        requireNonNull(model);

        // Check if command word exists
        if (!isValidCommand(commandWord)) {
            throw new CommandException(MESSAGE_INVALID_COMMAND_WORD);
        }

        model.addAlias(alias, commandWord);
        return new CommandResult(String.format(MESSAGE_SUCCESS, commandWord, alias));
    }

    @Override
    public boolean equals(Object obj) {
        return (obj == this)
                || (obj instanceof AddAliasCommand
                && commandWord.equals(((AddAliasCommand) obj).commandWord)
                && alias.equals(((AddAliasCommand) obj).alias));
    }

    private boolean isValidCommand(String commandWord) {
        return Command.isCommand(commandWord);
    }
}
