package seedu.tuitione.logic.commands;

import static java.util.Objects.requireNonNull;
import static seedu.tuitione.commons.core.Messages.HEADER_UPDATE;
import static seedu.tuitione.commons.core.Messages.MESSAGE_NO_STUDENTS_FOUND_OVERVIEW;
import static seedu.tuitione.commons.core.Messages.MESSAGE_STUDENTS_FOUND_OVERVIEW;

import seedu.tuitione.model.Model;
import seedu.tuitione.model.student.NameContainsKeywordsPredicate;

/**
 * Finds and lists all students in tuitione book whose name contains any of the argument keywords.
 * Keyword matching is case insensitive.
 */
public class FindCommand extends Command {

    public static final String COMMAND_WORD = "find";

    public static final String MESSAGE_USAGE = "Command: "
            + COMMAND_WORD + "\nFinds all students whose names contain any of "
            + "the specified keywords (case-insensitive) and displays them as a list with index numbers.\n\n"
            + "Parameters: KEYWORD [MORE_KEYWORDS]...\n"
            + "Example: " + COMMAND_WORD + " alice bob charlie";

    private final NameContainsKeywordsPredicate predicate;

    public FindCommand(NameContainsKeywordsPredicate predicate) {
        this.predicate = predicate;
    }

    @Override
    public CommandResult execute(Model model) {
        requireNonNull(model);

        model.updateFilteredStudentList(predicate);
        int numberOfFilteredStudents = model.getFilteredStudentList().size();

        String output = HEADER_UPDATE + ((numberOfFilteredStudents == 0)
                ? MESSAGE_NO_STUDENTS_FOUND_OVERVIEW
                : String.format(MESSAGE_STUDENTS_FOUND_OVERVIEW, numberOfFilteredStudents));

        return new CommandResult(output);
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof FindCommand // instanceof handles nulls
                && predicate.equals(((FindCommand) other).predicate)); // state check
    }
}
