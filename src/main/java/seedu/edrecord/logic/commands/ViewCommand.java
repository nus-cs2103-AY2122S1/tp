package seedu.edrecord.logic.commands;

import static java.util.Objects.requireNonNull;
import static seedu.edrecord.commons.core.Messages.MESSAGE_VIEW_CHANGED;

import java.util.Map;

import seedu.edrecord.logic.commands.exceptions.CommandException;
import seedu.edrecord.model.Model;
import seedu.edrecord.ui.PersonListPanel;

/**
 * Changes the selected view.
 */
public class ViewCommand extends Command {

    public static final String COMMAND_WORD = "view";
    public static final String MESSAGE_USAGE = COMMAND_WORD
            + ": Changes the currently selected view.\n"
            + "Parameters: [contacts/asg]\n"
            + "Example: " + COMMAND_WORD + " contacts";
    public static final String MESSAGE_NO_SUCH_VIEW = "View %s does not exist!";

    private static final Map<String, PersonListPanel.View> VIEWS = Map.of(
            "contacts", PersonListPanel.View.CONTACTS,
            "asg", PersonListPanel.View.ASSIGNMENTS
    );
    private final String selectedView;

    public ViewCommand(String selectedView) {
        this.selectedView = selectedView;
    }

    @Override
    public CommandResult execute(Model model) throws CommandException {
        requireNonNull(model);

        if (!VIEWS.containsKey(selectedView)) {
            throw new CommandException(String.format(MESSAGE_NO_SUCH_VIEW, selectedView));
        }

        model.setSelectedView(VIEWS.get(selectedView));
        return new CommandResult(String.format(MESSAGE_VIEW_CHANGED, selectedView));
    }


    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof ViewCommand // instanceof handles nulls
                && selectedView.equals(((ViewCommand) other).selectedView)); // state check
    }

}
