package seedu.plannermd.ui;

import javafx.stage.Stage;

/**
 * API of UI component
 */
public interface Ui {

    /** Starts the UI (and the App).  */
    void start(Stage primaryStage);

    /** Gets the PersonTabSwitcher from the UI */
    PersonTabSwitcher getPersonTabSwitcher();

}
