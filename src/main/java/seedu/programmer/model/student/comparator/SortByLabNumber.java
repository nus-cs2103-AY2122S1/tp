package seedu.programmer.model.student.comparator;

import java.util.Comparator;

import seedu.programmer.model.student.Lab;

/**
 * A class to help sort Students in ascending order.
 */
public class SortByLabNumber implements Comparator<Lab> {

    @Override
    public int compare(Lab l1, Lab l2) {
        return l1.getLabNumValue().compareTo(l2.getLabNumValue());
    }
}
