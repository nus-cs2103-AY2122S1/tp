package seedu.address.ui;

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
    private PersonListPanel personListPanel;
    private ResultDisplay resultDisplay;
    private HelpWindow helpWindow;
    private FriendMainCard friendMainCard;
    private GameMainCard gameMainCard;


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

        helpWindow = new HelpWindow();
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
    void fillInnerParts() {
        personListPanel = new PersonListPanel(logic.getFilteredFriendsList());
        showFriendList();

        resultDisplay = new ResultDisplay();
        resultDisplayPlaceholder.getChildren().add(resultDisplay.getRoot());

        StatusBarFooter statusBarFooter = new StatusBarFooter(logic.getAddressBookFilePath());
        statusbarPlaceholder.getChildren().add(statusBarFooter.getRoot());

        CommandBox commandBox = new CommandBox(this::executeCommand);
        commandBoxPlaceholder.getChildren().add(commandBox.getRoot());
    }

    /**
     * Sets the default size based on {@code guiSettings}.
     */
    private void setWindowDefaultSize(GuiSettings guiSettings) {
        primaryStage.setHeight(guiSettings.getWindowHeight());
        primaryStage.setWidth(guiSettings.getWindowWidth());
        if (guiSettings.getWindowCoordinates() != null) {
            primaryStage.setX(guiSettings.getWindowCoordinates().getX());
            primaryStage.setY(guiSettings.getWindowCoordinates().getY());
        }
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
        helpWindow.hide();
        primaryStage.hide();
    }

    private void removePersonListPanel() {
        personListPanelPlaceholder.getChildren().removeAll(personListPanel.getRoot());
    }

    private void removeFriendMainCard() {
        if (friendMainCard != null) {
            personListPanelPlaceholder.getChildren().removeAll(friendMainCard.getRoot());
            friendMainCard = null;
        }
    }

    private void removeGameMainCard() {
        if (gameMainCard != null) {
            personListPanelPlaceholder.getChildren().removeAll(gameMainCard.getRoot());
            gameMainCard = null;
        }
    }

    /**
     * Shows the {@Code FriendMainCard} when the {@Code find} command is run.
     * @param commandResult The {@Code commandResult} from the find command.
     */
    private void handleShowFriendMainCard(CommandResult commandResult) {
        // only mounts the friend main card if it is not already mounted or if friend different
        if (friendMainCard == null) {
            removePersonListPanel();
            removeGameMainCard();
            friendMainCard = new FriendMainCard(commandResult.getFriendToGet(), logic.getFilteredGamesList());
            personListPanelPlaceholder.getChildren().add(friendMainCard.getRoot());
        } else if (!friendMainCard.getCurrentFriend().equals(commandResult.getFriendToGet())) {
            removeFriendMainCard();
            friendMainCard = new FriendMainCard(commandResult.getFriendToGet(), logic.getFilteredGamesList());
            personListPanelPlaceholder.getChildren().add(friendMainCard.getRoot());
        }
    }

    /**
     * Shows the {@Code GameMainCard} when the {@Code find} command is run.
     * @param commandResult The {@Code commandResult} from the find command.
     */
    private void handleShowGameMainCard(CommandResult commandResult) {
        // only mounts the friend main card if it is not already mounted
        if (gameMainCard == null) {
            // add code to removeGameListPanel
            removeFriendMainCard();
            removePersonListPanel();
            //TODO: the friend list that is retrieved should contain friends that play that game only
            gameMainCard = new GameMainCard(commandResult.getGameToGet(), logic.getFilteredFriendsList());
            personListPanelPlaceholder.getChildren().add(gameMainCard.getRoot());
        } else if (!gameMainCard.getCurrentGame().equals(commandResult.getGameToGet())) {
            removePersonListPanel();
            gameMainCard = new GameMainCard(commandResult.getGameToGet(), logic.getFilteredFriendsList());
            personListPanelPlaceholder.getChildren().add(gameMainCard.getRoot());
        }
    }

    /**
     * Shows the {@Code PersonListPanel} when the {@Code friend --list} command is run.
     */
    private void showFriendList() {
        // only shows friend list if not already being shown
        if (!personListPanelPlaceholder.getChildren().contains(personListPanel.getRoot())) {
            personListPanelPlaceholder.getChildren().add(personListPanel.getRoot());
        }
    }

    /**
     * Handles the mounting and dismounting of UI Regions when a {@Code friend}
     * command is run.
     * @param commandResult The {@Code commandResult} from the {@Code friend} command.
     */
    private void handleFriendCommand(CommandResult commandResult) {
        if (commandResult.isFriendGet()) {
            handleShowFriendMainCard(commandResult);
        } else {
            // TODO: remove the friend main card and show the friend list
            removeFriendMainCard();
            removeGameMainCard();
            showFriendList();
        }
    }

    /**
     * Handles the mounting and dismounting of UI Regions when a {@Code game}
     * command is run.
     * @param commandResult The {@Code commandResult} from the {@Code game} command.
     */
    // TODO: Handle the game command to list or get
    private void handleGameCommand(CommandResult commandResult) {
        if (commandResult.isGameGet()) {
            // TODO: hide the personlist panel
            //TODO: show the game detailed card
            handleShowGameMainCard(commandResult);
        } else {
            // TODO: temporary, replace with showGameList
            showFriendList();
            // removes everything else
            //TODO: Add code to remove friend list
            removeFriendMainCard();
            removeGameMainCard();
        }
    }

    public PersonListPanel getPersonListPanel() {
        return personListPanel;
    }

    /**
     * Executes the command based on the {@Code CommandType}
     * enumeration.
     *
     * @see seedu.address.logic.Logic#execute(String)
     */
    private CommandResult executeCommand(String commandText) throws CommandException, ParseException {
        try {
            CommandResult commandResult = logic.execute(commandText);
            logger.info("Result: " + commandResult.getFeedbackToUser());
            resultDisplay.setFeedbackToUser(commandResult.getFeedbackToUser());

            switch (commandResult.getCommandType()) {
            case FRIEND_GET: case FRIEND_ADD: case FRIEND_LIST: case FRIEND_DELETE:
                handleFriendCommand(commandResult);
                break;
            case GAME_ADD: case GAME_GET: case GAME_DELETE: case GAME_LIST:
                handleGameCommand(commandResult);
                break;
            case HELP:
                handleHelp();
                break;
            case EXIT:
                handleExit();
                break;
            default:
                break;
            }
            return commandResult;
        } catch (CommandException | ParseException e) {
            logger.info("Invalid command: " + commandText);
            resultDisplay.setFeedbackToUser(e.getMessage());
            throw e;
        }
    }
}
