package seedu.address.logic.commands.modulelesson;

import seedu.address.commons.core.Messages;
import seedu.address.commons.core.index.Index;
import seedu.address.logic.commands.Command;
import seedu.address.logic.commands.CommandResult;
import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.logic.commands.person.DeletePersonCommand;
import seedu.address.model.Model;
import seedu.address.model.modulelesson.ModuleLesson;

public class DeleteModuleLessonCommand extends Command {

    public static final String MESSAGE_USAGE = "delete: "
            + "Deletes the Lesson identified by the index number used in the displayed Lesson list "
            + "Parameters: INDEX (must be a positive integer) "
            + "Example: deletec 1, deletec 1-3";

    public static final String MESSAGE_NUMBER_DELETED_LESSONS = "%d Deleted Lessons: \n";
    public static final String MESSAGE_DELETE_LESSON_SUCCESS = "%1$s \n";

    private final Index targetIndex;
    private final Index endIndex;

    /**
     * Creates a DeleteModuleLessonCommand to delete lesson at specified index
     *
     * @param targetIndex the lesson to be deleted
     */
    public DeleteModuleLessonCommand(Index targetIndex) {
        this.targetIndex = targetIndex;
        endIndex = targetIndex;
    }

    /**
     * Creates a DeleteModuleLessonCommand to delete lessons in a specified range
     *
     * @param targetIndex the lesson with the smallest index
     * @param endIndex the lesson with the largest index
     */
    public DeleteModuleLessonCommand(Index targetIndex, Index endIndex) {
        this.targetIndex = targetIndex;
        this.endIndex = endIndex;
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
        int sizeOfModuleLessonList = model.getFilteredModuleLessonList().size();
        if (targetIndex.getZeroBased() >= sizeOfModuleLessonList) {
            throw new CommandException(Messages.MESSAGE_INVALID_PERSON_DISPLAYED_INDEX);
        }
        if (targetIndex.getZeroBased() > endIndex.getZeroBased() || endIndex.getZeroBased() >= sizeOfModuleLessonList) {
            throw new CommandException(Messages.MESSAGE_INVALID_RANGE);
        }
        String successMessage = deleteAll(model);
        return new CommandResult(successMessage);
    }

    private String deleteAll(Model model) {
        int first = targetIndex.getZeroBased();
        int last = endIndex.getZeroBased();
        StringBuilder successMessage = new StringBuilder();
        while (first <= last) {
            ModuleLesson moduleLessonToDelete = model.getFilteredModuleLessonList().get(last);
            successMessage.insert(0, String.format(MESSAGE_DELETE_LESSON_SUCCESS, moduleLessonToDelete));
            model.deleteLesson(moduleLessonToDelete);
            last--;
        }
        return String.format(MESSAGE_NUMBER_DELETED_LESSONS, last - first + 1) + successMessage;
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof DeleteModuleLessonCommand // instanceof handles nulls
                && targetIndex.equals(((DeleteModuleLessonCommand) other).targetIndex)
                && endIndex.equals(((DeleteModuleLessonCommand) other).endIndex)); // state check
    }
}
