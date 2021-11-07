package tutoraid.ui;

import static tutoraid.ui.DetailLevel.HIGH;
import static tutoraid.ui.DetailLevel.LOW;
import static tutoraid.ui.DetailLevel.MED;

import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;
import java.util.logging.Logger;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.MenuItem;
import javafx.scene.control.TextInputControl;
import javafx.scene.input.KeyCombination;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;
import tutoraid.commons.core.GuiSettings;
import tutoraid.commons.core.LogsCenter;
import tutoraid.commons.core.Messages;
import tutoraid.logic.Logic;
import tutoraid.logic.commands.CommandResult;
import tutoraid.logic.commands.exceptions.CommandException;
import tutoraid.logic.parser.exceptions.ParseException;

/**
 * The Main Window. Provides the basic application layout containing
 * a menu bar and space where other JavaFX elements can be placed.
 */
public class MainWindow extends UiPart<Stage> {

    private static final String FXML = "MainWindow.fxml";
    private static StudentListPanel fullStudentPanel = null;
    private static StudentListPanel mediumStudentPanel = null;
    private static StudentListPanel minimalStudentPanel = null;
    private static LessonListPanel fullLessonPanel = null;
    private static LessonListPanel minimalLessonPanel = null;


    private final Logger logger = LogsCenter.getLogger(getClass());
    private final Queue<String> messageQueue = new LinkedList<>();

    private Stage primaryStage;
    private Logic logic;

    // Independent Ui parts residing in this Ui container
    private StudentListPanel studentListPanel;
    private LessonListPanel lessonListPanel;
    private ResultDisplay resultDisplay;
    private HelpWindow helpWindow;

    @FXML
    private StackPane commandBoxPlaceholder;

    @FXML
    private MenuItem helpMenuItem;

    @FXML
    private StackPane studentListPanelPlaceholder;

    @FXML
    private StackPane lessonListPanelPlaceholder;

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

        fullStudentPanel = new StudentListPanel(logic.getFilteredStudentList(), HIGH);
        mediumStudentPanel = new StudentListPanel(logic.getFilteredStudentList(), MED);
        minimalStudentPanel = new StudentListPanel(logic.getFilteredStudentList(), LOW);
        fullLessonPanel = new LessonListPanel(logic.getFilteredLessonList(), HIGH);
        minimalLessonPanel = new LessonListPanel(logic.getFilteredLessonList(), LOW);

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
     * Fills up all the student's particulars of this window.
     */
    void fillStudentCard(DetailLevel detailLevel) {
        studentListPanel = detailLevel == HIGH
                ? fullStudentPanel
                : detailLevel == MED
                ? mediumStudentPanel
                : minimalStudentPanel;
        studentListPanelPlaceholder.getChildren().clear();
        studentListPanelPlaceholder.getChildren().add(studentListPanel.getRoot());
    }

    /**
     * Fills up all the lesson's particulars of this window.
     */
    void fillLessonCard(DetailLevel detailLevel) {
        lessonListPanel = detailLevel == LOW
                ? minimalLessonPanel
                : fullLessonPanel;
        lessonListPanelPlaceholder.getChildren().clear();
        lessonListPanelPlaceholder.getChildren().add(lessonListPanel.getRoot());
    }

    /**
     * Fills up all the placeholders of this window.
     */
    void fillInnerParts() {
        fillStudentCard(MED); // display more detail on launch
        fillLessonCard(MED);
        resultDisplay = new ResultDisplay();
        resultDisplayPlaceholder.getChildren().add(resultDisplay.getRoot());
        resultDisplay.setFeedbackToUser(getMessageFromQueue());


        StatusBarFooter statusBarFooter = new StatusBarFooter(logic.getStudentBookFilePath());
        statusbarPlaceholder.getChildren().add(statusBarFooter.getRoot());

        CommandBox commandBox = new CommandBox(this::executeCommand);
        commandBoxPlaceholder.getChildren().add(commandBox.getRoot());
    }

    private String getMessageFromQueue() {
        StringBuilder output = new StringBuilder();
        while (!messageQueue.isEmpty()) {
            output.append(messageQueue.poll());
            output.append("\n");
        }
        return output.toString();
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

    public void show() {
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

    public StudentListPanel getStudentListPanel() {
        return studentListPanel;
    }

    public LessonListPanel getLessonListPanel() {
        return lessonListPanel;
    }

    public void printMessage(String message) {
        if (resultDisplay != null) {
            resultDisplay.setFeedbackToUser(message);
        } else {
            messageQueue.add(message);
        }
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
