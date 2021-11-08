package seedu.address.model;

import static java.util.Objects.requireNonNull;

import java.util.Objects;

import seedu.address.model.account.Password;
import seedu.address.model.util.SampleDataUtil;

/**
 * Represents an Account.
 * Guarantees: details are present and not null, field values are validated, immutable.
 */
public class Account implements ReadOnlyAccount {

    // Data fields
    private Password password;

    /**
     * Creates a {@code Account} with default values.
     */
    public Account() {
        password = SampleDataUtil.getDefaultPassword().getPassword();
    }

    /**
     * Creates a {@code Account} with the data in {@code account}.
     */
    public Account(ReadOnlyAccount account) {
        this();
        resetData(account);
    }

    /**
     * Every field must be present and not null.
     */
    public Account(Password password) {
        requireNonNull(password);
        this.password = password;
    }

    /**
     * Resets the existing data of this {@code Account} with {@code newAccount}.
     */
    public void resetData(ReadOnlyAccount newAccount) {
        requireNonNull(newAccount);
        setPassword(newAccount.getPassword());
    }

    /**
     * Sets the Password by giving {@code password}.
     */
    public void setPassword(Password password) {
        requireNonNull(password);
        this.password = password;
    }

    /**
     * Gets the password.
     *
     * @return Password of the account.
     */
    @Override
    public Password getPassword() {
        return password;
    }

    /**
     * Returns true if both accounts have the same password.
     * This defines a weaker notion of equality between two accounts.
     */
    public boolean isSamePassword(Account otherAccount) {
        if (otherAccount == this) {
            return true;
        }

        return otherAccount != null
                && otherAccount.getPassword().equals(getPassword());
    }

    /**
     * Overrides the equals method.
     * Returns true if both accounts have the same data fields.
     * This defines a stronger notion of equality between two accounts.
     */
    @Override
    public boolean equals(Object other) {
        if (other == this) {
            return true;
        }
        if (!(other instanceof Account)) {
            return false;
        }

        Account otherAccount = (Account) other;

        return otherAccount.getPassword().equals(getPassword());
    }

    /**
     * Overrides the hashcode method.
     * Use this method for custom fields hashing instead of implementing your own.
     */
    @Override
    public int hashCode() {
        return Objects.hash(password);
    }

    /**
     * Overrides the toString method.
     * @return String with password value.
     */
    @Override
    public String toString() {
        final StringBuilder builder = new StringBuilder();
        builder.append("Password: ").append(getPassword());
        return builder.toString();
    }
}
