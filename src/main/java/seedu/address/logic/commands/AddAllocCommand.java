package seedu.address.logic.commands;

import static java.util.Objects.requireNonNull;
import static seedu.address.logic.parser.CliSyntax.PREFIX_GROUP;
import static seedu.address.logic.parser.CliSyntax.PREFIX_ID;
import static seedu.address.logic.parser.CliSyntax.PREFIX_NAME;

import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.model.Model;
import seedu.address.model.student.*;
import seedu.address.model.tag.Tag;

import java.util.List;
import java.util.Map;
import java.util.Set;

/**
 * Adds a student to the address book.
 */
public class AddAllocCommand extends Command {

    public static final String COMMAND_WORD = "add alloc";

    public static final String MESSAGE_USAGE = COMMAND_WORD + ": Adds a student to an existing group. "
            + "Parameters: "
            + PREFIX_GROUP + "<group_name> "
            + "(" + PREFIX_NAME + "<student_name> | "
            + PREFIX_ID + "<student_id>)";

    public static final String MESSAGE_SUCCESS = "New student added: %1$s";
    public static final String MESSAGE_NONEXISTENT_GROUP = "This group does not exists.";
    public static final String MESSAGE_NONEXISTENT_STUDENT = "This student does not exists.";
    public static final String MESSAGE_DUPLICATE_STUDENT = "This student already exists in the group.";

    private final Group toAddInto;
    private final Student toAdd;

    /**
     * Creates an AddAllocCommand to add the specified {@code Student}
     */
    public AddAllocCommand(Group group, Student student) {
        requireNonNull(group);
        requireNonNull(student);
        toAddInto = group;
        toAdd = student;
    }

    @Override
    public CommandResult execute(Model model) throws CommandException {
        requireNonNull(model);

        if (!model.hasGroup(toAddInto)) {
            throw new CommandException(MESSAGE_NONEXISTENT_GROUP);
        }


        if (!model.hasStudent(toAdd)) {
            throw new CommandException(MESSAGE_NONEXISTENT_STUDENT);
        }

        if (model.hasStudentInGroup(toAddInto, toAdd)) {
            throw new CommandException(MESSAGE_DUPLICATE_STUDENT);
        }

        Student editedStudent = createAllocatedStudent(toAdd, toAddInto);

        model.addStudentToGroup(toAddInto, toAdd);
        model.setStudent(toAdd, editedStudent);
        return new CommandResult(String.format(MESSAGE_SUCCESS, toAdd));
    }

    private static Student createAllocatedStudent(Student studentToEdit, Group groupToAdd) {
        assert studentToEdit != null;

        Name unchangedName = studentToEdit.getName();
        ID unchangedID = studentToEdit.getId();

        List<Group> updatedGroups = studentToEdit.getGroups();
        updatedGroups.add(groupToAdd);

        Map<Assessment, Score> unchangedScores = studentToEdit.getScores();
        Set<Tag> unchangedTags = studentToEdit.getTags();

        return new Student(unchangedName, unchangedID, updatedGroups, unchangedScores, unchangedTags);
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof AddAllocCommand // instanceof handles nulls
                && toAdd.equals(((AddAllocCommand) other).toAdd));
    }
}
