package seedu.times.ui;

import static java.util.Objects.requireNonNull;

import java.util.List;
import java.util.logging.Logger;

import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.MenuItem;
import javafx.scene.control.Tab;
import javafx.scene.control.TabPane;
import javafx.scene.control.TextInputControl;
import javafx.scene.input.KeyCombination;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;
import seedu.times.commons.core.GuiSettings;
import seedu.times.commons.core.LogsCenter;
import seedu.times.logic.CommandObserver;
import seedu.times.logic.Logic;
import seedu.times.logic.commands.CommandResult;
import seedu.times.logic.commands.exceptions.CommandException;
import seedu.times.logic.parser.exceptions.ParseException;
import seedu.times.model.person.Student;
import seedu.times.model.tuitionclass.TuitionClass;
import seedu.times.ui.classtab.ClassPanel;
import seedu.times.ui.studenttab.StudentListPanel;
import seedu.times.ui.timetabletab.TimetablePanel;

/**
 * The Main Window. Provides the basic application layout containing
 * a menu bar and space where other JavaFX elements can be placed.
 */
public class MainWindow extends UiPart<Stage> implements CommandObserver {

    private static final String FXML = "MainWindow.fxml";

    private final Logger logger = LogsCenter.getLogger(getClass());

    private Stage primaryStage;
    private Logic logic;

    // Independent Ui parts residing in this Ui container
    private StudentListPanel studentListPanel;
    private ResultDisplay resultDisplay;
    private HelpWindow helpWindow;
    private ClassPanel classPanel;
    private TimetablePanel timetablePanel;

    @FXML
    private StackPane commandBoxPlaceholder;

    @FXML
    private MenuItem helpMenuItem;

    @FXML
    private StackPane studentListPanelPlaceholder;

    @FXML
    private StackPane resultDisplayPlaceholder;

    @FXML
    private StackPane statusbarPlaceholder;

    @FXML
    private StackPane classListPanelPlaceholder;

    @FXML
    private TabPane tabPane;

    @FXML
    private Tab studentsTab;

    @FXML
    private Tab tuitionClassTab;

    @FXML
    private StackPane timetablePanelPlaceholder;

    @FXML
    private Tab timetableTab;

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
        studentListPanel = new StudentListPanel(logic.getFilteredStudentList());
        studentListPanelPlaceholder.getChildren().add(studentListPanel.getRoot());

        classPanel = new ClassPanel(logic.getFilteredStudentList(), logic.getFilteredTuitionClassList());
        classListPanelPlaceholder.getChildren().add(classPanel.getRoot());
        hideTuitionClassStudentList();

        timetablePanel = new TimetablePanel(logic.getFilteredTuitionClassList());
        timetablePanelPlaceholder.getChildren().add(timetablePanel.getRoot());

        resultDisplay = new ResultDisplay();
        resultDisplayPlaceholder.getChildren().add(resultDisplay.getRoot());

        StatusBarFooter statusBarFooter = new StatusBarFooter(logic.getTimesTableFilePath());
        statusbarPlaceholder.getChildren().add(statusBarFooter.getRoot());

        CommandBox commandBox = new CommandBox(this::executeCommand);
        commandBoxPlaceholder.getChildren().add(commandBox.getRoot());

        studentsTab = new Tab("Students", studentListPanelPlaceholder);
        timetableTab = new Tab("Timetable", timetablePanelPlaceholder);
        tuitionClassTab = new Tab("Class", classListPanelPlaceholder);

        tabPane.setTabClosingPolicy(TabPane.TabClosingPolicy.UNAVAILABLE);
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

    @Override
    public void updateClass(Integer indexOfClassToSelect) {
        requireNonNull(indexOfClassToSelect);
        List<TuitionClass> lastShownList = logic.getFilteredTuitionClassList();

        ObservableList<Student> newStudentList;

        TuitionClass tuitionClass = lastShownList.get(indexOfClassToSelect);
        newStudentList = logic.getFilteredStudentList().filtered(student ->
                tuitionClass.containsStudent(student.getName()));
        classPanel.setItems(newStudentList);
    }

    @Override
    public void hideTuitionClassStudentList() {
        ObservableList<Student> newStudentList = logic.getFilteredStudentList().filtered(student -> false);

        classPanel.setItems(newStudentList);
    }

    @Override
    public void updateView(TabName tabToView) {
        requireNonNull(tabToView);
        tabPane.getSelectionModel().select(tabToView.getIndex());
        logger.info("Switched to " + tabToView + " because of updateView()");
    }



    /**
     * Executes the command and returns the result.
     *
     * @see seedu.times.logic.Logic#execute(String)
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
