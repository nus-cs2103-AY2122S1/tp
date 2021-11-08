package seedu.gamebook.ui;

import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.layout.Region;
import seedu.gamebook.model.gameentry.GameEntry;
import seedu.gamebook.model.stats.Average;
import seedu.gamebook.model.stats.Median;

public class StatsPanel extends UiPart<Region> {
    private static final String FXML = "StatsPanel.fxml";
    private ObservableList<GameEntry> gameEntryList;
    @FXML
    private Label mean;

    @FXML
    private Label median;

    /**
     * Constructor used to initialise a StatsPanel using a given GameEntry list
     * @param gameEntries
     */
    public StatsPanel(ObservableList<GameEntry> gameEntries) {
        super(FXML);
        this.gameEntryList = gameEntries;
    }

    public void getStats() {
        this.mean.setText(String.format("%.2f", Average.getOverallAverage(gameEntryList)));
        this.median.setText(String.format("%.2f", Median.getOverallMedian(gameEntryList)));
    }

    /**
     * Clears the existing values for the statistics, re-initialises the GameEntry list and recalculates the statistics
     * @param gameEntries
     */
    public void updateStats(ObservableList<GameEntry> gameEntries) {
        this.gameEntryList = gameEntries;
        this.getStats();
    }


}

