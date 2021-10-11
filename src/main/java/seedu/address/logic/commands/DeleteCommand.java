package seedu.address.logic.commands;

import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.model.Model;


/**
 * Deletes a student identified using it's displayed index from the address book.
 */
public abstract class DeleteCommand extends Command {
    public static final String COMMAND_WORD = "delete";
//
    public static final String MESSAGE_USAGE = COMMAND_WORD
            + ": Delete command message usage to be added\n";
//
//    public static final String MESSAGE_DELETE_PERSON_SUCCESS = "Deleted Person: %1$s";
//
//    private final Index targetIndex;
//
//    public DeleteCommand(Index targetIndex) {
//        this.targetIndex = targetIndex;
//    }

    @Override
    public abstract CommandResult execute(Model model) throws CommandException;
//        {
//        requireNonNull(model);
//        List<Student> lastShownList = model.getFilteredStudentList();
//
//        if (targetIndex.getZeroBased() >= lastShownList.size()) {
//            throw new CommandException(Messages.MESSAGE_INVALID_PERSON_DISPLAYED_INDEX);
//        }
//
//        Student studentToDelete = lastShownList.get(targetIndex.getZeroBased());
//        model.deleteStudent(studentToDelete);
//        return new CommandResult(String.format(MESSAGE_DELETE_PERSON_SUCCESS, studentToDelete));
//    }

    @Override
    public abstract boolean equals(Object other);
//        {
//        return other == this // short circuit if same object
//                || (other instanceof DeleteCommand // instanceof handles nulls
//                && targetIndex.equals(((DeleteCommand) other).targetIndex)); // state check
//    }

}
