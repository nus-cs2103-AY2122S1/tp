package seedu.address.ui;

import java.awt.Desktop;
import java.io.IOException;
import java.util.logging.Logger;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.MenuItem;
import javafx.scene.control.TextInputControl;
import javafx.scene.input.KeyCombination;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;
import seedu.address.commons.core.GuiSettings;
import seedu.address.commons.core.LogsCenter;
import seedu.address.logic.Logic;
import seedu.address.logic.commands.CommandResult;
import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.logic.parser.exceptions.ParseException;
import seedu.address.ui.util.Help;

/**
 * The Main Window. Provides the basic application layout containing
 * a menu bar and space where other JavaFX elements can be placed.
 */
public class MainWindow extends UiPart<Stage> {

    private static final String FXML = "MainWindow.fxml";

    private final Logger logger = LogsCenter.getLogger(getClass());

    private Stage primaryStage;
    private Logic logic;

    // Independent Ui parts residing in this Ui container
    private ContactListPanel contactListPanel;
    private ResultDisplay resultDisplay;
    private Help helpWindow;
    private HelpWindow backUpHelpWindow;

    @FXML
    private StackPane commandBoxPlaceholder;

    @FXML
    private MenuItem helpMenuItem;

    @FXML
    private StackPane contactListPanelPlaceholder;

    @FXML
    private StackPane resultDisplayPlaceholder;

    @FXML
    private StackPane statusbarPlaceholder;

    /**
     * Creates a {@code MainWindow} with the given {@code Stage} and {@code Logic}.
     */
    public MainWindow(Stage primaryStage, Logic logic) {
        super(FXML, primaryStage);

        // Set dependencies
        this.primaryStage = primaryStage;
        this.logic = logic;

        // Configure the UI
        setWindowDefaultSize(logic.getGuiSettings());

        setAccelerators();
        helpWindow = new Help();
        backUpHelpWindow = new HelpWindow();
    }

    public Stage getPrimaryStage() {
        return primaryStage;
    }

    private void setAccelerators() {
        setAccelerator(helpMenuItem, KeyCombination.valueOf("F1"));
    }

    /**
     * Sets the accelerator of a MenuItem.
     * @param keyCombination the KeyCombination value of the accelerator.
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
    void fillInnerParts() {
        resultDisplay = new ResultDisplay();
        resultDisplayPlaceholder.getChildren().add(resultDisplay.getRoot());

        StatusBarFooter statusBarFooter = new StatusBarFooter(logic.getAddressBookFilePath());
        statusbarPlaceholder.getChildren().add(statusBarFooter.getRoot());

        CommandBox commandBox = new CommandBox(this::executeCommand);
        commandBoxPlaceholder.getChildren().add(commandBox.getRoot());

        contactListPanel = new ContactListPanel(logic.getFilteredContactList(), logic.getSummary(), commandBox);
        contactListPanelPlaceholder.getChildren().add(contactListPanel.getRoot());
    }

    /**
     * Sets the default size based on {@code guiSettings}.
     */
    private void setWindowDefaultSize(GuiSettings guiSettings) {
        primaryStage.setHeight(guiSettings.getWindowHeight());
        primaryStage.setWidth(guiSettings.getWindowWidth());
        primaryStage.setMinHeight(guiSettings.getMinWindowHeight());
        primaryStage.setMinWidth(guiSettings.getMinWindowWidth());
        if (guiSettings.getWindowCoordinates() != null) {
            primaryStage.setX(guiSettings.getWindowCoordinates().getX());
            primaryStage.setY(guiSettings.getWindowCoordinates().getY());
        }
    }

    /**
     * A helper method that opens either user guide or command summary in the user's default browser depending on
     * the input {@code type}.
     * @param type an int that determines the page to be opened.
     * @throws IOException if the user default browser is not found, or it fails to be launched, or the default handler
     * application failed to be launched.
     */
    private void openSupport(int type) throws IOException {
        if (type == 1) {
            helpWindow.openUserGuide();
        } else if (type == 2) {
            helpWindow.openCommandSummary();
        } else {
            handleBackUpHelp();
        }
    }

    /**
     * A method that opens either user guide or command summary in the user's default browser depending on
     * the input {@code type}.
     * When type == 1, user guide will be attempted to be opened.
     * When type == 2, command summary will be attempted to be opened.
     * @param type an int that determines the page to be opened.
     */
    private void handleSupport(int type) {
        assert type == 1 || type == 2;
        if (Desktop.isDesktopSupported()) {
            try {
                openSupport(type);
            } catch (IOException | SecurityException | UnsupportedOperationException ex) {
                logger.info(ex.getMessage());
                handleBackUpHelp();
            }
        } else {
            handleBackUpHelp();
        }
    }

    /**
     * Opens the Online User Guide if possible, else opens the secondary internal help window.
     */
    public void handleHelp() {
        handleSupport(1);
    }

    /**
     * Opens the online Command Summary if possible, else opens the secondary internal help window.
     */
    public void handleCommandSummary() {
        handleSupport(2);
    }

    /**
     * Opens the secondary help window.
     */
    @FXML
    public void handleBackUpHelp() {
        if (!backUpHelpWindow.isShowing()) {
            backUpHelpWindow.show();
        } else {
            backUpHelpWindow.focus();
        }
    }

    void show() {
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
        primaryStage.hide();
    }

    public ContactListPanel getContactListPanel() {
        return contactListPanel;
    }

    /**
     * Executes the command and returns the result.
     *
     * @see seedu.address.logic.Logic#execute(String)
     */
    private CommandResult executeCommand(String commandText) throws CommandException, ParseException {
        try {
            CommandResult commandResult = logic.execute(commandText);
            logger.info("Result: " + commandResult.getFeedbackToUser());
            resultDisplay.setFeedbackToUser(commandResult.getFeedbackToUser());

            if (commandResult.isShowCommandSummary()) {
                handleCommandSummary();
            }

            if (commandResult.isShowHelp()) {
                handleHelp();
            }

            if (commandResult.isExit()) {
                handleExit();
            }

            if (commandResult.isDisplayContact()) {
                contactListPanel.handleDisplay(commandResult.getContactToDisplay());
            }

            if (commandResult.isDisplaySummary()) {
                contactListPanel.handleDisplay(commandResult.getSummaryToDisplay());
            }

            return commandResult;
        } catch (CommandException | ParseException e) {
            logger.info("Invalid command: " + commandText);
            resultDisplay.setFeedbackToUser(e.getMessage());
            throw e;
        }
    }
}
