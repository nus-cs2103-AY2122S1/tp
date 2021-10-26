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
    Snapshot snapshot;
    Model model;

    public Memento() {
        this.snapshot = new Snapshot();
    }

    public void recordPositionList(ObservableList<Position> positionList) {
        this.snapshot.withPositionList(positionList);
    }

    public void recordApplicantList(ObservableList<Applicant> applicantList) {
        this.snapshot.withApplicantList(applicantList);
    }

    public void record(Model model) {
        this.model = model;
    }

    public Model getRecord() {
        return model;
    }
}
