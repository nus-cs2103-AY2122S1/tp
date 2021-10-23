package seedu.address.logic.commands.modulelesson;

import seedu.address.commons.core.Messages;
import seedu.address.logic.commands.Command;
import seedu.address.logic.commands.CommandResult;
import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.model.Model;
import seedu.address.model.modulelesson.LessonDayContainsKeywordsPredicate;
import seedu.address.model.modulelesson.ModuleLesson;
import seedu.address.model.modulelesson.ModuleCodeContainsKeywordsPredicate;

import java.util.function.Predicate;

import static java.util.Objects.requireNonNull;
import static seedu.address.logic.parser.CliSyntax.PREFIX_LESSON_DAY;
import static seedu.address.logic.parser.CliSyntax.PREFIX_LESSON_TIME;
import static seedu.address.logic.parser.CliSyntax.PREFIX_MODULE_CODE;

public class FindModuleLessonCommand extends Command {

    public static final String MESSAGE_USAGE = "find: Finds all lesson whose details contain "
            + "any of the specified keywords (case-insensitive).\n"
            + "You can choose one of three ways to search:\n"
            + "1) search by module code(s) using the prefix " + PREFIX_MODULE_CODE
            + "find " + PREFIX_MODULE_CODE + "CS2030S CS2100\n"
            + "2) search by lesson day(s) using the prefix " + PREFIX_LESSON_DAY
            + "find " + PREFIX_LESSON_DAY + "2\n"
            + "3) search by lesson time(s) using the prefix " + PREFIX_LESSON_TIME
            + "find " + PREFIX_LESSON_TIME + "CS2030S CS2100\n";
    public static final String MESSAGE_SINGLE_PREFIX_SEARCH = "You can only search with a single prefix.";

    private final Predicate<ModuleLesson> predicate;

    public FindModuleLessonCommand(ModuleCodeContainsKeywordsPredicate predicate) {
        this.predicate = predicate;
    }

    public FindModuleLessonCommand(LessonDayContainsKeywordsPredicate predicate) {
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
    public CommandResult execute(Model model) {
        requireNonNull(model);
        model.updateFilteredModuleLessonList(predicate);
        return new CommandResult(
                String.format(Messages.MESSAGE_PERSONS_LISTED_OVERVIEW, model.getFilteredModuleLessonList().size()));
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof FindModuleLessonCommand // instanceof handles nulls
                && predicate.equals(((FindModuleLessonCommand) other).predicate)); // state check
    }
}
