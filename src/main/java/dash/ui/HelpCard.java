package dash.ui;

import dash.model.help.HelpCommand;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Region;

/**
 * An UI component that displays information of a {@code HelpCommand}.
 */
public class HelpCard extends UiPart<Region> {

    private static final String FXML = "HelpListCard.fxml";

    /**
     * Note: Certain keywords such as "location" and "resources" are reserved keywords in JavaFX.
     * As a consequence, UI elements' variable names cannot be set to such keywords
     * or an exception will be thrown by JavaFX during runtime.
     *
     * @see <a href="https://github.com/se-edu/addressbook-level4/issues/336">The issue on AddressBook level 4</a>
     */

    private HelpCommand helpCommand;

    private Image tag = new Image("/images/tag.png", 20, 20, false, true);
    private Image phoneIcon = new Image("/images/phone.png", 20, 20, false, true);
    private Image addressIcon = new Image("/images/address.png", 20, 20, false, true);
    private Image emailIcon = new Image("/images/email.png", 20, 20, false, true);

    @FXML
    private HBox cardPane;
    @FXML
    private Label name;
    @FXML
    private Label explanation;

    /**
     * Creates a {@code HelpCode} with the given {@code HelpCommand} and index to display.
     */
    public HelpCard(HelpCommand helpCommand, int displayedIndex) {
        super(FXML);
        this.helpCommand = helpCommand;
        name.setText(displayedIndex + ". " + helpCommand.getHeader());
        explanation.setText(helpCommand.getContent());
        if (displayedIndex % 2 == 0) {
            this.cardPane.setStyle("-fx-background-color: #91755a;");
        } else {
            this.cardPane.setStyle("-fx-background-color: #c9a481;");
        }

    }

    @Override
    public boolean equals(Object other) {
        // short circuit if same object
        if (other == this) {
            return true;
        }

        // instanceof handles nulls
        if (!(other instanceof HelpCard)) {
            return false;
        }

        // state check
        HelpCard card = (HelpCard) other;
        return helpCommand.equals(card.helpCommand);
    }
}
