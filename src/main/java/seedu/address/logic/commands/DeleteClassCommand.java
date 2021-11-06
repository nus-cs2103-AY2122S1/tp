package seedu.address.logic.commands;

import static java.util.Objects.requireNonNull;

import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

import seedu.address.commons.core.Messages;
import seedu.address.commons.core.index.Index;
import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.model.Model;
import seedu.address.model.student.Address;
import seedu.address.model.student.ClassCode;
import seedu.address.model.student.Email;
import seedu.address.model.student.EmptyClassCode;
import seedu.address.model.student.Name;
import seedu.address.model.student.Phone;
import seedu.address.model.student.Student;
import seedu.address.model.student.StudentMark;
import seedu.address.model.tag.Tag;
import seedu.address.model.tutorialclass.TutorialClass;
import seedu.address.model.tutorialgroup.TutorialGroup;

public class DeleteClassCommand extends Command {

    public static final String COMMAND_WORD = "deletec";

    public static final String MESSAGE_USAGE = COMMAND_WORD
            + ": Deletes the class identified by the index number used in the displayed class list.\n"
            + "Parameters: INDEX (must be a positive integer)\n"
            + "Example: " + COMMAND_WORD + " 1";

    public static final String MESSAGE_DELETE_CLASS_SUCCESS = "Deleted Class: %1$s";

    private final Index targetIndex;

    /**
     * Constructor for DeleteClassCommand
     *
     * @param targetIndex Index to be deleted.
     */
    public DeleteClassCommand(Index targetIndex) {
        requireNonNull(targetIndex);
        this.targetIndex = targetIndex;
    }

    @Override
    public CommandResult execute(Model model) throws CommandException {
        requireNonNull(model);
        List<TutorialClass> lastShownList = model.getFilteredTutorialClassList();
        List<Student> studentList = model.getUnfilteredStudentList();

        if (targetIndex.getZeroBased() >= lastShownList.size()) {
            throw new CommandException(Messages.MESSAGE_INVALID_CLASS_DISPLAYED_INDEX);
        }

        TutorialClass classToDelete = lastShownList.get(targetIndex.getZeroBased());
        model.deleteTutorialClass(classToDelete);
        List<Student> newStudentList = studentList.stream()
                .map(s -> removeStudentFromClass(s, classToDelete.getClassCode()))
                .collect(Collectors.toList());
        model.updateUnfilteredStudentList(newStudentList);

        return new CommandResult(String.format(MESSAGE_DELETE_CLASS_SUCCESS, classToDelete));
    }

    /**
     * Creates a new {@code Student} with empty class code
     */
    public static Student removeStudentFromClass(Student student, ClassCode toDelete) {
        ClassCode currClass = student.getClassCode();
        if (currClass.equals(toDelete)) {
            Name name = student.getName();
            Phone phone = student.getPhone();
            Email email = student.getEmail();
            Address address = student.getAddress();
            ClassCode classCode = new EmptyClassCode();
            Set<Tag> tags = student.getTags();
            List<StudentMark> marks = student.getMarks();
            Set<TutorialGroup> tutorialGroups = new HashSet<>();
            return new Student(name, phone, email, address, classCode, tags, marks, tutorialGroups);
        }
        return student;
    }

    @Override
    public boolean equals(Object other) {
        return other == this
                || (other instanceof DeleteClassCommand
                && targetIndex.equals(((DeleteClassCommand) other).targetIndex));
    }
}
