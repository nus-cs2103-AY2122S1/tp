package seedu.address.logic.commands;

import static java.util.Objects.requireNonNull;
import static seedu.address.logic.parser.CliSyntax.PREFIX_ASSESSMENT;

import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.model.Model;
import seedu.address.model.student.Assessment;
import seedu.address.model.student.AssessmentStatistics;

public class ShowAssessmentCommand extends Command {
    public static final String COMMAND_WORD = "show assessment";

    public static final String MESSAGE_USAGE = COMMAND_WORD + ": Displays an assessment and its statistics. \n"
            + "Parameters: "
            + PREFIX_ASSESSMENT + "<assessment_name>\n"
            + "Example: " + COMMAND_WORD + " "
            + PREFIX_ASSESSMENT + "Midterm";

    public static final String MESSAGE_SUCCESS = "Assessment displayed: %1$s";
    public static final String MESSAGE_NONEXISTENT_ASSESSMENT = "Assessment \"%1$s\" does not exist.";

    private final Assessment toDisplay;

    public ShowAssessmentCommand(Assessment assessment) {
        requireNonNull(assessment);
        this.toDisplay = assessment;
    }

    @Override
    public CommandResult execute(Model model) throws CommandException {
        requireNonNull(model);

        if (!model.hasAssessment(toDisplay)) {
            throw new CommandException(String.format(MESSAGE_NONEXISTENT_ASSESSMENT, toDisplay.getValue()));
        }

        Assessment assessmentFound = model.getAssessment(toDisplay);
        assert assessmentFound != null;

        AssessmentStatistics statistics = new AssessmentStatistics(assessmentFound);

        return new CommandResult(String.format(MESSAGE_SUCCESS, assessmentFound.getValue()), statistics.toBarChart());
    }
}
