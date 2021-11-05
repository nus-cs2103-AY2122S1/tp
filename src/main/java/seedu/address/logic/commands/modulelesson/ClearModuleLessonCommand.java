package seedu.address.logic.commands.modulelesson;

import static java.util.Objects.requireNonNull;

import seedu.address.logic.commands.Command;
import seedu.address.logic.commands.CommandResult;
import seedu.address.model.Model;

/**
 * Clears all the module lessons in contHACKS.
 */
public class ClearModuleLessonCommand extends Command {

    public static final String MESSAGE_SUCCESS = "The lessons in contHACKS has been cleared!";

    @Override
    public CommandResult execute(Model model) {
        requireNonNull(model);
        model.clearLessons();
        assert model.getConthacks().getModuleLessonList().isEmpty();
        return new CommandResult(MESSAGE_SUCCESS);
    }
}
