package seedu.programmer.testutil;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import seedu.programmer.model.student.Lab;

public class TypicalLabs {
    public static final Lab LAB_1 = new LabBuilder().withLabNum(1).withResult(10).withTotal(20).build();
    public static final Lab LAB_2 = new LabBuilder().withLabNum(2).withResult(0).withTotal(10).build();
    public static final Lab LAB_3 = new LabBuilder().withLabNum(3).withResult(20).withTotal(20).build();
    public static final Lab LAB_4 = new LabBuilder().withLabNum(4).withResult(30).withTotal(40).build();
    public static final Lab LAB_5 = new LabBuilder().withLabNum(5).withResult(5).withTotal(20).build();
    public static final Lab LAB_6 = new LabBuilder().withLabNum(6).withResult(0).withTotal(40).build();

    private TypicalLabs() {
    } // prevents instantiation

    /**
     * Returns an {@code labresultlist} with all the typical labs.
     */
    public static ObservableList<Lab> getTypicalLabList() {
        ObservableList<Lab> labResultList = FXCollections.observableArrayList();
        labResultList.addAll(getTypicalLabs());
        return labResultList;
    }

    public static List<Lab> getTypicalLabs() {
        return new ArrayList<>(Arrays.asList(LAB_1, LAB_2, LAB_3, LAB_4, LAB_5));
    }
}
