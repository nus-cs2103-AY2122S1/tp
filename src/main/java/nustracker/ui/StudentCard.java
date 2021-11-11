package nustracker.ui;

import javafx.fxml.FXML;
import javafx.scene.image.Image;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Region;
import javafx.scene.shape.Circle;
import javafx.scene.text.Text;
import nustracker.model.student.Student;
import nustracker.storage.ImageStorage;

/**
 * A UI component that displays information of a {@code Student}.
 */
public class StudentCard extends UiPart<Region> {

    private static final String FXML = "StudentListCard.fxml";
    public final Student student;
    private final ImageStorage imageStorage;
    private final String glowColorHexCode;
    private final ImageEditor imageEditor;

    @FXML
    private HBox cardPane;
    @FXML
    private Text name;
    @FXML
    private Text phone;
    @FXML
    private Text email;
    @FXML
    private Text year;
    @FXML
    private Text major;
    @FXML
    private Text studentId;
    @FXML
    private Text enrolledEvents;
    @FXML
    private Circle profilePictureHolder;

    /**
     * Creates a {@code StudentCode} with the given {@code Student}.
     */
    public StudentCard(Student student, String glowColorHexCode) {
        super(FXML);
        this.student = student;
        this.glowColorHexCode = glowColorHexCode;
        imageStorage = new ImageStorage();
        imageEditor = new ImageEditor(profilePictureHolder);

        setStudentFields();
        setProfilePicture();
    }

    /**
     * Sets the student's fields to be displayed on the student card.
     */
    private void setStudentFields() {
        name.setText(student.getName().fullName);
        phone.setText(student.getPhone().value);
        email.setText(student.getEmail().value);
        year.setText(student.getYear().value);
        major.setText(student.getMajor().value);
        studentId.setText(student.getStudentId().value);
        enrolledEvents.setText(student.getEvents().getEventNamesString());
    }

    /**
     * Sets the profile picture holder with the student's image.
     */
    private void setProfilePicture() {
        Image userImage = imageStorage.readImage(student.getStudentId().value);

        imageEditor.setUpHolder(glowColorHexCode);
        imageEditor.fillImage(userImage);
    }

    @Override
    public boolean equals(Object other) {
        //Short circuit if same object
        if (other == this) {
            return true;
        }

        // instanceof handles nulls
        if (!(other instanceof StudentCard)) {
            return false;
        }

        //State check
        StudentCard card = (StudentCard) other;
        return student.equals(card.student);
    }
}
