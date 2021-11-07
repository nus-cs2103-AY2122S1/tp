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
            "Command                            " + "\t\tDescription\n"
                    + "\nModify:\n"
                    + "add <parameters>                 " + "\t\tAdd a contact\n"
                    + "addt <index> <parameters>        " + "\tAdd tags to contacts\n"
                    + "remark <index> <parameters>   " + "\tAdd a remark by index\n"
                    + "edit <index> <parameters>        " + "\tEdit a contact by index\n"
                    + "delete <index>                   " + "\t\tDelete a contact by index\n"
                    + "deletem <parameters>             " + "\tDelete contacts by keywords\n"
                    + "deletet <index> <parameters>" + "\tDelete tags from contacts\n"
                    + "clear                            " + "\t\t\tClear all contacts\n"
                    + "\nView:\n"
                    + "list                             " + "\t\t\tList all contacts\n"
                    + "find <parameters>                " + "\t\tFind contacts by keywords\n"
                    + "sort <parameters>                " + "\t\tSort contacts by condition\n"
                    + "stat <parameters>                " + "\t\tShow gender and nationality statistics\n"
                    + "\nShare:\n"
                    + "import <file_name.json>          " + "\tImport contacts\n"
                    + "export <file_name.json>          " + "\tExport contacts\n"
                    + "\nAdvance:\n"
                    + "alias <parameters>               " + "\t\tCreate command shortcut\n"
                    + "\nGeneral:\n"
                    + "help                             " + "\t\t\tShow all available commands\n"
                    + "exit                             " + "\t\t\tQuit bot";

    @Override
    public CommandResult execute(Model model) {
        return new CommandResult(SHOWING_HELP_MESSAGE, true, false);
    }
}
