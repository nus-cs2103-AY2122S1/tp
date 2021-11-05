package seedu.address.logic.commands.modulelesson;

import static java.util.Objects.requireNonNull;
import static seedu.address.logic.parser.CliSyntax.PREFIX_LESSON_DAY;
import static seedu.address.logic.parser.CliSyntax.PREFIX_LESSON_TIME;
import static seedu.address.logic.parser.CliSyntax.PREFIX_MODULE_CODE;
import static seedu.address.logic.parser.CliSyntax.PREFIX_REMARK;

import seedu.address.logic.commands.Command;
import seedu.address.logic.commands.CommandResult;
import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.model.Model;
import seedu.address.model.modulelesson.ModuleLesson;

/**
 * Adds a module lesson to contHACKS.
 */
public class AddModuleLessonCommand extends Command {

    public static final String MESSAGE_USAGE = "addc: Adds a lesson to contHACKS.\n"
            + "Parameters: "
            + PREFIX_MODULE_CODE + "MODULE_CODE LESSON_CODE "
            + PREFIX_LESSON_DAY + "DAY "
            + PREFIX_LESSON_TIME + "START_TIME END_TIME "
            + "[" + PREFIX_REMARK + "REMARK]\n"
            + "Example: addc "
            + PREFIX_MODULE_CODE + "CS2103T T12 "
            + PREFIX_LESSON_DAY + "4 "
            + PREFIX_LESSON_TIME + "09:00 10:00 "
            + PREFIX_REMARK + "Online\n";

    public static final String MESSAGE_SUCCESS = "New lesson added: %1$s";
    public static final String MESSAGE_DUPLICATE_LESSON = "Unable to add: A lesson with the same module code and"
            + " lesson code already exists in contHACKS";
    public static final String MESSAGE_OVERLAPPING_LESSON = "Warning: Another lesson with overlapping timings exists.\n"
            + "New lesson added: %1$s";

    private final ModuleLesson toAdd;

    /**
     * Creates an AddModuleLessonCommand to add the specified {@code ModuleLesson}.
     */
    public AddModuleLessonCommand(ModuleLesson moduleLesson) {
        requireNonNull(moduleLesson);
        toAdd = moduleLesson;
    }

    @Override
    public CommandResult execute(Model model) throws CommandException {
        requireNonNull(model);

        if (model.hasModuleLesson(toAdd)) {
            throw new CommandException(MESSAGE_DUPLICATE_LESSON);
        }

        CommandResult result;
        if (model.hasModuleLessonClashingWith(toAdd)) {
            result = new CommandResult(String.format(MESSAGE_OVERLAPPING_LESSON, toAdd));
        } else {
            result = new CommandResult(String.format(MESSAGE_SUCCESS, toAdd));
        }

        model.addLesson(toAdd);
        return result;
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof AddModuleLessonCommand // instanceof handles nulls
                && toAdd.equals(((AddModuleLessonCommand) other).toAdd));
    }
}
