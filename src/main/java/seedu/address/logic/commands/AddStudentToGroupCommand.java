package seedu.address.logic.commands;

import seedu.address.commons.core.index.Index;
import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.model.Model;
import seedu.address.model.student.Student;
import seedu.address.model.tutorialgroup.TutorialGroup;

import java.util.HashSet;

import static java.util.Objects.requireNonNull;
import static seedu.address.commons.util.CollectionUtil.requireAllNonNull;
import static seedu.address.logic.parser.CliSyntax.PREFIX_CLASSCODE;
import static seedu.address.logic.parser.CliSyntax.PREFIX_GROUPNUMBER;
import static seedu.address.logic.parser.CliSyntax.PREFIX_TYPE;

public class AddStudentToGroupCommand extends Command {
    public static final String COMMAND_WORD = "addsg";

    public static final String MESSAGE_USAGE = COMMAND_WORD + ": Adds a student to a group to Classmate "
            + "by the index number used in the last person listing. "
            + "Parameters: INDEX (must be a positive integer) "
            + PREFIX_GROUPNUMBER + "GROUPNUMBER "
            + PREFIX_CLASSCODE + "CLASSCODE "
            + PREFIX_TYPE + "TYPE "
            + "Example: " + COMMAND_WORD + " 1 "
            + PREFIX_GROUPNUMBER + "1 "
            + PREFIX_CLASSCODE + "G06 "
            + PREFIX_TYPE + "OP1 ";

    public static final String MESSAGE_SUCCESS = "Index: %1$d added to Group: %2$s";
    public static final String MESSAGE_GROUP_NOT_EXIST = "The group does not exist in Classmate";

    private final Index index;
    private final TutorialGroup toAddTutorialGroup;

    /**
     * Creates an AddClassCommand to add the specified {@code Student}
     */
    public AddStudentToGroupCommand(Index index, TutorialGroup tutorialGroup) {
        requireAllNonNull(index, tutorialGroup);
        this.index = index;
        this.toAddTutorialGroup = tutorialGroup;
        // new class with the same class code created to check whether it already exists in ClassMATE
    }

    @Override
    public CommandResult execute(Model model) throws CommandException {
        requireNonNull(model);

        // check if tutorial group already exists in ClassMATE
        if (model.hasTutorialGroup(toAddTutorialGroup)) {
            throw new CommandException(MESSAGE_GROUP_NOT_EXIST);
        }

        //model.addStudentToGroup(toAddStudent);
        return new CommandResult(String.format(MESSAGE_SUCCESS, index.getOneBased(), toAddTutorialGroup));
    }

    @Override
    public boolean equals(Object other) {
        // short circuit if same object
        if (other == this) {
            return true;
        }

        // instanceof handles nulls
        if (!(other instanceof AddStudentToGroupCommand)) {
            return false;
        }

        // state check
        return index.equals(((AddStudentToGroupCommand) other).index)
                && toAddTutorialGroup.equals(((AddStudentToGroupCommand) other).toAddTutorialGroup);
    }
}
