package seedu.academydirectory.logic;

import seedu.academydirectory.logic.commands.Command;
import seedu.academydirectory.logic.commands.ViewCommand;
import seedu.academydirectory.logic.commands.VisualiseCommand;


public enum AdditionalViewType {
    DEFAULT, HELP, VIEW, EXIT, VISUALISE;

    /**
     *
     * @param command
     * @return
     */
    public static AdditionalViewType parse(Command command) {
        if (command instanceof ViewCommand) {
            return AdditionalViewType.VIEW;
        }
        if (command instanceof VisualiseCommand) {
            return AdditionalViewType.VISUALISE;
        }
        return AdditionalViewType.DEFAULT;
    }
}
