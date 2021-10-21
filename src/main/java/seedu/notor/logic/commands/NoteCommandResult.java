package seedu.notor.logic.commands;


public class NoteCommandResult<T> extends CommandResult {


    private final T notable;

    /**
     * Constructs a {@code CommandResult} with the specified fields.
     */
    public NoteCommandResult(String feedbackToUser, T notable) {
        super(feedbackToUser);
        this.notable = notable;
    }

    @Override
    public boolean isShowNote() {
        return true;
    }

    public T notable() {
        return notable;
    }

}
