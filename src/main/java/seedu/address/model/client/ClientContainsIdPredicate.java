package seedu.address.model.client;

import java.util.List;
import java.util.function.Predicate;

import seedu.address.commons.util.StringUtil;

/**
 * Tests that a {@code Client}'s {@code id} matches the id given.
 */
public class ClientContainsIdPredicate implements Predicate<Client> {
    //use a list for easier searching via streams
    private final List<String> id;

    public ClientContainsIdPredicate(List<String> id) {
        this.id = id;
    }

    @Override
    public boolean test(Client client) {
        return id.stream()
                .anyMatch(id -> {
                    Integer integerId = Integer.parseInt(id) - 1;
                    String stringOfId = integerId.toString();
                    return StringUtil.containsWordIgnoreCase(client.getId().toString(), stringOfId);
                });
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof ClientContainsIdPredicate)// instanceof handles nulls
                && id.equals(((ClientContainsIdPredicate) other).id); // state check
    }
}
