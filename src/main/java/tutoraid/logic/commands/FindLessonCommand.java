package tutoraid.logic.commands;

import static java.util.Objects.requireNonNull;

import tutoraid.commons.core.Messages;
import tutoraid.model.Model;
import tutoraid.model.lesson.LessonNameContainsSubstringsPredicate;

/**
 * Finds and lists all lessons in address book whose name contains any of the argument keywords.
 * Keyword matching is case-insensitive.
 */
public class FindLessonCommand extends FindCommand {

    public static final String COMMAND_FLAG = "-l";

    public static final String MESSAGE_USAGE = String.format("%1$s %2$s: Finds all lessons whose names contain any of "
                    + "the specified keywords (case-insensitive) and displays them as a list with index numbers."
                    + "\nParameters:"
                    + "\nKEYWORD [MORE KEYWORDS]"
                    + "\nExample:"
                    + "\n%1$s %2$s eng math sci",
            COMMAND_WORD, COMMAND_FLAG);

    private final LessonNameContainsSubstringsPredicate predicate;

    public FindLessonCommand(LessonNameContainsSubstringsPredicate predicate) {
        this.predicate = predicate;
    }

    @Override
    public CommandResult execute(Model model) {
        requireNonNull(model);
        model.updateFilteredLessonList(predicate);

        return new CommandResult(
                String.format(Messages.MESSAGE_LESSONS_LISTED_OVERVIEW, model.getFilteredLessonList().size()));
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof FindLessonCommand // instanceof handles nulls
                && predicate.equals(((FindLessonCommand) other).predicate)); // state check
    }
}

