package seedu.smartnus.ui.card;

import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Region;
import seedu.smartnus.model.statistic.TagStatistic;
import seedu.smartnus.ui.UiPart;

/**
 * An UI component that displays information of a {@code Question}.
 */
public class StatisticCard extends UiPart<Region> {

    private static final String FXML = "StatisticListCard.fxml";

    /**
     * Note: Certain keywords such as "location" and "resources" are reserved keywords in JavaFX.
     * As a consequence, UI elements' variable names cannot be set to such keywords
     * or an exception will be thrown by JavaFX during runtime.
     *
     * @see <a href="https://github.com/se-edu/addressbook-level4/issues/336">The issue on AddressBook level 4</a>
     */

    public final TagStatistic statistic;
    private int displayedIndex;

    @FXML
    private HBox cardPane;
    @FXML
    private Label id;
    @FXML
    private Label name;
    @FXML
    private Label attempt;
    @FXML
    private Label correct;
    @FXML
    private Label percentage;

    /**
     * Creates a {@code QuestionCard} with the given {@code Question} and index to display.
     */
    public StatisticCard(TagStatistic statistic, int displayedIndex) {
        super(FXML);
        this.statistic = statistic;
        this.displayedIndex = displayedIndex;

        id.setText(displayedIndex + ". ");
        name.setText(statistic.getTitle());
        name.setWrapText(true);

        attempt.setText("Attempts: " + statistic.getAttemptCount());
        correct.setText("Correct Attempts: " + statistic.getCorrectCount());
        percentage.setText("Performance: " + statistic.getCorrectPercentage() + "%");
    }

    @Override
    public boolean equals(Object other) {
        // short circuit if same object
        if (other == this) {
            return true;
        }

        // instanceof handles nulls
        if (!(other instanceof StatisticCard)) {
            return false;
        }

        // state check
        StatisticCard card = (StatisticCard) other;
        return displayedIndex == card.displayedIndex
                && statistic.equals(card.statistic);
    }
}
