package seedu.address.testutil;

import seedu.address.logic.parser.ParserUtil;
import seedu.address.logic.parser.exceptions.ParseException;
import seedu.address.model.Account;

/**
 * A utility class containing an account object to be used in tests.
 */
public class TypicalAccount {

    private TypicalAccount() {} // prevents instantiation

    /**
     * Returns an {@code Account}.
     * @throws ParseException if the typical password is invalid.
     */
    public static Account getTypicalAccount() throws ParseException {
        return new AccountBuilder().withPassword(ParserUtil.parsePassword("654321")).build();
    }
}
