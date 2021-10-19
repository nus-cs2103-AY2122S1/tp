package seedu.address.model.person.exceptions;

import static seedu.address.commons.util.StringUtil.CLIENTID_DELIMITER;
import static seedu.address.commons.util.StringUtil.joinListToString;

import java.util.List;

import seedu.address.model.person.ClientId;

/**
 * Signals that the operation is unable to find the specified person.
 */
public class PersonNotFoundException extends RuntimeException {

    public PersonNotFoundException() {}

    public PersonNotFoundException(List<ClientId> notFound) {
        super(joinListToString(notFound, CLIENTID_DELIMITER));
    }
}
