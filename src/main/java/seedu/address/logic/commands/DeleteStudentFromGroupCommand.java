package seedu.address.logic.commands;

import static java.util.Objects.requireNonNull;
import static seedu.address.commons.util.CollectionUtil.requireAllNonNull;
import static seedu.address.logic.parser.CliSyntax.PREFIX_CLASSCODE;
import static seedu.address.logic.parser.CliSyntax.PREFIX_GROUPNUMBER;
import static seedu.address.logic.parser.CliSyntax.PREFIX_TYPE;
import static seedu.address.model.Model.PREDICATE_SHOW_ALL_STUDENTS;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

import seedu.address.commons.core.Messages;
import seedu.address.commons.core.index.Index;
import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.model.Model;
import seedu.address.model.student.Student;
import seedu.address.model.tutorialgroup.TutorialGroup;


public class DeleteStudentFromGroupCommand extends Command {
    public static final String COMMAND_WORD = "deletesg";

    public static final String MESSAGE_USAGE = COMMAND_WORD + ": Deletes a student from a group in Classmate "
            + "by the index number used in the last person listing. "
            + "Parameters: INDEX (must be a positive integer) "
            + PREFIX_CLASSCODE + "CLASS_CODE "
            + PREFIX_TYPE + "GROUP_TYPE "
            + PREFIX_GROUPNUMBER + "GROUP_NUMBER "
            + "Example: " + COMMAND_WORD + " 1 "
            + PREFIX_GROUPNUMBER + "1 "
            + PREFIX_CLASSCODE + "G06 "
            + PREFIX_TYPE + "OP1 ";

    public static final String MESSAGE_SUCCESS = "Index: %1$d removed from Group: %2$s";
    public static final String MESSAGE_GROUP_NOT_EXIST = "The group does not exist in Classmate";
    public static final String MESSAGE_NOT_BELONG_GROUP = "The student does not being to this Group";

    private final Index index;
    private final TutorialGroup toDeleteTutorialGroup;

    /**
     * Creates an DeleteStudentFromGroupCommand to delete the specified {@code TutorialCode}
     */
    public DeleteStudentFromGroupCommand(Index index, TutorialGroup tutorialGroup) {
        requireAllNonNull(index, tutorialGroup);
        this.index = index;
        this.toDeleteTutorialGroup = tutorialGroup;
        // new class with the same class code created to check whether it already exists in ClassMATE
    }

    @Override
    public CommandResult execute(Model model) throws CommandException {
        requireNonNull(model);
        List<Student> lastShownList = model.getFilteredStudentList();

        // Check if index is within range of student list
        if (index.getZeroBased() >= lastShownList.size()) {
            throw new CommandException(Messages.MESSAGE_INVALID_STUDENT_DISPLAYED_INDEX);
        }

        // check if tutorial group already exists in ClassMATE
        if (!model.hasTutorialGroup(toDeleteTutorialGroup)) {
            throw new CommandException(MESSAGE_GROUP_NOT_EXIST);
        }

        Student studentToEdit = lastShownList.get(index.getZeroBased());

        // check if Student belongs to the Tutorial Group
        if (!isBelongGroup(studentToEdit, toDeleteTutorialGroup)) {
            throw new CommandException(MESSAGE_NOT_BELONG_GROUP);
        }

        Student editedStudent = deleteTutorialGroup(studentToEdit, toDeleteTutorialGroup);

        model.setStudent(studentToEdit, editedStudent);
        model.updateFilteredStudentList(PREDICATE_SHOW_ALL_STUDENTS);

        return new CommandResult(String.format(MESSAGE_SUCCESS, index.getOneBased(), toDeleteTutorialGroup));
    }

    public boolean isBelongGroup(Student student, TutorialGroup tutorialGroup) {
        return student.getTutorialGroups().contains(tutorialGroup);
    }

    @Override
    public boolean equals(Object other) {
        // short circuit if same object
        if (other == this) {
            return true;
        }

        // instanceof handles nulls
        if (!(other instanceof DeleteStudentFromGroupCommand)) {
            return false;
        }

        // state check
        return index.equals(((DeleteStudentFromGroupCommand) other).index)
                && toDeleteTutorialGroup.equals(((DeleteStudentFromGroupCommand) other).toDeleteTutorialGroup);
    }
    /**
     * Creates and returns a {@code Student} with the {@code TutorialGroup} deleted.
     */
    public static Student deleteTutorialGroup(Student studentToEdit, TutorialGroup group) {
        assert studentToEdit != null;

        Set<TutorialGroup> updatedTutorialGroups = new HashSet<>(studentToEdit.getTutorialGroups());
        updatedTutorialGroups.remove(group);
        return new Student(
                studentToEdit.getName(),
                studentToEdit.getPhone(),
                studentToEdit.getEmail(),
                studentToEdit.getAddress(),
                studentToEdit.getClassCode(),
                studentToEdit.getTags(),
                studentToEdit.getMarks(),
                updatedTutorialGroups);

    }
}
