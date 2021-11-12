package seedu.address.ui;

import java.util.Optional;
import java.util.logging.Logger;

import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.geometry.Rectangle2D;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonBar;
import javafx.scene.control.ButtonType;
import javafx.scene.control.MenuItem;
import javafx.scene.control.TextInputControl;
import javafx.scene.input.KeyCombination;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.StackPane;
import javafx.stage.Screen;
import javafx.stage.Stage;
import seedu.address.commons.core.GuiSettings;
import seedu.address.commons.core.LogsCenter;
import seedu.address.logic.Logic;
import seedu.address.logic.commands.CommandResult;
import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.logic.comparators.exceptions.ComparatorException;
import seedu.address.logic.parser.exceptions.ParseException;
import seedu.address.model.person.Person;

/**
 * The Main Window. Provides the basic application layout containing
 * a menu bar and space where other JavaFX elements can be placed.
 */
public class MainWindow extends UiPart<Stage> {

    private static final String FXML = "MainWindow.fxml";

    private final Logger logger = LogsCenter.getLogger(getClass());

    private Stage primaryStage;
    private Logic logic;

    private double windowWidth;

    // Independent Ui parts residing in this Ui container
    private PersonListPanel personListPanel;
    private ResultDisplay resultDisplay;
    private HelpWindow helpWindow;
    private FullPersonCard fullPersonCard;
    private String importStatus;

    @FXML
    private StackPane commandBoxPlaceholder;

    @FXML
    private MenuItem helpMenuItem;

    @FXML
    private StackPane personListPanelPlaceholder;

    @FXML
    private StackPane resultDisplayPlaceholder;

    @FXML
    private StackPane statusbarPlaceholder;

    @FXML
    private StackPane fullPersonCardPlaceholder;

    /**
     * Creates a {@code MainWindow} with the given {@code Stage} and {@code Logic}.
     */
    public MainWindow(Stage primaryStage, Logic logic) {
        super(FXML, primaryStage);

        // Set dependencies
        this.primaryStage = primaryStage;
        this.logic = logic;

        // Configure the UI
        setWindowDefaultSize();

        setAccelerators();

        helpWindow = new HelpWindow();

        this.windowWidth = this.primaryStage.getWidth();
    }

    public Stage getPrimaryStage() {
        return primaryStage;
    }

    private void setAccelerators() {
        setAccelerator(helpMenuItem, KeyCombination.valueOf("F1"));
    }

    /**
     * Sets the accelerator of a MenuItem.
     * @param keyCombination the KeyCombination value of the accelerator
     */
    private void setAccelerator(MenuItem menuItem, KeyCombination keyCombination) {
        menuItem.setAccelerator(keyCombination);

        /*
         * TODO: the code below can be removed once the bug reported here
         * https://bugs.openjdk.java.net/browse/JDK-8131666
         * is fixed in later version of SDK.
         *
         * According to the bug report, TextInputControl (TextField, TextArea) will
         * consume function-key events. Because CommandBox contains a TextField, and
         * ResultDisplay contains a TextArea, thus some accelerators (e.g F1) will
         * not work when the focus is in them because the key event is consumed by
         * the TextInputControl(s).
         *
         * For now, we add following event filter to capture such key events and open
         * help window purposely so to support accelerators even when focus is
         * in CommandBox or ResultDisplay.
         */
        getRoot().addEventFilter(KeyEvent.KEY_PRESSED, event -> {
            if (event.getTarget() instanceof TextInputControl && keyCombination.match(event)) {
                menuItem.getOnAction().handle(new ActionEvent());
                event.consume();
            }
        });
    }

    /**
     * Fills up all the placeholders of this window.
     */
    public void fillInnerParts() {
        personListPanel = new PersonListPanel(logic.getOriginalPersonList(), this.windowWidth);
        personListPanelPlaceholder.getChildren().add(personListPanel.getRoot());

        resultDisplay = new ResultDisplay();
        resultDisplayPlaceholder.getChildren().add(resultDisplay.getRoot());

        StatusBarFooter statusBarFooter = new StatusBarFooter(logic.getAddressBookFilePath());
        statusbarPlaceholder.getChildren().add(statusBarFooter.getRoot());

        CommandBox commandBox = new CommandBox(this::executeCommand);
        commandBoxPlaceholder.getChildren().add(commandBox.getRoot());

        //Displays first person in the list by default
        fullPersonCard = new FullPersonCard(this.logic.getFilteredPersonList(), this.windowWidth);
        fullPersonCardPlaceholder.getChildren().add(fullPersonCard.getRoot());
        resultDisplay.setFeedbackToUser(importStatus);
    }

