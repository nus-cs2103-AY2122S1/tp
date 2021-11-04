package safeforhall.ui;

import java.util.logging.Logger;

import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.Tab;
import javafx.scene.control.TabPane;
import javafx.scene.control.TextInputControl;
import javafx.scene.control.Tooltip;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyCombination;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;
import safeforhall.commons.core.GuiSettings;
import safeforhall.commons.core.LogsCenter;
import safeforhall.logic.Logic;
import safeforhall.logic.commands.CommandResult;
import safeforhall.logic.commands.exceptions.CommandException;
import safeforhall.logic.parser.exceptions.ParseException;

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
    private EventListPanel eventListPanel;
    private EventAdditionalListPanel eventAdditionalListPanel;
    private PersonListPanel personListPanel;
    private PersonAdditionalListPanel personAdditionalListPanel;
    private ResultDisplay resultDisplay;
    private HelpWindow helpWindow;
    private CommandBox commandBox;

    @FXML
    private StackPane commandBoxPlaceholder;

    @FXML
    private GridPane splitpane1;

    @FXML
    private Button helpMenuItem;

    @FXML
    private StackPane personListPanelPlaceholder;

    @FXML
    private StackPane personAdditionalListPanelPlaceholder;

    @FXML
    private StackPane eventListPanelPlaceholder;

    @FXML
    private StackPane eventAdditionalListPanelPlaceholder;

    @FXML
    private StackPane resultDisplayPlaceholder;

    @FXML
    private StackPane statusbarPlaceholder;

    @FXML
    private TabPane tabPane;

    @FXML
    private Tab residentsTab;

    @FXML
    private Tab eventsTab;

    @FXML
    private Tab helpTab;

    @FXML
    private Tab exitTab;

    @FXML
    private AnchorPane anchor;

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

        handleTabCreation();
    }

    private void handleTabCreation() {
        createResidentsTab();
        createEventsTab();
        createHelpTab();
        createExitTab();

        tabPane.getSelectionModel().selectedItemProperty().addListener((observable, oldTab, newTab) -> {
            boolean success = !newTab.equals(helpTab);
            if (!success) {
                Platform.runLater(() -> tabPane.getSelectionModel().select(oldTab));
            }
        });

        tabPane.setRotateGraphic(false);
    }

    private void createResidentsTab() {
        residentsTab.setGraphic(createTab("resident"));
        residentsTab.setTooltip(new Tooltip("Residents"));
    }

    private void createEventsTab() {
        eventsTab.setGraphic(createTab("event"));
        eventsTab.setTooltip(new Tooltip("Events"));
    }

    private void createHelpTab() {
        helpTab.setGraphic(createTab("help"));
        helpTab.setTooltip(new Tooltip("Help"));
        helpTab.setOnSelectionChanged(e -> {
            if (helpTab.isSelected()) {
                handleHelp();
            }
            e.consume();
        });
    }

    private void createExitTab() {
        exitTab.setGraphic(createTab("exit"));
        exitTab.setTooltip(new Tooltip("Exit"));
        exitTab.setOnSelectionChanged(e -> {
            if (exitTab.isSelected()) {
                handleExit();
            }
        });
    }

    private HBox createTab(String imageName) {
        HBox content = new HBox();
        ImageView icon = new ImageView("/images/" + imageName + ".png");
        icon.setFitHeight(38);
        icon.setFitWidth(38);
        icon.setPreserveRatio(true);
        content.getChildren().addAll(new Label("    "), icon, new Label("     "));
        return content;
    }

    public Stage getPrimaryStage() {
        return primaryStage;
    }

    public boolean getIsResidentTab() {
        // Dependent on the ordering of tabs in `MainWindow.fxml`
        // Resident is at index 0 and Event at index 1
        return tabPane.getSelectionModel().getSelectedIndex() == 0;
    }

    private void setAccelerators() {
        setAccelerator(helpMenuItem, KeyCombination.valueOf("F1"));
    }

    /**
     * Sets the accelerator of a MenuItem.
     * @param keyCombination the KeyCombination value of the accelerator
     */
    private void setAccelerator(Button menuItem, KeyCombination keyCombination) {
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
        personListPanel = new PersonListPanel(logic.getFilteredPersonList(), logic);
        personListPanelPlaceholder.getChildren().add(personListPanel.getRoot());

        personAdditionalListPanel = new PersonAdditionalListPanel(logic.getSinglePerson(), logic);
        personAdditionalListPanelPlaceholder.getChildren().add(personAdditionalListPanel.getRoot());

        eventListPanel = new EventListPanel(logic.getFilteredEventList(), logic);
        eventListPanelPlaceholder.getChildren().add(eventListPanel.getRoot());

        eventAdditionalListPanel = new EventAdditionalListPanel(logic.getSingleEvent());
        eventAdditionalListPanelPlaceholder.getChildren().add(eventAdditionalListPanel.getRoot());

        resultDisplay = new ResultDisplay();
        resultDisplayPlaceholder.getChildren().add(resultDisplay.getRoot());

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
    }

    @FXML
    private void handleSwitch() {
        int index = tabPane.getSelectionModel().getSelectedIndex() ^ 1;
        tabPane.getSelectionModel().select(index);
    }

    /**
     * Set isResidentTab of CommandBox to True.
     */
    @FXML
    private void commandBoxToResidents() {
        if (commandBox != null) {
            this.commandBox.setIsResidentTab(true);
            this.commandBox.refreshSuggestions();
        }
        if (personListPanel != null) {
            this.personListPanel.setIsResidentTab(true);
        }
        if (eventListPanel != null) {
            this.eventListPanel.setIsResidentTab(true);
        }
    }

    /**
     * Set isResidentTab of CommandBox to False.
     */
    @FXML
    private void commandBoxToEvents() {
        if (commandBox != null) {
            this.commandBox.setIsResidentTab(false);
            this.commandBox.refreshSuggestions();
        }
        if (personListPanel != null) {
            this.personListPanel.setIsResidentTab(false);
        }
        if (eventListPanel != null) {
            this.eventListPanel.setIsResidentTab(false);
        }
    }

    /**
     * Executes the command and returns the result.
     *
     * @see Logic#execute(String, Boolean)
     */
    private CommandResult executeCommand(String commandText) throws CommandException, ParseException {
        try {
            CommandResult commandResult = logic.execute(commandText, getIsResidentTab());
            logger.info("Result: " + commandResult.getFeedbackToUser());
            resultDisplay.setFeedbackToUser(commandResult.getFeedbackToUser());

            if (commandResult.isShowHelp()) {
                handleHelp();
            }

            if (commandResult.isExit()) {
                handleExit();
            }

            if (commandResult.isSwitchTab()) {
                handleSwitch();
            }

            return commandResult;
        } catch (CommandException | ParseException e) {
            logger.info("Invalid command: " + commandText);
            resultDisplay.setFeedbackToUser(e.getMessage());
            throw e;
        }
    }
}
