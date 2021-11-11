package seedu.intern.testutil;

import static seedu.intern.testutil.TypicalIndexes.INDEX_FIRST_APPLICANT;
import static seedu.intern.testutil.TypicalIndexes.INDEX_SECOND_APPLICANT;
import static seedu.intern.testutil.TypicalIndexes.INDEX_THIRD_APPLICANT;

import seedu.intern.commons.core.selection.Selection;

public class TypicalSelections {
    public static final Selection SELECTION_FIRST_APPLICANT = Selection.fromIndex(INDEX_FIRST_APPLICANT);
    public static final Selection SELECTION_SECOND_APPLICANT = Selection.fromIndex(INDEX_SECOND_APPLICANT);
    public static final Selection SELECTION_THIRD_APPLICANT = Selection.fromIndex(INDEX_THIRD_APPLICANT);
    public static final Selection SELECTION_ALL = Selection.fromAllFlag();
}
