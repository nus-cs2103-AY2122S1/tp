package seedu.address.ui;


import java.util.logging.Logger;

import javafx.collections.ObservableList;
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
import seedu.address.model.student.Student;
import seedu.address.model.tuition.TuitionClass;
import seedu.address.model.tuition.UniqueTuitionList;
import seedu.address.ui.infopage.InfoPage;
import seedu.address.ui.infopage.StudentInfoPage;
import seedu.address.ui.infopage.TimetableInfoPage;
import seedu.address.ui.infopage.TodayTuitionClassInfoPage;
import seedu.address.ui.infopage.TuitionClassInfoPage;

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
    private StudentListPanel studentListPanel;
    private TuitionListPanel tuitionListPanel;
    private ResultDisplay resultDisplay;
    private HelpWindow helpWindow;

    @FXML
    private StackPane commandBoxPlaceholder;

    @FXML
    private MenuItem helpMenuItem;

    @FXML
    private StackPane studentListPanelPlaceholder;

    @FXML
    private StackPane tuitionListPanelPlaceholder;

    @FXML
    private StackPane resultDisplayPlaceholder;

    @FXML
    private StackPane statusbarPlaceholder;

    @FXML
    private StackPane infoPagePlaceholder;

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
        studentListPanel = new StudentListPanel(logic.getFilteredStudentList());
        studentListPanelPlaceholder.getChildren().add(studentListPanel.getRoot());

        tuitionListPanel = new TuitionListPanel(logic.getFilteredTuitionList());
        tuitionListPanelPlaceholder.getChildren().add(tuitionListPanel.getRoot());

        resultDisplay = new ResultDisplay();
        resultDisplayPlaceholder.getChildren().add(resultDisplay.getRoot());

        StatusBarFooter statusBarFooter = new StatusBarFooter(logic.getAddressBookFilePath());
        statusbarPlaceholder.getChildren().add(statusBarFooter.getRoot());

        CommandBox commandBox = new CommandBox(this::executeCommand);
        commandBoxPlaceholder.getChildren().add(commandBox.getRoot());

        reminderDisplay();
    }


    /**
     * Display a reminder to tutor about today tuition classes
     */
    public void reminderDisplay() {
        ObservableList<TuitionClass> tuitionClasses = logic.getTodayTuitionList();
        TodayTuitionClassInfoPage todayTuitionClassInfoPage = new TodayTuitionClassInfoPage(tuitionClasses);
        updateInfoPage(todayTuitionClassInfoPage);
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

    public StudentListPanel getStudentListPanel() {
        return studentListPanel;
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

            executeUiAction(commandResult.getUiAction());

            return commandResult;
        } catch (CommandException | ParseException e) {
            logger.info("Invalid command: " + commandText);
            resultDisplay.setFeedbackToUser(e.getMessage());
            throw e;
        }
    }

    private void executeUiAction(CommandResult.UiAction action) {
        switch (action) {
        case EXIT:
            handleExit();
            break;
        case SHOW_HELP:
            handleHelp();
            break;
        case SHOW_TUITION_PAGE:
            showTuitionPage();
            break;
        case SHOW_TIMETABLE:
            showTimetable();
            break;
        case SHOW_STUDENT_PAGE:
            showStudentPage();
            break;
        case SHOW_TODAY_TUITIONS_PAGE:
            reminderDisplay();
            break;
        case SET_TUITIONS_DEFAULT:
            updateTuitionListTitle(false);
            break;
        case SET_STUDENTS_DEFAULT:
            updateStudentListTitle(false);
            break;
        case SET_TUITIONS_FILTERED:
            updateTuitionListTitle(true);
            break;
        case SET_STUDENTS_FILTERED:
            updateStudentListTitle(true);
            break;
        case NONE:
            updateInfoPage(null);
            break;
        default:
            break;
        }
    }

    private void showTimetable() {
        updateInfoPage(new TimetableInfoPage(UniqueTuitionList.getMostRecentTuitionClasses(), resultDisplay));
    }

    private void showStudentPage() {
        updateInfoPage(new StudentInfoPage(Student.getMostRecent()));
    }

    private void showTuitionPage() {
        updateInfoPage(new TuitionClassInfoPage(TuitionClass.getMostRecent()));
    }

    /**
     * Updates the Info Page section of the UI with a given info card.
     * @param infoPage InfoPage to be placed in the Info Page section.
     */
    private void updateInfoPage(InfoPage infoPage) {
        infoPagePlaceholder.getChildren().clear();
        if (infoPage != null) {
            infoPagePlaceholder.getChildren().add(infoPage.getRoot());
        }
    }

    private void updateStudentListTitle(boolean bool) {
        studentListPanel.setFiltered(bool);
    }

    private void updateTuitionListTitle(boolean bool) {
        tuitionListPanel.setFiltered(bool);
    }

}
