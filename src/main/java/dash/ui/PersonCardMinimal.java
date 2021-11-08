package dash.ui;

import java.util.Comparator;

import dash.model.person.Person;
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
public class PersonCardMinimal extends UiPart<Region> {

    private static final String FXML = "PersonListCardMinimal.fxml";

    /**
     * Note: Certain keywords such as "location" and "resources" are reserved keywords in JavaFX.
     * As a consequence, UI elements' variable names cannot be set to such keywords
     * or an exception will be thrown by JavaFX during runtime.
     *
     * @see <a href="https://github.com/se-edu/addressbook-level4/issues/336">The issue on AddressBook level 4</a>
     */

    public final Person person;

    private Image tag = new Image("/images/tag.png", 20, 20, false, true);
    private Image tagGrey = new Image("/images/tag_greyed.png", 20, 20, false, true);

    @FXML
    private HBox cardPane;
    @FXML
    private Label name;
    @FXML
    private Label id;
    @FXML
    private Label tagImage;
    @FXML
    private FlowPane tags;

    /**
     * Creates a {@code PersonCode} with the given {@code Person} and index to display.
     */
    public PersonCardMinimal(Person person, int displayedIndex) {
        super(FXML);
        this.person = person;
        id.setText(displayedIndex + ". ");
        name.setText(person.getName().fullName);
        ImageView tagIcon = new ImageView(tag);
        tagImage.setText(" ");
        tagImage.setGraphic(tagIcon);
        person.getTags().stream()
                .sorted(Comparator.comparing(tag -> tag.tagName))
                .forEach(tag -> tags.getChildren().add(new Label(tag.tagName)));
    }

    @Override
    public boolean equals(Object other) {
        // short circuit if same object
        if (other == this) {
            return true;
        }

        // instanceof handles nulls
        if (!(other instanceof PersonCardMinimal)) {
            return false;
        }

        // state check
        PersonCardMinimal card = (PersonCardMinimal) other;
        return id.getText().equals(card.id.getText())
                && person.equals(card.person);
    }
}
