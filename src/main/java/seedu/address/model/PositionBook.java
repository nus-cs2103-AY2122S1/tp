package seedu.address.model;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import seedu.address.model.applicant.Applicant;

public class PositionBook implements ReadOnlyPositionBook {

    public PositionBook() {}

    public PositionBook(ReadOnlyPositionBook toBeCopied) {}

    @Override
    public ObservableList<Applicant> getPositionList() {
        return FXCollections.emptyObservableList();
    }

}
