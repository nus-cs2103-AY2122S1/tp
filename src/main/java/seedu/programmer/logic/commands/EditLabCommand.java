package seedu.programmer.logic.commands;

import static java.util.Objects.requireNonNull;
import static seedu.programmer.logic.parser.CliSyntax.PREFIX_INDEX;
import static seedu.programmer.logic.parser.CliSyntax.PREFIX_LAB_NUM;
import static seedu.programmer.logic.parser.CliSyntax.PREFIX_LAB_RESULT;

import java.util.List;

import seedu.programmer.logic.commands.exceptions.CommandException;
import seedu.programmer.model.Model;
import seedu.programmer.model.student.Lab;
import seedu.programmer.model.student.Student;


/**
 * Adds a lab with total score and default score for all the students in the list.
 */
public class EditLabCommand extends Command {

    public static final String COMMAND_WORD = "editlab";

    public static final String MESSAGE_USAGE = COMMAND_WORD + ": Edits a lab number or total score."
            + "Parameters: "
            + PREFIX_INDEX + "Index "
            + PREFIX_LAB_NUM + "Lab Title "
            + PREFIX_LAB_RESULT + "Score"
            + "Example: " + COMMAND_WORD + " "
            + PREFIX_INDEX + "1 "
            + PREFIX_LAB_NUM + "1 "
            + PREFIX_LAB_RESULT + "15";

    public static final String MESSAGE_EDIT_LAB_SUCCESS = "Updated %1$s!";

    private final int newLabNum;
    private final Double total;
    private final Lab original;

    /**
     * @param original the lab to be edited.
     * */
    public EditLabCommand(Lab original, int newLabNum, Double total) {
        this.original = original;
        this.newLabNum = newLabNum;
        this.total = total;
    }
    /**
     * @param original the lab to be edited.
     * */
    public EditLabCommand(Lab original, Double total) {
        this.original = original;
        this.total = total;
        this.newLabNum = 0;
    }

    /**
     * @param original the lab to be edited.
     * @param newLabNum the new lab number
     * */
    public EditLabCommand(Lab original, int newLabNum) {
        this.original = original;
        this.newLabNum = newLabNum;
        this.total = original.getTotalScore();
    }

    @Override
    public CommandResult execute(Model model) throws CommandException {
        requireNonNull(model);
        List<Student> lastShownList = model.getFilteredStudentList();
        boolean labExists = false;
        for (Student std : lastShownList) {
            Student editedStd = std;
            if (!std.getLabResultList().contains(original)) {
                continue;
            }
            labExists = true;
            editedStd.editLabInfo(original, newLabNum, total);
            model.setStudent(std, editedStd);
        }

        return labExists ? new CommandResult(String.format(MESSAGE_EDIT_LAB_SUCCESS, original))
                         : new CommandResult("This lab does not exist!");
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof EditLabCommand// instanceof handles nulls
                && original.equals(((EditLabCommand) other).original)); // state check
    }


}

