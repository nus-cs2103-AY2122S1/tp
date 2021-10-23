package seedu.address.testutil;

import java.util.ArrayList;
import java.util.List;

import seedu.address.commons.core.index.Index;

public class TypicalIndexLists {
    public static final List<Index> INDEX_LIST_FIRST = List.of(Index.fromOneBased(1));
    public static final List<Index> INDEX_LIST_FIRST_TO_SECOND = List.of(
            Index.fromOneBased(1), Index.fromOneBased(2));
    public static final List<Index> INDEX_LIST_FIRST_TO_THIRD = List.of(
            Index.fromOneBased(1), Index.fromOneBased(2), Index.fromOneBased(3));
}
