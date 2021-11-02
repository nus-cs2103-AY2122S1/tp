package seedu.address.logic.commands;

import static java.util.Objects.requireNonNull;
import static seedu.address.logic.parser.CliSyntax.PREFIX_ASSESSMENT_NAME;
import static seedu.address.logic.parser.CliSyntax.PREFIX_SCORE;
import static seedu.address.model.assessment.Score.SCORE_DELIMITER;

import java.util.List;

import seedu.address.commons.core.Messages;
import seedu.address.commons.core.index.Index;
import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.model.Model;
import seedu.address.model.assessment.Assessment;
import seedu.address.model.student.Student;

/**
 * Adds an assessment to a student identified using it's displayed index from the address book.
 */
public class AddAssessmentCommand extends Command {

    public static final String COMMAND_WORD = "addassessment";

    public static final String MESSAGE_USAGE = COMMAND_WORD + ": Adds a student's assessment to the address book.\n"
            + "Parameters: "
            + "INDEX (must be a positive integer) "
            + PREFIX_ASSESSMENT_NAME + "ASSESSMENT NAME "
            + PREFIX_SCORE + "SCORE\n"
            + "Example: " + COMMAND_WORD + " 1 "
            + PREFIX_ASSESSMENT_NAME + "Midterms "
            + PREFIX_SCORE + "60" + SCORE_DELIMITER + "100 ";

    public static final String MESSAGE_SUCCESS = "New assessment added to %1$s: %2$s";
    public static final String MESSAGE_DUPLICATE_ASSESSMENT =
            "This assessment already exists in %1$s's assessments list";

    private final Index index;
    private final Assessment assessment;

    /**
     * Creates an AddAssessmentCommand to add the specified {@code Assessment} to the student.
     */
    public AddAssessmentCommand(Index index, Assessment assessment) {
        requireNonNull(index);
        requireNonNull(assessment);
        this.index = index;
        this.assessment = assessment;
    }

    @Override
    public CommandResult execute(Model model) throws CommandException {
        requireNonNull(model);
        List<Student> lastShownList = model.getFilteredStudentList();

        if (index.getZeroBased() >= lastShownList.size()) {
            throw new CommandException(Messages.MESSAGE_INVALID_STUDENT_DISPLAYED_INDEX);
        }

        Student student = lastShownList.get(index.getZeroBased());

        if (model.hasAssessment(student, assessment)) {
            throw new CommandException(String.format(MESSAGE_DUPLICATE_ASSESSMENT, student.getName()));
        }

        Assessment assessmentToAdd = new Assessment(assessment.getAssessmentName(), assessment.getScore());
        model.addAssessment(student, assessmentToAdd);
        return new CommandResult(String.format(MESSAGE_SUCCESS, student.getName(), assessment), false, false,
            student);
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof AddAssessmentCommand // instanceof handles nulls
                && index.equals(((AddAssessmentCommand) other).index)
                && assessment.equals(((AddAssessmentCommand) other).assessment));
    }
}
