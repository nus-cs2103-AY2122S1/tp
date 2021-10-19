package seedu.address.model.person;

import java.util.List;
import java.util.function.Predicate;

/**
 * Tests that a {@code Person}'s attributes matches any of the keywords given.
 */
public class PersonHasId implements Predicate<Person> {
    private final List<ClientId> clientId;

    public PersonHasId(List<ClientId> clientId) {
        this.clientId = clientId;
    }

    public PersonHasId(ClientId clientId) {
        this.clientId = List.of(clientId);
    }

    @Override
    public boolean test(Person person) {
        return clientId.contains(person.getClientId());
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
            || (other instanceof PersonHasId // instanceof handles nulls
            && clientId.equals(((PersonHasId) other).clientId)); // state check
    }
}
