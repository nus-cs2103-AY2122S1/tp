package seedu.address.logic.commands;

import static java.util.Objects.requireNonNull;

import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.logic.parser.AddressBookParser;
import seedu.address.logic.parser.exceptions.ParseException;
import seedu.address.model.Model;

/**
 * Finds and lists all persons in address book whose name contains any of the argument keywords.
 * Keyword matching is case insensitive.
 */
public class ShortcutCommand extends Command {

    public static final String COMMAND_WORD = "sc";

    public static final String MESSAGE_USAGE = COMMAND_WORD + ": Calls the command tied to the keyphrase added by "
            + "addsc\n"
            + "Parameters: KEYPHRASE\n"
            + "Example: " + COMMAND_WORD + " a";

    public static final String COMMAND_UNKNOWN = "Command invalid form: %s\n%s";

    public static final String SHORTCUT_NOT_FOUND = "Command with keyword %s not found";

    public static final String COMMAND_EXECUTE_ERROR = "Command execute error: %s\n%s";

    private final String shortcut;
    private UndoCommand commandToUndo;

    /**
     * Creates a shortcut command with the key of shortcut to be called
     * @param shortcut key of the shortcut
     */
    public ShortcutCommand(String shortcut) {
        requireNonNull(shortcut);
        commandToUndo = new UndoCommand();
        commandToUndo.setPrevCommand(this);
        this.shortcut = shortcut;
    }

    @Override
    public CommandResult execute(Model model) throws CommandException {
        requireNonNull(model);
        String commandString = model.getShortcutFromKey(shortcut);
        try {
            if (commandString == null) {
                commandToUndo.setPrevCommand(null);
                throw new CommandException(String.format(SHORTCUT_NOT_FOUND, shortcut));
            }
            Command command = (new AddressBookParser()).parseCommand(commandString);
            try {
                return command.execute(model);
            } catch (CommandException ce) {
                commandToUndo.setPrevCommand(null);
                throw new CommandException(String.format(COMMAND_EXECUTE_ERROR, commandString, ce.getMessage()));
            }
        } catch (ParseException e) {
            commandToUndo.setPrevCommand(null);
            throw new CommandException(String.format(COMMAND_UNKNOWN, commandString, e.getMessage()));
        }
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || ((other instanceof ShortcutCommand) // instanceof handles nulls
                && (shortcut.equals(((ShortcutCommand) other).shortcut))); // state check
    }
}
