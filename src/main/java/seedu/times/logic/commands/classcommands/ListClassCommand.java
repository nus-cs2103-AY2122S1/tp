package seedu.times.logic.commands.classcommands;

import static java.util.Objects.requireNonNull;
import static seedu.times.model.Model.PREDICATE_SHOW_ALL_CLASS;

import seedu.times.logic.commands.Command;
import seedu.times.logic.commands.CommandResult;
import seedu.times.model.Model;
import seedu.times.ui.TabName;

/**
 * Lists all tuition classes in the Timestable to the user.
 */
public class ListClassCommand extends Command {

    public static final String COMMAND_WORD = "listclass";

    public static final String MESSAGE_SUCCESS = "Listed all classes";

    @Override
    public CommandResult execute(Model model) {
        requireNonNull(model);
        updateView(TabName.CLASSES);
        model.updateFilteredClassList(PREDICATE_SHOW_ALL_CLASS);
        return new CommandResult(MESSAGE_SUCCESS);
    }
}
