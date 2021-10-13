package seedu.address.testutil;

import seedu.address.commons.core.index.Index;
import seedu.address.model.person.ClientId;

public class TypicalClientId {
    public static final ClientId CLIENTID_ZERO_PERSON = new ClientId(Integer.toString(0));
    public static final ClientId CLIENTID_FIRST_PERSON = new ClientId(Integer.toString(1));
    public static final ClientId CLIENTID_SECOND_PERSON = new ClientId(Integer.toString(2));
    public static final ClientId CLIENTID_THIRD_PERSON = new ClientId(Integer.toString(3));
    public static final ClientId CLIENTID_SIXTH_PERSON = new ClientId(Integer.toString(6));
    public static final ClientId CLIENTID_OUTOFBOUND = new ClientId(Integer.toString(99));
}
