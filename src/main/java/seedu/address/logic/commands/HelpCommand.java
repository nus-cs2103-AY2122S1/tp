package seedu.address.logic.commands;

import seedu.address.model.Model;

/**
 * Format full help instructions for every command for display.
 */
public class HelpCommand extends Command {

    public static final String COMMAND_WORD = "help";

    public static final String MESSAGE_USAGE = COMMAND_WORD + ": Shows program usage instructions.\n"
            + "Example: " + COMMAND_WORD;

    public static final String SHOWING_HELP_MESSAGE =
            "Try entering...\n"
            + "add <parameters>                 " + "\tAdd a contact\n"
            + "alias <parameters>               " + "\tCreate command shortcut\n"
            + "clear                            " + "\t\t\tClear all contacts\n"
            + "delete <index>                   " + "\t\tDelete a contact by index\n"
            + "deletem <parameters>             " + "\tDelete contacts by keywords\n"
            + "edit <index>                     " + "\t\tEdit a contact by index\n"
            + "exit                             " + "\t\t\tQuit bot\n"
            + "export <file_name.json>          " + "\tExport contacts\n"
            + "find <parameters>                " + "\tFind contacts by keywords\n"
            + "help                             " + "\t\t\tShow all available commands\n"
            + "import <file_name.json>          " + "\tImport contacts\n"
            + "list                             " + "\t\t\tList all contacts\n"
            + "remark <index>                   " + "\t\tAdd a remark by index\n"
            + "sort <parameters>                " + "\tSort contacts by condition\n"
            + "stat <parameters>                " + "\t\tShow gender and nationality statistics";

    @Override
    public CommandResult execute(Model model) {
        return new CommandResult(SHOWING_HELP_MESSAGE, true, false);
    }
}
