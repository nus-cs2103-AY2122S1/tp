package seedu.address.logic.commands.followupaction;

import static java.util.Objects.requireNonNull;

import seedu.address.ui.PieChartDisplayer;

public class ShowPieChartAction implements CommandFollowUpAction {

    private final PieChartDisplayer pieChartDisplayer;

    public ShowPieChartAction(PieChartDisplayer pieChartDisplayer) {
        this.pieChartDisplayer = requireNonNull(pieChartDisplayer);
    }

    @Override
    public void execute() {
        pieChartDisplayer.displayPieChart();
    }

}
