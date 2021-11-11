package seedu.intern.commons.core.selection;

public class Selection {
    private static final String MESSAGE_MISSING_INDEX = "Selection does not contain an Index.";
    private static final String MESSAGE_MISSING_FLAG = "Selection does not contain an ALL Flag.";
    private final Index index;
    private final Boolean isAllSelected;

    private Selection(Index index, Boolean isAllSelected) {
        assert (index == null || isAllSelected == null);
        this.index = index;
        this.isAllSelected = isAllSelected;
    }

    public Index getIndex() {
        return index;
    }

    public int getIndexOneBased() {
        if (this.hasIndex()) {
            return this.index.getOneBased();
        } else {
            throw new NullPointerException(MESSAGE_MISSING_INDEX);
        }
    }

    public int getIndexZeroBased() {
        if (this.hasIndex()) {
            return this.index.getZeroBased();
        } else {
            throw new NullPointerException(MESSAGE_MISSING_INDEX);
        }
    }

    /**
     * Returns true if ALL flag has been set by the user.
     */
    public boolean checkAllSelected() {
        if (!hasAllSelectFlag()) {
            throw new NullPointerException(MESSAGE_MISSING_FLAG);
        }
        // isAllSelected should not be false, as constructors are private.
        assert this.isAllSelected;

        return isAllSelected;
    }

    public boolean hasIndex() {
        return this.index != null;
    }

    public boolean hasAllSelectFlag() {
        return this.isAllSelected != null;
    }

    public static Selection fromAllFlag() {
        return new Selection(null, true);
    }

    public static Selection fromIndex(Index index) {
        return new Selection(index, null);
    }

    @Override
    public boolean equals(Object other) {
        if (other == this) {
            return true;
        }

        if (!(other instanceof Selection)) { // instanceof handles nulls
            return false;
        }

        Selection otherSelection = (Selection) other;

        if (this.hasIndex() != otherSelection.hasIndex()
                || this.hasAllSelectFlag() != otherSelection.hasAllSelectFlag()) {
            return false;
        }

        if (this.hasIndex() && this.getIndexOneBased() != otherSelection.getIndexOneBased()) {
            return false;
        } else if (this.hasAllSelectFlag()
                && this.checkAllSelected() != otherSelection.checkAllSelected()) {
            return false;
        } else {
            return true;
        }
    }
}
