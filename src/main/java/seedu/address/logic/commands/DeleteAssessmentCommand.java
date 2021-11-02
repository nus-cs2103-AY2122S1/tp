package seedu.address.logic.commands;

import static java.util.Objects.requireNonNull;
import static seedu.address.logic.parser.CliSyntax.PREFIX_ASSESSMENT_NAME;

import java.util.List;

import seedu.address.commons.core.Messages;
import seedu.address.commons.core.index.Index;
import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.model.Model;
import seedu.address.model.assessment.Assessment;
import seedu.address.model.assessment.AssessmentName;
import seedu.address.model.assessment.AssessmentNameMatchesKeywordPredicate;
import seedu.address.model.student.Student;

/**
 * Deletes an assessment from a student identified using it's displayed index from the address book.
 */
public class DeleteAssessmentCommand extends Command {

    public static final String COMMAND_WORD = "deleteassessment";

    public static final String MESSAGE_USAGE = COMMAND_WORD
            + ": Deletes the assessment from the student identified by the index number used in the displayed"
            + " student list.\n"
            + "Parameters: INDEX (must be a positive integer) "
            + PREFIX_ASSESSMENT_NAME + "ASSESSMENT NAME\n"
            + "Example: " + COMMAND_WORD + " 1 "
            + PREFIX_ASSESSMENT_NAME + "Midterms";

    public static final String MESSAGE_DELETE_ASSESSMENT_SUCCESS = "Deleted Assessment: %1$s";

    public static final String MESSAGE_ASSESSMENT_NOT_FOUND = "The assessment indicated does not exist";

    private final Index targetIndex;
    private final AssessmentName assessmentName;

    /**
     * Creates an DeleteAssessmentCommand to remove the specified {@code Assessment} from the student.
     */
    public DeleteAssessmentCommand(Index targetIndex, AssessmentName assessmentName) {
        this.targetIndex = targetIndex;
        this.assessmentName = assessmentName;
    }

    @Override
    public CommandResult execute(Model model) throws CommandException {
        requireNonNull(model);
        List<Student> lastShownList = model.getFilteredStudentList();

        if (targetIndex.getZeroBased() >= lastShownList.size()) {
            throw new CommandException(Messages.MESSAGE_INVALID_STUDENT_DISPLAYED_INDEX);
        }

        Student student = lastShownList.get(targetIndex.getZeroBased());

        // Get the assessment that we want to delete, indicated by the given assessment name
        student.updateFilteredAssessmentList(
                new AssessmentNameMatchesKeywordPredicate(assessmentName.toString()));

        if (student.getFilteredAssessmentList().size() == 0) {
            throw new CommandException(MESSAGE_ASSESSMENT_NOT_FOUND);
        }

        Assessment assessmentToDelete = student.getFilteredAssessmentList().get(0);
        model.deleteAssessment(student, assessmentToDelete);
        return new CommandResult(String.format(MESSAGE_DELETE_ASSESSMENT_SUCCESS,
                assessmentToDelete.getAssessmentName()), false, false, student);
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof DeleteAssessmentCommand // instanceof handles nulls
                && targetIndex.equals(((DeleteAssessmentCommand) other).targetIndex)) // state check
                && assessmentName.equals(((DeleteAssessmentCommand) other).assessmentName);
    }
}
