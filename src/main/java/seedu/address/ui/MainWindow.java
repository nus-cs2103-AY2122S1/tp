package seedu.address.ui;

import java.io.IOException;
import java.util.Iterator;
import java.util.List;
import java.util.logging.Logger;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.geometry.Rectangle2D;
import javafx.scene.control.Label;
import javafx.scene.control.MenuItem;
import javafx.scene.control.SplitPane;
import javafx.scene.control.Tab;
import javafx.scene.control.TabPane;
import javafx.scene.control.TextInputControl;
import javafx.scene.input.KeyCombination;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.stage.Screen;
import javafx.stage.Stage;
import net.fortuna.ical4j.data.ParserException;
import seedu.address.commons.core.GuiSettings;
import seedu.address.commons.core.LogsCenter;
import seedu.address.logic.Logic;
import seedu.address.logic.commands.CommandResult;
import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.logic.parser.exceptions.ParseException;

/**
 * The Main Window. Provides the basic application layout containing a menu bar
 * and space where other JavaFX elements can be placed.
 */
public class MainWindow extends UiPart<Stage> {

    private static final String FXML = "MainWindow.fxml";

    private final Logger logger = LogsCenter.getLogger(getClass());

    private Stage primaryStage;
    private Logic logic;

    // Independent Ui parts residing in this Ui container
    private AnalyticsPanel analyticsSection;
    private CustomGoalListPanel customGoalSection;
    private ScheduleListPanel dashboardScheduleSection;
    private TodoListPanel dashboardTodoSection;
    private PersonListPanel personListPanel;
    private PersonDetailedPanel personDetailedPanel;
    private PolicyListPanel policyListPanel;
    private NotesPanel notesPanel;
    private TodoListPanel todoListPanel;
    private ScheduleListPanel scheduleListPanel;
    private ResultDisplay resultDisplay;
    private HelpWindow helpWindow;
    private ImportWindow importWindow;

    @FXML
    private TabPane tabs;

    @FXML
    private Tab dashboardTab;

    @FXML
    private Tab contactsTab;

    @FXML
    private Tab scheduleTab;

    @FXML
    private Tab todosTab;

    @FXML
    private SplitPane topPane;

    @FXML
    private StackPane commandBoxPlaceholder;

    @FXML
    private MenuItem helpMenuItem;

    @FXML
    private VBox dashboardAnalyticsPlaceholder;

    @FXML
    private Label analyticsHeader;

    @FXML
    private VBox dashboardCustomGoalsPlaceholder;

    @FXML
    private VBox dashboardSchedulePlaceholder;

    @FXML
    private VBox dashboardTodosPlaceholder;

    @FXML
    private StackPane personListPanelPlaceholder;

    @FXML
    private VBox personDetailedPlaceholder;

    @FXML
    private VBox policyListPanelPlaceholder;

    @FXML
    private VBox notesPlaceholder;

    @FXML
    private StackPane scheduleListPanelPlaceholder;

    @FXML
    private StackPane todoListPanelPlaceholder;

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

        importWindow = new ImportWindow();
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
         * https://bugs.openjdk.java.net/browse/JDK-8131666 is fixed in later version of
         * SDK.
         *
         * According to the bug report, TextInputControl (TextField, TextArea) will
         * consume function-key events. Because CommandBox contains a TextField, and
         * ResultDisplay contains a TextArea, thus some accelerators (e.g F1) will not
         * work when the focus is in them because the key event is consumed by the
         * TextInputControl(s).
         *
         * For now, we add following event filter to capture such key events and open
         * help window purposely so to support accelerators even when focus is in
         * CommandBox or ResultDisplay.
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

        analyticsSection = new AnalyticsPanel(logic.getAnalytics());
        analyticsHeader.setText(AnalyticsPanel.getHeader());
        dashboardAnalyticsPlaceholder.getChildren().add(analyticsSection.getRoot());

        customGoalSection = new CustomGoalListPanel(logic.getFilteredCustomGoalList());
        dashboardCustomGoalsPlaceholder.getChildren().add(customGoalSection.getRoot());

        personListPanel = new PersonListPanel(logic.getFilteredPersonList());
        personListPanelPlaceholder.getChildren().add(personListPanel.getRoot());

        personDetailedPanel = new PersonDetailedPanel(logic.getSelectedPersonList(), logic.getSelectedPersonIndex());
        personDetailedPlaceholder.getChildren().add(personDetailedPanel.getRoot());

