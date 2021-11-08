package seedu.address.model;

import seedu.address.model.account.Password;

/**
 * Unmodifiable view of an account.
 */
public interface ReadOnlyAccount {

    Password getPassword();

}
