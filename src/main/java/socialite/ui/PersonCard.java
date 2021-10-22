package socialite.ui;

import java.util.Comparator;

import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.FlowPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Region;
import socialite.model.handle.Handle;
import socialite.model.person.Person;

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
    private Label phone;
    @FXML
    private ImageView tiktokIcon;
    @FXML
    private Label tiktok;
    @FXML
    private ImageView twitterIcon;
    @FXML
    private Label twitter;
    @FXML
    private ImageView facebookIcon;
    @FXML
    private Label facebook;
    @FXML
    private ImageView instagramIcon;
    @FXML
    private Label instagram;
    @FXML
    private ImageView telegramIcon;
    @FXML
    private Label telegram;
    @FXML
    private FlowPane tags;
    @FXML
    private Label dates;
    @FXML
    private Label remark;
    /**
     * Creates a {@code PersonCode} with the given {@code Person} and index to display.
     */
    public PersonCard(Person person, int displayedIndex) {
        super(FXML);
        this.person = person;
        id.setText(displayedIndex + ". ");
        name.setText(person.getName().fullName);
        phone.setText(person.getPhone().value);
        remark.setText(person.getRemark().value);
        this.makeHandle(person.getFacebook(), "facebook");
        this.makeHandle(person.getInstagram(), "instagram");
        this.makeHandle(person.getTelegram(), "telegram");
        this.makeHandle(person.getTiktok(), "tiktok");
        this.makeHandle(person.getTwitter(), "twitter");
        person.getTags().stream()
                .sorted(Comparator.comparing(tag -> tag.tagName))
                .forEach(tag -> tags.getChildren().add(new Label(tag.tagName)));
        dates.setText(person.getDates().value.toString());
    }

    private void makeHandle(Handle handle, String handleName) {
        String value = handle.get();
        switch (handleName) {
        case "facebook":
            if (value != null && !value.equals("")) {
                this.facebook.setText("@" + value + " ");
                this.facebookIcon.setImage(new Image(this.getClass().getResourceAsStream("/images/facebook.png")));
            } else {
                this.facebook.setText(null);
                this.facebookIcon.setFitWidth(0);
            }
            break;
        case "instagram":
            if (value != null && !value.equals("")) {
                this.instagram.setText("@" + value + " ");
                this.instagramIcon.setImage(new Image(this.getClass().getResourceAsStream("/images/instagram.png")));
            } else {
                this.instagram.setText(null);
                this.instagramIcon.setFitWidth(0);
            }
            break;
        case "telegram":
            if (value != null && !value.equals("")) {
                this.telegram.setText("@" + value + " ");
                this.telegramIcon.setImage(new Image(this.getClass().getResourceAsStream("/images/telegram.png")));
            } else {
                this.telegram.setText(null);
                this.telegramIcon.setFitWidth(0);
            }
            break;
        case "tiktok":
            if (value != null && !value.equals("")) {
                this.tiktok.setText("@" + value + " ");
                this.tiktokIcon.setImage(new Image(this.getClass().getResourceAsStream("/images/tik-tok.png")));
            } else {
                this.tiktok.setText(null);
                this.tiktokIcon.setFitWidth(0);
            }
            break;
        case "twitter":
            if (value != null && !value.equals("")) {
                this.twitter.setText("@" + value + " ");
                this.twitterIcon.setImage(new Image(this.getClass().getResourceAsStream("/images/twitter.png")));
            } else {
                this.twitter.setText(null);
                this.twitterIcon.setFitWidth(0);
            }
            break;
        default:
        }

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
