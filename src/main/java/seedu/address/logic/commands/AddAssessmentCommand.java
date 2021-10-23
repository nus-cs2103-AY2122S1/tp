package seedu.address.logic.commands;

import static java.util.Objects.requireNonNull;
import static seedu.address.logic.parser.CliSyntax.PREFIX_ASSESSMENT_NAME;
import static seedu.address.logic.parser.CliSyntax.PREFIX_NAME;
import static seedu.address.logic.parser.CliSyntax.PREFIX_SCORE;

import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.model.Model;
import seedu.address.model.assessment.Assessment;
import seedu.address.model.student.Student;

public class AddAssessmentCommand extends Command {

    public static final String COMMAND_WORD = "addassessment";

    public static final String MESSAGE_USAGE = COMMAND_WORD + ": Adds a student's assessment to the address book. "
            + "Parameters: "
            + PREFIX_NAME + "NAME "
            + PREFIX_ASSESSMENT_NAME + "ASSESSMENT NAME "
            + PREFIX_SCORE + "SCORE "
            + "Example: " + COMMAND_WORD + " "
            + PREFIX_NAME + "John Doe "
            + PREFIX_ASSESSMENT_NAME + "Midterms "
            + PREFIX_SCORE + "60/100 ";

    public static final String MESSAGE_SUCCESS = "New assessment added to %1$s: %2$s";
    public static final String MESSAGE_DUPLICATE_ASSESSMENT = "This assessment already exists in %1$s assessments";
    public static final String MESSAGE_STUDENT_NONEXISTENT =
            "The student indicated does not exist. Please add the student first.";

    private final Student student;
    private final Assessment toAdd;

    /**
     * Creates an AddAssessmentCommand to add the specified {@code Assessment} to the student.
     */
    public AddAssessmentCommand(Student student, Assessment toAdd) {
        requireNonNull(student);
        requireNonNull(toAdd);
        this.student = student;
        this.toAdd = toAdd;
    }

    @Override
    public CommandResult execute(Model model) throws CommandException {
        requireNonNull(model);

        if (!model.hasStudent(student)) {
            throw new CommandException(MESSAGE_STUDENT_NONEXISTENT);
        }

        if (model.hasAssessment(student, toAdd)) {
            throw new CommandException(MESSAGE_DUPLICATE_ASSESSMENT);
        }

        Assessment assessmentToAdd = new Assessment(toAdd.getAssessmentName(), toAdd.getScore());

        // Add student with the group fetched from the data in the model
        model.addAssessment(student, assessmentToAdd);
        return new CommandResult(String.format(MESSAGE_SUCCESS, student.getName(), toAdd));
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof AddAssessmentCommand // instanceof handles nulls
                && student.equals(((AddAssessmentCommand) other).student)
                && toAdd.equals(((AddAssessmentCommand) other).toAdd));
    }
}
