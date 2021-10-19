package dash.ui;

import java.util.Comparator;

import dash.model.task.Task;
import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.FlowPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Region;


/**
 * An UI component that displays information of a {@code Person}.
 */
public class TaskCard extends UiPart<Region> {

    private static final String FXML = "TaskListCard.fxml";

    /**
     * Note: Certain keywords such as "location" and "resources" are reserved keywords in JavaFX.
     * As a consequence, UI elements' variable names cannot be set to such keywords
     * or an exception will be thrown by JavaFX during runtime.
     *
     * @see <a href="https://github.com/se-edu/addressbook-level4/issues/336">The issue on AddressBook level 4</a>
     */

    public final Task task;

    private int cardIndex = 0;
    private Image checkmark_orange = new Image("/images/checkmark.png", 20, 20, false, true);
    private Image checkmark_grey = new Image("/images/checkmark_greyed.png", 20, 20, false, true);

    @FXML
    private HBox cardPane;
    @FXML
    private Label desc;
    @FXML
    private Label id;
    @FXML
    private Label completionStatus;
    @FXML
    private Label date;
    @FXML
    private Label time;
    @FXML
    private FlowPane tags;

    /**
     * Creates a {@code TaskCode} with the given {@code Task} and index to display.
     */
    public TaskCard(Task task, int displayedIndex) {
        super(FXML);
        this.task = task;
        id.setText(displayedIndex + ". ");
        this.cardIndex = displayedIndex;
        desc.setText(task.getTaskDescription().description);

        ImageView checkmark = new ImageView(checkmark_grey);
        completionStatus.setText(" ");
        completionStatus.setGraphic(checkmark);
        if(this.task.getCompletionStatus().get()) {
            setAsComplete();
        }

        if (task.getTaskDate().getDate() != null) {
            date.setText(task.getTaskDate().toDateString());
        }
        if (task.getTaskDate().getTime() != null) {
            time.setText(task.getTaskDate().toTimeString());
        }

        task.getTags().stream()
                .sorted(Comparator.comparing(tag -> tag.tagName))
                .forEach(tag -> tags.getChildren().add(new Label(tag.tagName)));
    }

    public void setAsComplete() {
        if (this.cardIndex % 2 == 0) {
            this.cardPane.setStyle("-fx-background-color: #ebcbae;");
        } else {
            this.cardPane.setStyle("-fx-background-color: #ffe6cf;");
        }
        this.desc.setStyle("-fx-text-fill: #878787");
        this.id.setStyle("-fx-text-fill: #878787");
        ImageView checkmark = new ImageView(checkmark_orange);
        completionStatus.setText(" ");
        completionStatus.setGraphic(checkmark);
        this.date.setStyle("-fx-text-fill: #878787");
        this.time.setStyle("-fx-text-fill: #878787");
        for (Node n: tags.getChildren()) {
            Label l = (Label) n;
            l.setStyle("-fx-background-color: #bf9284");
        }

    }

    @Override
    public boolean equals(Object other) {
        // short circuit if same object
        if (other == this) {
            return true;
        }

        // instanceof handles nulls
        if (!(other instanceof TaskCard)) {
            return false;
        }

        // state check
        TaskCard card = (TaskCard) other;
        return id.getText().equals(card.id.getText())
                && task.equals(card.task);
    }
}
