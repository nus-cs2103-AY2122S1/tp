package seedu.academydirectory.logic;

import seedu.academydirectory.logic.commands.Command;
import seedu.academydirectory.logic.commands.GetCommand;
import seedu.academydirectory.logic.commands.HelpCommand;
import seedu.academydirectory.logic.commands.HistoryCommand;
import seedu.academydirectory.logic.commands.ShowCommand;
import seedu.academydirectory.logic.commands.ViewCommand;
import seedu.academydirectory.logic.commands.VisualizeCommand;


public enum AdditionalViewType {
    DEFAULT, HELP, VIEW, EXIT, VISUALIZE, HISTORY, GET, SHOW;

    /**
     * Parse the type of additional view being returned to
     * @param command name of command
     * @return type of command
     */
    public static AdditionalViewType parse(Command command) {
        if (command instanceof ViewCommand) {
            return AdditionalViewType.VIEW;
        }
        if (command instanceof VisualizeCommand) {
            return AdditionalViewType.VISUALIZE;
        }
        if (command instanceof HistoryCommand) {
            return AdditionalViewType.HISTORY;
        }
        if (command instanceof HelpCommand) {
            return AdditionalViewType.HELP;
        }
        if (command instanceof GetCommand) {
            return AdditionalViewType.GET;
        }
        if (command instanceof ShowCommand) {
            return AdditionalViewType.SHOW;
        }
        return AdditionalViewType.DEFAULT;
    }
}
