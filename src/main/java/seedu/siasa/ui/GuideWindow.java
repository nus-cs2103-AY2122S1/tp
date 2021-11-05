package seedu.siasa.ui;

import java.util.logging.Logger;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import seedu.siasa.MainApp;
import seedu.siasa.commons.core.LogsCenter;

public class GuideWindow extends UiPart<Stage> {

    /**
     * Enum class storing the strings for all the pages in the current guide.
     */
    private enum Page {
        ONE("User Guide",
            "Welcome to Siasa! "
                + "This is a guide that teaches you all the basic commands to use this application.\n"
                + "For a more detailed guide, visit the user guide on our github repository.",
            "/images/guide/page_one.png"),
        TWO("User Guide - Contacts", "addcontact: Adds a contact to your Siasa address book.\n"
            + "addcontact n/NAME p/PHONE_NUMBER e/EMAIL a/ADDRESS [t/TAG]\n"
            + "Example: addcontact n/John Doe p/98765432 e/johnd@example.com a/John street, block 123, #01-01",
            "/images/guide/page_two.png"),
        THREE("User Guide - Contacts",
            "deletecontact: Removes a contact from your Siasa address book based on the index provided.\n"
                + "deletecontact INDEX\n"
                + "Example: deletecontact 2",
            "/images/guide/page_three.png"),
        FOUR("User Guide - Contacts",
            "editcontact: Edit a contact from your Siasa address book based on the index provided. "
                + "At least one of the optional fields should be filled.\n"
                + "editcontact INDEX [n/NAME] [p/PHONE] [e/EMAIL] [a/ADDRESS] [t/TAG]…\n"
                + "Example: editcontact 2 p/91234567 e/johndoe@example.com",
            "/images/guide/page_four.png"),
        FIVE("User Guide - Policies",
            "addpolicy: Adds a policy that is linked to a contact to your Siasa address book.\n"
                + "addpolicy n/NAME_OF_POLICY p/PMT_AMOUNT_CENTS [PMTS_PER_YR] [NUM_OF_PMTS] c/COMMISSION_% "
                + "NUM_OF_COMM cl/CONTACT_INDEX [e/COVERAGE_EXPIRY_DATE] [t/TAG]\n"
                + "Example: addpolicy n/Life Policy e/2021-12-13 p/1000 12 50 c/20 10 cl/1 ",
            "/images/guide/page_five.png"),
        SIX("User Guide - Policies",
            "deletepolicy: Removes a policy from your Siasa address book based on the index provided.\n"
                + "deletepolicy INDEX\n"
                + "Example: deletepolicy 2",
            "/images/guide/page_six.png"),
        SEVEN("User Guide - Policies",
                "editpolicy: Edits a policy from your Siasa address book based on the index provided. "
                + "At least one of the optional fields should be filled.\n"
                + "editpolicy INDEX [n/NAME_OF_POLICY] [p/PMT_AMOUNT_CENTS [PMTS_PER_YR] [NUM_OF_PMTS]] "
                + "[c/COMMISSION_% NUM_OF_COMMS] [cl/CONTACT_INDEX] [e/COVERAGE_EXPIRY_DATE] [t/TAG]\n"
                + "Example: editpolicy 2 n/Life Policy 2 c/20 20 e/2022-10-22",
            "images/guide/page_seven.png"),
        EIGHT("User Guide - Policies",
            "contactpolicy: List the policies belonging to the client based on the index provided.\n"
                + "contactpolicy INDEX\n"
                + "Example: contactpolicy 1",
            "/images/guide/page_eight.png"),
        NINE("User Guide - Policies",
            "listpolicy: List all policies in your Siasa address book.\n"
                + "listpolicy\n"
                + "Example: listpolicy",
            "/images/guide/page_nine.png");

        private final String title;
        private final String description;
        private final String screenshot;

        Page(String title, String description, String screenshot) {
            this.title = title;
            this.description = description;
            this.screenshot = screenshot;
        }
    }

    private static final Logger logger = LogsCenter.getLogger(GuideWindow.class);
    private static final String FXML = "GuideWindow.fxml";
    private Page currentPage;


    @FXML
    private Button nextButton;

    @FXML
    private Button backButton;

    @FXML
    private Text textBox;

    @FXML
    private ImageView screenshot;

    @FXML
    private Label title;

    /**
     * Creates a new Guide Window.
     *
     * @param root Stage to use as the root of the GuideWindow.
     */
    public GuideWindow(Stage root) {
        super(FXML, root);
    }

    /**
     * Creates a new GuideWindow.
     */
    public GuideWindow() {
        this(new Stage());
        currentPage = Page.ONE;
        showPage();
    }

    /**
     * Shows the GuideWindow.
     */
    public void show() {
        logger.fine("Showing walkthrough page about the application.");
        getRoot().show();
        getRoot().centerOnScreen();
    }

    /**
     * Returns true if the guide window is currently being shown.
     */
    public boolean isShowing() {
        return getRoot().isShowing();
    }

    /**
     * Hides the guide window.
     */
    public void hide() {
        getRoot().hide();
    }

    /**
     * Focuses on the guide window.
     */
    public void focus() {
        getRoot().requestFocus();
    }

    /**
     * Replaces the current texts and image with the values stored in the currentPage enum.
     */
    private void showPage() {
        title.setText(currentPage.title);
        textBox.setText(currentPage.description);
        Image demoImage = new Image(MainApp.class.getResourceAsStream(currentPage.screenshot));
        screenshot.setImage(demoImage);
    }

    /**
     * Show the next page of the guide.
     */
    @FXML
    private void handleNext() {
        switch (currentPage) {
        case ONE:
            currentPage = Page.TWO;
            showPage();
            return;
        case TWO:
            currentPage = Page.THREE;
            showPage();
            return;
        case THREE:
            currentPage = Page.FOUR;
            showPage();
            return;
        case FOUR:
            currentPage = Page.FIVE;
            showPage();
            return;
        case FIVE:
            currentPage = Page.SIX;
            showPage();
            return;
        case SIX:
            currentPage = Page.SEVEN;
            showPage();
            return;
        case SEVEN:
            currentPage = Page.EIGHT;
            showPage();
            return;
        default:
        }

    }

    /**
     * Show the previous page of the guide.
     */
    @FXML
    private void handleBack() {
        switch (currentPage) {
        case ONE:
            return;
        case TWO:
            currentPage = Page.ONE;
            showPage();
            return;
        case THREE:
            currentPage = Page.TWO;
            showPage();
            return;
        case FOUR:
            currentPage = Page.THREE;
            showPage();
            return;
        case FIVE:
            currentPage = Page.FOUR;
            showPage();
            return;
        case SIX:
            currentPage = Page.FIVE;
            showPage();
            return;
        case SEVEN:
            currentPage = Page.SIX;
            showPage();
            return;
        case EIGHT:
            currentPage = Page.SEVEN;
            showPage();
            return;
        default:
        }

    }
}
