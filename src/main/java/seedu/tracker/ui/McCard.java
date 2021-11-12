package seedu.tracker.ui;

import static seedu.tracker.model.module.McProgressList.BREADTH_DEPTH_TAG_TITLE;
import static seedu.tracker.model.module.McProgressList.FOUNDATION_TAG_TITLE;
import static seedu.tracker.model.module.McProgressList.GE_TAG_TITLE;
import static seedu.tracker.model.module.McProgressList.MATH_SCIENCE_TAG_TITLE;
import static seedu.tracker.model.module.McProgressList.PROFESSIONALISM_TAG_TITLE;
import static seedu.tracker.model.module.McProgressList.UE_TAG_TITLE;

import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.ProgressBar;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Region;
import seedu.tracker.model.module.Mc;
import seedu.tracker.model.module.McProgress;
import seedu.tracker.model.module.McProgressList;

/**
 * An UI component that displays information of a {@code McProgress}.
 */
public class McCard extends UiPart<Region> {

    private static final String FXML = "McListCard.fxml";

    /**
     * Note: Certain keywords such as "location" and "resources" are reserved keywords in JavaFX.
     * As a consequence, UI elements' variable names cannot be set to such keywords
     * or an exception will be thrown by JavaFX during runtime.
     *
     * @see <a href="https://github.com/se-edu/addressbook-level4/issues/336">The issue on ModuleTracker level 4</a>
     */

    public final Mc mc;

    @FXML
    private HBox cardPane;
    @FXML
    private Label title;
    @FXML
    private Label currMc;
    @FXML
    private Label targetMc;
    @FXML
    private ProgressBar progressBar;

    /**
     * Creates a {@code McCard} with the given {@code McProgress}.
     */
    public McCard(McProgress progress, int index) {
        super(FXML);
        this.mc = progress.getCompleted();
        currMc.setText(String.valueOf(mc.value));

        title.setText(getTitle(index));

        int target = progress.getTarget().value;
        targetMc.setText(" / " + target + " MCs");

        double ratio = progress.getCompletionRatio();
        progressBar.setProgress(ratio);

        customiseStyle(progress);
    }

    private String getTitle(int index) {
        switch (index) {
        case McProgressList.GE_INDEX:
            return GE_TAG_TITLE;
        case McProgressList.UE_INDEX:
            return UE_TAG_TITLE;
        case McProgressList.FOUNDATION_INDEX:
            return FOUNDATION_TAG_TITLE;
        case McProgressList.BREADTH_DEPTH_INDEX:
            return BREADTH_DEPTH_TAG_TITLE;
        case McProgressList.PROFESSIONALISM_INDEX:
            return PROFESSIONALISM_TAG_TITLE;
        case McProgressList.MATH_SCIENCE_INDEX:
            return MATH_SCIENCE_TAG_TITLE;
        default:
            return "Total";
        }
    }

    private void customiseStyle(McProgress progress) {
        if (!progress.isCompleted()) {
            currMc.setStyle("-fx-text-fill: pink;");
            progressBar.setStyle("-fx-accent: pink;");
        } else {
            currMc.setStyle("-fx-text-fill: paleturquoise;");
            progressBar.setStyle("-fx-accent: paleturquoise;");
        }
    }

    @Override
    public boolean equals(Object other) {
        // short circuit if same object
        if (other == this) {
            return true;
        }

        // instanceof handles nulls
        if (!(other instanceof ModuleCard)) {
            return false;
        }

        // state check
        McCard card = (McCard) other;
        return title.getText().equals(card.title.getText())
                && mc.equals(card.mc);
    }
}

