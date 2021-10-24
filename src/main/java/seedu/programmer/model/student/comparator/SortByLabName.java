package seedu.programmer.model.student.comparator;

import java.util.Comparator;

import seedu.programmer.model.student.Lab;

/**
 * A class to help sort Students in ascending order.
 */
public class SortByLabName implements Comparator<Lab> {

    @Override
    public int compare(Lab l1, Lab l2) {
        Integer firstLabIndex = Integer.parseInt(l1.getTitle().replace("lab", ""));
        Integer secondLabIndex = Integer.parseInt(l2.getTitle().replace("lab", ""));
        return firstLabIndex.compareTo(secondLabIndex);
    }
}
