package seedu.address.ui;

import java.util.Comparator;

import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Region;
import javafx.scene.layout.VBox;
import seedu.address.model.lesson.Lesson;

public class LessonCard extends UiPart<Region> {

    private static final String FXML = "LessonListCard.fxml";
    private static final double TEXT_WIDTH_PERC = 0.75;
    private static final double HW_WIDTH_PERC = 0.88;

    public final Lesson lesson;

    @FXML
    private HBox cardPane;
    @FXML
    private Label lessonId;
    @FXML
    private Label title;
    @FXML
    private Label date;
    @FXML
    private Label time;
    @FXML
    private Label rates;
    @FXML
    private VBox homeworkList;

    /**
     * Creates a {@code PersonCode} with the given {@code Person} and index to display.
     */
    public LessonCard(Lesson lesson, int displayedIndex) {
        super(FXML);
        this.lesson = lesson;
        lessonId.setText(displayedIndex + ". ");
        title.setText(lesson.getSubject() + " (" + lesson.getTypeOfLesson() + ")");
        date.setText(lesson.getDisplayDate().value);
        time.setText(lesson.getTimeRange().toString());
        rates.setText("$" + lesson.getLessonRates().toString());
        lesson.getHomework().stream()
            .sorted(Comparator.comparing(homework -> homework.description))
            .forEach(homework -> homeworkList.getChildren()
                .add(createHomeworkLabel(homework.toString())));
        title.maxWidthProperty().bind(getRoot().widthProperty().multiply(TEXT_WIDTH_PERC));
        rates.maxWidthProperty().bind(getRoot().widthProperty().multiply(TEXT_WIDTH_PERC));
    }

    private Label createHomeworkLabel(String homework) {
        Label label = new Label(homework);
        label.setWrapText(true);
        label.maxWidthProperty().bind(getRoot().widthProperty().multiply(HW_WIDTH_PERC));
        return label;
    }
}
