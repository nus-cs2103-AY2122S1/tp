package seedu.tracker.ui;

import java.util.Comparator;

import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.layout.FlowPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Region;
import seedu.tracker.model.calendar.AcademicCalendar;
import seedu.tracker.model.calendar.AcademicYear;
import seedu.tracker.model.calendar.Semester;
import seedu.tracker.model.module.CompletedMcList;
import seedu.tracker.model.module.Mc;
import seedu.tracker.model.module.Module;

/**
 * An UI component that displays information of a {@code Module}.
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

    /**
     * Creates a {@code ModuleCard} with the given {@code Module} and index to display.
     */
    public McCard(Mc mc, int index) {
        super(FXML);
        this.mc = mc;
        title.setText(getTitle(index));
        currMc.setText(String.valueOf(mc.value));

        int target = CompletedMcList.requirementsList[index];
        customiseStyle(mc.value, target);
        targetMc.setText(" / " + target + " MCs");
    }

    private String getTitle(int index) {
        switch (index) {
        case CompletedMcList.GE_INDEX:
            return "GE";
        case CompletedMcList.UE_INDEX:
            return "UE";
        case CompletedMcList.FOUNDATION_INDEX:
            return "Foundation";
        case CompletedMcList.BREADTH_DEPTH_INDEX:
            return "Breath and Depth";
        case CompletedMcList.PROFESSIONALISM_INDEX:
            return "IT Professionalism";
        case CompletedMcList.MATH_SCIENCE_INDEX:
            return "Math and Science";
        default:
            assert false; //should never reach here
            return "Module";
        }
    }

    private void customiseStyle(int mcValue, int target) {
        if (mcValue < target) {
            currMc.setStyle("-fx-text-fill: pink;");
        } else {
            currMc.setStyle("-fx-text-fill: paleturquoise;");
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