        policyListPanel = new PolicyListPanel(logic.getSelectedPersonList());
        policyListPanelPlaceholder.getChildren().add(policyListPanel.getRoot());

        notesPanel = new NotesPanel(logic.getSelectedPersonList());
        notesPlaceholder.getChildren().add(notesPanel.getRoot());

        todoListPanel = new TodoListPanel(logic.getFilteredTodoList());
        todoListPanelPlaceholder.getChildren().add(todoListPanel.getRoot());

        scheduleListPanel = new ScheduleListPanel(logic.getFilteredScheduleList());
        scheduleListPanelPlaceholder.getChildren().add(scheduleListPanel.getRoot());

        dashboardScheduleSection = new ScheduleListPanel(logic.getFilteredScheduleList());
        dashboardTodoSection = new TodoListPanel(logic.getFilteredTodoList());
        dashboardSchedulePlaceholder.getChildren().add(dashboardScheduleSection.getRoot());
        dashboardTodosPlaceholder.getChildren().add(dashboardTodoSection.getRoot());

        resultDisplay = new ResultDisplay();
        resultDisplayPlaceholder.getChildren().add(resultDisplay.getRoot());

        StatusBarFooter statusBarFooter = new StatusBarFooter(logic.getAddressBookFilePath());
        statusbarPlaceholder.getChildren().add(statusBarFooter.getRoot());

        CommandBox commandBox = new CommandBox(this::executeCommand);
        commandBoxPlaceholder.getChildren().add(commandBox.getRoot());

        topPane.setDividerPosition(0, 0.2);
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

    /**
     * Opens the OS's default file browser and allows user to select an ics file of their choice.
     */
    @FXML
    public void handleImport() {
        try {
            importWindow.show();
            logic.importSchedule(importWindow.getIcsFile());
            resultDisplay.setFeedbackToUser("Successfully imported schedule!");
        } catch (IOException e) {
            resultDisplay.setFeedbackToUser(e.getMessage());
        } catch (ParserException | ParseException | java.text.ParseException e) {
            resultDisplay.setFeedbackToUser(e.getMessage());
        } catch (CommandException e) {
            resultDisplay.setFeedbackToUser(e.getMessage());
        }
    }

    // @@author spdpnd98-reused
    // the built in method setMaximized from javafx does not work on MacOS, reused solution from stack overflow:
    // https://stackoverflow.com/questions/30049503/javafx-setmaximized-on-osx/30052801
    void show() {
        Screen screen = Screen.getPrimary();
        Rectangle2D visualBounds = screen.getVisualBounds();
        double width = visualBounds.getWidth();
        double height = visualBounds.getHeight();
        primaryStage.setWidth(width);
        primaryStage.setHeight(height);
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


    @FXML
    private void handleSwitchTab(seedu.address.commons.core.Tab tabId) {
        tabs.getSelectionModel().select(tabId.getIndex());
    }

    public TodoListPanel getTodoListPanel() {
        return todoListPanel;
    }

    public ScheduleListPanel getScheduleListPanel() {
        return scheduleListPanel;
    }

    /**
     * Executes the command and returns the result.
     *
     * @see seedu.address.logic.Logic#execute(String)
     */
    private CommandResult executeCommand(String commandText)
            throws CommandException, ParseException {
        try {
            String currentTab = this.getSelectedPane();
            List<CommandResult> commandResults = logic.execute(currentTab + " " + commandText);
            CommandResult commandResult = commandResults.get(0); // we can assume there will always be at least 1
            for (Iterator<CommandResult> i = commandResults.iterator(); i.hasNext();) {
                commandResult = i.next();
                logger.info("Result: " + commandResult.getFeedbackToUser());
                resultDisplay.setFeedbackToUser(commandResult.getFeedbackToUser());

                if (commandResult.isShowHelp()) {
                    handleHelp();
                }

                if (commandResult.isExit()) {
                    handleExit();
                }

                if (commandResult.isSwitchTab()) {
                    handleSwitchTab(commandResult.getTabId());
                }
            }

            if (commandResult.isShowImport()) {
                handleImport();
            }

            return commandResult;
        } catch (CommandException | ParseException e) {
            logger.info("Invalid command: " + commandText);
            resultDisplay.setFeedbackToUser(e.getMessage());
            throw e;
        }
    }


    public String getSelectedPane() {
        return tabs.getSelectionModel().getSelectedItem().getId();
    }
}
