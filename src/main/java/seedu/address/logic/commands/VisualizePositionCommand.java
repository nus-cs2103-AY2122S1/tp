package seedu.address.logic.commands;

import static java.util.Objects.requireNonNull;
import static seedu.address.logic.commands.AddApplicantCommand.MESSAGE_NO_SUCH_POSITION;
import static seedu.address.logic.parser.CliSyntax.PREFIX_TITLE;

import javafx.scene.chart.PieChart;
import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.logic.commands.followupaction.CommandFollowUpAction;
import seedu.address.logic.commands.followupaction.ShowPieChartAction;
import seedu.address.model.Model;
import seedu.address.model.position.Position;
import seedu.address.model.position.Title;
import seedu.address.model.position.exceptions.PositionNotFoundException;
import seedu.address.ui.PieChartDisplayer;
import seedu.address.ui.PositionPieChartFactory;

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

        Position position;
        try {
            position = model.getPositionByTitle(toShowTitle);
        } catch (PositionNotFoundException e) {
            throw new CommandException(MESSAGE_NO_SUCH_POSITION);
        }

        assert position != null; // Above try-catch block handles the 'Position not found' case

        PieChart positionChart = PositionPieChartFactory.createPieChart(model.getFilteredApplicantList(), position);
        PieChartDisplayer positionChartDisplayer = new PieChartDisplayer(positionChart);
        CommandFollowUpAction showPositionPieChart = new ShowPieChartAction(positionChartDisplayer);

        return new CommandResult(
                String.format(MESSAGE_SUCCESS, toShowTitle), false, showPositionPieChart, false);
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof VisualizePositionCommand // instanceof handles nulls
                && toShowTitle.equals(((VisualizePositionCommand) other).toShowTitle));
    }
}
