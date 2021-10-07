package seedu.academydirectory.ui;

import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.Scanner;
import java.util.logging.Logger;

import com.sandec.mdfx.MarkdownView;

import javafx.fxml.FXML;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.input.Clipboard;
import javafx.scene.input.ClipboardContent;
import javafx.scene.layout.HBox;
import javafx.stage.Stage;
import seedu.academydirectory.commons.core.LogsCenter;

/**
 * Controller for a help page
 */
public class HelpWindow extends UiPart<Stage> {

    public static final String USERGUIDE_URL = "https://ay2122s1-cs2103t-t15-3.github.io/tp/UserGuide.html";
    public static final String HELP_MESSAGE = "### User guide\n#### " + USERGUIDE_URL + "\n";

    private static final Logger logger = LogsCenter.getLogger(HelpWindow.class);
    private static final File USER_GUIDE = new File("docs/UserGuide.md");
    private static final String FXML = "HelpWindow.fxml";
    private static final String MARKDOWN_CSS = "/view/markdown.css";
    private static final String SUMMARY_HEADER = "## Command summary";

    private static final int DEFAULT_WIDTH = 750;
    private static final int DEFAULT_HEIGHT = 400;

    @FXML
    private Button copyButton;

    /**
     * Creates a new HelpWindow.
     *
     * @param root Stage to use as the root of the HelpWindow.
     */
    public HelpWindow(Stage root) {
        super(FXML, root);

        StringBuilder builder = new StringBuilder(HELP_MESSAGE);
        addHelpContent(builder);
        Label label = new Label(builder.toString());
        label.setVisible(false);

        MarkdownView markdownView = new MarkdownView();
        markdownView.mdStringProperty().bind(label.textProperty());
        markdownView.getStylesheets().add(MARKDOWN_CSS);

        ScrollPane content = new ScrollPane(markdownView);
        content.setFitToWidth(true);
        HBox container = new HBox(label, content);
        container.getChildren().add(copyButton);

        root.setScene(new Scene(container, DEFAULT_WIDTH, DEFAULT_HEIGHT));
    }

    /**
     * Creates a new HelpWindow.
     */
    public HelpWindow() {
        this(new Stage());
    }

    /**
     * Get markdown content of UG for conversion to help window
     * @param builder string builder to append
     */
    private void addHelpContent(StringBuilder builder) {
        try {
            FileReader fileReader = new FileReader(USER_GUIDE);
            Scanner sc = new Scanner(fileReader);
            boolean isSummary = false;
            while (sc.hasNextLine()) {
                String nextLine = sc.nextLine().trim();
                if (nextLine.contains(SUMMARY_HEADER)) {
                    isSummary = true;
                }
                if (isSummary) {
                    builder.append(nextLine).append("\n");
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
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
        url.putString(USERGUIDE_URL);
        clipboard.setContent(url);
    }
}
