package seedu.notor.ui.note;

import java.awt.Toolkit;
import java.util.ArrayList;
import java.util.logging.Logger;

import javafx.fxml.FXML;
import javafx.scene.control.TextArea;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyCodeCombination;
import javafx.scene.input.KeyCombination;
import javafx.scene.input.KeyCombination.Modifier;
import javafx.scene.input.KeyEvent;
import javafx.stage.Stage;
import seedu.notor.commons.core.LogsCenter;
import seedu.notor.commons.util.DateUtil;
import seedu.notor.logic.Logic;
import seedu.notor.logic.commands.exceptions.CommandException;
import seedu.notor.model.Notable;
import seedu.notor.model.person.Person;
import seedu.notor.ui.ConfirmationWindow;
import seedu.notor.ui.ResultDisplay;
import seedu.notor.ui.UiPart;


public abstract class NoteWindow extends UiPart<Stage> {

    public static final ArrayList<NoteWindow> OPENED_NOTE_WINDOWS = new ArrayList<>();

    private static final int WIDTH = 616;
    private static final int HEIGHT = 390;
    private static final int OFFSET = 10;
    private static final int SCREEN_X = Toolkit.getDefaultToolkit().getScreenSize().width;
    private static final int SCREEN_Y = Toolkit.getDefaultToolkit().getScreenSize().height;
    private static final int CENTER_X = (SCREEN_X - WIDTH) / 2;
    private static final int CENTER_Y = (SCREEN_Y - HEIGHT) / 2;
    private static final String FXML = "NoteWindow.fxml";
    private static final Modifier SHORTCUT = KeyCombination.SHORTCUT_DOWN;
    private static final KeyCombination SAVE_KEY = new KeyCodeCombination(KeyCode.S, SHORTCUT);
    private static final KeyCombination EXIT_AND_SAVE_KEY = new KeyCodeCombination(KeyCode.Q, SHORTCUT);
    private static final KeyCombination EXIT_KEY = new KeyCodeCombination(KeyCode.W, SHORTCUT);
    private static final KeyCombination TIME_STAMP_KEY = new KeyCodeCombination(KeyCode.T, SHORTCUT);

    @FXML
    protected TextArea noteTextArea;

    protected ConfirmationWindow confirmationWindow;

    protected final Logic logic;

    protected final ResultDisplay resultDisplay;

    protected final Logger logger = LogsCenter.getLogger(getClass());

    protected NoteWindow(Logic logic, ResultDisplay resultDisplay) {
        super(FXML);
        this.resultDisplay = resultDisplay;
        this.logic = logic;
        noteTextArea.setWrapText(true);
        getRoot().setOnCloseRequest(e -> {
            e.consume();
            handleExit();
        });
    }

    /**
     * Returns current DateTime in this string format: Last Modified: E, MMM dd yyyy HH:mm
     */
    public static String noteLastModified() {
        return "Last Modified: (" + DateUtil.getCurrentDateTime() + ")";
    }

    /**
     * Shows the noteWindow.
     */
    public void show() {
        getRoot().show();
        int newX = Math.min(CENTER_X + (OPENED_NOTE_WINDOWS.size() - 1) * OFFSET, SCREEN_X - WIDTH);
        int newY = Math.min(CENTER_Y + (OPENED_NOTE_WINDOWS.size() - 1) * OFFSET, SCREEN_Y - HEIGHT);
        getRoot().setX(newX);
        getRoot().setY(newY);
    }

    /**
     * Returns true if the note window is currently being shown.
     */
    public boolean isShowing() {
        return getRoot().isShowing();
    }

    /**
     * Hides the note window.
     */
    public void hide() {
        getRoot().hide();
    }

    /**
     * Focuses on the note window.
     */
    public void focus() {
        getRoot().setIconified(false);
        getRoot().requestFocus();
    }

    /**
     * Generates a command execution success message based on whether
     * the note is added.
     * {@code personToEdit}.
     */
    public abstract String generateSuccessMessage(String message);


    /**
     * Saves the file
     */
    @FXML
    public abstract void handleSave() throws CommandException;

    /**
     * Checks if current Note is saved.
     */
    public abstract boolean isSave();

    /**
     * Exits the note window if note is saved or user wants to exit without saving. Shows confirmation window if
     * note is not saved.
     */
    @FXML
    public void handleExit() {
        if (isSave()) {
            exit();
        } else {
            confirmationWindow.show();
        }
    }
    /**
     * Exits the note Window.
     */
    public abstract void exit();

    public abstract boolean belongsTo(Notable notable);

    public static boolean contains(Notable notable) {
        return OPENED_NOTE_WINDOWS.parallelStream().anyMatch(noteWindow -> noteWindow.belongsTo(notable));
    }

    /**
     * Exits and saves the note window.
     */
    @FXML
    public void handleSaveAndExit() throws CommandException {
        handleSave();
        handleExit();
    }

    private void timeStampNote() {
        int noOfCharsTillCursor = noteTextArea.getCaretPosition();
        String noteBeforeLine = noOfCharsTillCursor == 0 ? "" : noteTextArea.getText(0, noOfCharsTillCursor);
        String noteAfterLine = noteTextArea.getText(noOfCharsTillCursor, noteTextArea.getLength());
        String dateTime = DateUtil.getCurrentDateTime();

        noteTextArea.setText(noteBeforeLine + dateTime + noteAfterLine);
        noteTextArea.selectPositionCaret(noOfCharsTillCursor + dateTime.length());
        noteTextArea.deselect();
    }

    /**
     * Reads user key event. Saves on pressing CTRL + S. Exits on pressing CTRL + W. Exits and saves on pressing
     * CTRL + Q. TimeStamps on the current line when CTRL + T is pressed.
     * @param event User key Event
     * @see seedu.notor.logic.Logic#executeSaveNote(Person, Person)
     */
    @FXML
    public void handleOnKeyPressed(KeyEvent event) throws CommandException {
        if (SAVE_KEY.match(event)) {
            handleSave();
            return;
        }
        if (EXIT_AND_SAVE_KEY.match(event)) {
            handleSaveAndExit();
            return;
        }
        if (EXIT_KEY.match(event)) {
            handleExit();
            return;
        }
        if (TIME_STAMP_KEY.match(event)) {
            timeStampNote();
        }
    }




}
