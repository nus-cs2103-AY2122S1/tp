package seedu.address.model.util;

import java.util.Arrays;
import java.util.Set;
import java.util.stream.Collectors;

import seedu.address.model.AddressBook;
import seedu.address.model.ReadOnlyAddressBook;
import seedu.address.model.client.Client;
import seedu.address.model.person.Address;
import seedu.address.model.person.Email;
import seedu.address.model.person.Name;
import seedu.address.model.person.Person;
import seedu.address.model.person.Phone;
import seedu.address.model.tag.Tag;

/**
 * Contains utility methods for populating {@code AddressBook} with sample data.
 */
public class SampleClientDataUtil {
    public static Client[] getSampleClients() {
        return new Client[] {
                new Client(new seedu.address.model.commons.Name("Alex Yeoh")),
                new Client(new seedu.address.model.commons.Name("Bernice Yu")),
                new Client(new seedu.address.model.commons.Name("Charlotte Oliveiro")),
                new Client(new seedu.address.model.commons.Name("David Li")),
                new Client(new seedu.address.model.commons.Name("Irfan Ibrahim")),
                new Client(new seedu.address.model.commons.Name("Roy Balakrishnan"))
        };
    }

    public static ReadOnlyAddressBook getSampleAddressBook() {
        AddressBook sampleAb = new AddressBook();
        for (Client sampleClient : getSampleClients()) {
            sampleAb.addClient(sampleClient);
        }
        return sampleAb;
    }

    /**
     * Returns a tag set containing the list of strings given.
     */
    public static Set<Tag> getTagSet(String... strings) {
        return Arrays.stream(strings)
                .map(Tag::new)
                .collect(Collectors.toSet());
    }

}
