package seedu.address.logic.commands;

import static java.util.Objects.requireNonNull;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.chart.PieChart;

/**
 * Represents the result of a command execution.
 */
public class CommandResult {

    private final String feedbackToUser;

    private final Map<String, Map<String, Integer>> data;

    /** Help information should be shown to the user. */
    private final boolean showHelp;

    /** The application should exit. */
    private final boolean exit;

    /**
     * Constructs a {@code CommandResult} with the specified fields.
     */
    public CommandResult(String feedbackToUser, boolean showHelp,
                         boolean exit, Map<String, Map<String, Integer>> data) {
        this.feedbackToUser = requireNonNull(feedbackToUser);
        this.showHelp = showHelp;
        this.exit = exit;
        this.data = data;
    }

    public CommandResult(String feedbackToUser, boolean showHelp, boolean exit) {
        this(feedbackToUser, showHelp, exit, new HashMap<>());
    }

    /**
     * Constructs a {@code CommandResult} with the specified {@code feedbackToUser} and {@code data},
     * and other fields set to their default value.
     */
    public CommandResult(String feedbackToUser, Map<String, Map<String, Integer>> data) {
        this(feedbackToUser, false, false, data);
    }

    /**
     * Constructs a {@code CommandResult} with the specified {@code feedbackToUser},
     * and other fields set to their default value.
     */
    public CommandResult(String feedbackToUser) {
        this(feedbackToUser, false, false, new HashMap<>());
    }

    public String getFeedbackToUser() {
        return feedbackToUser;
    }

    public List<String> getPieChartTitles() {
        return new ArrayList<>(data.keySet());
    }

    public List<ObservableList<PieChart.Data>> getPieChartDatas() {
        if (data.isEmpty()) {
            return null;
        }

        List<ObservableList<PieChart.Data>> pieChartDatas = new ArrayList<>();

        for (Map.Entry<String, Map<String, Integer>> entry : data.entrySet()) {
            Map<String, Integer> map = entry.getValue();
            ObservableList<PieChart.Data> pieChartData = FXCollections.observableArrayList();

            for (Map.Entry<String, Integer> item : map.entrySet()) {
                String key = item.getKey();
                Integer value = item.getValue();

                pieChartData.add(new PieChart.Data(key, value));
            }

            pieChartDatas.add(pieChartData);
        }

        return pieChartDatas;
    }

    public boolean isShowFeedback() {
        return data.isEmpty();
    }

    public boolean isShowPieChart() {
        return !data.isEmpty();
    }

    public boolean isShowHelp() {
        return showHelp;
    }

    public boolean isExit() {
        return exit;
    }

    @Override
    public boolean equals(Object other) {
        if (other == this) {
            return true;
        }

        // instanceof handles nulls
        if (!(other instanceof CommandResult)) {
            return false;
        }

        CommandResult otherCommandResult = (CommandResult) other;
        return feedbackToUser.equals(otherCommandResult.feedbackToUser)
                && showHelp == otherCommandResult.showHelp
                && exit == otherCommandResult.exit;
    }

    @Override
    public int hashCode() {
        return Objects.hash(feedbackToUser, showHelp, exit);
    }

}
