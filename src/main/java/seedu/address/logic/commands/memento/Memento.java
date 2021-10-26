package seedu.address.logic.commands.memento;

import javafx.collections.ObservableList;
import seedu.address.model.Model;
import seedu.address.model.applicant.Applicant;
import seedu.address.model.position.Position;

/**
 * Keeps a {@code model} when a modification command executes.
 * The use of Memento pattern is adapted from
 * https://stackoverflow.com/questions/11530276/how-do-i-implement-a-simple-undo-redo-for-actions-in-java.
 */
public class Memento {
    Model model;
    String message;

    public Memento() {}

    public void record(Model model) {
        this.model = model;
    }

    public void recordMessage(String message) {
        this.message = message;
    }

    public Model getModel() {
        return model;
    }

    public String getMessage() {
        return message;
    }
}
