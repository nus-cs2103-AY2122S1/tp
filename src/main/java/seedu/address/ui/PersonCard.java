package seedu.address.ui;

import java.util.Comparator;
import java.util.concurrent.atomic.AtomicInteger;

import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.layout.FlowPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Region;
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
    private Label name;
    @FXML
    private Label id;
    @FXML
    private Label email;
    @FXML
    private Label faculty;
    @FXML
    private Label major;
    @FXML
    private FlowPane skills;
    @FXML
    private FlowPane languages;
    @FXML
    private FlowPane frameworks;

    @FXML
    private FlowPane tags;


    /**
     * Creates a {@code PersonCode} with the given {@code Person} and index to display.
     */
    public PersonCard(Person person, int displayedIndex) {
        super(FXML);
        this.person = person;
        id.setText(displayedIndex + ". ");
        name.setText(person.getName().fullName);
        faculty.setText(person.getFaculty().value);
        major.setText(person.getMajor().value);
        AtomicInteger index = new AtomicInteger(1);

        person.getSkills().stream()
                .sorted(Comparator.comparing(skill -> skill.skillName))
                .forEach(skill -> {
                    skills.getChildren().add(new Label(index + ". " + skill.skillName));
                    index.addAndGet(1);
                });
        index.set(1);

        person.getLanguages().stream()
                .sorted(Comparator.comparing(language -> language.languageName))
                .forEach(language -> {
                    languages.getChildren().add(new Label(index + ". " + language.languageName));
                    index.addAndGet(1);
                });
        index.set(1);

        person.getFrameworks().stream()
                .sorted(Comparator.comparing(framework -> framework.frameworkName))
                .forEach(framework -> {
                    frameworks.getChildren().add(new Label(index + ". " + framework.frameworkName));
                    index.addAndGet(1);
                });
        index.set(1);

        person.getTags().stream()
                .sorted(Comparator.comparing(tag -> tag.tagName))
                .forEach(tag -> {
                    tags.getChildren().add(new Label(index + ". " + tag.tagName));
                    index.addAndGet(1);
                });
        index.set(1);

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
        return id.getText().equals(card.id.getText())
                && person.equals(card.person);
    }
}
