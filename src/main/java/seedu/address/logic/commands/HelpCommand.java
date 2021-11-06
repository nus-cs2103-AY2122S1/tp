package seedu.address.logic.commands;

import static java.util.Objects.requireNonNull;

import java.util.Hashtable;

import seedu.address.model.Model;

/**
 * Format full help instructions for every command for display.
 */
public class HelpCommand extends Command {

    public static final String COMMAND_WORD = "man";

    public static final String DESCRIPTION = "Shows program usage instructions.";

    public static final String MESSAGE_USAGE = COMMAND_WORD
            + ": " + DESCRIPTION + "\n"
            + "Parameters: " + "[COMMAND]\n"
            + "Example: " + COMMAND_WORD + " add";


    public static final String SHOWING_HELP_MESSAGE = "Opened help window.";

    private static final Hashtable<String, CommandWordParser> commandTable = new Hashtable<>();

    private boolean isShowHelp = false;

    private String commandWord = null;

    private interface CommandWordParser {
        CommandResult parse();
    }

    /**
     * Constructor for a HelpCommand to show help window.
     */
    public HelpCommand() {
        isShowHelp = true;
        if (commandTable.isEmpty()) {
            fillCommandWordTable();
        }
    }

    /**
     * Constructor for a HelpCommand to show help for a specified command.
     *
     * @param commandSpecified The command specified to show help for.
     */
    public HelpCommand(String commandSpecified) {
        this.commandWord = commandSpecified;
        if (commandTable.isEmpty()) {
            fillCommandWordTable();
        }
    }

    @Override
    public CommandResult execute(Model model) {
        if (isShowHelp) {
            return new CommandResult(SHOWING_HELP_MESSAGE, true, false);
        } else {
            requireNonNull(commandWord);
            return parseCommandWord(commandWord);
        }
    }

    private void fillCommandWordTable() {
        commandTable.put(AddCommand.COMMAND_WORD, this::parseAdd);
        commandTable.put(ClearCommand.COMMAND_WORD, this::parseClear);
        commandTable.put(DeleteCommand.COMMAND_WORD, this::parseDelete);
        commandTable.put(DoneCommand.COMMAND_WORD, this::parseDone);
        commandTable.put(EditCommand.COMMAND_WORD, this::parseEdit);
        commandTable.put(ExitCommand.COMMAND_WORD, this::parseExit);
        commandTable.put(FindCommand.COMMAND_WORD, this::parseFind);
        commandTable.put(HelpCommand.COMMAND_WORD, this::parseHelp);
        commandTable.put(ListCommand.COMMAND_WORD, this::parseList);
        commandTable.put(ReminderCommand.COMMAND_WORD, this::parseReminder);
        commandTable.put(SortCommand.COMMAND_WORD, this::parseSort);
        commandTable.put(UndoCommand.COMMAND_WORD, this::parseUndo);
        commandTable.put(ViewTaskListCommand.COMMAND_WORD, this::parseViewTaskList);
    }

    /**
     * Parses the given commandWord.
     *
     * @param commandWord The specified commandWord.
     * @return CommandResult that contains information to display to the user.
     */
    public CommandResult parseCommandWord(String commandWord) {
        CommandResult defaultCommandResult = new CommandResult(DESCRIPTION);
        if (commandTable.containsKey(commandWord)) {
            return commandTable.get(commandWord).parse();
        }
        return defaultCommandResult;
    }

    public CommandResult parseAdd() {
        return new CommandResult(AddCommand.MESSAGE_USAGE);
    }

    public CommandResult parseClear() {
        return new CommandResult(ClearCommand.MESSAGE_USAGE);
    }

    public CommandResult parseDelete() {
        return new CommandResult(DeleteCommand.MESSAGE_USAGE);
    }

    public CommandResult parseDone() {
        return new CommandResult(DoneCommand.MESSAGE_USAGE);
    }

    public CommandResult parseEdit() {
        return new CommandResult(EditCommand.MESSAGE_USAGE);
    }

    public CommandResult parseExit() {
        return new CommandResult(ExitCommand.MESSAGE_USAGE);
    }

    public CommandResult parseFind() {
        return new CommandResult(FindCommand.MESSAGE_USAGE);
    }

    public CommandResult parseHelp() {
        return new CommandResult(HelpCommand.MESSAGE_USAGE);
    }

    public CommandResult parseList() {
        return new CommandResult(ListCommand.MESSAGE_USAGE);
    }

    public CommandResult parseReminder() {
        return new CommandResult(ReminderCommand.MESSAGE_USAGE);
    }

    public CommandResult parseSort() {
        return new CommandResult(SortCommand.MESSAGE_USAGE);
    }

    public CommandResult parseUndo() {
        return new CommandResult(UndoCommand.MESSAGE_USAGE);
    }

    public CommandResult parseViewTaskList() {
        return new CommandResult(ViewTaskListCommand.MESSAGE_USAGE);
    }

    @Override
    public boolean equals(Object other) {
        if (other instanceof HelpCommand) {
            if (commandWord == null && ((HelpCommand) other).commandWord != null || commandWord != null
                    && ((HelpCommand) other).commandWord == null) {
                return false;
            } else if (commandWord == null) {
                return other == this || ((HelpCommand) other).commandWord == null
                        && isShowHelp == ((HelpCommand) other).isShowHelp;
            } else {
                return other == this || ((HelpCommand) other).commandWord != null
                        && commandWord.equals(((HelpCommand) other).commandWord)
                        && isShowHelp == ((HelpCommand) other).isShowHelp;
            }
        }
        return false;
    }
}
