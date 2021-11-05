package seedu.address.storage;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

import seedu.address.commons.exceptions.IllegalValueException;
import seedu.address.model.person.Person;
import seedu.address.testutil.TypicalPersons;

/**
 * Test class for {@link JsonSerializableUserProfile}.
 */
public class JsonSerializableUserProfileTest {

    /**
     * Checks if conversion from {@code JsonAdaptedPerson} to {@code Person} is successful.
     *
     * @throws IllegalValueException If there is an error converting to Person.
     */
    @Test
    public void toModelType_jsonAdaptedPerson_success() throws IllegalValueException {
        Person alice = TypicalPersons.ALICE;
        JsonAdaptedPerson jsonAdaptedPerson = new JsonAdaptedPerson(alice);
        JsonSerializableUserProfile userProfile = new JsonSerializableUserProfile(jsonAdaptedPerson);
        assertEquals(alice, userProfile.toModelType());
    }
}
