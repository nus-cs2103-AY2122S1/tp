package seedu.academydirectory.ui;

import java.util.Comparator;
import java.util.logging.Logger;

import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.FlowPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Region;
import javafx.scene.layout.VBox;
import seedu.academydirectory.commons.core.LogsCenter;
import seedu.academydirectory.logic.commands.exceptions.CommandException;
import seedu.academydirectory.logic.parser.exceptions.ParseException;
import seedu.academydirectory.model.student.Student;

/**
 * An UI component that displays information of a {@code Student}.
 */
public class StudentCard extends UiPart<Region> {

    private static final String FXML = "StudentListCard.fxml";
    private final Logger logger = LogsCenter.getLogger(getClass());

    /**
     * Note: Certain keywords such as "location" and "resources" are reserved keywords in JavaFX.
     * As a consequence, UI elements' variable names cannot be set to such keywords
     * or an exception will be thrown by JavaFX during runtime.
     *
     * @see <a href="https://github.com/se-edu/addressbook-level4/issues/336">The issue on AcademyDirectory level 4</a>
     * TODO: Change view layout of student card later
     */
    private final Student student;

    @FXML
    private HBox cardPane;
    @FXML
    private VBox container;
    @FXML
    private Label name;
    @FXML
    private Label id;
    @FXML
    private Label phone;
    @FXML
    private Label email;
    @FXML
    private Label telegram;
    @FXML
    private Label studioRecord;
    @FXML
    private Label assessment;
    @FXML
    private FlowPane tags;
    @FXML
    private ImageView image;

    private int displayedIndex;
    private CommandExecutor commandExecutor;

    /**
     * Creates a {@code StudentCode} with the given {@code Student} and index to display.
     * @param student student to be displayed
     * @param displayedIndex index of the student
     */
    public StudentCard(Student student, int displayedIndex, CommandExecutor commandExecutor) {
        super(FXML);
        this.student = student;
        this.displayedIndex = displayedIndex;
        id.setText(displayedIndex + ". ");
        name.setText(student.getName().fullName);
        phone.setText(student.getPhone().toString());
        email.setText(student.getEmail().value);
        studioRecord.setText(student.getStudioRecord().toString());
        telegram.setText(student.getTelegram().value);
        assessment.setText(student.getAssessment().toString());
        student.getTags().stream()
                .sorted(Comparator.comparing(tag -> tag.tagName))
                .forEach(tag -> tags.getChildren().add(new Label(tag.tagName)));
        // Studio Record and Assessment will be removed for better aesthetics
        container.getChildren().removeAll(studioRecord, assessment, telegram);
        image.setImage(new Image("/images/student.png"));
        this.commandExecutor = commandExecutor;
    }

    @Override
    public boolean equals(Object other) {
        // short circuit if same object
        if (other == this) {
            return true;
        }

        // instanceof handles nulls
        if (!(other instanceof StudentCard)) {
            return false;
        }

        // state check
        StudentCard card = (StudentCard) other;
        return id.getText().equals(card.id.getText())
                && student.equals(card.student);
    }

    /**
     * Execute the equivalent command of `view INDEX` by clicking on the student
     */
    @FXML
    public void viewFullInformation() {
        logger.info("Item selected");
        String commandEquivalent = "view " + this.displayedIndex;
        try {
            commandExecutor.execute(commandEquivalent);
        } catch (ParseException | CommandException e) {
            logger.info("This should not be reached");
        }
    }
}
