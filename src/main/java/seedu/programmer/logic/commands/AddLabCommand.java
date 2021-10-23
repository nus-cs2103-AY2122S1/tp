package seedu.programmer.logic.commands;

import static java.util.Objects.requireNonNull;

import java.util.List;

import seedu.programmer.commons.core.Messages;
import seedu.programmer.commons.core.index.Index;
import seedu.programmer.logic.commands.exceptions.CommandException;
import seedu.programmer.model.Model;
import seedu.programmer.model.student.Lab;
import seedu.programmer.model.student.Student;

/**
 * Adds a lab with total score and default score for all the students in the list.
 */
public class AddLabCommand extends Command {

    public static final String COMMAND_WORD = "addlab";

    //todo: for test of show feature only
    public static final String MESSAGE_USAGE = COMMAND_WORD + "work in progress";

    public static final String MESSAGE_ADD_LAB_SUCCESS = "Lab Added: %1$s";

    //private final Index targetIndex;

    private final Lab result;

    /**
     * @param result the lab result to be added.
     * */
    public AddLabCommand(Lab result) {
        //this.targetIndex = targetIndex;
        this.result = result;
    }

    @Override
    public CommandResult execute(Model model) throws CommandException {
        requireNonNull(model);
        // Gets the last filtered list displayed
        List<Student> lastShownList = model.getFilteredStudentList();

        for(Student std: lastShownList) {
            Student target = std;
            target.addLabResult(this.result);
            model.setStudent(target, std);
        }

//        if (targetIndex.getZeroBased() >= lastShownList.size()) {
//            throw new CommandException(Messages.MESSAGE_INVALID_STUDENT_DISPLAYED_INDEX);
//        }
//
//        Student target = lastShownList.get(targetIndex.getZeroBased());
//        Student replacement = target;
//        replacement.addLabResult(result);

        //model.setStudent(target, replacement);
        return new CommandResult(String.format(MESSAGE_ADD_LAB_SUCCESS, result));
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof AddLabCommand// instanceof handles nulls
                && result.equals(((AddLabCommand) other).result)); // state check
    }
}

