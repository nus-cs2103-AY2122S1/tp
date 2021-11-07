package seedu.academydirectory.ui;

import java.util.logging.Logger;

import com.sandec.mdfx.MarkdownView;

import javafx.fxml.FXML;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.input.Clipboard;
import javafx.scene.input.ClipboardContent;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Priority;
import javafx.stage.Stage;
import seedu.academydirectory.commons.core.LogsCenter;
import seedu.academydirectory.commons.core.Messages;

/**
 * Controller for a help page
 */
public class HelpWindow extends UiPart<Stage> {

    public static final String USERGUIDE_URL = "https://ay2122s1-cs2103t-t15-3.github.io/tp/UserGuide.html";
    public static final String HELP_MESSAGE = "#### Copy user guide:\n##### " + USERGUIDE_URL
            + "\n##### Type in 'help' + COMMAND_NAME for more info of each command. E.g: `help add`\n"
            + "##### Press ESC to exit window otherwise\n";

    private static final Logger logger = LogsCenter.getLogger(HelpWindow.class);
    private static final String FXML = "HelpWindow.fxml";
    private static final String MARKDOWN_CSS = "/view/markdown.css";

    private static final int DEFAULT_WIDTH = 750;
    private static final int DEFAULT_HEIGHT = 400;

    @FXML
    private Button copyButton;

    private final Stage root;
    private final MarkdownView markdownView;

    /**
     * Creates a new HelpWindow.
     *
     * @param root Stage to use as the root of the HelpWindow.
     */
    public HelpWindow(Stage root) {
        super(FXML, root);
        this.root = root;
        root.addEventHandler(KeyEvent.KEY_RELEASED, event -> {
            if (event.getCode().equals(KeyCode.ESCAPE)) {
                hide();
            }
        });
        this.markdownView = new MarkdownView();
        markdownView.getStylesheets().clear();
        markdownView.getStylesheets().add(MARKDOWN_CSS);
        setHelpMessage(Messages.GENERAL_HELP_MESSAGE);
    }

    /**
     * Creates a new HelpWindow.
     */
    public HelpWindow() {
        this(new Stage());
    }


    /**
     * Set the help message to be put on the Help Window
     * @param helpMessage of a command
     */
    public void setHelpMessage(String helpMessage) {
        wrapInMarkdown(helpMessage);
        wrapInContainer(markdownView);
    }

    /**
     * Wrap the given Help Message in a Markdown-supported view
     * @param helpMessage of a command
     */
    private void wrapInMarkdown(String helpMessage) {
        Label label = new Label(HELP_MESSAGE + helpMessage);
        markdownView.mdStringProperty().bind(label.textProperty());
    }

    /**
     * Wrap the given Help Message Markdown view in a container
     * @param markdownView wrapper of help message
     */
    private void wrapInContainer(MarkdownView markdownView) {
        ScrollPane content = new ScrollPane(markdownView);
        content.setFitToWidth(true);
        HBox container = new HBox(content, copyButton);
        HBox.setHgrow(content, Priority.ALWAYS);
        root.setScene(new Scene(container, DEFAULT_WIDTH, DEFAULT_HEIGHT));
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
        logger.info("Link copied");
        final Clipboard clipboard = Clipboard.getSystemClipboard();
        final ClipboardContent url = new ClipboardContent();
        url.putString(USERGUIDE_URL);
        clipboard.setContent(url);
    }
}
