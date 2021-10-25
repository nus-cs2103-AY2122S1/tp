package seedu.friendbook.ui;

import static seedu.friendbook.model.person.Avatar.AVATAR_PATH;

import java.util.Objects;
import java.util.logging.Logger;

import javafx.fxml.FXML;
import javafx.geometry.Pos;
import javafx.scene.control.Accordion;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TitledPane;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.Clipboard;
import javafx.scene.input.ClipboardContent;
import javafx.scene.layout.TilePane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import seedu.friendbook.MainApp;
import seedu.friendbook.commons.core.LogsCenter;
import seedu.friendbook.logic.commands.Command;

/**
 * Controller for a help page
 */
public class HelpWindow extends UiPart<Stage> {

    public static final String USER_GUIDE_URL =
            "https://ay2122s1-cs2103-f10-3.github.io/tp/UserGuide.html";
    public static final String HELP_MESSAGE = "For more details, please refer to the user guide : " + USER_GUIDE_URL;

    private static final Logger logger = LogsCenter.getLogger(HelpWindow.class);
    private static final String FXML = "HelpWindow.fxml";

    @FXML
    private Button copyButton;

    @FXML
    private Label helpMessage;

    @FXML
    private Accordion commandListAccordion;

    private final Command.CommandList[] commandList = Command.CommandList.values();
    private final TitledPane[] commandPanes = new TitledPane[commandList.length];

    @FXML
    private TilePane avatarPane;

    /**
     * Creates a new HelpWindow.
     *
     * @param root Stage to use as the root of the HelpWindow.
     */
    public HelpWindow(Stage root) {
        super(FXML, root);
        helpMessage.setText(HELP_MESSAGE);

        // images view
        for (int i = 1; i < 21; i++) {
            VBox avatarContainer = new VBox();
            avatarContainer.setAlignment(Pos.TOP_CENTER);
            Image avatar = new Image(Objects.requireNonNull(
                    MainApp.class.getResourceAsStream(AVATAR_PATH + i + ".png")));
            ImageView imageView = new ImageView(avatar);
            imageView.setFitHeight(50);
            imageView.setFitWidth(50);
            Label avatarLabel = new Label("Avatar " + i);
            avatarContainer.getChildren().addAll(imageView, avatarLabel);
            avatarPane.getChildren().add(avatarContainer);
        }

        // Command list view
        for (int i = 0; i < commandList.length; i++) {
            Label commandInstruction = new Label(commandList[i].getCommandMessageUsage());
            commandInstruction.setWrapText(true);
            commandPanes[i] = new TitledPane(commandList[i].toString(), commandInstruction);
        }
        commandListAccordion.getPanes().addAll(commandPanes);

    }

    /**
     * Creates a new HelpWindow.
     */
    public HelpWindow() {
        this(new Stage());
    }

    /**
     * Shows the help window.
     * @throws IllegalStateException
     * <ul>
     *     <li>
     *         if this method is called on a thread other than the JavaFX Application Thread.
     *     </li>
     *     <li>
     *         if this method is called during animation or layout processing.
     *     </li>
     *     <li>
     *         if this method is called on the primary stage.
     *     </li>
     *     <li>
     *         if {@code dialogStage} is already showing.
     *     </li>
     * </ul>
     */
    public void show() {
        logger.fine("Showing help page about the application.");
        getRoot().show();
        getRoot().centerOnScreen();
    }

    /**
     * Returns true if the help window is currently being shown.
     */
    public boolean isShowing() {
        return getRoot().isShowing();
    }

    /**
     * Hides the help window.
     */
    public void hide() {
        getRoot().hide();
    }

    /**
     * Focuses on the help window.
     */
    public void focus() {
        getRoot().requestFocus();
    }

    /**
     * Copies the URL to the user guide to the clipboard.
     */
    @FXML
    private void copyUrl() {
        final Clipboard clipboard = Clipboard.getSystemClipboard();
        final ClipboardContent url = new ClipboardContent();
        url.putString(USER_GUIDE_URL);
        clipboard.setContent(url);
    }
}
