package seedu.notor.ui;

import java.util.logging.Logger;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.MenuItem;
import javafx.scene.control.TextInputControl;
import javafx.scene.input.KeyCombination;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;
import seedu.notor.commons.core.GuiSettings;
import seedu.notor.commons.core.LogsCenter;
import seedu.notor.logic.Logic;
import seedu.notor.logic.commands.CommandResult;
import seedu.notor.logic.commands.exceptions.CommandException;
import seedu.notor.logic.executors.exceptions.ExecuteException;
import seedu.notor.logic.parser.exceptions.ParseException;
import seedu.notor.model.Notable;
import seedu.notor.model.Notor;
import seedu.notor.model.group.Group;
import seedu.notor.model.person.Person;
import seedu.notor.ui.listpanel.GroupListPanel;
import seedu.notor.ui.listpanel.ListPanel;
import seedu.notor.ui.listpanel.PersonListPanel;
import seedu.notor.ui.listpanel.SubgroupListPanel;
import seedu.notor.ui.note.GeneralNoteWindow;
import seedu.notor.ui.note.GroupNoteWindow;
import seedu.notor.ui.note.NoteWindow;
import seedu.notor.ui.note.PersonNoteWindow;

/**
 * The Main Window. Provides the basic application layout containing
 * a menu bar and space where other JavaFX elements can be placed.
 */
public class MainWindow extends UiPart<Stage> {

    private static final String FXML = "MainWindow.fxml";
    private static final String UNSAVED_NOTE_MESSAGE = "You have unsaved notes, "
            + "do you want to exit Notor without saving?";

    private final Logger logger = LogsCenter.getLogger(getClass());

    private final Stage primaryStage;
    private final Logic logic;

    // Independent Ui parts residing in this Ui container
    private final HelpWindow helpWindow;
    private ResultDisplay resultDisplay;


    @FXML
    private StackPane commandBoxPlaceholder;

    @FXML
    private StackPane generalNotePlaceholder;

    @FXML
    private MenuItem helpMenuItem;

    @FXML
    private StackPane listPanelPlaceholder;

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
        getRoot().setOnCloseRequest(e -> {
            e.consume();
            handleExit();
        });
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
        ListPanel<Person> listPanel = new PersonListPanel(logic.getFilteredPersonList());
        listPanelPlaceholder.getChildren().add(listPanel.getRoot());

        resultDisplay = new ResultDisplay();
        resultDisplayPlaceholder.getChildren().add(resultDisplay.getRoot());

        StatusBarFooter statusBarFooter = new StatusBarFooter(logic.getNotorFilePath());
        statusbarPlaceholder.getChildren().add(statusBarFooter.getRoot());

        CommandBox commandBox = new CommandBox(this::executeCommand);
        commandBoxPlaceholder.getChildren().add(commandBox.getRoot());

        GeneralNote viewPane = new GeneralNote(logic.getNotor().getNote());
        generalNotePlaceholder.getChildren().add(viewPane.getRoot());
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
     * Opens NoteWindow if it is currently not opened. Focuses NoteWindow if otherwise.
     * @param noteWindow Note Window.
     */
    private void manageNoteWindow(NoteWindow noteWindow) {
        if (!NoteWindow.OPENED_NOTE_WINDOWS.contains(noteWindow)) {
            NoteWindow.OPENED_NOTE_WINDOWS.add(noteWindow);
            noteWindow.show();
        } else {
            int indexOfNoteWindow = NoteWindow.OPENED_NOTE_WINDOWS.indexOf(noteWindow);
            NoteWindow.OPENED_NOTE_WINDOWS.get(indexOfNoteWindow).focus();
        }
    }

    private void handleNote(Person person, Logic logic) {
        NoteWindow noteWindow = new PersonNoteWindow(person, logic, resultDisplay);
        manageNoteWindow(noteWindow);
    }

    private void handleNote(Group group, Logic logic) {
        NoteWindow noteWindow = new GroupNoteWindow(group, logic, resultDisplay, listPanelPlaceholder);
        manageNoteWindow(noteWindow);
    }

    private void handleNote(Notor notor, Logic logic) {
        NoteWindow noteWindow = new GeneralNoteWindow(notor, logic, resultDisplay, generalNotePlaceholder);
        manageNoteWindow(noteWindow);
    }

    /**
     * Handles opening of Person Note, Group Note or general Note.
     * @param notable The object that is notable.
     * @param logic The Logic of Notor.
     */
    public void handleNote(Notable notable, Logic logic) throws CommandException {
        if (notable instanceof Person) {
            handleNote((Person) notable, logic);
        } else if (notable instanceof Group) {
            handleNote((Group) notable, logic);
        } else {
            handleNote((Notor) notable, logic);
        }
    }

    void show() {
        primaryStage.show();
    }

    /**
     * Closes the application. Shows a warning window for user to confirm whether to exit without saving note if there
     * is unsaved note.
     */
    @FXML
    private void handleExit() {
        GuiSettings guiSettings = new GuiSettings(primaryStage.getWidth(), primaryStage.getHeight(),
                (int) primaryStage.getX(), (int) primaryStage.getY());
        logic.setGuiSettings(guiSettings);
        boolean isAllNotesSaved = checkIfAllNotesSaved();

        if (!isAllNotesSaved) {
            WarningWindow warningWindow = new WarningWindow(UNSAVED_NOTE_MESSAGE);
            warningWindow.show();
            if (!warningWindow.canContinue()) {
                return;
            }
        }
        NoteWindow.OPENED_NOTE_WINDOWS.forEach(NoteWindow::hide);
        helpWindow.hide();
        primaryStage.hide();
    }

    private boolean checkIfAllNotesSaved() {
        for (NoteWindow noteWindow : NoteWindow.OPENED_NOTE_WINDOWS) {
            if (!noteWindow.isSave()) {
                return false;
            }
        }
        return true;
    }

    public PersonListPanel getPersonListPanel() {
        PersonListPanel listPanel = new PersonListPanel(logic.getFilteredPersonList());
        listPanelPlaceholder.getChildren().add(listPanel.getRoot());
        return listPanel;
    }

    /**
     * Executes the command and returns the result.
     *
     * @see seedu.notor.logic.Logic#execute(String)
     */
    private CommandResult executeCommand(String commandText) throws CommandException, ParseException, ExecuteException {
        try {
            CommandResult commandResult = logic.execute(commandText);
            logger.info("Result: " + commandResult.getFeedbackToUser());
            resultDisplay.setFeedbackToUser(commandResult.getFeedbackToUser());
            if (logic.isPersonList()) {
                PersonListPanel listPanel = new PersonListPanel(logic.getFilteredPersonList());
                listPanelPlaceholder.getChildren().add(listPanel.getRoot());
            } else if (logic.isSuperGroupList()) {
                GroupListPanel listPanel = new GroupListPanel(logic.getFilteredSuperGroupList());
                listPanelPlaceholder.getChildren().add(listPanel.getRoot());
            } else {
                SubgroupListPanel listPanel = new SubgroupListPanel(logic.getFilteredSubGroupList());
                listPanelPlaceholder.getChildren().add(listPanel.getRoot());
            }
            if (commandResult.isShowHelp()) {
                handleHelp();
            }
            if (commandResult.isShowNote()) {
                handleNote(commandResult.getNotable(), logic);
            }
            if (commandResult.isExit()) {
                handleExit();
            }

            return commandResult;
        } catch (CommandException | ParseException | ExecuteException e) {
            logger.info("Invalid command: " + commandText);
            resultDisplay.setFeedbackToUser(e.getMessage());
            throw e;
        }
    }
}
