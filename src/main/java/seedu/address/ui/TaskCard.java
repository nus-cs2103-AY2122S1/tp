package seedu.address.ui;

import javafx.beans.binding.Bindings;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.ListCell;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Region;
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
    private Label taskName;

    /**
     * Creates a {@code TaskCard} with the given {@code Task} and index to display.
     */
    public TaskCard(Task task, int displayedIndex) {
        super(FXML);
        this.task = task;
        this.displayedIndex = displayedIndex;
    }

    /**
     * Initialises properties of FXML nodes of this TaskCard using {@code lc}'s properties.
     */
    public void initialise(ListCell<Task> lc) {

        // Sets width property of HBox to be dependent on list cell's width and padding property.
        descriptionPane.maxWidthProperty().bind(Bindings.createDoubleBinding(()->
            lc.getWidth() - lc.getPadding().getLeft() - lc.getPadding().getRight() - 1,
            lc.widthProperty(), lc.paddingProperty()));

        id.setText(displayedIndex + ". ");
        taskName.setText(task.getTaskName());
    }
}
