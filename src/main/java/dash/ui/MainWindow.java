package dash.ui;

import java.util.logging.Logger;

import dash.commons.core.GuiSettings;
import dash.commons.core.LogsCenter;
import dash.logic.Logic;
import dash.logic.commands.CommandResult;
import dash.logic.commands.exceptions.CommandException;
import dash.logic.parser.exceptions.ParseException;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.MenuItem;
import javafx.scene.control.TextInputControl;
import javafx.scene.input.KeyCombination;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;

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
    private PersonListPanelMinimal personListPanelMinimal;
    private TaskListPanel taskListPanel;
    private HelpListPanel helpListPanelGeneral;
    private HelpListPanel helpListPanelContact;
    private HelpListPanel helpListPanelTask;
    private HelpPanel helpPanel;
    private ResultDisplay resultDisplay;
    private HelpWindow helpWindow;
    private TabMenu tabMenu;
    private StatusBarFooter statusBarFooter;

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
    private StackPane tabMenuPlaceholder;

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
     *
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
        tabMenu = new TabMenu();
        tabMenuPlaceholder.getChildren().add(tabMenu.getRoot());

        resultDisplay = new ResultDisplay();
        resultDisplayPlaceholder.getChildren().add(resultDisplay.getRoot());

        personListPanel = new PersonListPanel(logic.getFilteredPersonList());
        tabMenu.getContactsGridPane().add(personListPanel.getRoot(), 0, 1);

        taskListPanel = new TaskListPanel(logic.getFilteredTaskList());
        tabMenu.getTasksGridPane().add(taskListPanel.getRoot(), 0, 1);
        personListPanelMinimal = new PersonListPanelMinimal(logic.getFilteredPersonList());
        tabMenu.getTasksGridPane().add(personListPanelMinimal.getRoot(), 1, 1);

        helpPanel = new HelpPanel(resultDisplay);
        helpListPanelGeneral = new HelpListPanel(helpPanel.initialiseHelpListGeneral());
        helpListPanelContact = new HelpListPanel(helpPanel.initialiseHelpListContact());
        helpListPanelTask = new HelpListPanel(helpPanel.initialiseHelpListTask());
        helpPanel.getCommandContainer().add(helpListPanelGeneral.getRoot(), 0, 2);
        helpPanel.getCommandContainer().add(helpListPanelContact.getRoot(), 0, 5);
        helpPanel.getCommandContainer().add(helpListPanelTask.getRoot(), 0, 8);
        tabMenu.getHelpGridPane().add(helpPanel.getRoot(), 0, 1);

        CommandBox commandBox = new CommandBox(this::executeCommand, logic.getInternalUserInputList());
        commandBoxPlaceholder.getChildren().add(commandBox.getRoot());

        statusBarFooter = new StatusBarFooter(logic.getAddressBookFilePath());
        statusbarPlaceholder.getChildren().add(statusBarFooter.getRoot());

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
     * Switch to Help Tab
     */
    public void handleHelp() {
        tabMenu.switchTab(2);
        logic.setTabNumber(2);
        statusBarFooter.hideFilePath();
    }

    /**
     * Switches to Contacts Tab
     */
    public void handleSwitchContactsTab() {
        tabMenu.switchTab(0);
        logic.setTabNumber(0);
        statusBarFooter.setFilePath(logic.getAddressBookFilePath());
    }

    /**
     * Switches to Tasks Tab
     */
    public void handleSwitchTasksTab() {
        tabMenu.switchTab(1);
        logic.setTabNumber(1);
        statusBarFooter.setFilePath(logic.getTaskListFilePath());
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

    public PersonListPanel getPersonListPanel() {
        return personListPanel;
    }

    /**
     * Executes the command and returns the result.
     *
     * @see Logic#execute(String)
     */
    private CommandResult executeCommand(String commandText) throws CommandException, ParseException {
        try {
            CommandResult commandResult = logic.execute(commandText);
            logger.info("Result: " + commandResult.getFeedbackToUser());
            resultDisplay.setFeedbackToUser(commandResult.getFeedbackToUser());

            if (commandResult.isShowHelp()) {
                handleHelp();
            }

            if (commandResult.isSwitchContactsTab()) {
                handleSwitchContactsTab();
            }

            if (commandResult.isSwitchTasksTab()) {
                handleSwitchTasksTab();
            }

            if (commandResult.isExit()) {
                handleExit();
            }

            return commandResult;
        } catch (CommandException | ParseException e) {
            logger.info("Invalid command: " + commandText);
            resultDisplay.setFeedbackToUser(e.getMessage());
            throw e;
        }
    }
}
