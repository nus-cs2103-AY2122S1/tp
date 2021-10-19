package seedu.notor.ui;

import java.awt.Toolkit;
import java.util.ArrayList;

import javafx.fxml.FXML;
import javafx.scene.control.TextArea;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyCodeCombination;
import javafx.scene.input.KeyCombination;
import javafx.scene.input.KeyCombination.Modifier;
import javafx.scene.input.KeyEvent;
import javafx.stage.Stage;
import seedu.notor.commons.util.DateUtil;
import seedu.notor.logic.Logic;
import seedu.notor.logic.commands.exceptions.CommandException;
import seedu.notor.model.common.Note;
import seedu.notor.model.person.Person;



public class NoteWindow extends UiPart<Stage> {

    protected static final ArrayList<NoteWindow> OPENED_NOTE_WINDOWS = new ArrayList<>();
    private static final String MESSAGE_SAVE_NOTE_SUCCESS = "Saved Note to Person: %1$s";
    private static final String MESSAGE_EXIT_NOTE_SUCCESS = "Exited Note of Person: %1$s";
    private static final int WIDTH = 616;
    private static final int HEIGHT = 390;
    private static final int OFFSET = 10;
    private static final int SCREEN_X = Toolkit.getDefaultToolkit().getScreenSize().width;
    private static final int SCREEN_Y = Toolkit.getDefaultToolkit().getScreenSize().height;
    private static final int CENTER_X = (SCREEN_X - WIDTH) / 2;
    private static final int CENTER_Y = (SCREEN_Y - HEIGHT) / 2;
    private static final String FXML = "NoteWindow.fxml";
    private static final Modifier CTRL = KeyCombination.CONTROL_DOWN;
    private static final KeyCombination SAVE_KEY = new KeyCodeCombination(KeyCode.S, CTRL);
    private static final KeyCombination EXIT_AND_SAVE_KEY = new KeyCodeCombination(KeyCode.Q, CTRL);
    private static final KeyCombination EXIT_KEY = new KeyCodeCombination(KeyCode.W, CTRL);
    private static final KeyCombination TIME_STAMP_KEY = new KeyCodeCombination(KeyCode.T, CTRL);

    @FXML
    private TextArea noteTextArea;

    private Person person;

    private final Logic logic;

    private final seedu.notor.ui.ConfirmationWindow confirmationWindow;

    private final ResultDisplay resultDisplay;


    /**
     * Creates a new NoteWindow.
     */
    public NoteWindow(Person person, Logic logic, ResultDisplay resultDisplay) {
        super(FXML);
        noteTextArea.setText(person.getNote().value);
        this.resultDisplay = resultDisplay;
        this.person = person;
        this.logic = logic;
        confirmationWindow = new ConfirmationWindow(person.getName().toString(), this);
        getRoot().setTitle(person.getName().toString());
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
    private String generateSuccessMessage(String message, Person personToEdit) {
        return String.format(message, personToEdit);
    }

    /**
     * Saves the file
     */
    @FXML
    public void handleSave() throws CommandException {
        String paragraph = noteTextArea.getText();
        Note editedNote = new Note(paragraph, noteLastModified());
        Person editedPerson = new Person(person.getName(), person.getPhone(), person.getEmail(),
                editedNote, person.getTags());
        person = editedPerson;
        logic.executeSaveNote(person, editedPerson);
        resultDisplay.setFeedbackToUser(generateSuccessMessage(MESSAGE_SAVE_NOTE_SUCCESS, person));
    }

    /**
     * Checks if current Note is saved.
     */
    private boolean isSave() {
        return person.getNote().value.equals(noteTextArea.getText());
    }

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
    public void exit() {
        getRoot().close();
        OPENED_NOTE_WINDOWS.remove(this);
        resultDisplay.setFeedbackToUser(generateSuccessMessage(MESSAGE_EXIT_NOTE_SUCCESS, person));
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


    @Override
    public boolean equals(Object other) {
        if (other == this) {
            return true;
        }

        if (!(other instanceof NoteWindow)) {
            return false;
        }

        NoteWindow otherPerson = (NoteWindow) other;
        return otherPerson.person.equals(this.person);

    }

}
