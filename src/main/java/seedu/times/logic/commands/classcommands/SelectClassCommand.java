package seedu.times.logic.commands.classcommands;

import java.util.List;

import seedu.times.commons.core.Messages;
import seedu.times.commons.core.index.Index;
import seedu.times.logic.commands.Command;
import seedu.times.logic.commands.CommandResult;
import seedu.times.logic.commands.exceptions.CommandException;
import seedu.times.model.Model;
import seedu.times.model.tuitionclass.TuitionClass;
import seedu.times.ui.TabName;

/**
 * Selects the class to view its students.
 */
public class SelectClassCommand extends Command {
    public static final String COMMAND_WORD = "class";
    public static final String MESSAGE_USAGE = COMMAND_WORD + ": Selects the appropriate class\n"
            + "Parameters: CLASS_INDEX\n"
            + "Example: " + COMMAND_WORD + " 1";
    public static final String INVALID_TAB = "This class doesn't exists.\n"
            + "You can only switch to indexes shown in the Classlist.";

    private final Index targetIndex;


    public SelectClassCommand(Index targetIndex) {
        this.targetIndex = targetIndex;
    }

    @Override
    public CommandResult execute(Model model) throws CommandException {
        List<TuitionClass> lastShownList = model.getFilteredTuitionClassList();

        if (targetIndex.getZeroBased() >= lastShownList.size()) {
            throw new CommandException(Messages.MESSAGE_INVALID_CLASS_DISPLAYED_INDEX);
        }

        TuitionClass tuitionClass = lastShownList.get(targetIndex.getZeroBased());

        // Switches the view to the class view and updates the class
        updateView(TabName.CLASSES);
        updateClass(targetIndex.getZeroBased());

        return new CommandResult("Viewing class " + tuitionClass.toString(),
                false, false);
    }

    @Override
    public boolean equals(Object o) {
        if (o == this) {
            return true;
        }

        if (!(o instanceof SelectClassCommand)) {
            return false;
        }

        SelectClassCommand other = ((SelectClassCommand) o);
        return other.targetIndex.equals(targetIndex);
    }
}
