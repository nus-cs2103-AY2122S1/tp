package seedu.address.testutil;

import static seedu.address.logic.commands.CommandTestUtil.VALID_ADDRESS_AMY;
import static seedu.address.logic.commands.CommandTestUtil.VALID_ADDRESS_BOB;
import static seedu.address.logic.commands.CommandTestUtil.VALID_EMAIL_AMY;
import static seedu.address.logic.commands.CommandTestUtil.VALID_EMAIL_BOB;
import static seedu.address.logic.commands.CommandTestUtil.VALID_NAME_AMY;
import static seedu.address.logic.commands.CommandTestUtil.VALID_NAME_BOB;
import static seedu.address.logic.commands.CommandTestUtil.VALID_PHONE_NUMBER_AMY;
import static seedu.address.logic.commands.CommandTestUtil.VALID_PHONE_NUMBER_BOB;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import seedu.address.model.AddressBook;
import seedu.address.model.client.Client;

/**
 * A utility class containing a list of {@code Client} objects to be used in tests.
 */
public class TypicalClients {

    public static final Client ALICE = new ClientBuilder()
            .withName("Alice Pauline")
            .withPhoneNumber("94351253")
            .withEmail("alice@example.com")
            .withAddress("123, Jurong West Ave 6, #08-111")
            .build();
    public static final Client BENSON = new ClientBuilder()
            .withName("Benson Meier")
            .withPhoneNumber("98765432")
            .withEmail("johnd@example.com")
            .withAddress("311, Clementi Ave 2, #02-25")
            .build();
    public static final Client CARL = new ClientBuilder()
            .withName("Carl Kurz")
            .withPhoneNumber("95352563")
            .withEmail("heinz@example.com")
            .withAddress("wall street")
            .build();
    public static final Client DANIEL = new ClientBuilder()
            .withName("Daniel Meier")
            .withPhoneNumber("87652533")
            .withEmail("cornelia@example.com")
            .withAddress("10th street")
            .build();
    public static final Client ELLE = new ClientBuilder()
            .withName("Elle Meyer")
            .withPhoneNumber("9482224")
            .withEmail("werner@example.com")
            .withAddress("michegan ave")
            .build();
    public static final Client FIONA = new ClientBuilder()
            .withName("Fiona Kunz")
            .withPhoneNumber("9482427")
            .withEmail("lydia@example.com")
            .withAddress("little tokyo")
            .build();
    public static final Client GEORGE = new ClientBuilder()
            .withName("George Best")
            .withPhoneNumber("9482442")
            .withEmail("anna@example.com")
            .withAddress("4th street")
            .build();

    // Manually added
    public static final Client HOON = new ClientBuilder()
            .withName("Hoon Meier")
            .withPhoneNumber("8482424")
            .withEmail("stefan@example.com")
            .withAddress("little india")
            .build();
    public static final Client IDA = new ClientBuilder()
            .withName("Ida Mueller")
            .withPhoneNumber("8482131")
            .withEmail("hans@example.com")
            .withAddress("chicago ave")
            .build();

    // Manually added - Client's details found in {@code CommandTestUtil}
    public static final Client AMY = new ClientBuilder().withName(VALID_NAME_AMY)
            .withPhoneNumber(VALID_PHONE_NUMBER_AMY)
            .withEmail(VALID_EMAIL_AMY)
            .withAddress(VALID_ADDRESS_AMY)
            .build();
    public static final Client BOB = new ClientBuilder()
            .withName(VALID_NAME_BOB)
            .withPhoneNumber(VALID_PHONE_NUMBER_BOB)
            .withEmail(VALID_EMAIL_BOB)
            .withAddress(VALID_ADDRESS_BOB)
            .build();

    public static final String KEYWORD_MATCHING_MEIER = "Meier"; // A keyword that matches MEIER

    private TypicalClients() {} // prevents instantiation

    /**
     * Returns an {@code AddressBook} with all the typical clients.
     */
    public static AddressBook getTypicalAddressBook() {
        AddressBook ab = new AddressBook();
        for (Client client : getTypicalClients()) {
            ab.addClient(client);
        }

        return ab;
    }

    public static List<Client> getTypicalClients() {
        return new ArrayList<>(Arrays.asList(ALICE, BENSON, CARL, DANIEL, ELLE, FIONA, GEORGE));
    }
}
