package seedu.address.logic.commands.modulelesson;

import seedu.address.commons.core.Messages;
import seedu.address.commons.core.index.Index;
import seedu.address.logic.commands.Command;
import seedu.address.logic.commands.CommandResult;
import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.model.Model;
import seedu.address.model.modulelesson.ModuleLesson;

public class DeleteModuleLessonCommand extends Command {

    public static final String MESSAGE_USAGE = "delete: "
            + "Deletes the Lesson identified by the index number used in the displayed Lesson list "
            + "Parameters: INDEX (must be a positive integer) "
            + "Example: delete 1";

    public static final String MESSAGE_NUMBER_DELETED_LESSONS = "%d Deleted Lessons: \n";
    public static final String MESSAGE_DELETE_LESSON_SUCCESS = "%1$s \n";

    private final Index targetIndex;

    /**
     * Creates a DeleteModuleLessonCommand to delete the person at specified index
     *
     * @param targetIndex the person to be deleted
     */
    public DeleteModuleLessonCommand(Index targetIndex) {
        this.targetIndex = targetIndex;
    }

    /**
     * Executes the command and returns the result message.
     *
     * @param model {@code Model} which the command should operate on.
     * @return feedback message of the operation result for display
     * @throws CommandException If an error occurs during command execution.
     */
    @Override
    public CommandResult execute(Model model) throws CommandException {

        if (targetIndex.getZeroBased() >= model.getFilteredModuleLessonList().size()) {
            throw new CommandException(Messages.MESSAGE_INVALID_PERSON_DISPLAYED_INDEX);
        }
        String successMessage = deleteAll(model);
        return new CommandResult(successMessage);
    }

    private String deleteAll(Model model) {
        ModuleLesson moduleLessonToDelete = model.getFilteredModuleLessonList().get(targetIndex.getZeroBased());
        String successMessage = String.format(MESSAGE_DELETE_LESSON_SUCCESS, moduleLessonToDelete);
        model.deleteLesson(moduleLessonToDelete);

        return String.format(MESSAGE_NUMBER_DELETED_LESSONS, 1) + successMessage;
    }
}
