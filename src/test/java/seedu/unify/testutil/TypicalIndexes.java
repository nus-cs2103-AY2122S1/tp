package seedu.unify.testutil;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import seedu.unify.commons.core.index.Index;

/**
 * A utility class containing a list of {@code Index} objects to be used in tests.
 */
public class TypicalIndexes {
    public static final Index INDEX_FIRST_TASK = Index.fromOneBased(1);
    public static final Index INDEX_SECOND_TASK = Index.fromOneBased(2);
    public static final Index INDEX_THIRD_TASK = Index.fromOneBased(3);
    public static final Index INDEX_DONE_TASK = Index.fromOneBased(7);
    public static final List<Index> INDEX_LIST_FIRST_TASK = new ArrayList<>(Arrays.asList(INDEX_FIRST_TASK));
    public static final List<Index> INDEX_LIST_SECOND_TASK = new ArrayList<>(Arrays.asList(Index.fromOneBased(2)));
}
