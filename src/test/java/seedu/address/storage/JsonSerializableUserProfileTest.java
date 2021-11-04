package seedu.address.storage;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

import seedu.address.commons.exceptions.IllegalValueException;
import seedu.address.model.person.Person;
import seedu.address.testutil.TypicalPersons;

public class JsonSerializableUserProfileTest {
    @Test
    public void toModelType_jsonAdaptedPerson_success() throws IllegalValueException {
        Person ALICE = TypicalPersons.ALICE;
        JsonAdaptedPerson jsonAdaptedPerson = new JsonAdaptedPerson(ALICE);
        JsonSerializableUserProfile userProfile = new JsonSerializableUserProfile(jsonAdaptedPerson);
        assertEquals(ALICE, userProfile.toModelType());
    }
}
