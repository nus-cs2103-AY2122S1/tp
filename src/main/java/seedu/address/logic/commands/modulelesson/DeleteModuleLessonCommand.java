package seedu.address.logic.commands.modulelesson;

import static seedu.address.logic.parser.CliSyntax.PREFIX_MODULE_CODE;

import java.util.function.Predicate;

import seedu.address.commons.core.index.Index;
import seedu.address.logic.commands.Command;
import seedu.address.logic.commands.CommandResult;
import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.model.Model;
import seedu.address.model.modulelesson.ModuleCodeContainsKeywordsPredicate;
import seedu.address.model.modulelesson.ModuleLesson;

/**
 * Deletes module lesson in contHACKS.
 */
public class DeleteModuleLessonCommand extends Command {

    public static final String MESSAGE_USAGE = "deletec: "
            + "Deletes the Lesson identified by the index number used in the displayed Lesson list "
            + "or by module code \n"
            + "Ensure that INDEX is a positive integer and exists in ContHACKS.\n"
            + "Example: deletec 1, deletec 1-3, deletec m/CS2040\n"
            + "Click on the Help button at the top for more detailed information";

    public static final String MESSAGE_NUMBER_DELETED_LESSONS = "%d Deleted Lessons: \n";
    public static final String MESSAGE_DELETE_LESSON_SUCCESS = "%1$s \n";
    public static final String MESSAGE_DELETE_BY_MODULE_CODE_USAGE = "deletec: "
            + "Delete only accepts one batch delete by Module Code at a time\n"
            + "Example: deletec " + PREFIX_MODULE_CODE + "CS2040S";
    public static final String MESSAGE_NO_SUCH_MODULE_CODE = "No such Module Code";
    public static final String MESSAGE_INVALID_FORMAT = "Invalid command format! \n";

    private final Index targetIndex;
    private final Index endIndex;
    private final Predicate<ModuleLesson> predicate;

    /**
     * Creates a DeleteModuleLessonCommand to delete lesson at specified index
     *
     * @param targetIndex the lesson to be deleted
     */
    public DeleteModuleLessonCommand(Index targetIndex) {
        this.targetIndex = targetIndex;
        endIndex = targetIndex;
        predicate = Model.PREDICATE_SHOW_ALL_LESSONS;
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
        predicate = Model.PREDICATE_SHOW_ALL_LESSONS;
    }

    /**
     * Creates a DeleteModuleLessonCommand to delete lessons with the specified predicate
     *
     * @param predicate condition to delete lesson
     */
    public DeleteModuleLessonCommand(ModuleCodeContainsKeywordsPredicate predicate) {
        targetIndex = Index.fromZeroBased(0);
        endIndex = Index.fromZeroBased(0);
        this.predicate = predicate;
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
        String successMessage;
        if (predicate != Model.PREDICATE_SHOW_ALL_LESSONS) {
            model.updateFilteredModuleLessonList(predicate);
            successMessage = deleteByModuleCode(model);
            model.updateFilteredModuleLessonList(Model.PREDICATE_SHOW_ALL_LESSONS);
        } else if (targetIndex.getZeroBased() >= sizeOfModuleLessonList) {
            throw new CommandException(MESSAGE_INVALID_FORMAT + MESSAGE_USAGE);
        } else if (targetIndex.getZeroBased() > endIndex.getZeroBased()
                || endIndex.getZeroBased() >= sizeOfModuleLessonList) {
            throw new CommandException(MESSAGE_INVALID_FORMAT + MESSAGE_USAGE);
        } else {
            successMessage = deleteAll(model, targetIndex.getZeroBased(), endIndex.getZeroBased());
        }
        return new CommandResult(successMessage);
    }

    private String deleteByModuleCode(Model model) throws CommandException {
        if (model.getFilteredModuleLessonList().isEmpty()) {
            model.updateFilteredModuleLessonList(Model.PREDICATE_SHOW_ALL_LESSONS);
            throw new CommandException(MESSAGE_NO_SUCH_MODULE_CODE);
        }
        return deleteAll(model, 0, model.getFilteredModuleLessonList().size() - 1);
    }

    private String deleteAll(Model model, int first, int last) {
        int deletedLessons = last - first + 1;
        StringBuilder successMessage = new StringBuilder();
        while (first <= last) {
            ModuleLesson moduleLessonToDelete = model.getFilteredModuleLessonList().get(last);
            successMessage.insert(0, String.format(MESSAGE_DELETE_LESSON_SUCCESS, moduleLessonToDelete));
            model.deleteLesson(moduleLessonToDelete);
            last--;
        }
        return String.format(MESSAGE_NUMBER_DELETED_LESSONS, deletedLessons) + successMessage;
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof DeleteModuleLessonCommand // instanceof handles nulls
                && targetIndex.equals(((DeleteModuleLessonCommand) other).targetIndex)
                && endIndex.equals(((DeleteModuleLessonCommand) other).endIndex)
                && predicate.equals(((DeleteModuleLessonCommand) other).predicate)); // state check
    }
}