    /**
     * Returns a list containing full details of persons stored in the address book.
     * @return An ObservableList of people stored in the address book.
     */
    public ObservableList<Person> getPersonList() {
        return this.logic.getFilteredPersonList();
    }

    /**
     * Updates the FullPersonCard window with the details of the person chosen for display
     */
    public void fillFullPersonCard() {
        fullPersonCardPlaceholder.getChildren().remove(fullPersonCard.getRoot());
        fullPersonCard = new FullPersonCard(getPersonList(), this.windowWidth);
        fullPersonCardPlaceholder.getChildren().add(fullPersonCard.getRoot());
        resultDisplay.setFeedbackToUser(importStatus);
    }

    /**
     * Sets the default size to full screen
     */
    private void setWindowDefaultSize() {
        Rectangle2D primaryScreenBounds = Screen.getPrimary().getVisualBounds();
        primaryStage.setHeight(primaryScreenBounds.getHeight());
        primaryStage.setWidth(primaryScreenBounds.getWidth());
        primaryStage.setMaximized(true);
    }

    /**
     * Opens the help window or focuses on it if it's already opened.
     */
    @FXML
    public void handleHelp() {
        if (!helpWindow.isShowing()) {
            helpWindow.show();
        } else {
            helpWindow.focus();
        }
    }

    /**
     * Runs popupwindow to get import settings and showing of primary stage
     *
     */
    void show() {
        importStatus = importCsvUserPrompt();
        primaryStage.show();
    }

    /**
     * Closes the application.
     */
    @FXML
    private void handleExit() {
        GuiSettings guiSettings = new GuiSettings(primaryStage.getWidth(), primaryStage.getHeight(),
                (int) primaryStage.getX(), (int) primaryStage.getY());
        logic.setGuiSettings(guiSettings);
        exportCsvUserPrompt();
        helpWindow.hide();
        primaryStage.hide();
    }

    public PersonListPanel getPersonListPanel() {
        return personListPanel;
    }

    /**
     * Executes the command and returns the result.
     *
     * @see Logic#execute(String)
     */
    private CommandResult executeCommand(String commandText)
            throws CommandException, ParseException, ComparatorException {
        try {
            CommandResult commandResult = logic.execute(commandText);
            logger.info("Result: " + commandResult.getFeedbackToUser());
            resultDisplay.setFeedbackToUser(commandResult.getFeedbackToUser());

            if (commandResult.isShowHelp()) {
                handleHelp();
            }

            if (commandResult.isExit()) {
                handleExit();
            }

            return commandResult;
        } catch (CommandException | ParseException | ComparatorException e) {
            logger.info("Invalid command: " + commandText);
            resultDisplay.setFeedbackToUser(e.getMessage());
            throw e;
        }
    }

    /**
     * getting the user setting for excel import
     *
     */
    private String importCsvUserPrompt() {
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.setHeaderText("Do you want to import contacts from csv?");
        alert.setContentText("There are " + logic.getFilteredPersonList().size() + " people currently in the "
                + "addressbook");
        ButtonType startNewUsingImport = new ButtonType("Start New Using Import", ButtonBar.ButtonData.NO);
        ButtonType addOnImports = new ButtonType("Add on imports", ButtonBar.ButtonData.YES);
        ButtonType dontImport = new ButtonType("Don't  Import", ButtonBar.ButtonData.CANCEL_CLOSE);
        alert.getButtonTypes().setAll(addOnImports, startNewUsingImport, dontImport);
        Optional<ButtonType> result = alert.showAndWait();

        if (result.get() == addOnImports) {
            return logic.importData();
        } else if (result.get() == startNewUsingImport) {
            logic.exportResetData();
            return logic.importData();
        }
        return "No additional import";
    }

    /**
     * getting the user setting for excel import
     *
     */
    private String exportCsvUserPrompt() {
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.setHeaderText("Do you want to export contacts from csv?");
        alert.setContentText("There are " + logic.getAddressBook().getPersonList().size() + " people currently in the "
                + "addressbook");
        ButtonType yesButton = new ButtonType("Export", ButtonBar.ButtonData.YES);
        ButtonType noButton = new ButtonType("Don't export", ButtonBar.ButtonData.NO);
        alert.getButtonTypes().setAll(yesButton, noButton);
        Optional<ButtonType> result = alert.showAndWait();

        if (result.get() == yesButton) {
            return logic.exportData();
        }
        return "Exiting application";
    }

}
