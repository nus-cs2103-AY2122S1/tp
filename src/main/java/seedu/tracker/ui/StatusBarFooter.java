package seedu.tracker.ui;

import java.nio.file.Path;
import java.nio.file.Paths;

import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Priority;
import javafx.scene.layout.Region;
import seedu.tracker.model.ReadOnlyUserInfo;

/**
 * A ui for the status bar that is displayed at the footer of the application.
 */
public class StatusBarFooter extends UiPart<Region> {

    private static final String FXML = "StatusBarFooter.fxml";

    @FXML
    private Label saveLocationStatus;

    @FXML
    private Label currentSemester;

    @FXML
    private Label mcGoal;

    @FXML
    private Region blankRegion;

    @FXML
    private HBox footerContainer;

    /**
     * Creates a {@code StatusBarFooter} with the given {@code Path}.
     */
    public StatusBarFooter(Path saveLocation, ReadOnlyUserInfo userInformation) {
        super(FXML);
        saveLocationStatus.setText(Paths.get(".").resolve(saveLocation).toString());

        String calender = userInformation.getCurrentSemester().toString();
        calender = calender.substring(0, 1).toUpperCase() + calender.substring(1, 8)
                + calender.substring(8, 9).toUpperCase() + calender.substring(9);
        currentSemester.setText(calender);

        mcGoal.setText("MC goal : " + userInformation.getMcGoal().toString());

        footerContainer.setHgrow(blankRegion, Priority.ALWAYS);
    }

}
