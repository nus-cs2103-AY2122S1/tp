package seedu.academydirectory.logic;

import seedu.academydirectory.logic.commands.Command;
import seedu.academydirectory.logic.commands.ViewCommand;
import seedu.academydirectory.logic.commands.VisualizeCommand;


public enum AdditionalViewType {
    DEFAULT, HELP, VIEW, EXIT, VISUALIZE;

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
        return AdditionalViewType.DEFAULT;
    }
}
