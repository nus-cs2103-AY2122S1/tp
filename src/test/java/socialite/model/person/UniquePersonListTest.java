package socialite.model.person;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static socialite.logic.commands.CommandTestUtil.VALID_NAME_BOB;
import static socialite.logic.commands.CommandTestUtil.VALID_PHONE_BOB;
import static socialite.logic.commands.CommandTestUtil.VALID_TAG_HUSBAND;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import org.junit.jupiter.api.Test;

import socialite.model.person.exceptions.DuplicatePersonException;
import socialite.model.person.exceptions.PersonNotFoundException;
import socialite.testutil.Assert;
import socialite.testutil.PersonBuilder;
import socialite.testutil.TypicalPersons;

public class UniquePersonListTest {

    private final UniquePersonList uniquePersonList = new UniquePersonList();

    @Test
    public void contains_nullPerson_throwsNullPointerException() {
        Assert.assertThrows(NullPointerException.class, () -> uniquePersonList.contains(null));
    }

    @Test
    public void contains_personNotInList_returnsFalse() {
        assertFalse(uniquePersonList.contains(TypicalPersons.ALICE));
    }

    @Test
    public void contains_personInList_returnsTrue() {
        uniquePersonList.add(TypicalPersons.ALICE);
        assertTrue(uniquePersonList.contains(TypicalPersons.ALICE));
    }

    @Test
    public void contains_personWithSameIdentityFieldsInList_returnsTrue() {
        uniquePersonList.add(TypicalPersons.ALICE);
        Person editedAlice = new PersonBuilder(TypicalPersons.ALICE)
                .withTags(VALID_TAG_HUSBAND)
                .build();
        assertTrue(uniquePersonList.contains(editedAlice));
    }

    @Test
    public void contains_personWithSameNameInList_returnsFalse() {
        uniquePersonList.add(TypicalPersons.ALICE);
        Person editedAlice = new PersonBuilder(TypicalPersons.ALICE)
                .withPhone(VALID_PHONE_BOB)
                .withTags(VALID_TAG_HUSBAND)
                .build();
        assertFalse(uniquePersonList.contains(editedAlice));
    }

    @Test
    public void contains_personWithSamePhoneNumberInList_returnsTrue() {
        uniquePersonList.add(TypicalPersons.ALICE);
        Person editedAlice = new PersonBuilder(TypicalPersons.ALICE)
                .withName(VALID_NAME_BOB)
                .withTags(VALID_TAG_HUSBAND)
                .build();
        assertTrue(uniquePersonList.contains(editedAlice));
    }

    @Test
    public void add_nullPerson_throwsNullPointerException() {
        Assert.assertThrows(NullPointerException.class, () -> uniquePersonList.add(null));
    }

    @Test
    public void add_duplicatePerson_throwsDuplicatePersonException() {
        uniquePersonList.add(TypicalPersons.ALICE);
        Assert.assertThrows(DuplicatePersonException.class, () -> uniquePersonList.add(TypicalPersons.ALICE));
    }

    @Test
    public void add_personWithSamePhone_throwsDuplicatePersonException() {
        uniquePersonList.add(TypicalPersons.ALICE);
        Person bobWithAlicePhone = new PersonBuilder(TypicalPersons.BOB)
                .withPhone(TypicalPersons.ALICE.getPhone().value)
                .build();
        Assert.assertThrows(DuplicatePersonException.class, ()
            -> uniquePersonList.add(bobWithAlicePhone));
    }

    @Test
    public void add_personWithSameName_success() {
        uniquePersonList.add(TypicalPersons.ALICE);
        Person bobWithAliceName = new PersonBuilder(TypicalPersons.BOB)
                .withName(TypicalPersons.ALICE.getName().toString())
                .build();
        uniquePersonList.add(bobWithAliceName);
        UniquePersonList expectedUniquePersonList = new UniquePersonList();
        expectedUniquePersonList.add(TypicalPersons.ALICE);
        expectedUniquePersonList.add(bobWithAliceName);
        assertEquals(uniquePersonList, expectedUniquePersonList);
    }

    @Test
    public void setPerson_nullTargetPerson_throwsNullPointerException() {
        Assert.assertThrows(
                NullPointerException.class, () -> uniquePersonList.setPerson(null, TypicalPersons.ALICE)
        );
    }

    @Test
    public void setPerson_nullEditedPerson_throwsNullPointerException() {
        Assert.assertThrows(NullPointerException.class, ()
            -> uniquePersonList.setPerson(TypicalPersons.ALICE, null));
    }

    @Test
    public void setPerson_targetPersonNotInList_throwsPersonNotFoundException() {
        Assert.assertThrows(PersonNotFoundException.class, ()
            -> uniquePersonList.setPerson(TypicalPersons.ALICE, TypicalPersons.ALICE));
    }

    @Test
    public void setPerson_editedPersonNotInListIsSamePerson_success() {
        uniquePersonList.add(TypicalPersons.ALICE);
        uniquePersonList.setPerson(TypicalPersons.ALICE, TypicalPersons.ALICE);
        UniquePersonList expectedUniquePersonList = new UniquePersonList();
        expectedUniquePersonList.add(TypicalPersons.ALICE);
        assertEquals(expectedUniquePersonList, uniquePersonList);
    }

    @Test
    public void setPerson_editedPersonNotInListHasSameIdentity_success() {
        uniquePersonList.add(TypicalPersons.ALICE);
        Person editedAlice = new PersonBuilder(TypicalPersons.ALICE)
                .withTags(VALID_TAG_HUSBAND)
                .build();
        uniquePersonList.setPerson(TypicalPersons.ALICE, editedAlice);
        UniquePersonList expectedUniquePersonList = new UniquePersonList();
        expectedUniquePersonList.add(editedAlice);
        assertEquals(expectedUniquePersonList, uniquePersonList);
    }

