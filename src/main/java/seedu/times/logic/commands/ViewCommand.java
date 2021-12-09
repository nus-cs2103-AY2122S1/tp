package seedu.times.logic.commands;

import seedu.times.logic.commands.exceptions.CommandException;
import seedu.times.model.Model;
import seedu.times.ui.TabName;

/**
 * Sets the UI to View the tab selected.
 */
public class ViewCommand extends Command {

    public static final String COMMAND_WORD = "view";
    public static final String MESSAGE_USAGE = COMMAND_WORD + ": Switches tab to specified tab\n"
            + "Parameters: TAB_TO_VIEW\n"
            + "Example: " + COMMAND_WORD + " timetable";
    public static final String INVALID_TAB = "This tab doesn't exists.\n"
            + "You can only switch to students, timetable or classes.";

    private final TabName tabToView;

    /**
     * Creates a ViewCommand to view the specified tab index.
     */
    public ViewCommand(TabName tab) {
        this.tabToView = tab;
    }


    @Override
    public CommandResult execute(Model model) throws CommandException {

        updateView(tabToView);

        return new CommandResult("Successfully switched to " + tabToView + " tab",
                false, false);
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        } else if (obj instanceof ViewCommand) {
            ViewCommand viewCommand = (ViewCommand) obj;
            return this.tabToView == viewCommand.tabToView;
        } else {
            return false;
        }
    }

}
