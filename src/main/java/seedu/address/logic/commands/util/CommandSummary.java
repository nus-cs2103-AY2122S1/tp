package seedu.address.logic.commands.util;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import seedu.address.logic.commands.AddCommand;
import seedu.address.logic.commands.ClearCommand;
import seedu.address.logic.commands.DeleteCommand;
import seedu.address.logic.commands.EditCommand;
import seedu.address.logic.commands.ExitCommand;
import seedu.address.logic.commands.FindCommand;
import seedu.address.logic.commands.HelpCommand;
import seedu.address.logic.commands.LessonAddCommand;
import seedu.address.logic.commands.LessonDeleteCommand;
import seedu.address.logic.commands.ListCommand;

/**
 * Wrapper class for command usage summary with the respective command action, format and example.
 */
public class CommandSummary {
    /** Description of what the command does. */
    private String action;

    /** Format of a valid command. */
    private String format;

    /** Example of a valid command. */
    private String example;

    /**
     * Constructs a CommandSummary object.
     *
     * @param action Description of what the command does.
     * @param format Format of a valid command.
     * @param example Example of a valid command.
     */
    public CommandSummary(String action, String format, String example) {
        this.action = action;
        this.format = format;
        this.example = example;
    }

    /**
     * Returns the description of what the command does.
     *
     * @return Description of what the command does.
     */
    public String getAction() {
        return action;
    }

    /**
     * Returns the format of a valid command.
     *
     * @return Format of a valid command.
     */
    public String getFormat() {
        return format;
    }

    /**
     * Returns the example of a valid command.
     *
     * @return Example of a valid command.
     */
    public String getExample() {
        return example;
    }

    /**
     * Creates and returns an observable list of command usage with action, format and example.
     *
     * @return An observable list of command usage.
     */
    public static ObservableList<CommandSummary> getCommandSummaryList() {
        return FXCollections.observableArrayList(
                new CommandSummary(HelpCommand.COMMAND_ACTION, HelpCommand.COMMAND_WORD,
                        HelpCommand.COMMAND_WORD),
                new CommandSummary(AddCommand.COMMAND_ACTION, AddCommand.COMMAND_FORMAT,
                        AddCommand.COMMAND_PARAMETERS),
                new CommandSummary(EditCommand.COMMAND_ACTION, EditCommand.COMMAND_FORMAT,
                        EditCommand.COMMAND_PARAMETERS),
                new CommandSummary(DeleteCommand.COMMAND_ACTION, DeleteCommand.COMMAND_FORMAT,
                        DeleteCommand.COMMAND_EXAMPLE),
                new CommandSummary(FindCommand.COMMAND_ACTION, FindCommand.COMMAND_FORMAT,
                        FindCommand.COMMAND_EXAMPLE),
                new CommandSummary(ListCommand.COMMAND_ACTION, ListCommand.COMMAND_WORD,
                        ListCommand.COMMAND_WORD),
                new CommandSummary(LessonAddCommand.COMMAND_ACTION, LessonAddCommand.COMMAND_FORMAT,
                        LessonAddCommand.COMMAND_EXAMPLE_RECURRING_LESSON),
                new CommandSummary(LessonDeleteCommand.COMMAND_ACTION, LessonDeleteCommand.COMMAND_FORMAT,
                        LessonDeleteCommand.COMMAND_EXAMPLE),
                new CommandSummary(ClearCommand.COMMAND_ACTION, ClearCommand.COMMAND_WORD,
                        ClearCommand.COMMAND_WORD),
                new CommandSummary(ExitCommand.COMMAND_ACTION, ExitCommand.COMMAND_WORD,
                        ExitCommand.COMMAND_WORD));
    }
}
