package seedu.address.logic.commands;

import static java.util.Objects.requireNonNull;
import static seedu.address.logic.commands.AddApplicantCommand.MESSAGE_NO_SUCH_POSITION;
import static seedu.address.logic.parser.CliSyntax.PREFIX_TITLE;
import static seedu.address.model.applicant.Application.ApplicationStatus;

import java.util.Arrays;
import java.util.stream.Collectors;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.chart.PieChart;
import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.model.Model;
import seedu.address.model.applicant.Applicant;
import seedu.address.model.position.Position;
import seedu.address.model.position.Title;
import seedu.address.model.position.exceptions.PositionNotFoundException;

// Adds a position to MrTechRecruiter.
public class VisualizePositionCommand extends Command {

    public static final String COMMAND_WORD = "visualize";

    public static final String MESSAGE_USAGE = COMMAND_WORD
            + ": Displays a visual representation of a position. "
            + "Parameters: "
            + PREFIX_TITLE + "TITLE "
            + "Example: " + COMMAND_WORD + " "
            + PREFIX_TITLE + "software engineer ";

    public static final String MESSAGE_SUCCESS = "Position displayed: %1$s";

    private final Title toShowTitle;

    /**
     * Creates a VisualizePositionCommand to visualize the specified {@code Position}
     */
    public VisualizePositionCommand(Title position) {
        requireNonNull(position);
        toShowTitle = position;
    }

    @Override
    public CommandResult execute(Model model) throws CommandException {
        requireNonNull(model);

        Position positionToShow;
        try {
            positionToShow = model.getPositionByTitle(toShowTitle);
        } catch (PositionNotFoundException e) {
            throw new CommandException(MESSAGE_NO_SUCH_POSITION);
        }

        assert positionToShow != null; // UniquePositionList#getPositionByTitle handles the 'Position not found' case

        PieChart positionChart = createPieChart(model.getFilteredApplicantList(), positionToShow);
        positionChart.setTitle(positionToShow.getTitle().fullTitle);

        return new CommandResult(String.format(MESSAGE_SUCCESS, toShowTitle));
    }

    /**
     * Processes an {@code ObservableList} of applicants under a specified position
     * into an {@code ObservableList} of {@code PieChart.Data}, where each list item
     * represents a slice in a pie chart.
     */
    public PieChart createPieChart(ObservableList<Applicant> applicants, Position position) {
        return createPieChart(applicants.filtered(applicant -> applicant.isApplyingTo(position)));
    }

    private PieChart createPieChart(ObservableList<Applicant> applicants) {
        int applicantCount = applicants.size();
        ObservableList<PieChart.Data> pieChartData = Arrays.stream(ApplicationStatus.values())
                .map(applicationStatus -> createPieChartData(applicants, applicantCount, applicationStatus))
                .collect(Collectors.toCollection(FXCollections::observableArrayList));

        return new PieChart(pieChartData);
    }

    /**
     * Internal method to create a {@code PieChart.Data} object from a given application status.
     */
    private PieChart.Data createPieChartData(
            ObservableList<Applicant> applicants, int applicantCount, ApplicationStatus status) {
        int applicantsWithStatusCount = applicants.filtered(applicant ->
                        applicant.hasApplicationStatus(status)).size();
        double percentage = (double) applicantsWithStatusCount / applicantCount;

        return new PieChart.Data(status.toString(), percentage);
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof VisualizePositionCommand // instanceof handles nulls
                && toShowTitle.equals(((VisualizePositionCommand) other).toShowTitle));
    }
}
