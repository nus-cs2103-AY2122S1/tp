package seedu.address.ui;

import static seedu.address.model.Model.DisplayType.STUDENTS;

import java.util.logging.Logger;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.MenuItem;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextInputControl;
import javafx.scene.input.KeyCombination;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import seedu.address.commons.core.GuiSettings;
import seedu.address.commons.core.LogsCenter;
import seedu.address.logic.Logic;
import seedu.address.logic.commands.CommandResult;
import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.logic.parser.exceptions.ParseException;
import seedu.address.model.Model;

/**
 * The Main Window. Provides the basic application layout containing
 * a menu bar and space where other JavaFX elements can be placed.
 */
public class MainWindow extends UiPart<Stage> {

    private static final String FXML = "MainWindow.fxml";
    private static final String STUDENTS_LIST_NAME = "Students";
    private static final String TASKS_LIST_NAME = "Tasks";
    private static final String GROUPS_LIST_NAME = "Groups";

    private final Logger logger = LogsCenter.getLogger(getClass());

    private final Stage primaryStage;
    private final Logic logic;

    // Independent Ui parts residing in this Ui container
    private final StudentListPanel studentListPanel;
    private final TaskListPanel taskListPanel;
    private final GroupListPanel groupListPanel;
    private Model.DisplayType currentDisplay;
    private final HelpWindow helpWindow;

    @FXML
    private StackPane commandBoxPlaceholder;

    @FXML
    private MenuItem helpMenuItem;

    @FXML
    private StackPane listPanelPlaceholder;

    @FXML
    private ScrollPane terminalScrollPane;

    @FXML
    private VBox terminalContainer;

    @FXML
    private StackPane statusBarPlaceholder;

    @FXML
    private Label listName;

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
        studentListPanel = new StudentListPanel(logic.getFilteredStudentList());
        groupListPanel = new GroupListPanel(logic.getFilteredGroupList());
        taskListPanel = new TaskListPanel(logic.getFilteredTaskList());
        listPanelPlaceholder.getChildren().add(groupListPanel.getRegion());
        listPanelPlaceholder.getChildren().add(taskListPanel.getRegion());
        listPanelPlaceholder.getChildren().add(studentListPanel.getRegion());
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
        currentDisplay = STUDENTS;

        listName.setText(STUDENTS_LIST_NAME);

        StatusBarFooter statusBarFooter = new StatusBarFooter(logic.getAddressBookFilePath());
        statusBarPlaceholder.getChildren().add(statusBarFooter.getRoot());

        CommandBox commandBox = new CommandBox(this::executeCommand);
        commandBoxPlaceholder.getChildren().add(commandBox.getRoot());
    }

    /**
     * Update the data lists.
     */
    void updateInnerParts() {
        Model.DisplayType type = logic.getDisplayType();
        if (currentDisplay != type) {
            switch (type) {
                case STUDENTS:
                    listPanelPlaceholder.getChildren().remove(studentListPanel.getRegion());
                    listPanelPlaceholder.getChildren().add(studentListPanel.getRegion());
                    listName.setText(STUDENTS_LIST_NAME);
                    break;
                case GROUPS:
                    listPanelPlaceholder.getChildren().remove(groupListPanel.getRegion());
                    listPanelPlaceholder.getChildren().add(groupListPanel.getRegion());
                    listName.setText(GROUPS_LIST_NAME);
                    break;
                case TASKS:
                    listPanelPlaceholder.getChildren().remove(taskListPanel.getRegion());
                    listPanelPlaceholder.getChildren().add(taskListPanel.getRegion());
                    listName.setText(TASKS_LIST_NAME);
                    break;
            }
            currentDisplay = type;
        }
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

    @FXML
    public void initialize() {
        terminalScrollPane.vvalueProperty().bind(terminalContainer.heightProperty());
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

    /**
     * Executes the command and returns the result.
     *
     * @see seedu.address.logic.Logic#execute(String)
     */
    private CommandResult executeCommand(String commandText) throws CommandException, ParseException {
        try {
            CommandResult commandResult = logic.execute(commandText);
            logger.info("Result: " + commandResult.getFeedbackToUser());

            if (commandResult.isShowHelp()) {
                handleHelp();
            } else if (commandResult.isExit()) {
                handleExit();
            } else {
                updateInnerParts();
            }

            terminalContainer.getChildren().add(
                    new TerminalBox(commandText, commandResult.getFeedbackToUser()));
            return commandResult;
        } catch (CommandException | ParseException e) {
            logger.info("Invalid command: " + commandText);
            terminalContainer.getChildren().add(
                    new TerminalBox(commandText, e));
            throw e;
        }
    }
}
