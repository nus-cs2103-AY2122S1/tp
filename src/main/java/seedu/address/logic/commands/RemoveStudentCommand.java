package seedu.address.logic.commands;

import seedu.address.commons.core.Messages;
import seedu.address.commons.core.index.Index;
import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.model.Model;
import seedu.address.model.person.Person;
import seedu.address.model.tuition.TuitionClass;

import java.util.List;

import static java.util.Objects.requireNonNull;
import static seedu.address.logic.parser.CliSyntax.PREFIX_STUDENT_INDEX;
import static seedu.address.logic.parser.CliSyntax.PREFIX_TUITION_CLASS;

public class RemoveStudentCommand extends Command {
    public static final String COMMAND_WORD = "remove";

    public static final String MESSAGE_USAGE = COMMAND_WORD
            + ": Removes a student from a tuition class.\n"
            + "Parameters: " + PREFIX_STUDENT_INDEX + "STUDENT_INDEX "
            + PREFIX_TUITION_CLASS + "CLASS_INDEX\n"
            + "Example: " + COMMAND_WORD + " " + PREFIX_STUDENT_INDEX + "1 " + PREFIX_TUITION_CLASS + "2";

    public static final String MESSAGE_REMOVE_STUDENT_SUCCESS = "Removed student: %1$s from tuition class: %2$s ";
    private final Index studentIndex, classIndex;

    public RemoveStudentCommand(Index studentIndex, Index classIndex) {
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
        List<Person> lastShownList = model.getFilteredPersonList();
        List<TuitionClass> lastClassList = model.getFilteredTuitionList();

        if (studentIndex.getZeroBased() >= lastShownList.size()) {
            throw new CommandException(Messages.MESSAGE_INVALID_PERSON_DISPLAYED_INDEX);
        }
        if (classIndex.getZeroBased() >= lastClassList.size()) {
            throw new CommandException(Messages.MESSAGE_INVALID_CLASS_DISPLAYED_INDEX);
        }

        personToRemove = lastShownList.get(studentIndex.getZeroBased());
        tuitionClass = lastClassList.get(classIndex.getZeroBased());

        if (personToRemove == null) {
            throw new CommandException(String.format(Messages.MESSAGE_STUDENT_NOT_FOUND));
        } else if (tuitionClass == null) {
            throw new CommandException(String.format(Messages.MESSAGE_CLASS_NOT_FOUND));
        } else if (personToRemove != null && tuitionClass != null) {
            if (tuitionClass.containsStudent(personToRemove)) {
                TuitionClass updatedClass = tuitionClass.removeStudent(personToRemove);
                Person updatedPerson = personToRemove.removeClass(tuitionClass);
                model.setTuition(tuitionClass, updatedClass);
                model.setPerson(personToRemove, updatedPerson);
            } else {
                throw new CommandException(String.format(Messages.MESSAGE_STUDENT_NOT_IN_CLASS,
                        personToRemove.getName().fullName, tuitionClass.getName().name));
            }
        }
        return new CommandResult(String.format(MESSAGE_REMOVE_STUDENT_SUCCESS,
                personToRemove.getName().fullName, tuitionClass.getName().name));
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof RemoveStudentCommand // instanceof handles nulls
                && classIndex.equals(((RemoveStudentCommand) other).classIndex)
                && studentIndex.equals(((RemoveStudentCommand) other).studentIndex));
    }
}

