package socialite.model.person;

import static socialite.logic.commands.CommandTestUtil.VALID_NAME_BOB;
import static socialite.logic.commands.CommandTestUtil.VALID_PHONE_BOB;
import static socialite.logic.commands.CommandTestUtil.VALID_TAG_HUSBAND;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import socialite.logic.commands.ShareCommand;
import socialite.testutil.Assert;
import socialite.testutil.PersonBuilder;
import socialite.testutil.TypicalPersons;

public class PersonTest {

    @Test
    public void asObservableList_modifyList_throwsUnsupportedOperationException() {
        Person person = new PersonBuilder().build();
        Assert.assertThrows(UnsupportedOperationException.class, () -> person.getTags().remove(0));
    }

    @Test
    public void isSamePerson() {
        // same object -> returns true
        Assertions.assertTrue(TypicalPersons.ALICE.isSamePerson(TypicalPersons.ALICE));

        // null -> returns false
        Assertions.assertFalse(TypicalPersons.ALICE.isSamePerson(null));

        // same name, all other attributes different -> returns false
        Person editedAlice = new PersonBuilder(TypicalPersons.ALICE)
                .withPhone(VALID_PHONE_BOB)
                .withTags(VALID_TAG_HUSBAND).build();
        Assertions.assertFalse(TypicalPersons.ALICE.isSamePerson(editedAlice));

        // same phone number, all other attributes different -> returns true
        editedAlice = new PersonBuilder(TypicalPersons.ALICE)
                .withName(VALID_NAME_BOB)
                .withTags(VALID_TAG_HUSBAND).build();
        Assertions.assertTrue(TypicalPersons.ALICE.isSamePerson(editedAlice));

        // same name and phone number, all other attributes different -> returns true
        editedAlice = new PersonBuilder(TypicalPersons.ALICE)
                .withTags(VALID_TAG_HUSBAND).build();
        Assertions.assertTrue(TypicalPersons.ALICE.isSamePerson(editedAlice));

        // different name, all other attributes same -> returns true
        editedAlice = new PersonBuilder(TypicalPersons.ALICE).withName(VALID_NAME_BOB).build();
        Assertions.assertTrue(TypicalPersons.ALICE.isSamePerson(editedAlice));

        // different phone number, all other attributes same -> returns false
        editedAlice = new PersonBuilder(TypicalPersons.ALICE).withPhone(VALID_PHONE_BOB).build();
        Assertions.assertFalse(TypicalPersons.ALICE.isSamePerson(editedAlice));

        // name differs in case, all other attributes same -> returns true
        Person editedBob = new PersonBuilder(TypicalPersons.BOB).withName(VALID_NAME_BOB.toLowerCase()).build();
        Assertions.assertTrue(TypicalPersons.BOB.isSamePerson(editedBob));

        // name has trailing spaces, all other attributes same -> returns true
        String nameWithTrailingSpaces = VALID_NAME_BOB + " ";
        editedBob = new PersonBuilder(TypicalPersons.BOB).withName(nameWithTrailingSpaces).build();
        Assertions.assertTrue(TypicalPersons.BOB.isSamePerson(editedBob));
    }


    @Test
    public void testSharingString() {
        Person alice = TypicalPersons.ALICE.copy();
        StringBuilder aliceInfo = new StringBuilder();

        // alice has all social media handles except twitter
        aliceInfo.append("\tPhone: ").append(alice.getPhone());
        aliceInfo.append("\n\tFacebook: ").append(alice.getFacebook().getUrl());
        aliceInfo.append("\n\tInstagram: ").append(alice.getInstagram().getUrl());
        aliceInfo.append("\n\tTelegram: ").append(alice.getTelegram().getUrl());
        aliceInfo.append("\n\tTikTok: ").append(alice.getTiktok().getUrl());
        String expectedStringAlice =
                String.format(ShareCommand.MESSAGE_SHARE_PERSON_TEMPLATE, alice.getName(), aliceInfo.toString());

        Assertions.assertEquals(alice.toSharingString(), expectedStringAlice);


        Person bob = TypicalPersons.BOB.copy();
        StringBuilder bobInfo = new StringBuilder();

        // bob has all social media handles
        bobInfo.append("\tPhone: ").append(bob.getPhone());
        bobInfo.append("\n\tFacebook: ").append(bob.getFacebook().getUrl());
        bobInfo.append("\n\tInstagram: ").append(bob.getInstagram().getUrl());
        bobInfo.append("\n\tTelegram: ").append(bob.getTelegram().getUrl());
        bobInfo.append("\n\tTikTok: ").append(bob.getTiktok().getUrl());
        bobInfo.append("\n\tTwitter: ").append(bob.getTwitter().getUrl());

        String expectedStringBob =
                String.format(ShareCommand.MESSAGE_SHARE_PERSON_TEMPLATE, bob.getName(), bobInfo.toString());

        Assertions.assertEquals(bob.toSharingString(), expectedStringBob);
    }


    @Test
    public void equals() {
        // same values -> returns true
        Person aliceCopy = new PersonBuilder(TypicalPersons.ALICE).build();
        Assertions.assertEquals(TypicalPersons.ALICE, aliceCopy);

        // same object -> returns true
        Assertions.assertEquals(TypicalPersons.ALICE, TypicalPersons.ALICE);

        // null -> returns false
        Assertions.assertNotEquals(null, TypicalPersons.ALICE);

        // different type -> returns false
        Assertions.assertNotEquals(5, TypicalPersons.ALICE);

        // different person -> returns false
        Assertions.assertNotEquals(TypicalPersons.ALICE, TypicalPersons.BOB);

        // different name -> returns false
        Person editedAlice = new PersonBuilder(TypicalPersons.ALICE).withName(VALID_NAME_BOB).build();
        Assertions.assertNotEquals(TypicalPersons.ALICE, editedAlice);

        // different phone -> returns false
        editedAlice = new PersonBuilder(TypicalPersons.ALICE).withPhone(VALID_PHONE_BOB).build();
        Assertions.assertNotEquals(TypicalPersons.ALICE, editedAlice);

        // different tags -> returns false
        editedAlice = new PersonBuilder(TypicalPersons.ALICE).withTags(VALID_TAG_HUSBAND).build();
        Assertions.assertNotEquals(TypicalPersons.ALICE, editedAlice);

        // Person & non-Person -> returns false
        Assertions.assertNotEquals(TypicalPersons.ALICE, 1);
    }
}
