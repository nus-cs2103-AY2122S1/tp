package seedu.address.ui;

import javafx.beans.binding.Bindings;
import javafx.fxml.FXML;
import javafx.geometry.Insets;
import javafx.scene.control.Label;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.layout.CornerRadii;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Region;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;
import seedu.address.model.task.Task;

/**
 * An UI component that displays information of a {@code Task}.
 */
public class TaskCard extends UiPart<Region> {
    private static final String FXML = "TaskListCard.fxml";

    public final Task task;
    public final int displayedIndex;

    @FXML
    private HBox descriptionPane;
    @FXML
    private Label id;
    @FXML
    private Text taskName;

    /**
     * Creates a {@code TaskCard} with the given {@code Task} and index to display.
     */
    public TaskCard(Task task, int displayedIndex) {
        super(FXML);
        this.task = task;
        this.displayedIndex = displayedIndex;
    }

    /**
     * Initialises properties of FXML nodes of this TaskCard using {@code vbox}'s properties.
     * @param value The extra amount to be reduced from the Text's width.
     */
    public void initialise(VBox vbox, double value) {

        // Sets width property of HBox to be dependent on VBox's width and padding property.
        descriptionPane.maxWidthProperty().bind(Bindings.createDoubleBinding(()->
                vbox.getWidth() - vbox.getPadding().getLeft() - vbox.getPadding().getRight() - 1,
                vbox.widthProperty(), vbox.paddingProperty()));

        // Sets width property of Text to be dependent on VBox's width and padding property.
        taskName.wrappingWidthProperty().bind(Bindings.createDoubleBinding(()->
                vbox.getWidth() - vbox.getPadding().getLeft() - vbox.getPadding().getRight() - value,
                vbox.widthProperty(), vbox.paddingProperty()));

        id.setText(displayedIndex + ". ");
        String name = task.getTaskName().taskName;
        String taskDate = task.getDate() == null ? "" : task.getDate().taskDate.toString();
        String taskTime = task.getTime() == null ? "" : task.getTime().taskTime.toString();
        String taskVenue = task.getVenue() == null ? "" : task.getVenue().venue;
        String taskString = "Task: " + name
                + "\nDate: " + taskDate + "; Time: " + taskTime + "; Venue: " + taskVenue;
        if (task.getDone()) {
            descriptionPane.setBackground(new Background(
                    new BackgroundFill(javafx.scene.paint.Paint.valueOf("green"), CornerRadii.EMPTY, Insets.EMPTY)));
        }
        task.updateDueDate();
        if (task.getIsDueSoon()) {
            descriptionPane.setBackground(new Background(
                    new BackgroundFill(javafx.scene.paint.Paint.valueOf("darkorange"),
                    CornerRadii.EMPTY, Insets.EMPTY)));
        }
        if (task.getIsOverdue()) {
            descriptionPane.setBackground(new Background(
                    new BackgroundFill(javafx.scene.paint.Paint.valueOf("red"), CornerRadii.EMPTY, Insets.EMPTY)));
        }
        taskName.setText(taskString);
    }
}
