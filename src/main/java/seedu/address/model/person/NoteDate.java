package seedu.address.model.person;

/**
 * Represents Note's last saved date.
 */
public class NoteDate {
    public static final String EMPTY = "";
    public final String value;

    /**
     * Constructor for a empty NoteDate instance.
     */
    public NoteDate() {
        this.value = EMPTY;
    }
    /**
     * Constructor for a NoteDate instance.
     *
     * @param value Value of the note.
     */
    public NoteDate(String value) {
        this.value = value;
    }

    @Override
    public String toString() {
        return value;
    }
}
