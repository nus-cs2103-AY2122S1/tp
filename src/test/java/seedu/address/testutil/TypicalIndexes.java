package seedu.address.testutil;

import static seedu.address.logic.parser.ParserUtil.INDEX_LIMIT;

import seedu.address.commons.core.index.Index;

/**
 * A utility class containing a list of {@code Index} objects to be used in tests.
 */
public class TypicalIndexes {
    public static final Index INDEX_FIRST_MODULE = Index.fromOneBased(1);
    public static final Index INDEX_SECOND_MODULE = Index.fromOneBased(2);
    public static final Index INDEX_THIRD_MODULE = Index.fromOneBased(3);
    public static final Index INDEX_FIRST_LESSON = Index.fromOneBased(1);
    public static final Index INDEX_SECOND_LESSON = Index.fromOneBased(2);
    public static final Index INDEX_FIRST_EXAM = Index.fromOneBased(1);
    public static final Index INDEX_SECOND_EXAM = Index.fromOneBased(2);
    public static final Index INDEX_MAX_LIMIT = Index.fromOneBased(INDEX_LIMIT);
}
