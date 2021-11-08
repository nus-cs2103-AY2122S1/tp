package seedu.address.ui;

import java.util.Comparator;

import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.layout.FlowPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Region;
import javafx.scene.text.Text;
import javafx.scene.text.TextFlow;
import seedu.address.model.done.Done;
import seedu.address.model.interview.Interview;
import seedu.address.model.notes.Notes;
import seedu.address.model.person.Person;

/**
 * An UI component that displays information of a {@code Person}.
 */
public class PersonCard extends UiPart<Region> {

    private static final String FXML = "PersonListCard.fxml";

    /**
     * Note: Certain keywords such as "location" and "resources" are reserved keywords in JavaFX.
     * As a consequence, UI elements' variable names cannot be set to such keywords
     * or an exception will be thrown by JavaFX during runtime.
     *
     * @see <a href="https://github.com/se-edu/addressbook-level4/issues/336">The issue on AddressBook level 4</a>
     */

    public final Person person;

    @FXML
    private HBox cardPane;
    @FXML
    private TextFlow name;
    @FXML
    private TextFlow id;
    @FXML
    private TextFlow phone;
    @FXML
    private TextFlow email;
    @FXML
    private TextFlow role;
    @FXML
    private TextFlow employmentType;
    @FXML
    private TextFlow expectedSalary;
    @FXML
    private TextFlow levelOfEducation;
    @FXML
    private TextFlow experience;
    @FXML
    private TextFlow interview;
    @FXML
    private TextFlow notes;
    @FXML
    private FlowPane tags;
    @FXML
    private FlowPane done;
    @FXML
    private FlowPane notDone;


    /**
     * Creates a {@code PersonCode} with the given {@code Person} and index to display.
     */
    public PersonCard(Person person, int displayedIndex) {
        super(FXML);
        this.person = person;
        id.getChildren().add(getText(displayedIndex + ". "));
        name.getChildren().add(getText(person.getName().fullName));
        phone.getChildren().add(getText("Phone Number: " + person.getPhone().value));
        email.getChildren().add(getText("Email: " + person.getEmail().value));
        role.getChildren().add(getText("Applied Role: " + person.getRole().role));
        employmentType.getChildren().add(getText("Employment Type: " + person.getEmploymentType().employmentType));
        expectedSalary.getChildren().add(getText("Expected Salary: $" + person.getExpectedSalary().value));
        levelOfEducation.getChildren().add(getText("Level of Education: "
                + person.getLevelOfEducation().levelOfEducation));
        experience.getChildren().add(getText("Years of Experience: " + person.getExperience().value));
        interview.getChildren().add(getText("Interview Time: "
                + person.getInterview().orElse(Interview.EMPTY_INTERVIEW).displayTime()));
        notes.getChildren().add(getText("Notes: " + person.getNotes().orElse(Notes.EMPTY_NOTES).information));

        person.getTags().stream()
                .sorted(Comparator.comparing(tag -> tag.tagName))
                .forEach(tag -> tags.getChildren().add(new Label(tag.tagName)));

        if (person.getDone().getDoneStatus().equals(Done.STATUS_DONE)) {
            done.getChildren().add(new Label(Done.STATUS_DONE));
            notDone.getChildren().clear();
        } else {
            notDone.getChildren().add(new Label(Done.STATUS_UNDONE));
            done.getChildren().clear();
        }
    }

    private Text getText(String text) {
        Text node = new Text(text);
        node.getStyleClass().add("text");
        return node;
    }

    private String getId() {
        Text textNode = (Text) id.getChildren().get(0);
        return textNode.getText();
    }

    @Override
    public boolean equals(Object other) {
        // short circuit if same object
        if (other == this) {
            return true;
        }

        // instanceof handles nulls
        if (!(other instanceof PersonCard)) {
            return false;
        }

        // state check
        PersonCard card = (PersonCard) other;
        return this.getId().equals(card.getId())
                && person.equals(card.person);
    }
}
