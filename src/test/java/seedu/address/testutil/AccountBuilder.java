package seedu.address.testutil;

import seedu.address.model.Account;
import seedu.address.model.account.Password;

/**
 * A utility class to help with building Account objects.
 * Example usage: <br>
 *     {@code Account account = new AccountBuilder().withPassword("123456").build();}
 */
public class AccountBuilder {

    private Account account;

    public AccountBuilder() {
        account = new Account();
    }

    public AccountBuilder(Account account) {
        this.account = account;
    }

    /**
     * Adds a new {@code Member} to the {@code EzFoodie} that we are building.
     */
    public AccountBuilder withPassword(Password password) {
        account.setPassword(password);
        return this;
    }

    public Account build() {
        return account;
    }
}
