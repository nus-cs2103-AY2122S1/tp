package seedu.address.logic.commands;

import static java.util.Objects.requireNonNull;
import static seedu.address.logic.parser.CliSyntax.PREFIX_STUDENT_INDEX;
import static seedu.address.logic.parser.CliSyntax.PREFIX_TUITION_CLASS;

import java.util.ArrayList;
import java.util.List;

import seedu.address.commons.core.Messages;
import seedu.address.commons.core.index.Index;
import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.model.Model;
import seedu.address.model.person.Person;
import seedu.address.model.tuition.TuitionClass;

public class RemoveStudentCommand extends Command {
    public static final String COMMAND_WORD = "remove";

    public static final String MESSAGE_USAGE = COMMAND_WORD
            + ": Removes a student from a tuition class.\n"
            + "Parameters: " + PREFIX_STUDENT_INDEX + "STUDENT_INDEX "
            + PREFIX_TUITION_CLASS + "CLASS_INDEX\n"
            + "Example: " + COMMAND_WORD + " " + PREFIX_STUDENT_INDEX + "1 2 " + PREFIX_TUITION_CLASS + "2";

    public static final String MESSAGE_REMOVE_STUDENT_SUCCESS = "Removed students: %1$s from class: %2$s";
    public static final String MESSAGE_REMOVE_STUDENT_FAILURE = "Students: %1$s are not found in class: %2$s";
    private final List<Index> studentIndex;
    private final Index classIndex;

    /**
     * Constructor for remove student command.
     *
     * @param studentIndex
     * @param classIndex
     */
    public RemoveStudentCommand(List<Index> studentIndex, Index classIndex) {
        this.studentIndex = studentIndex;
        this.classIndex = classIndex;
    }

    /**
     * Executes the command and returns the result message.
     *
     * @param model {@code Model} which the command should operate on.
     * @return feedback message of the operation result for display
     * @throws CommandException If an error occurs during command execution.
     */
    @Override
    public CommandResult execute(Model model) throws CommandException {
        requireNonNull(model);
        Person personToRemove = null;
        TuitionClass tuitionClass = null;
        List<String> studentsNotInClass = new ArrayList<>();
        List<String> studentsRemoved = new ArrayList<>();
        if (classIndex.getZeroBased() >= model.getFilteredTuitionList().size()) {
            throw new CommandException(Messages.MESSAGE_INVALID_CLASS_DISPLAYED_INDEX);
        }
        tuitionClass = model.getFilteredTuitionList().get(classIndex.getZeroBased());
        if (tuitionClass == null) {
            throw new CommandException(String.format(Messages.MESSAGE_CLASS_NOT_FOUND));
        }
        for (int i = 0; i < studentIndex.size(); i++) {
            Index currIndex = studentIndex.get(i);
            if (currIndex.getZeroBased() >= model.getFilteredPersonList().size()) {
                throw new CommandException(Messages.MESSAGE_INVALID_PERSON_DISPLAYED_INDEX);
            }
            personToRemove = model.getFilteredPersonList().get(currIndex.getZeroBased());
            if (personToRemove == null) {
                throw new CommandException(String.format(Messages.MESSAGE_STUDENT_NOT_FOUND));
            }
            if (tuitionClass.containsStudent(personToRemove)) {
                TuitionClass updatedClass = tuitionClass.removeStudent(personToRemove);
                Person updatedPerson = personToRemove.removeClass(tuitionClass);
                model.setTuition(tuitionClass, updatedClass);
                model.setPerson(personToRemove, updatedPerson);
                studentsRemoved.add(personToRemove.getName().fullName);
            } else {
                studentsNotInClass.add(personToRemove.getName().fullName);
            }
        }
        String feedback = (!studentsRemoved.isEmpty()
                ? String.format(MESSAGE_REMOVE_STUDENT_SUCCESS + "\n", studentsRemoved, tuitionClass.getName().name)
                : "") + (!studentsNotInClass.isEmpty()
                ? String.format(MESSAGE_REMOVE_STUDENT_FAILURE, studentsNotInClass, tuitionClass.getName().name)
                : "");
        return new CommandResult(feedback);
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof RemoveStudentCommand // instanceof handles nulls
                && classIndex.equals(((RemoveStudentCommand) other).classIndex)
                && studentIndex.equals(((RemoveStudentCommand) other).studentIndex));
    }
}

