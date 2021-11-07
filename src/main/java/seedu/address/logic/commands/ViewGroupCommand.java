package seedu.address.logic.commands;

import static java.util.Objects.requireNonNull;
import static seedu.address.logic.parser.CliSyntax.PREFIX_CLASSCODE;
import static seedu.address.logic.parser.CliSyntax.PREFIX_GROUPNUMBER;
import static seedu.address.logic.parser.CliSyntax.PREFIX_TYPE;

import seedu.address.commons.core.Messages;
import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.model.Model;
import seedu.address.model.student.GroupMemberPredicate;
import seedu.address.model.tutorialclass.TutorialClass;
import seedu.address.model.tutorialgroup.TutorialGroup;

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
            + PREFIX_CLASSCODE + "CLASS_CODE "
            + PREFIX_TYPE + "GROUP_TYPE "
            + PREFIX_GROUPNUMBER + "GROUP_NUMBER "
            + "Example: " + COMMAND_WORD + " "
            + PREFIX_GROUPNUMBER + "3 "
            + PREFIX_CLASSCODE + "G06 "
            + PREFIX_TYPE + "OP2 ";

    private final TutorialGroup toView;
    private final TutorialClass toViewTutorialClass;

    /**
     * Creates a ViewGroupCommand to show the students in the specified {@code TutorialGroup}
     */
    public ViewGroupCommand(TutorialGroup tutorialGroup) {
        requireNonNull(tutorialGroup);
        toView = tutorialGroup;
        // new class with the same class code created to check whether it exists in ClassMATE
        toViewTutorialClass = TutorialClass.createTestTutorialClass(toView.getClassCode());
    }

    @Override
    public CommandResult execute(Model model) throws CommandException {
        requireNonNull(model);

        // check if tutorial class exists in ClassMATE
        if (!model.hasTutorialClass(toViewTutorialClass)) {
            throw new CommandException(Messages.MESSAGE_CLASS_DOES_NOT_EXIST);
        }

        // check if tutorial group exists in ClassMATE
        if (!model.hasTutorialGroup(toView)) {
            throw new CommandException(Messages.MESSAGE_GROUP_DOES_NOT_EXIST);
        }

        model.updateFilteredStudentList(new GroupMemberPredicate(toView));
        return new CommandResult(
                String.format(Messages.MESSAGE_TUTORIAL_GROUP_LISTED_OVERVIEW,
                        model.getFilteredStudentList().size()));
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof ViewGroupCommand // instanceof handles nulls
                && toView.equals(((ViewGroupCommand) other).toView)); // state check
    }
}
