package seedu.address.logic.commands;

import static java.util.Objects.requireNonNull;
import static seedu.address.logic.parser.CliSyntax.PREFIX_ALIAS;
import static seedu.address.logic.parser.CliSyntax.PREFIX_COMMAND;

import java.util.HashMap;

import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.model.Model;

/**
 * Adds specified alias to the parser.
 */
public class AliasCommand extends Command {

    public static final String COMMAND_WORD = "alias";
    public static final String MESSAGE_SUCCESS = "Alias \"%1$s\" = \"%2$s\" has been added!";
    public static final String MESSAGE_USAGE = COMMAND_WORD
            + ": Adds an alias to the system.\n"
            + "Parameters: "
            + PREFIX_ALIAS + "ALIAS "
            + PREFIX_COMMAND + "COMMAND "
            + "Example: " + COMMAND_WORD + " "
            + PREFIX_ALIAS + "l "
            + PREFIX_COMMAND + "list";

    private final String alias;
    private final String command;
    private HashMap<String, String> aliasMap;

    /**
     * Creates an AliasCommand to add the specified alias, command pair to the alias map.
     */
    public AliasCommand(String alias, String command) {
        this.alias = alias;
        this.command = command;
    }

    /**
     * Set the Alias Map of the command.
     *
     * @param aliasMap Hashmap of alias in the parser
     * @return AliasCommand with aliasMap set
     */
    public Command setMap(HashMap<String, String> aliasMap) {
        this.aliasMap = aliasMap;
        return this;
    }

    @Override
    public CommandResult execute(Model model) throws CommandException {
        requireNonNull(aliasMap);
        aliasMap.put(alias, command);
        return new CommandResult(String.format(MESSAGE_SUCCESS, alias, command));
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof AliasCommand // instanceof handles nulls
                && alias.equals(((AliasCommand) other).alias)
                && command.equals(((AliasCommand) other).command)); // state check
    }
}
