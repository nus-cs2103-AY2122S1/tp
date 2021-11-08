package dash.ui;

import java.util.Comparator;

import dash.model.task.Task;
import javafx.fxml.FXML;
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
    private Image checkmarkDone = new Image("/images/checkmark.png", 20, 20, false, true);
    private Image checkmarkNotDone = new Image("/images/checkmark_greyed.png", 20, 20, false, true);
    private Image tag = new Image("/images/tag.png", 20, 20, false, true);
    private Image tagGrey = new Image("/images/tag_greyed.png", 20, 20, false, true);
    private Image contact = new Image("/images/contact.png", 20, 20, false, true);
    private Image contactGrey = new Image("/images/contact_greyed.png", 20, 20, false, true);
    private Image calendar = new Image("/images/date.png", 20, 20, false, true);
    private Image calendarGrey = new Image("/images/date_greyed.png", 20, 20, false, true);
    private Image clock = new Image("/images/time.png", 20, 20, false, true);
    private Image clockGrey = new Image("/images/time_greyed.png", 20, 20, false, true);


    @FXML
    private HBox cardPane;
    @FXML
    private Label desc;
    @FXML
    private Label id;
    @FXML
    private Label completionStatus;
    @FXML
    private Label dateImage;
    @FXML
    private Label date;
    @FXML
    private Label timeImage;
    @FXML
    private Label time;
    @FXML
    private Label tagImage;
    @FXML
    private FlowPane tags;
    @FXML
    private Label assigneeImage;
    @FXML
    private FlowPane assignees;

    /**
     * Creates a {@code TaskCode} with the given {@code Task} and index to display.
     */
    public TaskCard(Task task, int displayedIndex) {
        super(FXML);
        this.task = task;
        id.setText(displayedIndex + ". ");
        this.cardIndex = displayedIndex;
        desc.setText(task.getTaskDescription().description);

        ImageView checkmark = new ImageView(checkmarkNotDone);
        completionStatus.setText(" ");
        completionStatus.setGraphic(checkmark);

        ImageView tagIcon = new ImageView(tag);
        tagImage.setText(" ");
        tagImage.setGraphic(tagIcon);

        ImageView assigneeIcon = new ImageView(contact);
        assigneeImage.setText(" ");
        assigneeImage.setGraphic(assigneeIcon);

        ImageView dateIcon = new ImageView(calendar);
        dateImage.setText(" ");
        dateImage.setGraphic(dateIcon);

        ImageView timeIcon = new ImageView(clock);
        timeImage.setText(" ");
        timeImage.setGraphic(timeIcon);

        if (task.getTaskDate().hasDate()) {
            date.setText(task.getTaskDate().toDateString());
        }
        if (task.getTaskDate().hasTime()) {
            time.setText(task.getTaskDate().toTimeString());
        }

        task.getTags().stream()
                .sorted(Comparator.comparing(tag -> tag.tagName))
                .forEach(tag -> tags.getChildren().add(new Label(tag.tagName)));

        task.getPeople().stream()
                .sorted(Comparator.comparing(person -> person.getName().fullName))
                .forEach(person -> assignees.getChildren().add(new Label(person.getName().fullName)));

        if (this.task.getCompletionStatus().get()) {
            setAsComplete();
        }
    }

    public void setAsComplete() {
        this.desc.setStyle("-fx-opacity:0.5;");
        this.id.setStyle("-fx-opacity:0.5;");
        ImageView checkmark = new ImageView(checkmarkDone);
        completionStatus.setText("");
        completionStatus.setGraphic(checkmark);

        this.date.setStyle("-fx-opacity:0.5;");
        this.time.setStyle("-fx-opacity:0.5;");

        this.tags.setStyle("-fx-opacity:0.5;");
        ImageView tagIconGrey = new ImageView(tagGrey);
        tagImage.setText(" ");
        tagImage.setGraphic(tagIconGrey);

        this.assignees.setStyle("-fx-opacity:0.5;");
        ImageView assigneeIconGrey = new ImageView(contactGrey);
        assigneeImage.setText(" ");
        assigneeImage.setGraphic(assigneeIconGrey);

        ImageView dateIconGrey = new ImageView(calendarGrey);
        dateImage.setText(" ");
        dateImage.setGraphic(dateIconGrey);

        ImageView timeIconGrey = new ImageView(clockGrey);
        timeImage.setText(" ");
        timeImage.setGraphic(timeIconGrey);
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
