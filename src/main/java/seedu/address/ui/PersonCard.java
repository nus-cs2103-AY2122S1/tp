package seedu.address.ui;

import java.io.File;
import java.util.Comparator;

import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.FlowPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Region;
import seedu.address.model.person.Person;
import seedu.address.model.tag.Status;

/**
 * An UI component that displays information of a {@code Person}.
 */
public class PersonCard extends UiPart<Region> {

    private static final String FXML = "PersonListCard.fxml";

    private static final File iconFile = new File("src/main/resources/images/fav_icon.png");
    private static final Image favIcon = new Image(iconFile.toURI().toString());
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
    private Label id;
    @FXML
    private Label name;
    @FXML
    private Label studentId;
    @FXML
    private Label phone;
    @FXML
    private Label email;
    @FXML
    private ImageView fav;
    @FXML
    private FlowPane mods;

    /**
     * Creates a {@code PersonCode} with the given {@code Person} and index to display.
     */
    public PersonCard(Person person, int displayedIndex) {
        super(FXML);
        this.person = person;
        id.setText(displayedIndex + ".");
        name.setText(person.getName().fullName);
        if (person.getIsFavourite()) {
            fav.setImage(favIcon);
        }
        studentId.setText(person.getStudentId().value);
        phone.setText(person.getPhone().value);
        email.setText(person.getEmail().value);
        person.getMods().stream()
                .sorted(Comparator.comparing(mod -> mod.modName))
                .forEach(mod -> {
                    if (mod.status.equals(Status.NONE)) {
                        mods.getChildren().add(new Label(mod.modName));
                    } else if (mod.status.equals(Status.NEED_GROUP)) {
                        Label l = new Label(mod.modName);
                        l.setStyle("-fx-background-color: red;");
                        mods.getChildren().add(l);
                    } else if (mod.status.equals(Status.NEED_MEMBER)) {
                        Label l = new Label(mod.modName);
                        l.setStyle("-fx-background-color: yellow; -fx-text-fill: black;");
                        mods.getChildren().add(l);
                    } else {
                        mods.getChildren().add(new Label(mod.modName));
                    }
                });
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
