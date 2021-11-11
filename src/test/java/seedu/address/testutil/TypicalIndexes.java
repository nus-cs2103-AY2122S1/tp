package seedu.address.testutil;

import seedu.address.commons.core.index.Index;

/**
 * A utility class containing a list of {@code Index} objects to be used in tests.
 */
public class TypicalIndexes {
    public static final Index INDEX_FIRST_TUTEE = Index.fromOneBased(1);
    public static final Index INDEX_SECOND_TUTEE = Index.fromOneBased(2);
    public static final Index INDEX_THIRD_TUTEE = Index.fromOneBased(3);

    public static final Index INDEX_FIRST_LESSON = Index.fromOneBased(1);
    public static final Index INDEX_SECOND_LESSON = Index.fromOneBased(2);
    public static final Index INDEX_THIRD_LESSON = Index.fromOneBased(3);

    public static final String INDEX_OUT_OF_BOUNDS = "2147483648";
}
