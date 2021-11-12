package seedu.address.ui;

import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.layout.FlowPane;
import javafx.scene.layout.Priority;
import javafx.scene.layout.Region;
import javafx.scene.layout.VBox;
import seedu.address.commons.util.StringUtil;
import seedu.address.model.friend.Day;
import seedu.address.model.friend.exceptions.InvalidDayTimeException;
import seedu.address.ui.util.SampleStyles;

/**
 * A UI component that displays information of a {@code Day} in a {@code Schedule}.
 */
public class FriendScheduleDayCard extends UiPart<Region> {

    private static final String FXML = "FriendScheduleDayCard.fxml";

    /**
     * Note: Certain keywords such as "location" and "resources" are reserved keywords in JavaFX.
     * As a consequence, UI elements' variable names cannot be set to such keywords
     * or an exception will be thrown by JavaFX during runtime.
     *
     * @see <a href="https://github.com/se-edu/addressbook-level4/issues/336">The issue on AddressBook level 4</a>
     */

    public final Day day;
    @FXML
    private Label dayName;
    @FXML
    private FlowPane timeslots;

    /**
     * Creates a {@code FriendCard} with the given {@code Friend} and index to display.
     */
    public FriendScheduleDayCard(Day day) {
        super(FXML);
        this.day = day;
        dayName.setText(StringUtil.toTitleCase(day.getDayName()));
        timeslots.setHgap(20.0);
        timeslots.setVgap(15.0);
        VBox.setVgrow(timeslots, Priority.ALWAYS);
        try {
            this.day.getGroupedTimeSlots()
                    .forEach(timeslot -> {
                        String timeslotPeriod = timeslot[0] + " - " + timeslot[1];
                        Label label = new Label(timeslotPeriod);
                        label.setBackground(SampleStyles.BLURPLE_BACKGROUND);
                        timeslots.getChildren().add(label);
                    });
        } catch (InvalidDayTimeException e) {
            Label label = new Label("Invalid timeslots found, please reset Friend data.");
            timeslots.getChildren().add(label);
        }
    }


    @Override
    public boolean equals(Object other) {
        // short circuit if same object
        if (other == this) {
            return true;
        }

        // instanceof handles nulls
        if (!(other instanceof FriendScheduleDayCard)) {
            return false;
        }

        // state check
        FriendScheduleDayCard card = (FriendScheduleDayCard) other;
        return dayName.getText().equals(card.dayName.getText())
                && day.equals(card.day);
    }
}
