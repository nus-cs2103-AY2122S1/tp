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

    public static final Contact AIRZONE = new ContactBuilder().withCategoryCode("att").withName("AIRZONE")
            .withAddress("123, Jurong West Ave 6, #08-111").withEmail("airzone@example.com")
            .withPhone("94351253").withReview("great")
            .withTags("friends", "new").withRating("3").build();
    public static final Contact BATTLEBOX = new ContactBuilder().withCategoryCode("oth").withName("Battlebox Museum")
            .withAddress("311, Clementi Ave 2, #02-25")
            .withEmail("battlebox@example.com").withPhone("98765432")
            .withTags("fun", "friends").withReview("amazing").withRating("4").build();
    public static final Contact CARLTON = new ContactBuilder().withCategoryCode("acc").withName("Carlton Hotel")
            .withPhone("95352563").withEmail("carlton@example.com").withAddress("wall street")
            .withReview("bad").withRating("2").build();
    public static final Contact FUKTAKCHI = new ContactBuilder().withCategoryCode("oth").withName("Fuk Tak Chi Museum")
            .withPhone("87652533").withEmail("fuktakchi@example.com").withAddress("10th street")
            .withReview("great").withTags("friends").withRating("5").build();
    public static final Contact GSEA = new ContactBuilder().withCategoryCode("att")
            .withName("Gogreen Segway Eco Adventure")
            .withReview("great").withPhone("9482224")
            .withEmail("GSEA@example.com").withAddress("michegan ave").withRating("5").build();
    public static final Contact HOTEL_SOLOHA = new ContactBuilder().withCategoryCode("com").withName("Hotel Soloha")
            .withReview("great").withPhone("9482427").withEmail("soloha@example.com")
            .withAddress("little tokyo").withRating("5").build();
    public static final Contact ICHIBAN = new ContactBuilder().withCategoryCode("fnb").withName("Ichiban Boshi")
            .withPhone("9482442").withEmail("ichiban@example.com").withAddress("4th street").withReview("great")
            .withRating("2").build();
    public static final Contact JCUBE = new ContactBuilder().withCategoryCode("com").withName("JCube")
            .withPhone("90477602").withEmail("jcube@example.com").withAddress("23rd street").withReview("beautiful")
            .withRating("5").build();

    // Manually added
    public static final Contact MARITIME = new ContactBuilder().withCategoryCode("att")
            .withName("Maritime Experiential Museum")
            .withPhone("8482424").withEmail("maritime@example.com").withAddress("little india").withReview("great")
            .withRating("4").build();
    public static final Contact MUSTAFA = new ContactBuilder().withCategoryCode("oth").withName("Mustafa Centre")
            .withPhone("8482131").withEmail("mustafa@example.com").withAddress("chicago ave").withReview("bad")
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

    /**
     * Returns an {@code AddressBook} with randomised typical contacts.
     */
    public static AddressBook getRandomTypicalAddressBook() {
        AddressBook ab = new AddressBook();
        for (Contact contact : getRandomTypicalContacts()) {
            ab.addContact(contact);
        }
        return ab;
    }

    public static List<Contact> getTypicalContacts() {
        return new ArrayList<>(Arrays.asList(AIRZONE, BATTLEBOX, CARLTON, FUKTAKCHI,
                GSEA, HOTEL_SOLOHA, ICHIBAN, JCUBE));
    }

    public static List<Contact> getRandomTypicalContacts() {
        return new ArrayList<>(Arrays.asList(CARLTON, FUKTAKCHI, ICHIBAN, BATTLEBOX,
                JCUBE, GSEA, AIRZONE, HOTEL_SOLOHA));
    }
}
