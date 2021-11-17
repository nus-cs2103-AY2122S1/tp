package socialite.ui;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.nio.file.Paths;
import java.time.LocalDate;
import java.util.Comparator;

import javafx.concurrent.Task;
import javafx.concurrent.WorkerStateEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.Clipboard;
import javafx.scene.input.ClipboardContent;
import javafx.scene.layout.FlowPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Region;
import javafx.scene.shape.Circle;
import socialite.logic.commands.PinCommand;
import socialite.logic.commands.ShareCommand;
import socialite.logic.commands.UnpinCommand;
import socialite.model.handle.Handle;
import socialite.model.handle.Handle.Platform;
import socialite.model.person.Date;
import socialite.model.person.Dates;
import socialite.model.person.Person;
import socialite.model.person.Remark;

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
    private Button pinButton;
    @FXML
    private Button shareButton;
    @FXML
    private Label phone;
    @FXML
    private FlowPane handles;
    @FXML
    private HBox facebook;
    @FXML
    private ImageView facebookIcon;
    @FXML
    private Label facebookHandle;
    @FXML
    private HBox instagram;
    @FXML
    private ImageView instagramIcon;
    @FXML
    private Label instagramHandle;
    @FXML
    private HBox telegram;
    @FXML
    private ImageView telegramIcon;
    @FXML
    private Label telegramHandle;
    @FXML
    private HBox tiktok;
    @FXML
    private ImageView tiktokIcon;
    @FXML
    private Label tiktokHandle;
    @FXML
    private HBox twitter;
    @FXML
    private ImageView twitterIcon;
    @FXML
    private Label twitterHandle;
    @FXML
    private FlowPane tags;
    @FXML
    private FlowPane dates;
    @FXML
    private Label remark;
    @FXML
    private ImageView profilePicture;


    /**
     * Creates a {@code PersonCode} with the given {@code Person} and index to display.
     */
    public PersonCard(Person person, int displayedIndex) {
        super(FXML);
        this.person = person;
        id.setText(displayedIndex + ". ");
        name.setText(person.getName().fullName);
        phone.setText(person.getPhone().value);
        remark.managedProperty().bind(remark.visibleProperty());
        this.makeRemark(person.getRemark());
        this.makeHandle(person.getFacebook(), Platform.FACEBOOK);
        this.makeHandle(person.getInstagram(), Platform.INSTAGRAM);
        this.makeHandle(person.getTelegram(), Platform.TELEGRAM);
        this.makeHandle(person.getTiktok(), Platform.TIKTOK);
        this.makeHandle(person.getTwitter(), Platform.TWITTER);
        try {
            this.profilePicture.setImage(new Image(new FileInputStream(
                    Paths.get("data", "profilepictures")
                            .resolve(this.person.getProfilePicture().value)
                            .toString()
            )));
        } catch (NullPointerException | FileNotFoundException e) {
            this.profilePicture.setImage(new Image(
                    this.getClass().getResourceAsStream("/images/default_profile_picture.png")
            ));
        }

        Circle clip = new Circle(30);
        this.profilePicture.setFitHeight(60);
        this.profilePicture.setFitWidth(60);
        clip.setCenterX(profilePicture.getFitWidth() / 2);
        clip.setCenterY(profilePicture.getFitHeight() / 2);
        this.profilePicture.setClip(clip);
        centerImage(profilePicture);

        if (person.isPinned()) {
            // set background colour / button colour
            pinButton.setText("Unpin");
            pinButton.getStyleClass().add("pinButton");
        } else {
            pinButton.setText("Pin");
            pinButton.getStyleClass().remove("pinButton");
        }

        // Permits wrapping of tags/dates, when used in combination with `-fx-max-width`.
        tags.setMinWidth(Region.USE_PREF_SIZE);
        dates.setMinWidth(Region.USE_PREF_SIZE);

        person.getTags().stream()
                .sorted(Comparator.comparing(tag -> tag.tagName))
                .forEach(tag -> tags.getChildren().add(new Label(tag.tagName)));
        this.renderDates(person.getDates());
    }

    private void makeRemark(Remark remark) {
        String value = remark.get();
        if (value != null && !value.equals("")) {
            this.remark.setText(value);
            this.remark.setVisible(true);
        } else {
            this.remark.setText(null);
            this.remark.setVisible(false);
        }
    }

    // credits for this method goes to https://stackoverflow.com/questions/32781362/centering-an-image-in-an-imageview
    private void centerImage(ImageView imageView) {
        Image img = imageView.getImage();
        if (img == null) {
            return;
        }
        double w = 0;
        double h = 0;

        double ratioX = imageView.getFitWidth() / img.getWidth();
        double ratioY = imageView.getFitHeight() / img.getHeight();

        double reduceCoeff = Math.min(ratioX, ratioY);

        w = img.getWidth() * reduceCoeff;
        h = img.getHeight() * reduceCoeff;

        imageView.setX((imageView.getFitWidth() - w) / 2);
        imageView.setY((imageView.getFitHeight() - h) / 2);
    }


    private void makeHandle(Handle handle, Platform platform) {
        HBox box = null;
        Label label = null;
        ImageView icon = null;

        switch (platform) {
        case FACEBOOK:
            box = this.facebook;
            label = this.facebookHandle;
            icon = this.facebookIcon;
            break;
        case INSTAGRAM:
            box = this.instagram;
            label = this.instagramHandle;
            icon = this.instagramIcon;
            break;
        case TELEGRAM:
            box = this.telegram;
            label = this.telegramHandle;
            icon = this.telegramIcon;
            break;
        case TIKTOK:
            box = this.tiktok;
            label = this.tiktokHandle;
            icon = this.tiktokIcon;
            break;
        case TWITTER:
            box = this.twitter;
            label = this.twitterHandle;
            icon = this.twitterIcon;
            break;
        default:
        }

        // if platform is correct, label and icon should not be null
        assert box != null;
        assert label != null;
        assert icon != null;

        box.managedProperty().bind(box.visibleProperty());
        renderHandle(box, handle, label, icon, "/images/" + platform.name().toLowerCase() + ".png");
    }


    private void renderHandle(HBox box, Handle handle, Label label, ImageView icon, String iconFilePath) {
        if (handle.get() != null && !handle.get().equals("")) {
            box.setVisible(true);
            icon.setImage(new Image(this.getClass().getResourceAsStream(iconFilePath)));
            label.setText("@" + handle + " ");
            label.setOnMouseEntered(Event -> label.setUnderline(true));
            label.setOnMouseExited(Event -> label.setUnderline(false));
            label.setOnMousePressed(Event -> this.openBrowser(handle.getUrl()));
        } else {
            box.setVisible(false);
        }
    }

    private void renderDates(Dates displayedDates) {
        displayedDates.get().values().stream()
                .sorted(Date.getComparator(LocalDate.now()))
                .forEach(date -> {
                    long upcomingDays = date.getUpcomingDays(LocalDate.now());
                    boolean isUpcoming = upcomingDays >= 0 && upcomingDays <= 7;
                    String upcomingMessage = isUpcoming
                            ? " ("
                                + (upcomingDays == 0
                                    ? "today"
                                    : upcomingDays == 1
                                        ? "in 1 day"
                                        : "in " + upcomingDays + " days") + ")"
                            : "";

                    HBox hbox = new HBox();
                    hbox.getStyleClass().add("hbox");
                    if (isUpcoming) {
                        hbox.getStyleClass().add("upcoming");
                    }

                    String message = date + upcomingMessage;
                    String[] parts = message.split(": ");
                    Label name = new Label(parts[0]);
                    name.getStyleClass().add("name");
                    Label details = new Label(": " + parts[1]);

                    hbox.getChildren().add(name);
                    hbox.getChildren().add(details);
                    dates.getChildren().add(hbox);
                });
    }

    private void openBrowser(String url) {
        String os = System.getProperty("os.name").toLowerCase();
        Runtime runtime = Runtime.getRuntime();
        try {
            if (os.indexOf("win") >= 0) {
                runtime.exec("rundll32 url.dll,FileProtocolHandler " + url);
            } else if (os.indexOf("mac") >= 0) {
                runtime.exec("open " + url);
            } else if (os.indexOf("nix") >= 0 || os.indexOf("nux") >= 0) {
                runtime.exec("xdg-open " + url);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @FXML
    private void handlePinButtonAction() {
        MainWindow mainWindow = MainWindow.getWindow();
        if (person.isPinned()) {
            person.unpin();
            mainWindow.setFeedbackToUser(String.format(UnpinCommand.MESSAGE_UNPIN_PERSON_SUCCESS, person));
        } else {
            person.pin();
            mainWindow.setFeedbackToUser(String.format(PinCommand.MESSAGE_PIN_PERSON_SUCCESS, person));
        }
        mainWindow.showFullPersonList();
    }

    @FXML
    private void handleShareButtonAction() {
        Clipboard clipboard = Clipboard.getSystemClipboard();
        ClipboardContent content = new ClipboardContent();
        String shareInfo = person.toSharingString();
        content.putString(shareInfo);
        clipboard.setContent(content);

        // Show the copied info in result display
        MainWindow.getWindow().setFeedbackToUser(String.format(ShareCommand.MESSAGE_SHARE_PERSON_SUCCESS, shareInfo));

        shareButton.setText("Copied!");

        // Change the button text back after 2 seconds
        Task<Void> sleeper = new Task<>() {
            @Override
            protected Void call() {
                try {
                    Thread.sleep(2000);
                } catch (InterruptedException ignored) {
                    // TODO: throw some exception?
                }
                return null;
            }
        };
        sleeper.setOnSucceeded(new EventHandler<WorkerStateEvent>() {
            @Override
            public void handle(WorkerStateEvent event) {
                shareButton.setText("Share");
            }
        });
        new Thread(sleeper).start();
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
