package seedu.address.logic.commands;

import static java.util.Objects.requireNonNull;
import static seedu.address.logic.parser.CliSyntax.PREFIX_CLASSCODE;
import static seedu.address.logic.parser.CliSyntax.PREFIX_GROUPNAME;
import static seedu.address.logic.parser.CliSyntax.PREFIX_TYPE;

import seedu.address.commons.core.Messages;
import seedu.address.model.Model;
import seedu.address.model.student.ClassMemberPredicate;
import seedu.address.model.tutorialgroup.TutorialGroupContainsKeywordsPredicate;

/**
 * Lists all students in a tutorial group in ClassMATE given a class code, tutorial group type and tutorial group name
 * Keyword matching is case-insensitive.
 */
public class ViewGroupCommand extends Command {

    public static final String COMMAND_WORD = "viewg";

    public static final String MESSAGE_USAGE = COMMAND_WORD + ": Finds all students in a tutorial group given a class "
            + "code, tutorial group type and tutorial group name (case-insensitive) and displays them as a list"
            + "with index numbers.\n"
            + "Parameters: "
            + PREFIX_CLASSCODE + "CLASSCODE "
            + PREFIX_GROUPNAME + "GROUPNAME "
            + PREFIX_TYPE + "TYPE "
            + "Example: " + COMMAND_WORD + " "
            + PREFIX_GROUPNAME + "3 "
            + PREFIX_CLASSCODE + "G06 "
            + PREFIX_TYPE + "OP2 ";

    private final TutorialGroupContainsKeywordsPredicate predicate;

    public ViewGroupCommand(TutorialGroupContainsKeywordsPredicate predicate) {
        this.predicate = predicate;
    }

    @Override
    public CommandResult execute(Model model) {
        requireNonNull(model);
        model.updateFilteredTutorialGroupList(predicate);


        model.updateFilteredStudentList(new ClassMemberPredicate(targetClassCode));
        return new CommandResult(
                String.format(Messages.MESSAGE_TUTORIAL_GROUP_LISTED_OVERVIEW,
                        model.getFilteredStudentList().size()));
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof ViewGroupCommand // instanceof handles nulls
                && predicate.equals(((ViewGroupCommand) other).predicate)); // state check
    }
}