    @Test
    public void setPerson_editedPersonNotInListHasDifferentIdentity_success() {
        uniquePersonList.add(TypicalPersons.ALICE);
        uniquePersonList.setPerson(TypicalPersons.ALICE, TypicalPersons.BOB);
        UniquePersonList expectedUniquePersonList = new UniquePersonList();
        expectedUniquePersonList.add(TypicalPersons.BOB);
        assertEquals(expectedUniquePersonList, uniquePersonList);
    }

    @Test
    public void setPerson_editedPersonNotInListHasSamePhone_success() {
        uniquePersonList.add(TypicalPersons.ALICE);
        Person bobWithAlicePhone = new PersonBuilder(TypicalPersons.BOB)
                .withPhone(TypicalPersons.ALICE.getPhone().toString()).build();
        uniquePersonList.setPerson(TypicalPersons.ALICE, bobWithAlicePhone);
        UniquePersonList expectedUniquePersonList = new UniquePersonList();
        expectedUniquePersonList.add(bobWithAlicePhone);
        assertEquals(expectedUniquePersonList, uniquePersonList);
    }

    @Test
    public void setPerson_editedPersonExistsInList_throwsDuplicatePersonException() {
        uniquePersonList.add(TypicalPersons.ALICE);
        uniquePersonList.add(TypicalPersons.BOB);
        Assert.assertThrows(DuplicatePersonException.class, ()
            -> uniquePersonList.setPerson(TypicalPersons.ALICE, TypicalPersons.BOB));
    }

    @Test
    public void setPerson_editedPersonExistsInListWithDiffPhone_throwsDuplicatePersonException() {
        uniquePersonList.add(TypicalPersons.ALICE);
        Person differentAlice = new PersonBuilder(TypicalPersons.ALICE)
                .withPhone(VALID_PHONE_BOB)
                .build();
        uniquePersonList.add(differentAlice);
        Assert.assertThrows(DuplicatePersonException.class, ()
            -> uniquePersonList.setPerson(TypicalPersons.ALICE, differentAlice));
    }

    @Test
    // Amy and differentAmy (who has Bob's phone number) already exist in the list.
    // Attempting to set Amy with Bob's attributes will throw a DuplicatePersonException as differentAmy
    // has Bob's phone number as her attribute.
    public void setPerson_editedPersonHasSamePhoneNumber_throwsDuplicatePersonException() {
        uniquePersonList.add(TypicalPersons.AMY);
        Person differentAmy = new PersonBuilder(TypicalPersons.AMY)
                .withPhone(VALID_PHONE_BOB)
                .build();
        uniquePersonList.add(differentAmy);
        Assert.assertThrows(DuplicatePersonException.class, ()
            -> uniquePersonList.setPerson(TypicalPersons.AMY, TypicalPersons.BOB));
    }

    @Test
    public void remove_nullPerson_throwsNullPointerException() {
        Assert.assertThrows(NullPointerException.class, () -> uniquePersonList.remove(null));
    }

    @Test
    public void remove_personDoesNotExist_throwsPersonNotFoundException() {
        Assert.assertThrows(PersonNotFoundException.class, () -> uniquePersonList.remove(TypicalPersons.ALICE));
    }

    @Test
    public void remove_existingPerson_removesPerson() {
        uniquePersonList.add(TypicalPersons.ALICE);
        uniquePersonList.remove(TypicalPersons.ALICE);
        UniquePersonList expectedUniquePersonList = new UniquePersonList();
        assertEquals(expectedUniquePersonList, uniquePersonList);
    }

    @Test
    public void setPersons_nullUniquePersonList_throwsNullPointerException() {
        Assert.assertThrows(NullPointerException.class, () -> uniquePersonList.setPersons((UniquePersonList) null));
    }

    @Test
    public void setPersons_uniquePersonList_replacesOwnListWithProvidedUniquePersonList() {
        uniquePersonList.add(TypicalPersons.ALICE);
        UniquePersonList expectedUniquePersonList = new UniquePersonList();
        expectedUniquePersonList.add(TypicalPersons.BOB);
        uniquePersonList.setPersons(expectedUniquePersonList);
        assertEquals(expectedUniquePersonList, uniquePersonList);
    }

    @Test
    public void setPersons_nullList_throwsNullPointerException() {
        Assert.assertThrows(NullPointerException.class, () -> uniquePersonList.setPersons((List<Person>) null));
    }

    @Test
    public void setPersons_list_replacesOwnListWithProvidedList() {
        uniquePersonList.add(TypicalPersons.ALICE);
        List<Person> personList = Collections.singletonList(TypicalPersons.BOB);
        uniquePersonList.setPersons(personList);
        UniquePersonList expectedUniquePersonList = new UniquePersonList();
        expectedUniquePersonList.add(TypicalPersons.BOB);
        assertEquals(expectedUniquePersonList, uniquePersonList);
    }

    @Test
    public void setPersons_listWithDuplicatePersons_throwsDuplicatePersonException() {
        List<Person> listWithDuplicatePersons = Arrays.asList(TypicalPersons.ALICE, TypicalPersons.ALICE);
        Assert.assertThrows(DuplicatePersonException.class, ()
            -> uniquePersonList.setPersons(listWithDuplicatePersons));
    }

    @Test
    public void asUnmodifiableObservableList_modifyList_throwsUnsupportedOperationException() {
        Assert.assertThrows(UnsupportedOperationException.class, ()
            -> uniquePersonList.asUnmodifiableObservableList().remove(0));
    }
}
