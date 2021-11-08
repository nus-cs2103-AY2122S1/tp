package seedu.address.testutil;

import seedu.address.commons.core.index.Index;
import seedu.address.logic.parser.ParserUtil;

/**
 * A utility class containing a list of {@code Index} objects to be used in tests.
 */
public class TypicalIndexes {
    public static final Index MAX_INDEX = Index.fromOneBased(ParserUtil.MAX_INDEX_LIMIT);

    public static final Index INDEX_FIRST_PERSON = Index.fromOneBased(1);
    public static final Index INDEX_SECOND_PERSON = Index.fromOneBased(2);
    public static final Index INDEX_THIRD_PERSON = Index.fromOneBased(3);
    public static final Index INDEX_SIXTH_PERSON = Index.fromOneBased(6);

    public static final Index INDEX_FIRST_ASSIGNMENT = Index.fromOneBased(1);
    public static final Index INDEX_SECOND_ASSIGNMENT = Index.fromOneBased(2);

}
