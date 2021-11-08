package seedu.address.model.remark;

import static java.util.Objects.requireNonNull;
/**
 * Represents a Remark in the address book.
 */
public class Remark {

    public final String remarkDetail;

    /**
     * Constructs a {@code Remark}.
     *
     * @param remarkDetail A valid tag name.
     */
    public Remark(String remarkDetail) {
        requireNonNull(remarkDetail);
        this.remarkDetail = remarkDetail;
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof Remark // instanceof handles nulls
                && remarkDetail.equals(((Remark) other).remarkDetail)); // state check
    }

    @Override
    public int hashCode() {
        return remarkDetail.hashCode();
    }

    /**
     * Format state as text for viewing.
     */
    public String toString() {
        return '[' + remarkDetail + ']';
    }

}
