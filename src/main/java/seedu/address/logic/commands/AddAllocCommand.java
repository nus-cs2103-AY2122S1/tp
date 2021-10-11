package seedu.address.logic.commands;

import static java.util.Objects.requireNonNull;
import static seedu.address.logic.parser.CliSyntax.PREFIX_GROUP;
import static seedu.address.logic.parser.CliSyntax.PREFIX_ID;
import static seedu.address.logic.parser.CliSyntax.PREFIX_NAME;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.Set;
import java.util.function.Predicate;
import java.util.stream.Collectors;

import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.model.Model;
import seedu.address.model.ReadOnlyAddressBook;
import seedu.address.model.student.Assessment;
import seedu.address.model.student.Group;
import seedu.address.model.student.ID;
import seedu.address.model.student.Name;
import seedu.address.model.student.Score;
import seedu.address.model.student.Student;
import seedu.address.model.tag.Tag;

/**
 * Allocates an existing student to an existing group.
 */
public class AddAllocCommand extends Command {

    public static final String COMMAND_WORD = "add alloc";

    public static final String MESSAGE_USAGE = COMMAND_WORD + ": Adds a student to an existing group. "
            + "Parameters: "
            + PREFIX_GROUP + "<group_name> "
            + "(" + PREFIX_NAME + "<student_name> | "
            + PREFIX_ID + "<student_id>)";

    public static final String MESSAGE_SUCCESS = "New allocation added: %1$s";
    public static final String MESSAGE_NONEXISTENT_GROUP = "This group does not exist.";
    public static final String MESSAGE_NONEXISTENT_STUDENT = "This student does not exist.";
    public static final String MESSAGE_DUPLICATE_STUDENT_NAME =
            "This student needs to be allocated using ID due to duplicate naming.";
    public static final String MESSAGE_DUPLICATE_STUDENT = "This student already exists in the group.";

    private final AllocDescriptor allocDescriptor;

    /**
     * @param allocDescriptor details of the allocation
     */
    public AddAllocCommand(AllocDescriptor allocDescriptor) {
        requireNonNull(allocDescriptor);
        this.allocDescriptor = new AllocDescriptor(allocDescriptor);
    }

    @Override
    public CommandResult execute(Model model) throws CommandException {
        requireNonNull(model);

        ReadOnlyAddressBook addressBook = model.getAddressBook();
        List<Group> groupList = addressBook.getGroupList();
        List<Student> studentList = addressBook.getStudentList();

        assert allocDescriptor.getGroup().isPresent();
        if (!groupList.contains(allocDescriptor.getGroup().get())) {
            throw new CommandException(MESSAGE_NONEXISTENT_GROUP);
        }

        Group groupToEdit = getAllocGroup(groupList, allocDescriptor);
        List<Student> studentsToEdit = getAllocStudents(studentList, allocDescriptor);

        if (studentsToEdit.isEmpty()) {
            throw new CommandException(MESSAGE_NONEXISTENT_STUDENT);
        }

        if (studentsToEdit.size() > 1) {
            throw new CommandException(MESSAGE_DUPLICATE_STUDENT_NAME);
        }

        Student studentToEdit = studentsToEdit.get(0);

        if (groupToEdit.hasStudent(studentToEdit.getId())) {
            throw new CommandException(MESSAGE_DUPLICATE_STUDENT);
        }

        Student editedStudent = createEditedStudents(studentToEdit, allocDescriptor);

        groupToEdit.addStudent(editedStudent.getId());
        model.setStudent(studentToEdit, editedStudent);
        return new CommandResult(String.format(MESSAGE_SUCCESS, editedStudent));
    }

    /**
     * Gets and returns a list of {@code Student} with matching identity specified in the {@code allocDescriptor}.
     */
    public static Group getAllocGroup(List<Group> groups, AllocDescriptor allocDescriptor) {
        List<Group> allocGroups = groups.stream()
                .filter(allocDescriptor.isAllocGroup())
                .collect(Collectors.toList());
        assert allocGroups.size() == 1;
        return allocGroups.get(0);
    }

    /**
     * Gets and returns a list of {@code Student} with matching identity specified in the {@code allocDescriptor}.
     */
    public static List<Student> getAllocStudents(List<Student> students, AllocDescriptor allocDescriptor) {
        List<Student> allocStudents = students.stream()
                .filter(allocDescriptor.isAllocStudent())
                .collect(Collectors.toList());
        return Collections.unmodifiableList(allocStudents);
    }

    /**
     * Creates and returns a {@code Student} with the details of {@code preAllocStudent}
     * allocated to the group specified in the {@code allocDescriptor}.
     */
    public static Student createEditedStudents(Student toEditStudent, AllocDescriptor allocDescriptor) {
        assert toEditStudent != null;
        assert allocDescriptor.getGroup().isPresent();

        Name name = toEditStudent.getName();
        ID id = toEditStudent.getId();
        List<Group> groups = toEditStudent.getGroups();
        Map<Assessment, Score> scores = toEditStudent.getScores();
        Set<Tag> tags = toEditStudent.getTags();

        Group groupToEdit = allocDescriptor.getGroup().get();
        assert !groups.contains(groupToEdit);

        List<Group> editedGroups = new ArrayList<>(groups);
        editedGroups.add(groupToEdit);

        return new Student(name, id, editedGroups, scores, tags);
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof AddAllocCommand // instanceof handles nulls
                && allocDescriptor.equals(((AddAllocCommand) other).allocDescriptor));
    }

    /**
     * Stores the details to edit the student with. Each non-empty field value will replace the
     * corresponding field value of the student.
     */
    public static class AllocDescriptor {
        private Name name;
        private ID id;
        private Group group;

        public AllocDescriptor() {}

        /**
         * Copy constructor.
         */
        public AllocDescriptor(AllocDescriptor toCopy) {
            setName(toCopy.name);
            setId(toCopy.id);
            setGroup(toCopy.group);
        }

        public void setName(Name name) {
            this.name = name;
        }

        public Optional<Name> getName() {
            return Optional.ofNullable(name);
        }

        public void setId(ID id) {
            this.id = id;
        }

        public Optional<ID> getId() {
            return Optional.ofNullable(id);
        }

        public void setGroup(Group group) {
            this.group = group;
        }

        public Optional<Group> getGroup() {
            return Optional.ofNullable(group);
        }

        /**
         * Returns a {@code Predicate} checking if a group have a matched name.
         */
        public Predicate<Group> isAllocGroup() {
            return toCheck -> toCheck.equals(group);
        }

        /**
         * Returns a {@code Predicate} checking if a student have a matched name or ID.
         */
        public Predicate<Student> isAllocStudent() {
            return toCheck -> toCheck.getName().equals(name)
                    || toCheck.getId().equals(id);
        }

        @Override
        public boolean equals(Object other) {
            // short circuit if same object
            if (other == this) {
                return true;
            }

            // instanceof handles nulls
            if (!(other instanceof AllocDescriptor)) {
                return false;
            }

            // state check
            AllocDescriptor e = (AllocDescriptor) other;

            return getName().equals(e.getName())
                    && getId().equals(e.getId())
                    && getGroup().equals(e.getGroup());
        }
    }
}
