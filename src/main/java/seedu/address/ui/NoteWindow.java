package seedu.address.ui;

import java.util.ArrayList;

import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.TextArea;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyCodeCombination;
import javafx.scene.input.KeyCombination;
import javafx.scene.input.KeyCombination.Modifier;
import javafx.scene.input.KeyEvent;
import javafx.stage.Stage;
import seedu.address.commons.util.DateUtil;
import seedu.address.logic.Logic;
import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.model.person.Note;
import seedu.address.model.person.Person;



public class NoteWindow extends UiPart<Stage> {

    protected static final ArrayList<NoteWindow> OPENED_NOTE_WINDOWS = new ArrayList<>();
    private static final String FXML = "NoteWindow.fxml";
    private static final Modifier CTRL = KeyCombination.SHORTCUT_DOWN;
    private static final KeyCombination SAVE_KEY = new KeyCodeCombination(KeyCode.S, CTRL);
    private static final KeyCombination EXIT_AND_SAVE_KEY = new KeyCodeCombination(KeyCode.Q, CTRL);
    private static final KeyCombination EXIT_KEY = new KeyCodeCombination(KeyCode.W, CTRL);
    private static final KeyCombination TIME_STAMP_KEY = new KeyCodeCombination(KeyCode.T, CTRL);

    @FXML
    private TextArea noteTextArea;

    private final Person person;

    private final Logic logic;


    /**
     * Creates a new NoteWindow.
     */
    public NoteWindow(Person person, Logic logic) {
        super(FXML);
        noteTextArea.setText(person.getNote().value);
        this.person = person;
        this.logic = logic;
        getRoot().setTitle(person.getName().toString());
    }

    /**
     * Show the noteWindow.
     */
    public void show() {
        getRoot().show();
        getRoot().centerOnScreen();
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
     * Saves the file
     */
    @FXML
    public void handleSave() throws CommandException {
        String paragraph = noteTextArea.getText();
        Note editedNote = new Note(paragraph, DateUtil.getCurrentDateTime());
        Person editedPerson = new Person(person.getName(), person.getPhone(), person.getEmail(),
                editedNote, person.getTags());
        logic.executeSaveNote(person, editedPerson);
    }


    /**
     * Exits the note window.
     */
    @FXML
    public void handleExit() {
        getRoot().close();
        OPENED_NOTE_WINDOWS.remove(this);
    }
    /**
     * Exits and saves the note window.
     */
    @FXML
    public void handleSaveAndExit() throws CommandException {
        handleSave();
        handleExit();
    }

    /**
     * Returns the index of first character of current line of the text cursor.
     * @return index of current line
     */
    private int getLineIndex() {
        int noOfCharsTillCursor = noteTextArea.getCaretPosition();
        int wordIndex = 0;
        ObservableList<CharSequence> noteCharSequence = noteTextArea.getParagraphs();
        for (CharSequence charSequence : noteCharSequence) {
            int noOfChars = charSequence.length() + 1;
            if (wordIndex + noOfChars >= noOfCharsTillCursor) {
                return wordIndex;
            }
            wordIndex += noOfChars;
        }
        return wordIndex;
    }

    private void timeStampNote() {
        int currentLine = getLineIndex();
        String noteBeforeLine = currentLine == 0 ? "" : noteTextArea.getText(0, currentLine);
        System.out.println(noteBeforeLine);
        String noteAfterLine = noteTextArea.getText(currentLine, noteTextArea.getLength());
        noteTextArea.setText(noteBeforeLine + System.lineSeparator() + DateUtil.getCurrentDateTime()
                + System.lineSeparator() + noteAfterLine);
        noteTextArea.selectPositionCaret(currentLine);
        noteTextArea.deselect();
    }

    /**
     * Reads user key event. Saves on pressing Ctrl + S. Exits on pressing Ctrl + W. Exits and saves on pressing
     * Ctrl + Q.
     * @param event User key Event
     * @see seedu.address.logic.Logic#executeSaveNote(Person, Person)
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
