package seedu.address.testutil;

import static seedu.address.logic.commands.CommandTestUtil.VALID_MODULE_AMY;
import static seedu.address.logic.commands.CommandTestUtil.VALID_MODULE_BOB;
import static seedu.address.logic.commands.CommandTestUtil.VALID_EMAIL_AMY;
import static seedu.address.logic.commands.CommandTestUtil.VALID_EMAIL_BOB;
import static seedu.address.logic.commands.CommandTestUtil.VALID_NAME_AMY;
import static seedu.address.logic.commands.CommandTestUtil.VALID_NAME_BOB;
import static seedu.address.logic.commands.CommandTestUtil.VALID_TAG_FRIEND;
import static seedu.address.logic.commands.CommandTestUtil.VALID_TAG_HUSBAND;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import seedu.address.model.AddressBook;
import seedu.address.model.person.Person;

/**
 * A utility class containing a list of {@code Person} objects to be used in tests.
 */
public class TypicalPersons {

    public static final Person ALICE = new PersonBuilder().withName("Alice Pauline")
            .withEmail("alice@example.com")
            .withModule("CS2103T")
            .withTags("friends").build();
    public static final Person BENSON = new PersonBuilder().withName("Benson Meier")
            .withEmail("johnd@example.com")
            .withModule("CS1101S")
            .withAssignmentList(new String[] {"Assignment 2", "15 Oct 2021, 01:00 PM", "PENDING"},
                    new String[] {"Homework 1", "13 Sep 2021, 11:59 PM", "COMPLETED"})
            .withTags("owesMoney", "friends").build();
    public static final Person CARL = new PersonBuilder().withName("Carl Kurz")
            .withEmail("heinz@example.com")
            .withModule("IS2101")
            .withAssignmentList(new String[] {"iP", "17 Sep 2021, 11:59 PM", "COMPLETED"},
                    new String[] {"Assignment 2", "15 Oct 2021, 01:00 PM", "PENDING"}).build();
    public static final Person DANIEL = new PersonBuilder().withName("Daniel Meier")
            .withEmail("cornelia@example.com")
            .withModule("CM1102")
            .withAssignmentList(new String[] {"Homework 1", "13 Sep 2021, 11:59 PM", "COMPLETED"})
            .withTags("friends").build();
    public static final Person ELLE = new PersonBuilder().withName("Elle Meyer")
            .withEmail("werner@example.com")
            .withModule("GER1000")
            .withAssignmentList(new String[] {"Tutorial 2", "16 Sep 2021, 11:59 PM", "COMPLETED"}).build();
    public static final Person FIONA = new PersonBuilder().withName("Fiona Kunz")
            .withEmail("lydia@example.com")
            .withModule("GET1031")
            .withAssignmentList(new String[] {"Assignment 1", "10 Sep 2021, 11:59 PM", "COMPLETED"},
                    new String[] {"Assignment 2", "11 Sep 2021, 11:58 PM", "COMPLETED"},
                    new String[] {"Lab 3", "12 Nov 2021, 06:00 PM", "PENDING"})
            .build();
    public static final Person GEORGE = new PersonBuilder().withName("George Best")
            .withEmail("anna@example.com")
            .withModule("MA1101R")
            .withAssignmentList(new String[] {"Lab 1", "20 Sep 2021, 11:59 PM", "COMPLETED"}).build();

    // Manually added
    public static final Person HOON = new PersonBuilder().withName("Hoon Meier")
            .withEmail("stefan@example.com").withModule("CP2106")
            .withAssignmentList(new String[] {"Lab 1", "20 Sep 2021, 11:59 PM", "COMPLETED"}).build();
    public static final Person IDA = new PersonBuilder().withName("Ida Mueller")
            .withEmail("hans@example.com").withModule("EG2201A")
            .withAssignmentList(new String[] {"iP", "17 Sep 2021, 11:59 PM", "COMPLETED"}).build();

    // Manually added - Person's details found in {@code CommandTestUtil}
    public static final Person AMY = new PersonBuilder().withName(VALID_NAME_AMY)
            .withEmail(VALID_EMAIL_AMY).withModule(VALID_MODULE_AMY).withTags(VALID_TAG_FRIEND).build();
    public static final Person BOB = new PersonBuilder().withName(VALID_NAME_BOB)
            .withEmail(VALID_EMAIL_BOB).withModule(VALID_MODULE_BOB).withTags(VALID_TAG_HUSBAND, VALID_TAG_FRIEND)
            .build();

    public static final String KEYWORD_MATCHING_MEIER = "Meier"; // A keyword that matches MEIER

    private TypicalPersons() {} // prevents instantiation

    /**
     * Returns an {@code AddressBook} with all the typical persons.
     */
    public static AddressBook getTypicalAddressBook() {
        AddressBook ab = new AddressBook();
        for (Person person : getTypicalPersons()) {
            ab.addPerson(person);
        }
        return ab;
    }

    public static List<Person> getTypicalPersons() {
        return new ArrayList<>(Arrays.asList(ALICE, BENSON, CARL, DANIEL, ELLE, FIONA, GEORGE));
    }
}
