package seedu.teachbook.commons.core.index;

import java.util.Comparator;

/**
 * A comparator that compares two {@code Index} by comparing their index values.
 */
public class IndexComparator implements Comparator<Index> {

    @Override
    public int compare(Index s1, Index s2) {
        return Integer.compare(s2.getOneBased(), s1.getOneBased());
    }

}
