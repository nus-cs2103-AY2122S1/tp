package seedu.address.ui;

import java.util.concurrent.atomic.AtomicInteger;

import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.layout.FlowPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Region;
import seedu.address.model.organisation.Organisation;

public class OrganisationCard extends UiPart<Region> {
    private static final String FXML = "OrganisationListCard.fxml";

    /**
     * Note: Certain keywords such as "location" and "resources" are reserved keywords in JavaFX.
     * As a consequence, UI elements' variable names cannot be set to such keywords
     * or an exception will be thrown by JavaFX during runtime.
     *
     * @see <a href="https://github.com/se-edu/addressbook-level4/issues/336">The issue on AddressBook level 4</a>
     */

    public final Organisation organisation;

    @javafx.fxml.FXML
    private HBox cardPane;
    @FXML
    private Label name;
    @FXML
    private Label id;
    @FXML
    private Label email;
    @FXML
    private FlowPane persons;


    /**
     * Creates a {@code OrganisationCard} with the given {@code Organisation} and index to display.
     */
    public OrganisationCard(Organisation organisation, int displayedIndex) {
        super(FXML);
        this.organisation = organisation;
        id.setText(displayedIndex + ". ");
        name.setText(organisation.getName().fullName);
        email.setText(organisation.getEmail().value);
        AtomicInteger index = new AtomicInteger(1);
        organisation.getPersons().forEach(person -> {
            persons.getChildren().add(new Label(index + ". " + person.getName() + " "));
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
        if (!(other instanceof OrganisationCard)) {
            return false;
        }

        // state check
        OrganisationCard card = (OrganisationCard) other;
        return id.getText().equals(card.id.getText())
                && organisation.equals(card.organisation);
    }
}
