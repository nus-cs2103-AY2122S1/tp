package seedu.address.ui;

import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.layout.Region;
import javafx.scene.layout.StackPane;
import seedu.address.model.ViewingType;
import seedu.address.model.lesson.LessonWithAttendees;
import seedu.address.model.person.PersonWithDetails;

/**
 * Class for managing the viewing panel.
 * That includes responsibilities such as showing the schedule, or viewing a particular student.
 */
public class ViewingPanel extends UiPart<Region> {

    private static final String FXML = "ViewingPanel.fxml";

    @FXML
    private StackPane viewingPanel;

    private PersonViewCard personViewCard;
    private LessonSchedulePanel schedulePanel;

    /**
     * Constructs a Viewing Panel with the given observable values
     */
    public ViewingPanel(ObservableValue<ViewingType> viewType, ObservableValue<PersonWithDetails> peronWithDetails,
            ObservableList<LessonWithAttendees> lessonWithAttendees) {
        super(FXML);
        personViewCard = new PersonViewCard(peronWithDetails);
        schedulePanel = new LessonSchedulePanel(lessonWithAttendees);

        // main logic on what to do when view type changes in model!
        ChangeListener<? super ViewingType> changeListener = new ChangeListener<ViewingType>() {
            @Override
            public void changed(ObservableValue<? extends ViewingType> observable,
                                ViewingType oldValue, ViewingType newValue) {
                changeOnValue(newValue);
            }
        };
        viewingPanel.getChildren().clear();
        viewingPanel.getChildren().addAll(schedulePanel.getRoot());
        viewType.addListener(changeListener);
    }

    /**
     * Changes the viewing panel depending what the current ViewingType is
     * @param value the current viewing type
     */
    private void changeOnValue(ViewingType value) {
        viewingPanel.getChildren().clear();
        switch(value) {
        case SCHEDULE:
            viewingPanel.getChildren().addAll(schedulePanel.getRoot());
            break;
        case PERSON:
            viewingPanel.getChildren().addAll(personViewCard.getRoot());
            break;
        default:
            break;
        }
    }
}
