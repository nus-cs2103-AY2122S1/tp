package seedu.address.model.person.interests;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.testutil.Assert.assertThrows;

import java.util.ArrayList;

import org.junit.jupiter.api.Test;

import seedu.address.commons.core.index.Index;

public class InterestsListTest {

    private InterestsList interestsList = new InterestsList();

    @Test
    public void constructor_null_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> new InterestsList(null));
    }

    @Test
    public void addInterest_null_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> interestsList.addInterest(null));
    }

    @Test
    public void addUniqueInterest_success() {
        Interest i1 = new Interest("Watching anime");
        Interest i2 = new Interest("Reading manga");
        Interest i3 = new Interest("getting drunk");

        ArrayList<Interest> expectedList = new ArrayList<>();
        expectedList.add(i1);
        expectedList.add(i2);
        expectedList.add(i3);
        InterestsList expectedInterestsList = new InterestsList(expectedList);

        InterestsList test = new InterestsList();
        test.addInterest(i1);
        test.addInterest(i2);
        test.addInterest(i3);
        assertEquals(test, expectedInterestsList);

    }

    @Test
    public void addDuplicateInterest_throwsIllegalArgumentException() {
        Interest i1 = new Interest("Watching anime");
        Interest i2 = new Interest("Reading manga");
        Interest i3 = new Interest("getting drunk");

        InterestsList test = new InterestsList();
        test.addInterest(i1);
        test.addInterest(i2);
        test.addInterest(i3);

        assertThrows(IllegalArgumentException.class, () -> test.addInterest(i2));
    }

    @Test
    public void setInterest() {
        Interest i1 = new Interest("Watching anime");
        Interest i2 = new Interest("Reading manga");
        Interest i3 = new Interest("getting drunk");
        Interest i4 = new Interest("Having a life");

        ArrayList<Interest> expectedList = new ArrayList<>();
        expectedList.add(i1);
        expectedList.add(i2);
        expectedList.add(i4);
        InterestsList expectedInterestsList = new InterestsList(expectedList);

        InterestsList updatedInterestsList = new InterestsList();
        updatedInterestsList.addInterest(i1);
        updatedInterestsList.addInterest(i2);
        updatedInterestsList.addInterest(i3);
        updatedInterestsList.setInterest(i4, 2);

        assertEquals(updatedInterestsList, expectedInterestsList);
    }

    @Test
    public void setDuplicateInterest_throwsIllegalArgumentException() {
        Interest i1 = new Interest("Watching anime");
        Interest i2 = new Interest("Reading manga");
        Interest i3 = new Interest("getting drunk");
        Interest i4 = new Interest("Having a life");

        InterestsList test = new InterestsList();
        test.addInterest(i1);
        test.addInterest(i2);
        test.addInterest(i3);
        test.addInterest(i4);

        assertThrows(IllegalArgumentException.class, () -> test.setInterest(i4, 3));

    }

    @Test
    public void checkDuplicate() {
        Interest i1 = new Interest("Watching anime");
        Interest i2 = new Interest("Reading manga");
        Interest i3 = new Interest("getting drunk");
        Interest i4 = new Interest("Having a life");

        InterestsList interestsList = new InterestsList();

        assertFalse(interestsList.checkDuplicate(i4)); // nothing in the list to begin with

        interestsList.addInterest(i1);
        interestsList.addInterest(i2);
        interestsList.addInterest(i3);

        assertFalse(interestsList.checkDuplicate(i4)); // different interest

        assertTrue(interestsList.checkDuplicate(i3)); // same interest

    }

    @Test
    public void getInterestTest() {
        Interest i = new Interest("Flying");
        interestsList.addInterest(i);
        assertEquals(i, interestsList.getInterest(Index.fromZeroBased(0)));
    }

    @Test
    public void equalsTest() {
        Interest i1 = new Interest("Watching anime");
        Interest i2 = new Interest("Reading manga");
        Interest i3 = new Interest("getting drunk");

        interestsList.addInterest(i1);
        interestsList.addInterest(i2);
        interestsList.addInterest(i3);

        InterestsList otherList = new InterestsList();
        otherList.addInterest(i1);
        otherList.addInterest(i2);
        otherList.addInterest(i3);

        assertEquals(interestsList, otherList);
    }

    @Test
    public void toStringNoNumberingTest() {
        Interest i1 = new Interest("Watching anime");
        Interest i2 = new Interest("Reading manga");
        Interest i3 = new Interest("getting drunk");
        Interest i4 = new Interest("Having a life");

        InterestsList test = new InterestsList();
        String expectedStringEmpty = "";
        String actualStringEmpty = test.toStringNoNumbering();
        assertEquals(actualStringEmpty, expectedStringEmpty); //empty interestsList

        test.addInterest(i1);
        test.addInterest(i2);
        test.addInterest(i3);
        test.addInterest(i4);

        String expectedString = "Watching anime, Reading manga, getting drunk, Having a life";
        String actualString = test.toStringNoNumbering();

        assertEquals(expectedString, actualString);
    }
}
