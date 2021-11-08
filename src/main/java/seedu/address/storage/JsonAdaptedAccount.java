package seedu.address.storage;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

import seedu.address.commons.exceptions.IllegalValueException;
import seedu.address.model.Account;
import seedu.address.model.ReadOnlyAccount;
import seedu.address.model.account.Password;

/**
 * Jackson-friendly version of {@link Account}.
 */
class JsonAdaptedAccount {

    public static final String MISSING_FIELD_MESSAGE_FORMAT = "Account's %s is missing!";

    private final String password;

    /**
     * Constructs a {@code JsonAdaptedAccount} with the given account details.
     */
    @JsonCreator
    public JsonAdaptedAccount(@JsonProperty("password") String password) {
        this.password = password;
    }

    /**
     * Converts a given {@code Account} into this class for Jackson use.
     *
     * @param source future changes to this will not affect the created {@code JsonAdaptedAccount}.
     */
    public JsonAdaptedAccount(ReadOnlyAccount source) {
        this.password = source.getPassword().value;
    }

    /**
     * Converts this Jackson-friendly adapted account object into the model's {@code Account} object.
     *
     * @throws IllegalValueException if there were any data constraints violated in the adapted account.
     */
    public Account toModelType() throws IllegalValueException {
        if (password == null) {
            throw new IllegalValueException(
                    String.format(MISSING_FIELD_MESSAGE_FORMAT, Password.class.getSimpleName()));
        }
        if (!Password.isValidPassword(password)) {
            throw new IllegalValueException(Password.MESSAGE_CONSTRAINTS);
        }
        final Password modelPassword = new Password(password);

        return new Account(modelPassword);
    }

}
