package seedu.address.logic.commands.memento;

import javafx.collections.ObservableList;
import seedu.address.model.applicant.Applicant;
import seedu.address.model.position.Position;

import java.util.Optional;

/**
 * Records the state of {@code positionBook} and {@code applicantBook}
 */
public class Snapshot {
    ObservableList<Position> positionList;
    ObservableList<Applicant> applicantList;

    /**
     * Initializes a snapshot.
     */
    public Snapshot() {};
    /**
     * Constructs a snapshot with both {@code positionBook} and {@code applicantBook}.
     */
    private Snapshot(ObservableList<Position> positionList, ObservableList<Applicant> applicantList) {
        this.positionList = positionList;
        this.applicantList = applicantList;
    }

    public Snapshot withPositionList(ObservableList<Position> positionList) {
        this.positionList = positionList;
        return this;
    }

    public Snapshot withApplicantList(ObservableList<Applicant> applicantList) {
        this.applicantList = applicantList;
        return this;
    }

    public Optional<ObservableList<Position>> getPositionList() {
        return Optional.ofNullable(positionList);
    }

    public Optional<ObservableList<Applicant>> getApplicantList() {
        return Optional.ofNullable(applicantList);
    }

}
