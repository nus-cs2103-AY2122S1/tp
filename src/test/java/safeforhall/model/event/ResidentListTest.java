package safeforhall.model.event;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static safeforhall.testutil.Assert.assertThrows;

import java.util.ArrayList;

import org.junit.jupiter.api.Test;

import safeforhall.logic.parser.exceptions.ParseException;
import safeforhall.model.person.Person;
import safeforhall.testutil.TypicalPersons;

public class ResidentListTest {
    @Test
    public void constructor_null_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> new ResidentList(null));
    }

    @Test
    public void constructor_nameRoomConflict_throwsIllegalArgumentException() {
        assertThrows(IllegalArgumentException.class, () -> new ResidentList("Peter, a213")); // name and room
    }

    @Test
    public void isValidResidentList() throws ParseException {
        // null residentList
        assertThrows(NullPointerException.class, () -> ResidentList.isValidResidentList(null));

        // invalid residentList
        assertFalse(ResidentList.isValidResidentList("")); // empty string
        assertFalse(ResidentList.isValidResidentList(" ")); // spaces only
        assertFalse(ResidentList.isValidResidentList("a213 b423")); // no comma between information

        // valid residentList
        assertTrue(ResidentList.isValidResidentList(ResidentList.DEFAULT_LIST)); // default no residents string
        assertTrue(ResidentList.isValidResidentList("peter jack")); // alphabets only
        assertTrue(ResidentList.isValidResidentList("Capital Tan")); // with capital letters
        assertTrue(ResidentList.isValidResidentList("peter jack, Capital Tan")); // more than one name
        assertTrue(ResidentList.isValidResidentList("peter jack,Capital Tan")); // comma no space
        assertTrue(ResidentList.isValidResidentList("a213")); // rooms only
        assertTrue(ResidentList.isValidResidentList("A213")); // rooms capital
        assertTrue(ResidentList.isValidResidentList("A213, b423")); // more than one room
    }

    @Test
    public void hasUnvaccinatedResident() {
        ResidentList emptyResidentList = new ResidentList(ResidentList.DEFAULT_LIST);
        assertFalse(emptyResidentList.hasUnvaccinatedResident());
    }

    @Test
    public void numOfUnvaccinatedResidents() {
        ResidentList residentList = new ResidentList(ResidentList.DEFAULT_LIST);

        assertEquals(0, residentList.numOfUnvaccinatedResidents());
    }

    @Test
    public void isValidResidentStorage() {
        // null residentStorage
        assertThrows(NullPointerException.class, () -> ResidentList.isValidResidentStorage(null));

        // invalid residentStorage
        assertFalse(ResidentList.isValidResidentStorage("")); // empty string
        assertFalse(ResidentList.isValidResidentStorage(" ")); // spaces only
        assertFalse(ResidentList.isValidResidentStorage("David Li; Room: C112; Phone: 91031282; "
                + "Email: lidavid@example.com; Vaccinated: T; "
                + "Faculty: SDE; Last Fet Date: 02-10-2021;")); // missing a field
        assertFalse(ResidentList.isValidResidentStorage("Alex Yeoh; Room: E417; Phone: 87438807; "
                + "Email: alexyeoh@example.com; Vaccinated: T; Faculty: SOC; Last Fet Date: 01-10-2021; "
                + "Last Collection Date: 10-10-2021 Bernice Yu; Room: A213; Phone: 99272758; "
                + "Email: berniceyu@example.com; Vaccinated: F; Faculty: FASS; Last Fet Date: 10-10-2021; "
                + "Last Collection Date: 11-10-2021peter jack,Capital Tan")); // no comma between information

        // valid residentStorage
        assertTrue(ResidentList.isValidResidentStorage("None")); // default no residents string
        assertTrue(ResidentList.isValidResidentStorage("David Li; Room: C112; Phone: 91031282; "
                + "Email: lidavid@example.com; Vaccinated: T; Faculty: SDE; Last Fet Date: 02-10-2021; "
                + "Last Collection Date: 01-10-2021")); // single entry
        assertTrue(ResidentList.isValidResidentStorage("Alex Yeoh; Room: E417; Phone: 87438807; "
                + "Email: alexyeoh@example.com; Vaccinated: T; Faculty: SOC; Last Fet Date: 01-10-2021; "
                + "Last Collection Date: 10-10-2021, Bernice Yu; Room: A213; Phone: 99272758; "
                + "Email: berniceyu@example.com; Vaccinated: F; Faculty: FASS; Last Fet Date: 10-10-2021; "
                + "Last Collection Date: 11-10-2021, David Li; Room: C112; Phone: 91031282; "
                + "Email: lidavid@example.com; Vaccinated: T; Faculty: SDE; Last Fet Date: 02-10-2021; "
                + "Last Collection Date: 01-10-2021")); // multiple entries
        assertTrue(ResidentList.isValidResidentStorage("david li; room: C112; phone: 91031282; "
                + "Email: lidavid@example.com; vaccinated: T; faculty: SDE; last fet date: 02-10-2021; "
                + "last collection date: 01-10-2021")); // in lower case
        assertTrue(ResidentList.isValidResidentStorage("Alex Yeoh; Room: E417; Phone: 87438807; "
                + "Email: alexyeoh@example.com; Vaccinated: T; Faculty: SOC; Last Fet Date: 01-10-2021; "
                + "Last Collection Date: 10-10-2021,Bernice Yu; Room: A213; Phone: 99272758; "
                + "Email: berniceyu@example.com; Vaccinated: F; Faculty: FASS; Last Fet Date: 10-10-2021; "
                + "Last Collection Date: 11-10-2021")); // comma no space
        assertTrue(ResidentList.isValidResidentStorage("David Li;Room:C112;Phone:91031282;"
                + "Email: lidavid@example.com;Vaccinated:T;Faculty:SDE;Last Fet Date: 02-10-2021;"
                + "Last Collection Date: 01-10-2021")); // colon and semicolon no space
    }

    @Test
    public void getCombinedStorageStringTest() {
        ResidentList residentList = new ResidentList(ResidentList.DEFAULT_LIST);
        ArrayList<Person> toAdd = new ArrayList<>();
        toAdd.add(TypicalPersons.ALICE);

        // empty current, one person in toAdd
        String combinedString = residentList.getCombinedStorageString(toAdd);
        assertEquals(combinedString, TypicalPersons.ALICE.toString());

        // empty current, multiple persons in toAdd
        toAdd.add(TypicalPersons.BOB);
        toAdd.add(TypicalPersons.CARL);
        combinedString = residentList.getCombinedStorageString(toAdd);
        String expectedString = TypicalPersons.ALICE.toString()
                + ", "
                + TypicalPersons.BOB.toString()
                + ", "
                + TypicalPersons.CARL.toString();
        assertEquals(combinedString, expectedString);

        // current not empty
        residentList = new ResidentList(TypicalPersons.ELLE.getName().toString(), TypicalPersons.ELLE.toString());
        combinedString = residentList.getCombinedStorageString(toAdd);
        expectedString = TypicalPersons.ELLE.toString()
                + ", "
                + TypicalPersons.ALICE.toString()
                + ", "
                + TypicalPersons.BOB.toString()
                + ", "
                + TypicalPersons.CARL.toString();
        assertEquals(combinedString, expectedString);
    }

    @Test
    public void getCombinedDisplayStringTest() {
        ResidentList residentList = new ResidentList(ResidentList.DEFAULT_LIST);
        ArrayList<Person> toAdd = new ArrayList<>();

        // empty current, no one added
        String combinedString = residentList.getCombinedDisplayString(toAdd);
        assertEquals(combinedString, ResidentList.DEFAULT_LIST);

        // empty current, one person in toAdd
        toAdd.add(TypicalPersons.ALICE);
        combinedString = residentList.getCombinedDisplayString(toAdd);
        assertEquals(combinedString, TypicalPersons.ALICE.getName().toString());

        // empty current, multiple persons in toAdd
        toAdd.add(TypicalPersons.BOB);
        toAdd.add(TypicalPersons.CARL);
        combinedString = residentList.getCombinedDisplayString(toAdd);
        String expectedString = TypicalPersons.ALICE.getName().toString()
                + ", "
                + TypicalPersons.BOB.getName().toString()
                + ", "
                + TypicalPersons.CARL.getName().toString();
        assertEquals(combinedString, expectedString);

        // current not empty
        residentList = new ResidentList(TypicalPersons.ELLE.getName().toString(), TypicalPersons.ELLE.toString());
        combinedString = residentList.getCombinedDisplayString(toAdd);
        expectedString = TypicalPersons.ELLE.getName().toString()
                + ", "
                + TypicalPersons.ALICE.getName().toString()
                + ", "
                + TypicalPersons.BOB.getName().toString()
                + ", "
                + TypicalPersons.CARL.getName().toString();
        assertEquals(combinedString, expectedString);
    }

    @Test
    public void getRemovedStorageStringTest() {
        ResidentList residentList = new ResidentList(TypicalPersons.ALICE.getName().toString(),
                TypicalPersons.ALICE.toString());
        ArrayList<Person> toRemove = new ArrayList<>();
        toRemove.add(TypicalPersons.ALICE);

        // one resident in current, remove one of them
        String combinedString = residentList.getRemovedStorageString(toRemove);
        assertEquals(combinedString, ResidentList.DEFAULT_LIST);

        // two residents in current, remove one of them
        String constructorString1 = TypicalPersons.ALICE.getName().toString()
                + ", "
                + TypicalPersons.BOB.getName().toString();
        String constructorString2 = TypicalPersons.ALICE.toString()
                + ", "
                + TypicalPersons.BOB.toString();
        residentList = new ResidentList(constructorString1, constructorString2);
        combinedString = residentList.getRemovedStorageString(toRemove);
        assertEquals(combinedString, TypicalPersons.BOB.toString());

        // three residents in current, remove one of them
        constructorString1 = TypicalPersons.ALICE.getName().toString()
                + ", "
                + TypicalPersons.BOB.getName().toString()
                + ", "
                + TypicalPersons.CARL.getName().toString();
        constructorString2 = TypicalPersons.ALICE.toString()
                + ", "
                + TypicalPersons.BOB.toString()
                + ", "
                + TypicalPersons.CARL.toString();
        residentList = new ResidentList(constructorString1, constructorString2);
        combinedString = residentList.getRemovedStorageString(toRemove);
        assertEquals(combinedString, TypicalPersons.BOB.toString() + ", "
                + TypicalPersons.CARL.toString());

        // three residents in current, remove three of them
        toRemove.add(TypicalPersons.BOB);
        toRemove.add(TypicalPersons.CARL);

        residentList = new ResidentList(constructorString1, constructorString2);
        combinedString = residentList.getRemovedStorageString(toRemove);
        assertEquals(combinedString, ResidentList.DEFAULT_LIST);
    }

    @Test
    public void getRemovedDisplayStringTest() {
        ResidentList residentList = new ResidentList(TypicalPersons.ALICE.getName().toString(),
                TypicalPersons.ALICE.toString());
        ArrayList<Person> toRemove = new ArrayList<>();
        toRemove.add(TypicalPersons.ALICE);

        // one resident in current, remove one of them
        String combinedString = residentList.getRemovedDisplayString(toRemove);
        assertEquals(combinedString, ResidentList.DEFAULT_LIST);

        // two residents in current, remove one of them
        String constructorString1 = TypicalPersons.ALICE.getName().toString()
                + ", "
                + TypicalPersons.BOB.getName().toString();
        String constructorString2 = TypicalPersons.ALICE.toString()
                + ", "
                + TypicalPersons.BOB.toString();
        residentList = new ResidentList(constructorString1, constructorString2);
        combinedString = residentList.getRemovedDisplayString(toRemove);
        assertEquals(combinedString, TypicalPersons.BOB.getName().toString());

        // three residents in current, remove one of them
        constructorString1 = TypicalPersons.ALICE.getName().toString()
                + ", "
                + TypicalPersons.BOB.getName().toString()
                + ", "
                + TypicalPersons.CARL.getName().toString();
        constructorString2 = TypicalPersons.ALICE.toString()
                + ", "
                + TypicalPersons.BOB.toString()
                + ", "
                + TypicalPersons.CARL.toString();
        residentList = new ResidentList(constructorString1, constructorString2);
        combinedString = residentList.getRemovedDisplayString(toRemove);
        assertEquals(combinedString, TypicalPersons.BOB.getName().toString() + ", "
                + TypicalPersons.CARL.getName().toString());

        // three residents in current, remove three of them
        toRemove.add(TypicalPersons.BOB);
        toRemove.add(TypicalPersons.CARL);

        residentList = new ResidentList(constructorString1, constructorString2);
        combinedString = residentList.getRemovedDisplayString(toRemove);
        assertEquals(combinedString, ResidentList.DEFAULT_LIST);
    }
}

