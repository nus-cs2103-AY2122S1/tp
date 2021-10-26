package seedu.academydirectory.logic;

import seedu.academydirectory.logic.commands.Command;
import seedu.academydirectory.logic.commands.HistoryCommand;
import seedu.academydirectory.logic.commands.ViewCommand;
import seedu.academydirectory.logic.commands.VisualizeCommand;


public enum AdditionalViewType {
    DEFAULT, HELP, VIEW, EXIT, VISUALISE, HISTORY;

    /**
     *
     * @param command
     * @return
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
        return AdditionalViewType.DEFAULT;
    }
}
