package seedu.address.testutil;

import seedu.address.model.LastUpdatedDate;

public class LastUpdatedDateUtil {
    public static final String INVALID_LAST_ADDED_DATE = "2021-02-29";

    public static final String VALID_LAST_UPDATED_DATE = "2021-10-23";

    public static LastUpdatedDate getTypicalLastUpdatedDate() {
        return new LastUpdatedDate(VALID_LAST_UPDATED_DATE);
    }
}
