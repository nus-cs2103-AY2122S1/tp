package seedu.address.testutil;

import static seedu.address.logic.commands.CommandTestUtil.VALID_ADDRESS_AMY;
import static seedu.address.logic.commands.CommandTestUtil.VALID_ADDRESS_BOB;
import static seedu.address.logic.commands.CommandTestUtil.VALID_EMAIL_AMY;
import static seedu.address.logic.commands.CommandTestUtil.VALID_EMAIL_BOB;
import static seedu.address.logic.commands.CommandTestUtil.VALID_LP_AMY;
import static seedu.address.logic.commands.CommandTestUtil.VALID_LP_BOB;
import static seedu.address.logic.commands.CommandTestUtil.VALID_NAME_AMY;
import static seedu.address.logic.commands.CommandTestUtil.VALID_NAME_BOB;
import static seedu.address.logic.commands.CommandTestUtil.VALID_PHONE_AMY;
import static seedu.address.logic.commands.CommandTestUtil.VALID_PHONE_BOB;
import static seedu.address.logic.commands.CommandTestUtil.VALID_TAG_FRIEND;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import seedu.address.model.AddressBook;
import seedu.address.model.person.customer.Customer;

/**
 * A utility class containing a list of {@code Customers} objects to be used in tests.
 */
public class TypicalCustomers {

    public static final Customer CUSTOMER_ALICE = new CustomerBuilder().withName("Alice Pauline")
            .withAddress("123, Jurong West Ave 6, #08-111").withEmail("alice@example.com")
            .withPhone("94351253").withLoyaltyPoints("3000").withAllergies("Peanut butter")
            .withSpecialRequests("near toilet")
            .withTags("friends").build();
    public static final Customer CUSTOMER_BENSON = new CustomerBuilder().withName("Benson Meier")
            .withAddress("311, Clementi Ave 2, #02-25").withLoyaltyPoints("3000")
            .withAllergies("Peanut butter")
            .withSpecialRequests("near toilet")
            .withEmail("johnd@example.com").withPhone("98765432")
            .withTags("owesMoney", "friends").build();
    public static final Customer CUSTOMER_CARL = new CustomerBuilder().withName("Carl Kurz").withPhone("95352563")
            .withEmail("heinz@example.com").withAddress("wall street").withLoyaltyPoints("3000")
            .withSpecialRequests("near toilet").build();
    public static final Customer CUSTOMER_DANIEL = new CustomerBuilder().withName("Daniel Meier").withPhone("87652533")
            .withEmail("cornelia@example.com").withAddress("10th street")
            .withLoyaltyPoints("3000").withSpecialRequests("near toilet").withTags("friends").build();
    public static final Customer CUSTOMER_ELLE = new CustomerBuilder().withName("Elle Meyer").withPhone("9482224")
            .withEmail("werner@example.com").withAddress("michegan ave").build();
    public static final Customer CUSTOMER_FIONA = new CustomerBuilder().withName("Fiona Kunz").withPhone("9482427")
            .withEmail("lydia@example.com").withAddress("little tokyo").withLoyaltyPoints("3000")
            .withAllergies("Peanut butter", "butter", "walnuts")
            .withSpecialRequests("near toilet").build();
    public static final Customer CUSTOMER_GEORGE = new CustomerBuilder().withName("George Best").withPhone("9482442")
            .withEmail("anna@example.com").withAddress("4th street")
            .withLoyaltyPoints("3000").withAllergies("Peanut butter")
            .withSpecialRequests("near toilet", "far from kitchen", "no kids").build();

    // Manually added
    public static final Customer CUSTOMER_HOON = new CustomerBuilder().withName("Hoon Meier").withPhone(
            "8482424")
            .withEmail("stefan@example.com").withAddress("little india")
            .withLoyaltyPoints("3000").withAllergies("Peanut butter").build();
    public static final Customer CUSTOMER_IDA = new CustomerBuilder().withName("Ida Mueller").withPhone("8482131")
            .withEmail("hans@example.com").withAddress("chicago ave")
            .withLoyaltyPoints("3000")
            .withSpecialRequests("near toilet").build();

    // Manually added - Person's details found in {@code CommandTestUtil}
    public static final Customer CUSTOMER_AMY = new CustomerBuilder().withName(VALID_NAME_AMY)
            .withPhone(VALID_PHONE_AMY)
            .withEmail(VALID_EMAIL_AMY).withAddress(VALID_ADDRESS_AMY).withLoyaltyPoints(VALID_LP_AMY)
            .withTags(VALID_TAG_FRIEND).build();
    public static final Customer CUSTOMER_BOB = new CustomerBuilder().withName(VALID_NAME_BOB)
            .withPhone(VALID_PHONE_BOB)
            .withEmail(VALID_EMAIL_BOB).withAddress(VALID_ADDRESS_BOB)
            .withLoyaltyPoints(VALID_LP_BOB)
            .build();

    public static final String KEYWORD_MATCHING_CUSTOMER_MEIER = "CUSTOMER_Meier"; // A keyword that matches MEIER

    private TypicalCustomers() {} // prevents instantiation

    /**
     * Returns an {@code AddressBook} with all the typical customers.
     */
    public static AddressBook getTypicalAddressBook() {
        AddressBook ab = new AddressBook();
        for (Customer customer : getTypicalCustomers()) {
            ab.addCustomer(customer);
        }
        return ab;
    }

    public static List<Customer> getTypicalCustomers() {
        return new ArrayList<>(Arrays.asList(CUSTOMER_ALICE, CUSTOMER_BENSON, CUSTOMER_CARL,
                CUSTOMER_DANIEL, CUSTOMER_ELLE, CUSTOMER_FIONA, CUSTOMER_GEORGE));
    }
}
