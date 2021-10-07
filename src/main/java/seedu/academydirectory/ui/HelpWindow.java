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
    private static final String FXML = "HelpWindow.fxml";

    private static final File USER_GUIDE = new File("docs/UserGuide.md");
    private static final String COMMON = "## Command summary";

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
        MarkdownView markdownView = new MarkdownView();
        try {
            FileReader fileReader = new FileReader(USER_GUIDE);
            Scanner sc = new Scanner(fileReader);
            boolean isSummary = false;
            while (sc.hasNextLine()) {
                String nextLine = sc.nextLine().trim();
                if (nextLine.contains(COMMON)) {
                    isSummary = true;
                }
                if (isSummary) {
                    builder.append(nextLine).append("\n");
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            Label label = new Label(builder.toString());
            label.setVisible(false);
            markdownView.mdStringProperty().bind(label.textProperty());
            markdownView.getStylesheets().add("/view/markdown.css");
            ScrollPane content = new ScrollPane(markdownView);
            content.setFitToWidth(true);
            HBox temp = new HBox(label, content);
            temp.getChildren().add(copyButton);
            root.setScene(new Scene(temp, 750, 400));
        }
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
        url.putString(USERGUIDE_URL);
        clipboard.setContent(url);
    }
}
