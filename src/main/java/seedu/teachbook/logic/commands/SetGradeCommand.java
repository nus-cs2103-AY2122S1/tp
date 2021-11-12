package seedu.teachbook.logic.commands;

import static java.util.Objects.requireNonNull;

import java.util.List;

import seedu.teachbook.logic.commands.exceptions.CommandException;
import seedu.teachbook.model.Model;
import seedu.teachbook.model.gradeobject.Grade;
import seedu.teachbook.model.gradeobject.GradingSystem;

/**
 * Sets a grading system in the teachbook. A grading system is a set of grades the user specifies
 * which the user will use to grade the students. Setting a grading system is compulsory before
 * giving students their grades.
 */
public class SetGradeCommand extends Command {

    public static final String COMMAND_WORD = "setGrade";
    public static final String MESSAGE_USAGE = COMMAND_WORD
            + ": Sets a grading system.\n"
            + "Parameters: GRADE_1[>GRADE_2]...\n"
            + "Example: " + COMMAND_WORD + " A>B>C>D";

    public static final String MESSAGE_SUCCESS = "New grading system set: %1$s";
    public static final String MESSAGE_GRADING_SYSTEM_EXISTS = "A grading system is currently in use. "
            + "You can reset the grading system using \"" + ResetGradeCommand.COMMAND_WORD + "\".";

    public final List<Grade> gradeList;

    /**
     * Creates an SetGradeCommand to set a new grading system
     */
    public SetGradeCommand(List<Grade> grades) {
        requireNonNull(grades);
        this.gradeList = grades;
    }

    @Override
    public CommandResult execute(Model model) throws CommandException {
        requireNonNull(model);

        if (model.hasExistingGradingSystem()) {
            throw new CommandException(MESSAGE_GRADING_SYSTEM_EXISTS);
        }

        GradingSystem gradingSystem = new GradingSystem(gradeList);
        model.setGradingSystem(gradingSystem);
        model.commitTeachBook();
        return new CommandResult(String.format(MESSAGE_SUCCESS, gradingSystem));
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof SetGradeCommand // instanceof handles nulls
                && gradeList.equals(((SetGradeCommand) other).gradeList));
    }

}
