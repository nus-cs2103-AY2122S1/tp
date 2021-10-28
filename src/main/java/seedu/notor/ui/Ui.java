package seedu.notor.ui;

import javafx.stage.Stage;
import seedu.notor.ui.listpanel.PersonListPanel;

/**
 * API of UI component
 */
public interface Ui {

    /**
     * Starts the UI (and the App).
     */
    void start(Stage primaryStage);

    /**
     * Gets the PersonListPanel currently being displayed.
     */
    PersonListPanel getPersonListPanel();

}
