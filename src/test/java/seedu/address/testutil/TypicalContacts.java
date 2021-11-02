package seedu.address.testutil;

import static seedu.address.logic.commands.CommandTestUtil.VALID_ADDRESS_AMY;
import static seedu.address.logic.commands.CommandTestUtil.VALID_ADDRESS_BOB;
import static seedu.address.logic.commands.CommandTestUtil.VALID_CATEGORY_CODE_ATT;
import static seedu.address.logic.commands.CommandTestUtil.VALID_CATEGORY_CODE_OTH;
import static seedu.address.logic.commands.CommandTestUtil.VALID_EMAIL_AMY;
import static seedu.address.logic.commands.CommandTestUtil.VALID_EMAIL_BOB;
import static seedu.address.logic.commands.CommandTestUtil.VALID_NAME_AMY;
import static seedu.address.logic.commands.CommandTestUtil.VALID_NAME_BOB;
import static seedu.address.logic.commands.CommandTestUtil.VALID_PHONE_AMY;
import static seedu.address.logic.commands.CommandTestUtil.VALID_PHONE_BOB;
import static seedu.address.logic.commands.CommandTestUtil.VALID_RATING_AMY;
import static seedu.address.logic.commands.CommandTestUtil.VALID_RATING_BOB;
import static seedu.address.logic.commands.CommandTestUtil.VALID_REVIEW_AMY;
import static seedu.address.logic.commands.CommandTestUtil.VALID_REVIEW_BOB;
import static seedu.address.logic.commands.CommandTestUtil.VALID_TAG_FRIEND;
import static seedu.address.logic.commands.CommandTestUtil.VALID_TAG_HUSBAND;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import seedu.address.model.AddressBook;
import seedu.address.model.contact.Contact;

/**
 * A utility class containing a list of {@code Contact} objects to be used in tests.
 */
public class TypicalContacts {

    public static final Contact ALICE = new ContactBuilder().withCategoryCode("att").withName("Alice Pauline")
            .withAddress("123, Jurong West Ave 6, #08-111").withEmail("alice@example.com")
            .withPhone("94351253").withReview("great")
            .withTags("friends").withRating("3").build();
    public static final Contact BENSON = new ContactBuilder().withCategoryCode("oth").withName("Benson Meier")
            .withAddress("311, Clementi Ave 2, #02-25")
            .withEmail("johnd@example.com").withPhone("98765432")
            .withTags("owesMoney", "friends").withReview("amazing").withRating("4").build();
    public static final Contact CARL = new ContactBuilder().withCategoryCode("acc").withName("Carl Kurz")
            .withPhone("95352563").withEmail("heinz@example.com").withAddress("wall street")
            .withReview("bad").withRating("2").build();
    public static final Contact DANIEL = new ContactBuilder().withCategoryCode("tpt").withName("Daniel Meier")
            .withPhone("87652533").withEmail("cornelia@example.com").withAddress("10th street")
            .withReview("great").withTags("friends").withRating("5").build();
    public static final Contact ELLE = new ContactBuilder().withCategoryCode("att").withName("Elle Meyer")
            .withReview("great").withPhone("9482224")
            .withEmail("werner@example.com").withAddress("michegan ave").withRating("5").build();
    public static final Contact FIONA = new ContactBuilder().withCategoryCode("com").withName("Fiona Kunz")
            .withReview("great").withPhone("9482427").withEmail("lydia@example.com")
            .withAddress("little tokyo").withRating("5").build();
    public static final Contact GEORGE = new ContactBuilder().withCategoryCode("fnb").withName("George Best")
            .withPhone("9482442").withEmail("anna@example.com").withAddress("4th street").withReview("great")
            .withRating("2").build();
    public static final Contact JANE = new ContactBuilder().withCategoryCode("com").withName("Jane Parker")
            .withPhone("90477602").withEmail("jane@example.com").withAddress("23rd street").withReview("beautiful")
            .withRating("5").build();

    // Manually added
    public static final Contact HOON = new ContactBuilder().withCategoryCode("att").withName("Hoon Meier")
            .withPhone("8482424").withEmail("stefan@example.com").withAddress("little india").withReview("great")
            .withRating("4").build();
    public static final Contact IDA = new ContactBuilder().withCategoryCode("oth").withName("Ida Mueller")
            .withPhone("8482131").withEmail("hans@example.com").withAddress("chicago ave").withReview("bad")
            .withRating("2").build();

    // Manually added - Contact's details found in {@code CommandTestUtil}
    public static final Contact AMY = new ContactBuilder().withCategoryCode(VALID_CATEGORY_CODE_ATT)
            .withName(VALID_NAME_AMY).withPhone(VALID_PHONE_AMY).withEmail(VALID_EMAIL_AMY)
            .withAddress(VALID_ADDRESS_AMY).withReview(VALID_REVIEW_AMY).withTags(VALID_TAG_FRIEND)
            .withRating(VALID_RATING_AMY).build();
    public static final Contact BOB = new ContactBuilder().withCategoryCode(VALID_CATEGORY_CODE_OTH)
            .withName(VALID_NAME_BOB).withPhone(VALID_PHONE_BOB).withEmail(VALID_EMAIL_BOB)
            .withAddress(VALID_ADDRESS_BOB).withReview(VALID_REVIEW_BOB).withTags(VALID_TAG_HUSBAND, VALID_TAG_FRIEND)
            .withRating(VALID_RATING_BOB).build();

    public static final String KEYWORD_MATCHING_MEIER = "Meier"; // A keyword that matches MEIER

    private TypicalContacts() {} // prevents instantiation

    /**
     * Returns an {@code AddressBook} with all the typical contacts.
     */
    public static AddressBook getTypicalAddressBook() {
        AddressBook ab = new AddressBook();
        for (Contact contact : getTypicalContacts()) {
            ab.addContact(contact);
        }
        return ab;
    }

    public static AddressBook getRandomTypicalAddressBook() {
        AddressBook ab = new AddressBook();
        for (Contact contact : getRandomTypicalContacts()) {
            ab.addContact(contact);
        }
        return ab;
    }

    public static List<Contact> getTypicalContacts() {
        return new ArrayList<>(Arrays.asList(ALICE, BENSON, CARL, DANIEL, ELLE, FIONA, GEORGE, JANE));
    }

    public static List<Contact> getRandomTypicalContacts() {
        return new ArrayList<>(Arrays.asList(CARL, DANIEL, GEORGE, BENSON, JANE, ELLE, ALICE, FIONA));
    }
}
