package tutoraid.model.student;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.ArrayList;

import org.junit.jupiter.api.Test;

import tutoraid.testutil.Assert;

public class ProgressListTest {

    @Test
    public void constructor_null_throwsNullPointerException() {
        Assert.assertThrows(NullPointerException.class, () -> new Progress(null));
    }

    @Test
    public void isValidProgressList() {
        // null progress list
        Assert.assertThrows(NullPointerException.class, () -> ProgressList.isValidProgressList(null));

        // invalid progress list
        ArrayList<String> moreThanTenElements = new ArrayList<>();
        for (int i = 0; i < 11; i++) { // too many elements
            moreThanTenElements.add("abc"); // valid progress description
        }
        assertFalse(ProgressList.isValidProgressList(moreThanTenElements));

        ArrayList<String> nullElements = new ArrayList<>();
        nullElements.add(null);
        assertFalse(ProgressList.isValidProgressList(nullElements));

        ArrayList<String> invalidProgress = new ArrayList<>();
        invalidProgress.add("abc"); // valid progress description
        invalidProgress.add("exam*"); // invalid progress description
        assertFalse(ProgressList.isValidProgressList(invalidProgress));

        // valid progress list
        ArrayList<String> lessThanTenElements = new ArrayList<>();
        for (int i = 0; i < 9; i++) { // less than 10 elements
            moreThanTenElements.add("abc"); // valid progress description
        }
        assertTrue(ProgressList.isValidProgressList(lessThanTenElements));

        ArrayList<String> tenElements = new ArrayList<>();
        for (int i = 0; i < 10; i++) { // 10 elements
            moreThanTenElements.add("abc"); // valid progress description
        }
        assertTrue(ProgressList.isValidProgressList(tenElements));
    }
}
