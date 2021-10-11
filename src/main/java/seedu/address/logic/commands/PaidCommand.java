package seedu.address.logic.commands;

import static java.util.Objects.requireNonNull;
import static seedu.address.model.Model.PREDICATE_SHOW_ALL_PERSONS;

import java.util.List;

import seedu.address.commons.core.Messages;
import seedu.address.commons.core.index.Index;
import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.model.Model;
import seedu.address.model.student.PaymentStatus;
import seedu.address.model.student.Student;

/**
 * Sets the payment status of the student identified using the displayed index in TutorAid, to
 * 'Paid for the current month'.
 */
public class PaidCommand extends Command {

    public static final String COMMAND_WORD = "paid";

    public static final String MESSAGE_USAGE = COMMAND_WORD
            + ": Sets the payment status of the student identified by the index number used in the "
            + "displayed student list, to 'Paid for the current month'.\n"
            + "Parameters: INDEX (must be a positive integer)\n"
            + "Example: " + COMMAND_WORD + " 1";

    public static final String MESSAGE_SET_TO_PAID_SUCCESS =
            "Set payment status to 'Paid for the current month': %1$s";
    public static final String MESSAGE_ALREADY_PAID = "This student has already paid for the current month.";

    private final Index targetIndex;

    public PaidCommand(Index targetIndex) {
        this.targetIndex = targetIndex;
    }

    @Override
    public CommandResult execute(Model model) throws CommandException {
        requireNonNull(model);
        List<Student> lastShownList = model.getFilteredPersonList();

        if (targetIndex.getZeroBased() >= lastShownList.size()) {
            throw new CommandException(Messages.MESSAGE_INVALID_PERSON_DISPLAYED_INDEX);
        }

        Student studentToEdit = lastShownList.get(targetIndex.getZeroBased());

        if (studentToEdit.getPaymentStatus().hasPaid) {
            throw new CommandException(MESSAGE_ALREADY_PAID);
        }

        Student editedStudent = new Student(
                studentToEdit.getStudentName(), studentToEdit.getStudentPhone(), studentToEdit.getParentName(),
                studentToEdit.getParentPhone(), studentToEdit.getProgress(), new PaymentStatus(true));

        model.setPerson(studentToEdit, editedStudent);
        model.updateFilteredPersonList(PREDICATE_SHOW_ALL_PERSONS);

        return new CommandResult(String.format(MESSAGE_SET_TO_PAID_SUCCESS, editedStudent));
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof PaidCommand // instanceof handles nulls
                && targetIndex.equals(((PaidCommand) other).targetIndex)); // state check
    }
}
