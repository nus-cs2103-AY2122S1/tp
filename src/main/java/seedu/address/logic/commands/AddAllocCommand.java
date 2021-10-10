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
        List<Student> lastShownList = model.getFilteredStudentList();

        Optional<Group> groupToAlloc = allocDescriptor.getGroup();
        Optional<List<Student>> studentsToAlloc = getAllocStudents(lastShownList, allocDescriptor);

        assert groupToAlloc.isPresent();
        Group checkedGroup = groupToAlloc.get();

        if (!model.hasGroup(checkedGroup)) {
            throw new CommandException(MESSAGE_NONEXISTENT_GROUP);
        }

        if (studentsToAlloc.isEmpty()) {
            throw new CommandException(MESSAGE_NONEXISTENT_STUDENT);
        }

        if (studentsToAlloc.get().size() > 1) {
            throw new CommandException(MESSAGE_DUPLICATE_STUDENT_NAME);
        }

        Student checkedStudent = studentsToAlloc.get().get(0);

        if (checkedGroup.hasStudent(checkedStudent)) {
            throw new CommandException(MESSAGE_DUPLICATE_STUDENT);
        }

        Student updatedStudent = createAllocStudents(checkedStudent, allocDescriptor);

        model.addStudentToGroup(checkedGroup, checkedStudent);
        model.setStudent(checkedStudent, updatedStudent);
        return new CommandResult(String.format(MESSAGE_SUCCESS, updatedStudent));
    }

    /**
     * Get and returns a list of {@code Student} with matching identity specified in the {@code allocDescriptor}.
     */
    public static Optional<List<Student>> getAllocStudents(List<Student> students, AllocDescriptor allocDescriptor) {
        List<Student> allocStudents = students.stream()
                .filter(allocDescriptor.isAllocStudent())
                .collect(Collectors.toList());
        return Optional.of(Collections.unmodifiableList(allocStudents));
    }

    /**
     * Create and returns a {@code Student} with the details of {@code preAllocStudent}
     * allocated to the group specified in the {@code allocDescriptor}.
     */
    public static Student createAllocStudents(Student preAllocStudent, AllocDescriptor allocDescriptor) {
        assert preAllocStudent != null;
        assert allocDescriptor.getGroup().isPresent();

        Name name = preAllocStudent.getName();
        ID id = preAllocStudent.getId();
        List<Group> groups = preAllocStudent.getGroups();
        Map<Assessment, Score> scores = preAllocStudent.getScores();
        Set<Tag> tags = preAllocStudent.getTags();

        Group groupToAlloc = allocDescriptor.getGroup().get();
        assert !groups.contains(groupToAlloc);

        List<Group> updatedGroups = new ArrayList<>(groups);
        updatedGroups.add(groupToAlloc);

        return new Student(name, id, updatedGroups, scores, tags);
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
