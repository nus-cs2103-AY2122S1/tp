package seedu.address.ui;

import java.time.format.DateTimeFormatter;
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
public class PersonViewCard extends UiPart<Region> {

    private static final String FXML = "PersonViewCard.fxml";

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
    private Label nameLabel;
    @FXML
    private Label nameView;

    @FXML
    private Label emailLabel;
    @FXML
    private Label emailView;

    @FXML
    private Label facultyLabel;
    @FXML
    private Label facultyView;

    @FXML
    private Label majorLabel;
    @FXML
    private Label majorView;

    @FXML
    private Label skillsLabel;
    @FXML
    private FlowPane skills;

    @FXML
    private Label languagesLabel;
    @FXML
    private FlowPane languages;

    @FXML
    private Label frameworksLabel;
    @FXML
    private FlowPane frameworks;

    @FXML
    private Label tagsLabel;
    @FXML
    private FlowPane tags;

    @FXML
    private Label remarksLabel;
    @FXML
    private FlowPane remarks;

    @FXML
    private Label interactionsLabel;
    @FXML
    private FlowPane interactions;

    @FXML
    private Label compatibilityLabel;
    @FXML
    private Label compatibilityView;


    /**
     * Creates a {@code PersonCode} with the given {@code Person} and index to display.
     */
    public PersonViewCard(Person person) {
        super(FXML);
        this.person = person;

        nameView.setText(person.getName().fullName);
        emailView.setText(person.getEmail().value);
        facultyView.setText(person.getFaculty().value);
        majorView.setText(person.getMajor().value);

        emailLabel.setText("Email:");
        facultyLabel.setText("Faculty:");
        majorLabel.setText("Major:");

        skillsLabel.setText("Skills");
        languagesLabel.setText("Languages");
        frameworksLabel.setText("Frameworks");
        tagsLabel.setText("Tags");
        remarksLabel.setText("Remarks");
        interactionsLabel.setText("Interactions");

        compatibilityLabel.setText("Compatibility");
        if (person.getCompatibility().compatibilityRating.isEmpty()) {
            compatibilityView.setText("-");
        } else {
            compatibilityView.setText(person.getCompatibility().compatibilityRating.get() + "/100");
        }

        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd MMM yyyy");

        AtomicInteger index = new AtomicInteger(1);

        if (person.getSkills().size() == 0) {
            skills.getChildren().add(new Label("-"));
        } else {
            person.getSkills().stream()
                    .sorted(Comparator.comparing(skill -> skill.skillName))
                    .forEach(skill -> {
                        skills.getChildren().add(new Label(index + ". " + skill.skillName));
                        index.addAndGet(1);
                    });
            index.set(1);
        }

        if (person.getLanguages().size() == 0) {
            languages.getChildren().add(new Label("-"));
        } else {
            person.getLanguages().stream()
                    .sorted(Comparator.comparing(language -> language.languageName))
                    .forEach(language -> {
                        languages.getChildren().add(new Label(index + ". " + language.languageName));
                        index.addAndGet(1);
                    });
            index.set(1);
        }

        if (person.getFrameworks().size() == 0) {
            frameworks.getChildren().add(new Label("-"));
        } else {
            person.getFrameworks().stream()
                    .sorted(Comparator.comparing(framework -> framework.frameworkName))
                    .forEach(framework -> {
                        frameworks.getChildren().add(new Label(index + ". " + framework.frameworkName));
                        index.addAndGet(1);
                    });
            index.set(1);
        }

        if (person.getTags().size() == 0) {
            tags.getChildren().add(new Label("-"));
        } else {
            person.getTags().stream()
                    .sorted(Comparator.comparing(tag -> tag.tagName))
                    .forEach(tag -> {
                        tags.getChildren().add(new Label(index + ". " + tag.tagName));
                        index.addAndGet(1);
                    });
            index.set(1);
        }

        if (person.getRemarks().size() == 0) {
            remarks.getChildren().add(new Label("-"));
        } else {
            person.getRemarks().stream()
                    .sorted(Comparator.comparing(remark -> remark.remarkDetail))
                    .forEach(remark -> {
                        remarks.getChildren().add(new Label(index + ". " + remark.remarkDetail));
                        index.addAndGet(1);
                    });
            index.set(1);
        }

        if (person.getInteractions().size() == 0) {
            interactions.getChildren().add(new Label("-"));
        } else {
            person.getInteractions().stream()
                    .sorted(Comparator.comparing(remark -> remark.date))
                    .forEach(interaction -> {
                        String formattedDate = interaction.date.format(formatter);
                        interactions.getChildren().add(new Label(formattedDate + ": " + interaction.description));
                        index.addAndGet(1);
                    });
        }
    }

    @Override
    public boolean equals(Object other) {
        // short circuit if same object
        if (other == this) {
            return true;
        }

        // instanceof handles nulls
        if (!(other instanceof PersonViewCard)) {
            return false;
        }

        // state check
        PersonViewCard card = (PersonViewCard) other;
        return person.equals(card.person);
    }
}
