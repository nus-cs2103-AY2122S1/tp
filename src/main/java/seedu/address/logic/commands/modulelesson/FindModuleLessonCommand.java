package seedu.address.logic.commands.modulelesson;

import static java.util.Objects.requireNonNull;
import static seedu.address.logic.parser.CliSyntax.PREFIX_LESSON_DAY;
import static seedu.address.logic.parser.CliSyntax.PREFIX_LESSON_TIME;
import static seedu.address.logic.parser.CliSyntax.PREFIX_MODULE_CODE;
import static seedu.address.logic.parser.CommandWord.FIND_MODULE_LESSON;
import static seedu.address.logic.parser.CommandWord.getAliasList;

import java.util.function.Predicate;

import seedu.address.commons.core.Messages;
import seedu.address.logic.commands.Command;
import seedu.address.logic.commands.CommandResult;
import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.model.Model;
import seedu.address.model.modulelesson.LessonDayContainsKeywordsPredicate;
import seedu.address.model.modulelesson.LessonTimeContainsKeywordsPredicate;
import seedu.address.model.modulelesson.ModuleCodeContainsKeywordsPredicate;
import seedu.address.model.modulelesson.ModuleLesson;

/**
 * Finds module lesson in contHACKS.
 */
public class FindModuleLessonCommand extends Command {

    public static final String COMMAND_WORD = getAliasList(FIND_MODULE_LESSON).get(0);
    public static final String MESSAGE_USAGE = COMMAND_WORD + ": Finds all lesson whose details contain "
            + "any of the specified keywords (case-insensitive).\n"
            + "You can choose one of three ways to search:\n"
            + "1) search by module code(s) using the prefix '" + PREFIX_MODULE_CODE
            + "' e.g. " + COMMAND_WORD + " " + PREFIX_MODULE_CODE + " CS2030S CS2100\n"
            + "2) search by lesson day(s) using the prefix '" + PREFIX_LESSON_DAY
            + "' e.g. " + COMMAND_WORD + " " + PREFIX_LESSON_DAY + "2\n"
            + "3) search by lesson start time(s) using the prefix '" + PREFIX_LESSON_TIME
            + "' e.g. " + COMMAND_WORD + " " + PREFIX_LESSON_TIME + "14:00\n";
    public static final String MESSAGE_SINGLE_PREFIX_SEARCH = "You can only search with a single prefix.";
    public static final String MESSAGE_INVALID_DAY = "Input day should only be between 1 (Monday) and 7 (Sunday).";
    public static final String MESSAGE_INVALID_TIME = "Input time is in an invalid format!"
            + "It must be in the HH:mm format.";

    private final Predicate<ModuleLesson> predicate;

    public FindModuleLessonCommand(ModuleCodeContainsKeywordsPredicate predicate) {
        this.predicate = predicate;
    }

    public FindModuleLessonCommand(LessonDayContainsKeywordsPredicate predicate) {
        this.predicate = predicate;
    }

    public FindModuleLessonCommand(LessonTimeContainsKeywordsPredicate predicate) {
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
                String.format(Messages.MESSAGE_LESSONS_LISTED_OVERVIEW, model.getFilteredModuleLessonList().size()));
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof FindModuleLessonCommand // instanceof handles nulls
                && predicate.equals(((FindModuleLessonCommand) other).predicate)); // state check
    }
}
