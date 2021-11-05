package seedu.address.logic.commands.modulelesson;

import static java.util.Objects.requireNonNull;
import static seedu.address.model.Model.PREDICATE_SHOW_ALL_LESSONS;

import seedu.address.logic.commands.Command;
import seedu.address.logic.commands.CommandResult;
import seedu.address.model.Model;

/**
 * Lists all module lessons in contHACKS to the user.
 */
public class ListModuleLessonCommand extends Command {

    public static final String MESSAGE_SUCCESS = "Listed all lessons";

    @Override
    public CommandResult execute(Model model) {
        requireNonNull(model);
        model.updateFilteredModuleLessonList(PREDICATE_SHOW_ALL_LESSONS);
        return new CommandResult(MESSAGE_SUCCESS);
    }
}
