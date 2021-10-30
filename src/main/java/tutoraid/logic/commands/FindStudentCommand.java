package tutoraid.logic.commands;

import static java.util.Objects.requireNonNull;

import tutoraid.commons.core.Messages;
import tutoraid.model.Model;
import tutoraid.model.student.NameContainsSubstringsPredicate;

/**
 * Finds and lists all students in address book whose name contains any of the argument keywords.
 * Keyword matching is case insensitive.
 */
public class FindStudentCommand extends FindCommand {

    public static final String COMMAND_FLAG = "-s";

    public static final String MESSAGE_USAGE = FindCommand.COMMAND_WORD + COMMAND_FLAG
            + ": Finds all students whose names contain any of "
            + "the specified keywords (case-insensitive) and displays them as a list with index numbers.\n"
            + "Parameters: KEYWORD [MORE_KEYWORDS]...\n"
            + "Example: " + FindCommand.COMMAND_WORD + COMMAND_FLAG + " alice bob charlie";

    private final NameContainsSubstringsPredicate predicate;

    public FindStudentCommand(NameContainsSubstringsPredicate predicate) {
        this.predicate = predicate;
    }

    @Override
    public CommandResult execute(Model model) {
        requireNonNull(model);
        model.updateFilteredStudentList(predicate);

        return new CommandResult(
                String.format(Messages.MESSAGE_STUDENTS_LISTED_OVERVIEW, model.getFilteredStudentList().size()));
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof FindStudentCommand // instanceof handles nulls
                && predicate.equals(((FindStudentCommand) other).predicate)); // state check
    }
}
