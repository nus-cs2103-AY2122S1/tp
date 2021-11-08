package seedu.address.ui;

import java.util.logging.Logger;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.MenuItem;
import javafx.scene.control.TextInputControl;
import javafx.scene.input.KeyCombination;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;
import seedu.address.commons.core.GuiSettings;
import seedu.address.commons.core.LogsCenter;
import seedu.address.logic.Logic;
import seedu.address.logic.commands.CommandResult;
import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.logic.parser.exceptions.ParseException;
import seedu.address.model.util.TaskStatusChecker;

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
    private TaskListPanel taskListPanel;
    private ResultDisplay resultDisplay;
    private HelpWindow helpWindow;
    private StatisticsDisplay statisticsDisplay;
    private AllTaskListPanel allTaskListPanel;
    private CommandBox commandBox;
    private TaskStatusChecker taskStatusChecker;
    @FXML
    private StackPane commandBoxPlaceholder;

    @FXML
    private MenuItem helpMenuItem;

    @FXML
    private StackPane personListPanelPlaceholder;

    @FXML
    private AnchorPane resultDisplayPlaceholder;

    @FXML
    private AnchorPane statisticsDisplayPlaceholder;
    @FXML
    private StackPane statusbarPlaceholder;

    @FXML
    private AnchorPane personListSplitPanel;

    @FXML
    private AnchorPane taskListSplitPanel;

    /**
     * Creates a {@code MainWindow} with the given {@code Stage} and {@code Logic}.
     */
    public MainWindow(Stage primaryStage, Logic logic, TaskStatusChecker taskStatusChecker) {
        super(FXML, primaryStage);

        // Set dependencies
        this.primaryStage = primaryStage;
        this.logic = logic;
        this.taskStatusChecker = taskStatusChecker;

        // Configure the UI
        setWindowDefaultSize(logic.getGuiSettings());

        setAccelerators();

        helpWindow = HelpWindow.getWindow();
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
     * Event handler when user clicks on a particular {@code ListCell} in the {@code personListView}.
     * Updates the {@code taskListPanel} to show the task list of the {@code Person} represented
     * by the selected {@code ListCell}.
     */
    @FXML
    public void handleMouseClicked(String inputCommand) {
        try {
            executeCommand(inputCommand, true);
        } catch (ParseException | CommandException e) {
            logger.warning("HandleMouseClicked caught an exception when not supposed to:\n"
                    + e.getMessage());
            resultDisplay.setFeedbackToUser(e.getMessage());
        }
    }

    /** Makes child node of anchor resize together with its parent. */
    private void setAnchorProperties(AnchorPane ap) {
        AnchorPane.setBottomAnchor(ap.getChildren().get(0), 0.0);
        AnchorPane.setLeftAnchor(ap.getChildren().get(0), 0.0);
        AnchorPane.setRightAnchor(ap.getChildren().get(0), 0.0);
        AnchorPane.setTopAnchor(ap.getChildren().get(0), 0.0);
    }

    /**
     * Fills up all the placeholders of this window.
     */
    void fillInnerParts() {
        personListPanel = new PersonListPanel(logic.getFilteredPersonList(), this);

        personListSplitPanel.getChildren().add(personListPanel.getRoot());
        setAnchorProperties(personListSplitPanel);

        taskListPanel = new TaskListPanel(logic.getDisplayTaskList());
        allTaskListPanel = new AllTaskListPanel(logic.getFilteredPersonList());
        taskListSplitPanel.getChildren().add(taskListPanel.getRoot());
        setAnchorProperties(taskListSplitPanel);

        resultDisplay = new ResultDisplay();
        resultDisplayPlaceholder.getChildren().add(resultDisplay.getRoot());
        setAnchorProperties(resultDisplayPlaceholder);

        statisticsDisplay = new StatisticsDisplay(logic.getStatistics());
        statisticsDisplayPlaceholder.getChildren().add(statisticsDisplay.getRoot());
        setAnchorProperties(statisticsDisplayPlaceholder);

        StatusBarFooter statusBarFooter = new StatusBarFooter(logic.getAddressBookFilePath());
        statusbarPlaceholder.getChildren().add(statusBarFooter.getRoot());

        commandBox = new CommandBox(this::executeCommand);
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
        taskStatusChecker.stop();
    }

    @FXML
    private void handleDisplayAllTaskList() {
        taskListSplitPanel.getChildren().clear();
        taskListSplitPanel.getChildren().add(allTaskListPanel.getRoot());
        setAnchorProperties(taskListSplitPanel);
    }

    @FXML
    private void handleDisplaySingleTaskList() {
        taskListSplitPanel.getChildren().clear();
        taskListSplitPanel.getChildren().add(taskListPanel.getRoot());
        setAnchorProperties(taskListSplitPanel);
    }

    /**
     * Executes the command and returns the result.
     *
     * @see seedu.address.logic.Logic#execute(String, boolean)
     */
    private CommandResult executeCommand(String commandText, boolean isInternal)
            throws CommandException, ParseException {
        try {
            CommandResult commandResult = logic.execute(commandText, isInternal);
            logger.info("Result: " + commandResult.getFeedbackToUser());
            resultDisplay.setFeedbackToUser(commandResult.getFeedbackToUser());

            if (commandResult.isShowHelp()) {
                handleHelp();
            }
            if (commandResult.isExit()) {
                handleExit();
            }
            if (commandResult.isDisplayAllTaskList()) {
                handleDisplayAllTaskList();
            }
            if (commandResult.isDisplaySingleTaskList()) {
                handleDisplaySingleTaskList();
            }
            if (commandResult.isWriteCommand()) {
                allTaskListPanel.updateTreeView(logic.getObservablePersonList());
            }
            if (commandResult.isChangeCommandBox()) {
                commandBox.setText(commandResult.getAdditionalText());
            }

            return commandResult;
        } catch (CommandException | ParseException e) {
            logger.info("Invalid command: " + commandText);
            resultDisplay.setFeedbackToUser(e.getMessage());
            throw e;
        }
    }
}
