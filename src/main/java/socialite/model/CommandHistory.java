package socialite.model;

import static java.util.Objects.requireNonNull;

import java.util.ArrayList;
import java.util.List;

public class CommandHistory implements ReadOnlyCommandHistory {

    private List<String> history;

    /*
     * This non-static initialization block logic is copied over from ContactList to avoid duplication
     * between constructors.
     */
    {
        history = new ArrayList<>();
    }

    public CommandHistory() {}

    /**
     * Creates a CommandHistory using the commands in the {@code toBeCopied}.
     */
    public CommandHistory(ReadOnlyCommandHistory toBeCopied) {
        this();
        resetData(toBeCopied);
    }

    /**
     * Resets the existing data of this {@code CommandHistory} with {@code newData}.
     */
    public void resetData(ReadOnlyCommandHistory newData) {
        requireNonNull(newData);
        this.history = newData.getCommandHistory();
    }


    public void addCommand(String command) {
        history.add(command);
    }

    @Override
    public List<String> getCommandHistory() {
        return history;
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof CommandHistory) // instanceof handles nulls
                && history.equals(((CommandHistory) other).history);
    }

    @Override
    public String toString() {
        return this.history.toString();
    }
}
